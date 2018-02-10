import processing.core.PApplet;

public class Bateria {
	private int capacidad;
	private int carga;
	private int caso;
	private PApplet app;

	public Bateria(int _capacidad, PApplet _app) {
		// TODO Auto-generated constructor stub
		capacidad = _capacidad;
		_capacidad = 400;
		caso = 0;
		app = _app;
	}

	public void recibirCarga(int _energia) {
			carga += _energia;
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
			break;

		case 1:
			if (capacidad == 4000)
				capacidad = 6000;
			break;

		case 2:
			if (capacidad == 6000)
				capacidad = 8000;
			break;

		case 3:
			if (capacidad == 8000)
				capacidad = 10000;
			break;

		case 4:
			if (capacidad == 10000)
				capacidad = 12000;
			break;
		}
	}

	public int getCaso() {
		return caso;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
}
