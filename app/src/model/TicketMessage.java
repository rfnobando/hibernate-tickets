package model;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketMessage {
	private long id;
	private String body;
	private Timestamp createdAt;
	private User user;
	private Set<AttachedPicture> attachedPictures;
	private Ticket ticket;// Required for Hibernate mapping, even if we don't use it in the Java logic
	
	public TicketMessage() {}
	 
	public TicketMessage(String body, Timestamp createdAt, User user) {
		super();
		this.body = body;
		this.createdAt = createdAt;
		this.user = user;
		this.attachedPictures = new HashSet<AttachedPicture>();
	}
	public long getId() {
		return id;
	}
	private void setId(long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Set<AttachedPicture> getAttachedPictures() {
		return attachedPictures;
	}

	public void setAttachedPictures(Set<AttachedPicture> attachedPictures) {
		this.attachedPictures = attachedPictures;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "TicketMessage [id=" + id + ", body=" + body + ", createdAt=" + createdAt + ", user=" + user
				+ ", attachedPictures=" + attachedPictures + "]";
	}
	
	
	
}
