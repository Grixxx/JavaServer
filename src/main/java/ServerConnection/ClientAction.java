package ServerConnection;

import ConnectToServer.Request;
import DataBase.*;
import Models.NowUser;
import Models.Salary;
import Models.User;
import Result.Calculate;
import ConnectToServer.RequestType;


import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;

public class ClientAction extends Thread {
    private static Socket socket;
    private static ClientAction server;
    private static BufferedWriter output;
    private static BufferedReader input;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private static Calculate calculate;
    private static DataBaseAction databaseAction;
    private static UserAction databaseUserAction;
    private static EmployeeAction databaseEmployeeAction;
    private static SpecialtiesAction databaseSpecialtiesAction;
    private static TimeSalaryAction timeSalaryAction;
    private static PieceWorksalaryAction pieceWorksalaryAction;

    private static Request r = new Request();


    private final int currentCountClient = ServerConnect.countClient++;


    public ClientAction(Socket socket, DataBaseAction dateBaseAction, UserAction databaseUserAction,
                        EmployeeAction databaseEmployeeAction, SpecialtiesAction databaseSpecialtiesAction,
                        TimeSalaryAction timeSalaryAction, PieceWorksalaryAction pieceWorksalaryAction) throws IOException{
        this.socket = socket;
        this.databaseAction = dateBaseAction;
        this.databaseUserAction = databaseUserAction;
        this.databaseEmployeeAction = databaseEmployeeAction;
        this.databaseSpecialtiesAction = databaseSpecialtiesAction;
        this.timeSalaryAction = timeSalaryAction;
        this.pieceWorksalaryAction = pieceWorksalaryAction;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        start();
    }



    @Override
    public void run()  {
        while (socket.isConnected()) {
            //RequestType requestType = (RequestType)ois.readObject();
            switch (getMessage().recMessage) {
                case "РЕГИСТРАЦИЯ":
                    registrUSER();
                    break;
                case "ВХОД":
                    authUSER();
                    break;

                   case "ПРОВЕРКА ЮЗЕРА НА РАБОТНИКА":
                        checkNowUserIsEmployee();
                        break;


                    case "ВСЕ(АДМИН) ПОЛЬЗОВАТЕЛИ":
                        try {
                            showAllUsers();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "добавить(АДМИН) пользователя":
                        registrUSER();
                        break;
                    case "удалить(АДМИН) пользователя":
                        deleteUser();
                        break;
                    case "сделать(АДМИН) работником":
                        addEmployee();
                        break;
                    case "ВСЕ(АДМИН) РАБОТНИКИ":
                        showAllEmployees();
                        break;
                    case "удалить(АДМИН) работника":
                        deleteEmployee();
                        break;
                    case "ВСЕ(АДМИН) ВАКАНСИИ":
                        showAllSpecialties();
                        break;
                    case "добавить(АДМИН) вакансию":
                        addSpecialtie();
                        break;
                    case "удалить(АДМИН) вакансию":
                        deleteSpecialtie();
                        break;
                    case "ПРИСВОИТЬ(АДМИН) ВАКАНСИЮ РАБОТНИКУ":
                        addSpecialtieToEmployee();
                        break;
                    case "Рассчет повременной ЗП":
                        calculateTimeSalary();
                        break;
                    case "Рассчет повременной-премиальной ЗП":
                        calculateTimePremiumSalary();
                        break;
                    case "Рассчет прямой сдельной ЗП":
                        calculateStraightPieceworkSalary();
                        break;
                    case "Рассчет сдельно-премиальной ЗП":
                        calculatePremiumPieceworkSalary();
                        break;
                    case "Рассчет сдельно-прогрессивной ЗП":
                        calculateProgressivePieceworkSalary();
                        break;
                    case "Рассчет косвенно-сдельной ЗП":
                        calculateIndirectPieceworkSalary();
                        break;
                    case "Рассчет аккордной ЗП":
                        calculateAddAccordPieceworkSalary();
                        break;
                    case "Рассчет аккодно-премиальной ЗП":
                        calculateAccordPremiumPieceworkSalary();
                        break;
                    case "Рассчет месечной тарифной ставки ЗП":
                        calculateMonthlyTariffRate();
                        break;


                    case "ПРОВЕРКАюзера":
                        showNowUser();
                        break;
                    case "поменять(ЮЗЕР) пароль":
                        changePasswordNowUser();
                        break;
                    case "удалить(ЮЗЕР) пользователя":
                        deleteNowUser();
                        break;
                    case "ВСЕ(ЮЗЕР) ВАКАНСИИ":
                        showAllSpecialties();
                        break;
                    case "СОЗДАНИЕ РАБОТНИКА И ПРИСВОИТЬ(ЮЗЕР) ВАКАНСИЮ РАБОТНИКУ":
                        addSpecialtieToUser();
                        break;
                    case "удалить(ЮЗЕР) работника":
                        deleteNowEmployee();
                        break;
                    case "Зарпалата сотрудника":
                        showResultSalaryNowEmployee();
                        break;
                    case "EXIT":
                       closeEverything();
                      return;
            }
        }
    }

//    private void sendMessageToClient(String messageToClient) {
//        try {
//            output.write(messageToClient + "\n");
//            output.flush();
//        } catch (IOException ignored) {}
//    }
    public static void sendMessageToClient(String send){
        try{
            r.recMessage=send;
            oos.writeObject(r);
            oos.reset();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error sending message to the server");
        }
    }

    private Request getMessage(){
        Request req;
        try {
            req = (Request) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return req;
    }
    private void authUSER() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        if(databaseUserAction.authorizationUser(messageFromClient[0],messageFromClient[1])){
            if (NowUser.getInstance().getRolee().equals("user")) {
                ServerConnect.serverList.get(currentCountClient).sendMessageToClient("trueUser" + " " + NowUser.getInstance().getIdUser()+" "+
                        NowUser.getInstance().getFname() + " " + NowUser.getInstance().getLname() + " " + NowUser.getInstance().getLogin() + " " + NowUser.getInstance().getPasswrod());

                // String result = nowUserAction.addNowUser(messageFromClient[0],messageFromClient[1]);
            }else{
                ServerConnect.serverList.get(currentCountClient).sendMessageToClient("trueAdmin");

            }
        }
        else{
            ServerConnect.serverList.get(currentCountClient).sendMessageToClient("false");

        }

    }
    private void registrUSER() {
        String[] messageFromClient;
        String str = getMessage().recMessage;
        System.out.println(str);
        messageFromClient = str.split(" ");
        databaseUserAction.receiveMessageFromRegistration(messageFromClient[0],messageFromClient[1],messageFromClient[2],messageFromClient[3], "user");{

        }

    }


//    private void authUSER() {
//        try {
//            User user = (User)ois.readObject();
//
//           if( databaseUserAction.authorizationUser(user)){
//               if(NowUser.getInstance().getRolee().equals("user")){
//                   ServerConnect.serverList.get(currentCountClient).sendMessageToClient("trueUser");
//                   oos.writeObject(RequestType.AUTH);
//                   oos.writeObject(NowUser.getInstance());
//                   System.out.println(NowUser.getInstance().getIdUser());
//               }else{
//                   ServerConnect.serverList.get(currentCountClient).sendMessageToClient("trueAdmin");
//               }
//
//           }else{
//               ServerConnect.serverList.get(currentCountClient).sendMessageToClient("false");
//           }
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }


//    private void registrUSER() {
//        try {
//            User user = (User)ois.readObject();
//            databaseUserAction.receiveMessageFromRegistration(user);{
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }


    private void showNowUser() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        LinkedList<String> nowUser = new LinkedList<>();
        databaseUserAction.showNowUser(nowUser, Integer.parseInt(messageFromClient[0]));


        ServerConnect.serverList.get(currentCountClient).sendMessageToClient(String.valueOf(nowUser.size()));

        for (String n : nowUser) {
            ServerConnect.serverList.get(currentCountClient).sendMessageToClient(n);
        }
    }
    private void showAllUsers() throws SQLException {

        LinkedList<String> users = new LinkedList<>();
        databaseUserAction.showAllUsers(users);

        ServerConnect.serverList.get(currentCountClient).sendMessageToClient(String.valueOf(users.size()));

        for(String u:users){
            ServerConnect.serverList.get(currentCountClient).sendMessageToClient(u);
        }
    }
    private void showAllEmployees() {

        LinkedList<String> employee = new LinkedList<>();
        databaseEmployeeAction.showAllEmployees(employee);


        ServerConnect.serverList.get(currentCountClient).sendMessageToClient(String.valueOf(employee.size()));

        for(String e:employee){
            ServerConnect.serverList.get(currentCountClient).sendMessageToClient(e);
        }
    }
    private void showAllSpecialties() {

        LinkedList<String> specialties = new LinkedList<>();
        databaseSpecialtiesAction.showAllSpecialties(specialties);


        ServerConnect.serverList.get(currentCountClient).sendMessageToClient(String.valueOf(specialties.size()));

        for(String s:specialties){
            ServerConnect.serverList.get(currentCountClient).sendMessageToClient(s);
        }
    }
    private void showResultSalaryNowEmployee(){
        String[] messageFromClient;
        Salary.getInstance().setResultSalary(BigDecimal.valueOf(0));
        messageFromClient = getMessage().recMessage.split(" ");
        if(databaseEmployeeAction.showResultSalaryNowEmployee(Integer.parseInt(messageFromClient[0])) == true) {
            ServerConnect.serverList.get(currentCountClient).sendMessageToClient(String.valueOf(Salary.getInstance().getResultSalary()));
        } else {
            ServerConnect.serverList.get(currentCountClient).sendMessageToClient(String.valueOf(Salary.getInstance().getResultSalary()));
        }

    }
//    private void addUser() {
//        String[] messageFromClient;
//        try {
//            messageFromClient = input.readLine().split(" ");
//            databaseUserAction.addUserInDataBase(messageFromClient[0],messageFromClient[1],messageFromClient[2],messageFromClient[3]);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private void addEmployee() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        databaseEmployeeAction.addEmployees(Integer.parseInt(messageFromClient[0]));

    }
    private void addSpecialtie() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        databaseSpecialtiesAction.addSpecialties(messageFromClient[0]);
    }
    private void addSpecialtieToEmployee(){
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        databaseSpecialtiesAction.addSpecialtieToEmployee(Integer.parseInt(messageFromClient[0]), Integer.parseInt(messageFromClient[1]));
    }
    public void addSpecialtieToUser() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        databaseEmployeeAction.deleteEmployeesNowUser(Integer.parseInt(messageFromClient[0]));
        databaseEmployeeAction.addEmployeesNowUser(Integer.parseInt(messageFromClient[0]));
        databaseEmployeeAction.checkNowUserIsEmployee(Integer.parseInt(messageFromClient[0]));
        databaseSpecialtiesAction.addSpecialtieToEmployeeNowUser(NowUser.getInstance().getIdEmployee(), Integer.parseInt(messageFromClient[1]) );
        databaseEmployeeAction.checkEmployeeIsSpecialtie(NowUser.getInstance().getIdEmployee());
        databaseSpecialtiesAction.checkSpecialtie(NowUser.getInstance().getIdSpecialti());

    }
    private void changePasswordNowUser(){
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        databaseUserAction.changePasswordNowUser(Integer.parseInt(messageFromClient[0]), messageFromClient[1]);
    }
    private void checkNowUserIsEmployee(){
        String[] messageFromClient;
        NowUser.getInstance().setIdEmployee(0);
        NowUser.getInstance().setIdSpecialti(0);
        NowUser.getInstance().setSpecialtieName("ВыНеРаботаете");
        messageFromClient = getMessage().recMessage.split(" ");
        databaseEmployeeAction.checkNowUserIsEmployee(Integer.parseInt(messageFromClient[0]));
        databaseEmployeeAction.checkEmployeeIsSpecialtie(NowUser.getInstance().getIdEmployee());
        databaseSpecialtiesAction.checkSpecialtie(NowUser.getInstance().getIdSpecialti());
        ServerConnect.serverList.get(currentCountClient).sendMessageToClient(NowUser.getInstance().getSpecialtieName()+" " +NowUser.getInstance().getIdEmployee());
    }

    private void deleteNowUser() {
        try {
            databaseUserAction.deleteUser(Integer.parseInt(getMessage().recMessage));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteUser() {
        try {
            databaseUserAction.deleteUser(Integer.parseInt(getMessage().recMessage));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteEmployee() {
        databaseEmployeeAction.deleteEmployees(Integer.parseInt(getMessage().recMessage));
    }
    private void deleteNowEmployee() {
        databaseEmployeeAction.deleteEmployees(Integer.parseInt(getMessage().recMessage));
    }
    private void deleteSpecialtie() {
        databaseSpecialtiesAction.deleteSpecialtie(Integer.parseInt(getMessage().recMessage));
    }



    private void calculateTimeSalary() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        String paymentPerHourString = messageFromClient[1];
        BigDecimal paymentPerHour = new BigDecimal(paymentPerHourString);
        timeSalaryAction.deleteTimeSalaryTable(Integer.parseInt(messageFromClient[0]));
        timeSalaryAction.addInfTimeSalaryTable(Integer.parseInt(messageFromClient[0]), paymentPerHour, Integer.parseInt(messageFromClient[2]));
        timeSalaryAction.takeInfTimeSalaryTable(Integer.parseInt(messageFromClient[0]));
        timeSalaryAction.addResultTimeSalary(Integer.parseInt(messageFromClient[0]), Calculate.calculateTimeSalary());


    }
    private void calculateTimePremiumSalary() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        String paymentPerHourString = messageFromClient[1];
        BigDecimal paymentPerHour = new BigDecimal(paymentPerHourString);
        timeSalaryAction.deleteTimeSalaryTable(Integer.parseInt(messageFromClient[0]));
        timeSalaryAction.addInfTimePremiumSalaryTable(Integer.parseInt(messageFromClient[0]), paymentPerHour, Integer.parseInt(messageFromClient[2]), Integer.parseInt(messageFromClient[3]));
        timeSalaryAction.takeInfTimePremiumSalaryTable(Integer.parseInt(messageFromClient[0]));
        timeSalaryAction.addResultTimePremiumSalaryTable(Integer.parseInt(messageFromClient[0]), Calculate.calculateTimePremiumSalary());


    }
    private void calculateStraightPieceworkSalary() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        String paymentPerHourString = messageFromClient[1];
        BigDecimal paymentPerHour = new BigDecimal(paymentPerHourString);
        pieceWorksalaryAction.deletePieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addStraightPieceworkSalaryTable(Integer.parseInt(messageFromClient[0]), paymentPerHour, Integer.parseInt(messageFromClient[2]),
                Integer.parseInt(messageFromClient[3]),Integer.parseInt(messageFromClient[4]));
        pieceWorksalaryAction.takeInfStraightPieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addResultPieceworkSalary(Integer.parseInt(messageFromClient[0]), Calculate.calculateStraightPieceworkSalary());


    }
    private void calculatePremiumPieceworkSalary() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        String paymentPerHourString = messageFromClient[1];
        BigDecimal paymentPerHour = new BigDecimal(paymentPerHourString);
        pieceWorksalaryAction.deletePieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addPremiumPieceworkSalaryTable(Integer.parseInt(messageFromClient[0]), paymentPerHour, Integer.parseInt(messageFromClient[2]),
                Integer.parseInt(messageFromClient[3]),Integer.parseInt(messageFromClient[4]),Integer.parseInt(messageFromClient[5]),
                Integer.parseInt(messageFromClient[6]),Integer.parseInt(messageFromClient[7]));

        pieceWorksalaryAction.takeInfPremiumPieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addResultPieceworkSalary(Integer.parseInt(messageFromClient[0]), Calculate.calculatePremiumPieceworkSalary());


    }
    private void calculateProgressivePieceworkSalary() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        String paymentPerHourString = messageFromClient[1];
        BigDecimal paymentPerHour = new BigDecimal(paymentPerHourString);
        pieceWorksalaryAction.deletePieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addProgressivePieceworkSalaryTable(Integer.parseInt(messageFromClient[0]), paymentPerHour, Integer.parseInt(messageFromClient[2]),
                Integer.parseInt(messageFromClient[3]),Integer.parseInt(messageFromClient[4]),Integer.parseInt(messageFromClient[5]));
        pieceWorksalaryAction.takeInfProgressivePieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addResultPieceworkSalary(Integer.parseInt(messageFromClient[0]), Calculate.calculateProgressivePieceworkSalary());


    }
    private void calculateIndirectPieceworkSalary() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        String paymentPerHourString = messageFromClient[1];
        BigDecimal paymentPerHour = new BigDecimal(paymentPerHourString);
        pieceWorksalaryAction.deletePieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addIndirectPieceworkSalaryTable(Integer.parseInt(messageFromClient[0]), paymentPerHour, Integer.parseInt(messageFromClient[2]),
                Integer.parseInt(messageFromClient[3]),Integer.parseInt(messageFromClient[4]),Integer.parseInt(messageFromClient[5]));
        pieceWorksalaryAction.takeInfIndirectPieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addResultPieceworkSalary(Integer.parseInt(messageFromClient[0]), Calculate.calculateIndirectPieceworkSalary());


    }
    private void calculateAddAccordPieceworkSalary() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        String paymentPerHourString = messageFromClient[1];
        BigDecimal paymentPerHour = new BigDecimal(paymentPerHourString);
        pieceWorksalaryAction.deletePieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addAccordPieceworkSalaryTable(Integer.parseInt(messageFromClient[0]), paymentPerHour, Integer.parseInt(messageFromClient[2]), Integer.parseInt(messageFromClient[3]));
        pieceWorksalaryAction.takeInfAccordPieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addResultPieceworkSalary(Integer.parseInt(messageFromClient[0]), Calculate.calculateAccordPieceworkSalary());


    }
    private void calculateAccordPremiumPieceworkSalary() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        String paymentPerHourString = messageFromClient[1];
        BigDecimal paymentPerHour = new BigDecimal(paymentPerHourString);
        pieceWorksalaryAction.deletePieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addAccordPremiumPieceworkSalaryTable(Integer.parseInt(messageFromClient[0]), paymentPerHour, Integer.parseInt(messageFromClient[2]),
                Integer.parseInt(messageFromClient[3]),Integer.parseInt(messageFromClient[4]));
        pieceWorksalaryAction.takeInfAccordPremiumPieceworkSalaryTable(Integer.parseInt(messageFromClient[0]));
        pieceWorksalaryAction.addResultPieceworkSalary(Integer.parseInt(messageFromClient[0]), Calculate.calculateAccordPremiumPieceworkSalary());


    }
    private void calculateMonthlyTariffRate() {
        String[] messageFromClient;
        messageFromClient = getMessage().recMessage.split(" ");
        String monthlyTariffRateFirstClassString = messageFromClient[1];

        BigDecimal monthlyTariffRateFirstClass = new BigDecimal(monthlyTariffRateFirstClassString);
        BigDecimal result = monthlyTariffRateFirstClass.multiply(new BigDecimal(Integer.parseInt(messageFromClient[2])));
        databaseAction.addResultMonthlyTariffRate(Integer.parseInt(messageFromClient[0]), result);

    }


    private void closeEverything(){
        try{
            if( input !=null)  input.close();
            if(output !=null) output.close();
            if(socket != null) socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
