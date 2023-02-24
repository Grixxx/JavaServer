package DataBase;

import Const.Constants;
import Models.NowUser;
import Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static DataBase.JDBC.*;

public class UserAction {
    public UserAction() {
        try {
            getBdConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        new CreateTableDataBase(bdConnection, statement);
        JDBC.close();
    }
    public boolean authorizationUser(String login, String password) {

        try {
            JDBC.getBdConnection();
            String query = "SELECT *" + " FROM " + Constants.USER_TABLE +
                    " WHERE " + Constants.USER_LOGIN + " = " + "'" + login + "'" + " AND " + Constants.USER_PASSWORD + " = " + "'" + password + "'";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                if (!rs.getString(Constants.USER_LOGIN).equals("") &&
                        !rs.getString(Constants.USER_PASSWORD).equals("")) {
                    NowUser.getInstance().setIdUser(rs.getInt(Constants.USER_ID));
                    NowUser.getInstance().setFname(rs.getString(Constants.USER_FNAME));
                    NowUser.getInstance().setLname(rs.getString(Constants.USER_LNAME));
                    NowUser.getInstance().setLogin(rs.getString(Constants.USER_LOGIN));
                    NowUser.getInstance().setPasswrod(rs.getString(Constants.USER_PASSWORD));
                    NowUser.getInstance().setRolee(rs.getString(Constants.USER_ROLE));
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
        return false;
    }
//    public boolean authorizationUser(User user) {
//
//        try {
//            JDBC.getBdConnection();
//            String query = "SELECT *" + " FROM " + Constants.USER_TABLE +
//                    " WHERE " + Constants.USER_LOGIN + " = " + "'" + user.getLogin() + "'" + " AND " + Constants.USER_PASSWORD + " = " + "'" + user.getPassword() + "'";
//
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                if (!rs.getString(Constants.USER_LOGIN).equals("") &&
//                        !rs.getString(Constants.USER_PASSWORD).equals("")) {
//                    NowUser.getInstance().setIdUser(rs.getInt(Constants.USER_ID));
//                    NowUser.getInstance().setFname(rs.getString(Constants.USER_FNAME));
//                    NowUser.getInstance().setLname(rs.getString(Constants.USER_LNAME));
//                    NowUser.getInstance().setLogin(rs.getString(Constants.USER_LOGIN));
//                    NowUser.getInstance().setPasswrod(rs.getString(Constants.USER_PASSWORD));
//                    NowUser.getInstance().setRolee(rs.getString(Constants.USER_ROLE));
//                    return true;
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }finally {
//            JDBC.close();
//        }
//        return false;
//    }
//    public  void receiveMessageFromRegistration(User user) {
//
//        try {
//            JDBC.getBdConnection();
//            String insert = "INSERT INTO users VALUES(default, ?,?,?,?,?)";
//            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
//            preparedStatement.setString(1, user.getFirstName());
//            preparedStatement.setString(2, user.getLastName());
//            preparedStatement.setString(3, user.getLogin());
//            preparedStatement.setString(4, user.getPassword());
//            preparedStatement.setString(5, "user");
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }finally {
//            JDBC.close();
//        }
//    }
public  void receiveMessageFromRegistration(String fName, String lName, String login, String password, String rolee) {

    try {
        JDBC.getBdConnection();
        String insert = "INSERT INTO users VALUES(default, ?,?,?,?,?)";
        PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
        preparedStatement.setString(1, fName);
        preparedStatement.setString(2, lName);
        preparedStatement.setString(3, login);
        preparedStatement.setString(4, password);
        preparedStatement.setString(5, rolee);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }finally {
        JDBC.close();
    }
}
    public  void addUserInDataBase(String fName, String lName, String login, String password, String role) throws SQLException {

        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO users(firstName,lastName,login,password,rolee) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, lName);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void deleteUser(int idUser) throws SQLException {
        try {
            JDBC.getBdConnection();
            String selectSQL = "DELETE FROM "+Constants.USER_TABLE +  " WHERE idUser = ?";
            bdConnection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = bdConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, idUser);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }

    }
    public void showAllUsers(LinkedList<String> users) throws SQLException {

        try {
            JDBC.getBdConnection();
            String query = "SELECT *  FROM " + Constants.USER_TABLE;
            ResultSet rs = null;
            String user="";
            rs = statement.executeQuery(query);

            while (rs.next()) {

                user+=rs.getString(Constants.USER_ID)+" ";
                user+=rs.getString(Constants.USER_FNAME)+" ";
                user+=rs.getString(Constants.USER_LNAME) +" ";
                user+=rs.getString(Constants.USER_LOGIN) +" ";
                user+=rs.getString(Constants.USER_PASSWORD) +" ";
                user+=rs.getString(Constants.USER_ROLE)+" ";
                users.add(user);
                user="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new SQLException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void showNowUser(LinkedList<String> users, int idNowUser){

        try {
            JDBC.getBdConnection();
            String query = "SELECT *  FROM " + Constants.USER_TABLE + " WHERE idUser = ?";
            PreparedStatement preparedStmt = bdConnection.prepareStatement(query);
            preparedStmt.setInt(1, idNowUser);
            ResultSet rs = null;
            String user="";
            rs = preparedStmt.executeQuery();

            while (rs.next()) {

                user+=rs.getString(Constants.USER_FNAME)+" ";
                user+=rs.getString(Constants.USER_LNAME) +" ";
                user+=rs.getString(Constants.USER_LOGIN) +" ";
                user+=rs.getString(Constants.USER_PASSWORD) +" ";
                users.add(user);
                user="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public  void changePasswordNowUser(int idUser, String newPassword) {
        try {
            JDBC.getBdConnection();
            String insert = "update users set password = ?" +" WHERE idUser = ? ";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
}
