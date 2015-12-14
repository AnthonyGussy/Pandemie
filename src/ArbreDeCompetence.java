import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Victor on 04/12/2015.
 */
public class ArbreDeCompetence {
    HashMap<String, ArrayList<Competence>> competences;

    public ArbreDeCompetence(String document){

        competences = new HashMap<>();
        ArrayList<Competence> listCompetence = new ArrayList<>();

        for(int i=0;i<3;++i){

            Competence c = new Competence(document,i,this);
            listCompetence.add(c);

        }

        for(Competence c: listCompetence){

            competences.put(c.ligne+","+c.colone, new ArrayList<>());
            competences.get(c.ligne + ","+c.colone).add(c);
            if(c.sommetLie != null) {
                String sommet[] = c.sommetLie.split(",");

                for (String s : sommet) {

                    competences.get(c.ligne + ","+c.colone).add(listCompetence.get(Integer.parseInt(s)));

                }
            }


        }


    }

    void affichage(){

        Jeu.scene.setFill(new ImagePattern(new Image("file:fond2.png"), 0, 0, 1, 1, true));
        for (Map.Entry<String, ArrayList<Competence>> competence : competences.entrySet()) {
            for(Competence c : competence.getValue()){

                Line l = new Line();
                l.setStartX(competence.getValue().get(0).colone*200);
                l.setStartY(768-competence.getValue().get(0).ligne*100);
                l.setEndX(c.colone*200);
                l.setEndY(768-c.ligne*100);
                Jeu.root.getChildren().add(l);

                if(!Jeu.root.getChildren().contains(c.ligne+","+c.colone)) c.affichage();

            }

            if(!Jeu.root.getChildren().contains(competence.getValue().get(0))) competence.getValue().get(0).affichage();




        }


    }
    void debloquerCompetence(int ligne, int colone){

        competences.get(ligne+","+colone).get(0).setAchete();
        if(colone > 1 ) {

            boolean debloque = true;
            ArrayList<Competence> c = competences.get(ligne+1 + "," + (colone - 1));
            for(int i=1;i<c.size();++i){

                if(!c.get(i).debloque || !c.get(i).achete) debloque = false;

            }
            if(debloque) {

                c.get(0).setDebloque();
                System.out.print("debloquer");
            }

        }



        boolean debloque = true;
        ArrayList<Competence> c = competences.get(ligne+1 + "," + (colone));
        if(c != null && c.size() > 1) {
            for (int i = 1; i < c.size(); ++i) {

                if (!c.get(i).debloque || !c.get(i).achete) debloque = false;

            }
            if (debloque) {
                System.out.print("debloquer");
                c.get(0).setDebloque();
            }
        }

    }
}
