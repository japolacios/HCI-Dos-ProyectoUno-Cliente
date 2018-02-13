import java.util.ArrayList;

import processing.core.*;

public class Ui {

	private PApplet app;
	private int estacion;
	private float posX, posY;
	
	private ArrayList<PImage> casa;
	private ArrayList<PImage> bateria;
	private ArrayList<PImage> img_estacion;

	
	public Ui (PApplet app, int estacion){
		this.app = app;
		this.estacion = estacion;
		posX = app.random(app.width);
		posY = app.random(app.height);
		init();
		System.out.println("Se inicializa todo del Ui");
	
	}
	
	public void init() {
		casa = new ArrayList<>();
		bateria = new ArrayList<>();		
		img_estacion = new ArrayList<>();
		loadImages();
		System.out.println("se cargan todas las imagenes");
	}
	
	public void loadImages() {
		/*Cargar 5 imagenes de casas*/
		for (int i = 0; i < 4; i++) {
		PImage casaTemp = new PImage(); 
		casaTemp = app.loadImage("casa_" + i + ".png");
		casa.add(casaTemp);
		}
		System.out.println("se cargan img de casas");
		
		/*Cargar 5 imagenes de baterias*/
		for (int i = 0; i < 10; i++) {
		PImage bateriaTemp = new PImage(); 
		bateriaTemp = app.loadImage("casa_" + i + ".png");
		casa.add(bateriaTemp);
		}
		System.out.println("se cargan img de baterias");
		/*Cargar 5 imagenes de arboles*/
		for (int i = 0; i < 4; i++) {
		PImage tempEstaciones = new PImage(); 
		tempEstaciones = app.loadImage("casa_" + i + ".png");
		casa.add(tempEstaciones);
		}
		System.out.println("se cargan img de estaciones");
	}
	
	public void pintarCasas( ArrayList<Casa> _casas) {
			
		for (int i = 0; i < _casas.size(); i++) {
			app.image(casa.get(estacion), posX, posY); 
		}
	}
}