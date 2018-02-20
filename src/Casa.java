import java.io.Serializable;

import processing.core.*;

public class Casa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int habitantes;
	private int energia;
	private int capacidad;
	private int consumo;
	private int energiaPendiente;
	private float x,y;
	private PApplet app;
	public Casa(PApplet app, float _x, float _y) {
		System.out.println("Initializing New Casa");
		this.app = app;
		x = _x;
		y = _y;
		init();
	}
	
	public void init() {
		energia = 300;
		capacidad = 300;
		asignarPoblacion();
	}
	
	public void asignarPoblacion() {
		habitantes = (int)app.random(3,6);
		System.out.println("Numero de Habitantes en Casa: "+habitantes);
		asignarConsumo();
	}
	
	public void asignarConsumo() {
		consumo = 100 + (habitantes * 50);
		System.out.println("consumo de energia por casa:" + consumo);
	}
	
	
	//Verifica si la energia que tiene le alcanza para el autosustento, si no, guarda lo que le queda faltando
	//en energiaPendiente para que se le pueda dar desde la bateria
	public boolean consumirEnergia() {
		boolean needBateria = false;
		//Si no le alcanza su auto energia
		if (energia - consumo <= 0) {
			needBateria = true;
			energiaPendiente = -1*(energia - consumo);
			energia = 0;
		} else {
			//Si le alcanza la energia -> Se resta
			energia = energia - consumo;
		}
		return needBateria;
	}
	
	
	public void killPeople() {
		
	}
	
	public int getEnergiaPendiente() {
		return energiaPendiente;
	}
	
	public void setEnergiaPendiente(int _newPendiente) {
		energiaPendiente = _newPendiente;
	}
	
	public int getHabitantes() {
		return habitantes;
	}
	
	public void setHabitantes( int _habitantes) {
		habitantes = _habitantes;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public int getConsumo() {
		return consumo;
	}
	
	public int getEnergia() {
		return energia;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}