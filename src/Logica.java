import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class Logica implements Observer {

	// Atributos
	private ArrayList<Casa> casas;
	private PApplet app;
	private Bateria bateria;
	private Communicacion com;
	private Ui ui;
	private int col;
	private boolean turn;
	private int scene;
	private boolean addCasaEnabled;
	private boolean askEnergyEnabled;

	// Datos
	int capacidadTotal = 0;
	int casasTotales = 0;
	int habitantesTotales = 0;
	int energiaTotal = 0;
	int energiaBateria = 0;
	int consumoTotal = 0;

	// Other Vars
	private int capacidadInicialBateria = 2000;

	public Logica(PApplet _app) {
		System.out.println("Initializing Logic");
		app = _app;
		init();
	}

	public void changeScene(int _s) {
		scene = _s;
		ui.setScene(_s);
	}

	public void init() {
		System.out.println("Initializing Logic Classes");
		com = new Communicacion();
		new Thread(com).start();
		com.setBoss(this);
		com.setId(1);
		addCasaEnabled = false;
		askEnergyEnabled = false;
		col = 0;

		ui = new Ui(app, 0);
		//Change for Start
		scene = 1;
		changeScene(scene);
		initCasas();
		initBateria();
		collectData();
		sendCityData();
		turn = true;
		// newCasa();
	}

	// ---------------------------------
	// GamePlay
	// ---------------------------------
	public void generarConsumo() {
		// Recorro el array de casa para generar el consumo al finalizar cada turno
		for (int i = 0; i < casas.size(); i++) {
			Casa tempCasa = casas.get(i);
			if (tempCasa.consumirEnergia() == true) {
				// La casa sola no se pudo autosustentar, necesita energia de la bateria
				int energiaPendiente = tempCasa.getEnergiaPendiente();
				if (bateria.getCarga() - energiaPendiente >= 0) {
					// La energia de la bateria si alcanza para sustentar lo que le falta a la casa
					tempCasa.setEnergiaPendiente(0);
					bateria.donarEnergia(energiaPendiente);
				} else {
					// la energia de la bateria no alcanza para sostener la casa, debe morir gente
					// D:!
					int cuantosMueren = (int) (-1 * (bateria.getCarga() - energiaPendiente)) / 50;
					if (cuantosMueren > tempCasa.getHabitantes()) {
						// La energia no alcanza para nadie en la casa ni la casa... se destruye
						casas.remove(i);
						System.out.println("Casa " + i + " removida");
					} else {
						// Solo muere gente :(
						tempCasa.setHabitantes(tempCasa.getHabitantes() - cuantosMueren);
						tempCasa.asignarConsumo();
						System.out.println("Murieron " + cuantosMueren + " habitantes en la casa " + i);
					}
				}
			}

		}
		collectData();
	}

	public void terminaraTurno() {
		generarConsumo();
		if (casasTotales <= 0) {
			sendDead();
		} else {
			sendImDone();
		}
	}

	public void sendDead() {
		Validation tempVal = new Validation(true, 6, 0);

		try {
			com.enviar(tempVal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendImDone() {
		Validation tempVal = new Validation(true, 5, 0);

		try {
			com.enviar(tempVal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ---------------------------------
	// General Data
	// ---------------------------------
	// Por el momento solo hace el print del general de los datos
	public void collectData() {
		capacidadTotal = 0;
		casasTotales = casas.size();
		habitantesTotales = 0;
		energiaTotal = 0;
		energiaBateria = bateria.getCarga();
		consumoTotal = 0;

		for (int i = 0; i < casas.size(); i++) {
			Casa tempCasa = casas.get(i);
			capacidadTotal = capacidadTotal + tempCasa.getCapacidad();
			habitantesTotales = habitantesTotales + tempCasa.getHabitantes();
			energiaTotal = energiaTotal + tempCasa.getEnergia();
			consumoTotal = consumoTotal + tempCasa.getConsumo();
		}

		capacidadTotal = capacidadTotal + bateria.getCapacidad();
		energiaTotal = energiaTotal + bateria.getCarga();

		System.out.println("Capacidad total: " + capacidadTotal + " - Casas totales: " + casasTotales
				+ " - Habitantes totales: " + habitantesTotales + " - Energia Total: " + energiaTotal
				+ " - Energia Bateria: " + energiaBateria + " - Consumo Total: " + consumoTotal);
	}

	// ---------------------------------
	// Casas
	// ---------------------------------
	public void initCasas() {
		Casa casa;
		casas = new ArrayList<Casa>();
		casa = new Casa(app, app.width / 2, app.height / 2);
		casas.add(casa);
	}

	public void newCasa() {
		Casa casa;
		casa = new Casa(app, app.mouseX, app.mouseY);
		casas.add(casa);
		System.out.println("Casa agregada");
	}

	// ---------------------------------
	// Bateria
	// ---------------------------------
	public void initBateria() {
		bateria = new Bateria(capacidadInicialBateria, app);
	}

	public void update(Observable o, Object arg) {
		if (arg instanceof Validation) {
			Validation val = (Validation) arg;
			//Enable
			if (val.getType() == 1) {
				turn = true;
			}
			//Disable
			if (val.getType() == 2) {
				turn = false;
			}
			//GetEnergy
			if (val.getType() == 4) {
				bateria.recibirCarga(val.getEnergy());
			}
			//Someone died
			if (val.getType() == 7) {
				//GAMEOVER
			}
			//Start Game
			if (val.getType() == 8) {
				changeScene(1);
			}
		}
	}

	public void pintar() {
		sceneLogic();
		ui.pintarUi();
		gameScreen();
	}

	public void sceneLogic() {
		screenOne();
	}

	public void screenOne() {
		if (scene == 1) {
			if (app.mouseX >= 780 && app.mouseX <= 1140 && app.mouseY >= 920 && app.mouseY <= 1020) {
				changeScene(2);
			}

		}
		if (scene == 2) {
			if (!(app.mouseX >= 780 && app.mouseX <= 1140 && app.mouseY >= 920 && app.mouseY <= 1020)) {
				changeScene(1);
			}
		}
	}

	public void gameScreen() {
		if (scene == 3) {
			ui.pintarCasas(casas);
			ui.pintarBateria(bateria.getCaso());
			pintarDatos();
			pintarSecundarios();
		}
	}

	public void pintarDatos() {
		app.textSize(20);
		ui.pintarPoblacion(habitantesTotales);
		ui.pintarNumCasas(casasTotales);
		ui.pintarEnergia(energiaTotal, capacidadTotal);
	}

	public void clcik() {
		if (scene == 2) {
			changeScene(3);
		}
		if (scene == 3) {
			clickScene3();
		}
	}

	public void clickScene3() {
		// Boton Construir
		if (app.dist(app.mouseX, app.mouseY, 1765, 777) <= 60 && turn == true) {
			if (addCasaEnabled == false) {
				addCasaEnabled = true;
				return;
			} else {
				addCasaEnabled = false;
				return;
			}
		} else {
			if (addCasaEnabled == true) {
				newCasa();
				addCasaEnabled = false;
				collectData();
				return;
			}
		}
		// Ventana de pedir energia
		if (app.dist(app.mouseX, app.mouseY, 1765, 961) <= 60 && turn == true) {
			if (askEnergyEnabled == false) {
				askEnergyEnabled = true;
				return;
			} else {
				// Metodo Pedir energia
				askEnergyEnabled = false;
				return;
			}
		}
	}

	public void pintarSecundarios() {
		if (addCasaEnabled == true) {
			app.text("Agregar Casa nueva Aqui", app.mouseX, app.mouseY + 25);
			// Cobrar Casa
		} else {
			// Nadita
		}

		if (askEnergyEnabled == true) {
			// Pintar cosito energia
			ui.pintarPedirEnergia();
		}
	}

	public void keyPressed() {
		// Terminar turno
		if (app.key == 't' && turn == true) {
			System.out.println("Turno Terminado");
			terminaraTurno();
		}
		if (app.key == 'u' && turn == true) {
			upgradeBateria();
		}
	}

	public void upgradeBateria() {
		int caso = bateria.getCaso();
		if (caso <= 2) {
			System.out.println("Upgradeseando Bateria");
			if(energiaTotal>= bateria.getCosto()) {
			bateria.mejorar();
			} else {
				System.out.println("No hay luka");
			}
		}
	}

	public void sendCityData() {
		collectData();
		City cityTemp = new City(energiaTotal, habitantesTotales, casasTotales, capacidadTotal);
		try {
			com.enviar(cityTemp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendEnergyRequest() {
		Validation tempVal = new Validation(true, 3, 0);
		// 25%
		if (true) {
			tempVal.setEnergy((int) (capacidadTotal * 0.25));
		}
		// 50%
		if (true) {
			tempVal.setEnergy((int) (capacidadTotal * 0.5));
		}
		// 75%
		if (true) {
			tempVal.setEnergy((int) (capacidadTotal * 0.75));
		}
		// 100%
		if (true) {
			tempVal.setEnergy((int) (capacidadTotal * 1));
		}
		// 125%
		if (true) {
			tempVal.setEnergy((int) (capacidadTotal * 1.25));
		}
		// 150%
		if (true) {
			tempVal.setEnergy((int) (capacidadTotal * 1.50));
		}
		// 175%
		if (true) {
			tempVal.setEnergy((int) (capacidadTotal * 1.75));
		}
		// 200%
		if (true) {
			tempVal.setEnergy((int) (capacidadTotal * 2));
		}

		try {
			com.enviar(tempVal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}