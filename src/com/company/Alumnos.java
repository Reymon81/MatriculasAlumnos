package com.company;

public class Alumnos {

    private String dni, nombre, modulo;
    private int telefono;

    public Alumnos() {
    }

    public Alumnos(String dni, String nombre, String modulo, int telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.modulo = modulo;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Nombre: "+nombre+", Dni: "+dni+", Modulo: "+modulo+", Teléfono: "+telefono;
    }
}
