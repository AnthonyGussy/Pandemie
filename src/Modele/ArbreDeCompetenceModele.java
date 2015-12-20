package Modele;
import Vue.CompetenceVue;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Victor on 04/12/2015.
 */
public class ArbreDeCompetenceModele {
    HashMap<String, ArrayList<CompetenceModele>> competencesMod;


    public ArbreDeCompetenceModele(String document){

        competencesMod = new HashMap<>();
        ArrayList<CompetenceModele> listCompetence = new ArrayList<>();

        for(int i=0;i<3;++i){

            CompetenceModele c = new CompetenceModele(document,i,this);
            listCompetence.add(c);

        }

        for(CompetenceModele c: listCompetence){

            competencesMod.put(c.ligne + "," + c.colone, new ArrayList<>());
            competencesMod.get(c.ligne + ","+c.colone).add(c);
            if(c.sommetLie != null) {
                String sommet[] = c.sommetLie.split(",");

                for (String s : sommet) {

                    competencesMod.get(c.ligne + ","+c.colone).add(listCompetence.get(Integer.parseInt(s)));

                }
            }


        }


    }

    public void debloquerCompetence(int ligne, int colone){

        competencesMod.get(ligne+","+colone).get(0).setAchete();
        if(colone > 1 ) {

            boolean debloque = true;
            ArrayList<CompetenceModele> c = competencesMod.get(ligne+1 + "," + (colone - 1));
            for(int i=1;i<c.size();++i){

                if(!c.get(i).debloque || !c.get(i).achete) debloque = false;

            }
            if(debloque) {

                c.get(0).setDebloque();
                System.out.print("debloquer");
            }

        }



        boolean debloque = true;
        ArrayList<CompetenceModele> c = competencesMod.get(ligne+1 + "," + (colone));
        if(c != null && c.size() > 1) {
            for (int i = 1; i < c.size(); ++i) {

                if (!c.get(i).debloque || !c.get(i).achete) debloque = false;

            }
            if (debloque) {
                System.out.print("debloquer");
                c.get(0).setDebloque();
            }
        }

    }

    public HashMap<String, ArrayList<CompetenceModele>> getComp(){ return competencesMod; }
}
