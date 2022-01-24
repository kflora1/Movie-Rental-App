package application;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class OrderDB {

	private ArrayList<OrderModel> orderList = new ArrayList<OrderModel>();
	public String path = "";
	public DateTimeFormatter DateStringFrmt = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
	public OrderDB() throws Exception {
		load();
	}
	
	/**
	 * Reads customer database csv file
	 * @param path file path of the csv file
	 **/
	public void load() throws Exception{
		CsvReader reader = new CsvReader(path); 
		reader.readHeaders();
		
		while(reader.readRecord()){ 
			OrderModel order = new OrderModel();

			order.setOrderID(Integer.valueOf(reader.get("order id")));
			order.setCustomerID(Integer.valueOf(reader.get("customer id")));
			order.setMovieID(Integer.valueOf(reader.get("movie id")));
			LocalDate orderDate = LocalDate.parse(reader.get("order date"), DateStringFrmt);
			LocalDate dueDate = LocalDate.parse(reader.get("due date"), DateStringFrmt);
			LocalDate shippingDate = LocalDate.parse(reader.get("shipping date"), DateStringFrmt);
			order.setDateOrdered(orderDate);
			order.setDateDue(dueDate);
			order.setShippedDate(shippingDate);
			order.setPaymentID(Integer.valueOf(reader.get("payment id")));
			order.setStatus(reader.get("order status"));
			order.setPrice(Double.valueOf(reader.get("movie order price")));
			
			orderList.add(order);
		}
		
	}
	
	/**
	 * Writes to the csv file the new customer that was added
	 * @param path file path of the csv file
	 **/
	public void update() throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				
				csvOutput.write("order id");
				csvOutput.write("customer id");
		    	csvOutput.write("movie id");
				csvOutput.write("order date");
				csvOutput.write("due date");
				csvOutput.write("shipping date");
				csvOutput.write("payment id");
				csvOutput.write("order status");
				csvOutput.write("movie order price");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(OrderModel u: orderList){
					csvOutput.write(String.valueOf(u.getOrderID()));
					csvOutput.write(String.valueOf(u.getCustomerID()));
					csvOutput.write(String.valueOf(u.getMovieID()));
					csvOutput.write(String.valueOf(u.getDateOrdered()));
					csvOutput.write(String.valueOf(u.getDateDue()));
					csvOutput.write(String.valueOf(u.getShippedDate()));
					csvOutput.write(String.valueOf(u.getPaymentID()));
					csvOutput.write(u.getStatus());
					csvOutput.write(String.valueOf(u.getPrice()));					
					
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	public void createNewCSV() throws Exception{
		
	}
	
	public void addO(OrderModel order) {
		orderList.add(order);
	}
	public ArrayList<OrderModel> getOrderList() {
		return orderList;
	}

	public void setCustomerList(ArrayList<OrderModel> orderList) {
		this.orderList = orderList;
	}

}
