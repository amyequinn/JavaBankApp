package appPackage;

public class Account {

	private double balance = 0;
	private double interest = 0.02;
	private int accountNum;
	private static int numberOfAccounts = 1000000;
	
	Account(){
		accountNum = numberOfAccounts++;
	}
	
	
	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return the interest
	 */
	public double getInterest() {
		return interest * 100;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @param interest the interest to set
	 */
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	/**
	 * @return the accountNum
	 */
	public int getAccountNum() {
		return accountNum;
	}
	
	
	public void withdraw(double amount) {

		if(amount + 5 > balance) {
			System.out.println("You have insufficient funds.");
			return;
		}   
			balance -= amount + 5;
			checkInterest(0);
			System.out.println("You have withdrawn " + amount + "pounds and incurred a fee of £5");
			System.out.printf("You now have a balance of £%.2f ", balance);
	}
	
	public void deposit(double amount) {

		if(amount <= 0){
			System.out.println("You cannot deposit no money.");
			return;
		}

		checkInterest(amount);
		amount = amount + amount * interest;
		balance += amount;
		System.out.println("You have deposited £" + amount + " with an interest rate of " + (interest*100) + "%");
		System.out.printf("You now have a balance of £ %.2f", balance);
	
	}
	
	public void checkInterest(double amount) {
		if(balance + amount > 10000) {
			interest = 0.05;
		}else {
			interest = 0.02;
		}
	}


	@Override
	public String toString() {
		return "Account [balance=" + balance + ", interest=" + interest + ", accountNum=" + accountNum + "]";
	}

}










