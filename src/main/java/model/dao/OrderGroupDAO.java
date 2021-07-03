package model.dao;

import model.*;
import model.context.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderGroupDAO {

    public void createOrderByGroup(OrderByGroup order) {
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();
            String sql = "INSERT INTO OrderByGroup VALUES( ? , ? , ? )";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, order.getOrderCode());
            statement.setString(2, order.getOrderID());
            statement.setString(3, order.getAddress());
            statement.execute();

            statement.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<OrderByGroup> getOrderGroupByOrderCode(String orderCode) {
        ConnectDB db = ConnectDB.getInstance();
        List<OrderByGroup> orders = new ArrayList<>();
        try {
            Connection con = db.openConnection();
            String sql = "SELECT * FROM OrderByGroup WHERE OrderCode = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, orderCode);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String orderID = rs.getString(2);
                String userID = rs.getString(3);
                orders.add(new OrderByGroup(orderCode, orderID, userID));
            }
            statement.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return orders;
    }


    public List<OrderInformation> getOrderInfoByCode(String orderCode) {
        ConnectDB db = ConnectDB.getInstance();
        List<OrderInformation> orderInfo = new ArrayList<>();
        OrderDao orderDao = new OrderDao();
        ItemDao itemDao = new ItemDao();

        try {
            Connection con = db.openConnection();
            String sql = "SELECT OrderID FROM OrderByGroup WHERE OrderCode = ? ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, orderCode);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String orderID = rs.getString(1);
                Order order = orderDao.getOrderByID(orderID);
                ArrayList<Item> itemList = itemDao.getItemByOrderId(orderID);
                orderInfo.add(new OrderInformation(itemList, order.getNote(), order.getAddress(), order.getUserId()));
            }

            db.closeConnection(con, statement, rs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderInfo;
    }

    public void deleteOrderGroupByOrderID(String orderID) {
        ConnectDB db = ConnectDB.getInstance();


        try {
            Connection con = db.openConnection();
            String sql = "DELETE FROM OrderByGroup WHERE OrderID = ? ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, orderID);
            statement.execute();


            statement.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateOrder(OrderByGroup order) {
        ConnectDB db = ConnectDB.getInstance();


        try {
            Connection con = db.openConnection();
            String sql = "UPDATE OrderByGroup SET Address = ? WHERE OrderCode = ? ";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, order.getAddress());
            statement.setString(2, order.getOrderCode());
            statement.execute();


            statement.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
