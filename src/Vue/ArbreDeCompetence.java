package Vue;

import Constantes.Constantes;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArbreDeCompetence implements java.io.Serializable {
    private Modele.ArbreDeCompetence modele;
    private HashMap<String, Competence> competences;
    private Text nom;
    private boolean affiche = false;
    private String aCliquer;
    private ImageView retour;
    private Group lignes;
    protected Text noPoint;

    public ArbreDeCompetence(Modele.ArbreDeCompetence modele, Modele.Jeu jeu){

        noPoint = new Text("Vous n'avez pas assez de points");
        noPoint.setFill(Color.ORANGERED);
        retour = new ImageView(new Image("file:image\\RetourActif.jpg"));
        retour.setOnMouseEntered(mouseEvent -> retour.setImage(new Image("file:image\\Retour.jpg")));
        retour.setOnMouseExited(mouseEvent -> retour.setImage(new Image("file:image\\RetourActif.jpg")));
        this.modele = modele;
        aCliquer = "";
        competences = new HashMap<>();
        nom = new Text(modele.getDepartement().getNom());
        lignes = new Group();
        retour.setOnMouseClicked(event -> {
            this.affichage(jeu, 1);
            modele.getDepartement().afficherTaches(jeu.getVue().getScene(), 1);
            jeu.afficherCompte(0);
            jeu.retourJeu();
        });
        retour.setVisible(false);
        nom.setVisible(false);
        lignes.setVisible(false);
        retour.setVisible(false);
        noPoint.setVisible(false);

        jeu.getVue().getRoot().getChildren().addAll(nom, lignes, retour, noPoint);
        HashMap<String, ArrayList<Modele.Competence>> temporaire = modele.getComp();
        for (Map.Entry<String, ArrayList<Modele.Competence>> competence : temporaire.entrySet()) {
            competences.put(competence.getKey(), new Competence(competence.getValue().get(0), this,jeu));
        }
    }


    void affichage(Modele.Jeu jeu, int afficher) {
        Scene scene = jeu.getVue().getScene();
        switch(afficher) {
            case 0:
                for (Map.Entry<String, Competence> comp : competences.entrySet()) {
                    comp.getValue().affichage(jeu, 0);
                }
                dimensionnement(scene);
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieCompetence.jpg"), 0, 0, 1, 1, true));

                nom.setVisible(true);
                lignes.setVisible(true);
                retour.setVisible(true);
                break;
            case 1:
                for (Map.Entry<String, Competence> comp : competences.entrySet()) {
                    comp.getValue().affichage(jeu, 1);
                }
                nom.setVisible(false);
                lignes.setVisible(false);
                retour.setVisible(false);
                noPoint.setVisible(false);
                break;
            default:
                dimensionnement(scene);
                for (Map.Entry<String, Competence> comp : competences.entrySet()) {
                    comp.getValue().affichage(jeu, 2);
                }
        }

    }

    void dimensionnement(Scene scene){

        nom.setX(scene.getWidth() * Constantes.POS_X_NOM_DEPARTEMENT_ARBRE);
        nom.setY(scene.getHeight() * Constantes.POS_Y_NOM_DEPARTEMENT_ARBRE);
        nom.setFont(Font.loadFont("file:Font.ttf", scene.getWidth() * Constantes.TAILLE_POLICE_TITRE));
        retour.setTranslateX(scene.getWidth() * Constantes.POS_X_FLECHEARBRE);
        retour.setTranslateY(scene.getHeight() * Constantes.POS_Y_FLECHEARBRE);
        retour.setFitWidth(scene.getWidth() * Constantes.LARGEUR_RETOUR);
        retour.setFitHeight(scene.getHeight() * Constantes.HAUTEUR_RETOUR);

        HashMap<String, ArrayList<Modele.Competence>> temporaire = modele.getComp();
        lignes.getChildren().clear();
        for (Map.Entry<String, ArrayList<Modele.Competence>> competence : temporaire.entrySet()) {
            if(competence.getValue().size() > 1) {
                for (int i = 1; i < competence.getValue().size(); ++i){

                    double coefx1 = (double) 1 / competence.getValue().get(0).getNbLignes() * (competence.getValue().get(0).getLigne() - 1);
                    double coefy1 = (double) 1 / competence.getValue().get(0).getNbColonnes() * (competence.getValue().get(0).getColonne() - 1);
                    double coefx2 = (double) 1 / competence.getValue().get(i).getNbLignes() * (competence.getValue().get(i).getLigne() - 1);
                    double coefy2 = (double) 1 / competence.getValue().get(i).getNbColonnes() * (competence.getValue().get(i).getColonne() - 1);
                    Line l = new Line();
                    l.setStartX(scene.getWidth() * Constantes.X_LIGNE_1 + coefx1 * scene.getWidth() * Constantes.X_LIGNE_2);
                    l.setStartY(scene.getHeight() * Constantes.Y_LIGNE_1 - coefy1 * scene.getHeight() * Constantes.Y_LIGNE_2);
                    l.setEndX(scene.getWidth() * Constantes.X_LIGNE_1  + coefx2 * scene.getWidth() * Constantes.X_LIGNE_2);
                    l.setEndY(scene.getHeight() * Constantes.Y_LIGNE_1 - coefy2 * scene.getHeight() * Constantes.Y_LIGNE_2);
                    lignes.getChildren().add(l);
                }
            }
        }

    }

    void changementAffichage(int ligne){

        if(modele.getComp().get(ligne+1+","+1) != null) {
            for (int i = 1; i <= modele.getComp().get(ligne + 1 + "," + 1).get(0).getNbColonnes(); ++i) {

                if (competences.get((ligne + 1) + "," + (i)) != null) {
                    if (competences.get((ligne + 1) + "," + (i)).modele.getDebloque() && !competences.get((ligne + 1) + "," + (i)).modele.getAchete())
                        competences.get((ligne + 1) + "," + (i)).compet.setFill(new ImagePattern(new Image("file:image\\CompetenceDebloque.png"), 0, 0, 1, 1, true));
                }

            }
        }

    }



    public String getACliquer() { return aCliquer; }
    public void setACliquer(String aCliquer) { this.aCliquer = aCliquer; }

    public void setNoPoint(boolean visible) {
        noPoint.setVisible(visible);
    }

    public void erreurAchat(Modele.Jeu jeu,String nom) {
        noPoint.setText(nom);
        noPoint.setFont(Font.loadFont("file:Font.ttf", jeu.getVue().getScene().getHeight() * Constantes.TAILLE_POLICE));
        noPoint.setX((jeu.getVue().getScene().getWidth() * 83.5) / 100);
        noPoint.setY((jeu.getVue().getScene().getHeight() * 50) / 100);
        noPoint.setWrappingWidth((jeu.getVue().getScene().getWidth() * 14) / 100);
        noPoint.setVisible(true);
    }
}
