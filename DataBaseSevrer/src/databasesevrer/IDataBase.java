/*
 ********************************************************************************************************************
 * IDataBase.java
 *
 * Created on January 19, 2007, 11:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 ********************************************************************************************************************
 */

package databasesevrer;

import java.sql.SQLException;

public interface IDataBase {

    // (1) function check the Admin Password if it Valid with the Admin Password that contained on the Data Base        
    public String isValidPasswrodAdmin(String User,String Pass) throws SQLException;
    // (2) function check the User Password if it Valid                                                                 
    public String isValidPasswrodUser(String userName,String Password) throws SQLException;
    // (3) check if user valid                                                                                          
    public String isValidUser(String userName) throws SQLException ;
    // (4) Insert a new User by given username and password and returns a msg if succes or not                          
    public String InsertUser(String User_Name,String Pass) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
    // (5) Delete a new User by given username and returns a msg if succes or not       
    public String deleteUser(String userName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
    
}
