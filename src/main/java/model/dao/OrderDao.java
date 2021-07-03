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

import model.Order;
import model.OrderByGroup;
import model.User;
import model.context.ConnectDB;
import query.purchaseQuery;

import static query.purchaseQuery.deleteOrder;
import static query.purchaseQuery.updateOrder;
import static query.userQuery.deleteUser;
import static query.userQuery.updateUser;

/**
 * @author Man Nguyen
 */
public class OrderDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void addOrder(String id, String note, String address, String userId) {
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(purchaseQuery.createOrder);
            statement.setString(1, id);
            statement.setString(2, note);
            statement.setString(3, address);
            statement.setString(4, userId);

            statement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Order getOrderByID(String id) {
        ConnectDB db = ConnectDB.getInstance();
        Order order = null;
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(purchaseQuery.getOrderByID);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String note = rs.getString(2);
                String address = rs.getString(3);
                String userId = rs.getString(4);
                order = new Order(id, note, address, userId);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;

    }

    public ArrayList<Order> getOrderByUserId(String userId) {
        ConnectDB db = ConnectDB.getInstance();
        ArrayList<Order> orderList = new ArrayList<>();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(purchaseQuery.getOrderByUserID);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String note = rs.getString(2);
                String address = rs.getString(3);

                orderList.add(new Order(id, note, address, userId));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    public void updateOrder(Order order) {
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(updateOrder);
            statement.setString(1, order.getNote());
            statement.setString(2, order.getAddress());
            statement.setString(3, order.getUserId());
            statement.setString(4, order.getId());
            statement.execute();

            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(String ID) {
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();

            PreparedStatement statement = con.prepareStatement(deleteOrder);
            statement.setString(1, ID);
            statement.execute();

            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrders() {
        ConnectDB db = ConnectDB.getInstance();
        String query = "SELECT * FROM Orders";
        List<Order> lu = new ArrayList<>();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)

                );
                lu.add(order);
            }

            db.closeConnection(con, statement, rs);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }






}
