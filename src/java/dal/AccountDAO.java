/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import java.util.ArrayList;
import model.Account;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author LAPTOP
 */
public class AccountDAO extends DBContext{
    
    public List<Account> getAllAccounts() {
        
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM Accounts";
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();
            while (rs.next()){
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                list.add(a);
            }
        } catch (SQLException ex) {
            
        }
        return list;
    }
    
    public static void main(String[] args) {
        AccountDAO edao = new AccountDAO();
        List<Account> list = edao.getAllAccounts();
        System.out.println(list.get(0).getUsername());
    }
}
