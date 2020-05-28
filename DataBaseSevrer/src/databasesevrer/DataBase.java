/*
 ********************************************************************************************************************
 * DataBase.java                                                                                                    *
 * Created on January 19, 2007, 11:24 PM                                                                            *
 * this class conatain the function that talking to the databas directly                                            *
 * 5 functions :                                                                                                    *
 * (1) function check the Admin Password if it Valid with the Admin Password that contained on the Data Base        *
 * (2) function check the User Password if it Valid                                                                 *
 * (3) check if user valid                                                                                          *
 * (4) Insert a new User by given username and password and returns a msg if succes or not                          *
 * (5) Delete a new User by given username and returns a msg if succes or not                                       *
 *                                                                                                                  *
 * To change this template, choose Tools | Template Manager                                                         *
 * and open the template in the editor.                                                                             *
 ********************************************************************************************************************
 */

package databasesevrer;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import messages.ServerMessages;

/**
 * @author Nissim Zohar
 */

public class DataBase implements IDataBase {
    
    /** Creates a new instance of DataBase */
    public DataBase() {};
    
    //this function check the Admin Password if it Valid with the Admin Password that contained on the Data Base
    public String isValidPasswrodAdmin(String User,String Pass) throws SQLException{
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        // Connecting to DataBase By DriveManager
        con = DriverManager.getConnection(DBConfiguration.jdbcURL);
        if (!con.isClosed()) {
            // Working with the DB here :
            stmt = con.createStatement();
            // SQL SELECT * FROM Admin : choose the Admin Table
            rs= stmt.executeQuery("SELECT * FROM Admin");
        }
        //check if the user name and password correct
        boolean flag = false;
        while(rs.next()) {
            if(rs.getString("username").equals(User)) {
                flag = true;
                if(rs.getString("Password").equals(Pass))
                    return ServerMessages.GRANT_ACCESS;
            }
        }
        if(flag)
            return ServerMessages.WRONG_PASSWORD;
        return ServerMessages.ADMIN_NOT_EXIST;
    }
    
    //this function check the User Password if it Valid
    public  String  isValidPasswrodUser(String userName,String Password) throws SQLException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        // Connecting to DataBase By DriveManager
        con = DriverManager.getConnection(DBConfiguration.jdbcURL);
        if (!con.isClosed()) {
            // Working with the DB here :
            stmt = con.createStatement();
            // SQL SELECT * FROM Users : choose the Users Table
            rs= stmt.executeQuery("SELECT * FROM Users");
        }
        // checking the user and password if correct
        boolean flag = false;
        while(rs.next()) {
            if(rs.getString("UserName").equals(userName)) {
                flag = true;
                if(rs.getString("Password").equals(Password))
                    return ServerMessages.GRANT_ACCESS;
            }
        }
        if(flag)
            return ServerMessages.WRONG_PASSWORD;
        return ServerMessages.USER_NOT_EXIST;
    }
    
    // check if user valid
    public  String  isValidUser(String userName) throws SQLException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        con = DriverManager.getConnection(DBConfiguration.jdbcURL);
        if (!con.isClosed()) {
            // Working with the DB here
            stmt = con.createStatement();
            rs= stmt.executeQuery("SELECT UserName FROM Users");
        }
        while(rs.next()) {
            if(rs.getString("UserName").equals(userName))
                return ServerMessages.GRANT_ACCESS;
        }
        return ServerMessages.USER_NOT_VALID;
    }
    
    // Insert a new User by given username and password and returns a msg if succes or not
    public String InsertUser(String User_Name,String Pass) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Connection con = null;
        Class.forName(DBConfiguration.jdbcDriver).newInstance();
        con = DriverManager.getConnection(DBConfiguration.jdbcURL);
        ResultSet rs =null;
        if (!con.isClosed()) {
            // Working with the DB here
            Statement stmt = null;
            stmt = con.createStatement();
            rs=stmt.executeQuery("SELECT * FROM Users");
            synchronized(this) {
                if(this.isValidUser(User_Name).equals(ServerMessages.USER_NOT_VALID))
                    stmt.executeUpdate("INSERT INTO Users (UserName, Password) VALUES('" + User_Name + "','" + Pass + "')");
                else
                    return ServerMessages.USER_ALREADY_EXIST;
            }
        }
        return ServerMessages.USER_IS_ADDED;
    }
    
    // Delete a new User by given username and returns a msg if succes or not
    public String deleteUser(String userName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName(DBConfiguration.jdbcDriver).newInstance();
        con = DriverManager.getConnection(DBConfiguration.jdbcURL);
        if (!con.isClosed()) {
            // Working with the DB here
            Statement stmt = null;
            stmt = con.createStatement();
            if((stmt.executeQuery("SELECT * FROM Users"  ))==null)
                return ServerMessages.USER_FAIL_REMOVED;
            synchronized(this) {
                if(this.isValidUser(userName).equals(ServerMessages.GRANT_ACCESS))
                    stmt.executeUpdate("DELETE FROM Users WHERE UserName='" + userName +"'");
                else
                    return ServerMessages.USER_NOT_VALID;
            }
        }
        return ServerMessages.USER_IS_DELETED;
    }
    
    void SendAllUsers(ObjectOutputStream output, DesEncrypter encrypter) throws SQLException, IOException {
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        con = DriverManager.getConnection(DBConfiguration.jdbcURL);
        if (!con.isClosed()) {
            // Working with the DB here
            stmt = con.createStatement();
            rs= stmt.executeQuery("SELECT UserName FROM Users");
        }
        //sending to the user a string as object
        while(rs.next())
            output.writeObject(encrypter.encrypt( rs.getString("UserName")));
    }
    
}




