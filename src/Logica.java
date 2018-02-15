import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class Logica implements Observer {
	
	//Atributos
	private ArrayList<Casa> casas;
	private PApplet app;
	private Bateria bateria;
	private Communicacion com;
	private Ui ui;
	private int col;
	private boolean turn;
	
	//Other Vars
	private int capacidadInicialBateria = 2000;
	
	public Logica(PApplet _app){
		System.out.println("Initializing Logic");
		app = _app;
		init();
	}
	
	public void init(){
		System.out.println("Initializing Logic Classes");
		com = new Communicacion();
		new Thread(com).start();
		com.setBoss(this);
		com.setId(1);

		col = 0;
		
		ui = new Ui(app,1);
		initCasas();
		initBateria();
		collectData();
		terminaraTurno();
		collectData();
		newCasa();
		newCasa();
		newCasa();
		newCasa();
	}
	
	//---------------------------------
	//GamePlay
	//---------------------------------
	public void generarConsumo() {
		//Recorro el array de casa para generar el consumo al finalizar cada turno
		for (int i = 0; i < casas.size(); i++) {
			Casa tempCasa = casas.get(i);
			if(tempCasa.consumirEnergia() == true) {
				//La casa sola no se pudo autosustentar, necesita energia de la bateria
				int energiaPendiente = tempCasa.getEnergiaPendiente();
				if(bateria.getCarga() - energiaPendiente >= 0) {
					//La energia de la bateria si alcanza para sustentar lo que le falta a la casa
					tempCasa.setEnergiaPendiente(0);
					bateria.donarEnergia(energiaPendiente);
				} else {
					//la energia de la bateria no alcanza para sostener la casa, debe morir gente D:!
					int cuantosMueren = (int) (-1*(bateria.getCarga() - energiaPendiente))/50;
					if(cuantosMueren > tempCasa.getHabitantes()) {
						//La energia no alcanza para nadie en la casa ni la casa... se destruye
						casas.remove(i);
						System.out.println("Casa "+i+" removida");
					} else {
						//Solo muere gente :(
						tempCasa.setHabitantes(tempCasa.getHabitantes()-cuantosMueren);
						tempCasa.asignarConsumo();
						System.out.println("Murieron "+ cuantosMueren + " habitantes en la casa "+ i);
					}
				}
			}
			
		}
	}

	public void terminaraTurno() {
		generarConsumo();
	}
	//---------------------------------
	//General Data
	//---------------------------------
	// Por el momento solo hace el print del general de los datos
	public void collectData() {
		int capacidadTotal = 0;
		int casasTotales = casas.size();
		int habitantesTotales = 0;
		int energiaTotal = 0;
		int energiaBateria = bateria.getCarga();
		int consumoTotal = 0;
		
		for (int i = 0; i < casas.size(); i++) {
			Casa tempCasa = casas.get(i);
			capacidadTotal = capacidadTotal + tempCasa.getCapacidad();
			habitantesTotales = habitantesTotales + tempCasa.getHabitantes();
			energiaTotal = energiaTotal + tempCasa.getEnergia();	
			consumoTotal = consumoTotal + tempCasa.getConsumo();
		}
		
		capacidadTotal = capacidadTotal + bateria.getCapacidad();
		energiaTotal = energiaTotal + bateria.getCarga();
		
		System.out.println("Capacidad total: "+capacidadTotal+ " - Casas totales: "+ casasTotales+ " - Habitantes totales: "
				+ habitantesTotales+ " - Energia Total: "+energiaTotal+" - Energia Bateria: "+energiaBateria+" - Consumo Total: "
				+consumoTotal);
	}
	
	//---------------------------------
	//Casas
	//---------------------------------
	public void initCasas() {
		Casa casa;
		casas = new ArrayList<Casa>();
		casa = new Casa(app);
		casas.add(casa);
	}
	
	public void newCasa() {
		Casa casa;
		casa = new Casa(app);
		casas.add(casa);
		System.out.println("Casa agregada");
	}
	
	//---------------------------------
	//Bateria
	//---------------------------------
	public void initBateria() {
		bateria = new Bateria(capacidadInicialBateria,app);
	}
	
	
	
	public void update(Observable o, Object arg) {
		if (arg instanceof Validation) {
			Validation val = (Validation) arg;
			if (val.isCheck()) {
				if (val.getType().contains("start")) {
					if (col == 0) {
						col = 150;
					} else {
						col = 0;
					}
				} else if (val.getType().contains("yourTurn")) {
					turn = true;
				}
			} else {
				if (val.getType().contains("yourTurn")) {
					turn = false;
				}
			}
		}
	}
	
	public void pintar() {
		ui.pintarCasas(casas);
	}
	
}