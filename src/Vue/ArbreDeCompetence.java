package Vue;

import Constantes.Constantes;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
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
    private Polygon retour;
    private Group lignes;

    public ArbreDeCompetence(Modele.ArbreDeCompetence aC, Modele.Jeu jeu){
        retour = new Polygon();
        this.aC = aC;
        aCliquer = "";
        competences = new HashMap<>();
        HashMap<String, ArrayList<Modele.Competence>> temporaire = aC.getComp();
        for (Map.Entry<String, ArrayList<Modele.Competence>> competence : temporaire.entrySet()) {
            competences.put(competence.getKey(), new Competence(competence.getValue().get(0), this));
        }
        nom = new Text(aC.getDepartement().getNom());
        lignes = new Group();
        retour.setOnMouseClicked(event -> {
            this.affichage(jeu, 1);
            jeu.retourJeu();
        });
        retour.setFill(new ImagePattern(new Image("file:image\\Retour.png"), 0, 0, 1, 1, true));
        nom.setVisible(false);
        lignes.setVisible(false);
        retour.setVisible(false);
        jeu.getVue().getRoot().getChildren().addAll(nom, lignes, retour);
    }


    void affichage(Modele.Jeu jeu, int afficher) {
        Scene scene = jeu.getVue().getScene();
        Group root = jeu.getVue().getRoot();
        switch(afficher) {
            case 0:
                nom.setX(scene.getWidth() * 35 / 100);
                nom.setY(scene.getHeight() * 35 / 100);
                nom.setFont(Font.loadFont("file:Font.ttf", scene.getWidth() * Constantes.TAILLE_POLICE_TITRE));
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieCompetenceJournal.jpg"), 0, 0, 1, 1, true));
                HashMap<String, ArrayList<Modele.Competence>> temporaire = aC.getComp();
                lignes.getChildren().clear();
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
                            lignes.getChildren().add(l);
                        }
                    }
                }

                retour.getPoints().clear();
                retour.getPoints().addAll(60.0, scene.getHeight() - 30.0,
                        80.0, scene.getHeight() - 50.0,
                        80.0, scene.getHeight() - 40.0,
                        100.0, scene.getHeight() - 40.0,
                        100.0, scene.getHeight() - 20.0,
                        80.0, scene.getHeight() - 20.0,
                        80.0, scene.getHeight() - 10.0);

                for (Map.Entry<String, Competence> comp : competences.entrySet()) {
                    comp.getValue().affichage(jeu, 0);
                }
                nom.setVisible(true);
                lignes.setVisible(true);
                retour.setVisible(true);
                break;
           default:
                for (Map.Entry<String, Competence> comp : competences.entrySet()) {
                    comp.getValue().affichage(jeu, 1);
                }
                nom.setVisible(false);
                lignes.setVisible(false);
                retour.setVisible(false);
        }

    }

    void changementAffichage(int ligne){

        if(aC.getComp().get(ligne+1+","+1) != null) {
            for (int i = 1; i <= aC.getComp().get(ligne + 1 + "," + 1).get(0).getNbColonnes(); ++i) {

                if (competences.get((ligne + 1) + "," + (i)) != null) {
                    if (competences.get((ligne + 1) + "," + (i)).modele.getDebloque() && !competences.get((ligne + 1) + "," + (i)).modele.getAchete())
                        competences.get((ligne + 1) + "," + (i)).compet.setFill(new ImagePattern(new Image("file:image\\CompetenceDebloque.png"), 0, 0, 1, 1, true));
                }

            }
        }

    }

    public String getACliquer() { return aCliquer; }
    public void setACliquer(String aCliquer) { this.aCliquer = aCliquer; }
}
