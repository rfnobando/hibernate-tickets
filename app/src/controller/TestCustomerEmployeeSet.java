package controller;

import model.Customer;
import model.Employee;
import model.TicketCategory;
import service.CustomerService;
import service.EmployeeService;
import service.TicketCategoryService;

public class TestCustomerEmployeeSet {

	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		Customer customer = customerService.getByIdWithTickets(5);
		EmployeeService employeeService = new EmployeeService();
		Employee employee = employeeService.getByIdWithTickets(6);
		TicketCategoryService ticketCategoryService = new TicketCategoryService();
		TicketCategory ticketCategory = ticketCategoryService.getByIdWithTickets(1);
		
		System.out.println(ticketCategory.getTickets());
		
		
		// System.out.println(customer.getCreatedTickets());
		// System.out.println();
		
		// System.out.println(employee.getManagedTickets());
		
		// TicketService ticketService = new TicketService();
		// Ticket ticket = ticketService.getTicketWithStatusAndMessage(2);
	}
}
