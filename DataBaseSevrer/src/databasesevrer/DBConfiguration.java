/*
 ********************************************************************************************************************
 * DBConfiguration.java                                                                                             *
 * Created on January 19, 2007, 06:3 PM                                                                             *
 * Data Base Configurations                                                                                         *
 * To change this template, choose Tools | Template Manager                                                         *
 * and open the template in the editor.                                                                             *
 ********************************************************************************************************************
 */

package databasesevrer;

/**
 * @author Nissim Zohar
 */
public interface DBConfiguration {
    //Data Base Configurations
    
    public static final String dbHomeDirectory = "DB";
    public static final String jdbcURL = "jdbc:odbc:ChatDB";
    public static final String jdbcDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
}