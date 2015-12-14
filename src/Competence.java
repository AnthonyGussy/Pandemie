import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.*;

/**
 * Created by Victor on 20/11/2015.
 */
public class Competence {

    String nom;
    String description;
    int ligne;
    int colone;
    int[] effet;
    int cout;
    boolean debloque;
    String sommetLie;
    Circle compet;
    Group g;
    ArbreDeCompetence arbreDeCompetence;
    static boolean cliquer = false;


    Competence(String document,int numeroCompetence,ArbreDeCompetence arbre){

        arbreDeCompetence = arbre;
        effet = new int[3];
        InputStream flux= null;
        String lignes;
        int i=0;

        try {

            flux = new FileInputStream(document);
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            while ((lignes=buff.readLine())!=null && i!=numeroCompetence+1){

                if(i == numeroCompetence){

                    String info[] = lignes.split(";");
                    nom = info[0];
                    description = info[1];
                    ligne = Integer.parseInt(info[2]);
                    colone = Integer.parseInt(info[3]);
                    effet[0] = Integer.parseInt(info[4]);
                    effet[1] = Integer.parseInt(info[5]);
                    effet[2] = Integer.parseInt(info[6]);
                    cout =  Integer.parseInt(info[7]);
                    if(info.length == 9) sommetLie = info[8];
                    else sommetLie = null;
                    if(ligne==10) debloque = true;
                    else debloque = false;

                }

                ++i;

            }
            buff.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    void affichage(){

        compet = new Circle(ligne*100,colone*100,50);
        compet.setFill(new ImagePattern(new Image("file:Compet.png"), 0, 0, 1, 1, true));
        compet.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                if(cliquer) {
                    Jeu.root.getChildren().remove(Jeu.root.getChildren().size() - 1, Jeu.root.getChildren().size());
                    cliquer = false;
                }
                g = new Group();
                Rectangle r = new Rectangle();
                r.setX(ligne + 200);
                r.setY(colone + 200);
                r.setWidth(100);
                r.setHeight(100);
                Text nomR = new Text(nom + "\n" + description);
                nomR.setFill(Color.AQUA);
                nomR.setX(ligne + 210);
                nomR.setY(colone + 250);
                Button b = new Button("Achat");
                b.setLayoutX(ligne + 210);
                b.setLayoutY(colone + 280);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        if(debloque) arbreDeCompetence.debloquerCompetence(ligne+""+colone);
                    }
                });
                g.getChildren().add(r);
                g.getChildren().add(nomR);
                g.getChildren().add(b);
                Jeu.root.getChildren().add(g);


            }

        });
        compet.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                Jeu.root.getChildren().remove(Jeu.root.getChildren().size() - 1, Jeu.root.getChildren().size());


            }

        });
        compet.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                g = new Group();
                Rectangle r = new Rectangle();
                r.setX(ligne + 200);
                r.setY(colone + 200);
                r.setWidth(100);
                r.setHeight(100);
                Text nomR = new Text(nom + "\n" + description);
                nomR.setFill(Color.AQUA);
                nomR.setX(ligne + 210);
                nomR.setY(colone + 250);
                Button b = new Button("Achat");
                b.setLayoutX(ligne + 210);
                b.setLayoutY(colone + 280);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        if (debloque) arbreDeCompetence.debloquerCompetence(ligne+""+colone);
                    }
                });
                g.getChildren().add(r);
                g.getChildren().add(nomR);
                g.getChildren().add(b);
                Jeu.root.getChildren().add(g);
                cliquer = true;


            }

        });
        Jeu.root.getChildren().add(compet);

    }
    void applicationCompetence(){}
}
