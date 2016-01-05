package Constantes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Les différentes constantes utilisées dans le programme
 */
public final class Constantes {
	// Le tableau "effets"
	public static final int TAILLE_EFFETS = 3;
	public static final int MORAL = 0;
	public static final int EFFICACITE = 1;

	public static final int PTS_COMPETENCE = 0;
	public static final int TEMPS = 1;
	
	// Les chemins
	public static final String PATH_XML = System.getProperty("user.dir" )+"/xml/";
	public static final String PATH_EVEN_ARTICLE_MODELE = PATH_XML+"evenementarticle.xml";
	public static final String PATH_TACHES = PATH_XML+"tache.xml";
	public static final String PATH_PROJET = PATH_XML+"projet.xml";

	// Affichage jeu
	public static final double POS_X_LISTE = 0.84;
	public static final double POS_Y_LISTE = 0.5;
	public static final double LARGEUR_LISTE = 0.14;
	public static final double HAUTEUR_LISTE = 0.18;
	public static final double POS_X_TEXTE = 0.85;
	public static final double POS_Y_TEXTE = 0.47;
	public static final double TAILLE_POLICE = 0.032;
	public static final double POS_X_NOM_DEP = 0.83;
	public static final double POS_Y_NOM_DEP = 0.48;
	public static final double POS_X_INFOS = 0.835;
	public static final double POS_Y_INFOS = 0.55;

	// Affichage menu
	public static final double POS_X_BOUTON = 0.17;
	public static final double POS_Y_BOUTON = 0.5;
	public static final double LARGEUR_BOUTON = 0.15;
	public static final double HAUTEUR_BOUTON = 0.1;
	public static final double LARGEUR_BOUTON_SURVOL = 0.155;
	public static final double HAUTEUR_BOUTON_SURVOL = 0.105;

	// Affichage arbre de compétences
	public static final double TAILLE_POLICE_TITRE = 0.030;

	// Positions départements
	public static final double POS_X_INFO = 0.269;
	public static final double POS_Y_INFO = 0.528;
	public static final double POS_X_ENERGIE = 0.069;
	public static final double POS_Y_ENERGIE = 0.361;
	public static final double POS_X_IMSI = 0.537;
	public static final double POS_Y_IMSI = 0.375;
	public static final double POS_X_GMC = 0.500;
	public static final double POS_Y_GMC = 0.798;
	public static final double POS_X_EDIM = 0.046;
	public static final double POS_Y_EDIM = 0.729;

	// Polygones départements
	public static final ArrayList<Double> POLYGONE_INFO = new ArrayList<>(Arrays.asList(15.0, 35.0,
			104.0, 5.0,
			227.0, 19.0,
			258.0, 91.0,
			236.0, 148.0,
			94.0, 154.0,
			14.0, 125.0));
	public static final ArrayList<Double> POLYGONE_ENERGIE = new ArrayList<>(Arrays.asList(20.0, 32.0,
			93.0, 4.0,
			234.0, 3.0,
			267.0, 80.0,
			198.0, 104.0,
			95.0, 162.0,
			4.0, 146.0));
	public static final ArrayList<Double> POLYGONE_IMSI = new ArrayList<>(Arrays.asList(3.0, 19.0,
			86.0, 2.0,
			172.0, 3.0,
			254.0, 44.0,
			248.0, 122.0,
			180.0, 138.0,
			94.0, 124.0,
			25.0, 132.0));
	public static final ArrayList<Double> POLYGONE_GMC = new ArrayList<>(Arrays.asList(4.0, 10.0,
			79.0, 11.0,
			122.0, 30.0,
			167.0, 26.0,
			186.0, 0.0,
			239.0, 2.0,
			243.0, 111.0,
			6.0, 116.0));
	public static final ArrayList<Double> POLYGONE_EDIM = new ArrayList<>(Arrays.asList(4.0, 21.0,
			77.0, 2.0,
			133.0, 72.0,
			225.0, 68.0,
			229.0, 160.0,
			6.0, 161.0));
}
