package by.andrew.scrabl.control;

import ch.makery.adress.model.ServerComands;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;

public class Client {

    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    private Socket socket;
    private DataProv dataProvider;

    public Client(String host, int port) {

        try {
            socket = new Socket(host, port);
            ClientSet();

        } catch (IOException e) {

        }
    }

    public void stop() {
        try {
            socket.close();

            // System.exit(0);
        } catch (IOException e) {
            System.out.println(e);// "can't to stop"
        }
    }

    public final void ClientSet() {
        try {
            socket.setSoTimeout(100000);
        } catch (SocketException e1) {

        }
        try {
            reader = new ObjectInputStream(socket.getInputStream());
            writer = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {

        }
    }

    public void sendCommand(int command) {
        // "waiting command");

        if (socket.isClosed()) {
            return;
        }
        try {
            try {

                switch (command) {
                    case ServerComands.NEW_USER:
                        writer.writeObject(ServerComands.NEW_USER);
                        break;
                    case ServerComands.GET_DATA:
                        writer.writeObject(ServerComands.GET_DATA);
                        System.out.println("хотим читать поле");
                        String field = (String) reader.readObject();
                        System.out.println("прочитали поле");
                        String ask = (String) reader.readObject();

                        dataProvider.setField(field);
                        dataProvider.setAsk(ask);

                        break;
                    case ServerComands.ANSWER:
                        writer.writeObject(ServerComands.ANSWER);
                        String tmp1 = (String) dataProvider.getAnswer();
                        ArrayList data = (ArrayList) dataProvider.getData();
                        //writer.writeObject(new MyPackage(tmp1));
                        writer.writeObject(tmp1);
                        writer.writeObject(data);
                        System.out.println("Send 3");
                        break;

                    default:
                        break;

                }
            } catch (SocketException e) {
                // ("disconnect");
                return;
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (InterruptedByTimeoutException e2) {
            sendCommand(-1);
        } catch (IOException e) {
            // log.error("bad data");
        }
        if (!socket.isClosed()) {
            return;
        }
    }

    public void setDataProv(DataProv dataProvider) {
        // TODO Auto-generated method stub
        this.dataProvider = dataProvider;

    }
}
