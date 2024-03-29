package com.prs.business;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//	@ManyToOne(fetch=FetchType.EAGER)
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;
	private String description;
	private String justification;
	private LocalDate dateNeeded;
	private String deliveryMode;
	private String status;
	private double total;
	private LocalDateTime submittedDate;
	private String reasonForRejection;  

	public PurchaseRequest () {
		id =0;
		user =null;
		description ="";
		justification ="";
		dateNeeded =null;
		deliveryMode ="";
		status ="";
		total =0.0;
		submittedDate = null;
		reasonForRejection ="";
	}

	public PurchaseRequest (int id, User user, String description, String justification, LocalDate dateNeeded,
			String deliveryMode, String status, double total, 
			//	Date submittedDate, 
			String reasonForRejection,
			boolean isActive, LocalDate dateCreated, LocalDate dateUpdated, int updatedByUser) {
		super();
		this.id = id;
		this.user = user;
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		//this.submittedDate = submittedDate;
		this.reasonForRejection = reasonForRejection;
	}

	public PurchaseRequest (User user, String description, String justification, LocalDate dateNeeded,
			String deliveryMode, String status, double total, String reasonForRejection) {
		//super();
		this.user = user;
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		this.reasonForRejection = reasonForRejection;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getJustification() {
		return justification;
	}


	public void setJustification(String justification) {
		this.justification = justification;
	}


	public LocalDate getDateNeeded() {
		return dateNeeded;
	}


	public void setDateNeeded(LocalDate dateNeeded) {
		this.dateNeeded = dateNeeded;
	}


	public String getDeliveryMode() {
		return deliveryMode;
	}


	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public LocalDateTime getSubmittedDate() {
		return submittedDate;
	}


	public void setSubmittedDate(LocalDateTime submittedDate) {
		this.submittedDate = submittedDate;
	}


	public String getReasonForRejection() {
		return reasonForRejection;
	}


	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}

	@Override
	public String toString() {
		return "PurchaseRequest [id=" + id + ", user=" + user + ", description=" + description + ", justification="
				+ justification + ", dateNeeded=" + dateNeeded + ", deliveryMode=" + deliveryMode + ", status=" + status
				+ ", total=" + total + ", submittedDate=" + submittedDate + ", reasonForRejection=" + reasonForRejection
				+ "]";
	}
}
