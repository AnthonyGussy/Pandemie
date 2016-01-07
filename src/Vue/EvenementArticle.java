package Vue;

import Constantes.Constantes;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class EvenementArticle implements java.io.Serializable {
    private Text nom;
    private Text description;
    private Text departement;
    private ImageView journal;

    public EvenementArticle(Modele.Evenement event, Modele.Jeu jeu) {
        journal = new ImageView(new Image("file:image\\PandemieJournal.jpg"));
        nom = new Text(event.getNom());
        nom.setFill(Color.WHITE);
        description = new Text(event.getDescription());
        description.setFill(Color.WHITE);
        String classe = event.getClass().getSimpleName();
        String texte="";
        switch(classe) {
            case "EvenementArticle":
                Modele.EvenementArticle modele = (Modele.EvenementArticle) event;
                texte = "Effets sur les départements : ";
                texte += "moral => " + modele.getMoral()+" / ";
                texte += "efficacite => " + modele.getEfficacite()+" / ";
                texte += "temps => " + modele.getTemps();
                break;
            case "EvenementAccomplissement":
                texte = "Le projet final avance petit a petit.";
                break;
        }
        departement = new Text(texte);
        departement.setFill(Color.WHITE);
        nom.setVisible(false);
        description.setVisible(false);
        departement.setVisible(false);
        journal.setVisible(false);
        jeu.getVue().getRoot().getChildren().addAll(journal, nom, description, departement);
    }


    public void affichage(Modele.Jeu jeu, int afficher) {
    	Scene scene = jeu.getVue().getScene();
    	switch(afficher) {
            case 0:
                affichage(jeu, 2);
                nom.setVisible(true);
                description.setVisible(true);
                departement.setVisible(true);
                journal.setVisible(true);
                break;
            case 1:
                journal.setVisible(false);
                nom.setVisible(false);
                description.setVisible(false);
                departement.setVisible(false);
            default:
                description.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE));
                nom.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE_TITRE));
                departement.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE));
                journal.setTranslateX(scene.getWidth() * Constantes.POS_X_JOURNAL);
                nom.setX((int)(scene.getWidth() * Constantes.POS_X_EV_ART_NOM));
                nom.setY((int)(scene.getHeight() * Constantes.POS_Y_EV_ART_NOM));
                nom.setWrappingWidth(scene.getWidth() * Constantes.LARGEUR_ARTICLE);
                description.setX((int)(scene.getWidth() * Constantes.POS_X_EV_ART_DESC));
                description.setY((int) (scene.getHeight() * Constantes.POS_Y_EV_ART_DESC));
                departement.setX((int) (scene.getWidth() * Constantes.POS_X_EV_ART_DEP));
                departement.setY((int) (scene.getHeight() * Constantes.POS_Y_EV_ART_DEP));
                description.setWrappingWidth(scene.getWidth() * Constantes.LARGEUR_ARTICLE);
        }
    }
}
