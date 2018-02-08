import processing.core.PApplet;

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
		habitantes = (int)app.random(1,5);
		System.out.println("Numero de Habitantes en Casa: "+habitantes);
		asignarConsumo();
	}
	
	public void asignarConsumo() {
		//TODO: Calcular el consumo dependiendo de los habitantes
	}
	
	public void consumirTurno() {
		//TODO: Resta el consumo a la energia total, en caso de que la energia local se termine, tomar la de la bateria
	}
	
	public void verificarVida() {
		//TODO: Verificar si queda energia para sustentar la casa y si no muere
	}
	
	
	
}
