package support;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:sqlserver://172.20.0.206:1433;databaseName=BD_MAGISTERIAL;encrypt=true;trustServerCertificate=true";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la Base de Datos: " + e.getMessage());
        }
        return conexion;
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Conexión exitosa a la base de datos.");
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
}