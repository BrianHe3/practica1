package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import BLL.Usuario;
import BLL.Alumno;
import BLL.Profesor;
import repository.Encriptador;
import repository.UsuarioRepository;

public class ControllerUsuario<T extends Usuario> implements UsuarioRepository{

    private static Connection con = Conexion.getInstance().getConnection();

    @Override
    public T login(String nombre, String password) {
        T usuario = (T) new Usuario();
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM usuario WHERE nombre = ? AND password = ?"
            );
            stmt.setString(1, nombre);
            stmt.setString(2, usuario.encriptar(password));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String tipo = rs.getString("tipo");

                switch (tipo.toLowerCase()) {
                    case "alumno":
                        usuario = (T) new Alumno(id, nombre, email, tipo, usuario.desencriptar(password));
                        break;
                    case "profesor":
                        usuario = (T) new Profesor(id, nombre, email, tipo, usuario.desencriptar(password));
                        break;
                    default:
                        System.out.println("Tipo de usuario desconocido: " + tipo);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO usuario (nombre, email, tipo, password) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getTipo());
            statement.setString(4, usuario.encriptar(usuario.getPassword()));

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario agregado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LinkedList<Usuario> mostrarUsuarios() {
        LinkedList<Usuario> usuarios = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String tipo = rs.getString("tipo");
                String password = rs.getString("password");

                switch (tipo.toLowerCase()) {
                    case "alumno":
                        usuarios.add((T) new Alumno(id, nombre, email, tipo, password));
                        break;
                    case "profesor":
                        usuarios.add((T) new Profesor(id, nombre, email, tipo, password));
                        break;
                    default:
                        System.out.println("Tipo desconocido: " + tipo);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
