package query;

public interface userQuery {
    public static String createUser = "INSERT INTO Users\n"
            + "VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ?)";

    public static String deleteUser = "DELETE FROM Users WHERE id=?;";
    public static String updateUser = "UPDATE Users\n" +
            "SET name = ?, username = ?, password = ?, email = ?, phoneNumber = ?, avatar = ?, isSupplier = ?, description = ?\n" +
            "WHERE id = ? ;";

    public static String findUserByID = "SELECT * FROM Users WHERE id = ?";
    public static String findUserByUsername = "SELECT * FROM Users WHERE username = ?";
    public static String getPasswordByUserName = "SELECT password FROM Users WHERE username = ?";


}
