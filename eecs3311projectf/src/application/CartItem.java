package application;

public class CartItem {

	private String movieName;
	private Double moviePrice;
	
	public CartItem() {
		
	}
	
	public CartItem(String movName, Double movPrice) {
		this.setMovieName(movName);
		this.setMoviePrice(movPrice);
	}
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Double getMoviePrice() {
		return moviePrice;
	}
	public void setMoviePrice(Double moviePrice) {
		this.moviePrice = moviePrice;
	}

}
