package Conexion;

import java.sql.*;

public class DbConexion {


    private static final String url = "jdbc:mysql://localhost:3306/taller_jdbc";
    private static final String USER = "root";
    private static final String pass = "Jesusb.1050";



    public static Connection conectar(){
        try{
            return DriverManager.getConnection(url,USER,pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}