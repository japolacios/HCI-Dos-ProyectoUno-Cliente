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
		imageMode(CENTER);
		textMode(CENTER);
		textAlign(CENTER);
		fill(0,0,0);
		init();
	}

	public void draw() {
		background(255, 255, 255);
		logica.pintar();
	}
	
	public void mouseClicked() {
		logica.clcik();
	}
	
	public void keyPressed() {
		  logica.keyPressed();
		  }
}