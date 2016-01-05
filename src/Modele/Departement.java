package Modele;

import Constantes.Constantes;
import Enumerations.CompteurType;
import Enumerations.DepartementNom;
import Vue.Compteur;
import javafx.application.Platform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Departement implements java.io.Serializable {
    private Enumerations.DepartementNom nom;
    private int nbPersonne;
    private List<Compteur> compteurs;
    private List<Modele.Tache> taches;
    private List<Modele.Tache> tachesStockage;
    private Modele.ArbreDeCompetence arbre;
    private Vue.Departement vue;

    public Departement(DepartementNom depNom, boolean depart){

        this.nom = depNom;
        this.nbPersonne = 200 + (int)(Math.random() * 201);
        this.arbre = new ArbreDeCompetence(this);
        Compteur efficacite = new Compteur(40, 100, CompteurType.Efficacite);
        Compteur moral = new Compteur(80, 100, CompteurType.Moral);
        Compteur infecte = new Compteur(0,nbPersonne, CompteurType.Infectes);
        Compteur standBy = new Compteur(0,nbPersonne, CompteurType.Stand_By);
        compteurs = new ArrayList<>(4);
        compteurs.add(efficacite);
        compteurs.add(moral);
        compteurs.add(infecte);
        compteurs.add(standBy);
        taches = new ArrayList<>();
        tachesStockage = new ArrayList<>();
        creeListeTache();
        if(depart) creerTache();
        vue = new Vue.Departement(this);
    }

    public Vue.Departement getVue() { return vue; }

    void creerTache(){

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new File(Constantes.PATH_PROJET));
            Element racine = doc.getDocumentElement();
            NodeList racineNoeuds = racine.getChildNodes();


            for (int i = 0; i < racineNoeuds.getLength(); i++) {
                if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals(String.valueOf(this.nom))) {
                    NodeList difNoeuds = racineNoeuds.item(i).getChildNodes();
                    for (int k = 0; k < difNoeuds.getLength(); k++) {
                        if (difNoeuds.item(k).getNodeType() == Node.ELEMENT_NODE && difNoeuds.item(k).getNodeName().equals("projet")) {
                            Element elementTache = (Element) difNoeuds.item(k);
                            String nom = elementTache.getElementsByTagName("nom").item(0).getTextContent();
                            String description = elementTache.getElementsByTagName("description").item(0).getTextContent();

                            int temps = Integer.parseInt(elementTache.getElementsByTagName("temps").item(0).getTextContent());
                            int infectes = Integer.parseInt(elementTache.getElementsByTagName("infecte").item(0).getTextContent());

                            taches.add(new Tache(nom, description, new Compteur(temps, CompteurType.Temps), new Compteur(infectes, CompteurType.Infectes)));
                        }
                    }
                }
            }
        }

        catch (final ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        compteurs.get(3).modifCompte(-taches.get(0).getCompteurs().get(1).getCompte());
        compteurs.get(2).modifCompte(taches.get(0).getCompteurs().get(1).getCompte());
    }
    void infection(Modele.Jeu jeu){
        int nbTaches = taches.size();
        int infection = nbTaches + (100-compteurs.get(1).getCompte())/100*nbTaches;
        compteurs.get(3).modifCompte(-infection);
        compteurs.get(2).modifCompte(infection);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vue.affichage(jeu, 2);
            }
        });
    }
    void supprimerTache(){
        for(Tache tache :  taches){
            if(tache.getTermine()){
                compteurs.get(3).modifCompte(tache.getCompteurs().get(1).getCompte());
                compteurs.get(2).modifCompte(-tache.getCompteurs().get(1).getCompte());
                taches.remove(tache);
            }
        }
    }
    public int getEfficacite() { return compteurs.get(0).getCompte(); }
    public void setEfficacite(int ajout) { compteurs.get(0).modifCompte(ajout); }
    public int getMoral() { return compteurs.get(1).getCompte(); }
    public void setMoral(int ajout) { compteurs.get(1).modifCompte(ajout); }
    public String getNom(){ return String.valueOf(nom);}
    public ArbreDeCompetence getArbre(){ return arbre;}
    public DepartementNom getNomEnum(){ return nom;}
    public int getNbTaches(){ return taches.size();}

    private void creeListeTache(){

        int temps;
        int infectes;
        Tache a;
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new File(Constantes.PATH_TACHES));
            Element racine = doc.getDocumentElement();
            NodeList racineNoeuds = racine.getChildNodes();


            for (int i = 0; i < racineNoeuds.getLength(); i++) {
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals(String.valueOf(this.nom))) {
                    NodeList depNoeuds = racineNoeuds.item(i).getChildNodes();
                    for (int j = 0; j < depNoeuds.getLength(); j++) {
                        if(depNoeuds.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            NodeList difNoeuds = depNoeuds.item(j).getChildNodes();
                            for (int k = 0; k < difNoeuds.getLength(); k++) {
                                if(difNoeuds.item(k).getNodeType() == Node.ELEMENT_NODE && difNoeuds.item(k).getNodeName().equals("tache")) {
                                    Element elementTache = (Element) difNoeuds.item(k);
                                    String nom = elementTache.getElementsByTagName("nom").item(0).getTextContent();
                                    String description = elementTache.getElementsByTagName("description").item(0).getTextContent();

                                    temps = Integer.parseInt(elementTache.getElementsByTagName("temps").item(0).getTextContent());
                                    infectes = Integer.parseInt(elementTache.getElementsByTagName("infecte").item(0).getTextContent());

                                    a = new Tache(nom, description, new Compteur(temps, CompteurType.Temps), new Compteur(infectes, CompteurType.Infectes));
                                    tachesStockage.add(a);
                                }
                            }
                        }
                    }
                }
            }
        }

        catch (final ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public int getNbActif(){ return compteurs.get(2).getCompte();}
    public int getNbPersonne() {return nbPersonne;}
}
