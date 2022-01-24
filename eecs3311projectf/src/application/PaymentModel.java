package application;

import java.time.LocalDate;
import java.util.Date;

public class PaymentModel {

	private String cardType;
	private int paymentID;
	private String cardNumber;
	private String billingAddress;
	private String custName;
	private LocalDate expDate;
	private int custID;
	
	public PaymentModel(){
		
	}
	public PaymentModel(int custId, String custName, String pay_cardType, int pay_ID, String cardNumber, String billngAddress, LocalDate card_expDate) {
		this.setCustID(custId);
		this.setCustName(custName);
		this.setBillingAddress(billngAddress);
		this.setCardType(pay_cardType);
		this.setCardNumber(cardNumber);
		this.setExpDate(card_expDate);
		this.setPaymentID(pay_ID);
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public LocalDate getExpDate() {
		return expDate;
	}

	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}

}
