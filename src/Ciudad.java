import processing.core.PApplet;

public class Ciudad extends PApplet{
	
	private Logica logica;
	
	public static void main(String[] args) {
		PApplet.main("Ciudad");

	}

	public void settings() {
		System.out.println("Set Canvas Size");
		size(1024, 760);
	}

	public void init() {
		logica = new Logica(this);
	}
	
	public void setup() {

	}
	
	public void draw() {

	}
}
