/*******************************************************************************
Name: Sarah Redmon
Date: 3/19/19
Instructor: Ms. Tucker
Class: Customer
Purpose: Does the background work for encryption & decryption for passwords, & allowing access to the variables
*******************************************************************************
*/

public class Customer implements Encryptable
{
    //-----------------------------------------------------------------
    // Translates alphabet letters to sub letters for encryption, & sub letters to alphabet letters for decryption
    //-----------------------------------------------------------------
    private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H",
    "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
    "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
    "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
    "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    
    private String[] sub = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O",
    "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", 
    "B", "N", "M", "q", "w", "e", "r", "t", "y", "u", "i", "o",
    "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", 
    "b", "n", "m", "7", "8", "9", "4", "5", "6", "1", "2", "3", "0"};
    
    //-----------------------------------------------------------------
    // Initializes data members
    //-----------------------------------------------------------------
    private String customerID, lastName, firstName, emailAddress, userName, password;
    private float bankBalance;
    private boolean encrypted;
    
    //-----------------------------------------------------------------
    // Default Constructor
    //-----------------------------------------------------------------
    public Customer()
    {
        this.customerID = "";
        this.lastName = "";
        this.firstName = "";
        this.emailAddress = "";
        this.userName = "";
        this.bankBalance = 0.0f;
        this.password = "";
    }
    
    //-----------------------------------------------------------------
    // Constructor that sets the variables equal to the data members; calls encrypt() to encrypt password
    //-----------------------------------------------------------------
    public Customer(String customerID, String lastName, String firstName, 
    String emailAddress, String userName, float bankBalance, String password)
    {
        this.customerID = customerID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.userName = userName;
        this.bankBalance = bankBalance;
        this.password = password;
        
        encrypt();
    }
    
    //-----------------------------------------------------------------
    // Accessor methods for data members
    //-----------------------------------------------------------------
    public String getCustomerID() {
        return customerID;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public float getBankBalance() {
        return bankBalance;
    }
    
    public String getPassword() {
        return password;
    }
    
    //-----------------------------------------------------------------
    // Encrypt & decrypt methods for password
    //-----------------------------------------------------------------
    public void encrypt()
    {
        String tempPassword = "";
        String b;
        if (!encrypted) {
            for (int a = 0; a < password.length(); a++) {
                for (int i = 0; i < alphabet.length; i++) {
                    if (alphabet[i].equals(String.valueOf(password.charAt(a)))){
                        b = sub[i];
                        tempPassword = tempPassword.concat(b);
                        encrypted = true;
                    }
                }
            }
            password = tempPassword;
        }
    }
    
    public String decrypt()
    {
        String tempPassword = "";
        String c;
        if (encrypted) {
            for (int a = 0; a < password.length(); a++) {
                for (int i = 0; i < sub.length; i++) {
                    if (sub[i].equals(String.valueOf(password.charAt(a)))) {
                        c = alphabet[i];
                        tempPassword = tempPassword.concat(c);
                        encrypted = false;
                    }
                }
            }
        }
        return tempPassword;
    }
    
    //-----------------------------------------------------------------
    // Boolean method for password to encrypt/decrypt
    //-----------------------------------------------------------------
    public boolean isEncrypted()
    {
        return encrypted;
    }
    
    //-----------------------------------------------------------------
    // ToString method to return information needed (not password because that’s sensitive information)
    //-----------------------------------------------------------------
    public String toString()
    {
        return customerID + firstName + lastName;
    }
}