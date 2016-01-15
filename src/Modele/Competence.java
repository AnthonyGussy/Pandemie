package Modele;

/**
 * Partie Modele de la classe Compétence
 * Cette classe sert à instancier une compétence
 */
public class Competence implements java.io.Serializable {

    // Champs
    protected String nom;
    protected String description;
    protected int ligne;
    protected int colonne;
    protected int[] effets; // Contient 3 valeurs correspondant a l'efficacité, le moral et le temps
    protected int cout;
    protected int nbLignes;
    protected int nbColonnes;
    protected boolean debloque;
    protected boolean achete;
    protected String sommetLie; // La liste des sommets liés de forme ligne,colonne séparés par des points virgules
    transient protected ArbreDeCompetence arbreDeCompetence;

    // Constructeur
    Competence(String nom, String description, int ligne, int colonne, int[] effets, int cout, String sommetLie, int nbColonnes, int nbLignes, ArbreDeCompetence arbre){
        arbreDeCompetence = arbre;
        achete = false;
        this.nom = nom;
        this.description = description;
        this.ligne = ligne;
        this.colonne = colonne;
        this.effets = effets;
        this.cout = cout;
        this.nbColonnes = nbColonnes;
        this.nbLignes = nbLignes;
        this.sommetLie = sommetLie;
        debloque = ligne == 1;
    }

    // Getters et setters
    public void setAchete(){ achete = true; }
    public void setDebloque(){ debloque = true; }
    public int getLigne() { return ligne;}
    public int getColonne() { return colonne;}
    public String getNom(){ return nom;}
    public String getDescription() { return description;}
    public int getNbLignes() { return nbLignes; }
    public int getNbColonnes() { return nbColonnes; }
    public int[] getEffet() {return effets; }
    public ArbreDeCompetence getArbreDeCompetence() { return  arbreDeCompetence; }
    public boolean getDebloque(){ return debloque;}
    public boolean getAchete(){ return achete;}
    public int getCout() { return cout; }

    // Méthodes
    /**
     * Application des effets des compétences au département
     */
    public void applicationCompetenceDepartement(){
        if(effets[0] != 0) arbreDeCompetence.getDepartement().setEfficacite(effets[0]);
        if(effets[1] != 0) arbreDeCompetence.getDepartement().setMoral(effets[1]);
    }
}
