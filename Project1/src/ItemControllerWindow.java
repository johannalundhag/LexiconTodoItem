import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class ItemControllerWindow {
	private String description;
	private int days;
	private boolean complete;

	void showStage(String message, String oldDescription) {
		
		complete = false;
		Stage stage = new Stage();
		stage.setTitle(message);
		stage.initModality(Modality.APPLICATION_MODAL);

		VBox vBox = new VBox();
		HBox hBox = new HBox();
		vBox.setPadding(new Insets(10, 10, 10, 10));
		vBox.setSpacing(5);
		vBox.setMinWidth(250);
		hBox.setSpacing(5);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);

		Scene scene = new Scene(vBox);
		Label lableDescription = new Label("Description:");
		TextField tfDescription = new TextField();
		tfDescription.setText(oldDescription);

		Label lableDays = new Label("Days to done:");
		TextField tfDays = new TextField();

		Button addButton = new Button("Submit");
		Button cancelButton = new Button("Cancel");

		addButton.setOnAction(e -> {
			description = tfDescription.getText();
			try {
				days = Integer.parseInt(tfDays.getText());
				complete = true;
			} catch (NumberFormatException ne) {
				System.out.println("Not a number");
			}
			stage.close();
		});
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				stage.close();
			}
		});

		hBox.getChildren().addAll(addButton, cancelButton);
		vBox.getChildren().addAll(lableDescription, tfDescription, lableDays, tfDays, hBox);
		stage.setScene(scene);
		stage.showAndWait();
	}

	public int getDays() {
		return days;
	}

	public String getDescription() {
		return description;
	}
	
	public boolean getComplete(){
		return complete;
	}
	
}