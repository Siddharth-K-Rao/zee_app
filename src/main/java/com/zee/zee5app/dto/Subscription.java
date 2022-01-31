package com.zee.zee5app.dto;

import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subscription implements Comparable<Subscription> {
	
	public Subscription(String subId, String dateOfPurchase, String expiryDate, String paymentMode, String status, String type, String regId, Boolean autoRenewal, float amount) throws InvalidAmountException, InvalidIdLengthException {
		
		super();
		this.setAmount(amount);
		this.setAutoRenewal(autoRenewal);
		this.setSubId(subId);
		this.setDateOfPurchase(dateOfPurchase);
		this.setExpiryDate(expiryDate);
		this.setPaymentMode(paymentMode);
		this.setStatus(status);
		this.setType(type);
		this.setRegId(regId);
		
	}
	
	@Setter(value = AccessLevel.NONE)
	private String subId;
	
	private String dateOfPurchase;
	private String expiryDate;
	private String paymentMode;
	private String status;
	private String type;
	private boolean autoRenewal;
	private String regId;
	
	@Setter(value = AccessLevel.NONE)
	private float amount;

	
	public void setSubId(String subId) throws InvalidIdLengthException {
		this.subId = subId;
	}
	
	
	public void setAmount(float amount) throws InvalidAmountException {
		if(amount <= 0) {
			throw new InvalidAmountException("Amount enetered is not valid");
		}
		this.amount = amount;
	}
	
	
	
	@Override
	public int compareTo(Subscription o) {
		
		return this.subId.compareTo(o.subId);
	}


}
