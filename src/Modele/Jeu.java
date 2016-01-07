package Modele;

import Constantes.Constantes;
import Enumerations.CompteurType;
import Enumerations.DepartementNom;
import Enumerations.BoutonType;
import Vue.Compteur;
import Vue.Menu;
import Vue.Regles;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Partie Modele de la classe Jeu
 * Cette classe sert à instancier les composantes du jeu
 */
public class Jeu implements java.io.Serializable {

    //Champs
    private Vue.Jeu vue;
    private Menu menuPrincipal;
    private Menu menuJeu;
    private Regles regles;
    private ArrayList<Modele.Departement> departements;
    private ArrayList<Compteur> compteurs;
    private ArrayList<Modele.Evenement> evenements;
    private ArrayList<PopUp> popUps;
    private ArrayList<EvenementArticle> eventStockage;

    // Constructeur
    public Jeu(Stage primaryStage) {
        popUps = new ArrayList<>();
        eventStockage = new ArrayList<>();
        departements = new ArrayList<>();
        evenements = new ArrayList<>();
        compteurs = new ArrayList<>();
        vue = new Vue.Jeu(primaryStage, this);
        BoutonType[] boutons = new BoutonType[]{BoutonType.Jouer, BoutonType.Charger, BoutonType.Regles, BoutonType.Quitter};
        menuPrincipal = new Menu(boutons, this);
        menuPrincipal.affichage(this, 0);
        boutons = new BoutonType[]{BoutonType.Retour_Jeu, BoutonType.Sauvegarder, BoutonType.Regles, BoutonType.Quitter};
        menuJeu = new Menu(boutons, this);
        regles = new Regles(this);
        setListeEvenementStockage();
    }

    // Méthodes
    /**
     * Cette méthode renvoie la Vue de Jeu
     * @return Vue de Jeu
     */
    public Vue.Jeu getVue() { return vue; }

    public Vue.Menu getMenuJeu() { return menuJeu; }

    public Vue.Menu getMenuPrincipal() { return menuPrincipal; }

    /**
     * Cette méthode renvoie la liste des départements
     * @return Liste des départements
     */
    public ArrayList<Modele.Departement> getDepartements() { return departements; }

    /**
     * Méthode qui va mettre dans un ordre aléatoire la liste des départements,
     * le département à l'index 0 étant considéré comme le département d'origine du projet.
     * Cette méthode va aussi afficher le plateau de jeu et lancer les événements aléatoires.
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
            dep.getVue().affichage(this, 0);
        }
        String contexte = "Vous dirigez un groupe d'étudiant en " + departements.get(0).getNom();
        String description = "Vous devez réaliser le projet suivant : " + departements.get(0).getTaches().get(0).getNom() + "\n" + departements.get(0).getTaches().get(0).getDescription();
        evenements.add(new EvenementTextuel(contexte, description, this));
        evenements.get(0).setDuree(20);
        evenements.get(0).getVue().affichage(this, 0);
        vue.affichagePlateau(0);

        /*evenements.add(new Evenement(DepartementNom.Gmc, "Facile", 0));
        Evenement test = (Evenement) evenements.get(0);
        test.getEAV().affichagePlateau(this, 0);*/
    }

    public void regles() {
        regles.affichage(this, 0);
    }

    /**
     *
     */
    public void retourJeu() {
        vue.affichagePlateau(0);
        vue.affichagePopUp(0);
        if(evenements.size() > 0) evenements.get(evenements.size() - 1).getVue().affichage(this, 0);
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
     * Cette méthode va sauvegarder le jeu en sérialisant les différents composants du jeu
     */
    public void sauvegarder() {
        //departements.add(new Departement(DepartementNom.Edim));
        serialiser(departements, "departements");
    }

    /**
     * Cette méthode va charger le jeu en desérialisant les différents composants du jeu
     */
    public void charger() {
        departements = deserialiser("departements");
        System.out.println(departements.get(0).getNom());
    }

    /**
     * Cette méthode va enregistrer tous les composants sérialisables d'un objet dans un fichier du nom de l'objet
     * @param objet L'objet à sérialiser
     * @param nom Le nom de l'objet à sérialiser
     * @param <T> Le type de l'objet à sérialiser
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
     * Cette méthode va renvoyer un objet créé à partir d'un fichier précédement enregistré au nom de l'objet
     * @param nom Nom de l'objet à desérialiser
     * @param <T> Type de l'objet à desérialiser
     * @return L'objet créé à partir des données du fichier
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
     * Cette méthode redimensionne les éléments actifs de l'affichagePlateau lorsque la fenêtre est redimensionnée
     */
    public void redimensionner() {
        regles.affichage(this, 2);
        vue.affichagePlateau(3);
        menuPrincipal.affichage(this, 2);
        menuJeu.affichage(this, 2);
        for(Modele.Evenement evenement : evenements) {
            evenement.getVue().affichage(this, 2);
        }
        for(Modele.PopUp popUp : popUps) {
            popUp.getVue().affichage(this, 2);
        }

    }

    public void ajoutPopUp() {
        Departement depPopUp;
        do {
            depPopUp = departements.get((int)(Math.random()*5));
        } while(depPopUp.getNbActif() == 0);
        PopUp popUp = new PopUp(depPopUp,this);
        popUps.add(popUp);
        if(vue.getAffiche()) popUp.getVue().affichage(this, 0);
    }

    public void enleverPopUp() {
        for(int i = 0; i < popUps.size(); i++) {
            if(popUps.get(i).getDuree() == 0) {
                popUps.get(i).getVue().affichage(this, 3);
                popUps.remove(i);
            }
        }
    }

    public ArrayList<PopUp> getPopUps() { return popUps;}

    private void setListeEvenementStockage(){

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new File(Constantes.PATH_EVEN_ARTICLE_MODELE));
            Element racine = doc.getDocumentElement();
            NodeList racineNoeuds = racine.getChildNodes();
            EvenementArticle a;
            int effets[] = {0,0,0};

            for (int i = 0; i < racineNoeuds.getLength(); i++) {
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    NodeList evenNoeuds = racineNoeuds.item(i).getChildNodes();
                    for (int j = 0; j < evenNoeuds.getLength(); j++) {

                        if(evenNoeuds.item(j).getNodeType() == Node.ELEMENT_NODE && evenNoeuds.item(j).getNodeName().equals("evenement")) {

                            Element elementEven = (Element) evenNoeuds.item(j);

                            String nom = elementEven.getElementsByTagName("nom").item(0).getTextContent();
                            String description = elementEven.getElementsByTagName("description").item(0).getTextContent();
                            effets[0] = Integer.parseInt(elementEven.getElementsByTagName("moral").item(0).getTextContent());
                            effets[1] = Integer.parseInt(elementEven.getElementsByTagName("efficacite").item(0).getTextContent());
                            effets[2] = Integer.parseInt(elementEven.getElementsByTagName("temps").item(0).getTextContent());

                            a = new EvenementArticle(nom, description, effets, this);
                            eventStockage.add(a);
                        }
                    }
                }
            }
        }

        catch (final ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Evenement> getEvenements() { return evenements; }

    public void ajoutEvenement() {
        Platform.runLater(() -> {
            Modele.EvenementArticle  evenement = new EvenementArticle(eventStockage.get((int) (Math.random() * eventStockage.size())), this);
            evenement.getVue().affichage(this, 0);
            evenements.add(evenement);
        });
    }
    public void ajoutEvenement(String nom, String description) {
        Platform.runLater(() -> {
            Modele.EvenementTextuel  evenement = new EvenementTextuel(nom, description, this);
            evenement.getVue().affichage(this, 0);
            evenements.add(evenement);
        });
    }
}

