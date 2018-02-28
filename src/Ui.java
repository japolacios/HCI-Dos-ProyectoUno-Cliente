import java.util.ArrayList;

import processing.core.*;

public class Ui {

	private PApplet app;
	private int estacion;
	private float posX, posY;
	private int scene;
	// Images

	// HUD & Interface
	private PImage splash;
	private PImage startBtn;
	private PImage startBtnOver;
	private PImage mainHud;
	private PImage btnCon;
	private PImage btnConOver;
	private PImage btnEn;
	private PImage btnEnOver;
	private PImage energyW;
	private PImage primavera, verano, otono, invierno;

	// Houses & other assets
	private ArrayList<PImage> casa;
	private ArrayList<PImage> bateria;
	private ArrayList<PImage> img_estacion;

	public Ui(PApplet app, int estacion) {
		this.app = app;
		this.estacion = estacion;
		init();
		System.out.println("Se inicializa todo del Ui");

	}

	public void init() {
		casa = new ArrayList<>();
		bateria = new ArrayList<>();
		img_estacion = new ArrayList<>();
		loadImages();
		scene = 1;
		//estacion = 0;
		System.out.println("se cargan todas las imagenes");
	}

	public void loadImages() {
		loadHUD();
		loadHouses();
		loadBattery();
		loadEstaciones();

	}

	public void loadHouses() {

		for (int i = 0; i < 1; i++) {
			PImage casaTemp = new PImage();
			casaTemp = app.loadImage("buildings/casa" + i + ".png");
			casa.add(casaTemp);
		}
		System.out.println("se cargan img de casas");
	}

	public void loadBattery() {

		for (int i = 0; i < 2; i++) {
			PImage bateriaTemp = new PImage();
			bateriaTemp = app.loadImage("buildings/edificio" + (i + 1) + ".png");
			bateria.add(bateriaTemp);
		}
		System.out.println("se cargan img de baterias");
	}

	public void loadEstaciones() {
		for (int i = 0; i < 4; i++) {
			PImage estacionTemp = new PImage();
			estacionTemp = app.loadImage("stations/e" + (i) + ".png");
			img_estacion.add(estacionTemp);
		}
		System.out.println("se cargan img de estaciones");
	}

	public void loadHUD() {
		splash = app.loadImage("hud/splash.png");
		startBtn = app.loadImage("hud/btnstarton.png");
		startBtnOver = app.loadImage("hud/btnstartoff.png");
		mainHud = app.loadImage("hud/mainHud.png");
		btnCon = app.loadImage("hud/btnConstruoff.png");
		btnConOver = app.loadImage("hud/btnConstruon.png");
		btnEn = app.loadImage("hud/btnEneroff.png");
		btnEnOver = app.loadImage("hud/btnEneron.png");
		energyW = app.loadImage("hud/pedircarga.png");
		primavera = app.loadImage("fondo/Primavera.png");
		otono = app.loadImage("fondo/Otono.png");
		verano = app.loadImage("fondo/Verano.png");
		invierno = app.loadImage("fondo/Invierno.png");
	}

	public void pintarCasas(ArrayList<Casa> _casas) {
		for (int i = 0; i < _casas.size(); i++) {
			app.image(casa.get(0), _casas.get(i).getX(), _casas.get(i).getY());
		}
	}

	public void pintarBateria(int _lvl) {
		app.image(bateria.get(_lvl), app.width / 5, app.height / 3);
		//System.out.println("El nivel de la Bateria es: "+ _lvl);
	}


	
	public void pintarUi() {
		if (scene == 0) {
			pintarSplash();
			app.textSize(32);
			app.text("Esperando al Servidor...", app.width / 2, (app.height / 8) * 7);
		}

		if (scene == 1) {
			pintarSplash();
			app.image(startBtn, app.width / 2, app.height / 2);
		}

		if (scene == 2) {
			pintarSplash();
			app.image(startBtnOver, app.width / 2, app.height / 2);
		}
		if (scene == 3) {
			pintarMainGameHud();
		}
	}

	public void pintarSplash() {
		app.image(splash, app.width / 2, app.height / 2);
	}

	public void pintarMainGameHud() {
		
		if (estacion == 0) {
			app.image(primavera, app.width/2, app.height/2);
		}
		if (estacion == 1) {
			app.image(verano, app.width/2, app.height/2);
		}
		if (estacion == 2) {
			app.image(otono, app.width/2, app.height/2);
		}
		if (estacion == 3) {
			app.image(invierno, app.width/2, app.height/2);
		}
		
		//System.out.println("Estacioncita: " + estacion	);
		
		app.image(mainHud, app.width / 2, app.height / 2);
		
		pintarIconoEstacion();
		pintarBotones();
	}

	public void pintarIconoEstacion() {
		app.image(img_estacion.get(estacion), 1725, 90);
	}

	public void pintarBotones() {

		if (app.dist(app.mouseX, app.mouseY, 1765, 777) <= 60) {
			app.image(btnConOver, 1765, 777);
		} else {
			app.image(btnCon, 1765, 777);
		}

		if (app.dist(app.mouseX, app.mouseY, 1765, 961) <= 60) {
			app.image(btnEnOver, 1765, 961);
		} else {
			app.image(btnEn, 1765, 961);
		}
	}

	public void pintarFondo() {
		if(estacion == 0) {
			app.image(verano, app.width/2, app.height/2);
		}
	}
	
	public void pintarEnergia(int _energia, int _capacidad) {
		app.text(_energia + "/" + _capacidad, 270, 140);
		//System.out.println("CAPACIDAD: " + _capacidad);
	}

	public void pintarPoblacion(int _poblacion) {
		app.text(_poblacion, 1070, 140);
	}

	public void pintarNumCasas(int _numCasas) {
		app.text(_numCasas, 670, 140);
	}
	
	public void pintarPedirEnergia() {
		app.image(energyW, app.width/2, app.height/2);
	}
	
	public int getScene() {
		return scene;
	}

	public void setScene(int _s) {
		scene = _s;
	}
}