package org.kpu.ticketbox.payment;

public class CardPay {
	public static final double CARD_COMMISION = 0.15;

	public Receipt charge(String product, double amount, String name, String number) {
		Receipt receipt = new Receipt(name, product, "CardPay", number, amount, amount * (1 + CARD_COMMISION));
		//이름, 영화 제목, 결제방식, 은행번호, 원래가격, 커미션붙은 가격
		return receipt;
	}
}
