/**
 * 
 * @author ulysse
 * @name TourInfernale
 * @descr gestion des joueurs
 * 
 */
public abstract class Joueur
{
	private int jHor, jVer, jEtat;
	
	public Joueur(int abs, int ord)
	{
		this.jHor = abs;
		this.jVer = ord;
		// voir commentaire de la methode setEtat de Plateau pour plus de details
		this.jEtat = 2;
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
	
	public abstract int[] BloquerUneCase(int abs, int ord);
	
	public abstract String toString();
	
	

}
