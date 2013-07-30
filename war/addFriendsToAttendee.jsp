<html>
<head>
<%@ page import= "java.util.*, java.io.*" %>
<%@ page import ="dataStoreUtilPackage.DataStoreWrapperUtil" %>
<%@ page import ="dataStoreUtilPackage.Person" %>
<%
	Person allFriends[] = DataStoreWrapperUtil.getAllMyFriends();
	int noOfFriends = allFriends.length;
%>
</head>

<body>
<p>Welcome<br> 
</p>
<table border = "1" style="height:100% ; width:100% ">
	<tr>
		<td style=" width:30% ;text-align: left; vertical-align: top"> Menu </td>
		<td style="text-align: left; vertical-align: top"> Welcome <br><br>
			<form action="/ledgeraddattendeelist" method="post">
				Attendee List Name : <input type = "text" name="attendeeListName" size ="20" value = "${param["attendeeListName"]}"><br>
				No of Friends to be added : <input type = "text" name="attendeeNoOfFriends" size ="2"  value="${param["attendeeNoOfFriends"]}"><br>
				
				<%
					int j=0;
					for(int i =0;i<${param["attendeeNoOfFriends"]};i++)//For loop to add select boxes
					{
				%>
				<select id = <%="IdfriendsSelect"+i %> name = <%="friendsSelect"+i %>>
				<%
						for(j=0;j<noOfFriends;j++)// For loop to add options to select
						{
				%>
							<option value=<%=allFreinds[j].getPersonKeyString();%>><%= allFriends[j].getPersonName()+"-"+allFriends[j].getEmailAddress(); %></option>
				<%
						}
					}
				%>			
				</select>
			<input type="submit"/>
			</form>
		</td>
	</tr>
</table>
</body>
</html>