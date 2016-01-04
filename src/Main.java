import Modele.Jeu;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe main
 * C'est dans cette classe que le programme démarre et instancie un objet Jeu
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Jeu j = new Jeu(primaryStage);
    }

    public static void main(String[] args) { launch(args); }
}
