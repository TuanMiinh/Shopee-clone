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
import model.context.ConnectDB;
import model.Product;
import query.productQuery;

import static query.productQuery.*;

/**
 *
 * @author Man Nguyen
 */
public class ProductDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getProducts() {
        ConnectDB db = ConnectDB.getInstance();
        List<Product> lp = new ArrayList<>();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(productQuery.selectAll);
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

    public Product getProductById(String id) {
        ConnectDB db = ConnectDB.getInstance();
        Product result = new Product();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(productQuery.selectById);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                result = new Product(
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
            }

            db.closeConnection(con, statement, rs);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<Product> getProductsBySupplierId(String id) {
        ConnectDB db = ConnectDB.getInstance();
        List<Product> products = new ArrayList<>();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(productQuery.selectBySupplierId);
            statement.setString(1, id);
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
                products.add(product);
            }

            db.closeConnection(con, statement, rs);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public List<Product> getProductsByPage(String page) {
        ConnectDB db = ConnectDB.getInstance();
        List<Product> products = new ArrayList<>();
        int pageNum = Integer.parseInt(page);
        int startNum = productQuery.limit * (pageNum - 1) + 1;
        int endNum = productQuery.limit * pageNum;
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(productQuery.selectByPage);
            statement.setInt(1, startNum);
            statement.setInt(2, endNum);

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
                products.add(product);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public void createProducts(Product product) {
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(createProduct);
            statement.setString(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getDiscount());
            statement.setInt(5, product.getQuantity());
            statement.setString(6, product.getDescription());
            statement.setString(7, product.getSupplierId());
            statement.setBoolean(8, product.isIsHot());
            statement.setString(9, product.getImage());
            statement.setString(10, product.getCategory());
            statement.execute();

            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(updateProduct);
            statement.setString(10, product.getId());
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setInt(3, product.getDiscount());
            statement.setInt(4, product.getQuantity());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getSupplierId());
            statement.setBoolean(7, product.isIsHot());
            statement.setString(8, product.getImage());
            statement.setString(9, product.getCategory());
            statement.execute();

            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(String ID) {
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(deleteProduct);
            statement.setString(1, ID);
            statement.execute();

            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
