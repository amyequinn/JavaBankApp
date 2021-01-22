package appPackage;

public class Customer {

	private String firstName;
	private String lastName;
	private String SSN;
	private Account account;
	
	public Customer(String firstName, String lastName, String SSN, Account account) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.SSN = SSN;
		this.account = account;
	}

	public String basicInfo() {
		return 
				" Account Number: " + account.getAccountNum() +" - Name: " + firstName + " " + lastName;
}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @return the sSN
	 */
	public String getSSN() {
		return SSN;
	}

	Account getAccount() {
		return account;
	}

}
