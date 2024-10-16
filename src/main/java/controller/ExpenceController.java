package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import model.Category;
import model.Expense;
import model.income;
import service.ExpenseManager;

public class ExpenceController {
	Scanner scanner = new Scanner(System.in);
	ExpenseManager manager;

	public ExpenceController() throws SQLException {
		manager = new ExpenseManager();
	}

	public void addIncome() throws SQLException {
		System.out.println("enter userid:");
		int id = scanner.nextInt();
		System.out.println("enter your income:");
		int salary = scanner.nextInt();
		if(id>0 && salary>0) {
			income income = new income(id, salary);
			manager.addIncome(income);
		}
		else {
			System.out.println("give a valid id and salary");
		}
	}
    public void updateIncome() throws SQLException {
    	System.out.println("enter userid:");
		int id = scanner.nextInt();
		System.out.println("enter your income:");
		int salary = scanner.nextInt();
		income income = new income(id, salary);
		manager.updateIncome(income);
    }
	
    public void addExpense() {

		try {
			System.out.print("Enter user ID: ");
			int userId = scanner.nextInt();
			System.out.print("Enter categoryid: ");
			int categoryId = scanner.nextInt();
			System.out.print("Enter expense amount: ");
			double amount = scanner.nextDouble();
			System.out.print("Enter expense description: ");
			String description = scanner.next();
			System.out.print("Enter date (YYYY-MM-DD): ");
			String date = scanner.next();
			LocalDate convertDate = LocalDate.parse(date);
			Date sQLDate = Date.valueOf(convertDate);
			System.out.println("enter a category");
			String categoryname = scanner.next();
			String type = description;
		   if(userId>0 && categoryId>0 && amount>0) {
			Expense expense = new Expense(1, userId, categoryId, amount, description, sQLDate);
			Category category = new Category(categoryId, categoryname, type);
			manager.addExpenseCategory(expense,category);
		   }
		   else {
			   System.out.println("giv a positive id and amount");
		   }
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void updateExpences() {
		try {

			System.out.println("enter a id");
			Integer id = scanner.nextInt();
			System.out.print("Enter expense amount: ");
			double updamount = scanner.nextDouble();
			System.out.print("Enter expense description: ");
			String upddescription = scanner.next();
			System.out.print("Enter categoryid: ");
			int updcategoryId = scanner.nextInt();
			System.out.print("Enter user ID: ");
			int upduserId = scanner.nextInt();
			System.out.print("Enter date (YYYY-MM-DD): ");
			String upddate = scanner.next();
			LocalDate updateDate = LocalDate.parse(upddate);
			Date updateSQLDate = Date.valueOf(updateDate);
			System.out.println("enter a category");
			String updcategoryname = scanner.next();
			String updtype = upddescription;
			if(id>0 && upduserId>0 && updcategoryId>0 && updamount>0) {
			Expense updexpense = new Expense(id, upduserId, updcategoryId, updamount, upddescription, updateSQLDate);
			Category updcategory = new Category(updcategoryId, updcategoryname, updtype);
			manager.updateExpensecategory(updexpense,updcategory);
			}
			else {
				System.out.println("enter a positive id userid and categoryid and amount");
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void deleteExpenses() throws SQLException {
		System.out.println("Enter a id");
		int delid = scanner.nextInt();
		manager.delete(delid);
	}

	public void viewExpenceByCate() throws SQLException {
		System.out.println("enter the category=");
		String getcategory = scanner.next();
		manager.ExpencesByCategory(getcategory);
	}

	public void expencesByDate() throws SQLException {
		System.out.println("start  date");
		String startexdate = scanner.next();
		LocalDate startlDate = LocalDate.parse(startexdate);
		Date startdate = Date.valueOf(startlDate);
		System.out.println("end date");
		String endexdate = scanner.next();
		LocalDate endlDate = LocalDate.parse(endexdate);
		Date enddate = Date.valueOf(endlDate);
		manager.viewExpensesByDateRange(startdate, enddate);
	}

	public void report() throws SQLException {
		System.out.println("enter userid");
		int id = scanner.nextInt();
		manager.GenerateCategoryReport(id);
	}
}
