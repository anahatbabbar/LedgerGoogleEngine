package businessLogicUtilPackage;

import dataStoreUtilPackage.Event;
import dataStoreUtilPackage.EventTran;
import dataStoreUtilPackage.AccountEntries;

//import java.math.*;

class PayableReceivableAmount{
	String PersonKeyString;
	double payableAmount;
}

public class BusinessLogicUtil {
	
	// Selection sorting
	//Order in descending
	private static PayableReceivableAmount[] sortPayableAmountDesc(PayableReceivableAmount pra[])
	{
		
		int length = pra.length;
		if(length == 1){
			return pra;
		}
		int outer = 0;
		int inner = 0;
		int index;
		PayableReceivableAmount tempVar;
		
		for ( outer = 0 ; outer < length - 1;outer ++){
			index = outer;
			inner = outer+1;
			while(inner <= length-1){
				if(pra[index].payableAmount < pra[inner].payableAmount)
				{
					index = inner;
				}
				inner ++;
			}
			if(index != outer ){
				tempVar = pra[outer];
				pra[outer]= pra[index];
				pra[index]= tempVar;
			}
		}
				
		return pra;
	}
	
	//Order in ascending 
	private static PayableReceivableAmount[] sortReceivableAmountAsc(PayableReceivableAmount pra[])
	{
		
		int length = pra.length;
		if(length == 1){
			return pra;
		}
		int outer = 0;
		int inner = 0;
		int index;
		PayableReceivableAmount tempVar;
		
		for ( outer = 0 ; outer < length - 1;outer ++){
			index = outer;
			inner = outer+1;
			while(inner <= length-1){
				if(pra[index].payableAmount > pra[inner].payableAmount)
				{
					index = inner;
				}
				inner ++;
			}
			if(index != outer ){
				tempVar = pra[outer];
				pra[outer]= pra[index];
				pra[index]= tempVar;
			}
		}
				
		return pra;
	}
	
	
	public static AccountEntries[] generateAccountEntries(Event event, EventTran trans[])
	{
		double totalEventAmount = event.getEventAmount();
		double totalWeight = 0;
		PayableReceivableAmount accountPayableEntries[] = new PayableReceivableAmount[trans.length-1]; // setting default as no of participants - 1
		PayableReceivableAmount accountReceivableEntries[] = new PayableReceivableAmount[trans.length-1]; // same as above
		int i = 0;
		int payableCounter = 0;
		int receivableCounter = 0;
		int maxNoOfZeros = trans.length;
		int noOfZeros = 0;
		int counter = 0;
		
		
		
		//get the total weight to calculate person weighted amount
		for (i = 0;i < trans.length; i++){
			totalWeight= totalWeight + trans[i].getTranWeight();
		}
		
		//get person amount he should have paid based on weights entered
		for(i=0;i<trans.length;i++){
			PayableReceivableAmount pa = new PayableReceivableAmount();
			pa.PersonKeyString = trans[i].getPersonKeyString();
			pa.payableAmount = ((trans[i].getTranWeight()/totalWeight)*totalEventAmount) - trans[i].getAmount();
			if (pa.payableAmount<0){
				accountPayableEntries[payableCounter] = pa;
			}
			else{
				accountReceivableEntries[receivableCounter] = pa; // for both +ve vale and zero value
			}
		}
		
		// sort the two arrays in descending order
		accountPayableEntries = sortPayableAmountDesc(accountPayableEntries); // Descending
		accountReceivableEntries = sortReceivableAmountAsc(accountReceivableEntries); // Ascending
		
		AccountEntries[] accountEntries = new AccountEntries[(trans.length-1)*2]; // Taken a randon no. Needs to change later to a linked list
		
		while(noOfZeros <= maxNoOfZeros){
			if(Math.abs(accountPayableEntries[0].payableAmount) <= Math.abs(accountReceivableEntries[0].payableAmount)){
				accountReceivableEntries[0].payableAmount += accountPayableEntries[0].payableAmount;
				
				//Acoount entry 1
				AccountEntries acctEntry1 = new AccountEntries (accountReceivableEntries[0].PersonKeyString,event.getEventKeyString(),Math.abs(accountPayableEntries[0].payableAmount),0.0,accountPayableEntries[0].PersonKeyString,"pending");
				//Mirror account entry 2
				AccountEntries acctEntry2 = new AccountEntries (accountPayableEntries[0].PersonKeyString,event.getEventKeyString(),0.0,Math.abs(accountPayableEntries[0].payableAmount),accountReceivableEntries[0].PersonKeyString,"pending");
				
				accountEntries[counter]= acctEntry1;
				counter++;
				accountEntries[counter]= acctEntry2;
				counter++;
				accountPayableEntries[0].payableAmount = 0;
				noOfZeros++;
				if(accountPayableEntries[0].payableAmount == accountReceivableEntries[0].payableAmount){
					noOfZeros++;
				}
				
			}
			else{
				accountPayableEntries[0].payableAmount += accountReceivableEntries[0].payableAmount;
				
				//Acoount entry 1
				AccountEntries acctEntry1 = new AccountEntries (accountReceivableEntries[0].PersonKeyString,event.getEventKeyString(),Math.abs(accountPayableEntries[0].payableAmount),0.0,accountPayableEntries[0].PersonKeyString,"pending");
				//Mirror account entry 2
				AccountEntries acctEntry2 = new AccountEntries (accountPayableEntries[0].PersonKeyString,event.getEventKeyString(),0.0,Math.abs(accountPayableEntries[0].payableAmount),accountReceivableEntries[0].PersonKeyString,"pending");
				
				accountEntries[counter]= acctEntry1;
				counter++;
				accountEntries[counter]= acctEntry2;
				counter++;
				accountPayableEntries[0].payableAmount = 0;
				noOfZeros++;
			}
			
			accountPayableEntries = sortPayableAmountDesc(accountPayableEntries); // Descending
			accountReceivableEntries = sortReceivableAmountAsc(accountReceivableEntries); // Ascending
		}//while
				
						
		return null;
	}//method
	
	
}//class
