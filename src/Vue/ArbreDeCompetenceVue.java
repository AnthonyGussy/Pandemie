package Vue;

import Modele.ArbreDeCompetenceModele;
import Modele.CompetenceModele;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anthony on 15/12/2015.
 */
public class ArbreDeCompetenceVue {

    ArbreDeCompetenceModele aC;
    ArrayList<CompetenceVue> competences;

    ArbreDeCompetenceVue(ArbreDeCompetenceModele ac){

        aC = ac;
        competences = new ArrayList<>();
        HashMap<String, ArrayList<CompetenceModele>> temporaire = aC.getComp();
        for (Map.Entry<String, ArrayList<CompetenceModele>> competence : temporaire.entrySet()) {

            competences.add(new CompetenceVue(competence.getValue().get(0)));

        }

    }


    void affichage() {

        Jeu.scene.setFill(new ImagePattern(new Image("file:fond2.png"), 0, 0, 1, 1, true));
        HashMap<String, ArrayList<CompetenceModele>> temporaire = aC.getComp();
        for (Map.Entry<String, ArrayList<CompetenceModele>> competence : temporaire.entrySet()) {

            if(competence.getValue().size()>1) {
                for (int i=1;i<competence.getValue().size();++i){

                    Line l = new Line();
                    l.setStartX(competence.getValue().get(0).getColone() * 200);
                    l.setStartY(768 - competence.getValue().get(0).getLigne() * 100);
                    l.setEndX(competence.getValue().get(i).getColone() * 200);
                    l.setEndY(768 - competence.getValue().get(i).getLigne() * 100);
                    Jeu.root.getChildren().add(l);

                }
            }

        }
        for (CompetenceVue competence : competences) {

            competence.affichage();

        }
    }
}
