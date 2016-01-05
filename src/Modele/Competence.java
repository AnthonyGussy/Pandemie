package Modele;

public class Competence implements java.io.Serializable {

    String nom;
    String description;
    int ligne;
    int colonne;
    int[] effet;  //contient 3 valeurs correspondant a l'efficacite, le moral et le temps
    int cout;
    int nbLignes;
    int nbColonnes;
    boolean debloque;
    boolean achete;
    String sommetLie;   //la liste des sommets lie de forme ligne,colonne separer par des points virgules

    ArbreDeCompetence arbreDeCompetence;

    Competence(String _nom, String _description, int _ligne, int _colonne, int[] _effet, int _cout, String _sommetLie, int _nbColonnes, int _nbLignes, ArbreDeCompetence arbre){

        arbreDeCompetence = arbre;
        achete = false;
        nom = _nom;
        description = _description;
        ligne = _ligne;
        colonne = _colonne;
        effet = _effet;
        cout = _cout;
        nbColonnes = _nbColonnes;
        nbLignes = _nbLignes;
        sommetLie = _sommetLie;
        debloque = ligne == 1;




    }

    public void applicationCompetenceDepartement(){

        if(effet[0] != 0) arbreDeCompetence.getDepartement().setEfficacite(effet[0]);
        if(effet[1] != 0) arbreDeCompetence.getDepartement().setMoral(effet[1]);


    }

    public void setAchete(){ achete = true; }

    public void setDebloque(){ debloque = true; }

    public int getLigne() { return ligne;}

    public int getColonne() { return colonne;}

    public String getNom(){ return nom;}

    public String getDescription() { return description;}

    public int getNbLignes() { return nbLignes; }

    public int getNbColonnes() { return nbColonnes; }

    public int[] getEffet() {return effet; }

    public ArbreDeCompetence getArbreDeCompetence() { return  arbreDeCompetence; }

    public boolean getDebloque(){ return debloque;}

    public boolean getAchete(){ return achete;}

}
