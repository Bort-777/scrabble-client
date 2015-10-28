package by.andrew.scrabl.control;

import by.andrew.scrabl.control.Controller.StartServer;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    private String fieldText;

    public void setField(String field) {
        this.fieldText = field;
        String ch;
        System.out.println(fieldText);
        for (int iterator = 0; iterator < 12 * 12; iterator++) {
            ch = fieldText.substring(iterator, iterator + 1);
            System.out.println(iterator);
            labels.get(iterator).setText(ch);

        }
    }

    public Scene getScene() {
        labels = new ArrayList<Label>();
        Group root = new Group();
        Label tmp;
        for (int iterator = 0; iterator < 12; iterator++) {
            for (int j = 0; j < 12; j++) {
                tmp = createLabel(iterator, j);
                labels.add(tmp);
                root.getChildren().add(tmp);
            }
        }

        root.getChildren().add(createPortField(650, 0));
        root.getChildren().add(createAnswerField(650, 30));
        root.getChildren().add(createConnectButton(650, 90));
        root.getChildren().add(createDisconnectButton(720, 90));
        root.getChildren().add(createUpdateButton(650, 120));

        root.getChildren().add(createYesButton(650, 150));
        root.getChildren().add(createNoButton(700, 150));
        root.getChildren().add(createNextButton(750, 150));

        root.getChildren().add(createSendButton(650, 210));

        root.getChildren().add(createAsk(300, 500));
        Scene scene = new Scene(root, 850, 625);
        return scene;
    }

    public class StartServer extends Thread {

        private Client client;

        StartServer() {
            client = new Client(getAnswer(), Integer.parseInt((portField.getText())));
            textField.setText("слово");

        }

        public void run() {

            DataProv dataProvider = new LocDProv(client);

            client.setDataProv(dataProvider);
            gameControl.setDataProv(dataProvider);
            dataProvider.setGameControl(gameControl);
            gameControl.run();
        }

        public void stopServer() {
            client.stop();

        }

    }

    @FXML
    private Text askText;
    @FXML
    private TextField portField;
    @FXML
    private TextField textField;

    private StartServer tred;
    private GameControl gameControl;
    private ArrayList<Label> labels;

    public TextField createPortField(double x, double y) {
        portField = new TextField();
        portField.setText("8080");
        portField.setLayoutX(x);
        portField.setLayoutY(y);

        return portField;
    }

    public TextField createAnswerField(double x, double y) {
        textField = new TextField();
        textField.setText("localhost");
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setStyle("-fx-background-color: orange;");

        return textField;
    }

    public Text createAsk(double x, double y) {
        askText = new Text();
        askText.setText("-");
        askText.setLayoutX(x);
        askText.setLayoutY(y);
        askText.setStyle("<size=52>");
        return askText;
    }

    public Button createConnectButton(double x, double y) {
        Button connectButton = new Button();
        connectButton.setText("Connect");
        connectButton.setLayoutX(x);
        connectButton.setLayoutY(y);
        connectButton.setOnMousePressed(new EventHandler() {

            @Override
            public void handle(Event event) {
                connect();
            }
        });
        return connectButton;
    }

    public Button createDisconnectButton(double x, double y) {
        Button button = new Button();
        button.setText("Disconect");
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnMousePressed(new EventHandler() {

            @Override
            public void handle(Event event) {
                disconect();
            }
        });
        return button;

    }

    public Button createNextButton(double x, double y) {
        Button button = new Button();
        button.setText("Next");
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnMousePressed(new EventHandler() {

            @Override
            public void handle(Event event) {
                textField.setText("Далее");
                sendAnswer();
            }
        });
        return button;

    }

    public Button createUpdateButton(double x, double y) {
        Button button = new Button();
        button.setText("Update");
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnMousePressed(new EventHandler() {

            @Override
            public void handle(Event event) {
                update();

            }
        });
        return button;

    }

    public Button createSendButton(double x, double y) {
        Button button = new Button();
        button.setText("Send");
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnMousePressed(new EventHandler() {

            @Override
            public void handle(Event event) {
                sendAnswer();
            }
        });
        return button;

    }

    public Button createYesButton(double x, double y) {
        Button button = new Button();
        button.setText("Yes");
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnMousePressed(new EventHandler() {

            @Override
            public void handle(Event event) {
                textField.setText("да");
                sendAnswer();
            }
        });
        return button;

    }

    public Button createNoButton(double x, double y) {
        Button button = new Button();
        button.setText("No");
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOnMousePressed(new EventHandler() {

            @Override
            public void handle(Event event) {
                textField.setText("нет");
                sendAnswer();
            }
        });
        return button;

    }

    @FXML
    void connect() {

        gameControl = new GameControl(this);
        askText.setText("Connect..");
        tred = new StartServer();
        tred.start();

    }

    @FXML
    protected void disconect() {

        askText.setText("Disconnect..");
        tred.stopServer();

    }

    @FXML
    protected void update() {

        gameControl.update();

    }

    @FXML
    protected void sendAnswer() {
        try {
            gameControl.sendAnswer();
        } catch (NullPointerException e) {
        }
    }

    public String getAnswer() {
        return textField.getText();
    }

    public void setAsk(String ask) {
        askText.setText(ask);
    }

    public Label createLabel(int x, int y) {
        Integer t = x * y;
        Label label = LabelBuilder.create() // создание билдера для Label 
                .text(x + "*" + y) // текстовое значение 
                .prefWidth(50) // возможная ширина 
                .prefHeight(30) // возможная высота 
                .alignment(Pos.CENTER) // выравнивание содержимого по центру 
                .layoutX(x * 50) // задание  коорд. Х 
                .layoutY(y * 30) // задание  коорд. Y 
                .style("-fx-background-color: orange;") // зарисуем фон в оранжевый 
                .build(); // создадим из билдера сам объект класса Label 

        label.setOnMousePressed((Event event) -> {
            gameControl.setData(x, y);
            askText.setText(askText.getText() + " (" + (x + 1) + "," + (y + 1) + ")");

        });

        return label;
    }
}
