package DataBase;

import Models.Salary;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DataBase.JDBC.bdConnection;

public class TimeSalaryAction {
    public  void addInfTimeSalaryTable(int idEmployeeFK, BigDecimal paymentPerHour , int hours) {

        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO timesalary (idEmployeeFK, paymentPerHour, hours) VALUES(?,?,?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idEmployeeFK);
            preparedStatement.setBigDecimal(2, paymentPerHour);
            preparedStatement.setInt(3, hours);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void takeInfTimeSalaryTable(int idEmployeeFK){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM timesalary WHERE idEmployeeFK = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idEmployeeFK);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Salary.getInstance().setPaymentPerHour(rs.getBigDecimal("paymentPerHour"));
                Salary.getInstance().setHours(rs.getInt("hours"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }

//    public boolean updateInfTimeSalaryTable(int idEmployeeFK, BigDecimal paymentPerHour , int hours) {
//
//        try {
//            JDBC.getBdConnection();
//            String insert = "update timesalary set paymentPerHour = ?, hours = ? " + " WHERE idEmployeeFK = ?";
//            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
//            preparedStatement.setBigDecimal(1, paymentPerHour);
//            preparedStatement.setInt(2, hours);
//            preparedStatement.setInt(3, idEmployeeFK);
//            preparedStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }finally {
//            JDBC.close();
//        }
//        return false;
//    }


    public void addResultTimeSalary(int idEmployee, BigDecimal result){
        try {
            JDBC.getBdConnection();
            String insert = "update employees set resultSalary = ?" +" WHERE idEmployee = ? ";
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
    public  void addInfTimePremiumSalaryTable(int idEmployeeFK, BigDecimal paymentPerHour , int hours, int premium) {

        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO timesalary (idEmployeeFK, paymentPerHour, hours, premium) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idEmployeeFK);
            preparedStatement.setBigDecimal(2, paymentPerHour);
            preparedStatement.setInt(3, hours);
            preparedStatement.setInt(4, premium);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void takeInfTimePremiumSalaryTable(int idEmployeeFK){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM timesalary WHERE idEmployeeFK = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idEmployeeFK);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Salary.getInstance().setPaymentPerHour(rs.getBigDecimal("paymentPerHour"));
                Salary.getInstance().setHours(rs.getInt("hours"));
                Salary.getInstance().setPremium(rs.getInt("premium"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }

    public void addResultTimePremiumSalaryTable(int idEmployee, BigDecimal result){
        try {
            JDBC.getBdConnection();
            String insert = "update employees set resultSalary = ?" +" WHERE idEmployee = ? ";
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
    public void deleteTimeSalaryTable(int idEmployee){
        try {
            JDBC.getBdConnection();
            String selectSQL = "DELETE FROM timesalary   WHERE idEmployeeFK = ?";
            PreparedStatement preparedStmt = bdConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, idEmployee);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
}
