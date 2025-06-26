package com.proyecto.reservas.modelo;



import com.proyecto.reservas.ConexionBD;
import com.proyecto.reservas.modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public class EmpleadoDAO {

    // Insertar nuevo empleado
    public boolean insertarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleado (nombre, email, departamento) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getEmail());
            stmt.setString(3, empleado.getDepartamento());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());
            return false;
        }
    }

    // Listar todos los empleados
    public List<Empleado> listarEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt("id_empleado"));
                emp.setNombre(rs.getString("nombre"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartamento(rs.getString("departamento"));
                empleados.add(emp);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
        }
        return empleados;
    }

    // Eliminar empleado por ID
    public boolean eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleado WHERE id_empleado = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }

    // Actualizar un empleado
    public boolean actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE empleado SET nombre = ?, email = ?, departamento = ? WHERE id_empleado = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getEmail());
            stmt.setString(3, empleado.getDepartamento());
            stmt.setInt(4, empleado.getId());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        }
    }
}
