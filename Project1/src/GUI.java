import java.util.Iterator;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
	
	private TodoItem selectedItem;
	private final TableView<TodoItem> table = new TableView<TodoItem>();
	private ObservableList<TodoItem> todoItems;
	private Button addButton, editButton, doneButton, undoneButton, deleteButton, deleteAllDoneButton;

	public void start(Stage myStage) {

		myStage.setTitle("Todo list");
		initButtons();

		todoItems = FXCollections.observableArrayList(new TodoItem("äta lunch", 3), new TodoItem("äta middag", 5),
				new TodoItem("sova", 1), new TodoItem("städa", 10));

		table.setEditable(true);
		createTableColumns();
		table.setItems(todoItems);

		MultipleSelectionModel<TodoItem> tableViewSelModel = table.getSelectionModel();
		tableViewSelModel.selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
			public void changed(ObservableValue<? extends TodoItem> changed, TodoItem oldVal, TodoItem newVal) {
				selectedItem = newVal;
			}
		});

		VBox buttonSelection = new VBox();
		buttonSelection.setSpacing(10);
		buttonSelection.setAlignment(Pos.TOP_RIGHT);
		buttonSelection.getChildren().addAll(addButton, editButton, deleteButton, deleteAllDoneButton, doneButton, undoneButton);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10,10,10,10));
		borderPane.setCenter(table);
		borderPane.setRight(buttonSelection);
		BorderPane.setMargin(buttonSelection, new Insets(10,10,10,10));

		Scene myScene = new Scene(borderPane, 500, 300);
		myStage.setScene(myScene);
		myStage.show();
	}

	private void initButtons() {
		addButton = new Button("Add");
		editButton = new Button("Edit");
		doneButton = new Button("Done");
		undoneButton = new Button("Undone");
		deleteButton = new Button("Delete");
		deleteAllDoneButton = new Button("Delete done");

		addButton.setPrefWidth(100);
		editButton.setPrefWidth(100);
		doneButton.setPrefWidth(100);
		undoneButton.setPrefWidth(100);
		deleteButton.setPrefWidth(100);
		deleteAllDoneButton.setPrefWidth(100);

		addButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				addItem();
			}
		});
		editButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				editItem();
			}
		});
        doneButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				if (selectedItem != null)
					selectedItem.setDone();
				table.refresh();
			}
		});
		undoneButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				if (selectedItem != null)
					selectedItem.setUnDone();
				table.refresh();
			}
		});
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				todoItems.remove(selectedItem);
			}
		});
		deleteAllDoneButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				deleteAllDone();
			}
		});
	}

	private void createTableColumns() {
		TableColumn<TodoItem, String> descriptionColumn = new TableColumn<TodoItem, String>("Description");
		descriptionColumn.setMinWidth(100);
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<TodoItem, String> dueDateColumn = new TableColumn<TodoItem, String>("Due date");
		dueDateColumn.setMinWidth(90);
		dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateDue"));

		TableColumn<TodoItem, String> dateAddedColumn = new TableColumn<TodoItem, String>("Date added");
		dateAddedColumn.setMinWidth(90);
		dateAddedColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));

		TableColumn<TodoItem, String> completedColumn = new TableColumn<TodoItem, String>("Done");
		completedColumn.setMinWidth(80);
		completedColumn.setCellValueFactory(new PropertyValueFactory<>("statusString"));

		table.getColumns().addAll(descriptionColumn, dueDateColumn, dateAddedColumn, completedColumn);
	}

	private void addItem() {
		ItemControllerWindow wc = new ItemControllerWindow();
		wc.showStage("Add item", "");
		if (wc.getComplete())
			todoItems.add(new TodoItem(wc.getDescription(), wc.getDays()));
	}

	private void editItem() {
		ItemControllerWindow wc = new ItemControllerWindow();
		wc.showStage("Edit item", selectedItem.getDescription());
		if (wc.getComplete()) {
			selectedItem.setDescription(wc.getDescription());
			selectedItem.setDates(wc.getDays(), false);
		}
		table.refresh();
	}

	private void deleteAllDone() {
		for (Iterator<TodoItem> iterator = todoItems.iterator(); iterator.hasNext();) {
			TodoItem ti = iterator.next();
			if (ti.getStatus())
				iterator.remove();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
