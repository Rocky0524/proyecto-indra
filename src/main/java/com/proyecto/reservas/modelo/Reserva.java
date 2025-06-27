package com.proyecto.reservas.modelo;


import java.sql.Date;

public class Reserva {

    private int id;
    private int idEmpleado;
    private int idSala;
    private Date fecha;
    private String horaInicio;
    private String horaFin;

    public Reserva() {
    }

    public Reserva(int id, int idEmpleado, int idSala, Date fecha, String horaInicio, String horaFin) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idSala = idSala;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public int getIdSala() {
        return idSala;
    }
    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    public String getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}

