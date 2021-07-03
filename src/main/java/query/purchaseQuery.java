/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query;

/**
 * @author Man Nguyen
 */
public interface purchaseQuery {

    /*
        Order class
     */
    public static String createOrder = "INSERT INTO Orders\n"
            + "VALUES(?,?,?,?)";

    /*
        Product class
     */
    public static String updateQuantityProduct = "UPDATE Products\n"
            + "SET quantity-=? where id=?";

    /*
        Item class
     */
    public static String createItem = "INSERT INTO Items\n"
            + "VALUES(?,?,?,?)";

    public static String getOrderByID = "\n" +
            "  SELECT * FROM Orders WHERE ID = ?";

    public static String updateOrder = "UPDATE Orders\n" +
            "SET note = ?, address = ?, userId = ?\n" +
            "WHERE id = ? ;";


    public static String getOrderByUserID = "\n" +
            "  SELECT * FROM Orders WHERE userId = ?";

    public static String deleteOrder = "DELETE FROM Orders WHERE id=?;";

    public static String getItemByOrderId = "SELECT * FROM Items WHERE orderId = ?";

    public static String deleteItemByOrderId = "DELETE FROM Items WHERE orderId = ?";

    public static String updateQuantityProductWhenDelete = "UPDATE Products\n"
            + "SET quantity+=? where id=?";
}
