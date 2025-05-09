package org.example.util;


import java.sql.*;


public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3308/ejercicioJava";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "1234";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}