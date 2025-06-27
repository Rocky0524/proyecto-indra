package com.proyecto.reservas.modelo;

import com.proyecto.reservas.ConexionBD;
import com.proyecto.reservas.modelo.Sala;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {

    // Insertar nueva sala
    public boolean insertarSala(Sala sala) {
        String sql = "INSERT INTO sala (nombre, capacidad, recursos) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sala.getNombre());
            stmt.setInt(2, sala.getCapacidad());
            stmt.setString(3, sala.getRecursos());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar sala: " + e.getMessage());
            return false;
        }
    }

    // Listar todas las salas
    public List<Sala> listarSalas() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM sala";

        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Sala sala = new Sala();
                sala.setId(rs.getInt("id_sala"));
                sala.setNombre(rs.getString("nombre"));
                sala.setCapacidad(rs.getInt("capacidad"));
                sala.setRecursos(rs.getString("recursos"));
                salas.add(sala);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar salas: " + e.getMessage());
        }
        return salas;
    }

    // Eliminar sala por ID
    public boolean eliminarSala(int id) {
        String sql = "DELETE FROM sala WHERE id_sala = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar sala: " + e.getMessage());
            return false;
        }
    }

    // Actualizar una sala
    public boolean actualizarSala(Sala sala) {
        String sql = "UPDATE sala SET nombre = ?, capacidad = ?, recursos = ? WHERE id_sala = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sala.getNombre());
            stmt.setInt(2, sala.getCapacidad());
            stmt.setString(3, sala.getRecursos());
            stmt.setInt(4, sala.getId());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar sala: " + e.getMessage());
            return false;
        }
    }
}
