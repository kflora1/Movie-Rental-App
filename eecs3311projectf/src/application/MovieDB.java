package application;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MovieDB {

	private ArrayList<MovieModel> movieList = new ArrayList<MovieModel>();
	public String path = System.getProperty("user.dir") + "\\movieDB\\movieDB_2.csv";
	
	public MovieDB() throws Exception {
		load();
	}
	/**
	 * Reads movie database csv file
	 * @param path file path of the csv file
	 **/
	public void load() throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		movieList.clear();
		while(reader.readRecord()){
			MovieModel movie = new MovieModel();
			//title, genre, desc, availability, id
			movie.setTitle(reader.get("Title"));
			movie.setGenre(reader.get("Genre").toLowerCase());
			movie.setDescription(reader.get("Movie Description"));
			movie.setAvailability(reader.get("Availability").toLowerCase());
			movie.setPrice(Double.valueOf(reader.get("Rent Price")));
			movie.setId(Integer.valueOf(reader.get("id")));
			
			movieList.add(movie);					
		}
	}
	
	/**
	 * Writes to the csv file
	 * @param path file path of the csv file
	 **/
	public void update() throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				//name,id,email,password
				csvOutput.write("Title");
				csvOutput.write("Genre");
		    	csvOutput.write("Movie Description");
				csvOutput.write("Availability");
				csvOutput.write("Rent Price");
				csvOutput.write("id");
				
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(MovieModel m: movieList){
					csvOutput.write(m.getTitle());
					csvOutput.write(m.getGenre());
					csvOutput.write(m.getDescription());
					csvOutput.write(m.getAvailability());
					csvOutput.write(String.valueOf(m.getPrice()));
					csvOutput.write(String.valueOf(m.getId()));
					
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public Set<String> getMovieGenres() {
		Set<String> genreList = new HashSet<String>();
		for (MovieModel movie : movieList) {
			genreList.add(movie.getGenre());
		}
		return genreList;
	}
	
	public ArrayList<MovieModel> filterMovies(String filterOption, String filterValue) throws Exception {
		ArrayList<MovieModel> moviesFiltered = new ArrayList<MovieModel>();
		//filterOption = title or genre
		//if option = genre then filterValue = genre
		//if option = title then filterValue = title
		
		if (filterOption == "genre") { //filter by genre
			
			for (MovieModel movie : movieList) {
				if (movie.getGenre().equals(filterValue)&& !movie.getAvailability().equals("rented")) {
					moviesFiltered.add(movie);
				}
			}
		}
		else {//filter by search title
			
			for (MovieModel movie : movieList) { 
				if (movie.getTitle().toLowerCase().contains(filterValue.toLowerCase()) && !movie.getAvailability().equals("rented")) {
					moviesFiltered.add(movie);
				}
			}
		}
		return moviesFiltered;
		
	}
	
	public ArrayList<MovieModel> getMovieList(){
		return movieList;
	}
	
	public void setMovieList(ArrayList<MovieModel> movieList){
		this.movieList = movieList;
	}

}
