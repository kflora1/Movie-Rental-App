package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MovieModel {

	private String title;
	private int id;
	private String genre;
	private String description;
	private String availability;
	private Double price;
		
	public MovieModel() {
		
	}
	public MovieModel(String movie_title, int movie_id, String movie_genre, String movie_desc, String movie_avail, double movie_price) {
		this.setTitle(movie_title);
		this.setId(movie_id);
		this.setGenre(movie_genre);
		this.setDescription(movie_desc);
		this.setAvailability(movie_avail);
	}
	public MovieModel(MovieModel movie) {
		this(movie.title, movie.id, movie.genre, movie.description, movie.availability, movie.price);
	}
	@Override
	public String toString() {
		return "Movie [Title= " + title + 
				", Genre= " + genre + 
				", Description= "+ description + 
				", Availability= " + availability +
				", price= " + price +
				", id= " + id+ "]";
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

}
