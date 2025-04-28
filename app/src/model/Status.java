package model;

public class Status {
	public long id;
	public String name;
	
	public Status(){}
	
	public Status(String name) {
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
		return "State [id=" + id + ", name=" + name + "]";
	}
	
	
}
