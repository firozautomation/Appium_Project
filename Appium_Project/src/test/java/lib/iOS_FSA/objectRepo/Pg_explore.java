package lib.iOS_FSA.objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import lib.iOS_FSA.common.*;
import lib.iOS_FSA.initiator.Initiator;

public class Pg_explore {

	Initiator init = new Initiator();
	 Common wrpr = new Common();
	
//	@FindBy(xpath="//android.view.View[@content-desc='Explore']")
//	private String btn_exploreFact = "//*[text() = 'Explore']";
//	public WebElement btn_explore() 
//	{
//		return (WebElement) wrpr.fetchElementWrapper(btn_exploreFact);
//	}
	

	public  String btn_explore = "//*[text() = 'Explore']";
	public  String txt_search = "//input[@placeholder='Search']";
	public  String btn_search = "//*[.='Search'][@class = 'x-button-label']";
	public  String btn_actions = "//span[text()='Actions']";
	public  String btn_newEvent = "//*[text() = 'New Event']";
	public  String txt_startDateAndTime = "//*[contains(text(),'Start Date and Time')][@class = 'x-label-text-el']/../..//input";
	public  String txt_endDateAndTime = "//*[contains(text(),'End Date and Time')][@class = 'x-label-text-el']/../..//input";
	public  String txt_subject = "//*[. = 'Subject']//*[@class = 'x-input-el']";
	public  String btn_save = "//*[text() = 'Save']";
	public  String btn_yes = "//*[text() = 'Yes']";
	public  String txt_lineQty = "//*[. = 'Line Qty']//*[@class = 'x-input-el']";
	public  String txt_linePricePerUnit = "//*[. = 'Line Price Per Unit']//*[@class = 'x-input-el']";
	public  String btn_done = "//*[text() = 'Done']";
	public  String btn_recordTM = "//*[text() = 'Record T&M']";
	public  String txt_picker_search = "//*[@class='x-unsized x-textinput x-input x-component sfmsearch-picklist-borderless']//input[@name='picker']";
	public  String btn_parts_add = "//*[contains(text(),'Parts (')]/../../../../..//*[contains(text(),'Add')]";
	public  String btn_picklist_serach = "//input[@placeholder='Search'][@class='x-input-el']";
	public  String btn_picklist_addSelected = "//*[. = 'Add Selected']";
	public  String btn_travel_add = "//*[contains(text(),'Travel (')]/../../../../..//*[contains(text(),'Add')]";
	public  String btn_labour_add = "//*[contains(text(),'Labor (')]/../../../../..//*[contains(text(),'Add')]";
	public  String txt_part = "//*[. = 'Part']//*[@class = 'x-input-el']";
	public  String btn_activityType = "//*[. = 'Activity Type']//input";
	public  String btn_printServiceReport = "//*[text() = 'Print Service Report']";
	public  String btn_report_done = "//input[@value ='Done']";

	public  Pg_explore navigateToExplore() {
		//btn_explore().click();
		wrpr.fetchElement(btn_explore).tap();
return this;
	}

	public  Pg_explore searchForWorOrder(String searchName, String workOrderNumber) {
		navigateToExplore();
		wrpr.fetchElement("//*[text() = '" + searchName + "']").tap();
		wrpr.fetchElement(txt_search).sendKeyWrapper("");
		wrpr.fetchElement(txt_search).sendKeyWrapper(workOrderNumber);
		return this;

	}

	public  Pg_explore searchAndSelectWorOrder(String searchName, String workOrderNumber) {
		searchForWorOrder(searchName, workOrderNumber);
		wrpr.fetchElement(btn_search).tap();
		wrpr.fetchElement("//*[@class='x-gridcell sfmsearch-grid-cell']//*[contains(.,'" + workOrderNumber + "')]").tap();
		return this;

	}

	public  Pg_explore createEvent(String searchName, String workOrderNumber, String startDate, String endDate, String Subject) {
		searchAndSelectWorOrder(searchName, workOrderNumber);

		
		wrpr.fetchElement(btn_actions).tap(true);
		
		wrpr.fetchElement(btn_newEvent).tap();
		wrpr.setDateWrapper(txt_startDateAndTime, startDate);

		wrpr.setDateWrapper(txt_endDateAndTime, endDate);
		wrpr.fetchElement(txt_subject).sendKeyWrapper(Subject);
		wrpr.fetchElement(btn_save).tap();

		if (wrpr.fetchElement(btn_yes) != null) {
			wrpr.fetchElement(btn_yes).tap();
		}
		return this;


	}
}
