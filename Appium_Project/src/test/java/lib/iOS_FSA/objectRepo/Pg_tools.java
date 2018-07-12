package lib.iOS_FSA.objectRepo;

import org.openqa.selenium.By;

import lib.iOS_FSA.common.Common;
import lib.iOS_FSA.initiator.Initiator;

public class Pg_tools {
	Initiator init = new Initiator();
	 Common wrpr = new Common();
	public String btn_tools = "//*[text() = 'Tools']";
	public String btn_syncDataNow = "//*[text() = 'Sync Data Now']";
	public String btn_startSync = "//*[text() = 'Start Sync']";
	public String txt_syncSuccess = "//*[text() = 'Success']";
	public String txt_refreshingView ="//*[text()='Refreshing view']";
	
	public boolean doDataSync() {
		
		wrpr.fetchElement(btn_tools).tap();
		wrpr.fetchElement(btn_syncDataNow).tap();
		wrpr.fetchElement(btn_startSync).tap();


		try {
			
			if (wrpr.fetchElement(txt_refreshingView).waitforElement(30) != false) {
				System.out.println("Sync completed successfully");
				return true;
			}else {
				
				System.out.println("Sync Failed !");
				return false;

			}
			
		} catch (Exception e) {
			System.out.println("Sync error : " + e);
			return false;

		}
		
	}


}
