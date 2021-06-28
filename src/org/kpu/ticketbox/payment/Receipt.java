package org.kpu.ticketbox.payment;

public class Receipt {
	String client; // 사용자 이름
	String productName; // 영화 상품 이름
	String payMethod; // 결제 수단
	String payNumber; // 결제 정보(번호)
	double subTotalAmount; // 커미션 제외한 금액
	double totalAmount; // 커미션 포함한 금액
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public Receipt (String client,String productName,String payMethod,String payNumber,double subTotalAmount,double totalAmount)
	{
		this.client=client;
		this.productName=productName;
		this.payMethod=payMethod;
		this.payNumber=payNumber;
		this.subTotalAmount=subTotalAmount;
		this.totalAmount=totalAmount;
	}
	
	public String toString() 
	{
		return "[ Client :	"+client+"	]"+"\n" + "[ Product :	"+productName+"	]"+"\n" + "[ PayMethod :	"+payMethod+"	]"+"\n"+ "[ PayNumber :	"+payNumber+"	]"+"\n" + "[ SubTotal :	"+subTotalAmount+"	]"+"\n" + "[ Total :	"+String.format("%.1f", totalAmount)+"	]"+"\n";
	}
	
	public String toBackupString() {
		 return this.client+","+this.productName+","+this.payMethod+","+this.payNumber+","+this.subTotalAmount+","+this.totalAmount;
	}

	
}
