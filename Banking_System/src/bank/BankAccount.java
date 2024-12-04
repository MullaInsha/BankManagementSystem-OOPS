package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankAccount implements BankOperation {
	List<BankAccount> accounts= new ArrayList<>();
    private String accountNumber;
    private String accountType;
    private String accountHolderName;
    private double balance; 

  //getter method for accountNumber
    public String getAccountNumber() {
		return accountNumber;
	}
	
    public String getAccountHolderName() {
		return accountHolderName;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	public BankAccount(String accountNumber, String accountType, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
    public void createAccount() {
    	 Scanner scanner=new Scanner(System.in);
        System.out.println("\n*** Create New Account ***");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter account type (Savings/Checking/Loan): ");
        String accountType = scanner.next();
        System.out.print("Enter account holder's name: ");
        String accountHolderName = scanner.next();
        System.out.print("Enter initial balance: $");
        double balance = scanner.nextDouble();

        BankAccount newAccount;
        if (accountType.equalsIgnoreCase("Savings")) {
        	System.out.print("Enter interest rate (as a percentage): ");
            double interestRate = scanner.nextDouble() / 100;
            newAccount = new SavingsAccount(accountNumber, accountHolderName, balance, interestRate);
          
        } else if (accountType.equalsIgnoreCase("Checking")) {
            newAccount = new CheckingAccount(accountNumber, accountHolderName, balance);
        } else if (accountType.equalsIgnoreCase("Loan")) {
            System.out.print("Enter loan amount: $");
            double loanAmount = scanner.nextDouble();
            System.out.print("Enter interest rate (as a percentage): ");
            double interestRate = scanner.nextDouble() / 100;
            System.out.print("Enter loan term (in months): ");
            int loanTerm = scanner.nextInt();
            newAccount = new LoanAccount(accountNumber, accountHolderName, loanAmount, interestRate, loanTerm);
        } else {
            System.out.println("Invalid account type. Please enter either 'Savings', 'Checking', or 'Loan'.");
            return;
        }

        accounts.add(newAccount);
        System.out.println("Account created successfully!");
    }
 

    // CheckingAccount class
    class CheckingAccount extends BankAccount {
        //private double overdraftLimit;

        public CheckingAccount(String accountNumber, String accountHolderName, double balance) {
            super(accountNumber, "Checking", accountHolderName, balance);
        }
    }
    
    private BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    public void displayAccount() {
    	Scanner scanner=new Scanner(System.in);
        System.out.println("\n*** Display Account Details ***");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        BankAccount account = findAccount(accountNumber);

        if (account != null) {
            account.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }
    public void displayAccountDetails() {
        System.out.println("\nAccount Details:");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: $" + balance);
    }
    
    public void deposit() {
    	Scanner scanner=new Scanner(System.in);
        System.out.println("\n*** Deposit Amount ***");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount deposited: $" + amount);
    }
    
    public void withdraw() {
    	Scanner scanner=new Scanner(System.in);
        System.out.println("\n*** Withdraw Amount ***");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }
    public boolean withdraw(double amount) {
        // Check if the account can be overdrawn 
        if (balance >= amount ) {
            balance -= amount;
            System.out.println("Amount withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds and overdraft limit exceeded.");
            return false;
        }
    }
    //Create a new menu option for applying interest to savings accounts:
    public void applyInterest() {
    	Scanner scanner=new Scanner(System.in);
        System.out.println("\n*** Apply Interest ***");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter the number of months to apply interest for: ");
        int months = scanner.nextInt();

        BankAccount account = findAccount(accountNumber);
        if (account instanceof SavingsAccount) {
            ((SavingsAccount) account).applyInterest(months);
        } else {
            System.out.println("Account not found or not a savings account.");
        }
    }
    
    //Create methods to display loan details and make payments toward loans
    public void displayLoanDetails() {
    	Scanner scanner=new Scanner(System.in);
        System.out.println("\n*** Display Loan Account Details ***");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();

        BankAccount account = findAccount(accountNumber);
        if (account instanceof LoanAccount) {
            ((LoanAccount) account).displayLoanDetails();
        } else {
            System.out.println("Account not found or not a loan account.");
        }
    }

    public void makeLoanPayment() {
    	Scanner scanner=new Scanner(System.in);
        System.out.println("\n*** Make Loan Payment ***");
        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();

        BankAccount account = findAccount(accountNumber);
        if (account instanceof LoanAccount) {
            System.out.print("Enter payment amount: $");
            double paymentAmount = scanner.nextDouble();
            ((LoanAccount) account).makePayment(paymentAmount);
        } else {
            System.out.println("Account not found or not a loan account.");
        }
    }
   

    
}
