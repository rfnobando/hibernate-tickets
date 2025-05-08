package controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import dto.LoginResponseDTO;
import model.AttachedPicture;
import model.Customer;
import model.ProfilePicture;
import model.Ticket;
import service.AuthService;
import service.CustomerService;
import service.TicketCategoryService;
import service.TicketService;
import session.SessionManager;

public class SystemController {
	private final AuthService authService;
	private final CustomerService customerService;
	private final TicketService ticketService;
	private final TicketCategoryService ticketCategoryService;
	private final Scanner scanner;
	
	public SystemController() {
		this.authService = new AuthService();
		this.customerService = new CustomerService();
		this.ticketService = new TicketService();
		this.ticketCategoryService = new TicketCategoryService();
		this.scanner = new Scanner(System.in);
	}
	
	public void initApp() {
		requestAuthentication();
		LoginResponseDTO loggedInUser = SessionManager.getLoggedInUser().get();
		
		if (loggedInUser.getUserType() == "customer") {
			openCustomerMenu(loggedInUser.getId());
		} else if(loggedInUser.getUserType() == "employee") {
			openEmployeeMenu(loggedInUser.getId());
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
					requestCreateTicket(userId);
					option = requestCustomerOption();
					break;
				case "2":
					showCustomerCreatedTickets(userId);
					option = requestCustomerOption();
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
	
	private void openEmployeeMenu(long userId) {
		String option = requestEmployeeOption();
		
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
	
	private String requestEmployeeOption() {
		String option;
		
		System.out.println("\n--- MENÚ DE EMPLEADOS ---");
		System.out.println("1.");
		System.out.println("2. Ver tickets asignados");
		System.out.println("3. Salir");
		System.out.print("Elegí una opción: ");
		option = scanner.nextLine();
		System.out.println();
		
		return option;
	}
	
	private void showCustomerCreatedTickets(long userId) {
		Set<Ticket> ticketSet = customerService.getByIdWithTickets(userId).getCreatedTickets();
		System.out.println(ticketSet);
	}
	
	private void requestCreateTicket(long userId) {
		/*seCreo = abmTicket.createTicket(
			"El último de todos los tickets",
			abmCustomer.getById(5), 
			abmTicketCategory.getById(1), 
			"q capo deepseek 2",
			null
		);*/
		
		System.out.println("1. Consultas Generales");
		System.out.println("2. Atención al Cliente");
		System.out.println("3. Soporte Técnico");
		System.out.println("4. Errores");
		System.out.println("5. Solicitudes de Servicio");
		System.out.println("6. Mantenimiento");
		System.out.println("7. Feedback");
		
		System.out.print("Elegí la categoría del ticket: ");
		String categoryId = scanner.nextLine();
		System.out.println();
		
		while (!Arrays.asList("1", "2", "3", "4", "5", "6", "7").contains(categoryId)) {
			System.out.print("Categoría incorrecta, elija otra: ");
			categoryId = scanner.nextLine();
		}
		
		System.out.print("Título del ticket: ");
		String title = scanner.nextLine();
		System.out.print("Mensaje: ");
		String message = scanner.nextLine();
		Set<AttachedPicture> attachedPictures = new HashSet<AttachedPicture>();
		String pictureName = "0";
		
		System.out.println("Si tu mensaje lleva fotos adjuntas, indicanos el nombre de cada archivo.");
		System.out.println("Si tu mensaje no lleva fotos adjuntas, ingresá 0.");
		System.out.println();
		
		do {
			System.out.print("Foto: ");
			pictureName = scanner.nextLine();
			
			if (!pictureName.equals("0")) {
				attachedPictures.add(new AttachedPicture(pictureName, null));
			}
		} while (!pictureName.equals("0"));
		
		try {
			ticketService.createTicket(
				title,
				customerService.getById(userId),
				ticketCategoryService.getById(Long.parseLong(categoryId)),
				message,
				attachedPictures
			);
			
			System.out.println("Ticket creado con éxito.");
		} catch (Exception e) {
			System.out.println("Error al crear ticket: " + e.getMessage());
		}
		
		
	}
}
