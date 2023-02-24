package ServerConnection;

import ServerConnection.ServerConnect;

public class StartServer {
    public static void main(String[] args) {
      ServerConnect serverConnect = new ServerConnect();
      serverConnect.startServer();
      serverConnect.connectNewClientInToServer();
      serverConnect.closeAll();
    }
//    CREATE TABLE `salary`.`time-based_salary` (
//            `idTime-based_salary` INT NOT NULL AUTO_INCREMENT,
//  `idUser` INT NULL DEFAULT NULL,
//  `paymentPerHour` DECIMAL NULL DEFAULT NULL,
//  `hours` INT NULL DEFAULT NULL,
//  `premium` INT NULL DEFAULT NULL,
//    PRIMARY KEY (`idTime-based_salary`));
}
