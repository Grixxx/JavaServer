
import DataBase.CreateTableDataBase;
import DataBase.JDBC;
import DataBase.UserAction;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.fail;

public class JDBC_CRUDTest {
    private static final String ADRESS = "jdbc:mysql://localhost:3306/jdbctest";
    private static final String LOGIN="root";
    private static final String PASSWORD="root";
    private static final String TABLE_NAME="programmers";

    @Test
    public void testClass() {
        try {
            JDBC.getBdConnection();
        }
        catch(RuntimeException e) {
            e.printStackTrace();
            fail("Failed to connect. SQLException.");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to connect. SQLException.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail("Failed to connect. SQLException.");
        }
    }


    @Test
    public void testCreate() {
        try {
            JDBC.getBdConnection();
            CreateTableDataBase create = new CreateTableDataBase(JDBC.bdConnection,JDBC.statement);
            create.createUsersDB();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to create new user. SQLException");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail("Failed to connect. SQLException.");
        }
    }

    @Test
    public void testDelete() {
        try {
            UserAction userAction = new UserAction();
            userAction.deleteUser(14);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to delete user. SQLException");
        }
    }

    @Test
    public void testRead() {
        try {
            UserAction userAction = new UserAction();
            LinkedList<String> users = new LinkedList<>();
            userAction.showAllUsers(users);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Failed to read database. SQLException");
        }
    }
}
