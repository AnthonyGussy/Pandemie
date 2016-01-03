package Modele;

public class EvenementPopUp extends Evenement implements java.io.Serializable {

    private int duree;
    private int effet;
    
    public void affichage(){
    	super.affichage();
    }
    
    public void appliquerEffet(){}
    
    public int getDuree() { return duree; }
    
    public int getEffet() { return effet; }
}
