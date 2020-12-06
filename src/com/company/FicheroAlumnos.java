package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FicheroAlumnos {
    private ArrayList<Alumnos> alumnos= new ArrayList<>();

    public FicheroAlumnos() {

    }

    public void cargarAlumnos(ArrayList<Alumnos> alumnos){
        File fichero = new File("alumnos.txt");
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            while ((linea = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(linea,";");
                String dni = st.nextToken();
                String nombre = st.nextToken();
                String modulo = st.nextToken();
                int telefono = Integer.parseInt(st.nextToken());
                Alumnos alumno= new Alumnos(dni, nombre, modulo, telefono);
                alumnos.add(alumno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarAlumnos(ArrayList<Alumnos> alumnos){
        File fichero;
        fichero = new File("alumnos.txt");
        File ficheroTemporal = new File("alumnos.txt.tmp");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroTemporal, true))){
            for (Alumnos alumno : alumnos) {
                String linea = alumno.getDni() + ";" + alumno.getNombre() + ";" + alumno.getModulo() + ";" + alumno.getTelefono() + "\r\n";
                bw.write(linea);
            }
            bw.close();

            Files.deleteIfExists(Paths.get(String.valueOf(fichero)));
            ficheroTemporal.renameTo(new File(String.valueOf(fichero)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void annadirAlumnos(ArrayList<Alumnos> alumnos, String dni, String nombre, String modulo, int telefono){

        Alumnos alumno = new Alumnos(dni,nombre,modulo,telefono);
        alumnos.add(alumno);
    }

    public void eliminarAlumno(ArrayList<Alumnos> alumnos, String dni){
       for (int i = 0; i < alumnos.size(); i++) {
            Alumnos alumno = alumnos.get(i);
            String dni1 = alumno.getDni();
            if(dni.equals(dni1)){
                alumnos.remove(i);
            }
       }
    }

    public void modificarAlumno(ArrayList<Alumnos> alumnos, String dni, String nombre, String modulo, int telefono){
        for (int i = 0; i < alumnos.size(); i++) {
            Alumnos alumno = alumnos.get(i);
            if(alumno.getDni().equals(dni)){
                alumno.setDni(dni);
                alumno.setNombre(nombre);
                alumno.setModulo(modulo);
                alumno.setTelefono(telefono);
                alumnos.set(i, alumno);
            }
        }
    }

    public void mostrarAlumnos(ArrayList<Alumnos> alumnos){
        int contador =0;
        for (Alumnos alumno : alumnos) {
            System.out.println(alumno);
            contador++;
        }
        if(contador==0) System.out.println("no hay alumnos matriculados");
    }
}