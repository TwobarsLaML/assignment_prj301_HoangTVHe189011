/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Item;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author LAPTOP
 */
public class ItemDAO extends DBContext {

    public List<Item> getAll() {

        List<Item> list = new ArrayList<>();
        String sql = "SELECT * FROM Items";
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Item i = new Item(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"), rs.getString("category"),
                        rs.getString("img"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Item getItemById(int id) {

        Item i = new Item();
        String sql = "SELECT * FROM Items WHERE id = ?";
        try {
            PreparedStatement smt = connection.prepareStatement(sql);
            smt.setInt(1, id);
            ResultSet rs = smt.executeQuery();
            if(rs.next()){
                i.setId(rs.getInt(1));
                i.setImg(rs.getString(2));
                i.setName(rs.getString(3));
                i.setPrice(rs.getFloat(4));
                i.setCategory(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return i;
    }

    //Testing connection
    public static void main(String[] args) {
        ItemDAO edao = new ItemDAO();
        List<Item> list = edao.getAll();
        System.out.println(list.get(0).getName());
    }
}
