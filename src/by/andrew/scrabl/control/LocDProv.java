package by.andrew.scrabl.control;

import ch.makery.adress.model.ServerComands;
import java.util.List;

import by.andrew.scrabl.control.GameControl;

/**
 *
 * @author Администратор
 */
public class LocDProv implements DataProv {

    Client client;
    private GameControl gameControl;

    public LocDProv(Client client) {
        // TODO Auto-generated constructor stub
        this.client = client;
    }

    @Override
    public void sendCommand(int command) {
        client.sendCommand(command);

    }

    @Override
    public void update() {
        sendCommand(ServerComands.GET_DATA);
    }

    public Object getAnswer() {

        return gameControl.getAnswer();
    }

    public Object getData() {

        return gameControl.getData();
    }

    @Override
    public void setGameControl(GameControl gameControl) {
        this.gameControl = gameControl;
    }

    @Override
    public void setAsk(String ask) {
        gameControl.setAsk(ask);
    }

    @Override
    public void setField(String field) {
        gameControl.setField(field);
    }

}
