package model;

import java.sql.Date;

public class Expense {
	private int id;
	private int userId;
	private int categoryId;
	private double amount;
	private String description;
	private Date date;

	public Expense(int id, int userId, int categoryId, double amount, String description, Date date) {
		this.id = id;
		this.userId = userId;
		this.categoryId = categoryId;
		this.amount = amount;
		this.description = description;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
