package lib.iOS_FSA.objectRepo;

import org.openqa.selenium.By;

import lib.iOS_FSA.initiator.Initiator;
import lib.iOS_FSA.wrapper.Wrapper;

public class Pg_tools {
	Initiator init = new Initiator();
	 Wrapper wrpr = new Wrapper();
	public String btn_tools = "//*[text() = 'Tools']";
	public String btn_syncDataNow = "//*[text() = 'Sync Data Now']";
	public String btn_startSync = "//*[text() = 'Start Sync']";
	public String txt_syncSuccess = "//*[text() = 'Success']";
	
	public boolean doDataSync() {
		
		wrpr.fetchElementWrapper(btn_tools).tap();
		wrpr.fetchElementWrapper(btn_syncDataNow).tap();
		wrpr.fetchElementWrapper(btn_startSync).tap();


		try {
			if (wrpr.FetchElementDetails(txt_syncSuccess) != null) {
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
