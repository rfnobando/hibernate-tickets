package model;

public class ProfilePicture {
	public long id;
	public String fileName;
	
	public ProfilePicture(){}
	
	public ProfilePicture(String fileName) {
		super();
		this.fileName = fileName;
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

	@Override
	public String toString() {
		return "ProfilePhoto [id=" + id + ", fileName=" + fileName + "]";
	}
	
	public boolean equals(ProfilePicture picture) {
		return this.fileName.equals(picture.getFileName());
	}
}
