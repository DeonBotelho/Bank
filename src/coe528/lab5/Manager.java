/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Deon
 */
public class Manager extends User {

    private final static ArrayList<Customer> c = new ArrayList<>();
    private Scanner s;
    private static final String userName = "Admin", password = "Admin";

    private static String name, pw, checking, savings;

    private Manager(String userName, String password) {
        super(userName, password, "Manager");
    }

    public static Manager online() {
        return holder.m;
    }

    private static class holder {

        private static final Manager m = new Manager(userName, password);

    }

    public void addCustomer() throws IOException {
        FileEditor.openFile();
        s = new Scanner(System.in);
        boolean flag = false;
        System.out.print("Please Enter a User name :");
        name = s.next();
        System.out.print("Please Enter a Password  :");
        pw = s.next();
        checking = "-1";
        while (!flag) {
            System.out.println("Please Enter the starting balance for your checking account. ");
            System.out.println("Please not the minimum balance allowed is " + Customer.minOpeningBalance);
            System.out.print("$");
            checking = s.next();
            flag = isDouble(checking);
            if (!flag) {
                System.out.println("Invalid Entree.");
            } else {
                if (Double.valueOf(checking) < Customer.minOpeningBalance) {
                    System.out.println("Opening checking balance must be greater than $" + Customer.minOpeningBalance + ".");
                    flag = false;
                }
            }

        }
        savings = "-1";
        Outer:
        while (true) {
            System.out.println("Would you like to open a savings account as well? <Y/N/>");
            String choice = s.next();
            if (choice.equalsIgnoreCase("y")) {
                flag = false;
                while (!flag) {
                    System.out.println("Please Enter the starting balance for your savings account. ");
                    System.out.println("Please not the minimum balance allowed is " + Customer.minOpeningBalance);
                    System.out.print("$");
                    savings = s.next();
                    flag = isDouble(savings);
                    if (!flag) {
                        System.out.println("Invalid Entree.");
                    } else {
                        if (Double.valueOf(savings) < Customer.minOpeningBalance) {
                            System.out.println("Opening savings balance must be greater than $" + Customer.minOpeningBalance + ".");
                            flag = false;
                        } else {
                            break Outer;
                        }
                    }
                }
            } else if (choice.equalsIgnoreCase("n")) {
                break;

            } else {
                System.out.println("Invalid Entree");
            }
        }

        System.out.println("filewrite");
        if (savings.equalsIgnoreCase("-1")) {
            c.add(new Customer(name, pw, Double.valueOf(checking)));
            String x = name + " " + pw + " " + Double.valueOf(checking) + " " + Double.valueOf(-1.0);
            FileEditor.addToFile(x);
            System.out.println(x);
        } else {

            c.add(new Customer(name, pw, Double.valueOf(checking), Double.valueOf(savings)));
            String x = name + " " + pw + " " + Double.valueOf(checking) + " " + Double.valueOf(savings);
            FileEditor.addToFile(x);
            System.out.println(x);
        }
        FileEditor.closeFile();
    }

    public void deleteCustomer() throws IOException {
        s = new Scanner(System.in);

        System.out.println("Please Enter username to be deleted");
        name = s.next();
        // FileEditor.openFile();
        FileEditor.deleteFromFile(name);
        for (Customer c : this.c) {
            if (c.userName.equalsIgnoreCase(name)) {
                this.c.remove(c);
                break;
            }
        }

        FileEditor.closeFile();

    }

    public static Customer authenticate(String username, String password) {
        Customer returnThis;
                name = username;
        pw = password;

        Customer exsits;//= authenticate(u,p);
        for (Customer customer : c) {
            exsits = customer.authenticate(name, pw);
            if (exsits != (null)) {
                return exsits;
                
            }
        }
        return null;

    }

    /**
     *
     */
    @Override
    public void viewAccountDetails() {
        s = new Scanner(System.in);
        System.out.println("Please Enter Customer user name and password");
        System.out.print("User name: ");
        name = s.next();
        System.out.print("Password : ");
        pw = s.next();

        Customer exsits;//= authenticate(u,p);
        for (Customer customer : c) {
            exsits = customer.authenticate(name, pw);
            if (exsits != (null)) {
                exsits.viewAccountDetails();
                break;
            }
        }

    }

    /**
     *
     */
    @Override
    public void transfer() {
        s = new Scanner(System.in);
        System.out.println("Please Enter Customer user name and password");
        System.out.print("User name: ");
        name = s.next();
        System.out.print("Password : ");
        pw = s.next();

        Customer exsits;//= authenticate(u,p);
        for (Customer customer : c) {
            exsits = customer.authenticate(name, pw);
            if (exsits != (null)) {
                exsits.transfer();
                break;
            }
        }
    }

    public void getCustomersFromfile() throws FileNotFoundException, IOException {
        ArrayList<Customer> x = FileEditor.getCustomersFromFile();
        for (Customer a : x) {
            c.add(new Customer(a.userName, a.password, a.getCheckingBalance(), a.getSavingsBalance()));
            
            System.out.println(a.userName + " " + a.password + " " + a.getCheckingBalance() + " " + a.getSavingsBalance());
        }
    }

    private boolean isDouble(String s) {
        try {
            double d = Double.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

}
