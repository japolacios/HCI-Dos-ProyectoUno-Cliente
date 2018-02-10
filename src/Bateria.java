public class Bateria {
	private int capacidad;
	private int carga;
	public int caso;

	public Bateria(int _capacidad) {
		// TODO Auto-generated constructor stub
		capacidad = _capacidad;
		_capacidad = 400;
		caso = 0;
	}

	public void recibirCarga(int _energia) {
		// TODO: Recibe la energia para cargar la bateria
			carga += _energia;
	}

	public void donarEnergia(int _energia) {
		// TODO: envia energia para las casas y la descuenta de la carga
		carga -= _energia;
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

}
