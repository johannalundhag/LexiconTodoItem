import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UI {

	private String[] options;
	private ArrayList<TodoItem> todoItemList;
	private BufferedReader br;
	private boolean doAgain;

	public UI() {
		options = new String[] { "add item", "edit item", "remove item", "remove all completed items", "list items",
				"complete item", "uncomplete items", "find item", "save", "exit" };
		todoItemList = new ArrayList<TodoItem>();
		br = new BufferedReader(new InputStreamReader(System.in));
		doAgain = true;
	}

	public boolean getInput() {
		printOptions();
		int noOfOperation = getNumberFromUser("What do you want to do? Please enter number:");
		doOperation(noOfOperation);

		return doAgain;
	}

	private void printOptions() {
		System.out.println("\nOptions:");
		System.out.println("--------------------------------------------");
		for (int i = 0; i < options.length; i++)
			System.out.println((i + 1) + "\t" + options[i]);
	}

	private void doOperation(int number) {
		switch (number) {

		case 1:
			addItem();
			break;
		case 2:
			editItem();
			break;
		case 3:
			deleteItem();
			break;
		case 4:
			deleteAllCompletedItems();
			break;
		case 5:
			printItemList();
			break;
		case 6:
			completeItem();
			break;
		case 7:
			uncompleteItem();
			break;
		case 8:
			findItem();
			break;
		case 9:

			break;
		case 10:
			doAgain = false;
			break;
		default:
			doAgain = false;
			break;
		}
	}

	private void printItemList() {
		if (todoItemList.size() == 0) {
			System.out.println("There are no items to list.");
			return;
		}

		System.out.println("ID \tDescription \tDue date \tDate added \tCompleted");
		for (TodoItem ti : todoItemList)
			System.out.println(ti.toString(todoItemList.indexOf(ti) + 1));
	}

	// TODO: fix issue with exception.
	private int getNumberFromUser(String message) {
		try {
			System.out.println(message);
			int number = Integer.parseInt(br.readLine());
			return number;
		} catch (NumberFormatException e) {
			System.out.println("You did not enter a number. Try again");
			return 0;
		} catch (IOException e) {
			System.out.println("You did not enter a number. Try again");
			return 0;
		}
	}

	// TODO: fix issue with exception.
	private String getStringFromUser(String message) {
		try {
			System.out.println(message);
			String userInput = br.readLine();
			return userInput;
		} catch (IOException e) {
			System.out.println("You did not enter a String. Try again");
			return "";
		}
	}

	private void addItem() {
		String description = getStringFromUser("Description of the new todo item:");
		int days = getNumberFromUser("In how many days should this task be finished:");

		TodoItem newItem = new TodoItem(description, days);
		todoItemList.add(newItem);
	}

	private void editItem() {
		// TODO Auto-generated method stub

	}

	private void deleteItem() {
		int index = getNumberFromUser("Enter ID of item you want to delete");
		todoItemList.remove(index);
	}

	private void deleteAllCompletedItems() {
		// TODO Auto-generated method stub

	}

	private void completeItem() {
		int index = getNumberFromUser("Enter ID of item you have completed");
		todoItemList.get(index).setDone();
	}

	private void uncompleteItem() {
		int index = getNumberFromUser("Enter ID of item you have not completed");
		todoItemList.get(index).setUnDone();
	}

	private void findItem() {
		// TODO Auto-generated method stub

	}

}
