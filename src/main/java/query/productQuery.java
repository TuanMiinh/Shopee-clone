/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package query;

/**
 * @author Man Nguyen
 */
public interface productQuery {

    public static int limit = 20;
    public static String selectAll = "SELECT * FROM Products";
    public static String selectById = "SELECT * FROM Products\n"
            + "WHERE id=?";
    public static String selectBySupplierId = "SELECT * FROM Products\n"
            + "WHERE supplierId=?";
    public static String selectByPage = "SELECT id, name, price, discount, quantity, description, supplierId, isHot, image, category\n"
            + "from (select ROW_NUMBER() over (order by id) as RowNum, * from Products) as RowConstrainedResult\n"
            + "where RowNum >=?\n"
            + "and RowNum <= ?\n"
            + "order by RowNum";

    public static String createProduct = "INSERT INTO Products\n"
            + "VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
    public static String updateProduct = "UPDATE Products\n" +
            "SET name = ?, price = ?, discount = ?, quantity = ?, description = ?, supplierId = ?, isHot = ?, image = ? , category = ?\n" +
            "WHERE id = ? ;";
    public static String deleteProduct = "DELETE FROM Products WHERE id=?;";

}
