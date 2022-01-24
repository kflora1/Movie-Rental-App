package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegisterNewCController {

	@FXML
	private TextField username;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField confirmPassword;	
	@FXML
	private Label messageLabel;
	@FXML
	private Hyperlink loginLink;
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	CustomerDB database;
	
	public RegisterNewCController() {
		System.out.println("This is RegisterNewCController");
	}
	
	@FXML private void register(ActionEvent event) throws Exception {
		if (username.getText().toString().equals("") || password.getText().toString().equals("") || confirmPassword.getText().toString().equals("") || email.getText().toString().equals("")) {
			messageLabel.setText("Please fill in all account details");
			messageLabel.setTextFill(Color.RED);
			loginLink.setText("or go back to Login");
		}
		else {
			boolean result = authenticateNewAccount(username.getText().toString(),
													password.getText().toString(),
													confirmPassword.getText().toString(),
													email.getText().toString());
			if (result) {
				username.clear(); email.clear(); password.clear(); confirmPassword.clear();
				messageLabel.setText("Account Created!");
				messageLabel.setTextFill(Color.LIMEGREEN);
				loginLink.setText("Back to Login");
			}
			else {
				username.clear(); email.clear(); password.clear(); confirmPassword.clear();
				messageLabel.setText("Password not the same, Try again.");
				messageLabel.setTextFill(Color.RED);
				loginLink.setText("Back to Login");
			}
		}
	}
	
	public boolean authenticateNewAccount(String user, String pw1, String pw2, String email) throws Exception {
		if (pw1.equals(pw2)) {
			database = new CustomerDB();
			CustomerModel newCustomer = new CustomerModel(user, database.generateNextCustID(), email, pw1,"",database.generateNextPayID(), database.generateNextOrderID());
			database.addC(newCustomer);
			database.update();
			
			return true;
		}
		else {
			return false;
		}
	}
	
	@FXML private void backToLoginPage(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainVC.fxml"));
		root = loader.load();
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
