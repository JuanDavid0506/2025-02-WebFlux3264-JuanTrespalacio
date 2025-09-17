package Conexion;

import java.sql.*;

public class DbConexion {


    private static final String url = "jdbc:mysql://localhost:3306/db_estudiantes";
    private static final String USER = "root";
    private static final String pass = "juan123";



    public static Connection conectar(){
        try{
            return DriverManager.getConnection(url,USER,pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}