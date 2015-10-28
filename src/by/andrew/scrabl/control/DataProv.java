package by.andrew.scrabl.control;

import java.util.List;

import by.andrew.scrabl.control.GameControl;

public interface DataProv {

    void sendCommand(int oPEN_TABLE);

    public Object getAnswer();

    public Object getData();

    public void update();

    public void setGameControl(GameControl gameControl);

    public void setAsk(String ask);

    public void setField(String field);

}
