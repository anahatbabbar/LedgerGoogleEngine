<%@ page import = "dataStoreUtilPackage.DataStoreWrapperUtil" %>
<%@ page import = "dataStoreUtilPackage.Attendee" %>
<html>
<head>
<script>
	
	
</script>
</head>

<body>

<p>Welcome<br> 
</p>
<table border = "1" style="height:100% ; width:100% ">
	<tr>
		<td style=" width:30% ;text-align: left; vertical-align: top"> Menu </td>
		<td style="text-align: left; vertical-align: top"> Welcome <br><br>
		
		<input type="radio" name="user_activity_type" id = "by_attendee" value="byAttendeeList" checked/>Add by Attendee list<br>
				
		<div id='ByAttendeeDiv' style="display: none;">
			<form name ="attendeeForm" id = "attendeeForm" action = "/ledgergoogle" method="get"> 
			Select Attendee List : <SELECT id = "AttendeeDrop" name="attendeeListSelect" >
			<option selected>Choose Attendee List</option>
			<% 
			Attendee attendeeList[] ;
			attendeeList = DataStoreWrapperUtil.getAllAttendeeList();
			for ( int i=0;i< attendeeList.length;i++)
			{
			%>
			
			<option value=<%=attendeeList[i].getAttendeeListNo()%>> 
			<%= attendeeList[i].getStrAttendeeListName() %></option>
			
			<% 
			}
			
			%>
			</SELECT>
			<input type="submit">
			</form>
		</div>
		
		</td>
	</tr>
</table>
</body>
</html>