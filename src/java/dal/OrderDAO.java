/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.Cart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Order;
import java.util.ArrayList;

/**
 *
 * @author LAPTOP
 */
public class OrderDAO extends DBContext {

    public List<Order> getOrdersByUser(String username) {

        String sql = "SELECT * FROM Orders WHERE account_name = ?";
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            smt.setString(1, username);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Order ord = new Order();
                ord.setUsername(rs.getString("account_name"));
                ord.setItemid(rs.getInt("item_id"));
                ord.setQuantity(rs.getInt("quantity"));
                ord.setTotalPrice(rs.getFloat("total_price"));
                list.add(ord);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void AddOrders(Order order) {

        String sql = "INSERT INTO Orders VALUES(?,?,?,?)";
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            smt.setString(1, order.getUsername());
            smt.setInt(2, order.getItemid());
            smt.setInt(3, order.getQuantity());
            smt.setFloat(4, order.getTotalPrice());
            smt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
