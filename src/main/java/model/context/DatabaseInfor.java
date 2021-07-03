/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.context;

/**
 *
 * @author Man Nguyen
 */
public interface DatabaseInfor {
    // khai bao lien quan den database
    public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url="jdbc:sqlserver://localhost:1433;databaseName=shopeeDB";
    public static String user="sa";
    public static String pass="123456";
}
