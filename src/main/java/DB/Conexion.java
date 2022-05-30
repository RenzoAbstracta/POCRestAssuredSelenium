package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class Conexion {

    private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/paying";
    private static final String NOMBRE_USUARIO_BASE_DATOS = "root";
    private static final String CONTRASENA_BASE_DATOS = "#bios.2021";
    //private static final String CONTRASENA_BASE_DATOS = "root";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("No se pudo instanciar el driver JDBC.");
        }
    }

    protected static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL_CONEXION, NOMBRE_USUARIO_BASE_DATOS, CONTRASENA_BASE_DATOS);
    }

    protected static void cerrarRecursos(AutoCloseable... recursos) {
        try {
            for (AutoCloseable r : recursos) {
                if (r != null) {
                    r.close();
                }
            }
        } catch (Exception ex) {
            System.out.println("¡ERROR! Ocurrió un problema al cerrar los recursos.");
        }
    }
}
