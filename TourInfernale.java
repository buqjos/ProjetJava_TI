import java.util.Scanner;
import java.util.LinkedList;

/**
 * 
 * @author ulysse
 * @name TourInfernale
 * @descr gestion du jeu
 * 
 */
public class TourInfernale 
{
	// nombre de tours
	private int nbTours;
	private int nbJoueurs;
	private Plateau p;
	private LinkedList<Joueur> listeJ;
	
	TourInfernale(int nbJoueurs, int pLargeur, int pHauteur)
	{
		this.nbTours=0;
		this.nbJoueurs=nbJoueurs;
		this.p = new Plateau(pLargeur, pHauteur);
		for (int i=0; i<this.nbJoueurs; i++)
		{
			this.listeJ.add(new Humain(i,i));
			p.setEtat(listeJ.get(i).getJoueur());
		}
		System.out.println(p.toString());
	}

	public static Joueur creerJoueur(Plateau p, int abs, int ord)
	{
		Joueur j = new Humain(abs,ord);
		p.setEtat(j.getJoueur());
		System.out.print(j.toString());
		
		return j;
	}
	
	public static int[] creationPlateau()
	{
		// parametres de jeu
		Scanner scanner = new Scanner(System.in);
		
		int valInt = 0;
		int[] pVal = new int[2];
		int min = 5;
		int max = 20;
		do
		{
			System.out.println("Veuillez saisir la largeur du plateau svp :("+min+","+max+")");
			valInt = scanner.nextInt();
			System.out.println("Vous avez saisi : " + valInt);
			pVal[0] = valInt;
		}
		while(valInt<=min || valInt>=max);
		
		do
		{
			System.out.println("Veuillez saisir la hauteur du plateau svp :("+min+","+max+")");
			valInt = scanner.nextInt();
			System.out.println("Vous avez saisi : " + valInt);
			pVal[1] = valInt;
		}
		while(valInt<=min || valInt>=max);
		
		// ferme le lecteur
		scanner.close();
		
		return pVal;
	}
	
	public static void main(String[] args)
	{
		int [] tab = new int[2];
		
		boolean tabVal = true;
		do
		{
			try
			{
				Scanner sc = new Scanner(System.in);
				int nb;
				nb = sc.nextInt();
				tab[nb] = 4;
				sc.close();
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Reste dans le tableau connard !");
				tabVal = false;
			}
		}
		while (tabVal==false);
		
//		// creation du plateau de jeu
//		Plateau plateau = new Plateau(creationPlateau());
//		
//		Joueur j1;
//		j1 = creerJoueur(plateau, 3, 2);
//		//plateau.setEtat(j1.getJoueur());
//		Joueur j2 = new Humain(4,5);
//		plateau.setEtat(j2.getJoueur());
//		
//		plateau.griserCase(6, 6);
//		
//		System.out.print(plateau.toString());
		 
		
//		// test de jeu
//		TourInfernale jeu = new TourInfernale(2, 5, 5);

	}
}