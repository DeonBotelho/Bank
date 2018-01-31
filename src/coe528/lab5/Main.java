package coe528.lab5;

import java.io.IOException;
import java.util.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Deon
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    private static Scanner s;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
      
        //FileEditor.deleteFile();
        Manager m = Manager.online();
        m.getCustomersFromfile();
        
        s = new Scanner(System.in);
        while (true) {
            
            System.out.println("Please Log in ");
            System.out.println("Username:Terminate \nPassword:Terminate\n will terminate program.");
            String user = " ",pw=" ";
            try{
            System.out.print("User Name:");
            user = s.next();
            System.out.print("Password :");
            pw = s.next();
            }
            catch (Exception e)
            {
                //break;
            }
            if (user.equalsIgnoreCase("Admin") && pw.equalsIgnoreCase("Admin")) {
                //Manager menu
                managerMenu(m);

            }
            else if(user.equalsIgnoreCase("Terminate") && pw.equalsIgnoreCase("Terminate")) {
                break;
            
            }
            else {
                //Customer menu
                Customer c = Manager.authenticate(user, pw);

                if (c != null) {
                    customerMenu(c);
                } else {
                    System.out.println("Invalid Username or password");

                }

            }
            
        }
    }

    public static void customerMenu(Customer c) {
        // Scanner s = new Scanner(System.in);
        boolean flag = true;
        String choice = "E";
        while (flag) {
            System.out.println("-------------Main Menu-------------");
            System.out.println("Please choose one of the follow options:");
            System.out.println("<T> Transfer funds between your checkings and savings account");
            System.out.println("<V> View current acccount details. ");
            System.out.println("<E> logout.");
           if (s.hasNext()) {
                choice = s.next();
            }

            switch (choice) {
                case "t":
                case "T":
                    c.transfer();
                    break;
                case "v":
                case "V":
                    c.viewAccountDetails();
                    break;
                case "e":
                case "E":
                    flag = false;
                    break;
            }
        }
        
    }

    public static void managerMenu(Manager m) throws IOException {
        //  Scanner s = new Scanner(System.in);
        String choice = "E";
        boolean flag = true;
        while (flag) {
            System.out.println("-------------Main Menu-------------");
            System.out.println("Please choose one of the follow options:");
            System.out.println("<A> Add a new customer");
            System.out.println("<D> Delete a current customer");
            System.out.println("<T> Transfer funds between customers checkings and savings account");
            System.out.println("<V> View current acccount details of a customer. ");
            System.out.println("<E> Logout");

            if (s.hasNext()) {
                choice = s.next();
            }

            switch (choice) {
                case "a":
                case "A":
                    m.addCustomer();
                    break;
                case "d":
                case "D":
                    m.deleteCustomer();
                    break;
                case "t":
                case "T":
                    m.transfer();
                    break;
                case "v":
                case "V":
                    m.viewAccountDetails();
                    break;
                case "e":
                case "E":
                    flag = false;
                    break;

            }

        }
        
    }
}
