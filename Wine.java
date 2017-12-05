/*
 * Demonstrates the "Wine" class. It contains the relevant instance variables
 * and it will be called by an LWMGUI method in order for a Wine object to be
 * created. That method will be responsible for getting details from the text fields
 * of the interface when either the sale or return button is pressed.
 **/

public class Wine {

	private  String wineName;
	private  int  numBottles;
	private  double pricePerBottle;

	//Constructor method
	public Wine(String name, int quantity, double price) {
		wineName = name;
		numBottles = quantity;
		pricePerBottle = price;
	}

	//Methods to return the values for name, quantity and price per bottle
	public String getName() {
		return wineName;
	}
	public int getQuantity() {
		return numBottles;
	}
	public double getPricePer() {
		return pricePerBottle;
	}
}