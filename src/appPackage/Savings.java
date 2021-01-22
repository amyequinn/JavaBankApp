package appPackage;

public class Savings extends Account{

	
	private static String accountType = "Savings";
	
	public Savings(double initialDeposit) {
		this.setBalance(initialDeposit);
		this.checkInterest(0);
	}


	@Override
	public String toString() {
		return " Account Type: " + accountType + " Account\n" +
			   " Account Number: " + this.getAccountNum() + "\n" + 
			   " Balance: " + this.getBalance() + "\n" +
			   " Interest Rate: " + this.getInterest() + "%\n";
	}
	

	

	
	
	
}
