package DataBase;

import Const.Constants;
import Models.NowUser;
import Models.Salary;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static DataBase.JDBC.*;

public class EmployeeAction {

    public void showAllEmployees(LinkedList<String> employees){

        try {
            JDBC.getBdConnection();
            String query = "SELECT *  FROM " + Constants.EMPLOYEE_TABLE;
            ResultSet rs = null;
            String employee="";
            rs = statement.executeQuery(query);

            while (rs.next()) {

                employee+=rs.getString(Constants.EMPLOYEE_ID)+" ";
                employee+=rs.getString(Constants.EMPLOYEE_IDFK_USER)+" ";
                if (rs.getString(Constants.EMPLOYEE_IDFK_SPEC) == null){
                    employee+= 0 + " ";
                }else {
                    employee += rs.getString(Constants.EMPLOYEE_IDFK_SPEC) + " ";
                }
                if (rs.getString(Constants.EMPLOYEE_RESULT) == null){
                    employee+= 0 + " ";
                }else {
                    employee += rs.getString(Constants.EMPLOYEE_RESULT) + " ";
                }
                if (rs.getString(Constants.EMPLOYEE_RATE) == null){
                    employee+= 0 + " ";
                }else {
                    employee += rs.getString(Constants.EMPLOYEE_RATE) + " ";
                }
                employees.add(employee);
                employee="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public  void addEmployees(int idUserFK) {
        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO employees" + "(idEmployee, idUserFK)" + "VALUES(default, ?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idUserFK);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void deleteEmployees(int idEmployees){
        try {
            JDBC.getBdConnection();
            String selectSQL = "DELETE FROM "+Constants.EMPLOYEE_TABLE +  " WHERE idEmployee = ?";
            bdConnection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = bdConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, idEmployees);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }

    }
    public  void addEmployeesNowUser(int idUserFK) {
        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO employees" + "(idEmployee, idUserFK)" + "VALUES(default, ?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idUserFK);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
//    public void takeInfEmployeesNowUserTable(int idUserFK){
//        try {
//            JDBC.getBdConnection();
//            String query = "SELECT * FROM employees WHERE idUserFK = ?";
//            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
//            preparedStatement.setInt(1, idUserFK);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                NowUser.getInstance().setIdEmployee(rs.getInt("idEmployee"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }finally {
//            JDBC.close();
//        }
//    }
    public void deleteEmployeesNowUser(int idUserFK){
        try {
            JDBC.getBdConnection();
            String selectSQL = "DELETE FROM "+Constants.EMPLOYEE_TABLE +  " WHERE idUserFK = ?";
            bdConnection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = bdConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, idUserFK);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }

    }
    public void checkNowUserIsEmployee(int idUserFK){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM employees WHERE idUserFK = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idUserFK);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                NowUser.getInstance().setIdEmployee(rs.getInt("idEmployee"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void checkEmployeeIsSpecialtie(int idEmployees){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM employees WHERE idEmployee = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idEmployees);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                NowUser.getInstance().setIdSpecialti(rs.getInt("idSpecialtieFK"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public boolean showResultSalaryNowEmployee(int idEmployees){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM employees WHERE idEmployee = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idEmployees);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (rs.getBigDecimal("resultSalary") == null){
                    Salary.getInstance().setResultSalary(BigDecimal.valueOf(1));
                }else {
                    Salary.getInstance().setResultSalary(rs.getBigDecimal("resultSalary"));
                }
                return true;
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
}
