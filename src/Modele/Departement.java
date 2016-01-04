package Modele;

import Enumerations.CompteurType;
import Enumerations.DepartementNom;
import Vue.Compteur;

import java.util.ArrayList;
import java.util.List;

public class Departement implements java.io.Serializable {
    Enumerations.DepartementNom nom;
    int nbPersonne;
    List<Compteur> compteurs;
    List<Modele.Tache> taches;
    Modele.ArbreDeCompetence arbre;

    public Departement(DepartementNom depNom){
        this.nom = depNom;
        this.nbPersonne = 200 + (int)(Math.random() * 201);
        this.arbre = new ArbreDeCompetence(this);
        Compteur efficacite = new Compteur(100,100, CompteurType.Efficacite);
        Compteur moral = new Compteur(100,100, CompteurType.Moral);
        Compteur infecte = new Compteur(0,nbPersonne, CompteurType.Infectes);
        Compteur standBy = new Compteur(0,nbPersonne, CompteurType.Stand_By);
        compteurs = new ArrayList<>(4);
        compteurs.add(efficacite);
        compteurs.add(moral);
        compteurs.add(infecte);
        compteurs.add(standBy);
        taches = new ArrayList<>();
    }
    void affichage(){

    }
    void creerTache(){
        int difficulte = 1 + (int)(Math.random() * 3);
        String difficulteTache;
        switch (difficulte){
            case 1 :
                difficulteTache="Facile";
                break;
            case 2 :
                difficulteTache="Moyenne";
                break;
            case 3 :
                difficulteTache="Avancee";
                break;
            default:
                difficulteTache="Facile";
                break;
        }
        Modele.Tache tache = new Tache(String.valueOf(this.nom),difficulteTache,0);
        compteurs.get(3).modifCompte(-tache.getCompteurs().get(1).getCompte());
        compteurs.get(2).modifCompte(tache.getCompteurs().get(1).getCompte());
        taches.add(tache);
    }
    void infection(){
        int nbTaches = taches.size();
        int infection = nbTaches + (100-compteurs.get(1).getCompte())/100*nbTaches;
        compteurs.get(3).modifCompte(-infection);
        compteurs.get(2).modifCompte(infection);
    }
    void supprimerTache(){
        for(Tache tache :  taches){
            if(tache.getTermine()){
                compteurs.get(3).modifCompte(tache.getCompteurs().get(1).getCompte());
                compteurs.get(2).modifCompte(-tache.getCompteurs().get(1).getCompte());
                taches.remove(tache);
            }
        }
    }
    public int getEfficacite() { return compteurs.get(0).getCompte(); }
    public void setEfficacite(int ajout) { compteurs.get(0).modifCompte(ajout); }
    public int getMorale() { return compteurs.get(1).getCompte(); }
    public void setMorale(int ajout) { compteurs.get(1).modifCompte(ajout); }
    public String getNom(){ return String.valueOf(nom);}
    public ArbreDeCompetence getArbre(){ return arbre;}
    public DepartementNom getNomEnum(){ return nom;}
    public int getNbTaches(){ return taches.size();}

    public int getNbActif(){ return compteurs.get(2).getCompte();}
    public int getNbPersonne() {return nbPersonne;}
}
