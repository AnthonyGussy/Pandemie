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
    HashMap<String, CompetenceVue> competences;

    ArbreDeCompetenceVue(ArbreDeCompetenceModele ac){

        aC = ac;
        competences = new  HashMap<String, CompetenceVue>();
        HashMap<String, ArrayList<CompetenceModele>> temporaire = aC.getComp();
        for (Map.Entry<String, ArrayList<CompetenceModele>> competence : temporaire.entrySet()) {

            competences.put(competence.getKey(), new CompetenceVue(competence.getValue().get(0), this));

        }

    }


    void affichage() {

        Jeu.scene.setFill(new ImagePattern(new Image("file:fond2.png"), 0, 0, 1, 1, true));
        HashMap<String, ArrayList<CompetenceModele>> temporaire = aC.getComp();
        for (Map.Entry<String, ArrayList<CompetenceModele>> competence : temporaire.entrySet()) {

            if(competence.getValue().size()>1) {
                for (int i=1;i<competence.getValue().size();++i){

                    Line l = new Line();
                    l.setStartX(competence.getValue().get(0).getColonne() * 200);
                    l.setStartY(768 - competence.getValue().get(0).getLigne() * 100);
                    l.setEndX(competence.getValue().get(i).getColonne() * 200);
                    l.setEndY(768 - competence.getValue().get(i).getLigne() * 100);
                    Jeu.root.getChildren().add(l);

                }
            }

        }
        for (Map.Entry<String, CompetenceVue> comp : competences.entrySet()) {

            comp.getValue().affichage();

        }
    }

    void changementAffichage(int ligne,int colonne){

        if(competences.get((ligne+1)+","+(colonne-1)) != null){
            if(competences.get((ligne+1)+","+(colonne-1)).compM.getDebloque() && !competences.get((ligne+1)+","+(colonne-1)).compM.getAchete())
                competences.get((ligne+1)+","+(colonne-1)).compet.setFill(new ImagePattern(new Image("file:CompetenceDebloque.png"), 0, 0, 1, 1, true));
        }
        if(competences.get((ligne+1)+","+(colonne)) != null){
            if(competences.get((ligne+1)+","+(colonne)).compM.getDebloque() && !competences.get((ligne+1)+","+(colonne)).compM.getAchete())
                competences.get((ligne+1)+","+(colonne)).compet.setFill(new ImagePattern(new Image("file:CompetenceDebloque.png"), 0, 0, 1, 1, true));
        }
        if(competences.get((ligne+1)+","+(colonne+1)) != null){
            if(competences.get((ligne+1)+","+(colonne+1)).compM.getDebloque() && !competences.get((ligne+1)+","+(colonne+1)).compM.getAchete())
                competences.get((ligne+1)+","+(colonne+1)).compet.setFill(new ImagePattern(new Image("file:CompetenceDebloque.png"), 0, 0, 1, 1, true));
        }

    }
}
