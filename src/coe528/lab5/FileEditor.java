/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author Deon
 */
public class FileEditor {

    private static final String admin = "Admin Admin 0 0";
    private static final File file = new File("Accounts.txt");
    private static FileWriter writer;
    private static ArrayList<String> customers = new ArrayList<>();
   
    private static boolean firstInstance = false;

    public static void openFile() throws IOException {
        file.delete();
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            writer = new FileWriter(file);
        } catch (IOException ex) {
            System.out.print(ex);
        }
        if (!firstInstance) {
            customers.add(0, admin);
            firstInstance = true;
        }
        writer.write(admin + "\n");

    }

    public static void addToFile(String user) throws IOException {
        customers.add(user);
        refresh();
    }

    private static void refresh() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        if (!customers.get(0).equals(admin)) {
            customers.add(0, admin);
            
        }
        
        
        try{
        writer.close();
        }
        catch(Exception e)
        {}
        
        writer = new FileWriter(file);

        for (String s : customers) {

            writer.write(s + "\n");
        }
        writer.close();
    }

    public static void deleteFromFile(String user) throws IOException {

        try {
            writer.close();
        } catch (Exception e) {
            //writer = new FileWriter(file);
        }
        String delete = findInFile(user);
        if (delete != null) {
            customers.remove(delete);
        }
        writer = new FileWriter(file);

        for (String s : customers) {
            writer.write(s + "\n");
        }

    }

  

    public static void editCustomer(String user, String pw, double check, double save) throws IOException {
        for (String c : customers) {
            System.out.println("This:" + c);
            if (c.contains(user) && c.contains(pw)) {
                
                int index =  customers.indexOf(c);
                customers.remove(c);
                c = user + " " + pw + " " + check + " " + save;
                
                customers.add(index,c);
                System.out.println("THat:" + c);
                break;
            }
        }
        refresh();
    }

    public static ArrayList getCustomersFromFile() throws FileNotFoundException, IOException {
        ArrayList<Customer> c = new ArrayList<>();
        
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        String name, password, checking, savings;
        double check = -1, save = -1;

        while (s.hasNext()) {
            try {
                name = s.next();
                password = s.next();
                checking = s.next();
                savings = s.next();
            } catch (Exception e) {
                break;
            }

            if (isDouble(checking)) {
                check = Double.valueOf(checking);
            } else {
                System.out.println("File has been altered");
                file.delete();
            }
            if (isDouble(savings)) {
                save = Double.valueOf(savings);
            } else {
                System.out.println("File has been altered");
                file.delete();
            }
            System.out.println(name + password + check + save);
            if (!name.equalsIgnoreCase("Admin")) {
                c.add(new Customer(name, password, check, save));
                customers.add(name + " " + password + " " + checking + " " + savings);
            }

        }
        customers.add(0,admin);
        return c;
        
    }

    private static boolean isDouble(String s) {
        try {
            double d = Double.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    private static String findInFile(String user) throws FileNotFoundException, IOException {
        if (!file.exists()) {
            openFile();
        }
        Scanner s = new Scanner(file);
        String c = null;
        while (s.hasNextLine()) {
            c = s.nextLine();
            if (c.contains(user)) {
                return c;
            }
        }
        s.close();

        return c;

    }

    public static void closeFile() throws IOException {
        writer.close();
    }

}
