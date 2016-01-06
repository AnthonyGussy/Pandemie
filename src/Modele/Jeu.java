package Modele;

import Constantes.Constantes;
import Enumerations.CompteurType;
import Enumerations.DepartementNom;
import Enumerations.BoutonType;
import Vue.Coin;
import Vue.Compteur;
import Vue.Menu;
import javafx.stage.Stage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Partie Modele de la classe Jeu
 * Cette classe sert � instancier les composantes du jeu
 */
public class Jeu implements java.io.Serializable {

    //Champs
    private Vue.Jeu vue;
    private ArrayList<Menu> menus;
    private ArrayList<Modele.Departement> departements;
    private ArrayList<Compteur> compteurs;
    private ArrayList<Modele.Evenement> evenements;

    // Constructeur
    public Jeu(Stage primaryStage) {
        menus = new ArrayList<>();
        departements = new ArrayList<>();
        evenements = new ArrayList<>();
        compteurs = new ArrayList<>();
        vue = new Vue.Jeu(primaryStage, this);
        BoutonType[] boutons = new BoutonType[]{BoutonType.Jouer, BoutonType.Charger, BoutonType.Regles, BoutonType.Quitter};
        menus.add(new Menu(boutons, this));
        menus.get(0).affichage(this, 0);
    }

    // M�thodes
    /**
     * Cette m�thode renvoie la Vue de Jeu
     * @return Vue de Jeu
     */
    public Vue.Jeu getVue() { return vue; }

    /**
     * Cette m�thode renvoie la liste des d�partements
     * @return Liste des d�partements
     */
    public ArrayList<Modele.Departement> getDepartements() { return departements; }

    /**
     * M�thode qui va mettre dans un ordre al�atoire la liste des d�partements,
     * le d�partement � l'index 0 �tant consid�r� comme le d�partement d'origine du projet.
     * Cette m�thode va aussi afficher le plateau de jeu et lancer les �v�nements al�atoires.
     */
    public void commencerPartie() {
        List<DepartementNom> departementNoms = new ArrayList<>(Arrays.asList(DepartementNom.Edim, DepartementNom.Energie, DepartementNom.Gmc, DepartementNom.Imsi, DepartementNom.Informatique));
        for(int i = 0; i<5; ++i) {
            int alea = (int)(Math.random()*departementNoms.size());
            if(i == 0)
                departements.add(new Modele.Departement(departementNoms.get(alea),true, this));
            else
                departements.add(new Modele.Departement(departementNoms.get(alea),false, this));
            departementNoms.remove(alea);
        }
        compteurs.add(new Compteur(0, CompteurType.Points_de_competence));
        for(Modele.Departement dep : departements) {
            dep.getVue().affichage(vue.getScene(), 0);
        }
        vue.affichagePlateau(0);

        /*evenements.add(new EvenementArticle(DepartementNom.Gmc, "Facile", 0));
        EvenementArticle test = (EvenementArticle) evenements.get(0);
        test.getEAV().affichagePlateau(this, 0);*/
    }

    /**
     *
     */
    public void retourJeu() {
        vue.affichagePlateau(0);
    }

    /**
     *
     * @return
     */
    public int getPtsCompetence() { return compteurs.get(Constantes.PTS_COMPETENCE).getCompte(); }

    /**
     *
     * @param pts
     */
    public void setPtsCompetence(int pts) {
        compteurs.get(Constantes.PTS_COMPETENCE).modifCompte(pts);
    }

    /**
     * Cette m�thode va sauvegarder le jeu en s�rialisant les diff�rents composants du jeu
     */
    public void sauvegarder() {
        //departements.add(new Departement(DepartementNom.Edim));
        serialiser(departements, "departements");
    }

    /**
     * Cette m�thode va charger le jeu en des�rialisant les diff�rents composants du jeu
     */
    public void charger() {
        departements = deserialiser("departements");
        System.out.println(departements.get(0).getNom());
    }

    /**
     * Cette m�thode va enregistrer tous les composants s�rialisables d'un objet dans un fichier du nom de l'objet
     * @param objet L'objet � s�rialiser
     * @param nom Le nom de l'objet � s�rialiser
     * @param <T> Le type de l'objet � s�rialiser
     */
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

    /**
     * Cette m�thode va renvoyer un objet cr�� � partir d'un fichier pr�c�dement enregistr� au nom de l'objet
     * @param nom Nom de l'objet � des�rialiser
     * @param <T> Type de l'objet � des�rialiser
     * @return L'objet cr�� � partir des donn�es du fichier
     */
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

    /**
     * Cette m�thode redimensionne les �l�ments actifs de l'affichagePlateau lorsque la fen�tre est redimensionn�e
     */
    public void redimensionner() {
        vue.affichagePlateau(3);
        for(Menu menu : menus) {
            menu.affichage(this, 2);
        }

        for(Modele.Evenement evenement : evenements) {
            String type = evenement.getClass().getName();
            switch(type) {
                case "Modele.EvenementArticle":
                    EvenementArticle temp = (EvenementArticle) evenement;
                    temp.getEAV().affichage(this, 2);
                    break;
                case "Modele.EvenementAccomplissementModele":
                    break;
                default:
            }
        }
    }
}

