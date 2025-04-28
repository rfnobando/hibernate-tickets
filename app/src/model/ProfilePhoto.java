package model;

public class ProfilePhoto {
	public long id;
	public String name;
	
	public ProfilePhoto(){}
	
	public ProfilePhoto(String name) {
		super();
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ProfilePhoto [id=" + id + ", name=" + name + "]";
	}
	
	
}
