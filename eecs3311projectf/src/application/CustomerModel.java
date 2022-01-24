package application;

import java.util.ArrayList;

public class CustomerModel {

	private String name;
	private int id;
	private String email;
	private String password;
	private String address;
	private int payID;
	private int orderID;
	ArrayList<OrderModel> custOrderList = new ArrayList<OrderModel>();
	
	public CustomerModel(String cust_name, int cust_id, String cust_email, String cust_pw, String cust_address, int cust_payID, int cust_orderID) {
		this.name = cust_name;
		this.id = cust_id;
		this.email = cust_email;
		this.password = cust_pw;
		this.address = cust_address;
		this.payID = cust_payID;
		this.orderID = cust_orderID;
	}

	public CustomerModel() {
		
	}

//	public CustomerModel(CustomerModel c) {
//		this(c.getName(), c.getId(), c.getEmail(), c.getPassword(), c.getAddress());
//	}
	
	@Override
	public String toString() {
		return "User [name= " + name + 
				", id= " + id + 
				", email= " + email + 
				", password= " + password + 
				", address= " + address + 
				", pay id= " + payID + 
				", order id=" +orderID + 
				", OrderList = " + custOrderList + "]";
	}
	
	public void addToOrderList(OrderModel order) {
		this.custOrderList.add(order);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getOrderID() {
		return orderID;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getPayID() {
		return payID;
	}
	public void setPayID(int payID) {
		this.payID = payID;
	}
	public ArrayList<OrderModel> getCustOrderList() {
		return custOrderList;
	}
	public void setCustOrderList(ArrayList<OrderModel> custOrderList) {
		this.custOrderList = custOrderList;
	}
	



}
