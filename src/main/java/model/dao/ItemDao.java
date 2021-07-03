/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Item;
import model.Product;
import model.context.ConnectDB;
import query.purchaseQuery;
import service.GenerateRandomId;

import static query.purchaseQuery.*;

/**
 * @author Man Nguyen
 */
public class ItemDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void addItem(Item item, String orderId) throws ClassNotFoundException, SQLException {
        ConnectDB db = ConnectDB.getInstance();
        String itemId = GenerateRandomId.next();

        try {
            Connection con = db.openConnection();
            PreparedStatement statementItem = con.prepareStatement(purchaseQuery.createItem);
            PreparedStatement statementProduct = con.prepareStatement(purchaseQuery.updateQuantityProduct);

            statementItem.setString(1, itemId);
            statementItem.setString(2, orderId);
            statementItem.setString(3, item.getProductId());
            statementItem.setInt(4, item.getQuantity());

            statementProduct.setInt(1, item.getQuantity());
            statementProduct.setString(2, item.getProductId());

            statementItem.execute();
            statementProduct.execute();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Product> getProducts() {
        ConnectDB db = ConnectDB.getInstance();
        String query = "SELECT * FROM Products";
        List<Product> lp = new ArrayList<>();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getByte("discount"),
                        rs.getInt("quantity"),
                        rs.getString("description"),
                        rs.getString("supplierId"),
                        rs.getBoolean("isHot"),
                        rs.getString("category")
                );
                lp.add(product);
            }

            db.closeConnection(con, statement, rs);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lp;
    }

    public ArrayList<Item> getItemByOrderId(String orderId) {
        ConnectDB db = ConnectDB.getInstance();
        ArrayList<Item> items = new ArrayList<>();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(getItemByOrderId);
            statement.setString(1, orderId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String productId = rs.getString(3);
                int quantity = rs.getInt(4);
                Item item = new Item(productId, quantity);
                items.add(item);
            }

            db.closeConnection(con, statement, rs);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public void deleteItemByOrderId(String orderId) {
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            ArrayList<Item> items = getItemByOrderId(orderId);
            PreparedStatement statementProduct = null;
            for (Item item : items) {
                statementProduct = con.prepareStatement(updateQuantityProductWhenDelete);
                statementProduct.setInt(1, item.getQuantity());
                statementProduct.setString(2, item.getProductId());
                statementProduct.execute();
            }

            PreparedStatement statement = con.prepareStatement(deleteItemByOrderId);
            statement.setString(1, orderId);
            statement.execute();
            statement.close();


            con.close();


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
