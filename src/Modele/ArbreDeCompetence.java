package Modele;

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
import java.util.HashMap;

/**
 * Partie Modele de la classe ArbreDeCompetence
 * Cette classe sert à instancier un arbre regroupant des compétences pour un département donné
 */
public class ArbreDeCompetence implements java.io.Serializable {

    // Champs
    private HashMap<String, ArrayList<Competence>> competencesMod;
    private Departement depart;

    // Constructeur
    public ArbreDeCompetence(Departement depart){
        this.depart = depart;
        competencesMod = new HashMap<>();

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new File("xml\\competence.xml"));
            Element racine = doc.getDocumentElement(); // Récupère l'élément "compétences"

            NodeList racineNoeuds = racine.getChildNodes(); // Récupère tous les sous-éléments de "compétences"
            int nbRacineNoeuds = racineNoeuds.getLength();

            boolean initialiser = false;

            for (int i = 0; i<nbRacineNoeuds && !initialiser; ++i) {

                // Ne prend que la partie qui nous intéresse càd les compétences du type du département
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals(depart.getNom())) {

                    Element type = (Element) racineNoeuds.item(i);

                    NodeList lignes = type.getElementsByTagName("lignes"); // Récupère tous les éléments "lignes"
                    Element lignesE = (Element) lignes.item(0);

                    NodeList colonnes = lignesE.getElementsByTagName("colonne");
                    int nbLignes = colonnes.getLength();

                    NodeList competences = type.getElementsByTagName("competence"); // Récupère tous les éléments "compétence"
                    int nbCompetences = competences.getLength();

                    for (int j = 0; j<nbCompetences; ++j) {
                        Element elementComp = (Element) competences.item(j);//recupere les info de la competence qui nous interesse

                        String nom = elementComp.getElementsByTagName("nom").item(0).getTextContent();
                        String description = elementComp.getElementsByTagName("description").item(0).getTextContent();
                        int ligne = Integer.parseInt(elementComp.getElementsByTagName("ligne").item(0).getTextContent());
                        int colone = Integer.parseInt(elementComp.getElementsByTagName("colonne").item(0).getTextContent());
                        int[] effet = new int[3];
                        effet[0] = Integer.parseInt(elementComp.getElementsByTagName("moral").item(0).getTextContent());
                        effet[1] = Integer.parseInt(elementComp.getElementsByTagName("efficacite").item(0).getTextContent());
                        effet[2] = Integer.parseInt(elementComp.getElementsByTagName("temps").item(0).getTextContent());
                        int cout = Integer.parseInt(elementComp.getElementsByTagName("cout").item(0).getTextContent());

                        NodeList sommets = elementComp.getElementsByTagName("sommetLie"); // recupere les info sur les sommets lié

                        Element so = (Element) sommets.item(0);
                        String sommetLie = "";

                        NodeList sommetL = so.getElementsByTagName("sommet");
                        int nbSommetL = sommetL.getLength();

                        for (int k = 0; k < nbSommetL; ++k) {

                            sommetLie += sommetL.item(k).getTextContent();
                            sommetLie += ";";

                        }


                        Competence c = new Competence(nom,description,ligne,colone,effet,cout,sommetLie, Integer.parseInt(colonnes.item(ligne-1).getTextContent()),nbLignes,this);

                        competencesMod.put(c.ligne + "," + c.colonne, new ArrayList<>());
                        competencesMod.get(c.ligne + ","+c.colonne).add(c);
                        if(c.sommetLie != null) {
                            String sommet[] = c.sommetLie.split(";");

                            for (String s : sommet) {

                                competencesMod.get(c.ligne + ","+c.colonne).add(competencesMod.get(s).get(0));

                            }
                        }
                    }

                    initialiser = true;
                }

            }

        }
        catch (final ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }

    // Méthodes
    public Departement getDepartement() { return depart; }
    public void debloquerCompetence(int ligne, int colone){

        competencesMod.get(ligne+","+colone).get(0).setAchete();
        competencesMod.get(ligne+","+colone).get(0).applicationCompetenceDepartement();
        if(competencesMod.get(ligne+1+","+1) != null) {
            for (int i = 1; i <= competencesMod.get(ligne + 1 + "," + 1).get(0).getNbColonnes(); ++i) {

                boolean debloque = true;
                ArrayList<Competence> c = competencesMod.get(ligne + 1 + "," + i);
                if (c != null) {

                    for (int j = 1; j < c.size(); ++j) {

                        if (!c.get(j).debloque || !c.get(j).achete) debloque = false;

                    }
                    if (debloque) {

                        c.get(0).setDebloque();

                    }

                }

            }
        }

    }

    public HashMap<String, ArrayList<Competence>> getComp(){ return competencesMod; }

    public String getNom(){ return depart.getNom();}

}
