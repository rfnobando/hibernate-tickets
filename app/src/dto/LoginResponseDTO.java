package dto;

import model.User;

/**
 * This class is a Data Transfer Object (DTO) for login responses.
 * It encapsulates the user's id, email, name, and surname to be sent as a response.
 */
public class LoginResponseDTO {
    private final long id;
    private final String email;
    private final String name;
    private final String surname;

    /**
     * Parameterized constructor.
     * Initializes a LoginResponseDTO object using a User entity.
     *
     * @param user The User entity containing the id, email, name, and surname.
     */
    public LoginResponseDTO(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.id = user.getId();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public long getId() {
        return id;
    }
}