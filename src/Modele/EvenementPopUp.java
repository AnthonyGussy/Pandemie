package Modele;
/**
 * Created by Victor on 04/12/2015.
 */
public class EvenementPopUp extends Evenement {

    private int duree;
    private int effet;
    
    public void affichage(){
    	super.affichage();
    }
    
    public void appliquerEffet(){}
    
    public int getDuree() { return duree; }
    
    public int getEffet() { return effet; }
}
