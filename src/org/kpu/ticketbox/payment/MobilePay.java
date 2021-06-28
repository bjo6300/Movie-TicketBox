package org.kpu.ticketbox.payment;

public class MobilePay {
	public static final double MOBILE_COMMISION = 0.2;

	public Receipt charge(String product, double amount, String name, String number) {
		Receipt receipt = new Receipt(name, product, "MobilePay", number, amount, amount * (1 + MOBILE_COMMISION));
		//이름, 영화 제목, 결제방식, 은행번호, 원래가격, 커미션붙은 가격
		return receipt;
	}
}
