package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainSceneController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private int loginAuthenticate;
	
	@FXML
	private Button loginButton;
	@FXML
	private PasswordField password;
	@FXML
	private TextField username;
	@FXML
	private Label loginLabel;
	private int custLoggedinID;
	
	public MainSceneController() {
		System.out.println("This is MainSceneController");
	}
	
	@FXML private void handleCustLogin(ActionEvent event) throws IOException{
		try {
			loginAuthenticate = customerLogin(username.getText().toString(), password.getText().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (loginAuthenticate == -1) { //user did not type anything in the fields
			loginLabel.setText("Please enter your login details.");
		}
		else if (loginAuthenticate == 0) { //wrong login credentials
			username.clear(); password.clear();
			loginLabel.setText("Invalid username and password");
			loginLabel.setTextFill(Color.RED);
		}
		else {
			navigateToHomePage(event);//correct credentials
		}
		
	}
	
	public int customerLogin(String user, String pw) throws Exception{
		CustomerDB customerDB = new CustomerDB();
		int result;				
		
		if (!user.isEmpty() && !pw.isEmpty()) { //user input aren't empty
			for(CustomerModel u: customerDB.getCustomerList()){
				if (u.getName().equals(user) && u.getPassword().equals(pw)) {					
					custLoggedinID = u.getId();
					return custLoggedinID;
				}
			}
			result = 0; //user put in the wrong password/username
			return result;
		}
		else { //user input is empty
			result = -1;
			return result;
		}
	}
	
	@FXML public void navigateToHomePage(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
		root = loader.load();
		
		HomePageController homePage = loader.getController();
		homePage.saveCustID(custLoggedinID); //pass value of customer ID after login
		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML public void navigateToRegisterPage(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterNewCustVC.fxml"));
		root = loader.load();		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}

}
