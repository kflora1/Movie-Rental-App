package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewMovieController{

	MovieDB movieDB;
	LocalDate startDate;
	LocalDate dueDate;
	Period rentPeriod;
	OrderModel custOrder;
	ArrayList<OrderModel> orderList;
	String movieName;
	Double moviePrice;
	
	private int custID;
	private int orderID;
	private Stage stage;
	private Scene scene;
	private Parent root;
	private MovieModel movieTitleOrdered;
	private int selectmvID;

	@FXML
	private Button custAccountBtn;	
	@FXML
	private Button addtoCartBtn;
	@FXML
	private Button searchHomePageBtn;
	@FXML
	private Button backBtn;
	@FXML
	private Button allMoviesBtn;
	@FXML
	private Button ordersBtn;
	@FXML
	private Button cartBtn;
	@FXML
	private Label showPrice;
	@FXML
	private Label showAvailability;
	@FXML
	private Label orderResultMsg;
	@FXML
	private Label showMovieDesc;
	@FXML
	private Label showMovieTitle;
	@FXML
	private Label showMovieGenre;
	@FXML
	private DatePicker dueDatePicker;
	@FXML
	private Label rentStartDate;
	@FXML
	private Label rentDueDate;
	
	public ViewMovieController() {
		System.out.println("This is ViewMovieController");
		orderList = new ArrayList<OrderModel>();
	}
	
	@FXML
	public void chooseDates(ActionEvent event) throws Exception {
		startDate = LocalDate.now(); 
		rentStartDate.setText(""+startDate);		
		
		dueDate = dueDatePicker.getValue();
		
		rentPeriod = Period.between(startDate, dueDate);
		rentPeriod.getDays();
		
		rentDueDate.setText("This movie is due : " + dueDate + ".\n"
				+ "Renting movie for : " + rentPeriod.getDays() + " days.");
		
		movieDB = new MovieDB();
		for (MovieModel m : movieDB.getMovieList()) {
			if (m.getId() == selectmvID) {
				movieName = m.getTitle();
				moviePrice = m.getPrice();
				collectMovieOrderInfo(m.getTitle(), selectmvID, dueDate, startDate, rentPeriod);
				
				System.out.println("after collectMvinfo");
			}
		}
	}
	
	public void saveCustID(int customerId) {
		custID = customerId;
	}
	
	public void displayMovieSelected(int selectedMovieID) throws Exception {
		movieDB = new MovieDB();
		for (MovieModel m : movieDB.getMovieList()) {
			if (m.getId() == selectedMovieID) {
				selectmvID= m.getId();
				showMovieTitle.setText(m.getTitle());
				showMovieGenre.setText(m.getGenre());
				showMovieDesc.setText(m.getDescription());
				showMovieDesc.setWrapText(true);
				showAvailability.setText(m.getAvailability());
				showPrice.setText("$" + String.valueOf(m.getPrice()));
			}
		}
	}
	
	public void collectMovieOrderInfo(String mtitle, int mID, LocalDate dueDate, LocalDate startDate, Period rentPeriod) throws Exception {
		CustomerDB custDB = new CustomerDB();
		MovieDB moviesDb = new MovieDB();
		EventHandler<Event> addtoCart = new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				for (CustomerModel c: custDB.getCustomerList()) {
					
					if (custID == c.getId()) {					
						for (MovieModel mov : moviesDb.getMovieList()) {
							
							if (mID == mov.getId()) {
								mov.setAvailability("rented");
								try {
									moviesDb.update();
								} catch (Exception e) {
									e.printStackTrace();
								}
								movieName = mov.getTitle();
							}
							
						}
						custOrder = new OrderModel(custID, "in cart", c.getOrderID(), mID, startDate, dueDate, null, "not shipped", -1);
						orderID = c.getOrderID();
						c.addToOrderList(custOrder);
						
						orderList.add(custOrder);
					}
				}
				
				try {
					navigateToOrderingPg(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		};
		
		addtoCartBtn.setOnMouseClicked(addtoCart);
		
	}
	
	private void navigateToOrderingPg(Event event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCart.fxml"));
		root = loader.load();
		System.out.println(movieName);
		System.out.println(moviePrice);
		
		CartController orderMovieController = loader.getController();
		orderMovieController.saveCartItems(movieName, moviePrice); //pass value of movie name and movie price
		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void backToPrevScene(String fxmlURL,Stage stage) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(fxmlURL));
        Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void navigateToCustAcc(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerAccount.fxml"));
		root = loader.load();
		
		CustAccController custAccController = loader.getController();
		try {
			custAccController.displayCustName(custID); //display customer Name 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	

	
	
	
}
