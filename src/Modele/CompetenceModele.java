package Modele;

import java.io.*;

/**
 * Created by Anthony on 15/12/2015.
 */
public class CompetenceModele {

    String nom;
    String description;
    int ligne;
    int colone;
    int[] effet;  //contient 3 valeurs correspondant a l'efficacite, le moral et le temps
    int cout;
    boolean debloque;
    boolean achete;
    String sommetLie;   //la liste des sommets lie separer par des virgules

    ArbreDeCompetenceModele arbreDeCompetence;

    CompetenceModele(String document,int numeroCompetence, ArbreDeCompetenceModele arbre){

        arbreDeCompetence = arbre;
        achete = false;
        effet = new int[3];
        InputStream flux= null;
        String lignes;
        int i=0;

        try {

            flux = new FileInputStream(document);
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            while ((lignes=buff.readLine())!=null && i!=numeroCompetence+1){

                if(i == numeroCompetence){

                    String info[] = lignes.split(";");
                    nom = info[0];
                    description = info[1];
                    ligne = Integer.parseInt(info[2]);
                    colone = Integer.parseInt(info[3]);
                    effet[0] = Integer.parseInt(info[4]);
                    effet[1] = Integer.parseInt(info[5]);
                    effet[2] = Integer.parseInt(info[6]);
                    cout =  Integer.parseInt(info[7]);
                    if(info.length == 9) sommetLie = info[8];
                    else sommetLie = null;
                    if(ligne==1) debloque = true;
                    else debloque = false;

                }

                ++i;

            }
            buff.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void applicationCompetence(){}

    public void setAchete(){ achete = true; }

    public void setDebloque(){ debloque = true; }

    public int getLigne() { return ligne;}

    public int getColone() { return colone;}

    public String getNom(){ return nom;}

    public String getDescription() { return description;}

    public ArbreDeCompetenceModele getArbreDeCompetence() { return  arbreDeCompetence; }

    public boolean getDebloque(){ return debloque;}

    public boolean getAchete(){ return achete;}

}
