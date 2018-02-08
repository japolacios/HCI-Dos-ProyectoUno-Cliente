package proyectoUnoHciDos;



public class Casa {
	private int habitantes;
	private int energia;
	private int capacidad;
	private int consumo;
	public Casa() {	
		init();
	}
	
	public void init() {
		energia = 300;
		capacidad = 400;
		asignarPoblacion();
	}
	
	
	public void asignarPoblacion() {
		//TODO: darle a habitantes un numero random entre 1 y 5
		asignarConsumo();
	}
	
	public void asignarConsumo() {
		//TODO: Calcular el consumo dependiendo de los habitantes
	}
	
	public void consumirTurno() {
		//TODO: Resta el consumo a la energia total, en caso de que la energia local se termine, tomar la de la bateria
	}
	
	public void verificarVidaa() {
		//TODO: Verificar si queda energia para sustentar la casa y si no muere
	}
	
	
	
}
