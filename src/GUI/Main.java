package GUI;

import javax.swing.JOptionPane;
import DLL.ControllerUsuario;
import BLL.Usuario;
import BLL.Alumno;
import BLL.Profesor;

public class Main {
    public static void main(String[] args) {
        
        ControllerUsuario controller = new ControllerUsuario();
        
        String[] acciones = { "Login", "Registrar", "Salir" };
        int menu = 0;
        
        do {
            menu = JOptionPane.showOptionDialog(null, "Bienvenido", null, 0, 0, null, acciones, acciones[0]);

            switch (menu) {
                case 0:
                    String nombre = "";
                    while (nombre.isEmpty()) {
                        nombre = JOptionPane.showInputDialog("Ingrese nombre");
                        if (nombre.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Incorrecto");
                        }
                    }

                    String contrasenia = "";
                    while (contrasenia.isEmpty()) {
                        contrasenia = JOptionPane.showInputDialog("Ingrese contraseña");
                        if (contrasenia.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Incorrecto");
                        }
                    }

                    Usuario usuario = controller.login(nombre, contrasenia);
                    if (usuario != null) {
                        if (usuario instanceof Profesor) {
                            JOptionPane.showMessageDialog(null, "Bienvenido Profesor " + usuario);
                            // Ir a menu de profesor
                        } else if (usuario instanceof Alumno) {
                            JOptionPane.showMessageDialog(null, "Bienvenido Alumno " + usuario.getNombre());
                            // Ir a menu de alumno
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                    break;

                case 1: 
                	
                	controller.agregarUsuario(
                			new Profesor("Gamaliel","Ghamiboxbox@gmail.com","Profesor","programacion123")
                			);

                	
                    break;
            }
        } while (menu != 2);
    }
}
