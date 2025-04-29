package model;

public abstract class User {
	
	protected long id;
	protected String name;
	protected String surname;
	protected String email;
	protected String password;
	protected ProfilePicture profilePicture;
	
	public User(){}
	
	public User(String name, String surname, String email, String password, ProfilePicture profilePicture) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
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
