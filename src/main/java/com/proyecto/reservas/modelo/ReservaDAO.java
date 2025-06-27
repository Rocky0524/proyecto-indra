package com.proyecto.reservas.modelo;

import com.proyecto.reservas.ConexionBD;
import com.proyecto.reservas.modelo.Reserva;
import com.proyecto.reservas.modelo.Sala;
import com.proyecto.reservas.modelo.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    // Insertar una nueva reserva, con control de conflictos
    public boolean insertarReserva(Reserva reserva) {
        if (existeConflicto(reserva)) {
            System.out.println("⚠️ Conflicto de horario: la sala ya está reservada en esa franja.");
            return false;
        }

        String sql = "INSERT INTO reserva (id_empleado, id_sala, fecha, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdEmpleado());
            stmt.setInt(2, reserva.getIdSala());
            stmt.setDate(3, reserva.getFecha());
            stmt.setString(4, reserva.getHoraInicio());
            stmt.setString(5, reserva.getHoraFin());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar reserva: " + e.getMessage());
            return false;
        }
    }

    // Nuevo método público para comprobar conflictos por parámetros directos
    public boolean hayConflicto(int idSala, Date fecha, String horaInicio, String horaFin) {
        Reserva reservaTemp = new Reserva();
        reservaTemp.setIdSala(idSala);
        reservaTemp.setFecha(fecha);
        reservaTemp.setHoraInicio(horaInicio);
        reservaTemp.setHoraFin(horaFin);
        reservaTemp.setId(0); // Id 0 para indicar que no es una reserva existente

        return existeConflicto(reservaTemp);
    }

    // Listar todas las reservas
    public List<Reserva> listarReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt("id_reserva"));
                r.setIdEmpleado(rs.getInt("id_empleado"));
                r.setIdSala(rs.getInt("id_sala"));
                r.setFecha(rs.getDate("fecha"));
                r.setHoraInicio(rs.getString("hora_inicio"));
                r.setHoraFin(rs.getString("hora_fin"));
                reservas.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar reservas: " + e.getMessage());
        }
        return reservas;
    }

    // Eliminar reserva por ID
    public boolean eliminarReserva(int id) {
        String sql = "DELETE FROM reserva WHERE id_reserva = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar reserva: " + e.getMessage());
            return false;
        }
    }

    // Actualizar reserva con control de conflictos
    public boolean actualizarReserva(Reserva reserva) {
        if (existeConflicto(reserva)) {
            System.out.println("⚠️ Conflicto de horario al actualizar: la sala ya está reservada en esa franja.");
            return false;
        }

        String sql = "UPDATE reserva SET id_empleado = ?, id_sala = ?, fecha = ?, hora_inicio = ?, hora_fin = ? WHERE id_reserva = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdEmpleado());
            stmt.setInt(2, reserva.getIdSala());
            stmt.setDate(3, reserva.getFecha());
            stmt.setString(4, reserva.getHoraInicio());
            stmt.setString(5, reserva.getHoraFin());
            stmt.setInt(6, reserva.getId());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar reserva: " + e.getMessage());
            return false;
        }
    }

    // Método privado para detectar conflictos de horario
    private boolean existeConflicto(Reserva reserva) {
        String sql = "SELECT COUNT(*) FROM reserva WHERE id_sala = ? AND fecha = ? AND id_reserva <> ? AND (" +
                     "(hora_inicio < ? AND hora_fin > ?) OR " +    // Empieza antes y acaba después del inicio nuevo
                     "(hora_inicio < ? AND hora_fin > ?) OR " +    // Empieza antes y acaba después del fin nuevo
                     "(hora_inicio >= ? AND hora_fin <= ?))";      // Está completamente dentro del rango nuevo

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdSala());
            stmt.setDate(2, reserva.getFecha());
            stmt.setInt(3, reserva.getId());  // Para excluir la misma reserva si se está actualizando
            stmt.setString(4, reserva.getHoraInicio());
            stmt.setString(5, reserva.getHoraInicio());
            stmt.setString(6, reserva.getHoraFin());
            stmt.setString(7, reserva.getHoraFin());
            stmt.setString(8, reserva.getHoraInicio());
            stmt.setString(9, reserva.getHoraFin());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar conflictos: " + e.getMessage());
        }
        return false;
    }
}
