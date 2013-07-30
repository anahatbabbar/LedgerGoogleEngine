package dataStoreUtilPackage;



import businessLogicUtilPackage.BusinessLogicUtil;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;


public class DataStoreWrapperUtil {
	
	private static DatastoreService dataService=null;
	
	
	public static void initialize(){
		if (dataService == null)
		dataService=DatastoreServiceFactory.getDatastoreService();
		
		
	}
	
		
	// Attendee List related methods. .........................................................
	
	public static Attendee[] getAllAttendeeList()
	{
		int i= 0, j=0;
		
		Filter ownerFilter = new FilterPredicate("owner_person_id", FilterOperator.EQUAL, /*(String)session.getAttribute("userSession.email")*/"anahatbabbar");
		Query queryAttendeeList = new Query("Attendee").setFilter(ownerFilter);// Changed it form 'attendee' to 'Attendee' type. 
		PreparedQuery pq = dataService.prepare(queryAttendeeList);
		
		//Count no of Entities that will be returned
		Attendee[] allAttendeeList = new Attendee[pq.countEntities(null)];
		
		//Iterate through the result set
		for (Entity result : pq.asIterable()) {
			
			String attendeeListKeyString =KeyFactory.keyToString(result.getKey());
			int noOfPerson = (Integer) result.getProperty("no_of_persons");
			String [] personsKeyStrings=new String[noOfPerson];
			
			for (j=1;j<= noOfPerson;j++){
				personsKeyStrings[j-1]=(String)result.getProperty("person"+j);
			}	
			
			allAttendeeList[i] = new Attendee(attendeeListKeyString,(String) result.getProperty("attendee_list_name"),(String) result.getProperty("date_of_creation"),(Integer) result.getProperty("no_of_persons"),personsKeyStrings);
		}
		return allAttendeeList;
	}
	
	
	
	public static Attendee getAttendeeFromKeyString(String attendeeKeyString)
	{
		
		try{
		Key attendeeKey = KeyFactory.stringToKey(attendeeKeyString);
		
		Filter attendeeFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY,FilterOperator.EQUAL,attendeeKey);
		Query attendeeQuery = new Query("Attendee").setFilter(attendeeFilter);
		PreparedQuery pq = dataService.prepare(attendeeQuery);
		
		Entity attendeeListEntity = pq.asSingleEntity();
		int noOfPerson = (Integer) attendeeListEntity.getProperty("no_of_persons");
		String [] personsKeyStrings=new String[noOfPerson];
		
		for (int j=1;j<= noOfPerson;j++){
			personsKeyStrings[j-1]=(String)attendeeListEntity.getProperty("person"+j);
		}	
		Attendee attendeeList = new Attendee(attendeeKeyString,(String)attendeeListEntity.getProperty("attendee_list_name"),(String) attendeeListEntity.getProperty("date_of_creation"),(Integer) attendeeListEntity.getProperty("no_of_persons"),personsKeyStrings);
		return attendeeList;
		
		}catch(Exception ex){
			System.out.println("\n************* Exception CAUGHT in getAttendee!!!**********************\n");
			ex.printStackTrace();
		}
		return null;
	}
	
	
	public static void addNewAttendeeList( /*HttpSession session,*/Attendee attendeeList,String currentDateTime){
		
		try{
		int keyId = 0;
		
		Key key = KeyFactory.createKey("Attendee",/*((String)session.getAttribute("userSession.email"))*/"anahatbabbar" +"_"+"attendee"+"_"+ currentDateTime +"_"+ keyId);
			
		Entity entityAttendeeList = new Entity(key);
		
		entityAttendeeList.setProperty("attendee_list_name", attendeeList.getAttendeeListName());
		
		System.out.println("Attendee List name is :"+attendeeList.getAttendeeListName());
		
		entityAttendeeList.setProperty("date_of_creation", currentDateTime);
		entityAttendeeList.setProperty("owner_person_id", /*(String)session.getAttribute("userSession.email")*/"anahatbabbar");
		entityAttendeeList.setProperty("no_of_persons", attendeeList.getnoOfPersons());
		
		System.out.println("No of persons is :"+attendeeList.getnoOfPersons());
		String personsKeyStrings[]= attendeeList.getAttendeeListPersonKeyStrings();
		for(int i=1;i<=attendeeList.getnoOfPersons();i++){
			entityAttendeeList.setProperty("person"+i, personsKeyStrings[i-1]);
		}
		
		Key key1 = dataService.put(entityAttendeeList);
		System.out.println("This is from Servlet --------------> "+key1.getName());
		
		}catch(Exception ex)
		{
			System.out.println("\n************* Exception CAUGHT in setAttendeeList!!!**********************\n");
			ex.printStackTrace();
		}
		
	}

	public static void setNewNameForAttendeeList(int intAttendeeListNo,String name)
	{
		
	}
	
	//........................................................................................

	
	// AccountEntries related methods .......................................................
	public static AccountEntries[] getAccountEntries(int intPersonId){
		
		return null;
	}
	
	public static void addAccountingEntries(Event event, EventTran trans[], String strDatetime)
	{
		
		AccountEntries[] acctEntries = BusinessLogicUtil.generateAccountEntries(event, trans);
		int i = 0;
		int keyId = 0;
		Key key;
		while(acctEntries[i] != null){
			
			 key = KeyFactory.createKey(KeyFactory.stringToKey(acctEntries[i].getPersonKeyString()),"AccountEntries",/*((String)session.getAttribute("userSession.email"))*/"anahatbabbar" + "_"+"accountEntries"+"_"+ strDatetime +"_"+ keyId);
			 Entity acctEntriesEntity = new Entity(key);
			 
			 acctEntriesEntity.setProperty("event_id",acctEntries[i].getEventKeyString());
			 acctEntriesEntity.setProperty("ar", acctEntries[i].getAccountAR());
			 acctEntriesEntity.setProperty("ap",acctEntries[i].getAccountAp());
			 acctEntriesEntity.setProperty("from_to_person_id", acctEntries[i].getFromToPersonId());
			 acctEntriesEntity.setProperty("acct_entries_status", acctEntries[i].getAccountEntryStatus());
			 
			 dataService.put(acctEntriesEntity);
			 i++;
			 keyId++;
		}
	}
	
	public static void closeOutAccountWith(int personId,int fromToPersonId)
	{
		
	}
	
	public static void removeAccountEntries(int intPersonId, int eventId)
	{
		
	}
	
	public static void addAccountEntriesForPerson(int personId, AccountEntries[] acctEntries){
		
	}
	
	public static AccountEntries[] getAccountEntriesFor(int personId,int fromToPersonId,int Entrystatus){
		
		return null;
	}
	
	// Friend related methods..............................................................
	public static void addModifyFriend(Person friend, String currentDateTime)
	{
		try{
			//checking if it is addition of friend
			if(friend.getPersonKeyString()==null){	
				int keyId = 0;
				Key key = KeyFactory.createKey("Person",/*((String)session.getAttribute("userSession.email"))*/"anahatbabbar" +"_"+"person"+"_"+ currentDateTime +"_"+ keyId);
				Entity entityPerson = new Entity(key);
		
				entityPerson.setProperty("name",friend.getPersonName());
				entityPerson.setProperty("email_address", friend.getEmailAddress());
				entityPerson.setProperty("friend_list_of", /*(String)session.getAttribute("userSession.email")*/"anahatbabbar");
				entityPerson.setProperty("date_of_creation", currentDateTime);
				entityPerson.setProperty("contact", friend.getPersonContact());
		
				dataService.put(entityPerson);
				System.out.println("*****Added a new friend to your list:"+KeyFactory.keyToString(key));
			}
			
			// This was not addition of friend , but modification
			else{
				Key key = KeyFactory.stringToKey(friend.getPersonKeyString());
				
				Filter personFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY,FilterOperator.EQUAL, key);
				Query friendQuery = new Query("Person").setFilter(personFilter);
				
				PreparedQuery pq = dataService.prepare(friendQuery);
				
				for (Entity result : pq.asIterable()) {
					
					result.setProperty("name",friend.getPersonName());
					result.setProperty("contact", friend.getPersonContact());
					dataService.put(result);
				}
				
				System.out.println("*****Modified a friend in your list:"+KeyFactory.keyToString(key));
				
			}
		}catch(Exception ex)
		{
			System.out.println("\n************* Exception CAUGHT in addModifyFriend!!!**********************\n");
			ex.printStackTrace();
		}
				
	}
	
	public static Person[] getAllMyFriends(/*HttpSession session*/ )
	{
		int i = 0;
		Filter friendListOwnerFilter = new FilterPredicate("friend_list_of",FilterOperator.EQUAL,/*(String)session.getAttribute("userSession.email")*/"anahatbabbar");
		Query allFriendsQuery = new Query("Person").setFilter(friendListOwnerFilter);
		
		PreparedQuery pq = dataService.prepare(allFriendsQuery);
		int count = pq.countEntities(null);
		
		Person friendList[] = new Person[count];
		for(Entity result: pq.asIterable())
		{
			String personKeyString =KeyFactory.keyToString(result.getKey());
			String strName = (String)result.getProperty("name");
			String strEmailAddress = (String)result.getProperty("email_address");
			String strDateOfCreation = (String)result.getProperty("date_of_creation");
			String strContact = (String) result.getProperty("contact");
			
			friendList[i]= new Person(personKeyString,strName,strEmailAddress,strContact, strDateOfCreation);
		}
		return friendList;
				
	}
	
	public static Person getFriendfromEmailId(String emailId){
		try{
			
			Filter emailFilter = new FilterPredicate("email_address", FilterOperator.EQUAL,emailId);
			Query query = new Query("Person").setFilter(emailFilter);
			PreparedQuery pq = dataService.prepare(query);
			Entity personEntity =pq.asSingleEntity(); // It throws an exception if more than one entity is returned. More than one person can't have same email. 
			if(personEntity == null)
			{
				return null;
			}
			Person friend;
			String personKeyString =KeyFactory.keyToString(personEntity.getKey());
			String strName = (String)personEntity.getProperty("name");
			String strEmailAddress = (String)personEntity.getProperty("email_address");
			String strDateOfCreation = (String)personEntity.getProperty("date_of_creation");
			String strContact = (String) personEntity.getProperty("contact");
				
			friend= new Person(personKeyString,strName,strEmailAddress,strContact, strDateOfCreation);
			return friend;
			
		}catch(Exception ex){
			if (ex.getClass().getName().equalsIgnoreCase("PreparedQuery.TooManyResultsException")){
				System.out.println("\n\n ******* This is custom exception. Too many entities returned for the query that should have returned only one");
			}
			System.out.println("\n************* Exception CAUGHT in getFriendEmailId which is also called by AJAX !!!**********************\n");
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Person[] getFriendsfromKeyStrings(String personKeyStrings[]){
		try{
				Key personKeys[] = new Key[personKeyStrings.length];
				for(int j=0;j<personKeyStrings.length;j++){
					personKeys[j]= KeyFactory.stringToKey(personKeyStrings[j]);
				}
				//The below code is to convert an array into iterable collection , i.e. Link list or vector...
				java.util.List<Key> personkeyStringList = java.util.Arrays.asList(personKeys);
				
				//Once converted to a collection , it can be easily converted into other collections like Vector e.g.
				// java.utl.Vector vector = new vector (personkeyStringsList);
				//End here
				Filter emailFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.IN,personkeyStringList);
				Query query = new Query("Person").setFilter(emailFilter);
				PreparedQuery pq = dataService.prepare(query);
				if(pq.countEntities(null) == 0){		
					return null;
				}
				else{
					Person[] friends= new Person[pq.countEntities(null)];
					int num = -1;
					for(Entity personEntity:pq.asIterable()){
						num=num+1;
						String personKeyString =KeyFactory.keyToString(personEntity.getKey());
						String strName = (String)personEntity.getProperty("name");
						String strEmailAddress = (String)personEntity.getProperty("email_address");
						String strDateOfCreation = (String)personEntity.getProperty("date_of_creation");
						String strContact = (String) personEntity.getProperty("contact");
				
						friends[num]= new Person(personKeyString,strName,strEmailAddress,strContact, strDateOfCreation);
					}//for
				return friends;
			}
		}catch(Exception ex){
			System.out.println("\n************* Exception CAUGHT in getFriendEmailId which is also called by AJAX !!!**********************\n");
			ex.printStackTrace();
		}
		return null;
	}

//Event related methods..........................................................................

	public static void addModifyEvent(Event event, EventTran trans[], String currentDateTime, String action){
		if(action.equals("add")){
			//creating Event key
			int keyId = 0;
			Key key = KeyFactory.createKey("Event",/*((String)session.getAttribute("userSession.email"))*/"anahatbabbar" +"_"+"event"+"_"+ currentDateTime +"_"+ keyId);
			Entity eventEntity = new Entity(key);
			
			eventEntity.setProperty("event_name", event.getEventName());
			eventEntity.setProperty("attendee_list_key", event.getAttendeeKeyString());
			eventEntity.setProperty("amount", event.getEventAmount());
			eventEntity.setProperty("dateTime", event.getEventDateTime());
			eventEntity.setProperty("location", event.getLocation());
			eventEntity.setProperty("eventStatus", event.getEventStatus());
			
			dataService.put(eventEntity);
			
			//Now create tran in this newly created Event
			
			addModifyTran(KeyFactory.keyToString(key),trans,currentDateTime, action);
			
			addAccountingEntries(event, trans, currentDateTime);
		}
		
	}




// Tran related 
	
	public static void addModifyTran(String eventKeyString , EventTran trans[], String currentDateTime, String action)
	{
		if(action.equals("add")){
			Key key = null;
			int noOfTrans = trans.length;
			
			for(int keyId = 0;keyId < noOfTrans;keyId++){
				key = KeyFactory.createKey(KeyFactory.stringToKey(eventKeyString),"EventTran",/*((String)session.getAttribute("userSession.email"))*/"anahatbabbar" + "_"+"event"+"_"+ currentDateTime +"_"+ keyId);
				Entity eventTranEntity = new Entity("EventTran");
				
				eventTranEntity.setProperty("person_key_string", trans[keyId].getPersonKeyString());
				eventTranEntity.setProperty("weight", trans[keyId].getTranWeight());
				eventTranEntity.setProperty("amt", trans[keyId].getAmount());
				eventTranEntity.setProperty("amt_to_charge", trans[keyId].getAmountToCharge());
				eventTranEntity.setProperty("tranStatus", trans[keyId].getTranStatus());
				
				dataService.put(eventTranEntity);
			}
		}//if
	}

}	

















