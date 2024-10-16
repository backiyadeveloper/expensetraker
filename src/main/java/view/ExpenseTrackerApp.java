package view;

import java.sql.SQLException;
import java.util.Scanner;
import controller.ExpenceController;
import service.ExpenseManager;

public class ExpenseTrackerApp {

	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		ExpenceController controller = new ExpenceController();
		ExpenseManager manager = new ExpenseManager();

		while (true) {
			System.out.println("\nPersonal Expense Tracker System");
			System.out.println("1. Add Income");
			System.out.println("2. update Income");
			System.out.println("3. Add Expense");
			System.out.println("4. Update Expense");
			System.out.println("5. Delete Expense");
			System.out.println("6. View All Expenses");
			System.out.println("7. View Expenses by Category");
			System.out.println("8. View Expenses by Date Range");
			System.out.println("9. Generate Category Report");
			System.out.println("10. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();

			switch (choice) {
				case 1:
					controller.addIncome();
					break;
				case 2:
					controller.updateIncome();
					break;
				case 3:
	
					controller.addExpense();
					break;
	
				case 4:
					controller.updateExpences();
					break;
	
				case 5:
					controller.deleteExpenses();
					break;
	
				case 6:
					manager.viewAllExpenses();
					break;
	
				case 7:
					controller.viewExpenceByCate();
					break;
	
				case 8:
					controller.expencesByDate();
					break;
	
				case 9:
					controller.report();
					break;
	
				case 10:
					System.out.println("Exiting...");
					System.exit(0);
					break;
	
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
