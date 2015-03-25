<<<<<<< HEAD:TourInfernale/Joueur.java
package TourInfernale;
/**
 * 
 * @author ulysse
 * @name TourInfernale
 * @descr gestion des joueurs
 * 
 */
public abstract class Joueur
{
	protected int jHor, jVer, jEtat;
	protected String jName;
//	protected char jLettre;
	
	public Joueur(int abs, int ord)
	{
		this.jHor = abs;
		this.jVer = ord;
		// voir commentaire de la methode setEtat de Plateau pour plus de details
		this.jEtat = 2;
	}
	
	public Joueur(int abs, int ord, String nom)
	{
		this(abs, ord);
		this.jName = nom;
//		this.jLettre = jName.charAt(0);
	}
	
	public int[] getJoueur()
	{
		int[] joueur = new int[3];
		joueur[0] = this.jHor;
		joueur[1] = this.jVer;
		joueur[2] = this.jEtat;
		return joueur;
	}
	
	public void setJoueur(int abs, int ord)
	{
		this.jHor = abs;
		this.jVer = ord;
	}
	
	public abstract String getNom();
	
	public abstract String toString();
}
=======
/**
 * 
 * @author ulysse
 * @name TourInfernale
 * @descr gestion des joueurs
 * 
 */
public abstract class Joueur
{
	protected int jHor, jVer, jEtat;
	protected String jName;
	
	public Joueur(int abs, int ord)
	{
		this.jHor = abs;
		this.jVer = ord;
		// voir commentaire de la methode setEtat de Plateau pour plus de details
		this.jEtat = 2;
	}
	
	public Joueur(int abs, int ord, String nom)
	{
		this(abs, ord);
		this.jName = nom;
	}
	
	public int[] getJoueur()
	{
		int[] joueur = new int[3];
		joueur[0] = this.jHor;
		joueur[1] = this.jVer;
		joueur[2] = this.jEtat;
		return joueur;
	}
	
	public void setJoueur(int abs, int ord)
	{
		this.jHor = abs;
		this.jVer = ord;
	}
	
	public abstract String getNom();
	
	public abstract void deplacer(int M);
	
	public abstract String toString();
}
>>>>>>> f2dfedafc9f2cff8cca9a14cf1820e75596035f4:Joueur.java
