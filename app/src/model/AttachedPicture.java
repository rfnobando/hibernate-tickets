package model;

public class AttachedPicture {
	private long id;
	private String fileName;
	private TicketMessage ticketMessage;
	
	public AttachedPicture(){}
	
	public AttachedPicture(String fileName, TicketMessage ticketMessage) {
		super();
		this.fileName = fileName;
		this.ticketMessage = ticketMessage;
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
	
	public TicketMessage getTicketMessage() {
		return ticketMessage;
	}
	
	public void setTicketMessage(TicketMessage ticketMessage) {
		this.ticketMessage = ticketMessage;
	}

	@Override
	public String toString() {
		return "AttachedPicture [id=" + id + ", fileName=" + fileName + "]";
	}
	
	public boolean equals(AttachedPicture picture) {
		return this.fileName.equals(picture.getFileName());
	}
	
}
