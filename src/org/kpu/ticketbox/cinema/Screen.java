package org.kpu.ticketbox.cinema;


import java.util.HashMap;

import org.kpu.ticketbox.payment.MovieTicket;
import org.kpu.ticketbox.payment.Receipt;

public abstract class Screen {
	protected int nTicketPrice; // 티켓 가격
	protected int nRowMax; // 좌석 행 최대 값
	protected int nColMax; // 좌석 열 최대 값
	protected String strMovieName; // 상영중인 영화 제목
	protected String strMovieStory; // 상영중인 영화 줄거리
	protected MovieTicket[][] seatArray; // 좌석 2차원 배열
	private int nCurrentReservedId = 100; // 예약번호
	private HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>();
	
	public Receipt getReceiptIdMap(int id) { //예약번호 출력
		return receiptMap.get(id);
	}

	public void setReceiptMap(int id, Receipt receipt) { // 예약번호, receipt 설정
		receiptMap.put(id, receipt);
	}
	
	public HashMap<Integer,Receipt> getrmap()
	{
		return receiptMap;
	}

	public int getnCurrentReservedId() {
		return nCurrentReservedId;
	}

	public void setnCurrentReservedId(int nCurrentReservedId) {
		this.nCurrentReservedId = nCurrentReservedId;
	}

	public abstract void showMovieInfo(); // 영화 정보 보여주기

	Screen(String name, String story, int price, int row, int col) { // 생성자
		this.strMovieName=name;
		this.strMovieStory=story;
		this.nTicketPrice=price;
		this.nRowMax=row;
		this.nColMax=col;
		this.seatArray = new MovieTicket [row][col];
	}

	public void showScreenMenu() { // 상영관 메뉴 보여주기
		
	}

	public void showSeatMap() { // 상영관 좌석 예약 현황 보여주기
	}
	
	public void reserveTicket() { // 좌석 예약	
	}
	
	public void changeTicket() {} //좌석 변경
	
	
	private MovieTicket checkReservedId(int id) {return null;} //예약번호 확인
	
	public void payment(){} // 좌석 예약 결제
	
	public void printTicket(){} // 좌석 티켓 출력

	
	
}
