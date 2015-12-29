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
 * Created by Victor on 04/12/2015.
 */
public class ArbreDeCompetenceModele {
    HashMap<String, ArrayList<CompetenceModele>> competencesMod;


    public ArbreDeCompetenceModele(String departement){

        competencesMod = new HashMap<>();

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new File("competence.xml"));
            Element racine = doc.getDocumentElement(); //recupere l'element competences

            NodeList racineNoeuds = racine.getChildNodes(); //recupere toute les sous elements de competences
            int nbRacineNoeuds = racineNoeuds.getLength();

            boolean initialiser = false;

            for (int i = 0; i<nbRacineNoeuds && !initialiser; ++i) {

                //ne prend que la partie qui nous interesse càd les competences du type du departement
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals(departement)) {

                    Element type = (Element) racineNoeuds.item(i);

                    NodeList competences = type.getElementsByTagName("competence"); //recupere toute les element competence
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


                        CompetenceModele c = new CompetenceModele(nom,description,ligne,colone,effet,cout,sommetLie,this);

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

    public void debloquerCompetence(int ligne, int colone){

        competencesMod.get(ligne+","+colone).get(0).setAchete();
        for(int i=1;i<5;++i){

            boolean debloque = true;
            ArrayList<CompetenceModele> c = competencesMod.get(ligne+1 + "," + i);
            if(c != null) {

                for (int j = 1; j < c.size(); ++j) {

                    if (!c.get(j).debloque || !c.get(j).achete) debloque = false;

                }
                if (debloque) {

                    c.get(0).setDebloque();

                }

            }

        }

    }

    public HashMap<String, ArrayList<CompetenceModele>> getComp(){ return competencesMod; }
}
