package controller;

import dto.LoginResponseDTO;
import model.Customer;
import model.ProfilePicture;
import service.AuthService;
import session.SessionManager;
import java.util.Optional;
import java.util.Scanner;

public class AuthServiceTest {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENÚ DE AUTENTICACIÓN ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Cerrar sesion");
            System.out.println("4. Salir");
            System.out.print("Elegí una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Email: ");
                    String emailLogin = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String passwordLogin = scanner.nextLine();
                    try {
                        Optional<LoginResponseDTO> loginResult = authService.login(emailLogin, passwordLogin);
                        if (loginResult.isPresent()) {
                            LoginResponseDTO dto = loginResult.get();
                            System.out.println("¡Inicio de sesión exitoso!");
                            System.out.println("ID: " + dto.getId());
                            System.out.println("Nombre: " + dto.getName());
                            System.out.println("Apellido: " + dto.getSurname());
                            System.out.println("Email: " + dto.getEmail());
                        } else {
                            System.out.println("Credenciales inválidas. No se pudo iniciar sesión.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al iniciar sesión: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                        e.printStackTrace();
                    }

                    break;

                case "2":
                    try {
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = scanner.nextLine();
                        System.out.print("Email: ");
                        String emailRegistro = scanner.nextLine();
                        System.out.print("Contraseña: ");
                        String contraseña = scanner.nextLine();
                        System.out.print("Nombre del archivo de foto (opcional): ");
                        String foto = scanner.nextLine();

                        ProfilePicture profilePicture = foto.isEmpty() ? null : new ProfilePicture(foto);
                        Customer nuevoUsuario = new Customer(nombre, apellido, emailRegistro, contraseña, profilePicture);

                        int id = authService.register(nuevoUsuario);
                        System.out.println("¡Registro exitoso! ID del nuevo usuario: " + id);
                        System.out.println("Nombre: " + nuevoUsuario.getName());
                        System.out.println("Apellido: " + nuevoUsuario.getSurname());
                        System.out.println("Email: " + nuevoUsuario.getEmail());

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al registrar usuario: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error inesperado: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case "3":
                    if (!SessionManager.isLoggedIn()) {
                        System.out.println("No hay ninguna sesión iniciada.");
                    } else {
                        authService.logout();
                        System.out.println("Sesión cerrada.");
                    }
                    break;

                case "4":
                    System.out.println("¡Hasta luego!");
                    running = false;

                default:
                    System.out.println("Opción no válida. Intentá de nuevo.");
                    break;
            }
        }
        scanner.close();
    }
}