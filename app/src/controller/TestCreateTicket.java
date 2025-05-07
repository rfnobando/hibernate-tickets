package controller;

//import java.security.Timestamp;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import model.AttachedPicture;
import model.Customer;
import model.Ticket;
import model.TicketCategory;
import model.User;
import service.CustomerService;
import service.TicketCategoryService;
import service.TicketService;

public class TestCreateTicket {

	public static void main(String[] args) {
		
		
		TicketService abmTicket = new TicketService();
		TicketCategoryService abmTicketCategory = new TicketCategoryService();
		CustomerService abmCustomer = new CustomerService();
		long seCreo= 0;
		long seAgrego = 0;
		
		/*
		 * For this to work, you need a user and a customer that inherits from user.
		 * (In this case, data was manually inserted into MySQL):
		 * 
		 * 1. First insert the base user into the 'users' table:
		 *    INSERT INTO `hibernate_tickets`.`users` (`first_name`, `last_name`, `email`, `password`)
		 *    VALUES ('Juan', 'Pérez', 'juan.perez@example.com', 'password123');
		 * 
		 * 2. Then insert into the 'customers' table, using the user ID from the previous step.
		 *    Assuming the generated ID was 1:
		 *    INSERT INTO `hibernate_tickets`.`customers` (`user_id`)
		 *    VALUES (1);
		 */
		try {
			/*
			seCreo = abmTicket.createTicket(
				"El último de todos los tickets",
				abmCustomer.getById(5), 
				abmTicketCategory.getById(1), 
				"q capo deepseek 2",
				null
			);
			*/
			
			
			// abmTicket.closeTicket(abmTicket.getTicketWithStatusAndMessage(1));

			AttachedPicture attachedPicture1 = new AttachedPicture("file1.png", null);
			AttachedPicture attachedPicture2 = new AttachedPicture("file2.png", null);
			AttachedPicture attachedPicture3 = new AttachedPicture("file3.png", null);
			AttachedPicture attachedPicture4 = new AttachedPicture("file4.png", null);
			AttachedPicture attachedPicture5 = new AttachedPicture("file5.png", null);
			Set<AttachedPicture> attachedPictures = new HashSet<AttachedPicture>();
			attachedPictures.add(attachedPicture1);
			attachedPictures.add(attachedPicture2);
			attachedPictures.add(attachedPicture3);
			attachedPictures.add(attachedPicture4);
			attachedPictures.add(attachedPicture5);
			
			abmTicket.createTicketMessage(
				abmTicket.getTicketWithStatusAndMessage(2),
				abmCustomer.getById(5),
				"Este seria el último mensaje",
				attachedPictures
			);
			
			//Ticket ticket, Timestamp createdAt, User user, String body, Set<AttachedPicture> attachedPictures)
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(seCreo);// Prints the created ticket's ID
			System.out.println(seAgrego);// Prints the new ticketMessage ID
		}
		
		
	}

}
