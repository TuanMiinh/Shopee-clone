/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import model.context.ConnectDB;
import service.TokenUtil;

import static query.purchaseQuery.updateQuantityProductWhenDelete;

/**
 * @author Man Nguyen
 */
public class test {

    static ProductDao productDao = new ProductDao();
    static Product product = new Product();
    static ItemDao itemDao = new ItemDao();
    static OrderDao orderDao = new OrderDao();
    static OrderGroupDAO orderGroupDAO = new OrderGroupDAO();


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String orderCode = "sp3cIQizCG4dp87-0toEtKl3";
        System.out.println(orderGroupDAO.getOrderGroupByOrderCode(orderCode));


    }


}



