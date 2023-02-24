package DataBase;

import Const.Constants;

import java.sql.*;

public class CreateTableDataBase {
    private Connection connection;
    private Statement statement;
    public CreateTableDataBase (Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
        //createDB();
        createUsersDB();
        createEmployeesDB();
        createSpecialtiesDB();
        createPieceworksalaryDB();
        createTimesalaryDB();



    }


    public void createEmployeesDB(){
        try {
            String SQL = "CREATE TABLE IF NOT EXISTS `employees` (\n" +
                    "  `idEmployee` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `idUserFK` int DEFAULT NULL,\n" +
                    "  `idSpecialtieFK` int DEFAULT NULL,\n" +
                    "  `resultSalary` decimal(20,2) DEFAULT NULL,\n" +
                    "  `monthlyTariffRate` decimal(20,2) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idEmployee`),\n" +
                    "  KEY `FKUser` (`idUserFK`),\n" +
                    "  KEY `FKSpecialtie` (`idSpecialtieFK`),\n" +
                    "  CONSTRAINT `FKSpecialtie` FOREIGN KEY (`idSpecialtieFK`) REFERENCES `specialties` (`idSpecialtie`) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT `FKUser` FOREIGN KEY (`idUserFK`) REFERENCES `users` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ")";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createPieceworksalaryDB(){
        try {
            String SQL = "CREATE TABLE IF NOT EXISTS `pieceworksalary` (\n" +
                    "  `idPieceworksalary` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `idEmployeeFXp` int DEFAULT NULL,\n" +
                    "  `hourlyRate` decimal(20,2) DEFAULT NULL,\n" +
                    "  `productionRate` int DEFAULT NULL,\n" +
                    "  `numberOfItems` int DEFAULT NULL,\n" +
                    "  `numberOfProducts` int DEFAULT NULL,\n" +
                    "  `performanceBonusPercentage` int DEFAULT NULL,\n" +
                    "  `overfulfillmentBonusPercentage` int DEFAULT NULL,\n" +
                    "  `overfulfillmentBonusPlan` int DEFAULT NULL,\n" +
                    "  `сolProductsPlan` int DEFAULT NULL,\n" +
                    "  `coefficient` int DEFAULT NULL,\n" +
                    "  `colProductsReleased` int DEFAULT NULL,\n" +
                    "  `ancillaryHourlyRate` decimal(20,2) DEFAULT NULL,\n" +
                    "  `auxiliaryServiceRate` int DEFAULT NULL,\n" +
                    "  `productionRateMain` int DEFAULT NULL,\n" +
                    "  `totalEarnings` decimal(20,2) DEFAULT NULL,\n" +
                    "  `allWorkingTime` int DEFAULT NULL,\n" +
                    "  `employeeTime` int DEFAULT NULL,\n" +
                    "  `premiumAcord` int DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idPieceworksalary`),\n" +
                    "  KEY `FKemloyeeP` (`idEmployeeFXp`),\n" +
                    "  CONSTRAINT `FKemloyeeP` FOREIGN KEY (`idEmployeeFXp`) REFERENCES `employees` (`idEmployee`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ") ";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createSpecialtiesDB(){
        try {
            String SQL = "CREATE TABLE IF NOT EXISTS `specialties` (\n" +
                    "  `idSpecialtie` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `specialtieName` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idSpecialtie`)\n" +
                    ")";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createTimesalaryDB(){
        try {
            String SQL = "CREATE TABLE IF NOT EXISTS `timesalary` (\n" +
                    "  `idTimeSalary` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `idEmployeeFK` int DEFAULT NULL,\n" +
                    "  `paymentPerHour` decimal(20,2) DEFAULT NULL,\n" +
                    "  `hours` int DEFAULT NULL,\n" +
                    "  `premium` int DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`idTimeSalary`),\n" +
                    "  KEY `FKemployee` (`idEmployeeFK`),\n" +
                    "  CONSTRAINT `FKemployee` FOREIGN KEY (`idEmployeeFK`) REFERENCES `employees` (`idEmployee`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ")";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createUsersDB(){
        try {
            String SQL = "CREATE TABLE IF NOT EXISTS `users` (\n" +
                    "  `idUser` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `firstName` varchar(45) NOT NULL,\n" +
                    "  `lastName` varchar(45) NOT NULL,\n" +
                    "  `login` varchar(45) NOT NULL,\n" +
                    "  `password` varchar(45) NOT NULL,\n" +
                    "  `rolee` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
                    "  PRIMARY KEY (`idUser`)\n" +
                    ")";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private void addTableTimeSalary() {
//        if (tableExists(Constants.TIME_SALARY_TABLE)) {
//            try {
//                String SQL = "CREATE TABLE timebasedsalary" +
//                        "( " +
//                        " idTimeBasedSalary INTEGER NOT NULL AUTO_INCREMENT," +
//                        " idUser INTEGER DEFAULT NULL," +
//                        " paymentPerHour decimal(20,2) DEFAULT NULL," +
//                        " hours  INTEGER DEFAULT NULL," +
//                        " premium INTEGER DEFAULT NULL," +
//                        "PRIMARY KEY (idTimeBasedSalary) USING BTREE," +
//                        "KEY FKtimeBasedSalaryUsers (idUser)," +
//                        "CONSTRAINT FKtimeBasedSalaryUsers FOREIGN KEY (idUser) REFERENCES users (idUser) ON DELETE CASCADE ON UPDATE CASCADE" +
//                        ")";
//                statement.executeUpdate(SQL);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
