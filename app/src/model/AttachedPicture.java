package model;

public class AttachedPicture {
	public long id;
	public String fileName;
	
	public AttachedPicture(){}
	
	public AttachedPicture(String fileName) {
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
		return "AttachedPicture [id=" + id + ", fileName=" + fileName + "]";
	}
	
	public boolean equals(AttachedPicture picture) {
		return this.fileName.equals(picture.getFileName());
	}
	
}
