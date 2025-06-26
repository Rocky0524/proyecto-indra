package com.proyecto.reservas;

import com.proyecto.reservas.modelo.*;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;

public class main {

    private static Scanner sc = new Scanner(System.in);
    private static EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private static SalaDAO salaDAO = new SalaDAO();
    private static ReservaDAO reservaDAO = new ReservaDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Gestión de Empleados");
            System.out.println("2. Gestión de Salas");
            System.out.println("3. Gestión de Reservas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    menuEmpleados();
                    break;
                case 2:
                    menuSalas();
                    break;
                case 3:
                    menuReservas();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida, prueba otra vez.");
                    break;
            }
        } while (opcion != 0);

        sc.close();
    }

    // Menú Empleados
    private static void menuEmpleados() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Empleados ---");
            System.out.println("1. Insertar empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Actualizar empleado");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    insertarEmpleado();
                    break;
                case 2:
                    listarEmpleados();
                    break;
                case 3:
                    eliminarEmpleado();
                    break;
                case 4:
                    actualizarEmpleado();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void insertarEmpleado() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Departamento: ");
        String departamento = sc.nextLine();

        Empleado emp = new Empleado(0, nombre, email, departamento);
        if (empleadoDAO.insertarEmpleado(emp)) {
            System.out.println("✅ Empleado insertado.");
        } else {
            System.out.println("⚠️ Error al insertar empleado.");
        }
    }

    private static void listarEmpleados() {
        List<Empleado> empleados = empleadoDAO.listarEmpleados();
        System.out.println("\nLista de empleados:");
        for (Empleado e : empleados) {
            System.out.println(e.getId() + " | " + e.getNombre() + " | " + e.getEmail() + " | " + e.getDepartamento());
        }
    }

    private static void eliminarEmpleado() {
        System.out.print("ID empleado a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (empleadoDAO.eliminarEmpleado(id)) {
            System.out.println("✅ Empleado eliminado.");
        } else {
            System.out.println("⚠️ Error al eliminar empleado.");
        }
    }

    private static void actualizarEmpleado() {
        System.out.print("ID empleado a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo email: ");
        String email = sc.nextLine();
        System.out.print("Nuevo departamento: ");
        String departamento = sc.nextLine();

        Empleado emp = new Empleado(id, nombre, email, departamento);
        if (empleadoDAO.actualizarEmpleado(emp)) {
            System.out.println("✅ Empleado actualizado.");
        } else {
            System.out.println("⚠️ Error al actualizar empleado.");
        }
    }

    // Menú Salas
    private static void menuSalas() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Salas ---");
            System.out.println("1. Insertar sala");
            System.out.println("2. Listar salas");
            System.out.println("3. Eliminar sala");
            System.out.println("4. Actualizar sala");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    insertarSala();
                    break;
                case 2:
                    listarSalas();
                    break;
                case 3:
                    eliminarSala();
                    break;
                case 4:
                    actualizarSala();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void insertarSala() {
        System.out.print("Nombre sala: ");
        String nombre = sc.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = sc.nextInt();
        sc.nextLine();
        System.out.print("Recursos disponibles: ");
        String recursos = sc.nextLine();

        Sala sala = new Sala(0, nombre, capacidad, recursos);
        if (salaDAO.insertarSala(sala)) {
            System.out.println("✅ Sala insertada.");
        } else {
            System.out.println("⚠️ Error al insertar sala.");
        }
    }

    private static void listarSalas() {
        List<Sala> salas = salaDAO.listarSalas();
        System.out.println("\nLista de salas:");
        for (Sala s : salas) {
            System.out.println(s.getId() + " | " + s.getNombre() + " | " + s.getCapacidad() + " | " + s.getRecursos());
        }
    }

    private static void eliminarSala() {
        System.out.print("ID sala a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (salaDAO.eliminarSala(id)) {
            System.out.println("✅ Sala eliminada.");
        } else {
            System.out.println("⚠️ Error al eliminar sala.");
        }
    }

    private static void actualizarSala() {
        System.out.print("ID sala a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nueva capacidad: ");
        int capacidad = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevos recursos: ");
        String recursos = sc.nextLine();

        Sala sala = new Sala(id, nombre, capacidad, recursos);
        if (salaDAO.actualizarSala(sala)) {
            System.out.println("✅ Sala actualizada.");
        } else {
            System.out.println("⚠️ Error al actualizar sala.");
        }
    }

    // Menú Reservas
    private static void menuReservas() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Reservas ---");
            System.out.println("1. Crear reserva");
            System.out.println("2. Listar reservas");
            System.out.println("3. Eliminar reserva");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearReserva();
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    eliminarReserva();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void crearReserva() {
        System.out.print("ID empleado: ");
        int idEmpleado = sc.nextInt();
        sc.nextLine();

        System.out.print("ID sala: ");
        int idSala = sc.nextInt();
        sc.nextLine();

        System.out.print("Fecha (YYYY-MM-DD): ");
        String fechaStr = sc.nextLine();
        Date fecha;
        try {
            fecha = Date.valueOf(fechaStr);
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Fecha inválida.");
            return;
        }

        System.out.print("Hora inicio (HH:MM): ");
        String horaInicio = sc.nextLine();

        System.out.print("Hora fin (HH:MM): ");
        String horaFin = sc.nextLine();

        // Comprobar conflictos con otras reservas en la misma sala y fecha
        if (reservaDAO.hayConflicto(idSala, fecha, horaInicio, horaFin)) {
            System.out.println("⚠️ Conflicto de horario: la sala ya está reservada en esa franja.");
            return;
        }

        Reserva reserva = new Reserva(0, idEmpleado, idSala, fecha, horaInicio, horaFin);
        if (reservaDAO.insertarReserva(reserva)) {
            System.out.println("✅ Reserva creada.");
        } else {
            System.out.println("⚠️ Error al crear reserva.");
        }
    }

    private static void listarReservas() {
        List<Reserva> reservas = reservaDAO.listarReservas();
        System.out.println("\nLista de reservas:");
        for (Reserva r : reservas) {
            System.out.println(r.getId() + " | Empleado: " + r.getIdEmpleado() + " | Sala: " + r.getIdSala() +
                               " | Fecha: " + r.getFecha() + " | " + r.getHoraInicio() + " - " + r.getHoraFin());
        }
    }

    private static void eliminarReserva() {
        System.out.print("ID reserva a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (reservaDAO.eliminarReserva(id)) {
            System.out.println("✅ Reserva eliminada.");
        } else {
            System.out.println("⚠️ Error al eliminar reserva.");
        }
    }
}
