package crudsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Clase de prueba para la conexión a la base de datos y la creación de una
 * tabla
 */
public class PruebaConexion {

    public static void main(String[] args) {
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();

        try {
            // Abrir la conexión a la base de datos
            connMySQL.open();
//            System.out.println("Conexión estable");
//
//            // Crear la tabla de videojuegos
//            String createTableSQL = "CREATE TABLE IF NOT EXISTS videojuegos ("
//                    + "idVideojuego INT(11) NOT NULL AUTO_INCREMENT,"
//                    + "nombre VARCHAR(50) NOT NULL,"
//                    + "año INT(11) NOT NULL,"
//                    + "sinopsis TEXT NOT NULL,"
//                    + "PRIMARY KEY (idVideojuego))";
//            PreparedStatement preparedStatement = conn.prepareStatement(createTableSQL);
//            preparedStatement.executeUpdate();
//            System.out.println("Tabla creada correctamente");

            String insertSQL = "INSERT INTO videojuegos (nombre, año, sinopsis) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
            preparedStatement.setString(1, "Zelda");
            preparedStatement.setInt(2, 1995);
            preparedStatement.setString(3, "Zelda, uno de los mayores juegos y reliquia");
            preparedStatement.executeUpdate();
            System.out.println("Datos insertados correctamente");
            
            
            
            // Consultar los datos de la tabla de videojuegos
            Statement statement = conn.createStatement();
            String selectSQL = "SELECT * FROM videojuegos";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                int id = resultSet.getInt("idVideojuego");
                String nombre = resultSet.getString("nombre");
                int año = resultSet.getInt("año");
                String sinopsis = resultSet.getString("sinopsis");
                System.out.println(id + "\t" + nombre + "\t" + año + "\t" + sinopsis);
            }
            resultSet.close();
            statement.close();

            // Cerrar la conexión a la base de datos
            preparedStatement.close();
            connMySQL.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear la tabla");
        }
    }
}
