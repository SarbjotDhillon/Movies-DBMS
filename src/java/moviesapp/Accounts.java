/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesapp;

import java.sql.*;
/**
 *
 * @author gurvir
 */
public class Accounts{

    PreparedStatement insertAccounts = null;
    public Accounts()
    {
        try{
         moviesdatabase db = new moviesdatabase();
         Connection con = db.getConnection();
         Statement statement = con.createStatement();
         insertAccounts = con.prepareStatement("INSERT INTO Accounts (email, first_name, last_name, password, time)" + " VALUES (?, ?, ?, ?, ?)");
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }  

    public int setAccount(String email, String first, String last, String password, Timestamp time)
    {
        int result = 0;
        try{
            insertAccounts.setString(1,email);
            insertAccounts.setString(2,first);
            insertAccounts.setString(3,last);
            insertAccounts.setString(4, password);
            insertAccounts.setTimestamp(5, time);
            result = insertAccounts.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }   
    public int UniqueEmail(String email)
    { 
        int result = 0;
        try{
            moviesdatabase db = new moviesdatabase();
            Connection con = db.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select email from Accounts where email = '" + email + "'");
            if(rs.next())
            {
                result = 1;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    } 
    public boolean FindUser(String email, String password)
    {
        boolean result = false;
        try{
            moviesdatabase db = new moviesdatabase();
            Connection con = db.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select email,password from Accounts where email = '" + email + "'");
            while(rs.next())
            {
                if(rs.getString("email").equals(email) && rs.getString("password").equals(password))
                    return true; 
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }       
}