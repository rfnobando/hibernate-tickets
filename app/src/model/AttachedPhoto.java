package model;

public class AttachedPhoto {
	public long id;
	public String name;
	
	public AttachedPhoto(){}
	
	public AttachedPhoto(String name) {
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
		return "AttachedPhoto [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
