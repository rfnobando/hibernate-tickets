package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import dto.LoginResponseDTO;
import model.AttachedPicture;
import model.Customer;
import model.Employee;
import model.ProfilePicture;
import model.Ticket;
import model.TicketMessage;
import service.AuthService;
import service.CustomerService;
import service.EmployeeService;
import service.TicketCategoryService;
import service.TicketService;
import service.UserService;
import session.SessionManager;

public class SystemController {
	private final AuthService authService;
	private final UserService userService;
	private final CustomerService customerService;
	private final EmployeeService employeeService;
	private final TicketService ticketService;
	private final TicketCategoryService ticketCategoryService;
	private final Scanner scanner;
	
	public SystemController() {
		this.authService = new AuthService();
		this.userService = new UserService();
		this.customerService = new CustomerService();
		this.employeeService = new EmployeeService();
		this.ticketService = new TicketService();
		this.ticketCategoryService = new TicketCategoryService();
		this.scanner = new Scanner(System.in);
	}
	
	public void initApp() {
		requestAuthentication();
		LoginResponseDTO loggedInUser = SessionManager.getLoggedInUser().get();
		String userType = loggedInUser.getUserType();
		
		if (userType == "customer") {
			openCustomerMenu();
		} else if(userType == "employee") {
			openEmployeeMenu();
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
	
	private void openCustomerMenu() {
		String option = requestCustomerOption();
		
		do {
			switch(option) {
				case "1":
					requestCreateTicket();
					option = requestCustomerOption();
					break;
				case "2":
					showCustomerCreatedTickets();
					option = requestCustomerOption();
					break;
				case "3":
					attachNewTicketMessage();
					option = requestCustomerOption();
					break;
				case "4":
					closeApp();
					break;
				default:
					option = requestValidOption();
					break;
			}
		} while (option != "4");
	}
	
	private String requestCustomerOption() {
		String option;
		
		System.out.println("\n--- MENÚ DE CLIENTES ---");
		System.out.println("1. Crear nuevo ticket");
		System.out.println("2. Ver mis tickets");
		System.out.println("3. Responder un ticket");
		System.out.println("4. Salir");
		System.out.print("Elegí una opción: ");
		option = scanner.nextLine();
		System.out.println();
		
		return option;
	}
	
	private void openEmployeeMenu() {
		String option = requestEmployeeOption();
		
		do {
			switch(option) {
				case "1":
					choosePendingTicket();
					option = requestEmployeeOption();
					break;
				case "2":
					showEmployeeManagedTickets();
					option = requestEmployeeOption();
					break;
				case "3":
					attachNewTicketMessage();
					option = requestEmployeeOption();
					break;
				case "4":
					closeInProgressTicket();
					option = requestEmployeeOption();
					break;
				case "5":
					closeApp();
					break;
				default:
					option = requestValidOption();
					break;
			}
		} while (option != "5");
	}
	
	private String requestEmployeeOption() {
		String option;
		
		System.out.println("\n--- MENÚ DE EMPLEADOS ---");
		System.out.println("1. Elegir un ticket pendiente");
		System.out.println("2. Ver tickets asignados");
		System.out.println("3. Responder un ticket");
		System.out.println("4. Cerrar un ticket en progreso");
		System.out.println("5. Salir");
		System.out.print("Elegí una opción: ");
		option = scanner.nextLine();
		System.out.println();
		
		return option;
	}
	
	private void showCustomerCreatedTickets() {
		LoginResponseDTO loggedInUser = SessionManager.getLoggedInUser().get();
		long userId = loggedInUser.getId();
		
		Set<Ticket> ticketSet = customerService.getByIdWithTickets(userId).getCreatedTickets();
		List<Long> ticketIds = new ArrayList<Long>();
		
		if (!ticketSet.isEmpty()) {
			for (Ticket ticket : ticketSet) {
				ticketIds.add(ticket.getId());
			}
			
			System.out.println(ticketSet);
			System.out.println();
			
			System.out.println("Si querés ver los mensajes de un ticket, ingresá su id");
			System.out.print("Si querés salir, ingresá 0: ");
			String chosenTicketId = scanner.nextLine();
			System.out.println();
			
			while (!ticketIds.contains(Long.parseLong(chosenTicketId)) && !chosenTicketId.equals("0")) {
				System.out.print("ID incorrecto, elegí otro: ");
				chosenTicketId = scanner.nextLine();
			}
			
			if (!chosenTicketId.equals("0")) {
				Ticket ticket = ticketService.getTicketWithStatusAndMessage(Long.parseLong(chosenTicketId));
				
				List<TicketMessage> sortedMessages = ticket.getMessages().stream()
						.sorted(Comparator.comparing(TicketMessage::getId))
						.collect(Collectors.toList());
				
				System.out.println(ticket);
				System.out.println(sortedMessages);
			}			
		} else {
			System.out.println("Aún no tenés tickets.");
		}
		
	}
	
	private void showEmployeeManagedTickets() {
		LoginResponseDTO loggedInUser = SessionManager.getLoggedInUser().get();
		long userId = loggedInUser.getId();
		
		Set<Ticket> ticketSet = employeeService.getByIdWithTickets(userId).getManagedTickets();
		List<Long> ticketIds = new ArrayList<Long>();
		
		if (!ticketSet.isEmpty()) {
			for (Ticket ticket : ticketSet) {
				ticketIds.add(ticket.getId());
			}
			
			System.out.println(ticketSet);
			System.out.println();
			
			System.out.println("Si querés ver los mensajes de un ticket, ingresá su id");
			System.out.print("Si querés salir, ingresá 0: ");
			String chosenTicketId = scanner.nextLine();
			System.out.println();
			
			while (!ticketIds.contains(Long.parseLong(chosenTicketId)) && !chosenTicketId.equals("0")) {
				System.out.print("ID incorrecto, elegí otro: ");
				chosenTicketId = scanner.nextLine();
			}
			
			if (!chosenTicketId.equals("0")) {
				Ticket ticket = ticketService.getTicketWithStatusAndMessage(Long.parseLong(chosenTicketId));
				
				List<TicketMessage> sortedMessages = ticket.getMessages().stream()
					    .sorted(Comparator.comparing(TicketMessage::getId))
					    .collect(Collectors.toList());
				
				System.out.println(ticket);
				System.out.println(sortedMessages);
			}
		} else {
			System.out.println("Aún no tenés tickets asignados.");
		}
	}
	
	private void choosePendingTicket() {
		LoginResponseDTO loggedInUser = SessionManager.getLoggedInUser().get();
		long userId = loggedInUser.getId();
		
		Set<Ticket> ticketSet = ticketService.getPendingTicketsWithoutEmployees();
		List<Long> ticketIds = new ArrayList<Long>();
		
		if (!ticketSet.isEmpty()) {
			for (Ticket ticket : ticketSet) {
				ticketIds.add(ticket.getId());
			}
			
			System.out.println(ticketSet);
			System.out.println();
			
			System.out.print("Elegí un ticket pendiente ingresando su id: ");
			String chosenTicketId = scanner.nextLine();
			
			while (!ticketIds.contains(Long.parseLong(chosenTicketId))) {
				System.out.print("ID incorrecto, elegí otro: ");
				chosenTicketId = scanner.nextLine();
			}
			
			final long longChosenTicketId = Long.parseLong(chosenTicketId);
			
			Optional<Ticket> optionalChosenTicket = ticketSet.stream()
					.filter(ticket -> ticket.getId() == longChosenTicketId)
					.findFirst();
			
			Employee employee = employeeService.getByIdWithTickets(userId);
			
			if (optionalChosenTicket.isPresent()) {			
				employeeService.addTicket(employee, optionalChosenTicket.get());
				System.out.println("¡El ticket fue asignado con éxito!");
			}			
		} else {
			System.out.println("Aún no hay tickets pendientes.");
		}
	}
	
	private void closeInProgressTicket() {
		LoginResponseDTO loggedInUser = SessionManager.getLoggedInUser().get();
		long userId = loggedInUser.getId();
		
		Employee employee = employeeService.getByIdWithInProgressTickets(userId);
		
		if (employee != null) {
			Set<Ticket> ticketSet = employee.getManagedTickets();
			List<Long> ticketIds = new ArrayList<Long>();
			
			for (Ticket ticket : ticketSet) {
				ticketIds.add(ticket.getId());
			}
			
			System.out.println(ticketSet);
			System.out.println();
			
			System.out.print("Cerrá un ticket que tengas asignado ingresando su id: ");
			String chosenTicketId = scanner.nextLine();
			
			while (!ticketIds.contains(Long.parseLong(chosenTicketId))) {
				System.out.print("ID incorrecto, elegí otro: ");
				chosenTicketId = scanner.nextLine();
			}
			
			final long longChosenTicketId = Long.parseLong(chosenTicketId);
			
			Optional<Ticket> optionalChosenTicket = ticketSet.stream()
					.filter(ticket -> ticket.getId() == longChosenTicketId)
					.findFirst();
			
			if (optionalChosenTicket.isPresent()) {
				try {
					ticketService.closeTicket(optionalChosenTicket.get().getId());
					System.out.println("¡El ticket fue cerrado con éxito!");
				} catch (Exception e) {
					System.out.println("Error al cerrar el ticket: " + e.getMessage());
				}
			}
		} else {
			System.out.println("No tenés tickets en progreso.");
		}
		
		
	}
	
	private void requestCreateTicket() {
		LoginResponseDTO loggedInUser = SessionManager.getLoggedInUser().get();
		long userId = loggedInUser.getId();
		
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
			System.out.print("Categoría incorrecta, elegí otra: ");
			categoryId = scanner.nextLine();
		}
		
		System.out.print("Título del ticket: ");
		String title = scanner.nextLine();
		System.out.print("Mensaje: ");
		String message = scanner.nextLine();
		Set<AttachedPicture> attachedPictures = new HashSet<AttachedPicture>();
		String pictureName = "0";
		
		System.out.println("Si tu mensaje lleva fotos adjuntas, indicanos el nombre de cada archivo.");
		System.out.println("Si tu mensaje no lleva fotos adjuntas o querés salir, ingresá 0.");
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
	
	private void attachNewTicketMessage() {
		LoginResponseDTO loggedInUser = SessionManager.getLoggedInUser().get();
		long userId = loggedInUser.getId();
		String userType = loggedInUser.getUserType();
		Set<Ticket> ticketSet = null;
		List<Long> ticketIds = new ArrayList<Long>();

		if (userType.equals("employee")) {
			Employee employee = employeeService.getByIdWithInProgressTickets(userId);
			
			if (employee != null) {
				ticketSet = employee.getManagedTickets();				
			}
			
		} else if (userType.equals("customer")) {
			Customer customer = customerService.getByIdWithInProgressTickets(userId);
			
			if (customer != null) {				
				ticketSet = customer.getCreatedTickets();
			}
			
		}

		if (ticketSet != null) {
			for (Ticket ticket : ticketSet) {
				ticketIds.add(ticket.getId());
			}
			
			System.out.println(ticketSet);
			System.out.println();
			
			System.out.print("Enviá un mensaje en un ticket en progreso: ");
			String chosenTicketId = scanner.nextLine();
			
			while (!ticketIds.contains(Long.parseLong(chosenTicketId))) {
				System.out.print("ID incorrecto, elegí otro: ");
				chosenTicketId = scanner.nextLine();
			}
			
			System.out.print("Mensaje: ");
			String message = scanner.nextLine();
			Set<AttachedPicture> attachedPictures = new HashSet<AttachedPicture>();
			String pictureName = "0";
			
			System.out.println("Si tu mensaje lleva fotos adjuntas, indicanos el nombre de cada archivo.");
			System.out.println("Si tu mensaje no lleva fotos adjuntas o querés salir, ingresá 0.");
			System.out.println();
			
			do {
				System.out.print("Foto: ");
				pictureName = scanner.nextLine();
				
				if (!pictureName.equals("0")) {
					attachedPictures.add(new AttachedPicture(pictureName, null));
				}
			} while (!pictureName.equals("0"));
			
			try {
				ticketService.createTicketMessage(
					ticketService.getTicketWithStatusAndMessage(Long.parseLong(chosenTicketId)),
					userService.getById(userId),
					message,
					attachedPictures
				);
				
				System.out.println("Mensaje enviado con éxito.");
			} catch (Exception e) {
				System.out.println("Error al enviar mensaje: " + e.getMessage());
			}
		} else {
			System.out.println("No tenés tickets en progreso.");
		}
		
	}
}
