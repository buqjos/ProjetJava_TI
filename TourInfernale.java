/**
 * 
 * @author ulysse
 * @name TourInfernale
 * @descr gestion du jeu
 * 
 */
public class TourInfernale 
{

	public static void main(String[] args)
	{
		Plateau plateau = new Plateau();
		
		Joueur j1 = new Humain(3,2);
		plateau.setEtat(j1.getJoueur());
		Joueur j2 = new Humain(4,5);
		plateau.setEtat(j2.getJoueur());
		
		System.out.print(plateau.toString());

	}

}

/*public Joueur creerJoueur(Plateau p, int abs, int ord)
{
	Joueur j = new Humain(abs,ord);
	p.setEtat(j.getJoueur());
	System.out.print(j.toString());
	
	return j;
}*/
