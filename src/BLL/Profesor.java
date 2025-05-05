package BLL;

public class Profesor extends Usuario {

    

	public Profesor(int id, String nombre, String email, String tipo, String password) {
		super(id, nombre, email, tipo, password);
	}
	public Profesor( String nombre, String email, String tipo, String password) {
		super( nombre, email, tipo, password);
	}
	@Override
	public String toString() {
		return "Profesor [toString()=" + super.toString() + "]";
	}

    

 
}
