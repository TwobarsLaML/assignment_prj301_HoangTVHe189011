/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import java.util.ArrayList;
import model.Cart;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author LAPTOP
 */
public class CartDAO extends DBContext{
    
    public List<Cart> getCartsFromID(String id) {
        
        String sql = "SELECT * FROM Carts WHERE account_name = ?";
        List<Cart> list = new ArrayList<>();
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            smt.setString(1, id);
            ResultSet rs = smt.executeQuery();
            while(rs.next()){
                Cart c = new Cart();
                c.setId(rs.getInt("cart_id"));
                c.setUserName(rs.getString("account_name"));
                c.setItemId(rs.getInt("item_id"));
                c.setQuantity(rs.getInt("quantity"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("hehehe");
        }
        return list;
    }
    
    public void addCart(Cart cart) {
        
        String sql = "INSERT INTO Carts VALUES(?,?,?)";
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            smt.setString(1, cart.getUserName());
            smt.setInt(2, cart.getItemId());
            smt.setInt(3, cart.getQuantity());
            smt.executeUpdate();
        } catch(Exception e) {
            System.out.println("hehehe");
        }
    }
    
    public void changeCartInfo(Cart cart) {
        
        String sql = "UPDATE Carts SET quantity = ? WHERE cart_id = ?";
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            smt.setInt(1, cart.getQuantity());
            smt.setInt(2, cart.getId());
            smt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteCartByID(int id) {
        
        String sql = "DELETE FROM Carts WHERE cart_id = ?";
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            smt.setInt(1, id);
            smt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
