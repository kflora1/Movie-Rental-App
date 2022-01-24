package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController{

	private String mName;
	private Double mPrice;
	private Stage stage;
	private Scene scene;
	private Parent root;
	MovieDB movieDB;
	CustomerDB customerDB;
	ArrayList<OrderModel> orders;
	ArrayList<CartItem> cartItems;
	CartItem cartItem;
	
	@FXML
	private TableView<CartItem> ordersTableView;
	@FXML
	private TableColumn<CartItem, String> movie;
	@FXML
	private TableColumn<CartItem, Double> price;
	
	public CartController() {
		System.out.println("This is CartController");
	}
	
	public void saveCartItems(String movieName, Double moviePrice) {
		System.out.println("This is saveCartItems");
		mName = movieName;
		mPrice = moviePrice;
		
		cartItems = new ArrayList<CartItem>();
		
		CartItem cartItem1 = new CartItem(mName , mPrice);
				
		cartItems.add(cartItem1);
				
		ObservableList<CartItem> cartList = FXCollections.observableArrayList(cartItems);
		
		movie.setCellValueFactory(new PropertyValueFactory<CartItem, String>("movieName"));
		price.setCellValueFactory(new PropertyValueFactory<CartItem, Double>("moviePrice"));
		
		ordersTableView.setItems(cartList);
	}

	

	

}
