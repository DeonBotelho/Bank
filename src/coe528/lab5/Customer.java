/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

import java.util.Scanner;

/**
 *
 * @author Deon
 */
public class Customer extends User {

    
    //Overview: Customer class object represents a real customer with the ability to
    //transfer funds in an exsisting account and view account details.
    
    
    final static double minOpeningBalance = 20;
    private double checkingBalance = -1, savingsBalance = -1;

    /*Requires: String field <userName>, String field <password>,
              Double values for both <checkingOpeningBalance> and <savingsOpeningBalance>
    //Modifies: this
    //Effects: Creates Customer object represented by <userName> and <password> 
               with a checkings and savings opening Balance equal to the amount specified
     */
    Customer(String userName, String password, double checkingOpeningBalance, double savingsOpeningBalance) {
        super(userName, password, "Customer");

       
         checkingBalance = checkingOpeningBalance;
            savingsBalance = savingsOpeningBalance;
    }

    /*Requires: String field <userName>, String field <password>, and
              Double value <checkingOpeningBalance> 
    //Modifies: this
    //Effects: Creates Customer object represented by <userName> and <password> 
               with a checkings opening Balance equal to the amount specified   */
    Customer(String userName, String password, double checkingOpeningBalance) {
        super(userName, password, "Customer");

       
         checkingBalance = checkingOpeningBalance;
         savingsBalance = -1;

    }

    //Effects: returns current checking balance
    public double getCheckingBalance() {
        return checkingBalance;
    }

    //Effects: returns current savings balance
    public double getSavingsBalance() {
        return savingsBalance;
    }

    /*Effects:  Displays current account details. ie. current savings and/or savings balance
     */
    @Override
    public void viewAccountDetails() {
        if (checkingBalance > 0) {
            System.out.println("Current Checking Balance: $" + checkingBalance);
        } else {
            System.out.println("You do not currently have a Checking Account open");
        }
        if (savingsBalance > 0) {
            System.out.println("Current Savings  Balance: $" + savingsBalance);
        } else {
            System.out.println("You do not currently have a Savings Account open");
        }
        
    }

    @Override
    //Modifies: this
    //Effects : Asks user for input for transfer
    public void transfer() {
        Scanner in = new Scanner(System.in);
        double amount = 0;
        String x, choice;
        boolean flag = false;

        if (savingsBalance >= 0 && checkingBalance >= 0) {
            while (!flag) {
                System.out.println("Please Select one of the follow options:");
                System.out.println("<1> Transfer money from your Checking account into your Savings account.");
                System.out.println("<2> Transfer money from your Savings account into your Checking account.");
                System.out.println("Enter <!> to get back to main menu");
                choice = in.next();

                if (choice.equals("1")) {
                    amount = tryTransfer();
                    if (checkingBalance >= amount && amount != 0) {
                        checkingBalance -= amount;
                        savingsBalance += amount;
                        flag = true;

                    } else if (checkingBalance < amount) {
                        System.out.println("Insufficient funds");
                    }
                } else if (choice.equals("2")) {
                    amount = tryTransfer();
                    if (savingsBalance >= amount && amount != 0) {
                        savingsBalance -= amount;
                        checkingBalance += amount;
                        flag = true;

                    } else if (savingsBalance < amount) {
                        System.out.println("Insufficient funds");
                    }
                } else if (choice.equals("!")) {
                    flag = true;

                } else {
                    System.out.println("Not a valid choice");

                }

            }
            try{
            System.out.println(this.checkingBalance + " "+ this.savingsBalance);
            FileEditor.editCustomer(userName,password,checkingBalance,savingsBalance);
            
            }
            catch(Exception e)
            {
                System.out.println("No change can be made to offical records");
            }
            viewAccountDetails();
            
        }
        else
        {
            System.out.print("This function is not currently open to you because ");
            System.out.println("you do not currently have a savings acccount open");
        }
    
    }

    /*Modifies: this 
    //Effects: Takes user input and checks if it is valid, if valid transfer goes through
                if not valid a error message is displayed
     */
    private double tryTransfer() {
        Scanner in = new Scanner(System.in);
        double amount = 0;
        System.out.println("Please enter the amount you would like to transfer");
        System.out.print("$");
        String value = in.next();

        try {
            amount = Double.valueOf(value);
            if (amount < 0) {
                System.out.println("\n<<Invalid entree :" + value + ">>\n");
                return 0;
            }

        } catch (NumberFormatException e) {
            System.out.println("\n<<Invalid entree :" + value + ">>\n");
            return 0;
        }
        return amount;
    }

    /*Requires: String username and String password to check if it matches for this
    //Effects: Returns this instance of the customer object
     */
    public Customer authenticate(String username, String password) {
        if (username.equals(userName) && password.equals(this.password)) {
            return this;
        }
        return null;
                
    }
}
