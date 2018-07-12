package lib.iOS_FSA.testCase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ilb.iOS_FSA.utility.ApiServices;
import ilb.iOS_FSA.utility.ScreenshotUtility;
import lib.iOS_FSA.common.Common;
import lib.iOS_FSA.initiator.Initiator;
import lib.iOS_FSA.objectRepo.Pg_calendar;
import lib.iOS_FSA.objectRepo.Pg_explore;
import lib.iOS_FSA.objectRepo.Pg_login;
import lib.iOS_FSA.objectRepo.Pg_tools;

//@Listeners({ ScreenshotUtility.class })

public class FSATest_1 {
	Initiator init = null;
	Common comm = null;
	String woNum = null;
	Pg_login Pg_login = null;
	Pg_calendar Pg_calendar = null;
	Pg_explore Pg_explore = null;
	Pg_tools Pg_tools = null;
	String appiumResultCommonPath = "./src/test/java/lib/iOS_FSA/workBench/appiumResultCommon.txt";

	@SuppressWarnings("rawtypes")
	@BeforeMethod
	public void setup() throws IOException {
		comm = new Common();
		init = new Initiator();
		Pg_login = new Pg_login();
		Pg_calendar = new Pg_calendar();
		Pg_explore = new Pg_explore();
		Pg_tools = new Pg_tools();

		init.startDriver();
	}

	@Test(priority = 0)
	public void testiOS() throws IOException {

		// From API

		ApiServices appServices = new ApiServices();

		appServices.getAccessToken();

		String sWOJsonData = "{\"SVMXC__City__c\":\"Delhi\",\"SVMXC__Zip__c\":\"110003\",\"SVMXC__Country__c\":\"India\",\"SVMXC__State__c\":\"Haryana\"}";

		woNum = appServices.getWOName(appServices.getWOORecordID(sWOJsonData));
		System.out.println("WO NUMBER FETCHED " + woNum);
		comm.writeTextFile(appiumResultCommonPath, "true," + woNum);

		// Start the appium driver, as the server usualy times out if no commands are sent
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		Pg_login.login(init.un, init.pwd);

		Pg_tools.doDataSync();
		comm.fetchElement(Pg_explore.btn_explore).tap();

		comm.fetchElement(Pg_calendar.btn_calendar).tap();
		comm.takeScreenShotWrapper();
		Pg_explore.createEvent("Work Order Search 2", woNum, "default", "default", "new event");
//
		Pg_calendar.openClaendarEvent(woNum);
		
		comm.fetchElement(Pg_explore.btn_actions).tap(true);
		comm.fetchElement(Pg_explore.btn_recordTM).tap();
		
		comm.fetchElement(Pg_explore.btn_parts_add).tap();
		comm.setSelectedWrapper(Pg_explore.txt_picker_search, "Starts With");

		// wrpr.fetchElementWrapper("//*[.='Include Online']/..//*[@type='checkbox']/..").tap();
		comm.fetchElement(Pg_explore.btn_picklist_serach).sendKeyWrapper("BlueLake Men Watch");
		comm.fetchElement(Pg_explore.btn_search).tap();
		comm.fetchElement("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']").tap();

		comm.fetchElement(Pg_explore.btn_picklist_serach).sendKeyWrapper("GE Product");
		comm.fetchElement(Pg_explore.btn_search).tap();
		comm.fetchElement("//*[.='GE Product'][@class = 'x-gridcell']").tap();
		comm.fetchElement(Pg_explore.btn_picklist_addSelected).tap();

		comm.fetchElement(Pg_explore.btn_travel_add).tap();
		comm.setDateWrapper(Pg_explore.txt_startDateAndTime, "futureStart");
		comm.setDateWrapper(Pg_explore.txt_endDateAndTime, "futureEnd");
		comm.fetchElement(Pg_explore.txt_lineQty).sendKeyWrapper("100");
		comm.fetchElement(Pg_explore.txt_linePricePerUnit).sendKeyWrapper("20");
		comm.fetchElement(Pg_explore.btn_done).tap();

		comm.fetchElement(Pg_explore.btn_labour_add).tap();
		comm.fetchElement("//*[. = 'Part']//*[@class = 'x-input-el']").tap();
		comm.fetchElement(Pg_explore.btn_picklist_serach).sendKeyWrapper("BlueLake Men Watch");
		comm.fetchElement(Pg_explore.btn_search).tap();
		comm.fetchElement("//*[.='BlueLake Men Watch'][@class = 'x-gridcell']").tap();

		comm.setSelectedWrapper(Pg_explore.btn_activityType, "Cleanup");
		comm.setDateWrapper(Pg_explore.txt_startDateAndTime, "futureStart");
		comm.setDateWrapper(Pg_explore.txt_endDateAndTime, "futureEnd");
		comm.fetchElement(Pg_explore.txt_lineQty).sendKeyWrapper("100");
		comm.fetchElement(Pg_explore.txt_linePricePerUnit).sendKeyWrapper("20");

		comm.fetchElement(Pg_explore.btn_done).tap();

		comm.fetchElement(Pg_explore.btn_save).tap();
		// wrpr.fetchElementWrapper(Pg_explore.btn_yes).tap();

		comm.fetchElement(Pg_explore.btn_actions).tap(true);

		comm.fetchElement(Pg_explore.btn_printServiceReport).tap();

		try {
			if (init.driver.findElement(By.xpath("//*[@class = 'content'][contains(.,'" + woNum + "')]")) != null) {
				System.out.println("Opened the document page successfully");
			}
		} catch (Exception e) {
			System.out.println("Document error : " + e);
		}
		comm.takeScreenShotWrapper();

		comm.fetchElement(Pg_explore.btn_report_done).click();

		init.driver.rotate(ScreenOrientation.LANDSCAPE);
		init.driver.rotate(ScreenOrientation.PORTRAIT);

		// We need to rotate to landscape before rotating to portraite init.driver.rotate(ScreenOrientation.LANDSCAPE); init.driver.rotate(ScreenOrientation.PORTRAIT);

		if (true) {
			// FRom UI Execute a Sahi Pro script and then proceed only if it has passed
			String[] commonFileValArray = comm.execSahiScript("backOffice/appium_verifyWorkDetails.sah");
			if (commonFileValArray[0].equals("false")) {
				System.out.println("Stopping Execution !");
				try {
					comm.writeTextFile(appiumResultCommonPath, "false," + woNum);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}

		} else {
			try {
				comm.writeTextFile(appiumResultCommonPath, "false," + woNum);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	

	@AfterMethod
	public void tearDown() {
		init.driver.close();

	}

}
