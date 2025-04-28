package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketMessage {
	public long id;
	public String text;
	public LocalDate createDate;
	public User user;
	public List<AttachedPhoto> photos;
	public TicketMessage(String text, LocalDate createDate, User user) {
		super();
		this.text = text;
		this.createDate = createDate;
		this.user = user;
		this.photos = new ArrayList<AttachedPhoto>();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<AttachedPhoto> getPhotos() {
		return photos;
	}
	public void setPhotos(List<AttachedPhoto> photos) {
		this.photos = photos;
	}
	@Override
	public String toString() {
		return "TicketMessage [id=" + id + ", text=" + text + ", createDate=" + createDate + ", user=" + user
				+ ", photos=" + photos + "]";
	}
	
	
	
}
