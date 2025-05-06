package controller;

import dao.UserDAO;
import model.User;
import org.hibernate.HibernateException;

import java.util.Optional;

public class UserDaoTest {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        String emailToSearch = "alanleonelmacie2l@gmail.com"; // Cambiá por un email real

        try {
            Optional<User> userOptional = userDAO.findUserByEmail(emailToSearch);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                System.out.println("Usuario encontrado:");
                System.out.println("ID: " + user.getId());
                System.out.println("Nombre: " + user.getName());
                System.out.println("Apellido: " + user.getSurname());
                System.out.println("Email: " + user.getEmail());
            } else {
                System.out.println("No se encontró ningún usuario con el email: " + emailToSearch);
            }

        } catch (HibernateException e) {
            System.err.println("Error al buscar el usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
