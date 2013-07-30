package ledger;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataStoreUtilPackage.Person;
import dataStoreUtilPackage.DataStoreWrapperUtil;

@SuppressWarnings("serial")
public class LedgerAddFriendServelet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String str = request.getQueryString();
		System.out.println("**********The query has : "+str);
		//Splitting the query string
		//String [] splitqueryStr = str.split("?");
		response.setContentType("text/plain");
		
		String responseStr = "blank";
		
		//Check if the get request came from friendScreen web page
		if(str.charAt(0)=='q')
		{
			String emailId = str.substring(2, (str.length()-1));
			DataStoreWrapperUtil.initialize();
			Person friend = DataStoreWrapperUtil.getFriendfromEmailId(emailId);
			if(friend !=null){
				responseStr = friend.getPersonKeyString()+","+friend.getPersonName()+","+friend.getPersonContact();
				//response.getWriter().println(responseStr);
			}
			response.setContentType("text/html");
			response.getWriter().append(responseStr);
			System.out.println("\n **** The reposnse being sent is :"+response.toString());
		}
	}
	
	public void dePost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		if(request.getParameter("addmodify").equals("add")==true){
			DateFormat dateFormat  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String strDate = dateFormat.format(date);
		
			Person friend = new Person(null,(String)request.getParameter("friendEmail"),(String)request.getParameter("friendName"),(String)request.getParameter("friendContact"), strDate);
		
			DataStoreWrapperUtil.addModifyFriend(friend, strDate);
		}
		
		else {
			Person friend = new Person((String)request.getParameter("keyString"),(String)request.getParameter("friendEmail_mod"),(String)request.getParameter("friendName_mod"),(String)request.getParameter("friendContact_mod"), null);
			DataStoreWrapperUtil.addModifyFriend(friend, null);
		}
		
	}

}
