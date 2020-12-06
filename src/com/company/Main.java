package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    /**
     * Procedimiento para imprimir el menú que vamos a mostrar en el main
     */
    public static void printMenu() {
        System.out.println("\n******* Menú Alumnos *******\n");
        System.out.println("1. Añadir Alumno\n" + "2. Modificar Alumno\n"
                + "3. Eliminar Alumno\n" + "4. Listar Alumnos\n" + "5. Salir\n");
    }

    public static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static void annadirAlumno(ArrayList<Alumnos> alumnos) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> asignaturas = new ArrayList<>();
        int i = 1;
        System.out.println("introduce el dni del alumno:");
        String dni = sc.nextLine();
        System.out.println("introduce el nombre del alumno:");
        String nombre = sc.nextLine();
        System.out.println("introduce el telefono del alumno:");
        String cadena = sc.nextLine();
        while (!esNumero(cadena)) {
            System.out.println("no has introducido un numero, prueba de nuevo:");
            cadena = sc.nextLine();
        }
        int telefono = Integer.parseInt(cadena);
        System.out.println("introduce el numero de modulo al que pertenece:");
        File modulos = new File("modulos.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(modulos))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(i + ". " + linea);
                asignaturas.add(linea);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            int opcion = Integer.parseInt(sc.nextLine());
            if (opcion > 0 && opcion < 5) {
                switch (opcion) {
                    case 1 -> {
                        FicheroAlumnos fa = new FicheroAlumnos();
                        fa.annadirAlumnos(alumnos, dni, nombre, asignaturas.get(0), telefono);
                    }
                    case 2 -> {
                        FicheroAlumnos fa = new FicheroAlumnos();
                        fa.annadirAlumnos(alumnos, dni, nombre, asignaturas.get(1), telefono);
                    }
                    case 3 -> {
                        FicheroAlumnos fa = new FicheroAlumnos();
                        fa.annadirAlumnos(alumnos, dni, nombre, asignaturas.get(2), telefono);
                    }
                    case 4 -> {
                        FicheroAlumnos fa = new FicheroAlumnos();
                        fa.annadirAlumnos(alumnos, dni, nombre, asignaturas.get(3), telefono);
                    }

                }
            } else {
                System.out.println("no has introducido la opcion correcta");
            }
        } catch (Exception e) {
            System.out.println("no se ha introducido un numero");
        }
    }

    public static void eliminarAlumno(ArrayList<Alumnos> alumnos) {
        Scanner sc = new Scanner(System.in);
        System.out.println("introduce el numero de dni del alumno: ");
        String dni = sc.nextLine();
        if (!comprobarDni(alumnos, dni)) {
            System.out.println("el alumno no existe");
        } else {
            FicheroAlumnos fa = new FicheroAlumnos();
            fa.eliminarAlumno(alumnos, dni);
        }
    }

    public static boolean comprobarDni(ArrayList<Alumnos> alumnos, String dni) {
        boolean existe = false;
        for (Alumnos alumno : alumnos) {
            if (alumno.getDni().equals(dni)) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public static void modificarAlumno(ArrayList<Alumnos> alumnos) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> asignaturas = new ArrayList<>();
        int i = 1;
        System.out.println("introduce el dni del alumno");
        String dni = sc.nextLine();
        if (!comprobarDni(alumnos, dni)) {
            System.out.println("el alumno no existe");
        } else {
            System.out.println("introduce el nombre del alumno:");
            String nombre = sc.nextLine();
            System.out.println("introduce el telefono del alumno:");
            String cadena = sc.nextLine();
            while (!esNumero(cadena)) {
                System.out.println("no has introducido un numero, prueba de nuevo:");
                cadena = sc.nextLine();
            }
            int telefono = Integer.parseInt(cadena);
            System.out.println("introduce el numero de modulo al que pertenece:");
            File modulos = new File("modulos.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(modulos))) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    System.out.println(i + ". " + linea);
                    asignaturas.add(linea);
                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                int opcion = Integer.parseInt(sc.nextLine());
                if (opcion > 0 && opcion < 5) {
                    switch (opcion) {
                        case 1 -> {
                            FicheroAlumnos fa = new FicheroAlumnos();
                            fa.modificarAlumno(alumnos, dni, nombre, asignaturas.get(0), telefono);
                        }
                        case 2 -> {
                            FicheroAlumnos fa = new FicheroAlumnos();
                            fa.modificarAlumno(alumnos, dni, nombre, asignaturas.get(1), telefono);
                        }
                        case 3 -> {
                            FicheroAlumnos fa = new FicheroAlumnos();
                            fa.modificarAlumno(alumnos, dni, nombre, asignaturas.get(2), telefono);
                        }
                        case 4 -> {
                            FicheroAlumnos fa = new FicheroAlumnos();
                            fa.modificarAlumno(alumnos, dni, nombre, asignaturas.get(3), telefono);
                        }
                    }
                } else {
                    System.out.println("no has introducido una opcion correcta");
                }
            } catch (Exception e) {
                System.out.println("no se ha introducido un numero");
            }
        }
    }

    //metodo para comprobar si existe el fichero, si no lo encuentra lo crea
    public static void existeFichero(){
        File fichero = new File("config.ser");

        try{

            if (fichero.exists()) {
                recuperarFecha();
            }
            else {
                fichero.createNewFile();
            }
        }catch(IOException ex){
            System.out.println("Excepción al crear el fichero: " + ex);
        }

    }

    //metodo para almacenar la configuracion
    public static void almacenarFecha() throws IOException{

        FileOutputStream fs = null;
        ObjectOutputStream os = null;

        try{
            Configuracion a1 = new Configuracion();
            fs = new FileOutputStream("config.ser");//Creamos el archivo
            os = new ObjectOutputStream(fs);//Esta clase tiene el método
            os.writeObject(a1);//El método writeObject() serializa el objeto y lo escribe en el archivo
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            os.close();//Hay que cerrar siempre el archivo
        }

    }

    //metodo para recuperar la configuracion
    public static void recuperarFecha() {

        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("config.ser"))){
            //Cuando no haya mas objetos saltara la excepcion EOFException
            while(true){
                Configuracion aux=(Configuracion)ois.readObject();
                System.out.println("Fecha:" + aux.getFecha() + " " + "Hora: " + aux.getHora()+"\n");
            }
        } catch(ClassNotFoundException | IOException ignored){
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner sl = new Scanner(System.in);
        String apagar;
        boolean salir = false;

        ArrayList<Alumnos> alumnos = new ArrayList<>();
        FicheroAlumnos fa = new FicheroAlumnos();
        fa.cargarAlumnos(alumnos);
        existeFichero();
        int opcion;

        while (!salir) {

            printMenu();
            try {
                System.out.print("Elije una opcion: ");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1 -> annadirAlumno(alumnos);
                    case 2 -> modificarAlumno(alumnos);
                    case 3 -> eliminarAlumno(alumnos);
                    case 4 -> fa.mostrarAlumnos(alumnos);
                    case 5 -> {
                        System.out.println("¿Estas seguro de querer salir? s/n");
                        apagar = sl.nextLine();
                        while (!apagar.equals("s") && !apagar.equals("n")) {
                            System.out.println("la respuesta es incorrecta, prueba de nuevo s/n");
                            apagar = sl.nextLine();
                        }
                        if (apagar.equals("s")) {
                            fa.guardarAlumnos(alumnos);
                            almacenarFecha();
                            salir = true;
                        }
                    }
                }
            } catch (InputMismatchException | IOException e) {
                System.err.println("Error, elije una opción del 1 al 5");
                sc.next();
            }
        }
    }
}
