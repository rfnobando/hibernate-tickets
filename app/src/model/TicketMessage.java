package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketMessage {
	public long id;
	public String body;
	public LocalDateTime createdAt;
	public User user;
	public List<AttachedPicture> pictures;
	
	public TicketMessage() {}
	 
	public TicketMessage(String body, LocalDateTime createdAt, User user) {
		super();
		this.body = body;
		this.createdAt = createdAt;
		this.user = user;
		this.pictures = new ArrayList<AttachedPicture>();
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<AttachedPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<AttachedPicture> pictures) {
		this.pictures = pictures;
	}

	@Override
	public String toString() {
		return "TicketMessage [id=" + id + ", body=" + body + ", createdAt=" + createdAt + ", user=" + user
				+ ", pictures=" + pictures + "]";
	}
	
	
	
}
