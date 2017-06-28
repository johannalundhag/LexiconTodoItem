import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class UI {

	private String[] options;
	private ArrayList<TodoItem> todoItemList;
	private BufferedReader br;
	private boolean doAgain;

	public UI() {
		options = new String[] { "add item", "edit item", "remove item", "remove all completed items", "list items",
				"complete item", "uncomplete item", "find item", "save (not implemented)", "exit" };
		todoItemList = new ArrayList<TodoItem>();
		br = new BufferedReader(new InputStreamReader(System.in));
		createItems();
		doAgain = true;
	}
	
	private void createItems(){
		TodoItem ti1 = new TodoItem("äta lunch", 3);
		TodoItem ti2 = new TodoItem("äta middag", 5);
		TodoItem ti3 = new TodoItem("sova", 1);
		TodoItem ti4 = new TodoItem("städa", 10);
		
		todoItemList.add(ti1);
		todoItemList.add(ti3);
		todoItemList.add(ti2);
		todoItemList.add(ti4);		
	}

	public boolean getInput() {
		int noOfOperation = getNumberFromUser("\nWhat do you want to do? To list options type 0.\nPlease enter number:");
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
			printOptions();
			break;
		}
	}

	private void printItemList(ArrayList<TodoItem> listToPrint) {
		if (listToPrint.size() == 0) {
			System.out.println("There are no items to list.");
			return;
		}

		System.out.println(String.format("%5s%20s%16s%16s%12s", "ID", "Description", "Due date", "Date added", "Completed"));

		for (TodoItem ti : listToPrint)
			System.out.println(ti.toString(listToPrint.indexOf(ti) + 1));
	}

	private int getNumberFromUser(String message) {
		int number = 100;
		System.out.println(message);
		
		while(number == 100){
			try {
				number = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.println("You did not enter a number. Try again");
			}
		}
		return number;
	}
	
	private int getIDFromUser(String message) {
		int number = getNumberFromUser(message);
		
		while(number > todoItemList.size() | number == 0){
			
			number = getNumberFromUser("Not a valid ID. Please try again:");
		}
		return number;
	}

	private String getStringFromUser(String message) {
		String str = "";
		System.out.println(message);
		
		while (str.equals("")){
			try {
				str = br.readLine();
			} catch (Exception e) {
				System.out.println("Something went wrong. Try again.");
			}
		}
		return str;
	}

	private void addItem() {
		String description = getStringFromUser("Description of the new todo item:");
		int days = getNumberFromUser("In how many days should this task be finished:");

		TodoItem newItem = new TodoItem(description, days);
		todoItemList.add(newItem);
	}

	private void editItem() {
		int id = getIDFromUser("Enter ID of item you want to edit:");
		TodoItem itemToEdit = todoItemList.get(id - 1);
		String description = getStringFromUser("Old description \"" + itemToEdit.getDescription() + "\", enter new description:");
		int days = getNumberFromUser("In how many days should this task be finished:");

		itemToEdit.setDates(days, false);
		itemToEdit.setDescription(description);
	}

	private void deleteItem() {
		int id = getIDFromUser("Enter ID of item you want to delete:");
		todoItemList.remove(id - 1);
	}

	private void deleteAllCompletedItems() {
		for (Iterator<TodoItem> iterator = todoItemList.iterator(); iterator.hasNext();) {
			TodoItem ti = iterator.next();
			if (ti.getStatus())
				iterator.remove();
		}
		System.out.println("Completed items removed");
	}

	private void completeItem() {
		int id = getIDFromUser("Enter ID of item you have completed");
		todoItemList.get(id - 1).setDone();
	}

	private void uncompleteItem() {
		int id = getIDFromUser("Enter ID of item you have not completed:");
		todoItemList.get(id - 1).setUnDone();
	}

	private void findItem() {
		ArrayList<TodoItem> searchArray = new ArrayList<TodoItem>();
		String searchString = getStringFromUser("What do you want to search for:");

		for (TodoItem ti : todoItemList)
			if (ti.getDescription().contains(searchString))
				searchArray.add(ti);

		printItemList(searchArray);
	}

}
