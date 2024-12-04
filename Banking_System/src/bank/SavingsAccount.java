package bank;

//SavingsAccount class
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance, double interestRate) {
        super(accountNumber, "Savings", accountHolderName, balance);
        this.interestRate = interestRate;
    }

    // Method to calculate and apply interest to the balance
    public void applyInterest(int months) {
        // Calculate monthly interest rate from annual interest rate
        double monthlyInterestRate = interestRate / 12;
        
        // Calculate interest for the given number of months
        double interest = getBalance() * monthlyInterestRate * months;
        
        // Add interest to the account balance
        setBalance(getBalance()+interest);
        
        
        // Print the details of the interest calculation for verification
        System.out.println("Interest applied for " + months + " months:");
        System.out.println("Initial balance: $" + (getBalance() - interest));
        System.out.println("Interest rate: " + (interestRate * 100) + "% annually");
        System.out.println("Monthly interest rate: " + (monthlyInterestRate * 100) + "%");
        System.out.println("Interest applied: $" + interest);
        System.out.println("New balance after applying interest: $" + getBalance());
    }

    // Getter for the interest rate
    public double getInterestRate() {
        return interestRate;
    }

    // Setter for the interest rate
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}

