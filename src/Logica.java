import processing.core.PApplet;

public class Logica {
	
	private Casa casa;
	private PApplet app;
	public Logica(PApplet _app){
		System.out.println("Initializing Logic");
		app = _app;
		init();
	}
	
	public void init(){
		System.out.println("Initializing Logic Classes");
		casa = new Casa(app);
		
	}
	
	
}