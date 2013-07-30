<%@ page import = "dataStoreUtilPackage.DataStoreWrapperUtil" %>
<%@ page import = "dataStoreUtilPackage.Attendee" %>
<%@ page import = "dataStoreUtilPackage.Person" %>
<%@ page import="java.text.DateFormat,java.text.SimpleDateFormat,java.util.Date;" %>
<%
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date currentDateTime = new Date();
			String strDateTime = dateFormat.format(currentDateTime);
			int noOfPersons=0;//no of selects
			Person[] friends=null;//options . For attendeelist , put one friend in 1 select. For friends, put all friends in 1 select. 
			if(request.getParameter("attendeeFriendsText").equals("1"))
			{
				String attendeeListkeyString = request.getParameter("attendeeListSelect");	
				Attendee attendeeList = DataStoreWrapperUtil.getAttendeeFromKeyString(attendeeListkeyString);
				noOfPersons = attendeeList.getnoOfPersons();
				String personKeyStrings[] = attendeeList.getAttendeeListPersonKeyStrings();
				friends = DataStoreWrapperUtil.getFriendsfromKeyStrings(personKeyStrings);
			}
			else if(request.getParameter("attendeeFriendsText").equals("2")){
				noOfPersons = Integer.parseInt(request.getParameter("friendno"));
				friends = DataStoreWrapperUtil.getAllMyFriends();				
			}
				
%>
<html>
<head>
<script>
	validateForm(){
		
		//validation 1 : atleast one weightage is non zero
		//validation 2 : Sum of all amounts = total check
		//validation 3 : Sum of amount to charge <= total check 
		//validation 4 : check that total check > 0
		var i=0;
		var errorString=null;
		
		//validation 2
		
		
		//validation 4
		if(document.getElementById(eventTotalId).value =="0")
		{
			errorString[i]="Total check can't be zero . Please record how much was the bill... "
			i++;
		}
		
	}
</script>

</head>

<body>

<p>Welcome<br> 
</p>
<table border = "1" style="height:100% ; width:100% ">
	<tr>
		<td style=" width:30% ;text-align: left; vertical-align: top"> Menu </td>
		<td style="text-align: left; vertical-align: top"> Welcome <br><br>
		
			<form name ="eventsForm" id = "eventsFormId" action = "/ledgergoogle" method="post"> 
			<% if(request.getParameter("attendeeFriendsText").equals("1")) 
				{ 
			%>
				<input type = "text" name = "attendeeFriendsText" value ="1" style="display: none;"/>
				Attendee List : <input type ="text" id = "AttendeeText" name="attendeeListText" readonly="readonly" value="<%=request.getParameter("attendeeListSelect") %>">
			<%
				}
				else
				{
			%>
				<input type = "text" name = "attendeeFriendsText" value ="2" style="display: none;"/>
				No Of Friends : <input type ="text" id = "FriendText" name="friendText" readonly="readonly" value="<%=request.getParameter("friendno") %>">
			<%
				}
			%>
			<table border = "1" style="height:50% ; width:70% ">
			<tr>
				<th> Attendees </th>
				<th> Split weightage </th>
				<th> Amount paid </th>
				<th> Amount to charge </th>
			</tr>
			<tr>
				<td> <input type = "text" name = "hostAttendee" value ="anahatbabbar" readonly="readonly"/>
				<td> <input type = "text" name = "hostAttendeeWeightage" size = "2" value = "1"/>
				<td> <input type = "text" name = "hostAttendeeAmount" size="3" value="0"/>
				<td> <input type = "text" name = "hostAttendeeToCharge" size="3" value="0"/>
			</tr>
			<%
			int looper=0;
			for(int num=1;num<= noOfPersons;num++)
			{
			%>
			<tr>
				<td>
				<%
				if(request.getParameter("attendeeFriendsText").equals("1"))
				{
					//put only one friend in one input box. This is because we already know the friends in attendeeList 
				%>
						<input type = "text" name ="<%="attendee"+num %>" value ="<%=friends[num].getPersonName()+":"+friends[num].getEmailAddress()%>" readonly="readonly"/> 
				<%
				}
				else if(request.getParameter("attendeeFriendsText").equals("2"))
				{
					// put all friends as options in the select drop box. This is because we don'y know who all are attending the party. 	
				%>
						<Select id="<%="friendSelectId"+num%>" name="<%="friendSelect"+num%>">
				<%
								for (looper=0;looper<friends.length;looper++)
								{
				%>
									<option value="<%=friends[looper].getPersonKeyString()%>"><%=friends[looper].getPersonName()+":"+friends[looper].getEmailAddress() %></option>
				<% 
								}
				%>
						</Select>
				<%
				}
				%>
								
				</td>
				<td> <input type = "text" name = "<%="AttendeeWeightage"+num %>" size = "2" value="1" /></td>
				<td> <input type = "text" name = "<%="AttendeeAmount"+num %>" size="3" value = "0"/></td>
				<td> <input type = "text" name = "<%="AttendeeToCharge"+num %>" size="3" value ="0"/></td>
			</tr>
			<%
			}
			%>
			<tr>
			<td> Total Check :</td>
			<td></td>
			<td><input type = "text" id="eventTotalId" name = "eventTotal" size="3" value="0"></td>
			<td></td>
			</table>
			If the total Amount to Charge is not equal to Total Check, we assume that the balance was the tip. So...
			<input type="radio" name = "BalanceAsTip"> Split the tip (balance) across all attendees. If not checked , the balance will be charged to your account. Someone gotta pay..  ;)	
			<br>
			<br>
			Event Info <br>
			Event name :<input type ="text" name="eventName" id ="eventNameId" size = "20"/>
			Event Location: <input type ="text" name="eventLocation" id ="eventLocationId" size = "20"/>	
			Event Date : <input type ="text" name = "eventDateTime" id = "eventDateTimeId" size ="30" value="<%=strDateTime %>" readonly="readonly"/>					
			<input type="submit" onclick = "validateForm()">
			</form>
			
			
		</td>
	</tr>
</table>
</body>
</html>