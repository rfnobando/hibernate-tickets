package session;

import dto.LoginResponseDTO;
import java.util.Optional;

/**
 * Utility class for managing the session of the currently logged-in user.
 * Provides methods to set, retrieve, check, and clear the logged-in user.
 */
public final class SessionManager {
    /**
     * Stores the currently logged-in user's information.
     */
    private static LoginResponseDTO loggedInUser;

    // Private constructor to prevent object creation
    private SessionManager() {
        throw new UnsupportedOperationException("Cannot instantiate SessionManager");
    }

    /**
     * Sets the logged-in user.
     *
     * @param userDto The LoginResponseDTO object containing the user's information.
     */
    public static void setLoggedInUser(LoginResponseDTO userDto) {
        loggedInUser = userDto;
    }
    
    /**
     * Retrieves the logged-in user as an Optional.
     * This avoids potential NullPointerExceptions when no user is logged in.
     *
     * @return An Optional containing the logged-in user's information, or an empty Optional if no user is logged in.
     */
    public static Optional<LoginResponseDTO> getLoggedInUser() {
        return Optional.ofNullable(loggedInUser);
    }

    public static void clearSession() {
        loggedInUser = null;
    }
    
    public static boolean isLoggedIn(){
        return loggedInUser!=null;
    }
    
}