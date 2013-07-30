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
			noOfPersons = Integer.parseInt(request.getParameter("friendno"));
			friends = DataStoreWrapperUtil.getAllMyFriends();				
			
				
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
			<input type = "text" name = "attendeeFriendsText" value ="2" style="display: none;"/>
			No Of Friends : <input type ="text" id = "FriendText" name="friendText" readonly="readonly" value="<%=request.getParameter("friendno") %>">
						
			<%
			int looper=0;
			for(int num=1;num<= noOfPersons;num++)
			{
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
			
			<input type="submit" onclick = "validateForm()">
			</form>
			
			
		</td>
	</tr>
</table>
</body>
</html>