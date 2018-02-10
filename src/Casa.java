import processing.core.*;

public class Casa {
	private int habitantes;
	private int energia;
	private int capacidad;
	private int consumo;
	private PApplet app;
	public Casa(PApplet app) {
		System.out.println("Initializing New Casa");
		this.app = app;
		init();
	}
	
	public void init() {
		energia = 300;
		capacidad = 400;
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
	
	public void consumirTurno() {
		//TODO: Resta el consumo a la energia total, en caso de que la energia local se termine, tomar la de la bateria
	}
	
	public void verificarVida() {
		if (energia < consumo) {
			System.out.println("Casa muere");
		} else {
			System.out.println("Casa vive otro dia");
		}
	}
}