package dataStoreUtilPackage;

public class Event {
	
	private String strKeyString;
	private String strEventName;
	private String strAttendeeKeyString;
	private double dblEventAmount;
	private String strLocation;
	private String strEventDateTime;
	private String strEventStatus;
	
	public Event(String keyString,String eventName,String attendeeKeyString,double eventAmount,String location,String dateTime,String eventStatus){
		strKeyString = keyString;
		strEventName = eventName;
		strAttendeeKeyString = attendeeKeyString;
		dblEventAmount = eventAmount;
		strLocation =location;
		strEventDateTime =dateTime;
		strEventStatus =eventStatus;
	}
	
	public String getEventKeyString(){
		return strKeyString;
	}
	
	public String getEventName(){
		return strEventName;
	}
	
	public String getAttendeeKeyString(){
		return this.strAttendeeKeyString;
	}
	
	public double getEventAmount(){
		return this.dblEventAmount;
	}
	
	public String getLocation(){
		return this.strLocation;
	}
	
	public String getEventDateTime(){
		return this.strEventDateTime;
	}
	
	public String getEventStatus(){
		return this.strEventStatus;
	}

}
