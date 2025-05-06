package model;

import util.ValidatorUtil;

public abstract class User {
    protected long id;
    protected String name;
    protected String surname;
    protected String email;
    protected String password;
    protected ProfilePicture profilePicture;

    public User() {
    }

    public User(String name, String surname, String email, String password, ProfilePicture profilePicture) throws IllegalArgumentException {
        super();
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPassword(password);
        this.profilePicture = profilePicture;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (!ValidatorUtil.isValidName(name)) {
            throw new IllegalArgumentException("Please enter a valid name: Contain only alphabetic characters (letters and accents) and spaces.");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws IllegalArgumentException {
        if (!ValidatorUtil.isValidName(surname)) {
            throw new IllegalArgumentException("Please enter a valid surname: Contain only alphabetic characters (letters and accents) and spaces.");
        }
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if (!ValidatorUtil.isValidEmail(email)) {
            throw new IllegalArgumentException("Please enter a valid email address.");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if (!(ValidatorUtil.isNotEmptyOrNull(password) && password.length() >= 6 && !password.contains(" "))) {
            throw new IllegalArgumentException("Please enter a valid password: at least 6 characters long and without spaces.");
        }
        this.password = password;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", profilePicture=" + profilePicture;
    }

    public boolean equals(User user) {
        return this.id == user.getId();
    }
}