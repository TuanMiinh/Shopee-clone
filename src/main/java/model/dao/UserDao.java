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

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.User;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.context.ConnectDB;
import service.GenerateRandomId;

import static query.userQuery.*;

/**
 * @author Man Nguyen
 */
public class UserDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<User> getUsers() {
        ConnectDB db = ConnectDB.getInstance();
        String query = "SELECT * FROM Users";
        List<User> lu = new ArrayList<>();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getBoolean("isSupplier")
                );
                lu.add(user);
            }

            db.closeConnection(con, statement, rs);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }


    public void createUser(User user) {
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(createUser);
            statement.setString(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getAvatar());
            statement.setString(8, String.valueOf(user.getIsSupplier()));
            statement.setString(9, user.getDescription());
            statement.execute();

            statement.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateUser(User user) {
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(updateUser);
            statement.setString(9, user.getId());
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getAvatar());
            statement.setString(7, String.valueOf(user.getIsSupplier()));
            statement.setString(8, user.getDescription());
            statement.execute();

            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteUser(String ID) {
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();

            PreparedStatement statement = con.prepareStatement(deleteUser);
            statement.setString(1, ID);
            statement.execute();

            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByID(String ID) {
        User user = null;
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(findUserByID);
            statement.setString(1, ID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(5);
                String phoneNumber = rs.getString(6);
                String avatar = rs.getString(7);
                boolean isSupplier = rs.getBoolean(8);
                String description = rs.getString(9);

                user = new User(ID, name, username, password, email, phoneNumber, avatar, isSupplier, description);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    public User getUserByUsername(String username) {
        User user = null;
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(findUserByUsername);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(4);
                String email = rs.getString(5);
                String phoneNumber = rs.getString(6);
                String avatar = rs.getString(7);
                boolean isSupplier = rs.getBoolean(8);
                String description = rs.getString(9);

                user = new User(ID, name, username, password, email, phoneNumber, avatar, isSupplier, description);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    public String getPasswordByUserName(String userName) {
        String password = null;
        ConnectDB db = ConnectDB.getInstance();
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(getPasswordByUserName);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
                password = rs.getString("password");
            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return password;

    }


    public boolean checkUserLogin(User user) {
        return user.getPassword().equals(getPasswordByUserName(user.getUsername()));
    }





}
