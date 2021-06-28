package org.kpu.ticketbox.payment;

public class BankTransfer implements Pay{
	public static final double BANK_TRANSFER_COMMISION = 0.1;
	public  Receipt charge(String product, double amount, String name, String number) {
		Receipt receipt=new Receipt(name,product,"BankTransfer",number,amount,amount*(1+BANK_TRANSFER_COMMISION));
		//이름, 영화 제목, 결제방식, 은행번호, 원래가격, 커미션붙은 가격
		return receipt;
	};
}
