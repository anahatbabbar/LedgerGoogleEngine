package ledger;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dataStoreUtilPackage.Attendee;
import dataStoreUtilPackage.DataStoreWrapperUtil;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LedgerAddAttendeeListServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String currentDateTime= dateformat.format(date);
		
		String  attendeeListName = req.getParameter("attendeeListName");
		String noOfPersons = req.getParameter("attendeeNoOfFriends");
		int intNoOfPersons = Integer.parseInt(noOfPersons);
		String[] strPersonsKeyStrings=new String[intNoOfPersons];
		for(int i=0;i<intNoOfPersons;i++){
			strPersonsKeyStrings[i]=req.getParameter("friendsSelect"+i);
		}
		
		Attendee newAttendeeList = new Attendee(null,attendeeListName,currentDateTime,Integer.valueOf(noOfPersons),strPersonsKeyStrings);
		DataStoreWrapperUtil.initialize();
		DataStoreWrapperUtil.addNewAttendeeList(newAttendeeList, currentDateTime);
		
		resp.setContentType("text/plain");
		resp.getWriter().println("operation completed !!!");
	}
}


