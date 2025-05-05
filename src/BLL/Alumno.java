package BLL;

public class Alumno extends Usuario {

    
    public Alumno(int id, String nombre, String email, String tipo, String password) {
		super(id, nombre, email, tipo, password);
	}
	public Alumno() {
        super();
    }
	@Override
	public String toString() {
		return "Alumno [toString()=" + super.toString() + "]";
	}
    
    
   
}
