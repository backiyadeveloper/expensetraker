package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.JdbcConnection;
import database.DatabaseConnection;
import model.Category;
import model.Expense;
import model.income;

public class ExpenseManager {
	private static final DatabaseConnection connoction = new DatabaseConnection();
	private Connection jdbcConnection;

	public ExpenseManager() throws SQLException {
		jdbcConnection = connoction.getConnection();
	}

	public void addIncome(income income) throws SQLException {
		String selectquery="select * from Income where userid=?";
		PreparedStatement ps=jdbcConnection.prepareStatement(selectquery);
		ps.setInt(1, income.getId());
		ResultSet rs=ps.executeQuery();
		if(!rs.next()) {
			String query = " INSERT INTO Income (userid, income) values(?,?)";
			PreparedStatement preparedstatement = jdbcConnection.prepareStatement(query);
			preparedstatement.setInt(1, income.getId());
			preparedstatement.setDouble(2, income.getIncome());
			preparedstatement.executeUpdate();
			System.out.println("Income added successfully.");
		}
		else {
			System.out.println("user already exists\n");
		}
	
	}
   public void updateIncome(income income) throws SQLException {
	   String query=" update Income set income=? where userid=?;";
	   PreparedStatement ps=jdbcConnection.prepareStatement(query);
		ps.setDouble(1, income.getIncome());
		ps.setInt(2, income.getId());
		ps.executeUpdate();
		System.out.println("updated sucessfully");
   }
	public void addExpenseCategory(Expense expense,Category category) throws SQLException {
		String query = "INSERT INTO Expenses (userId, categoryId, amount, description, date) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement preparedstatement = jdbcConnection.prepareStatement(query);
		preparedstatement.setInt(1, expense.getUserId());
		preparedstatement.setInt(2, expense.getCategoryId());
		preparedstatement.setDouble(3, expense.getAmount());
		preparedstatement.setString(4, expense.getDescription());
		preparedstatement.setDate(5, expense.getDate());
		preparedstatement.executeUpdate();
		System.out.println("Expense added successfully.");
		String categoryquery = "insert into Categories(categoryid,name,type) values(?,?,?)";
		PreparedStatement statement = jdbcConnection.prepareStatement(categoryquery);
        statement.setInt(1, category.getCategoryid());
		statement.setString(2, category.getName());
		statement.setString(3, category.getType());
		statement.executeUpdate();
		System.out.println("category added successfully");
	}

	

	public void updateExpensecategory(Expense expense,Category updcategory) throws SQLException {
		String query = "update Expenses set userid=?,amount=?,description=?,date=? where id=? and categoryid=?";
		PreparedStatement preparedstatement = jdbcConnection.prepareStatement(query);
		preparedstatement.setInt(1, expense.getUserId());
		preparedstatement.setDouble(2, expense.getAmount());
		preparedstatement.setString(3, expense.getDescription());
		preparedstatement.setDate(4, expense.getDate());
		preparedstatement.setInt(5, expense.getId());
		preparedstatement.setInt(6, expense.getCategoryId());
		preparedstatement.executeUpdate();
		System.out.println("Expense updated successfully.");
		String categoryquery = "update Categories set categoryid=?,name=?,type=? where categoryid=?";
		PreparedStatement statement = jdbcConnection.prepareStatement(categoryquery);
		statement.setInt(1, updcategory.getCategoryid());
		statement.setString(2, updcategory.getName());
		statement.setString(3, updcategory.getType());
		statement.setInt(4, updcategory.getCategoryid());
		statement.executeUpdate();
		System.out.println("category updated successfully");
	}

	public void delete(int id) throws SQLException {
		String query = " delete from Categories where categoryid=(select categoryid from Expenses where id=?)";
		PreparedStatement preparedstatement = jdbcConnection.prepareStatement(query);
		preparedstatement.setInt(1, id);
		preparedstatement.executeUpdate();
        String query1 = "delete from Expenses where id=?";
		PreparedStatement preparedstatement1 = jdbcConnection.prepareStatement(query1);
		preparedstatement1.setInt(1, id);
		preparedstatement1.executeUpdate();
		System.out.println("deleted expenses id");
	}

	public void viewAllExpenses() throws SQLException {

		String query = "SELECT * FROM Expenses";
		PreparedStatement preparedstatement = jdbcConnection.prepareStatement(query);
		ResultSet result = preparedstatement.executeQuery();
		while (result.next()) {
			System.out.println("id=" + result.getInt("id") + ", userid=" + result.getInt("userid") + ", categoryid="
					+ result.getInt("categoryid") + ", amount=" + result.getDouble("amount") + ", description="
					+ result.getString("description") + ", date=" + result.getDate("date"));
		}

	}

	public void ExpencesByCategory(String category) throws SQLException {

		String query = "select id,amount,description,type from Expenses join Categories on Expenses.categoryid=Categories.categoryid where type=?";
		PreparedStatement preparedstatement = jdbcConnection.prepareStatement(query);
		preparedstatement.setString(1, category);
		ResultSet result = preparedstatement.executeQuery();
		while (result.next()) {
			int id = result.getInt("id");
			double amount = result.getDouble("amount");
			String description = result.getString("description");
			String type = result.getString("type");
			System.out.println(
					"id=" + id + "," + "amount=" + amount + "," + "description=" + description + "," + "type=" + type);
		}

	}

	public void viewExpensesByDateRange(Date startdate, Date enddate) throws SQLException {

		String query = "select * from Expenses where date  between ? and ?";
		PreparedStatement preparedstatement = jdbcConnection.prepareStatement(query);
        preparedstatement.setDate(1, startdate);
		preparedstatement.setDate(2, enddate);
		ResultSet result = preparedstatement.executeQuery();
		while (result.next()) {
			int id = result.getInt("id");
			int userid = result.getInt("userid");
			int categoryid = result.getInt("categoryId");
			double amount = result.getDouble("amount");
			String description = result.getString("description");
			Date date = result.getDate("date");
			System.out.println("id=" + id + "," + "userid=" + userid + "," + "categoryid=" + categoryid + ","
					+ "amount=" + amount + "," + "description=" + description + "," + "date=" + date);
		}
	}

	public void GenerateCategoryReport(int id) throws SQLException {
		double income = 0;
		String query1 = "select income from Income where userid=?";
		PreparedStatement preparedstatement1 = jdbcConnection.prepareStatement(query1);
		preparedstatement1.setDouble(1, id);
		ResultSet result1 = preparedstatement1.executeQuery();
		while (result1.next()) {
			income = result1.getDouble("income");
		}
		String query = "select type,sum(amount)  persentage from Expenses e join Categories c on e.categoryid=c.categoryid where e.userid=? group by type ;";
		PreparedStatement preparedstatement = jdbcConnection.prepareStatement(query);
		preparedstatement.setInt(1, id);
		ResultSet result = preparedstatement.executeQuery();
		while (result.next()) {
			String category = result.getString("type");
			double persentage = result.getDouble("persentage");
		    int percentage=(int)((persentage/income)*100);
			System.out.println("category=" + category + "," + "persentage=" + percentage + "%");

		}
	}
}
