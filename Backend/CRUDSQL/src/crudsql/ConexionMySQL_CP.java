/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crudsql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author qwer1
 */
public class ConexionMySQL_CP {
    Connection conn;

    public Connection open() {
        String user = "u47sfguxkqadjvuz";//se pone el usuario que definimos en MySQL Workbench
        String password = "00DNnKrjaFegDfSr7V4C";//Se establece la contraseña que asigné
        String url = "jdbc:mysql://bul8j0n3gxr694caqjfd-mysql.services.clever-cloud.com:3306/bul8j0n3gxr694caqjfd?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8";//se añade la URL

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//Se añade el driver de MySQL
            conn = DriverManager.getConnection(url, user, password);//Y le añadimos nuestras variables previas
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception controlada");
            }
        }
    }
}
