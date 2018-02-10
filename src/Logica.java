import java.util.ArrayList;

import processing.core.PApplet;

public class Logica {
	
	private ArrayList<Casa> casas;
	private PApplet app;
	private Bateria bateria;
	public Logica(PApplet _app){
		System.out.println("Initializing Logic");
		app = _app;
		init();
	}
	
	public void init(){
		System.out.println("Initializing Logic Classes");
		initCasas();
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
	}
	
	//---------------------------------
	//Bateria
	//---------------------------------
	public void initBateria() {
		bateria = new Bateria(app);
	}
	
}