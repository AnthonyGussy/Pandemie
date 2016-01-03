package Vue;

import Enumerations.CompteurType;
import Enumerations.DepartementNom;
import Modele.ArbreDeCompetenceModele;
import Modele.Departement;
import Modele.EvenementArticleModele;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jeu implements java.io.Serializable {
    List<Menu> menus;
    List<Departement> departements;
    List<Compteur> compteurs;
    List<Modele.Evenement> evenements;
    public static Group root;
    public static Scene scene;

    public Jeu(Stage primaryStage) {
        menus = new ArrayList<>();
        root = new Group();

        departements = new ArrayList<>();
        evenements = new ArrayList<>();
        scene = new Scene(root, 1024, 768);

        primaryStage.setTitle("Study Project Simulator");
        primaryStage.setScene(scene);

        String[] boutons = new String[]{"Jouer", "Charger", "Quitter"};
        menus.add(new Menu(boutons, this));
        menus.get(0).affichage(0);
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            redimensionner();
        });
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            redimensionner();
        });
        primaryStage.show();
    }

    public void commencerPartie() {
        List<DepartementNom> departementNoms = new ArrayList<>(Arrays.asList(DepartementNom.Edim, DepartementNom.Energie, DepartementNom.Gmc, DepartementNom.Imsi, DepartementNom.Informatique));
        for(int i = 0; i<5; ++i) {
            int alea = (int)(Math.random()*departementNoms.size());
            departements.add(new Departement(departementNoms.get(alea)));
            departementNoms.remove(alea);
        }

        evenements.add(new EvenementArticleModele(DepartementNom.Gmc, "Facile", 0));
        EvenementArticleModele test = (EvenementArticleModele) evenements.get(0);
        test.getEAV().affichage(0);
    }

    public void sauvegarder() {
        departements.add(new Departement(DepartementNom.Edim));
        serialiser(departements, "departements");
        //serializer(compteurs);
    }

    public void charger() {
        departements = deserialiser("departements");
        System.out.println(departements.get(0).getNom());
    }

    public <T> void serialiser(T objet, String nom) {
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream(nom+".ser");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(objet);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public <T> T deserialiser(String nom) {
        ObjectInputStream ois = null;
        T objet = null;
        try {
            final FileInputStream fichier = new FileInputStream(nom + ".ser");
            ois = new ObjectInputStream(fichier);
            objet = (T) ois.readObject();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return objet;
    }

    private void redimensionner() {
        for(Menu menu : menus) {
            menu.affichage(2);
        }
        for(Modele.Evenement evenement : evenements) {
            //evenement.affichage();
            String type = evenement.getClass().getName();
            switch(type) {
                case "EvenementArticleModele":
                    EvenementArticleModele temp = (EvenementArticleModele) evenement;
                    temp.getEAV().affichage(2);
                    break;
                case "EvenementAccomplissementModele":
                    break;
                default:
            }
        }
    }
}

