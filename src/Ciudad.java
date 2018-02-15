import processing.core.PApplet;

public class Ciudad extends PApplet {

	private Logica logica;

	public static void main(String[] args) {
		PApplet.main("Ciudad");
	}

	public void settings() {
		System.out.println("Set Canvas Size");
		
		size(1920, 1080);
	}

	public void init() {
		System.out.println("Initializing Classes");
		logica = new Logica(this);
	}

	public void setup() {
		System.out.println("Initializing App");
		init();
	}

	public void draw() {
		background(0, 0, 0);
		logica.pintar();
	}
}