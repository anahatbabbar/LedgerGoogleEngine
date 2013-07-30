<%@ page import = "dataStoreUtilPackage.DataStoreWrapperUtil" %>
<%@ page import = "dataStoreUtilPackage.Attendee" %>
<html>
<head>
<script>
	
	function showhide(str) {
	
	if(str == 'byAttendeeList'){
		if (document.all) { //IS IE 4.0 or less
			eval( "document.all.ByAttendeeDiv.style.display = 'block'"); 
			eval( "document.all.ByFriendDiv.style.display = 'none'"); 
		} 
		if (document.layers) { //IS NETSCAPE 
			document.layers[ByAttendeeDiv].display = 'block'; 
			document.layers[ByFriendDiv].display = 'none'; 
		} 
		//if (document.getElementById &&!document.all) { 
		else{	
			hza = document.getElementById(ByAttendeeDiv); 
			hza.style.display = 'block'; 
			hzb = document.getElementById(ByFriendDiv); 
			hzb.style.display = 'none'; 
		} 
	} 
	else{
		if (document.all) { //IS IE 4.0 or less
			eval( "document.all.ByFriendDiv.style.display = 'block'"); 
			eval( "document.all.ByAttendeeDiv.style.display = 'none'"); 
		} 
		if (document.layers) { //IS NETSCAPE 
			document.layers[ByFriendDiv].display = 'block';
			document.layers[ByAttendeeDiv].display = 'none';  
		} 
		else { 
			hza = document.getElementById(ByFriendDiv); 
			hza.style.display = 'block'; 
			hzb = document.getElementById(ByAttendeeDiv); 
			hzb.style.display = 'none'; 
		} 
	}
} //showhide

		
</script>
</head>

<body>

<p>Welcome<br> 
</p>
<table border = "1" style="height:100% ; width:100% ">
	<tr>
		<td style=" width:30% ;text-align: left; vertical-align: top"> Menu </td>
		<td style="text-align: left; vertical-align: top"> Welcome <br><br>
		
		<input type="radio" name="user_activity_type" id = "by_attendee" value="byAttendeeList" onclick = "showhide('byAttendeeList');"/>Add by Attendee list<br>
		<input type="radio" name="user_activity_type" id = "by_friend" value="byFriend" onclick = "showhide('byFriend');"/>Add by Friends<br>
		
		<div id='ByAttendeeDiv' style="display: none;">
			<form name ="attendeeForm" id = "attendeeForm" action = "/ledgergoogle" method="post"> 
			<input type = "text" name = "attendeeFriendsText" value ="1" style="display: none;"/>
			Select Attendee List : <SELECT id = "AttendeeDrop" name="attendeeListSelect" >
			<option selected>Choose Attendee List</option>
			<% 
			Attendee attendeeList[] ;
			attendeeList = DataStoreWrapperUtil.getAllAttendeeList();
			for ( int i=0;i< attendeeList.length;i++)
			{
			%>
			
			<option value=<%=attendeeList[i].getAttendeeListKeyString()%>> 
			<%= attendeeList[i].getAttendeeListName()%></option>
			
			<% 
			}
			
			%>
			</SELECT>
			<input type="submit">
			</form>
		</div>
		<div id='ByFriendDiv' style="display: none;"> 
			<form name="friendForm"  id = "friendForm" action="/ledgergoogle" method="post">
			<input type = "text" name = "attendeeFriendsText" value ="2" style="display: none;"/>
			Enter no of friends in the event: <input type ='text' id ="friendNo" size='2' name="friendno" >
			<input type="submit">
			</form>
		</div>	
		</td>
	</tr>
</table>
</body>
</html>