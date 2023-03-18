
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CRUDSQL {
    private static Connection connection = null;

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            // Credenciales de conexión a la base de datos
            

            // Cadena de conexión de JDBC
            String jdbcUrl = String.format("jdbc:sqlserver://exame.database.windows.net:1433;database=examen;user=CloudSAb5c07c0c@exame;password=10012003;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

            // Conexión a la base de datos
            connection = DriverManager.getConnection(jdbcUrl);
        }

        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }

    // Método principal para probar la conexión
    public static void main(String[] args) {
        try {
            Connection connection = CRUDSQL.getConnection();
            System.out.println("Conexión exitosa a la base de datos en Azure.");
            CRUDSQL.closeConnection();
        } catch (SQLException ex) {
            System.err.println("Error al conectar a la base de datos en Azure.");
            ex.printStackTrace();
        }
    }
}
