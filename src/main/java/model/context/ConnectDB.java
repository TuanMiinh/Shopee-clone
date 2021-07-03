/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.context.DatabaseInfor.driverName;
import static model.context.DatabaseInfor.pass;
import static model.context.DatabaseInfor.url;
import static model.context.DatabaseInfor.user;

/**
 *
 * @author Man Nguyen
 */
public class ConnectDB {

    private static ConnectDB instance;

    public Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        Connection con = DriverManager.getConnection(url, user, pass);

        return con;
    }

    public static ConnectDB getInstance() {
        if (instance == null) {
            instance = new ConnectDB();
        }

        return instance;
    }

    public void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        try {
            con.close();
            stmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
