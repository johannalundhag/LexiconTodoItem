import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class OperationsExecuter {

	private ArrayList<TodoItem> todoItemList;
	private BufferedReader br;
	
	public OperationsExecuter(){
		todoItemList = new ArrayList<TodoItem>();
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void createItems(){
		TodoItem ti1 = new TodoItem("äta lunch", 3);
		TodoItem ti2 = new TodoItem("äta middag", 5);
		TodoItem ti3 = new TodoItem("sova", 1);
		TodoItem ti4 = new TodoItem("städa", 10);
		
		todoItemList.add(ti1);
		todoItemList.add(ti3);
		todoItemList.add(ti2);
		todoItemList.add(ti4);		
	}
	
	public ArrayList<TodoItem> getTodoItemList(){
		return todoItemList;
	}
	
	public void printItemList(ArrayList<TodoItem> listToPrint) {
		if (listToPrint.size() == 0) {
			System.out.println("There are no items to list.");
			return;
		}

		System.out.println(String.format("%5s%20s%16s%16s%12s", "ID", "Description", "Due date", "Date added", "Completed"));

		for (TodoItem ti : listToPrint)
			System.out.println(ti.toString(todoItemList.indexOf(ti) + 1));
	}

	public int getNumberFromUser(String message) {
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

	public void addItem() {
		String description = getStringFromUser("Description of the new todo item:");
		int days = getNumberFromUser("In how many days should this task be finished:");

		TodoItem newItem = new TodoItem(description, days);
		todoItemList.add(newItem);
	}

	public void editItem() {
		int id = getIDFromUser("Enter ID of item you want to edit:");
		TodoItem itemToEdit = todoItemList.get(id - 1);
		String description = getStringFromUser("Old description \"" + itemToEdit.getDescription() + "\", enter new description:");
		int days = getNumberFromUser("In how many days should this task be finished:");

		itemToEdit.setDates(days, false);
		itemToEdit.setDescription(description);
	}

	public void deleteItem() {
		int id = getIDFromUser("Enter ID of item you want to delete:");
		todoItemList.remove(id - 1);
	}

	public void deleteAllCompletedItems() {
		for (Iterator<TodoItem> iterator = todoItemList.iterator(); iterator.hasNext();) {
			TodoItem ti = iterator.next();
			if (ti.getStatus())
				iterator.remove();
		}
		System.out.println("Completed items removed");
	}

	public void completeItem() {
		int id = getIDFromUser("Enter ID of item you have completed");
		todoItemList.get(id - 1).setDone();
	}

	public void uncompleteItem() {
		int id = getIDFromUser("Enter ID of item you have not completed:");
		todoItemList.get(id - 1).setUnDone();
	}

	public void findItem() {
		ArrayList<TodoItem> searchArray = new ArrayList<TodoItem>();
		String searchString = getStringFromUser("What do you want to search for:");

		for (TodoItem ti : todoItemList)
			if (ti.getDescription().contains(searchString))
				searchArray.add(ti);

		printItemList(searchArray);
	}
	
	
}
