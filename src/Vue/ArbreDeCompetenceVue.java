package Vue;

import Modele.ArbreDeCompetenceModele;
import Modele.CompetenceModele;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anthony on 15/12/2015.
 */
public class ArbreDeCompetenceVue {

    ArbreDeCompetenceModele aC;
    HashMap<String, CompetenceVue> competences;
    Text nom;
    public static String aCliquer;

    ArbreDeCompetenceVue(ArbreDeCompetenceModele ac){

        aC = ac;
        aCliquer="";
        competences = new  HashMap<String, CompetenceVue>();
        HashMap<String, ArrayList<CompetenceModele>> temporaire = aC.getComp();
        for (Map.Entry<String, ArrayList<CompetenceModele>> competence : temporaire.entrySet()) {

            competences.put(competence.getKey(), new CompetenceVue(competence.getValue().get(0), this));

        }

    }


    void affichage() {

        Jeu.scene.setFill(new ImagePattern(new Image("file:fond2.png"), 0, 0, 1, 1, true));

        nom = new Text(aC.getNom());
        nom.setFont(Font.loadFont("file:Font.ttf", 40));
        nom.setX(Jeu.scene.getHeight() * 50 / 100);
        nom.setY(Jeu.scene.getWidth()*10/100);

        Jeu.root.getChildren().add(nom);
        HashMap<String, ArrayList<CompetenceModele>> temporaire = aC.getComp();
        for (Map.Entry<String, ArrayList<CompetenceModele>> competence : temporaire.entrySet()) {

            if(competence.getValue().size()>1) {
                for (int i=1;i<competence.getValue().size();++i){

                    double coefx1 = (double) 1/competence.getValue().get(0).getNbColonnes()*(competence.getValue().get(0).getColonne()-1);
                    double coefy1 = (double) 1/ competence.getValue().get(0).getNbLignes()*( competence.getValue().get(0).getLigne()-1);;
                    double coefx2 = (double) 1/competence.getValue().get(i).getNbColonnes()*(competence.getValue().get(i).getColonne()-1);;
                    double coefy2 = (double) 1/competence.getValue().get(i).getNbLignes()*( competence.getValue().get(i).getLigne()-1);;

                    Line l = new Line();
                    l.setStartX((Jeu.scene.getWidth()*20)/100+coefx1*(Jeu.scene.getWidth()*50/100));
                    l.setStartY((Jeu.scene.getHeight()*85)/100-coefy1*(Jeu.scene.getHeight()*70/100));
                    l.setEndX((Jeu.scene.getWidth()*20)/100+coefx2*(Jeu.scene.getWidth()*50/100));
                    l.setEndY((Jeu.scene.getHeight()*85)/100-coefy2*(Jeu.scene.getHeight()*70/100));
                    Jeu.root.getChildren().add(l);

                }
            }

        }
        for (Map.Entry<String, CompetenceVue> comp : competences.entrySet()) {

            comp.getValue().affichage();

        }

        Rectangle r = new Rectangle();
        r.setFill(new ImagePattern(new Image("file:post-it.png"), 0, 0, 1, 1, true));
        r.setX((Jeu.scene.getWidth() * 77) / 100);
        r.setY((Jeu.scene.getHeight() * 55) / 100);
        r.setWidth((Jeu.scene.getWidth() * 22) / 100);
        r.setHeight((Jeu.scene.getHeight() * 47) / 100);
        Jeu.root.getChildren().add(r);

    }

    void changementAffichage(int ligne){

        if(aC.getComp().get(ligne+1+","+1) != null) {
            for (int i = 1; i <= aC.getComp().get(ligne + 1 + "," + 1).get(0).getNbColonnes(); ++i) {

                if (competences.get((ligne + 1) + "," + (i)) != null) {
                    if (competences.get((ligne + 1) + "," + (i)).compM.getDebloque() && !competences.get((ligne + 1) + "," + (i)).compM.getAchete())
                        competences.get((ligne + 1) + "," + (i)).compet.setFill(new ImagePattern(new Image("file:CompetenceDebloque.png"), 0, 0, 1, 1, true));
                }

            }
        }

    }
}
