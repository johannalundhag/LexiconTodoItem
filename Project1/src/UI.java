import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class UI {

	private String[] options;
	OperationsExecuter operationsExecuter;

	private boolean doAgain;

	public UI() {
		options = new String[] { "add item", "edit item", "remove item", "remove all completed items", "list items",
				"complete item", "uncomplete item", "find item", "save (not implemented)", "exit" };
		operationsExecuter = new OperationsExecuter();

		operationsExecuter.createItems();
		doAgain = true;
	}
	

	public boolean getInput() {
		int noOfOperation = operationsExecuter.getNumberFromUser("\nWhat do you want to do? To list options type 0.\nPlease enter number:");
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
			operationsExecuter.addItem();
			break;
		case 2:						//edit item
			operationsExecuter.editItem();
			break;
		case 3:						//remove item
			operationsExecuter.deleteItem();
			break;
		case 4:						//remove all completed items
			operationsExecuter.deleteAllCompletedItems();
			break;
		case 5:						//list items
			operationsExecuter.printItemList(operationsExecuter.getTodoItemList());
			break;
		case 6:						//complete item
			operationsExecuter.completeItem();
			break;
		case 7:						//uncomplete item
			operationsExecuter.uncompleteItem();
			break;
		case 8:						//find item
			operationsExecuter.findItem();
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
}
