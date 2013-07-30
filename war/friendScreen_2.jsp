<html>
<head>
<script>

	function showhide(str) {
	
		if(str == 'add_create_friend'){
			if (document.all) { //IS IE 4.0 or less
				eval( "document.all.addCreateDiv.style.display = 'block'"); 
				eval( "document.all.modifyDiv.style.display = 'none'"); 
			} 
			if (document.layers) { //IS NETSCAPE 
				document.layers[addCreateDiv].display = 'block'; 
				document.layers[modifyDiv].display = 'none'; 
			} 
			//if (document.getElementById &&!document.all) { 
			else{	
				hza = document.getElementById(addCreateDiv); 
				hza.style.display = 'block'; 
				hzb = document.getElementById(modifyDiv); 
				hzb.style.display = 'none'; 
			} 
		} 
		else{
			if (document.all) { //IS IE 4.0 or less
				eval( "document.all.modifyDiv.style.display = 'block'"); 
				eval( "document.all.addCreateDiv.style.display = 'none'"); 
			} 
			if (document.layers) { //IS NETSCAPE 
				document.layers[modifyDiv].display = 'block';
				document.layers[addCreateDiv].display = 'none';  
			} 
			else { 
				hza = document.getElementById(modifyDiv); 
				hza.style.display = 'block'; 
				hzb = document.getElementById(addCreateDiv); 
				hzb.style.display = 'none'; 
			} 
		}
	} //showhide
	
	
	function checkEmail(toDo,str)
	{
		if(str != null)
			{
				if(str.substr(str.length-10, 10)== "@gmail.com")
				{
					xmlhttp = new XMLHttpRequest();
					xmlhttp.onreadystatechange=function() //called by AJAX 
					{
						if (xmlhttp.readyState==4 && xmlhttp.status==200)
					    {
					    	if(xmlhttp.responseText.length !=0)
					    	{
								if(toDo == "checkEmail"){
					    		document.getElementById("idFriendEmail").style.color="red";
								}
								else{
									
									var responseString= xmlhttp.responseText.split(",");
									modFriendName = document.getElementById("modifyFriendName");
									modFriendName.value= responseString[1];
									modFriendContact = document.getElementById("modifyFriendContact");
									modFriendContact.value = responseString[2];
									
									document.getElementById("errorText").style.display = 'none';
									document.getElementById("keyString").value = responseString[2];
									document.getElementById("modifyFriendEmail").readonly = "readonly";
									modFriendName.style.display = 'block';
									modFriendContact.style.display = 'block';
									
									document.getElementById("ModifyFriendFormSubmit").style.display ='block' ;
																
								}
					    	}
					    	else{
					    		if(toDo =="setNameContact"){
					    			document.getElementById("errorText").style.display = 'block';
					    		}
					    	}
					    	
					    }
					}
					xmlhttp.open("GET","/ledgeraddfriend?q="+str,true);
					xmlhttp.send();
				}
		}
		else
		{
			document.getElementById("idFriendEmail").style.color="";
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
		
		<input type="radio" name="user_activity_type" id = "add_create_friend" value="add_create_friend" onclick = "showhide('add_create_friend');"/>Add new friend<br>
		<input type="radio" name="user_activity_type" id = "modify_friend" value="modify_friend" onclick = "showhide('modify_friend');"/>Modify friend details<br>
		
		<div id='addCreateDiv' style="display: none;"> 
			<form action="/ledgeraddfriend" method="post">
			Email Id*    : <input type="text" id = "idFriendEmail" name="friendEmail" size = "50" onblur="checkEmail('checkEmail', this.value)"><br>
			Friend Name* : <input type = "text" name="friendName" size ="50"><br>
			Contact no  : <input type="text" name = "friendContact" size = "50"><br>
			<input type = "text" name = "addmodify" value ="add" style="display:none;"/>
			<input type="submit" value = "Cheers" onClick="validateForm()">
			</form>
		</div>
		<textarea rows="1" cols="1" id= "errorText" style="display:none;">No such email Id found in our records . Are you sure !!! </textarea>
		<div id='modifyDiv' style="display: none;"> 
			<form action = "/ledgeraddfriend" method="post">
			Present Email Id : <input type = "text" id = "modifyFriendEmail" name="FriendEmail_mod" size ="20" readonly = "" onblur ="checkEmail('setNameContact',this.value"/><br>
			Change Friend Name : <input type ="text" id = "modifyFriendName" name="friendName_mod" style="size:20;display:none;" /><br> 
			Change Friend Contact : <input type = "text" id="modifyFriendContact" name="friendContact_mod" style="size:20 ; display:none;" ><br>
			<input type = "text" name = "keyString" value = "" style="display:none;"/>
			<input type = "text" name = "addmodify" value ="modify" style="display:none;"/>
			<input type="submit" id= "ModifyFriendFormSubmit" style = "display:none;">
			</form>
		</div>	
		</td>
	</tr>
</table>
</body>
</html>