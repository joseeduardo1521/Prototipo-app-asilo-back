package com.example.scarleth.model;

public class Residente {
    private String uid;
    private String Nombre;
    private String Edad;
    private String Sexo;
    private String Sangre;
    private String Telefono;
    private String Tratamiento;
    private String Horario;
    private String Estatus;
    private String Domicilio;
    private String Fecha;
    private String Alergias;
    private String Ingreso;
    private String Egreso;
    private String Habitacion;

    public Residente() {
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }


    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getSangre() {
        return Sangre;
    }

    public void setSangre(String sangre) {
        Sangre = sangre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getTratamiento() {
        return Tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        Tratamiento = tratamiento;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    public String getEstatus() {
        return Estatus;
    }

    public void setEstatus(String estatus) {
        Estatus = estatus;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getAlergias() {
        return Alergias;
    }

    public void setAlergias(String alergias) {
        Alergias = alergias;
    }

    public String getIngreso() {
        return Ingreso;
    }

    public void setIngreso(String ingreso) {
        Ingreso = ingreso;
    }

    public String getEgreso() {
        return Egreso;
    }

    public void setEgreso(String egreso) {
        Egreso = egreso;
    }

    public String getHabitacion() {
        return Habitacion;
    }

    public void setHabitacion(String habitacion) {
        Habitacion = habitacion;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
