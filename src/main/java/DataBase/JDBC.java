package DataBase;
import java.sql.*;
public class JDBC {
    public static Connection bdConnection;
    public static Statement statement;

    public static void  getBdConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        bdConnection= DriverManager.getConnection("jdbc:mysql://localhost/salary?useUnicode=true,serverTimezone=UTC", "root", "griha999");
        statement = bdConnection.createStatement();
        System.out.println("Database connection is done");
        if (bdConnection == null) {
            throw new SQLException();
        }
        else
        {
            System.out.println("Successfully connected");
        }
    }
    public static void close() {
        try {
            if(bdConnection != null) {
                bdConnection.close();
                System.out.println("Closing connection");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
