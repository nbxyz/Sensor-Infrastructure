/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL_controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author taras
 */
public class UserControler {

    private String std_dbname = "jdbc:mysql://ubuntu4.javabog.dk:53067/";
    private String std_uname = "root";
    private String std_password = "sensorDB";
    private String UserDB = "DataBase";/**//*
    private String std_dbname = "jdbc:mysql://localhost/";
    private String std_uname = "root";
    private String std_password = "";
    private String UserDB = "userdb";/**/
    private Connection con_UserDB;
    private String db_table = "userdb";

    /**
     * Creates Creates Sql user controler
     */
    public UserControler() {
        try {
            con_UserDB = DriverManager.getConnection(std_dbname + UserDB, std_uname, std_password);
            System.out.println("Connected to User Database");

            Statement stmt_exp_data;

            String sql = "CREATE TABLE IF NOT EXISTS `"+db_table+"` ("
                    + "`UserID` int(11) NOT NULL AUTO_INCREMENT,"
                    + "`UserName` text NOT NULL,"
                    + "`Password` text NOT NULL,"
                    + "`Email` text NOT NULL,"
                    + "`Name` text NOT NULL,"
                    + "`Status` text NOT NULL,"
                    + "`Creation_Date` timestamp NOT NULL,"
                    + " PRIMARY KEY (`UserID`)"
                    + ") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11";

            stmt_exp_data = con_UserDB.createStatement();
            stmt_exp_data.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new IllegalStateException("Cannot connect the User database!" + ex.getMessage());
        }
    }
    /**
     * tests if user exists if not then user is created 
     * @param uname username
     * @param password password
     * @param email email address
     * @param name name of the user 
    */
    public boolean createUser(String uname, String password, String email, String name) {
        try {
            
            PreparedStatement prepStmt_testUser = con_UserDB.prepareStatement("SELECT * FROM "+db_table+" WHERE UserName = ? or Email = ?");
            prepStmt_testUser.setString(1, uname);
            prepStmt_testUser.setString(2, email);
            ResultSet rs= prepStmt_testUser.executeQuery();
            if (rs.next()){
               System.out.println("Username or Email is being Used");
               return false;
            }
            Statement stmt = con_UserDB.createStatement();
  
            String sql = "INSERT INTO "+db_table
                    + "(UserID, UserName, Password, Email, Name, Status, Creation_Date) VALUES "
                    + "(null,'?','?','?','?','Basic',now())";
            stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet tableKeys = stmt.getGeneratedKeys();
            tableKeys.next();
            int autoGeneratedID = tableKeys.getInt(1);
            
            PreparedStatement prepStmt = con_UserDB.prepareStatement("UPDATE `"+db_table+"` SET UserName=?,Password=?,`Email`=?,`Name`=? WHERE UserID = "+autoGeneratedID);
            prepStmt.setString(1, uname);
            prepStmt.setString(2, password);
            prepStmt.setString(3, email);
            prepStmt.setString(4, name);
            prepStmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
            System.out.println(ex.getMessage());
        }
        return false;

    }
    
    public void changePassword(String Uname, String changeParam){
        try {
            PreparedStatement prepStmt = con_UserDB.prepareStatement("UPDATE `"+db_table+"` SET Password=? Where UserName=? or Email = ?", Statement.SUCCESS_NO_INFO);
            prepStmt.setString(1, changeParam);
            prepStmt.setString(2, Uname);
            prepStmt.setString(3, Uname);
            int updates = prepStmt.executeUpdate();
            if(updates == 0){
                System.out.println("User Does Not Exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
            System.out.println(ex.getMessage());
        }
        System.out.println("User "+Uname+": has changed password");
    }
    /**
     * returns users id, if user does not exists returns 0
     * @param user
     * @param password
     * @return 
     */
    public int getID(String user, String password){
            try {
            PreparedStatement prepStmt = con_UserDB.prepareStatement("SELECT * FROM "+db_table+" WHERE `UserName` = ? AND `Password` = ?");
            prepStmt.setString(1, user);            
            prepStmt.setString(2, password);
            ResultSet rs = prepStmt.executeQuery();
            if(!rs.next()){
                prepStmt = con_UserDB.prepareStatement("SELECT * FROM "+db_table+" WHERE `Email` = ? AND `Password` = ?");
                prepStmt.setString(1, user);            
                prepStmt.setString(2, password);
                rs = prepStmt.executeQuery();
                if(!rs.next()){
                    return -1;
                }
            }
            return (int)(rs.getObject(1));
            
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    /**
     * Changes status of the user  
     * @param UserId
     * @param status 
     */
    public void changeStatus(int UserId, String status){
             try {
            PreparedStatement prepStmt = con_UserDB.prepareStatement("UPDATE `"+db_table+"` SET `Status`= ? WHERE `UserID` = ?");
            prepStmt.setString(1, status);
            prepStmt.setInt(2, UserId);
            int updates = prepStmt.executeUpdate();
            if(updates == 0){
                System.out.println("User Does Not Exist");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    
    }
}
