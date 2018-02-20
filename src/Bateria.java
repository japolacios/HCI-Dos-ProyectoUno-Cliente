import processing.core.PApplet;

public class Bateria {
	private int capacidad;
	private int carga;
	private int caso;
	private PApplet app;
	private int costo;

	public Bateria(int _capacidad, PApplet _app) {
		// TODO Auto-generated constructor stub
		capacidad = _capacidad;
		//_capacidad = 400;
		caso = 0;
		app = _app;
		costo = 1500;
		//carga = 2000;
	}

	public void recibirCarga(int _energia) {
			carga += _energia;
			if(carga > capacidad) {
				carga = capacidad;
			}
	}

	public void donarEnergia(int _energia) {
		carga -= _energia;
	}
	
	public int getCarga() {
		return carga;
	}

	public void mejorar() {
		// TODO: mejorar la capacidad de almacenamiento de la bateria
		switch (caso) {
		case 0:
			if (capacidad == 2000)
				capacidad = 4000;
			System.out.println("Bateria Nivel 2");
			break;

		case 1:
			if (capacidad == 4000)
				capacidad = 6000;
			System.out.println("Bateria Nivel 3");
			break;

		case 2:
			if (capacidad == 6000)
				capacidad = 8000;
			System.out.println("Bateria Nivel 4");
			break;
		}
		calcularCosto();
	}

	public int getCaso() {
		return caso;
	}
	
	public void setCaso(int _caso) {
		caso = _caso;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void calcularCosto() {
		if (caso == 0) {
			costo = 1500;
		}
		if (caso == 1) {
			costo = 3200;
		}
		if (caso == 2) {
			costo = 4500;
		}
	}
	
	public int getCosto() {
		return costo;
	}
}
