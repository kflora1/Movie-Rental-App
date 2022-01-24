package application;

import java.time.LocalDate;
import java.util.Date;

public class OrderModel {

	private String status;
	private int orderID;
	private int movieID;
	private LocalDate dateDue;
	private LocalDate dateOrdered;
	private LocalDate shippedDate;
	private double price;
	private String shippingStatus;
	private int customerID;
	private int paymentID;
	
	public OrderModel() {
		
	}
	public OrderModel(int customerID,String order_status, int order_id, int order_movie, LocalDate order_date, LocalDate order_dueDate, LocalDate order_shippedDate, String order_shippingStatus, int payment_id) {
		this.setCustomerID(customerID);
		this.setStatus(order_status);
		this.setOrderID(order_id);
		this.setMovieID(order_movie);
		this.setShippingStatus(order_shippingStatus);
		this.setPaymentID(payment_id);
		this.setDateOrdered(order_date);
		this.setDateDue(order_dueDate);
		this.setShippedDate(order_shippedDate);
	}
	@Override
	public String toString() {
		return "Order [Order ID=" + orderID + ",  Order Status= " + status +
						", Movie ID= " + movieID + 
						", Order Date= " + dateOrdered + 
						", Due Date= " + dateDue + 
						", Date Shipped= " + shippedDate + 
						", Shipping Status= "+ shippingStatus +"]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int id) {
		this.orderID = id;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shipped) {
		this.shippingStatus = shipped;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public LocalDate getDateDue() {
		return dateDue;
	}
	public void setDateDue(LocalDate dateDue) {
		this.dateDue = dateDue;
	}
	public LocalDate getDateOrdered() {
		return dateOrdered;
	}
	public void setDateOrdered(LocalDate dateOrdered) {
		this.dateOrdered = dateOrdered;
	}
	public LocalDate getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}

}
