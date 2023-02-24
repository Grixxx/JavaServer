package DataBase;

import Models.Salary;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DataBase.JDBC.bdConnection;

public class PieceWorksalaryAction {


    public  void addStraightPieceworkSalaryTable(int idEmployeeFXp, BigDecimal hourlyRate , int productionRate, int numberOfItems, int numberOfProducts) {
        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO pieceworksalary (idEmployeeFXp, hourlyRate, productionRate,numberOfItems,numberOfProducts) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idEmployeeFXp);
            preparedStatement.setBigDecimal(2, hourlyRate);
            preparedStatement.setInt(3, productionRate);
            preparedStatement.setInt(4, numberOfItems);
            preparedStatement.setInt(5, numberOfProducts);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public  void addPremiumPieceworkSalaryTable(int idEmployeeFXp, BigDecimal hourlyRate , int productionRate, int numberOfItems, int numberOfProducts,
                                                int performanceBonusPercentage, int overfulfillmentBonusPercentage,  int overfulfillmentBonusPlan) {
        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO pieceworksalary (idEmployeeFXp, hourlyRate, productionRate,numberOfItems,numberOfProducts,performanceBonusPercentage,overfulfillmentBonusPercentage, overfulfillmentBonusPlan )"+
                    "VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idEmployeeFXp);
            preparedStatement.setBigDecimal(2, hourlyRate);
            preparedStatement.setInt(3, productionRate);
            preparedStatement.setInt(4, numberOfItems);
            preparedStatement.setInt(5, numberOfProducts);
            preparedStatement.setInt(6, performanceBonusPercentage);
            preparedStatement.setInt(7, overfulfillmentBonusPercentage);
            preparedStatement.setInt(8, overfulfillmentBonusPlan);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public  void addProgressivePieceworkSalaryTable(int idEmployeeFXp, BigDecimal hourlyRate , int productionRate, int сolProductsPlan, int coefficient, int colProductsReleased) {
        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO pieceworksalary (idEmployeeFXp, hourlyRate, productionRate,сolProductsPlan,coefficient,colProductsReleased ) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idEmployeeFXp);
            preparedStatement.setBigDecimal(2, hourlyRate);
            preparedStatement.setInt(3, productionRate);
            preparedStatement.setInt(4, сolProductsPlan);
            preparedStatement.setInt(5, coefficient);
            preparedStatement.setInt(6, colProductsReleased);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public  void addIndirectPieceworkSalaryTable(int idEmployeeFXp, BigDecimal ancillaryHourlyRate , int auxiliaryServiceRate, int productionRateMain, int numberOfItems, int numberOfProducts) {
        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO pieceworksalary (idEmployeeFXp, ancillaryHourlyRate, auxiliaryServiceRate, productionRateMain,numberOfItems, numberOfProducts ) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idEmployeeFXp);
            preparedStatement.setBigDecimal(2, ancillaryHourlyRate);
            preparedStatement.setInt(3, auxiliaryServiceRate);
            preparedStatement.setInt(4, productionRateMain);
            preparedStatement.setInt(5, numberOfItems);
            preparedStatement.setInt(6, numberOfProducts);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public  void addAccordPieceworkSalaryTable(int idEmployeeFXp, BigDecimal totalEarnings , int allWorkingTime, int employeeTime) {
        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO pieceworksalary (idEmployeeFXp, totalEarnings, allWorkingTime, employeeTime ) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idEmployeeFXp);
            preparedStatement.setBigDecimal(2, totalEarnings);
            preparedStatement.setInt(3, allWorkingTime);
            preparedStatement.setInt(4, employeeTime);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public  void addAccordPremiumPieceworkSalaryTable(int idEmployeeFXp, BigDecimal totalEarnings , int allWorkingTime, int employeeTime, int premiumAcord) {
        try {
            JDBC.getBdConnection();
            String insert = "INSERT INTO pieceworksalary (idEmployeeFXp, totalEarnings, allWorkingTime, employeeTime, premiumAcord) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setInt(1, idEmployeeFXp);
            preparedStatement.setBigDecimal(2, totalEarnings);
            preparedStatement.setInt(3, allWorkingTime);
            preparedStatement.setInt(4, employeeTime);
            preparedStatement.setInt(5, premiumAcord);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }




    public void takeInfStraightPieceworkSalaryTable(int idEmployeeFXp){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM pieceworksalary WHERE idEmployeeFXp = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idEmployeeFXp);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Salary.getInstance().setHourlyRate(rs.getBigDecimal("hourlyRate"));
                Salary.getInstance().setProductionRate(rs.getInt("productionRate"));
                Salary.getInstance().setNumberOfItems(rs.getInt("numberOfItems"));
                Salary.getInstance().setNumberOfProducts(rs.getInt("numberOfProducts"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void takeInfPremiumPieceworkSalaryTable(int idEmployeeFXp){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM pieceworksalary WHERE idEmployeeFXp = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idEmployeeFXp);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Salary.getInstance().setHourlyRate(rs.getBigDecimal("hourlyRate"));
                Salary.getInstance().setProductionRate(rs.getInt("productionRate"));
                Salary.getInstance().setNumberOfItems(rs.getInt("numberOfItems"));
                Salary.getInstance().setNumberOfProducts(rs.getInt("numberOfProducts"));
                Salary.getInstance().setPerformanceBonusPercentage(rs.getInt("performanceBonusPercentage"));
                Salary.getInstance().setOverfulfillmentBonusPercentage(rs.getInt("overfulfillmentBonusPercentage"));
                Salary.getInstance().setOverfulfillmentBonusPlan(rs.getInt("overfulfillmentBonusPlan"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void takeInfProgressivePieceworkSalaryTable(int idEmployeeFXp){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM pieceworksalary WHERE idEmployeeFXp = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idEmployeeFXp);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Salary.getInstance().setHourlyRate(rs.getBigDecimal("hourlyRate"));
                Salary.getInstance().setProductionRate(rs.getInt("productionRate"));
                Salary.getInstance().setСolProductsPlan(rs.getInt("сolProductsPlan"));
                Salary.getInstance().setCoefficient(rs.getInt("coefficient"));
                Salary.getInstance().setColProductsReleased(rs.getInt("colProductsReleased"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void takeInfIndirectPieceworkSalaryTable(int idEmployeeFXp){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM pieceworksalary WHERE idEmployeeFXp = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idEmployeeFXp);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Salary.getInstance().setAncillaryHourlyRate(rs.getBigDecimal("ancillaryHourlyRate"));
                Salary.getInstance().setAuxiliaryServiceRate(rs.getInt("auxiliaryServiceRate"));
                Salary.getInstance().setProductionRateMain(rs.getInt("productionRateMain"));
                Salary.getInstance().setNumberOfItems(rs.getInt("numberOfItems"));
                Salary.getInstance().setNumberOfProducts(rs.getInt("numberOfProducts"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void takeInfAccordPieceworkSalaryTable(int idEmployeeFXp){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM pieceworksalary WHERE idEmployeeFXp = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idEmployeeFXp);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Salary.getInstance().setTotalEarnings(rs.getBigDecimal("totalEarnings"));
                Salary.getInstance().setAllWorkingTime(rs.getInt("allWorkingTime"));
                Salary.getInstance().setEmployeeTime(rs.getInt("employeeTime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void takeInfAccordPremiumPieceworkSalaryTable(int idEmployeeFXp){
        try {
            JDBC.getBdConnection();
            String query = "SELECT * FROM pieceworksalary WHERE idEmployeeFXp = ?";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(query);
            preparedStatement.setInt(1, idEmployeeFXp);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Salary.getInstance().setTotalEarnings(rs.getBigDecimal("totalEarnings"));
                Salary.getInstance().setAllWorkingTime(rs.getInt("allWorkingTime"));
                Salary.getInstance().setEmployeeTime(rs.getInt("employeeTime"));
                Salary.getInstance().setPremiumAcord(rs.getInt("premiumAcord"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }


    public void addResultPieceworkSalary(int idEmployeeFXp, BigDecimal result){
        try {
            JDBC.getBdConnection();
            String insert = "update employees set resultSalary = ?" +" WHERE idEmployee = ? ";
            PreparedStatement preparedStatement = bdConnection.prepareStatement(insert);
            preparedStatement.setBigDecimal(1, result);
            preparedStatement.setInt(2, idEmployeeFXp);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close();
        }
    }
    public void deletePieceworkSalaryTable(int idEmployeeFXp){
        try {
            JDBC.getBdConnection();
            String selectSQL = "DELETE FROM pieceworksalary   WHERE idEmployeeFXp = ?";
            PreparedStatement preparedStmt = bdConnection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, idEmployeeFXp);
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
