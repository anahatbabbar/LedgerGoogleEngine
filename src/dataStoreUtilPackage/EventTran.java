package dataStoreUtilPackage;

public class EventTran {

	private String strTranKeyString;
	private String strPersonKeyString;
	private double dblTranWeight;
	private double dblAmount;
	private double dblAmountToChange;
	private String strTranStatus;
	
	public EventTran(String tranKeyString,String personKeyString,double tranWeight,double amount,double amountToChange,String tranStatus){
		strTranKeyString = tranKeyString ;
		strPersonKeyString = personKeyString;
		dblTranWeight = tranWeight;
		dblAmount = amount;
		dblAmountToChange = amountToChange;
		strTranStatus = tranStatus;
	}
	
	public String getTranKeyString(){
		return strTranKeyString;
	}
	
	public String getPersonKeyString(){
		return strPersonKeyString;
	}
	
	public double getTranWeight(){
		return dblTranWeight;
	}
	
	public double getAmount(){
		return dblAmount;
	}
	
	public double getAmountToCharge(){
		return dblAmountToChange;
	}
	
	public String getTranStatus(){
		return strTranStatus;
	}
	
}
