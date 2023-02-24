package DataBase;

import Const.Constants;
import Models.NowUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static DataBase.JDBC.*;

public class SpecialtiesAction {
    public void showAllSpecialties(LinkedList<String> specialties){

        try {
            JDBC.getBdConnection();
            String query = "SELECT *  FROM " + Constants.SPECIALTIE_TABLE;
            ResultSet rs = null;
            String specialtie="";
            rs = statement.executeQuery(query);

            while (rs.next()) {

                specialtie+=rs.getString(Constants.SPECIALTIE_ID)+" ";
                specialtie+=rs.getString(Constants.SPECIALTIE_NAME)+" ";
                specialties.add(specialtie);
                specialtie="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }

    public  void addSpecialties(String specialtieName) {

        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO specialties VALUES(default, ?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setString(1, specialtieName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public  void addSpecialtieToEmployee(int idEmployee, int idSpecialtieFK) {
        try {
            JDBC.getBdConnection();
            String insert = "update employees set idSpecialtieFK = ?" +" WHERE idEmployee = ? ";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idSpecialtieFK);
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
    public void deleteSpecialtie(int idSpecialtie){
        try {
            JDBC.getBdConnection();
            String selectSQL = "DELETE FROM "+Constants.SPECIALTIE_TABLE +  " WHERE idSpecialtie = ?";
            bdConnection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = bdConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, idSpecialtie);
            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }

    }
    public  void addSpecialtieToEmployeeNowUser(int idEmployee, int idSpecialtieFK) {
        try {
            JDBC.getBdConnection();
            String insert = "update employees set idSpecialtieFK = ?" +" WHERE idEmployee = ? ";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idSpecialtieFK);
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
    public void checkSpecialtie(int idSpecialtieFK){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM specialties WHERE idSpecialtie = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idSpecialtieFK);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if(rs.getString("specialtieName") == null){
                  //  NowUser.getInstance().setSpecialtieName("Вы не работаете");
                }else {
                   // NowUser.getInstance().setSpecialtieName(rs.getString("specialtieName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }

}
