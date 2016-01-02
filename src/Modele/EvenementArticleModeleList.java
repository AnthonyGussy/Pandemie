package Modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Enumerations.DepartementNom;

public class EvenementArticleModeleList implements Constantes{
	
	List<EvenementArticleModele> liste;
	
	public EvenementArticleModeleList(DepartementNom departement, String difficulte) {
		liste = new ArrayList<>();
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new File(PATH_EVEN_ARTICLE_MODELE));
            Element racine = doc.getDocumentElement();
            NodeList racineNoeuds = racine.getChildNodes();
            EvenementArticleModele a = null;
            int effets[] = {0,0,0};
            
            for (int i = 0; i < racineNoeuds.getLength(); i++) {
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals(difficulte)) {
                	NodeList evenNoeuds = racineNoeuds.item(i).getChildNodes();
                    for (int j = 0; j < evenNoeuds.getLength(); j++) {

                    	if(evenNoeuds.item(j).getNodeType() == Node.ELEMENT_NODE && evenNoeuds.item(j).getNodeName().equals("evenement")) {

			                Element elementEven = (Element) evenNoeuds.item(j);
			                
			                String nom = elementEven.getElementsByTagName("nom").item(0).getTextContent();
			                String description = elementEven.getElementsByTagName("description").item(0).getTextContent();
			                effets[0] = Integer.parseInt(elementEven.getElementsByTagName("moral").item(0).getTextContent());
			                effets[1] = Integer.parseInt(elementEven.getElementsByTagName("efficacite").item(0).getTextContent());
			                effets[2] = Integer.parseInt(elementEven.getElementsByTagName("temps").item(0).getTextContent());
			                
			                a = new EvenementArticleModele(departement, nom, description, effets);
			                liste.add(a);
                    	}
                    }
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
	
	public List<EvenementArticleModele> getListEvenement() {
		return liste;
	}
	
	public static void main(String[] args) {
		EvenementArticleModeleList tmp = new EvenementArticleModeleList(DepartementNom.Informatique, "Facile");
		List<EvenementArticleModele> tmp2 = tmp.getListEvenement();
		for (int i = 0; i < tmp2.size(); i++) {
			tmp2.get(i).affichage();
			System.out.println("\n");
		}
	}
	
}
