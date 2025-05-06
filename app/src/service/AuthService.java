package service;

import dao.UserDAO;
import dto.LoginResponseDTO;
import model.User;
import org.hibernate.HibernateException;
import org.mindrot.jbcrypt.BCrypt;
import session.SessionManager;
import java.util.Optional;

/**
 * Service class responsible for handling login-related operations.
 */
public class AuthService {
    private final UserDAO userDao;

    public AuthService() {
        this.userDao = new UserDAO();
    }

    public Optional<LoginResponseDTO> login(String email, String password) throws HibernateException {
        // check if user is logged in
        if (SessionManager.isLoggedIn()) {
            throw new IllegalArgumentException("User is already logged in");
        }
        Optional<User> userOptional = userDao.findUserByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Verify the provided password against the hashed password stored in the database.
            if (BCrypt.checkpw(password, user.getPassword())) {
                LoginResponseDTO userResponseDto = new LoginResponseDTO(user);
                // Set the logged-in user in the session manager.
                SessionManager.setLoggedInUser(userResponseDto);
                return Optional.of(userResponseDto); // Return the response DTO if login is successful.
            }
        }
        return Optional.empty(); // Return an empty Optional if login fails.
    }

    //In case the registration logic changes, we could opt to create one for customers and another for employees.
    //In case of successful registration, the ID of the registered user is returned.
    public int register(User user) throws IllegalArgumentException {
        // Check if the user is already registered
        if (userDao.findUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
        // Hash the password before saving
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        return userDao.create(user);
    }

    public void logout() {
        SessionManager.clearSession();
    }
}//Fin AuthService