package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomePageController implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Button custAccountBtn;
	@FXML
	private Button searchHomePageBtn;
	@FXML
	private Button allMoviesBtn;
	@FXML
	private Button ordersBtn;
	@FXML
	private Button cartBtn;
	@FXML
	private ChoiceBox<String> genreDropdown;
	@FXML
	private TableView<MovieModel> movieSearchResults;
	@FXML
	private TableColumn<MovieModel, String> mvTitle;
	@FXML
	private TableColumn<MovieModel, String> mvAvailable;
	@FXML
	private TableColumn<MovieModel, String> mvDesc;
	@FXML
	private TableColumn<MovieModel, String> mvGenre;
	@FXML
	private TableColumn<MovieModel, String> mvPrice;
	@FXML
	private TextField enterSearch;
	
	MovieDB movieDB;
	int customerId;
	
	public HomePageController() {
		System.out.println("This HomePageController");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//initialize a controller after its root element has been processed
		try {
			movieDB = new MovieDB();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Set<String> genreSet = new HashSet<String>();
		
		try {
						
			genreSet.addAll(movieDB.getMovieGenres());
			
			ObservableList<String> eList = FXCollections.observableArrayList(genreSet);
			if (genreDropdown.getItems().isEmpty()) {
				genreDropdown.setItems(eList);
			}
			
			ObservableList<MovieModel> movDataList = FXCollections.observableArrayList(movieDB.filterMovies("movie", ""));
			
			mvTitle.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("title"));
			mvGenre.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("genre"));
			mvDesc.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("description"));
			mvAvailable.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("availability"));
			mvPrice.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("price"));

			movieSearchResults.setItems(movDataList);//show all movies on home page
			
			genreDropdown.setOnAction(event -> { //filter by genre
				try {
					filterByGenre(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void viewMovie(MouseEvent event) throws IOException{
	    if (event.getClickCount() == 1) //Checking click
	    {
	    	int selectedMovieID = movieSearchResults.getSelectionModel().getSelectedItem().getId();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMovie.fxml"));
			root = loader.load();
			
			ViewMovieController viewMovieController = loader.getController();
			try {
				viewMovieController.displayMovieSelected(selectedMovieID); //display selected movie 
				viewMovieController.saveCustID(customerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
	}
	
	//search bar results
	@FXML
	private void onEnter(ActionEvent event) throws Exception {
		movieSearchResults.getItems().clear();
		movieDB = new MovieDB();
		
		String filter = enterSearch.getText().toString();
		//Arraylist of filtered movies from movieDB is added to the observableList for it to be shown on the GUI
		ObservableList<MovieModel> movDatasearch = FXCollections.observableArrayList(movieDB.filterMovies("movie", filter));
		
		mvTitle.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("title"));
		mvGenre.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("genre"));
		mvDesc.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("description"));
		mvAvailable.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("availability"));
		mvPrice.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("price"));

		movieSearchResults.setItems(movDatasearch);

	}

	//filter movies by genre
	@FXML
	private void filterByGenre(ActionEvent event) throws Exception {
		enterSearch.clear();
		movieSearchResults.getItems().clear();
		
		String genre = genreDropdown.getValue().toString();		
		
		//Arraylist of filtered movies from movieDB is added to the observableList for it to be shown on the GUI
		ObservableList<MovieModel> movDataList = FXCollections.observableArrayList(movieDB.filterMovies("genre", genre));
		
		mvTitle.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("title"));
		mvGenre.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("genre"));
		mvDesc.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("description"));
		mvAvailable.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("availability"));
		mvPrice.setCellValueFactory(new PropertyValueFactory<MovieModel, String>("price"));
		
		movieSearchResults.setItems(movDataList);
	}
	
	public void saveCustID(int custID) {
		customerId = custID;
	}
	
	@FXML
	public void navigateToCustAcc(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerAccount.fxml"));
		root = loader.load();
		
		CustAccController custAccController = loader.getController();
		try {
			custAccController.displayCustName(customerId); //display customer Name 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML public void navigateToHomePage(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
		root = loader.load();
		
		HomePageController homePage = loader.getController();
		homePage.saveCustID(customerId); //pass value of customer ID after login
		
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML public void navigateToCreateOrderPg(ActionEvent event) throws IOException {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
//		root = loader.load();
//		ViewMovieController viewMovieController = loader.getController();
//		viewMovieController.saveCustID(customerId);
//		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//		scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
	}

}
