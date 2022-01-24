package application;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;



public class CustomerDB {

	private ArrayList<CustomerModel> customerList = new ArrayList<CustomerModel>();
	
	public String path = System.getProperty("user.dir") + "\\customerDB\\customerDB.csv";
	
	public CustomerDB() throws Exception {
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
			CustomerModel customer = new CustomerModel();
			//ArrayList<OrderModel> orderList = new ArrayList<OrderModel>();
			//name,id,email,password,address
			customer.setName(reader.get("name"));
			customer.setId(Integer.valueOf(reader.get("id")));
			customer.setEmail(reader.get("email"));
			customer.setPassword(reader.get("password"));
			customer.setAddress(reader.get("address"));
			customer.setPayID(Integer.valueOf(reader.get("payment id")));
			customer.setOrderID(Integer.valueOf(reader.get("order id")));
			
			customerList.add(customer);
		}
		
	}
	
	/**
	 * Writes to the csv file the new customer that was added
	 * @param path file path of the csv file
	 **/
	public void update() throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');
				//name,id,email,password
				csvOutput.write("name");
				csvOutput.write("id");
		    	csvOutput.write("email");
				csvOutput.write("password");
				csvOutput.write("address");
				csvOutput.write("payment id");
				csvOutput.write("order id");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(CustomerModel u: customerList){
					csvOutput.write(u.getName());
					csvOutput.write(String.valueOf(u.getId()));
					csvOutput.write(u.getEmail());
					csvOutput.write(u.getPassword());
					csvOutput.write(u.getAddress());
					csvOutput.write(String.valueOf(u.getPayID()));
					csvOutput.write(String.valueOf(u.getOrderID()));
					
					
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	public void addC(CustomerModel customer) {
		customerList.add(customer);
	}
	public int generateNextCustID() {
		return customerList.size() + 1;
	}
	public int generateNextPayID() {
		return customerList.size() + (110 * 15);
	}
	public int generateNextOrderID() {
		return customerList.size() + (5 * 12);
	}
	
	public ArrayList<CustomerModel> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(ArrayList<CustomerModel> customerList) {
		this.customerList = customerList;
	}

}
