package org.kpu.ticketbox.util;
import java.io.*;
import java.util.HashMap;

import org.kpu.ticketbox.payment.Receipt;

public class BackupWriter {
	// c:\\temp\\receipt.txt 파일에 전체 스크린 매출 영수증 출력하기
	public void backupFile(String filename, HashMap<Integer, Receipt> map) {
		
		
		try {
			
			
			FileWriter out=new FileWriter(filename,true);
			for(int i=0;i<map.size();i++) {
				Receipt r = map.get(i+100);
				out.write(r.toBackupString()+"\r\n");
			}
			out.flush();
			
			out.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
