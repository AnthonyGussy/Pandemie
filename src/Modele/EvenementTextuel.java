package Modele;

/**
 *
 */
public class EvenementTextuel extends Evenement{
    public EvenementTextuel(String nom, String description, Modele.Jeu jeu) {
        super(nom, description, jeu);
        event = new Vue.Evenement(this, jeu);
    }
}
