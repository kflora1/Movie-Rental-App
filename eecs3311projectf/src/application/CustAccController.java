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
import javafx.stage.Stage;

public class CustAccController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private int custLoggedInID;
	OrderModel custOrder;

	@FXML
	public Label welcomeUser;
	@FXML
	private Button custOrdersBtn;
	@FXML
	private Button accSettingsBtn;
	@FXML
	private Button paymentSettingsBtn;
	
	public CustAccController() {
		System.out.println("This is CustAccController");
	}
	
	public void displayCustName(int custId) throws Exception {
		CustomerDB customerDB = new CustomerDB();
		for (CustomerModel customer : customerDB.getCustomerList()) {
			if (customer.getId() == custId) {
				custLoggedInID = custId;
				welcomeUser.setText("Welcome " + customer.getName());
			}
		}
	}
		
	@FXML private void navigateToAccSettingsPg(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountSettings.fxml"));
		root = loader.load();
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML private void navigateToCustOrdersPg(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustOrders.fxml"));
		root = loader.load();
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML private void navigateToPaymentSettingsPg(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentSettings.fxml"));
		root = loader.load();
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML public void navigateToHomePage(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
		root = loader.load();
		
		HomePageController homePage = loader.getController();
		homePage.saveCustID(custLoggedInID);
		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public int getCustLoggedInID() {
		return custLoggedInID;
	}

	public void setCustLoggedInID(int custLoggedInID) {
		this.custLoggedInID = custLoggedInID;
	}

}
