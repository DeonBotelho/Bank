/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.lab5;
/**
 *
 * @author Deon
 */
public abstract class User {

    final protected String userName, type;
    final protected String password;
    /*Requires: String field <userName>, String field <password>,String field<tyoe>
    //Modifies: this
    //Effects: Creates User-child object represented by <userName> and <password> 
               with an attached 'type' which represents the child instance type
     */

    User(String userName, String password, String type) {
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    /*Effects:  Returns the user name associated with user-child object
     */
    public String getUserName() {
        return userName;
    }
    /*Effects:  Returns type representation of user instance
     */
    public String getType() {
        return type;
    }

    /*Effects:  Displays current account details for an account
     */
    public abstract void viewAccountDetails();

    /*Modifies: user-child's instance variables as specified by each child
    //Effects:  Transfer funds for a user-child object
     */
    public abstract void transfer();

}
