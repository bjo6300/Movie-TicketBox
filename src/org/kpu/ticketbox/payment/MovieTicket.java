package org.kpu.ticketbox.payment;

public class MovieTicket {
	public static final char SEAT_EMPTY_MARK = '-';
	public static final char SEAT_RESERVED_MARK = 'R';
	public static final char SEAT_PAY_COMPLETION_MARK = '$';
	private int nRow; // 좌석 행
	private int nCol; // 좌석 열
	private int nReservedId; // 예약 번호
	private char cStatus; // 좌석 상태 - EMPTY, RESERVED, PAY_COMPLETION
	
	public void setSeat(int row, int col) {
		this.nRow = row;
		this.nCol=col;
	} // 좌석번호저장

	public void setnReservedId(int id) {
		this.nReservedId=id;
	} // 예약번호저장

	public int getnReservedId() {
		return nReservedId;
	} // 예약번호 읽기

	

	public int getnRow() {
		return nRow;
	}

	public int getnCol() {
		return nCol;
	}

	public char getcStatus() {
		return cStatus;
	}

	public void setcStatus(char cStatus) {
		this.cStatus = cStatus;
	}
	
}
