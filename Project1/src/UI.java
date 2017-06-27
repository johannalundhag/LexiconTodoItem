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
				"complete item", "uncomplete item", "find item", "save", "exit" };
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

		case 1:						//add item
			addItem();
			break;
		case 2:						//edit item
			editItem();
			break;
		case 3:						//remove item
			deleteItem();
			break;
		case 4:						//remove all completed items
			deleteAllCompletedItems();
			break;
		case 5:						//list items
			printItemList(todoItemList);
			break;
		case 6:						//complete item
			completeItem();
			break;
		case 7:						//uncomplete item
			uncompleteItem();
			break;
		case 8:						//find item
			findItem();
			break;
		case 9:						//save

			break;
		case 10:					//exit
			doAgain = false;
			break;
		default:
			doAgain = false;
			break;
		}
	}

	private void printItemList(ArrayList<TodoItem> listToPrint) {
		if (listToPrint.size() == 0) {
			System.out.println("There are no items to list.");
			return;
		}

		System.out.println(String.format("%5s%20s%16s%16s%12s", "ID", "Description", "Due date", "Date added", "Completed"));

		//System.out.println("ID \tDescription \tDue date \tDate added \tCompleted");
		for (TodoItem ti : listToPrint)
			System.out.println(ti.toString(listToPrint.indexOf(ti) + 1));
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
			System.out.println("You did not enter a String. Try again.");
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
		int id = getNumberFromUser("Enter ID of item you want to edit:");
		TodoItem itemToEdit = todoItemList.get(id-1);
		String description = getStringFromUser("Old description \"" + itemToEdit.getDescription() + "\", enter new description:");
		int days = getNumberFromUser("In how many days should this task be finished:");
		
		itemToEdit.setDates(days, false);
		itemToEdit.setDescription(description);

	}

	private void deleteItem() {
		int id = getNumberFromUser("Enter ID of item you want to delete:");
		todoItemList.remove(id-1);
	}

	private void deleteAllCompletedItems() {
		int[] indexes = new int[todoItemList.size()];
		
		for(TodoItem ti: todoItemList)
			if(ti.getStatus())
				indexes[indexes.length] = todoItemList.indexOf(ti);

		for(int index: indexes)
			todoItemList.remove(index);
	}

	private void completeItem() {
		int id = getNumberFromUser("Enter ID of item you have completed");
		todoItemList.get(id-1).setDone();
	}

	private void uncompleteItem() {
		int id = getNumberFromUser("Enter ID of item you have not completed:");
		todoItemList.get(id-1).setUnDone();
	}

	private void findItem() {
		ArrayList<TodoItem> searchArray = new ArrayList<TodoItem>();
		String searchString = getStringFromUser("What do you want to search for:");
		
		for(TodoItem ti: todoItemList)
		if(ti.getDescription().contains(searchString))
			searchArray.add(ti);
		
		printItemList(searchArray);
	}

}
