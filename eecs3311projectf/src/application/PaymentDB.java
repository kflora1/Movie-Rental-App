package application;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class PaymentDB {

	public ArrayList<PaymentModel> paymentlist = new ArrayList<PaymentModel>();
	public DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
	public String path = "C:\\Users\\fyk20\\Downloads\\customerDB.csv";
	
	public PaymentDB() throws Exception {
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
			PaymentModel p = new PaymentModel();
			p.setCustID(Integer.valueOf(reader.get("customer id")));
			p.setCustName(reader.get("customer name"));
			p.setPaymentID(Integer.valueOf(reader.get("payment id")));
			p.setCardType(reader.get("card type"));
			p.setCardNumber(reader.get("card number"));
			p.setBillingAddress(reader.get("address"));
			LocalDate expDate = LocalDate.parse(reader.get("expiry date"), dateTimeFormatter);
			p.setExpDate(expDate);
			
			paymentlist.add(p);
		}
		
	}
	
	/**
	 * Writes to the csv file the new customer that was added
	 * @param path file path of the csv file
	 **/
	public void update() throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');

				csvOutput.write("customer id");
				csvOutput.write("customer name");
		    	csvOutput.write("payment id");
				csvOutput.write("card type");
				csvOutput.write("card number");
				csvOutput.write("address");
				csvOutput.write("expiry date");
				
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(PaymentModel u: paymentlist){
					csvOutput.write(String.valueOf(u.getCustID()));
					csvOutput.write(u.getCustName());
					csvOutput.write(String.valueOf(u.getPaymentID()));
					csvOutput.write(u.getCardType());
					csvOutput.write(u.getCardNumber());
					csvOutput.write(u.getBillingAddress());
					csvOutput.write(String.valueOf(u.getExpDate()));
					
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	public void addP(PaymentModel payment) {
		paymentlist.add(payment);
	}
 
}
