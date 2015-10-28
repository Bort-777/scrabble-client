package Client;

import by.andrew.scrabl.control.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Controller loader = new Controller();
        stage.setTitle("Client @FXML Welcome");
        stage.setScene(loader.getScene());
        stage.show();
    }
}
