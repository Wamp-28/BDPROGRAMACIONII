package org.example;

import org.example.dao.ProfesorDAO;
import org.example.dao.ProfesorDAOImpl;
import org.example.model.Profesor;
import org.example.util.ConexionBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConexionBD.obtenerConexion()) {
            ProfesorDAO profesorDAO = new ProfesorDAOImpl(connection);
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\nMenu:");
                System.out.println("1. Registrar Profesor");
                System.out.println("2. Leer Profesor");
                System.out.println("3. Actualizar Profesor");
                System.out.println("4. Eliminar Profesor");
                System.out.println("5. Listar Profesores");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1: // Registrar
                        System.out.print("Cédula: ");
                        String cedula = scanner.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = scanner.nextLine();
                        System.out.print("Edad: ");
                        int edad = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer
                        System.out.print("Profesión: ");
                        String profesion = scanner.nextLine();

                        Profesor profesor = new Profesor(cedula, nombre, apellido, edad, profesion);
                        profesorDAO.crear(profesor);
                        System.out.println("Profesor registrado.");
                        break;

                    case 2: // Leer
                        System.out.print("Ingrese la cédula del profesor: ");
                        String cedulaLeer = scanner.nextLine();
                        Profesor prof = profesorDAO.leer(cedulaLeer);
                        if (prof != null) {
                            System.out.println(prof);
                        } else {
                            System.out.println("Profesor no encontrado.");
                        }
                        break;

                    case 3: // Actualizar
                        System.out.print("Cédula del profesor a actualizar: ");
                        String cedulaActualizar = scanner.nextLine();
                        Profesor profActualizar = profesorDAO.leer(cedulaActualizar);
                        if (profActualizar != null) {
                            System.out.print("Nuevo nombre: ");
                            profActualizar.setNombre(scanner.nextLine());
                            System.out.print("Nuevo apellido: ");
                            profActualizar.setApellido(scanner.nextLine());
                            System.out.print("Nueva edad: ");
                            profActualizar.setEdad(scanner.nextInt());
                            scanner.nextLine(); // Limpiar el buffer
                            System.out.print("Nueva profesión: ");
                            profActualizar.setProfesion(scanner.nextLine());

                            profesorDAO.actualizar(profActualizar);
                            System.out.println("Profesor actualizado.");
                        } else {
                            System.out.println("Profesor no encontrado.");
                        }
                        break;

                    case 4: // Eliminar
                        System.out.print("Cédula del profesor a eliminar: ");
                        String cedulaEliminar = scanner.nextLine();
                        profesorDAO.eliminar(cedulaEliminar);
                        System.out.println("Profesor eliminado.");
                        break;

                    case 5: // Listar
                        List<Profesor> listaProfesores = profesorDAO.listar();
                        for (Profesor p : listaProfesores) {
                            System.out.println(p);
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
