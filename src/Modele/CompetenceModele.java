package Modele;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by Anthony on 15/12/2015.
 */
public class CompetenceModele {

    String nom;
    String description;
    int ligne;
    int colone;
    int[] effet;  //contient 3 valeurs correspondant au moral, efficacité et le temps
    int cout;
    boolean debloque;
    boolean achete;
    String sommetLie;   //la liste des sommets lie de forme ligne,colonne separer par des points virgules

    ArbreDeCompetenceModele arbreDeCompetence;

    CompetenceModele(String departement,int numeroCompetence, ArbreDeCompetenceModele arbre){

        arbreDeCompetence = arbre;
        achete = false;
        effet = new int[3];

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new File("competence.xml"));
            Element racine = doc.getDocumentElement(); //recupere l'element competences

            NodeList racineNoeuds = racine.getChildNodes(); //recupere toute les sous elements de competences
            final int nbRacineNoeuds = racineNoeuds.getLength();

            boolean initialiser = false;

            for (int i = 0; i<nbRacineNoeuds && !initialiser; i++) {

                //ne prend que la partie qui nous interesse càd les competences du type du departement
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals(departement)) {

                    Element type = (Element) racineNoeuds.item(i);

                    NodeList competences = type.getElementsByTagName("competence"); //recupere toute les element competence

                    Element elementComp = (Element) competences.item(numeroCompetence);//recupere les info de la competence qui nous interesse

                    nom = elementComp.getElementsByTagName("nom").item(0).getTextContent();
                    description = elementComp.getElementsByTagName("description").item(0).getTextContent();
                    ligne = Integer.parseInt(elementComp.getElementsByTagName("ligne").item(0).getTextContent());
                    colone = Integer.parseInt(elementComp.getElementsByTagName("colonne").item(0).getTextContent());
                    effet[0] = Integer.parseInt(elementComp.getElementsByTagName("moral").item(0).getTextContent());
                    effet[1] = Integer.parseInt(elementComp.getElementsByTagName("efficacite").item(0).getTextContent());
                    effet[2] = Integer.parseInt(elementComp.getElementsByTagName("temps").item(0).getTextContent());
                    cout = Integer.parseInt(elementComp.getElementsByTagName("cout").item(0).getTextContent());

                    NodeList sommets = elementComp.getElementsByTagName("sommetLie"); // recupere les info sur les sommets lié

                    Element so = (Element) sommets.item(0);
                    sommetLie = "";

                    NodeList sommetL = so.getElementsByTagName("sommet");
                    int nbSommetL = sommetL.getLength();

                    for(int j=0; j<nbSommetL;++j){

                        sommetLie += sommetL.item(j).getTextContent();
                        sommetLie += ";";

                    }

                    if (ligne == 1) debloque = true;
                    else debloque = false;

                    initialiser = true;
                }

            }

        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }


    }

    public void applicationCompetence(){}

    public void setAchete(){ achete = true; }

    public void setDebloque(){ debloque = true; }

    public int getLigne() { return ligne;}

    public int getColonne() { return colone;}

    public String getNom(){ return nom;}

    public String getDescription() { return description;}

    public ArbreDeCompetenceModele getArbreDeCompetence() { return  arbreDeCompetence; }

    public boolean getDebloque(){ return debloque;}

    public boolean getAchete(){ return achete;}

}
