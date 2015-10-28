package by.andrew.scrabl.control;

import java.util.List;

import ch.makery.adress.model.ServerComands;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameControl {

    private DataProv dataProvider;
    private Controller buttonControl;
    private ArrayList data;

    public GameControl(Controller buttonControl) {
        this.buttonControl = buttonControl;
        data = new ArrayList();
    }

    public void setDataProv(DataProv dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void run() {
        // TODO Auto-generated method stub
        System.out.println("Start game");

        newUser();
        Thread update = new Thread() {
            public void run() {

                try {
                    while (true) {
                        //update();
                        sleep(10000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                setDaemon(true);
            }

        };
        update.start();

    }

    public String getAnswer() {
        return buttonControl.getAnswer();
    }

    public Object getData() {
        ArrayList tmp = new ArrayList(data);

        data.clear();
        System.out.println(tmp.size());
        return tmp;
    }

    public void update() {
        dataProvider.sendCommand(ServerComands.GET_DATA);
    }

    public void sendAnswer() {
        //if (2*getAnswer().length()==data.size()) {
        dataProvider.sendCommand(ServerComands.ANSWER);
        // }
    }

    public void setAsk(String ask) {
        buttonControl.setAsk(ask);
    }

    private void newUser() {
        dataProvider.sendCommand(ServerComands.NEW_USER);
    }

    public void setData(int x, int y) {
        data.add(x);
        data.add(y);
    }

    public void setField(String field) {
        buttonControl.setField(field);
    }
}
