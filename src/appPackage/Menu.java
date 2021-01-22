/**
 * 
 */
package appPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author amyquinn
 *
 */
public class Menu {

	
	//instance variables 

	
	Bank bank = new Bank();
	
	boolean exit;
	
	/**
	 * @param Scanner keyboard
	 * @param Bank bank
	 * @throws InvalidAccountTypeException 
	 */
	public static void main(String[] args) throws InvalidAccountTypeException {
	
		Menu menu = new Menu();
		menu.runMenu();
	}
	
	
	public void runMenu() throws InvalidAccountTypeException {
		
		printHeader();
		
		while(!exit) {
			printMenu();
			int choice = getInput();
			performAction(choice);

		}
	}


	private void printHeader() {
	
		System.out.println(" -----------------------------");
		System.out.println("|       Welcome to Amy's      |");
		System.out.println("|       Awesome Bank App      |");
		System.out.println(" -----------------------------");
		
	}
	
	private void printMenu() {
		
		displayHeader("Please make a selection");
		
		System.out.println("\t1) Create a New Account");
		System.out.println("\t2) Make a deposit");
		System.out.println("\t3) Make a withdrawl");
		System.out.println("\t4) List account balance");
		System.out.println("\t0) Exit");
		
	}
	

	private int getInput() {
		int choice = -1;
	    Scanner keyboard = new Scanner(System.in);
		do {
			System.out.println("Enter your choice: ");
			try {
				choice = Integer.parseInt(keyboard.nextLine());
				

			}catch(NumberFormatException e) {
				System.out.println("Invalid selection, numbers only please.");
			}
			if(choice< 0 || choice > 4) {
				System.out.println("Choice outside of range, please choose again.");
			}
			
		}while(choice< 0 || choice > 4);
	
		return choice;

	}
	
	private void performAction(int choice) throws InvalidAccountTypeException {
		
		switch(choice) {
		case 0:
			System.out.println("Thank you for using our application.");
			System.exit(0);
			break;
		case 1:
			createAccount();
			break;
		case 2: 
			makeDeposit();
			break;
		case 3:
			makeWithdrawl();
			break;
		case 4:
			listBalance();
			break;
			default:
				System.out.println("Unknown error has occured.");
		}
	}
	

	private String askQuestion(String question, List<String> accountTypes) {
		
		String response = "";
		Scanner input = new Scanner(System.in);
		boolean choices = ((accountTypes == null) || accountTypes.size() == 0) ? false : true;
		
		boolean firstRun = true;
		do {
			if(!firstRun) {
				System.out.println("Invalid selection, please try again.");
			}
			System.out.print(question);
			if(choices) {
				System.out.print("(");
				for(int i =0; i< accountTypes.size()-1; ++i) {
					System.out.print(accountTypes.get(i) + " ");
				}
				System.out.print(accountTypes.get(accountTypes.size()-1));
				System.out.print(") : ");
			}
			response = input.nextLine();
		
			firstRun = false;
			if(!choices) {
				break;
			}
		
		}while(!accountTypes.contains(response));
	
		return response;
	}
	
	private double getDeposit(String accountType) {
		double initialDeposit = 0;
		boolean valid = false;
		while(!valid) {
			System.out.println("Please enter an initial deposit");
			
			try {
			    Scanner keyboard = new Scanner(System.in);
				initialDeposit = Double.parseDouble(keyboard.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Deposit must be a number");
			}
			if(accountType.equalsIgnoreCase("checking")) {
				if(initialDeposit< 100) {
					System.out.println("Checking accounts require a Minimum deposit of 100");
				}else {
					valid = true;
				}
			}
			else if(accountType.equalsIgnoreCase("savings")) {
				if(initialDeposit< 50) {
					System.out.println("Savings accounts require a Minimum deposit of 50");
				}else {
					valid = true;
				}
			}
		}
		return initialDeposit;
	}

	
	private void createAccount() throws InvalidAccountTypeException {
		
		displayHeader("Create an Account");
		
		//get account information
		
		String accountType = askQuestion("Please enter an account type: ",Arrays.asList("checking", "savings"));
		String firstName = askQuestion("Please enter your first name: ", null);
		String lastName = askQuestion("Please enter your last name: ", null);
		String SSN = askQuestion("Please enter your Social Security Number: ", null);
	
		double initialDeposit = getDeposit(accountType);
		
		//we can now create an account as we have obtained all the necessary details to do so
		//add the customer to the Bank customer array list
		
		Account account;
		if(accountType.equalsIgnoreCase("checking")) {
			account = new Checking(initialDeposit);
		}else if(accountType.equalsIgnoreCase("savings")){
			account = new Savings(initialDeposit);
		}
		else {
			throw new InvalidAccountTypeException();
		}
		Customer customer = new Customer(firstName, lastName, SSN, account);
		bank.addCustomer(customer);
	}
	
	
	private double getAmount(String question) {
		System.out.println(question);
		double amount = 0;
		try {
		    Scanner keyboard = new Scanner(System.in);
			amount = Double.parseDouble(keyboard.nextLine());
			
		}catch(NumberFormatException e) {
			amount = 0;
		}
		return amount;
	}
	

	private void makeDeposit() {
		  displayHeader("Make a Deposit");
			int account = selectAccount();
			if(account >= 0) {
				double amount = getAmount("How much would you like to deposit?: ");	
				bank.getCustomer(account).getAccount().deposit(amount);
				}
			}
	
	private void makeWithdrawl() {
		displayHeader("Make a Withdrawl");
		int account = selectAccount();
		if(account >= 0) {
			double amount = getAmount("How much would you like to withdraw?: ");
			bank.getCustomer(account).getAccount().withdraw(amount);
		}
	}
	
	private void listBalance() {
		displayHeader("List Account Details");
		int account = selectAccount();
		if(account >= 0) {	
			displayHeader(bank.getCustomer(account).basicInfo());
			System.out.println(bank.getCustomer(account).getAccount());
		}
	}
	
	private int selectAccount() {
		ArrayList<Customer> customers = bank.getCustomers();

			if(customers.size()<= 0) {
				System.out.println("\nNo customers at your bank!");
				return -1;
			}
		
			
				System.out.println("Select an account: ");
				for(int i =0; i< customers.size(); i++) {
					System.out.println("\t" + (i + 1) + ") " +  customers.get(i).basicInfo());
				}
				
				int account;
				System.out.println("\nPlease enter your selection: ");
				
				try {
				    Scanner keyboard = new Scanner(System.in);
					account = Integer.parseInt(keyboard.nextLine()) - 1;
					
				}catch(NumberFormatException e) {
					account = -1;
				}
				
				if(account < 0 || account > customers.size()) {
					System.out.println("Invalid account selected");
					account = -1;
				}
				return account;
			}
	
	private void displayHeader(String message) {
		System.out.println();
		int width = message.length() + 6;
		StringBuilder sb = new StringBuilder();
		sb.append("+");
		for(int i =0; i< width; i++) {
			sb.append("-");
		}
		sb.append("+");
		System.out.println(sb.toString());
		System.out.println("|   " + message + "   |");
		System.out.println(sb.toString());
		
	}

}
