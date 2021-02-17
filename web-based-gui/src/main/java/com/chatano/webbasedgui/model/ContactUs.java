package com.chatano.webbasedgui.model;

import java.util.Date;

public class ContactUs {
	private String name;
	private String email;
	private String description;
	private boolean isSubmited;
	private Date timestamp;

	public ContactUs() {
		this.isSubmited = false;
	}

	public ContactUs(boolean isSubmited) {
		this.isSubmited = isSubmited;
	}

	public ContactUs(String name, String email, String description) {
		super();
		this.name = name;
		this.email = email;
		this.description = description;
	}

	public boolean getIsSubmited() {
		return isSubmited;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSubmited(boolean isSubmited) {
		this.isSubmited = isSubmited;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ContactUs{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", description='" + description + '\'' +
				", isSubmited=" + isSubmited +
				", timestamp=" + timestamp +
				'}';
	}
}
