package com.proyecto.reservas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521/XEPDB2";
    private static final String USUARIO = "DW2425_BAL_KENNETH";
    private static final String PASSWORD = "A49383038Z";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}
