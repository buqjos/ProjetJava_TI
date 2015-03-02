
public class Humain extends Joueur
{
	public Humain(int abs, int ord)
	{
		super(abs, ord);
	}
	
	public Humain(int abs, int ord, String nom)
	{
		super(abs, ord, nom);
	}
	
	public String getNom()
	{
		return this.jName;
	}

	public String toString()
	{		
		return "Je suis un joueur "+this.getClass()+" en position : "+super.getJoueur()[0]+","+super.getJoueur()[1]+"\n";
	}
	
	/*
	 * @author
	 * @name : bloquerUneCase
	 * @descr : méthode permettant l'implémentation de la fonction
	 * bloquer une case par le joueur
	 */
	public int[] bloquerCase(int abs, int ord)
	{
		
		return null;
	}
	

}
