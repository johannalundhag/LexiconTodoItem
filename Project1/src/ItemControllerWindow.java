import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class ItemControllerWindow {
	private String description;
	private int days;

	void showStage(String message, String oldDescription) {
		Stage stage = new Stage();
		stage.setTitle(message);
		stage.initModality(Modality.APPLICATION_MODAL);

		VBox root = new VBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setSpacing(5);
		root.setMinWidth(250);

		Scene scene = new Scene(root);
		Label lableDescription = new Label("Description:");
		TextField tfDescription = new TextField();
		tfDescription.setText(oldDescription);

		Label lableDays = new Label("Days to done:");
		TextField tfDays = new TextField();

		Button add = new Button("Submit");

		add.setOnAction(e -> {
			description = tfDescription.getText();
			try {
				days = Integer.parseInt(tfDays.getText());
			} catch (NumberFormatException ne) {
				System.out.println("Not a number");
			}
			stage.close();
		});

		root.getChildren().addAll(lableDescription, tfDescription, lableDays, tfDays, add);
		stage.setScene(scene);
		stage.showAndWait();
	}

	int getDays() {
		return days;
	}

	String getDescription() {
		return description;
	}
}