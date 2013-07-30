package dataStoreUtilPackage;

	//The access specifier of this class is default. This it can't be accessed form outside this package. 
	public class AccountEntries {
	
	private String strEventKeyString;
	private String strPersonkeyString;
	private double dblAccountAr;
	private double dblAccountAp;
	private String strFromToPersonKeyString;
	private String strStatus;
	
	public AccountEntries(String personKeyString,String eventKeyString, double accountAR, double accountAP,String personId, String status){
		this.strPersonkeyString = personKeyString;
		this.strEventKeyString = eventKeyString;
		this.dblAccountAr = accountAR;
		this.dblAccountAp = accountAP;
		this.strFromToPersonKeyString = personId;
		this.strStatus= status;
	}
	
	public String getPersonKeyString(){
		return this.strPersonkeyString;
	}
	
	public int getEventKeyString(){
		return this.getEventKeyString();
	}
	
	public double getAccountAR(){
		return this.dblAccountAr;
	}
	
	public double getAccountAp(){
		return this.dblAccountAp;
	}
	
	public String getFromToPersonId(){
		return this.strFromToPersonKeyString;
	}
	
	public String getAccountEntryStatus(){
		return this.strStatus;
	}

}
