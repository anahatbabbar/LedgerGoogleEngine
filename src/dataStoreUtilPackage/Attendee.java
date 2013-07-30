package dataStoreUtilPackage;


/*This is the structure of the object
 * ------------------------------------------------------------------------------
 * |strAttendeeListNo|strAttendeeListName|strDateOfCreation| | 
 * -------------------------------------------------------------
 * |Key1              |Punter             |09/29/2012      |1|
 * -------------------------------------------------------------
 * |                 |                   |                 |2|
 * -------------------------------------------------------------
 * |                 |                   |                 |3|
 * * -----------------------------------------------------------
 * 
 * The second last column stores person_id and the last column stores person name in the attendee list
 */

public class Attendee {
	
	private String strAttendeeListKeyString;
	private String strAttendeeListName;
	private String strDateOfCreation;
	private int intNoOfPersons;
	private String[] attendeeListPersonKeyStrings;
	
	public Attendee(){}
	
	public Attendee(String attendeeListKeyString,String strAttListName,String strCreationDate,int NoOfPersons,String[] personKeyStrings)
	{
		this.strAttendeeListKeyString = attendeeListKeyString;
		this.strAttendeeListName = strAttListName;
		this.strDateOfCreation = strCreationDate;
		this.intNoOfPersons = NoOfPersons;
		this.attendeeListPersonKeyStrings = personKeyStrings;
		
	}
	
	
//getters
	public String getAttendeeListName()
	{
		return this.strAttendeeListName;
	}
	public String getDateOfCreation()
	{
		return this.strDateOfCreation;
	}
	
	public String[] getAttendeeListPersonKeyStrings()
	{
		return this.attendeeListPersonKeyStrings;
	}
	
	public int getnoOfPersons()
	{
		return this.intNoOfPersons;
	}
	public String getAttendeeListKeyString()
	{
		return this.strAttendeeListKeyString;
	}
	
//Setters	
	/*public void setStrAttendeeListName(String str)
	{
		this.strAttendeeListName=str;
	}
	

	public void setStrDateOfCreation(String str)
	{
		this.strDateOfCreation=str;
	}
	public void setIntNoOfPersons(int no)
	{
		this.intNoOfPersons=no;
	}
	
	
	public void setAttendeeListPersons(int intPersonId)
	{
		
	}
	*/
	
}
