import java.util.InputMismatchException;
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
	private LinkedList<Joueur> listeJ = new LinkedList<Joueur>();
	
	TourInfernale(int nbJoueurs)
	{
		this.nbTours=0;
		this.nbJoueurs=nbJoueurs;
		
		// creation du Plateau de jeu
		this. p = new Plateau(creationPlateau());
		Scanner scanner = new Scanner(System.in);
		// declaration et initialisation des joueurs
		for (int i=0; i<this.nbJoueurs; i++)
		{
			int jAbs,jOrd;
			String jNom;
			System.out.println("Quel est le nom du joueur "+(i+1)+" ?");
			jNom = scanner.next();
			System.out.println("Quel est la position du joueur "+(i+1)+" ?");
			
			boolean saisie = true;

			try
			{
				jAbs = scanner.nextInt();
				jOrd = scanner.nextInt();			
				scanner.reset();
				this.listeJ.add(new Humain(jAbs,jOrd, jNom));
				saisie = false;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Erreur de saisie. Ce n'est pas un entier. On choisit pour vous.");
				scanner.reset();
				this.listeJ.add(new Humain(i,i, jNom));
				saisie = false;
			}
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
	
	public int getTiNbTours()
	{
		return this.nbTours;
	}
	
	public void setTiNbTours(int nbTours)
	{
		this.nbTours = nbTours;
	}
	
	public int getTiNbJoueurs()
	{
		return this.nbJoueurs;
	}
	
	public static int renvoiEntier()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			System.out.println("Veuillez saisir un entier svp.");
			int entier = sc.nextInt(5); // entier de 5 cara max
			
			return entier;
		}
		catch(InputMismatchException e)
		{
			System.out.println("Ce n'est pas un entier.");
			
			return -1;
		}
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
		
		// reflechir si nécessité de fermer le lecteur
		// scanner.close();

		return pVal;
	}
	
	public static void main(String[] args)
	{
		//Initialisation de la partie
		Scanner scanner = new Scanner(System.in);
		System.out.println("Combien y a-t-il de joueurs ?");
		int nbjoueur = scanner.nextInt();

		// test de jeu
		TourInfernale ti = new TourInfernale(nbjoueur);
		
		//Début partie
		int nbTour = 0;
		boolean jeu = false;
		while(jeu == false){
			int numJoueur = ti.getTiNbTours()%ti.getTiNbJoueurs();
			
			// selection du joueur suivant le tour
			ti.listeJ.get(numJoueur);
			
			// choix d'une case à griser
			System.out.println("Joueur " + ti.listeJ.get(numJoueur).getNom() + ", choisissez une case à griser.");
			int cgh = scanner.nextInt();
			int cgv = scanner.nextInt();
			ti.p.griserCase(cgh,cgv);
			
			// deplacement du joueur
			System.out.println("Joueur " + ti.listeJ.get(numJoueur).jName + ", veuillez vous déplacer.");
			int M = 0;
			do
			{
				M = renvoiEntier();
			}
			while(M==-1);
			
			ti.listeJ.get(numJoueur).deplacer(M);
			System.out.print(ti.p.toString());
			
			// incrementation du nb de tours
			ti.setTiNbTours(ti.getTiNbTours()+1);
		}
		
		// fin du jeu
		System.out.println("-----GAME OVER-----");
	}
	
	
//		// creation du plateau de jeu
//		Plateau plateau = new Plateau(creationPlateau());
//		
//		Joueur j1;
//		j1 = creerJoueur(plateau, 3, 2);
//		plateau.setEtat(j1.getJoueur());
//		Joueur j2 = new Humain(4,5);
//		plateau.setEtat(j2.getJoueur());
//		if(plateau.griserCase(6, 6)==0)
//			System.out.println("Case grisée !");
//		else
//			System.out.println("Case NON grisée.");		
//		System.out.print(plateau.toString());
		
		
		
		
//		Scanner scanner = new Scanner(System.in);
//		int deplacement;
//		deplacement = scanner.nextInt();
//		j1.deplacer(6);
////		scanner.close();
//		plateau.setEtat(j1.getJoueur());
//		System.out.print(plateau.toString());

//		// test de jeu
//		TourInfernale jeu = new TourInfernale(2);//, 5, 5);
//	}
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