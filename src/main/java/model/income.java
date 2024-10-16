package model;

public class income {
	private int id;
	private double income;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public income(int id, double income) {

		this.id = id;
		this.income = income;
	}

}
