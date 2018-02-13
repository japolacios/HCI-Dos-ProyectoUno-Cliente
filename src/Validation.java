import java.io.Serializable;

public class Validation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean check;
    private String type;
    
	public Validation(boolean check, String type) {
		super();
		this.check = check;
		this.type = type;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}