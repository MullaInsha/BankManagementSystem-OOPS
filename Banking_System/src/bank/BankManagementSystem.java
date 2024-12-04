package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankManagementSystem {
	public static void main(String[] args) {		 
		  		    
        BankManagementSystem system = new BankManagementSystem();
        system.run();
                
    }
	 public void run() {
         BankAccount b=new BankAccount(null, null, null, 0);
		 Scanner scanner=new Scanner(System.in);
		 
	        int choice;
	        do {
	        	System.out.println("\n*** Banking System Application ***");
	            System.out.println("1. Create new account");
	            System.out.println("2. Display account details");
	            System.out.println("3. Deposit amount");
	            System.out.println("4. Withdraw amount");
	            System.out.println("5. Apply interest to savings account");
	            System.out.println("6. Display loan account details");
	            System.out.println("7. Make a loan payment");
	            System.out.println("8. Exit");
	            System.out.print("Enter your choice: ");
	            
	            choice = scanner.nextInt();
	            
	            switch (choice) {
	                case 1:
	                    b.createAccount();
	                    break;
	                case 2:
	                    b.displayAccount();
	                    break;
	                case 3:
	                    b.deposit();
	                    break;
	                case 4:
	                    b.withdraw();
	                    break;
	                case 5:
	                    b.applyInterest();
	                    break;
	                case 6:
	                    b.displayLoanDetails();
	                    break;
	                case 7:
	                    b.makeLoanPayment();
	                    break;
	                case 8:
	                    System.out.println("Exiting the application...");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while (choice != 8);
	    }

}
