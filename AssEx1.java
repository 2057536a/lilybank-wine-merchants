/*
 * Demonstrates the main method. It gets the customer name
 * and initial balance via two input dialog boxes.
 *
 * It creates the CustomerAccount object
 *
 * It creates and makes the interface visible by using the
 * CustomerAccount object as a parameter.
 **/

import javax.swing.JOptionPane;

public class AssEx1 {

	public static String customerName, balance;
	public static double customerBalance;

	public static void main(String [] args)
	{
		//Dialog box to capture the customer's name
		//If the user closes the window or clicks
		// "cancel", program will terminate
		String customerName = JOptionPane.showInputDialog("Enter customer's name: ");
		if (customerName == null || customerName.equals("")) {
			System.exit(0);
		}

		//Dialog box to capture the customer's balance
		//If the user closes the window or clicks
		// "cancel", program will terminate. If the user
		//inputs invalid data, the program keeps asking for the correct input.
		String balance = JOptionPane.showInputDialog(null,"Enter customer's initial balance: ");
		for(;;) {
			if (balance == null ) {
				System.exit(0);
			}
			else {
				try
				{
					customerBalance = Double.parseDouble(balance);
					customerBalance = Math.round(customerBalance*100)/100D;
     				break;
				}
				catch (NumberFormatException nfx)
				{
					JOptionPane.showMessageDialog(null,"Balance must be a number","Error",JOptionPane.ERROR_MESSAGE);
					balance = JOptionPane.showInputDialog(null,"Enter customer's initial balance: ");
				}
			}
		}

		//Create a CustomerAccount object with the input
		//from the dialog boxes as captured above
		CustomerAccount myCustomer = new CustomerAccount(customerName,customerBalance);

		//Create a new LWMGUI object after getting the customer's details
		LWMGUI frame = new LWMGUI(myCustomer);
		frame.setVisible(true);
	}
}
