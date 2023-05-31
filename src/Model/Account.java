/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Account {
    private int id;
    private int user_id;
    private String accountNumber;
    private String userName;
    private String curreny;
    private String balance;
    private String creation_date;
    
       public Account(String accountNumber, String userName, String curreny, String balance, String creation_date) {
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.curreny = curreny;
        this.balance = balance;
        this.creation_date = creation_date;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCurreny() {
        return curreny;
    }

    public void setCurreny(String curreny) {
        this.curreny = curreny;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
    
    
    public int save() throws SQLException, ClassNotFoundException{
        
        Connection c = DB.getInstance().getConnection();
        PreparedStatement psUser=null;
        PreparedStatement ps = null;
        int recordCounter =0;
        ResultSet rs = null;
//        String sqlUser_id="select u.id from users u,accounts a"
//                + "where u.username = a.username";
//        psUser=c.prepareStatement(sqlUser_id);
//        rs = psUser.executeQuery();
//               

        String sql = "INSERT INTO accounts (ID, USER_ID,ACCOUNT_NUMBER,USERNAME,CURRENCY, BALANCE, CREATION_DATE)"
                + "VALUES (?,(SELECT ID FROM USERS WHERE username = ?),?,?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2,this.getUserName());
        ps.setString(3, this.getAccountNumber());
        ps.setString(4,this.getUserName());
        ps.setString(5, this.getCurreny());
        ps.setString(6, this.getBalance());
        ps.setString(7, this.getCreation_date());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUserName()
                    +" Account was added successfully!");
           
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
    
    public static ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()){
            Account account = new Account(rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            account.setId(rs.getInt(1));
            account.setUser_id(rs.getInt(2));
            accounts.add(account);
            
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return accounts;
    }
    
    public int update() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "UPDATE accounts SET ACCOUNT_NUMBER=?, USERNAME=?, CURRENCY=? , BALANCE=?,CREATION_DATE=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1,this.getAccountNumber());
        ps.setString(2, this.getUserName());
        ps.setString(3, this.getCurreny());
        ps.setString(4, this.getBalance());
        ps.setString(5, this.getCreation_date());
        ps.setInt(6, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Account with id : "+this.getId()+" was updated successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
    
      public int delete() throws SQLException, ClassNotFoundException{
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter =0;
        String sql = "DELETE FROM accounts WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The Accounts with id : "+this.getId()+" was deleted successfully!");
        }
        if (ps != null){
            ps.close();
        }
        c.close();
        return recordCounter;  
    }
     
      public static ArrayList<Account> searchAccountByNumber(String accountNumber) throws SQLException {
       ArrayList<Account> accounts = new ArrayList<>();
           Connection c = DB.getInstance().getConnection();
           PreparedStatement ps = null;
            String sql = "SELECT * FROM accounts WHERE account_number = ?";
            ps=c.prepareStatement(sql);
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id=rs.getInt("USER_ID");
                String accountnumber = rs.getString("account_number");
                String userName=rs.getString("USERNAME");
                String curreny=rs.getString("CURRENCY");
                String balance=rs.getString("BALANCE");
                String creationDate=rs.getString("CREATION_DATE");
                
            Account account = new Account(accountnumber,userName,curreny,balance,creationDate);
            account.setId(id);
            account.setUser_id(user_id);
            accounts.add(account);
            }
       
       if (ps != null){
            ps.close();
        }
        c.close();
        return accounts;
    }
    
    
}
