package dataStoreUtilPackage;

import dataStoreUtilPackage.AccountEntries;
public class Person {
	
	private String personKeyString;
	private String strDateOfCreation;
	private String strPersonName;
	private String strEmailAddress;
	private String strPersonContact;
	
		
	Person(){
	}
	
	public Person(String keyString, String name, String emailAddress, String contact, String date)
	{
		this.personKeyString = keyString;
		this.strPersonName=name;
		this.strEmailAddress=emailAddress;
		this.strPersonContact=contact;
		this.strDateOfCreation = date;
				
	}
	
	public String getPersonName()
	{
		return strPersonName;
	}
	
	public String getPersonContact()
	{
		return strPersonContact;
	}
	
	public String getEmailAddress()
	{
		return strEmailAddress;
	}
	
	public String getDateOfCreation()
	{
		return strDateOfCreation;
	}
	
	public String getPersonKeyString()
	{
		return personKeyString;
	}

}
