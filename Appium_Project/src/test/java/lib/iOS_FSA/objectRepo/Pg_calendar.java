package lib.iOS_FSA.objectRepo;

import lib.iOS_FSA.common.Common;
import lib.iOS_FSA.initiator.Initiator;

public class Pg_calendar {
	Initiator init = new Initiator();
	 Common wrpr = new Common();
	public static String btn_calendar = "//*[text() = 'Calendar']";


	/**
	 * 
	 * @param woNum
	 */
	public void openClaendarEvent(String woNum) {
	wrpr.fetchElement(Pg_calendar.btn_calendar).tap();

	wrpr.fetchElement("//div[contains(.,'" + woNum + "')][@class='sfmevent-title']").tap();

	}
}
