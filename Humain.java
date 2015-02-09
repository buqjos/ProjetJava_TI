
public class Humain extends Joueur
{
	public Humain(int abs, int ord)
	{
		super(abs, ord);
	}

	/*
	 * @author
	 * @name : BloquerUneCase
	 * @descr : méthode permettant l'implémentation de la fonction
	 * bloquer une case par le joueur
	 */
	public int[] BloquerUneCase(int abs, int ord)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String toString()
	{		
		return "Je suis un joueur "+this.getClass()+" en position :"+super.getJoueur()[0]+","+super.getJoueur()[1]+"\n";
	}
}
