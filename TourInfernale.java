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
			int entier = sc.nextInt();
			
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
	
	public void verification(){
		
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
		int[] jPerdant = new int[ti.getTiNbJoueurs()-1];
		for(int i=0; i<jPerdant.length; i++)
			jPerdant[i] = -1;
		while(jeu == false){
			int numJoueur = ti.getTiNbTours()%ti.getTiNbJoueurs();
			int tourSuivant = 0;
			for(int i = 0; i<ti.getTiNbJoueurs()-1;i++)
			{
				if(numJoueur==jPerdant[i])
				{
					tourSuivant = 1;
					ti.setTiNbTours(ti.getTiNbTours()+1);
				}
			}
			if(tourSuivant == 1)
			{
				tourSuivant = 0;
				break;
			}
			
			// selection du joueur suivant le tour
			ti.listeJ.get(numJoueur);
			
			//Vérifions si le joueur est placé sur une case bonus
			boolean bonus = false;
			if(ti.p.getEtat(ti.listeJ.get(numJoueur).jHor,ti.listeJ.get(numJoueur).jVer) == 3){
				bonus = true;
				System.out.println("Joueur " + ti.listeJ.get(numJoueur).getNom() + ", vous êtes actuellement sur une case bonus. Vous avez donc la possibilité de jouer une seconde fois.");
			}
			
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
			
			int[] nouvellePosition = ti.p.deplacer(M, ti.listeJ.get(numJoueur).jHor, ti.listeJ.get(numJoueur).jVer); 
			ti.listeJ.get(numJoueur).setJoueur(nouvellePosition[0],nouvellePosition[1]);
			System.out.print(ti.p.toString());
			
			// incrementation du nb de tours
			if(bonus == true){
				ti.setTiNbTours(ti.getTiNbTours());
			}
			else{
				ti.setTiNbTours(ti.getTiNbTours()+1);
			}
			
			//On vérifie si la partie est fini
			int joueurElimine = 0;
			for(int i=0; i<nbjoueur; i++){
				if(     ti.listeJ.get(i).jHor != 0 && ti.listeJ.get(i).jVer != 0 && ti.listeJ.get(i).jHor != ti.p.abs && ti.listeJ.get(i).jVer != ti.p.ord   
						&& ti.p.getEtat(ti.listeJ.get(i).jHor+1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor-1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer+1)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer-1)!=0){
					joueurElimine++;
					System.out.println(ti.listeJ.get(i).jName + " a perdu !");
				}
				else if(ti.listeJ.get(i).jHor == 0 && ti.listeJ.get(i).jVer == 0   
						&& ti.p.getEtat(ti.listeJ.get(i).jHor+1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer+1)!=0){
					joueurElimine++;
					System.out.println(ti.listeJ.get(i).jName + " a perdu !");
				}
				else if(ti.listeJ.get(i).jHor == 0 && ti.listeJ.get(i).jVer == ti.p.ord   
						&& ti.p.getEtat(ti.listeJ.get(i).jHor+1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer-1)!=0){
					joueurElimine++;
					System.out.println(ti.listeJ.get(i).jName + " a perdu !");
				}
				else if(ti.listeJ.get(i).jHor == ti.p.abs && ti.listeJ.get(i).jVer == ti.p.ord   
						&& ti.p.getEtat(ti.listeJ.get(i).jHor-1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer-1)!=0){
					joueurElimine++;
					System.out.println(ti.listeJ.get(i).jName + " a perdu !");
				}
				else if(ti.listeJ.get(i).jHor == ti.p.abs && ti.listeJ.get(i).jVer == 0   
						&& ti.p.getEtat(ti.listeJ.get(i).jHor-1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer+1)!=0){
					joueurElimine++;
					System.out.println(ti.listeJ.get(i).jName + " a perdu !");
				}
				else if(ti.listeJ.get(i).jHor == 0 && ti.listeJ.get(i).jVer != 0 && ti.listeJ.get(i).jVer != ti.p.ord 
						&& ti.p.getEtat(ti.listeJ.get(i).jHor+1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer+1)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer-1)!=0){
					joueurElimine++;
					System.out.println(ti.listeJ.get(i).jName + " a perdu !");
				}
				else if(ti.listeJ.get(i).jHor == ti.p.abs && ti.listeJ.get(i).jVer != 0 && ti.listeJ.get(i).jVer != ti.p.ord   
						&& ti.p.getEtat(ti.listeJ.get(i).jHor-1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer+1)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer-1)!=0){
					joueurElimine++;
					System.out.println(ti.listeJ.get(i).jName + " a perdu !");
				}
				else if(ti.listeJ.get(i).jHor != 0 && ti.listeJ.get(i).jHor != ti.p.abs  && ti.listeJ.get(i).jVer == 0   
						&& ti.p.getEtat(ti.listeJ.get(i).jHor+1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor-1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer+1)!=0){
					joueurElimine++;
					System.out.println(ti.listeJ.get(i).jName + " a perdu !");
				}
				else if(ti.listeJ.get(i).jHor != 0 && ti.listeJ.get(i).jHor != ti.p.abs  && ti.listeJ.get(i).jVer == ti.p.ord   
						&& ti.p.getEtat(ti.listeJ.get(i).jHor+1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor-1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer-1)!=0){
					joueurElimine++;
					System.out.println(ti.listeJ.get(i).jName + " a perdu !");
				}
				
			}
			
//			// try-catch : gestion de l'erreur
//			try
//			{
//				deplacerJoueur();
//			}
//			catch (IndexOutOfBoundsException | InputMismatchException e2)
//			{
//				System.out.println("Vous ne pouvez effectuer ce déplacement !");
//			}
//			finally
//			{
//				scanner.close();
//			}
			
//			// do-while
//			do
//			{
//				System.out.println("Je m'execute toujours une fois.");
//			}
//			while(false);
			
			
			
			//On vérifie si nbjoueur-1 ont perdu
			if(joueurElimine == nbjoueur-1){
				jeu = true;
			}
			
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