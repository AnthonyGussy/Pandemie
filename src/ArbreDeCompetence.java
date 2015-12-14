import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Victor on 04/12/2015.
 */
public class ArbreDeCompetence {
    HashMap<String, List<Competence>> competences;

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

        for (Map.Entry<String, List<Competence>> competence : competences.entrySet()) {
            for(Competence c : competence.getValue()){

                Line l = new Line();
                l.setStartX(competence.getValue().get(0).ligne*100);
                l.setStartY(competence.getValue().get(0).colone*100);
                l.setEndX(c.ligne*100);
                l.setEndY(c.colone*100);
                Jeu.root.getChildren().add(l);

                if(!Jeu.root.getChildren().contains(c.ligne+","+c.colone)) c.affichage();

            }

            if(!Jeu.root.getChildren().contains(competence.getValue().get(0))) competence.getValue().get(0).affichage();




        }


    }
    void debloquerCompetence(String compet){

        System.out.print(compet);

    }
}
