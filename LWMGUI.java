/*
 * Demonstrates the main interface class in the simplest form
 * with just two main buttons and an action listener associated with them.
 * Tested with system output depending on which button the user has clicked
 **/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LWMGUI extends JFrame implements ActionListener{
	
	//Initialising the instance variables
	private JButton saleButton, returnButton;
	private JPanel buttonPanel, productPanel, bottomPanel, bottom, wineBrandPanel;
	private JTextField wineNameText, numBottlesText,pricePerBottleText,transactionText, balanceText;
	public  static Wine myWine;
	public static CustomerAccount myCustomer;
	public static  JLabel winePanelLabel;
	
	// Constructor of the class to get a JFrame with buttons
	public LWMGUI(CustomerAccount myCustomer ) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650,180);
		setLocation(100,100);
		setTitle("Lilybank Wine Merchants: " + myCustomer.getCustomerName());
		layoutComponents(myCustomer);				
	}
	
	//Creating three components for the GUI.
	//The top panel will contain the wine information
	//The middle will contain the sale and return buttons
	//The bottom will contain the name of the wine purchased
	//and the amounts of transaction and current balance
	private void layoutComponents(CustomerAccount myCustomer) {
		layoutTop(myCustomer);
        layoutMiddle(myCustomer);
		layoutBottom(myCustomer);
	}
	
	//Panel creation for the product info to be in text fields at the NORTH of the frame
	private void layoutTop(CustomerAccount myCustomer) {
		
		productPanel = new JPanel();
				
		JLabel wineNameLabel = new JLabel("Name:");
		productPanel.add(wineNameLabel);
		wineNameText = new JTextField(10);
		productPanel.add(wineNameText);
			
		JLabel numBottlesLabel = new JLabel("Quantity:");
		productPanel.add(numBottlesLabel);
		numBottlesText = new JTextField(5);
		productPanel.add(numBottlesText);
				
		JLabel pricePerBottleLabel = new JLabel("Price: £");
		productPanel.add(pricePerBottleLabel);
		pricePerBottleText = new JTextField(5);
		productPanel.add(pricePerBottleText);
				
		//Placing the product info panel on the top side of the frame
		add(productPanel,BorderLayout.NORTH);	
	}
	
	//Panel creation for the buttons in the middle of the frame
	private void layoutMiddle(CustomerAccount myCustomer ) {
		buttonPanel = new JPanel();
		//Buttons creation and actionListener bound to them
		saleButton = new JButton("Process Sale");
		saleButton.addActionListener(this);
		buttonPanel.add(saleButton);
		returnButton = new JButton("Process Return");
		returnButton.addActionListener(this);
		buttonPanel.add(returnButton);	
			
		//Adding the button panel at the north of the middle JPanel
		add(buttonPanel,BorderLayout.CENTER);
	}
	
	//Panel for the bottom of the frame, containing transaction
	// information  and the name of the wine purchased
	private void layoutBottom(CustomerAccount myCustomer ) {
		
		bottom = new JPanel();
		bottom.setLayout(new BorderLayout());

		//Panel creation for the non- editable text fields at the bottom of the frame
		bottomPanel = new JPanel();						
		JLabel transactionLabel = new JLabel("Amount of transaction: ");
		bottomPanel.add(transactionLabel);
		transactionText = new JTextField(10);
		transactionText.setEditable(false);
		bottomPanel.add(transactionText);						
		JLabel balanceLabel = new JLabel("Initial balance: ");
		bottomPanel.add(balanceLabel);
		balanceText = new JTextField(20);
		balanceText.setEditable(false);
		bottomPanel.add(balanceText);		
		//Placing the non editable  panel on the south side of the bottom panel
		bottom.add(bottomPanel,BorderLayout.SOUTH);
				
		if (CustomerAccount.getCustomerBalance() >= 0) {
			balanceText.setText("£" + CustomerAccount.getCustomerBalance());     
			balanceText.setEditable(false);
			bottomPanel.add(balanceText);
		}
		else {
			balanceText.setText("£ CR " + Math.abs(CustomerAccount.getCustomerBalance()));
			balanceText.setEditable(false);
			bottomPanel.add(balanceText);
		}
			
		//Panel for wine purchased part
		wineBrandPanel =  new JPanel();	
		JLabel winePanelLabel = new JLabel("Wine purchased:                            ");
		wineBrandPanel.add(winePanelLabel);
		bottom.add(wineBrandPanel, BorderLayout.WEST);
		
		add(bottom, BorderLayout.SOUTH);
	}
	
	//Method to clear the wine information text fields.
	//It will be used after a transaction is done
	public void wineFieldsReset() {
		wineNameText.setText("");
		numBottlesText.setText("");
		pricePerBottleText.setText("");
		
	}
	
	
	//Method to catch the event of the user clicking on either button.
	//User will be notified with a system output which button has been clicked.
	//A wine object will be created with the input from the text fields as parameters
	//A warning message will be thrown if the data type is invalid
	public void actionPerformed(ActionEvent e) {
		
		String wineName = (wineNameText.getText()).trim();		         //EXTRACTS NAME AS STRING TRIMMED	
		String numBottlesString = numBottlesText.getText();
		String priceBottleString = pricePerBottleText.getText();
				
		//Try catch the case that bottle quantity is not a positive integer
		try {
			int numBottles = Integer.parseInt(numBottlesString);     //Extracting quantity as an integer
			if(numBottles <=0) {
				JOptionPane.showMessageDialog(null,"Enter a valid quantity of bottles");
				wineFieldsReset();
				
			}
		}
		catch (NumberFormatException nfx) {
			JOptionPane.showMessageDialog(null,"Enter a valid quantity of bottles");
			wineFieldsReset();
		}
		int numBottles = Integer.parseInt(numBottlesText.getText());         
		
		
		//Try and catch the case that the price is not a positive double
		try {
			double pricePerBottle = Double.parseDouble(priceBottleString);   //Extracting price as double
			if (pricePerBottle <= 0.0) {
				JOptionPane.showMessageDialog(null,"Enter a valid price");
				wineFieldsReset();
			}	
		}
		catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null,"Enter a valid price");
			wineFieldsReset();
		}
		double pricePerBottle = Double.parseDouble(priceBottleString);       
		

		
		//Creating a wine objects by using the information above as parameters		
		Wine myWine = new Wine(wineName, numBottles, pricePerBottle);
		
		
		//Event handling for the sale and return buttons
		if(e.getSource() == saleButton)
		{	
			//System outputs for checking that the code works
			System.err.println("You clicked 'Sales' Button.");
			System.err.println(myWine.getName() + " has been ordered");											
			System.err.println("You have ordered " + myWine.getQuantity() + " bottles.");   
			System.err.println("Price per bottle is " + myWine.getPricePer());              
			System.err.println("The transaction amount of this sale is " + CustomerAccount.saleCalc(myWine));
			System.err.println("The customer current balance is " + CustomerAccount.updateSaleBalance(myWine));
			
			//Updating the transaction amount text field
			transactionText.setText("£ " + Double.toString(CustomerAccount.saleCalc(myWine)));
			
			//Updating the current balance text field. Checking if the customer
			// is in credit or not and writes the string accordingly
			if (CustomerAccount.updateSaleBalance(myWine) > 0) {
				balanceText.setText(Double.toString(CustomerAccount.updateSaleBalance(myWine)));
			}
			else {
				balanceText.setText("£ CR " + Double.toString(Math.abs(CustomerAccount.updateSaleBalance(myWine))));
			}
			
			
			//Resets the wine information text fields
			//after the sale transaction is complete
			//and the updated balance after one sale transaction
			// becomes the initial balance for the next transaction.
			//Useful for consecutive transactions for the same customer
			wineFieldsReset();
			CustomerAccount.accHolderBalance = CustomerAccount.updateSaleBalance(myWine);
			
										
			
			//MAKE THE WINE PURCHASED WORK
			/////////////////////////
			///////////////////////
			///////////////////////
//		    winePanelLabel.setText("Wine purchased: " +  wineName);
	
			
			
						
			
		}
		else {
			//System outputs for checking that the code works
			System.err.println("You have clicked the 'Return' Button.");
			System.err.println(myWine.getName() + " has been returned back to us.");
			System.err.println("You have returned " + myWine.getQuantity() + " bottles.");
			System.err.println("Price per bottle is " + myWine.getPricePer());			
			System.err.println("The transaction amount of this return  is " + CustomerAccount.returnCalc(myWine));			
			System.err.println("The customer current balance is " + CustomerAccount.updateReturnBalance(myWine));
			
			//Updating the transaction amount text field
			transactionText.setText(Double.toString(CustomerAccount.returnCalc(myWine)));
			
			//Updating the current balance text field. Checking if the customer
			// is in credit or not and writes the string accordingly
			if (CustomerAccount.updateReturnBalance(myWine) > 0) {
				balanceText.setText(Double.toString(CustomerAccount.updateReturnBalance(myWine)));
			}
			else {
				balanceText.setText("£ CR " + Double.toString((Math.abs(CustomerAccount.updateReturnBalance(myWine)))));
			}
			
			//resets the top text fields after the transaction is complete
			//and the updated balance after one return transaction
			//becomes the initial balance for the next transaction.
			//Useful for consecutive transactions for the same customer
			wineFieldsReset();
			CustomerAccount.accHolderBalance = CustomerAccount.updateReturnBalance(myWine);
			
		}
		
	
	}	
}