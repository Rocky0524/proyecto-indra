package com.proyecto.reservas;

import com.proyecto.reservas.modelo.Empleado;
import com.proyecto.reservas.modelo.EmpleadoDAO;

public class main {

    public static void main(String[] args) {

        EmpleadoDAO dao = new EmpleadoDAO();

        // Insertar un empleado de prueba
        Empleado nuevo = new Empleado(0, "Juan Pérez", "juan.perez@empresa.com", "Ventas");
        if (dao.insertarEmpleado(nuevo)) {
            System.out.println("✅ Empleado insertado correctamente.");
        } else {
            System.out.println("⚠️ Error al insertar empleado.");
        }

        // Listar todos los empleados
        System.out.println("\n📋 Lista de empleados:");
        for (Empleado emp : dao.listarEmpleados()) {
            System.out.println(emp.getId() + " - " + emp.getNombre() + " - " + emp.getEmail() + " - " + emp.getDepartamento());
        }
    }
}
