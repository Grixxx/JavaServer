package ServerConnection;

import DataBase.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerConnect {
    public static LinkedList<ClientAction> serverList = new LinkedList<>();
    public static  int countClient=0;

    protected static ServerSocket server;


    public void startServer(){
        try {
            server = new ServerSocket(4303, 20);
            System.out.println("Сервер запускается ...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void connectNewClientInToServer(){
        DataBaseAction databaseAction = new DataBaseAction();
        UserAction databaseUserAction = new UserAction();
        EmployeeAction databaseEmployeeAction = new EmployeeAction();
        SpecialtiesAction databaseSpecialtiesAction = new SpecialtiesAction();
        TimeSalaryAction timeSalaryAction = new TimeSalaryAction();
        PieceWorksalaryAction pieceWorksalaryAction = new PieceWorksalaryAction();
        try {
            while (true) {
                Socket socket = server.accept();

                try {
                    serverList.add(new ClientAction(socket,databaseAction, databaseUserAction, databaseEmployeeAction, databaseSpecialtiesAction, timeSalaryAction, pieceWorksalaryAction));
                    System.out.println("Клиент подключился !");
                } catch (IOException e) {

                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void closeAll(){
        try {
            server.close();
            System.out.println("Сервер остановился !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}