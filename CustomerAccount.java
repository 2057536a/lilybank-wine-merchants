/*Represents a CustomerAccount class that is responsible for creating
 *a customer object by using the name and the initial balance as
 *variables as captured by the dialog boxes in the main method of AssEx1 class
 */


public class CustomerAccount {
	
	//Instance variables declaration
	private static double discount = 0.2;     //It is a constant so it is declared as final
	public static double saleTotal,returnTotal,returnAmount, saleAmount;
	private String accHolderName;
	public static double accHolderBalance;
	
	//Constructor initialising the instance variables
	// for name and current balance
	public CustomerAccount(String name, double balance) {
		accHolderName = name;
		accHolderBalance = balance;
	}
	
	//Methods to get the customer's name and current balance
	public String getCustomerName() {
		return accHolderName;
	}
	public static double getCustomerBalance() {
		return accHolderBalance;	
	}

	//Methods to calculate the amount of the sale
	// and return, by using the wine object. The final
	//amount is rounded to two decimal places
	public static double saleCalc(Wine myWine) {
		saleAmount = myWine.getPricePer()*myWine.getQuantity();
		saleTotal = Math.round(saleAmount*100)/100D;        //'D' makes it Double literal and rounds to 2 d.p.
		return saleTotal;
	}

	public static double returnCalc(Wine myWine) {
		returnAmount = myWine.getPricePer()*myWine.getQuantity()*(1-discount);
		returnTotal = Math.round(returnAmount*100)/100D;    //'D' makes it Double literal and rounds to 2 d.p.
		return returnTotal;
	}
	
	//Methods to update the customer's balance
	//in the case of sale or return. Final amounts
	//are again rounded to two decimal places
	public static double updateSaleBalance(Wine myWine) {
		double updatedSaleBalance =  Math.round((getCustomerBalance() + saleTotal)*100)/100D;
		return updatedSaleBalance;
	}
	
	public static double updateReturnBalance(Wine myWine) {
		double updatedReturnBalance =  Math.round((getCustomerBalance() - returnTotal)*100)/100D;
		return updatedReturnBalance;
	}
}