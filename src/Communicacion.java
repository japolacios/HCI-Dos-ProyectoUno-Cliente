import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observer;

public class Communicacion implements Runnable {

	private Socket s;
	private Observer boss;
	private int id;

	private final static int PORT = 5000;
	private final static String ADDRESS = "172.30.178.47";
	private boolean life;

	public Communicacion() {
		s = null;
		life = true;
	}

	@Override
	public void run() {
		while (life) {

			try {
				if (s == null) {
					s = new Socket(InetAddress.getByName(ADDRESS), PORT);
				} else {
					recibir();
				}

				Thread.sleep(500);

			} catch (IOException e) {
				System.out.println("No hay servidor encendido");
				life = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void recibir() throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		Object o = in.readObject();
		boss.update(null, o);
	}

	public void enviar(Object o) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		out.writeObject(o);
		out.flush();
	}

	public void setBoss(Observer boss) {
		this.boss = boss;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}