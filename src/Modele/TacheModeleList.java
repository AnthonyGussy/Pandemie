package Modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Constantes.Constantes;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Enumerations.CompteurType;
import Vue.Compteur;

public class TacheModeleList implements java.io.Serializable {

	List<TacheModele> listeTaches;
	
	public TacheModeleList(String departement, String difficulte) {
		int temps;
		int infectes;
		listeTaches = new ArrayList<>();
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		TacheModele a;
		
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new File(Constantes.PATH_TACHES));
            Element racine = doc.getDocumentElement();
            NodeList racineNoeuds = racine.getChildNodes();
            
            
            for (int i = 0; i < racineNoeuds.getLength(); i++) {
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals(departement)) {
                	NodeList depNoeuds = racineNoeuds.item(i).getChildNodes();
                    for (int j = 0; j < depNoeuds.getLength(); j++) {
                    	if(depNoeuds.item(j).getNodeType() == Node.ELEMENT_NODE && depNoeuds.item(j).getNodeName().equals(difficulte)) {
                    		NodeList difNoeuds = depNoeuds.item(j).getChildNodes();
                    		for (int k = 0; k < difNoeuds.getLength(); k++) {
                    			if(difNoeuds.item(k).getNodeType() == Node.ELEMENT_NODE && difNoeuds.item(k).getNodeName().equals("tache")) {
                    				Element elementTache = (Element) difNoeuds.item(k);
                    				String nom = elementTache.getElementsByTagName("nom").item(0).getTextContent();
                    				String description = elementTache.getElementsByTagName("description").item(0).getTextContent();
                    				
                    				temps = Integer.parseInt(elementTache.getElementsByTagName("temps").item(0).getTextContent());
                    				infectes = Integer.parseInt(elementTache.getElementsByTagName("infecte").item(0).getTextContent());
                    				
                    				a = new TacheModele(nom, description, new Compteur(temps, CompteurType.Temps), new Compteur(infectes, CompteurType.Infectes));
                    				listeTaches.add(a);
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
	
	public List<TacheModele> getListTache() {
		return listeTaches;
	}
	
	public static void main(String[] args) {
		TacheModeleList tmp = new TacheModeleList("Informatique", "Facile");
		List<TacheModele> tmp2 = tmp.getListTache();
		for (TacheModele tmp2Elm : tmp2) {
			tmp2Elm.affichage();
			System.out.println("\n");
		}
	}
}
