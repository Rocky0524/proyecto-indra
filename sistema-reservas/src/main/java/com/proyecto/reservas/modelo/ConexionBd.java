package com.proyecto.reservas.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBd {

    private static final String URL = "jdbc:oracle.ilerna.com:1521:XEPDB2"; // Cambia por tu configuración real
    private static final String USUARIO = "DW2425_BAL_KENNETH";
    private static final String PASSWORD = "A49383038Z";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}
