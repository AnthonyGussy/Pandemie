package Vue;

import Enumerations.CompteurType;
import Vue.Compteur;

public class Date extends Compteur {
    Date(int c, CompteurType t){
        super(c, t);
    }
    Date(int c, int vMax, CompteurType t){
        super(c, vMax, t);
    }
    public void affichage(){}
}
