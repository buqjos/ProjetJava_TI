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
	// declaration des variables
	private int nbTours;
	private int nbJoueurs;
	private Plateau p;
	private LinkedList<Joueur> listeJ;
	
	TourInfernale(int nbJoueurs)//, int pLargeur, int pHauteur)
	{
		this.nbTours=0;
		this.nbJoueurs=nbJoueurs;
//		this.p = new Plateau(pLargeur, pHauteur);
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
		// creation du plateau de jeu
		Plateau plateau = new Plateau(creationPlateau());
		
		Joueur j1;
		j1 = creerJoueur(plateau, 3, 2);
		plateau.setEtat(j1.getJoueur());
		Joueur j2 = new Humain(4,5);
		plateau.setEtat(j2.getJoueur());
		if(plateau.griserCase(6, 6)==0)
			System.out.println("Case grisée !");
		else
			System.out.println("Case NON grisée.");		
		System.out.print(plateau.toString());
//		Scanner scanner = new Scanner(System.in);
//		int deplacement;
//		deplacement = scanner.nextInt();
		j1.deplacer(6);
//		scanner.close();
		plateau.setEtat(j1.getJoueur());
		System.out.print(plateau.toString());

//		// test de jeu
//		TourInfernale jeu = new TourInfernale(2, 5, 5);
	}
}

//// bout de code try catch-scanner
//try
//{
//	Scanner sc = new Scanner(System.in);
//	int nb;
//	nb = sc.nextInt();
//	sc.close();
//}
//catch (ArrayIndexOutOfBoundsException e)
//{
//	System.out.println("Reste dans le tableau connard !");
//}