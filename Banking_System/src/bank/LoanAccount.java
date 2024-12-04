package bank;

import java.util.Scanner;

class LoanAccount extends BankAccount {
    private double loanAmount;
    private double interestRate;
    private int loanTerm; // in months
    private double monthlyPayment;

    public LoanAccount(String accountNumber, String accountHolderName, double loanAmount, double interestRate, int loanTerm) {
        super(accountNumber, "Loan", accountHolderName, 0.0);
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
        calculateMonthlyPayment();
    }

    // Method to calculate the monthly payment based on loan amount, term, and interest rate
    private void calculateMonthlyPayment() {
        double monthlyInterestRate = interestRate / 12;
        monthlyPayment = (loanAmount * monthlyInterestRate) / 
                         (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));
    }

    // Method to make a payment toward the loan
    public void makePayment(double paymentAmount) {
        if (paymentAmount > monthlyPayment) {
            System.out.println("Payment is higher than the monthly payment, adjusting loan balance...");
        }

        loanAmount -= paymentAmount;
        System.out.println("Payment made: $" + paymentAmount);
        
        if (loanAmount <= 0) {
            System.out.println("Loan is fully paid off.");
        }
    }

    // Method to display loan account details
    public void displayLoanDetails() {
        System.out.println("\nLoan Account Details:");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Holder: " + getAccountHolderName());
        System.out.println("Loan Amount: $" + loanAmount);
        System.out.println("Interest Rate: " + interestRate);
        System.out.println("Loan Term: " + loanTerm + " months");
        System.out.println("Monthly Payment: $" + monthlyPayment);
    }
    
}
