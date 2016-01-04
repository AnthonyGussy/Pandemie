package Vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArbreDeCompetence implements java.io.Serializable {
    private Modele.ArbreDeCompetence aC;
    private HashMap<String, Competence> competences;
    private Text nom;
    private boolean affiche = false;
    private String aCliquer;

    public String getACliquer() { return aCliquer; }
    public void setACliquer(String aCliquer) { this.aCliquer = aCliquer; }

    public ArbreDeCompetence(Modele.ArbreDeCompetence ac){

        aC = ac;
        aCliquer="";
        competences = new HashMap<>();
        HashMap<String, ArrayList<Modele.Competence>> temporaire = aC.getComp();
        for (Map.Entry<String, ArrayList<Modele.Competence>> competence : temporaire.entrySet()) {

            competences.put(competence.getKey(), new Competence(competence.getValue().get(0), this));

        }
        nom = new Text(aC.getDepartement().getNom());
        nom.setFont(Font.loadFont("file:Font.ttf", 40));
    }


    void affichage(Modele.Jeu jeu, int afficher) {
        Scene scene = jeu.getVue().getScene();
        Group root = jeu.getVue().getRoot();
        switch(afficher) {
            case 0:
                affiche = true;
                nom.setX(scene.getWidth() * 35 / 100);
                nom.setY(scene.getHeight()*35/100);
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieCompetenceJournal.jpg"), 0, 0, 1, 1, true));
                root.getChildren().add(nom);
                HashMap<String, ArrayList<Modele.Competence>> temporaire = aC.getComp();
                for (Map.Entry<String, ArrayList<Modele.Competence>> competence : temporaire.entrySet()) {
                    if(competence.getValue().size() > 1) {
                        for (int i = 1; i < competence.getValue().size(); ++i){

                            double coefx1 = (double) 1 / competence.getValue().get(0).getNbLignes() * (competence.getValue().get(0).getLigne() - 1);
                            double coefy1 = (double) 1 / competence.getValue().get(0).getNbColonnes() * (competence.getValue().get(0).getColonne() - 1);
                            double coefx2 = (double) 1 / competence.getValue().get(i).getNbLignes() * (competence.getValue().get(i).getLigne() - 1);
                            double coefy2 = (double) 1 / competence.getValue().get(i).getNbColonnes() * (competence.getValue().get(i).getColonne() - 1);

                            Line l = new Line();
                            l.setStartX((scene.getWidth() * 15) / 100 + coefx1 * (scene.getWidth() * 60 / 100));
                            l.setStartY((scene.getHeight() * 90) / 100 - coefy1 * (scene.getHeight() * 65 / 100));
                            l.setEndX((scene.getWidth() * 15) / 100 + coefx2 * (scene.getWidth() * 60 / 100));
                            l.setEndY((scene.getHeight() * 90) / 100 - coefy2 * (scene.getHeight() * 65 / 100));
                            root.getChildren().add(l);
                        }
                    }

                }
                for (Map.Entry<String, Competence> comp : competences.entrySet()) {
                    comp.getValue().affichage(jeu);
                }
                break;
            case 1:
                if(affiche) {
                    affiche = false;
                    root.getChildren().clear();
                }
                break;
            default:
                if(affiche) {
                    affichage(jeu, 0);
                }
        }

    }

    void changementAffichage(int ligne){

        if(aC.getComp().get(ligne+1+","+1) != null) {
            for (int i = 1; i <= aC.getComp().get(ligne + 1 + "," + 1).get(0).getNbColonnes(); ++i) {

                if (competences.get((ligne + 1) + "," + (i)) != null) {
                    if (competences.get((ligne + 1) + "," + (i)).compM.getDebloque() && !competences.get((ligne + 1) + "," + (i)).compM.getAchete())
                        competences.get((ligne + 1) + "," + (i)).compet.setFill(new ImagePattern(new Image("file:image\\CompetenceDebloque.png"), 0, 0, 1, 1, true));
                }

            }
        }

    }
}
