package DataBase;

import Const.Constants;
import ServerConnection.ClientAction;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;

import static DataBase.JDBC.*;

public class DataBaseAction {




//    public void addNowUser(LinkedList<String> nowUser) {
//        try {
//            JDBC.getBdConnection();
//            String query = "SELECT  " + Constants.USER_FNAME + "," + Constants.USER_LNAME + "," + Constants.USER_LOGIN + "," + Constants.USER_PASSWORD + " FROM " + Constants.USER_TABLE +
//                    " WHERE " + Constants.USER_LOGIN + " = " + "'" + ClientAction.loginUser + "'" + " AND " + Constants.USER_PASSWORD + " = " + "'" + ClientAction.passwordUser + "'";
//            String userNow ="";
//
//            ResultSet rs = statement.executeQuery(query);
//            while (rs.next()) {
//                if (!rs.getString(Constants.USER_LOGIN).equals("") &&
//                        !rs.getString(Constants.USER_PASSWORD).equals("")) {
//                    userNow+=rs.getString(Constants.USER_FNAME)+" ";
//                    userNow+=rs.getString(Constants.USER_LNAME) +" ";
//                    userNow+=rs.getString(Constants.USER_LOGIN)+ " ";
//                    userNow+=rs.getString(Constants.USER_PASSWORD)+" ";
//                    nowUser.add(userNow);
//
//                    userNow ="";
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
//    }


    public void addResultMonthlyTariffRate(int idEmployee, BigDecimal result){
        try {
            JDBC.getBdConnection();
            String insert = "update employees set monthlyTariffRate = ?" +" WHERE idEmployee = ? ";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setBigDecimal(1, result);
            preparedStatement.setInt(2, idEmployee);
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
