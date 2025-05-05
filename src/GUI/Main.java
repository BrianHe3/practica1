package GUI;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import BLL.Alumno;
import BLL.Profesor;
import DLL.ControllerUsuario;
import BLL.Usuario;

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
                        	Profesor profesor = (Profesor)usuario;
                            JOptionPane.showMessageDialog(null, "Bienvenido Profesor " + usuario.getNombre());
                            // Ir a menu de profesor
                            profesor.menuProfesor();
                        } else if (usuario instanceof Alumno) {
                            JOptionPane.showMessageDialog(null, "Bienvenido Alumno " + usuario.getNombre());
                            // Ir a menu de alumno
                            Alumno alumno = (Alumno)usuario;
                            alumno.menuAlumno();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                    break;

                case 1: 
                	
                	//falta registrar :D
                    
                    break;
            }
        } while (menu != 2);
    }
}
