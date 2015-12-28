import Modele.Compteur;

import java.util.List;

/**
 * Created by Victor on 20/11/2015.
 */
public class Departement {
    DepartementNom nom;
    int nbPersonne;
    List<Compteur> compteurs;
    List<Tache> taches;
    ArbreDeCompetence arbre;
    void affichage(){}
    void creerTache(){}
    void infection(){}
    void supprimerTache(){}
}
