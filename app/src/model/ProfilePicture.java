package model;

public class ProfilePicture {
	private long id;
	private String fileName;
	private User user;
	
	public ProfilePicture(){}
	
	public ProfilePicture(String fileName, User user) {
		super();
		this.fileName = fileName;
		this.user = user;
	}
	public long getId() {
		return id;
	}
	private void setId(long id) {
		this.id = id;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ProfilePhoto [id=" + id + ", fileName=" + fileName + "]";
	}
	
	public boolean equals(ProfilePicture picture) {
		return this.fileName.equals(picture.getFileName());
	}
}
