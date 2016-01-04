package Vue;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Jeu j = new Jeu(primaryStage);

    }

    public static void main(String[] args) { launch(args); }

}
