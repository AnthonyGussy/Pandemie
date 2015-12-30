package Modele;

// Les constantes pour le jeu

public interface Constantes {
	
	// Le tableau "effets"
	public static final int TAILLE_EFFETS = 3;
	public static final int INDEX_MORAL = 0;
	public static final int INDEX_EFFICACITE = 1;
	public static final int INDEX_TEMPS = 2;
	
	// Les chemins
	public static final String PATH_XML = System.getProperty("user.dir" )+"/xml/";
	public static final String PATH_EVEN_ARTICLE_MODELE = PATH_XML+"evenementarticle.xml";
}
