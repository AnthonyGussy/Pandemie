package Modele;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Enumerations.DepartementNom;


public class EvenementArticleModele extends Evenement implements Constantes {
	
	// Attributs
	
	String nom;
	String description;
	int effets[] = new int[TAILLE_EFFETS]; // Contient le moral, efficacité et temps
	
	// Méthodes
	
	// Constructeur
	public EvenementArticleModele(DepartementNom departement, int index) {
		
		super(departement);
        effets = new int[3];
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new File(PATH_EVEN_ARTICLE_MODELE));
            Element racine = doc.getDocumentElement();
            NodeList racineNoeuds = racine.getChildNodes();
            boolean initialiser = false;
            
            int compteur = 0; 
            
            for (int i = 0; i < racineNoeuds.getLength() && !initialiser; i++) {

                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals("evenement")) {
                	
            		if (compteur == index) {
            			
	                    Element elementEven = (Element) racineNoeuds.item(i);
	
	                    nom = elementEven.getElementsByTagName("nom").item(0).getTextContent();
	                    description = elementEven.getElementsByTagName("description").item(0).getTextContent();
	                    effets[0] = Integer.parseInt(elementEven.getElementsByTagName("moral").item(0).getTextContent());
	                    effets[1] = Integer.parseInt(elementEven.getElementsByTagName("efficacite").item(0).getTextContent());
	                    effets[2] = Integer.parseInt(elementEven.getElementsByTagName("temps").item(0).getTextContent());
	
		                initialiser = true;
            		}
            		
                	compteur++;
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
	
	// TO-DO : Méthode qui retourne une liste d'EvenementArticle selon difficulté

	public void affichage(){
    	super.affichage();
    	System.out.println("nom : "+nom);
    	System.out.println("description : "+description);
    	System.out.println("moral : "+effets[0]);
    	System.out.println("efficacité : "+effets[1]);
    	System.out.println("temps : "+effets[2]);
    }
    
	public void appliquerEffet(){}
	
	// Test
	public static void main(String[] args) {
		EvenementArticleModele a = new EvenementArticleModele(DepartementNom.GI, 1);
		a.affichage();
	}
}
