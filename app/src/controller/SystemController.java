package controller;

import java.util.Optional;
import java.util.Scanner;

import dto.LoginResponseDTO;
import model.Customer;
import model.ProfilePicture;
import service.AuthService;
import session.SessionManager;

public class SystemController {
	private final AuthService authService;
	private final Scanner scanner;
	
	public SystemController() {
		this.authService = new AuthService();
		this.scanner = new Scanner(System.in);
	}
	
	public void initApp() {
		requestAuthentication();
		LoginResponseDTO loggedInUser = SessionManager.getLoggedInUser().get();
		
		if (loggedInUser.getUserType() == "customer") {
			openCustomerMenu(loggedInUser.getId());
		}
		
	}

	private void requestAuthentication() {
		boolean isLoggedIn = false;
		String option = requestAuthenticationOption();
		
		do {
			switch (option) {
				case "1":
					isLoggedIn = handleLogin();
					break;
				case "2":
					handleRegister();
					option = requestAuthenticationOption();
                    break;
				case "3": 
					closeApp();
					break;
				default:
					option = requestValidOption();
					break;
			}
		} while (!isLoggedIn);
	}
	
	private String requestAuthenticationOption() {
		String option;
		
		System.out.println("\n--- AUTENTICACIÓN ---");
		System.out.println("1. Iniciar sesión");
		System.out.println("2. Registrar usuario");
		System.out.println("3. Salir");
		System.out.print("Elegí una opción: ");
		option = scanner.nextLine();
		System.out.println();
		
		return option;
	}
	
	private String requestValidOption() {
		String option;
	
		System.out.print("Opción incorrecta, elija otra opción: ");
		option = scanner.nextLine();
		
		return option;
	}
	
	private void closeApp() {
		System.out.println();
		System.out.println("¡Hasta luego!");
		System.exit(0);
	}
	
	private String formatUserInstanceType(String instanceType) {
		return instanceType == "customer"
				? "Cliente"
				: instanceType == "employee"
					? "Empleado"
					: "Desconocido";
	}
	
	private boolean handleLogin() {
		boolean success = false; 
        System.out.print("Email: ");
        String emailLogin = scanner.nextLine();
        System.out.print("Contraseña: ");
        String passwordLogin = scanner.nextLine();
        
        try {
            Optional<LoginResponseDTO> loginResult = authService.login(emailLogin, passwordLogin);
            if (loginResult.isPresent()) {
                LoginResponseDTO dto = loginResult.get();
                System.out.println();
                System.out.println("¡Inicio de sesión exitoso!");
                System.out.println("ID: " + dto.getId());
                System.out.println("Nombre: " + dto.getName());
                System.out.println("Apellido: " + dto.getSurname());
                System.out.println("Email: " + dto.getEmail());
                System.out.println("Rol: " + formatUserInstanceType(dto.getUserType()));
                success = true;
            } else {
                System.out.println("Credenciales inválidas. Intente nuevamente por favor.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
        
        return success;
	}
	
	private void handleRegister() {
        try {
            System.out.print("Nombre: ");
            String name = scanner.nextLine();
            System.out.print("Apellido: ");
            String surname = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();
            System.out.print("Nombre del archivo de foto de perfil: ");
            String pictureFileName = scanner.nextLine();

            Customer newUser = new Customer(name, surname, email, password, null);
            ProfilePicture profilePicture = new ProfilePicture(pictureFileName, null);
            newUser.setProfilePicture(profilePicture);
            profilePicture.setUser(newUser);
            
            int id = authService.register(newUser);
            System.out.println("¡Registro exitoso! ID del nuevo usuario: " + id);
            System.out.println("Ya podés iniciar sesión con tu nuevo usuario");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
	}
	
	private void openCustomerMenu(long userId) {
		String option = requestCustomerOption();
		
		do {
			switch(option) {
				case "1":
					break;
				case "3":
					closeApp();
					break;
				default:
					option = requestValidOption();
					break;
			}
		} while (option != "3");
	}
	
	private String requestCustomerOption() {
		String option;
		
		System.out.println("\n--- MENÚ DE CLIENTES ---");
		System.out.println("1. Crear nuevo ticket");
		System.out.println("2. Ver mis tickets");
		System.out.println("3. Salir");
		System.out.print("Elegí una opción: ");
		option = scanner.nextLine();
		System.out.println();
		
		return option;
	}
}
