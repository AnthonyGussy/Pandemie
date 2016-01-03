package Modele;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Vue.EvenementArticleVue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Enumerations.DepartementNom;


public class EvenementArticleModele extends Evenement implements Constantes, java.io.Serializable {
	
	// Attributs
	Vue.EvenementArticleVue eAV;
	private String nom;
	private String description;
	private int effets[] = new int[TAILLE_EFFETS]; // Contient le moral, efficacité et temps
	
	// Méthodes
	public EvenementArticleVue getEAV() { return eAV; }
	// Constructeurs
	
	public EvenementArticleModele(DepartementNom departement, String nom, String description, int effets[]) {
		super(departement);
		this.nom = nom;
		this.description = description;
		this.effets = effets;
		eAV = new EvenementArticleVue(this);
	}
	
	public EvenementArticleModele(DepartementNom departement, String difficulte, int index) {
		
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

                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals(difficulte)) {
                	
                	NodeList evenNoeuds = racineNoeuds.item(i).getChildNodes();
                    
                    for (int j = 0; j < evenNoeuds.getLength() && !initialiser; j++) {

                    	if(evenNoeuds.item(j).getNodeType() == Node.ELEMENT_NODE && evenNoeuds.item(j).getNodeName().equals("evenement")) {
                    		
		            		if (compteur == index) {
			                    Element elementEven = (Element) evenNoeuds.item(j);
			
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
            }
        }
        
        catch (final ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
		eAV = new EvenementArticleVue(this);
	}

	// temporaire
/*	public void affichage(){
    	super.affichage();
    	System.out.println("nom : "+nom);
    	System.out.println("description : "+description);
    	System.out.println("moral : "+effets[0]);
    	System.out.println("efficacité : "+effets[1]);
    	System.out.println("temps : "+effets[2]);
    }
*/
	public void appliquerEffet(){}
	
	// Test
/*	public static void main(String[] args) {
		EvenementArticleModele a = new EvenementArticleModele(DepartementNom.Informatique, "Moyen", 1);

		EvenementArticleModele b = new EvenementArticleModele(a.departement, a.nom, a.description, a.effets);
		a.effets[0] = 1000;
		b.effets[1] = -5;
		a.affichage();
		b.affichage();
	}
*/
	public String getNom() { return nom; }
	
	public void setNom(String nom) { this.nom = nom; }
	
	public String getDescription() { return description; }
	
	public void setDescription(String description) { this.description = description; }
	
	public int[] getEffets() { return effets; }
	
	public int getMoral() { return effets[0]; }
	
	public void setMoral(int moral) { this.effets[0] = moral; }
	
	public int getEfficacite() { return effets[1]; }
	
	public void setEfficacite(int efficacite) { this.effets[1] = efficacite; }
	
	public int getTemps() { return effets[2]; }
	
	public void setTemps(int temps) { this.effets[2] = temps; }
}
