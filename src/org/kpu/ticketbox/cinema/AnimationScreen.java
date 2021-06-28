package org.kpu.ticketbox.cinema;

import java.util.Scanner;

import org.kpu.ticketbox.payment.BankTransfer;
import org.kpu.ticketbox.payment.CardPay;
import org.kpu.ticketbox.payment.MobilePay;
import org.kpu.ticketbox.payment.MovieTicket;
import org.kpu.ticketbox.payment.Pay;
import org.kpu.ticketbox.payment.Receipt;

public class AnimationScreen extends Screen {

	public AnimationScreen(String name, String story, int price, int row, int col) {
		super(name, story, price, row, col);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				seatArray[i][j] = new MovieTicket();
				seatArray[i][j].setcStatus(MovieTicket.SEAT_EMPTY_MARK); // -로 초기화
			}
		}
	}

	public void showScreenMenu() {
		System.out.println("--------------------");
		System.out.println("영화 메뉴 - " + strMovieName);
		System.out.println("--------------------");
		System.out.println("1. 상영 영화 정보");
		System.out.println("2. 좌석 예약 현황");
		System.out.println("3. 좌석 예약 하기");
		System.out.println("4. 좌석 변경 하기");
		System.out.println("5. 좌석 결제 하기");
		System.out.println("6. 티켓 출력 하기");
		System.out.println("7. 메인 메뉴 이동");
	}

	public void showMovieInfo() {
		System.out.println(strMovieName + " 소개");
		System.out.println("영화관 : 애니메이션 영화 2관");
		System.out.println("줄거리 : " + strMovieStory);
		System.out.println("가격 : " + nTicketPrice);
	}

	public void showSeatMap() { // 상영관 좌석 예약 현황 보여주기
		System.out.println("      [ 좌석 예약 현황 ]");
		System.out.println("        [1] [2] [3] [4] [5] [6] [7] [8] [9] [10]");
		for (int i = 0; i < nRowMax; i++) {
			System.out.print("[" + (i + 1) + "]	");
			for (int j = 0; j < nColMax; j++) {
				System.out.print("[" + seatArray[i][j].getcStatus() + "] ");
			}
			System.out.println("");
		}
	}

	public void reserveTicket() { // 좌석 예약
		System.out.println("[ 좌석 예약 ]");

		Scanner scan = new Scanner(System.in);
		int rownum, colnum;
		while (true) {
			System.out.print("좌석 행 번호 입력(1~10) : ");
			rownum = scan.nextInt();
			System.out.print("좌석 열 번호 입력(1~10) : ");
			colnum = scan.nextInt();
			if (rownum > 10 || colnum > 10) {
				System.out.println("좌석번호를 다시 확인하세요.");
				break;
			} else if (rownum < 0 || colnum < 0) {
				System.out.println("좌석번호를 다시 확인하세요.");
				break;
			} else if (seatArray[rownum - 1][colnum - 1].getcStatus() != MovieTicket.SEAT_EMPTY_MARK) {
				System.out.println("예약된 좌석입니다.");
				break;
			}

			else {
				System.out.println("행[" + rownum + "] 열[" + colnum + "] " + getnCurrentReservedId() + " 예약번호로 접수되었습니다.");
				seatArray[rownum - 1][colnum - 1].setcStatus(MovieTicket.SEAT_RESERVED_MARK); // R로 설정
				seatArray[rownum - 1][colnum - 1].setnReservedId(getnCurrentReservedId()); // 예약번호 저장
				seatArray[rownum - 1][colnum - 1].setSeat(rownum, colnum); // 예약번호에 맞는 행 열 설정
				setnCurrentReservedId(getnCurrentReservedId() + 1); // 예약번호 ++

				break;
			}
		}

	}

	public void changeTicket() { //
		int rownum, colnum, seatrv; // 변경할 좌석 행, 변경할 좌석 열, 예약번호
		int oldrow, oldcol; // 과거 행, 열

		Scanner scan = new Scanner(System.in);
		System.out.println(" [ 좌석 변경] ");
		while (true) {

			System.out.print("좌석 예약 번호 입력 : ");
			seatrv = scan.nextInt();
			MovieTicket existrv = checkReservedId(seatrv); // 예약번호 받아오기
			System.out.print("좌석 행 번호 입력(1~10) : ");
			rownum = scan.nextInt();
			System.out.print("좌석 열 번호 입력(1~10) : ");
			colnum = scan.nextInt();
			if (rownum > 10 || colnum > 10) {
				System.out.println("좌석번호를 다시 확인하세요.");
				break;
			} else if (rownum < 0 || colnum < 0) {
				System.out.println("좌석번호를 다시 확인하세요.");
				break;
			}

			else if (seatArray[rownum][colnum].getcStatus() != MovieTicket.SEAT_EMPTY_MARK) {
				System.out.println("예약된 좌석입니다.");
				break;
			}

			else if (existrv == null) {
				System.out.println("존재하지 않는 예약번호입니다.");
				break;
			} else {
				oldrow = existrv.getnRow(); // 과거 행 받아오기
				oldcol = existrv.getnCol(); // 과거 열 받아오기

				System.out.println(
						"예약번호 " + existrv.getnReservedId() + " 행[" + rownum + "] 열[" + colnum + "] " + "좌석으로 변경되었습니다.");

				seatArray[oldrow - 1][oldcol - 1].setcStatus(MovieTicket.SEAT_EMPTY_MARK);// 과거 행,열 빈자리
				seatArray[oldrow - 1][oldcol - 1].setnReservedId(0); // 과거 자리 예약번호 초기화
				seatArray[rownum - 1][colnum - 1].setnReservedId(seatrv); // 예약번호 재설정
				seatArray[oldrow][oldcol].setSeat(rownum, colnum); // 예약번호에 맞는 행 열 설정
				seatArray[rownum - 1][colnum - 1].setcStatus(MovieTicket.SEAT_RESERVED_MARK); // 변경된 자리 R

				break;
			}
		}

	}

	private MovieTicket checkReservedId(int id) {
		for (int i = 0; i < nRowMax; i++) {
			for (int j = 0; j < nColMax; j++) {
				if (seatArray[i][j].getnReservedId() == id)
					return seatArray[i][j];
			}
		}
		return null;
	}

	public void payment() {
		int seatrv, way;

		Scanner scan = new Scanner(System.in);
		System.out.println("[좌석 예약 결제]");
		while (true) {
			System.out.print("예약 번호 입력 : ");
			seatrv = scan.nextInt();

			MovieTicket existrv = checkReservedId(seatrv); // 예약번호 받아오기

			if (existrv == null) {
				System.out.println("존재하지 않는 예약번호입니다.");
				break;
			}

			if (existrv.getcStatus() != MovieTicket.SEAT_RESERVED_MARK) {
				System.out.println("예약하지 않거나 결제된 예약번호 입니다.");
				break;
			}

			System.out.println("--------------------");
			System.out.println(" 결제  방식  선택  ");
			System.out.println("--------------------");
			System.out.println("1. 은행 이체");
			System.out.println("2. 카드 결제");
			System.out.println("3. 모바일 결제");
			System.out.print("결제 방식 입력(1~3) : ");

			way = scan.nextInt();

			if (way == Pay.BANK_TRANSFER_PAYMENT) { // 은행 이체
				String bname, bnumber;
				System.out.println("[ 은행이체 ]");
				BankTransfer b = new BankTransfer();
				System.out.print("이름 입력 : ");
				bname = scan.next();
				System.out.print("은행 번호 입력(12자리 수) : ");
				bnumber = scan.next();
				if (bnumber.length() != 12) {
					System.out.println("잘못된 은행 번호 입니다.");
					break;
				}
				System.out.println(
						"은행 결제가 완료 되었습니다. : " + nTicketPrice * (1 + BankTransfer.BANK_TRANSFER_COMMISION) + "원");
				Receipt br = b.charge(strMovieName, nTicketPrice, bname, bnumber); // 정보 저장
				setReceiptMap(seatrv, br); // 예약번호, 영수증 저장
				existrv.setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK); // 결제완료 표시 저장
				break;

			} else if (way == Pay.CREDIT_CARD_PAYMENT) { // 카드결제
				String cname, cnumber;
				System.out.println("[ 카드결제 ]");
				CardPay c = new CardPay();
				System.out.print("이름 입력 : ");
				cname = scan.next();
				System.out.print("카드 번호 입력(12자리 수) : ");
				cnumber = scan.next();
				if (cnumber.length() != 12) {
					System.out.println("잘못된 카드 번호 입니다.");
					break;
				}
				System.out.println("카드 결제가 완료 되었습니다. : " + nTicketPrice * (1 + CardPay.CARD_COMMISION) + "원");
				Receipt cr = c.charge(strMovieName, nTicketPrice, cname, cnumber);
				setReceiptMap(seatrv, cr);
				existrv.setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK); // 결제완료 표시 저장
				break;
			} else if (way == Pay.MOBILE_PHONE_PAYMENT) { // 모바일 결제
				String mname, mnumber;
				System.out.println("[ 모바일결제 ]");
				MobilePay m = new MobilePay();
				System.out.print("이름 입력 : ");
				mname = scan.next();
				System.out.print("핸드폰 번호 입력(11자리 수) : ");
				mnumber = scan.next();
				if (mnumber.length() != 11) {
					System.out.println("잘못된 핸드폰 번호 입니다.");
					break;
				}
				System.out.println("모바일 결제가 완료 되었습니다. : " + nTicketPrice * (1 + MobilePay.MOBILE_COMMISION) + "원");
				Receipt mr = m.charge(strMovieName, nTicketPrice, mname, mnumber);
				setReceiptMap(seatrv, mr);
				existrv.setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK); // 결제완료 표시 저장
				break;
			} else {
				System.out.println("없는 결제 방식 입니다.");
				break;
			}
		}
	}

	public void printTicket() { // 좌석 티켓 출력
		System.out.println("[좌석 티켓 출력]");

		Scanner scan = new Scanner(System.in);
		while (true) {
			int seatrv;
			System.out.print("좌석 예약 번호 입력 : ");
			seatrv = scan.nextInt();
			MovieTicket existrv = checkReservedId(seatrv);
			if (existrv == null) {
				System.out.println("존재하지 않는 예약번호입니다.");
				break;
			} else if (existrv.getcStatus() != MovieTicket.SEAT_PAY_COMPLETION_MARK) {
				System.out.println("결제 되지 않은 좌석입니다.");
				break;
			}
			System.out.println("--------------------");
			System.out.println(" --   Receipt   -- ");
			System.out.println("--------------------");

			Receipt i = getReceiptIdMap(seatrv);
			System.out.println(i.toString());
			break;
		}

	}

}
