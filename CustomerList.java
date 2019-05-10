/*******************************************************************************
Name: Sarah Redmon
Date: 3/19/19
Instructor: Ms. Tucker
Class: CustomerList
Purpose: To display a report based on the info from Customer2.csv
*******************************************************************************
*/

/*----------------------------------------------------------------------------
	FEEDBACK FROM INSTRUCTOR:
	Excellent work, Sarah.  Thanks for sticking with this.
-----------------------------------------------------------------------------*/

import java.io.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CustomerList extends Customer
{
    public static void main(String args[]) throws IOException, InvalidPWException {
        //-----------------------------------------------------------------
        // Initializes variables & scanner
        //-----------------------------------------------------------------
        Scanner scan = new Scanner (System.in);
        
        String customerID2 = "", lastName2 = "", firstName2 = "", email2 = "", userName2 = "", password2 = "";
        float bankBalance2 = 0.0f;
        String string, error = "";
        int digit = 0, shortCount = 0, noDigitCount = 0, bothCount = 0;
        
        //-----------------------------------------------------------------
        // Sets up date to be shown in report
        //-----------------------------------------------------------------
        System.out.print("DATE: ");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");  
        System.out.println(formatter.format(date));
        System.out.println();
        
        //-----------------------------------------------------------------
        // Sets up heading in report
        //-----------------------------------------------------------------
        System.out.printf("%-30.30s  %-30.30s %-30.30s %-30.30s %-30.30s %-40.40s%n ",
        "CUSTOMER ID", "FIRST NAME", "LAST NAME", "ENCRYPTED", "DECRYPTED", 
        "ERROR (if any)");
        System.out.println();
        
        //-----------------------------------------------------------------
        // Reads the file and picks out all of the info needed to put into 
        // the file & puts it into a Customer object
        //-----------------------------------------------------------------
        Scanner fileScan, lineScan;
        fileScan = new Scanner(new File("Customer2.csv"));
        
        while (fileScan.hasNext()) {
            string = fileScan.nextLine();
                
            lineScan = new Scanner(string);
	    lineScan.useDelimiter(",");
            
            customerID2 = lineScan.next();
            lastName2 = lineScan.next();
            firstName2 = lineScan.next();
            email2 = lineScan.next();
            userName2 = lineScan.next();
            bankBalance2 = lineScan.nextFloat();
            password2 = lineScan.next();
            
            Customer bankCustomer =  new Customer(customerID2, lastName2, firstName2, email2, userName2, bankBalance2, password2);
            try {
                //-----------------------------------------------------------------
                // Counts the number of digits of the password
                //-----------------------------------------------------------------
                digit = 0;
                error = "";
                for (int i = 0; i < password2.length(); i++) {
                    if (Character.isDigit(password2.charAt(i))) {
                        digit++;
                    }
                }
                
                //-----------------------------------------------------------------
                // * If the password is less than 10 characters & digit is 
                // equal to 0, add 1 to bothCount & throw an InvalidPWException 
                // that says so
                //
                // * Else if just the password is less than 10 characters, 
                // add 1 to short count & throw an InvalidPWException that 
                // says so
                //
                // * Else if just the digit is equal to 0, add 1 to 
                // noDigitCount & throw an InvalidPWException that says so
                //
                // * Else just leave error empty
                //-----------------------------------------------------------------
                if ((password2.length() < 10) && (digit == 0)) {
                    bothCount += 1;
                    throw new InvalidPWException("Password not 10 characters & not complex");
                }
                else if (password2.length() < 10) {
                    shortCount += 1;
                    throw new InvalidPWException("Password not 10 characters");
                }
                else if (digit == 0) {
                    noDigitCount += 1;
                    throw new InvalidPWException("Password not complex");
                }
                else {
                    error = " ";
                }
                
                //-----------------------------------------------------------------
                // Display the data in the report
                //-----------------------------------------------------------------
                System.out.printf("%-30.30s  %-30.30s %-30.30s %-30.30s %-30.30s %-30.40s%n ", 
                bankCustomer.getCustomerID(), bankCustomer.getLastName(), 
                bankCustomer.getFirstName(), bankCustomer.getPassword(), 
                bankCustomer.decrypt(), error);
            } catch (InvalidPWException message) {
                //-----------------------------------------------------------------
                // Catch the error & display it in the error space in the report
                //-----------------------------------------------------------------
                if (password2.length() < 10) {
                    error = message.getMessage();
                }
                else if (digit == 0) {
                    error = message.getMessage();
                }
                else if ((password2.length() < 10) && (digit == 0)) {
                    error = message.getMessage();
                }
                
                //-----------------------------------------------------------------
                // Display the data in the report
                //-----------------------------------------------------------------
                System.out.printf("%-30.30s  %-30.30s %-30.30s %-30.30s %-30.30s %-30.40s%n ", 
                bankCustomer.getCustomerID(), bankCustomer.getLastName(), 
                bankCustomer.getFirstName(), bankCustomer.getPassword(), 
                bankCustomer.decrypt(), error);
            }
        }
        //-----------------------------------------------------------------
        // Lists the number of errors for each type of error
        //-----------------------------------------------------------------
        System.out.println();
        System.out.println("----------NUMBER OF ERRORS----------");
        System.out.println("PASSWORD NOT LONG ENOUGH: " + shortCount);
        System.out.println("PASSWORD NOT COMPLEX ENOUGH: " + noDigitCount);
        System.out.println("PASSWORD NOT LONG NOR COMPLEX ENOUGH: " + bothCount);
    }
}
