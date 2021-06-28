package org.kpu.ticketbox.main;

import java.util.*;

import org.kpu.ticketbox.cinema.AnimationScreen;
import org.kpu.ticketbox.cinema.FamillyScreen;
import org.kpu.ticketbox.cinema.PremiumScreen;
import org.kpu.ticketbox.cinema.Screen;
import org.kpu.ticketbox.util.BackupWriter;
import org.kpu.ticketbox.util.Statistics;

public class TicketBox {
	private FamillyScreen famillyScreen;
	private AnimationScreen animationScreen;
	private PremiumScreen premiumScreen;
	public static final String ADMIN_PASSWORD = "admin"; // 관리자

	Scanner scan = new Scanner(System.in);

	public void initScreen() {
		// Screen(영화제목, 영화 줄거리, 티켓가격, 좌석(rowMax), 좌석(colMax))
		famillyScreen = new FamillyScreen("타짜", "목숨을 걸 수 없다면, 배팅하지 마라!", 8000, 10, 10);
		animationScreen = new AnimationScreen("짱구는 못말려", "사고뭉치 짱구의 하루", 10000, 10, 10);
		premiumScreen = new PremiumScreen("암살", "1933년 조국이 사라진 시대", 25000, 5, 5);
	}

	public Screen selectScreen() {
		int choice = 0; // 상영관 선택

		
		Scanner scan = new Scanner(System.in);
		System.out.println("--------------------");
		System.out.println("-   상영관 선택 메인메뉴   -");
		System.out.println("--------------------");
		System.out.println("1. 가족 영화 1관");
		System.out.println("2. 애니메이션 영화 2관");
		System.out.println("3. 프리미엄 영화 3관 (커피, 케익 제공)");
		System.out.println("선택(1~3, 9)외 종료");
		System.out.println("9. 관리자 메뉴");
		System.out.print("상영관 선택 : ");
		choice = scan.nextInt();

		switch (choice) {
		case 1:
			return famillyScreen;
		case 2:
			return animationScreen;
		case 3:
			return premiumScreen;
		case 9:
			managerMode();
			System.exit(0);
		default:
			return null;
		}

	}

	void managerMode() {
		
		Scanner scan = new Scanner(System.in);
		String fname = "C:\\temp\\tBoxReceipt.txt";
		String pwd="";
		while (true) {
			System.out.print("암호 입력  : ");
			pwd=scan.next();
			if(pwd.equals(ADMIN_PASSWORD)==false) {
				System.out.println("암호가 틀렸습니다. ");
				continue;
			}
			System.out.println("--------------------");
			System.out.println("----  관리자 기능   ----");
			System.out.println("--------------------");
			System.out.println("가족 영화관 결제 총액: "+ Statistics.sum(famillyScreen.getrmap()));
			System.out.println("애니메이션 영화관 결제 총액: "+ Statistics.sum(animationScreen.getrmap()));
			System.out.println("프리미엄 영화관 결제 총액: "+ Statistics.sum(premiumScreen.getrmap()));
			System.out.println("영화관 총 티켓 판매량 : "+(Statistics.ticketsize(famillyScreen.getrmap())+Statistics.ticketsize(animationScreen.getrmap())+Statistics.ticketsize(premiumScreen.getrmap())));
			System.out.println(fname+" 백업 시작합니다.");
			
			BackupWriter write = new BackupWriter();
			write.backupFile(fname, famillyScreen.getrmap());
			write.backupFile(fname, animationScreen.getrmap());
			write.backupFile(fname, premiumScreen.getrmap());
			System.out.println("가족 영화관 매출 백업 완료");
			System.out.println("애니메이션 영화관 매출 백업 완료");
			System.out.println("프리미엄 영화관 매출 백업 완료");
			System.out.print(" 안녕히 가세요 !");
			break;
		}
	}
}
