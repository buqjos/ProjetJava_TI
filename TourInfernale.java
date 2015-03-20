//import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.LinkedList;
//import java.io.*;

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
	
	// pour les saisies
	private Scanner scanner = new Scanner(System.in);
	
	public TourInfernale()
	{
		nbTours = 0;
		nbJoueurs = 2;
		p = new Plateau(9,9);
		listeJ.add(new Humain(2,3,"jojo"));
		listeJ.add(new Humain(5,6,"jerem"));
	}
	
	TourInfernale(int nbJoueurs)
	throws NombreDeJoueursException
	{
		if(nbJoueurs<0)
			throw new NombreDeJoueursException();
		else
			this.nbJoueurs = nbJoueurs;
	
		this.nbTours=0;
		this.nbJoueurs=nbJoueurs;
		
		// creation du Plateau de jeu
		this.p = new Plateau(creationPlateau());
		// declaration et initialisation des joueurs
		saisieJoueurs();
	}
	
	public void finalise()
	{
		scanner.close();
		System.out.println("Objet Tour Infernale nettoyé.");
	}
	
	public void saisieJoueurs()
	{
		for (int i=0; i<this.nbJoueurs; i++)
		{
			int jAbs,jOrd;
			String jNom;
			System.out.println("Quel est le nom du joueur "+(i+1)+" ?");
			jNom = scanner.next();
			System.out.println("Quel est la position du joueur "+(i+1)+" ?");
			
			jAbs = lireEntier();
			jOrd = lireEntier();			
			this.listeJ.add(new Humain(jAbs,jOrd, jNom));
			
			p.setEtat(listeJ.get(i).getJoueur());	
		}
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
	
	public int[] creationPlateau()
	{
		return creationPlateau(5,20);
	}
	
	public int[] creationPlateau(int min, int max)
	{
		// paramètres de jeu		
		int valInt = 0;
		int[] pVal = new int[2];
		
		do
		{
			System.out.println("Veuillez saisir la largeur du plateau svp :("+min+","+max+")");
			valInt = lireEntier("");
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
	
	public boolean testVictoire (int nbJoueurElimine)
	{
		if(nbJoueurElimine == this.nbJoueurs - 1)
		{
			System.out.println("Fin de la partie");
			return true;
		}
		else return false;
	}
	
	// vérifie si une chaine de caractère est un entier
	// méthode testée et fonctionnelle
	public boolean verifEntier(String entier)
	{
		try
		{
			Integer.parseInt(entier);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Variable non entière.");
			return false;
		}
	}
	
	public int lireEntier()
	{
		return lireEntier("Veuillez saisir un entier svp");
	}
	
	public int lireEntier(String texteUtilisateur)
	{
		System.out.println(texteUtilisateur);
		
		String entier = scanner.next();
		
		if (texteUtilisateur.isEmpty() || !verifEntier(entier))
		{
	        System.out.println("Veuillez renseigner votre choix svp.");
	        return lireEntier(texteUtilisateur);
	    } else {
	        return Integer.parseInt(entier);
	    }
	}
	
	public static void main(String[] args)
	{	
		Scanner scan = new Scanner(System.in);
		String s;
		boolean cont = true;
		do{
			s=scan.next();
			if(s.matches("^[a-zA-Z]*$"))
				cont=false;
		}
		while(cont);
		System.out.println("Super !");
		scan.reset();
		scan.close();

		TourInfernale test = new TourInfernale();
		System.out.println(test.lireEntier("Saisissez un entier svp."));
		
		//Initialisation de la partie
		Scanner scanner = new Scanner(System.in);
		System.out.println("Combien y a-t-il de joueurs ?");
		int nbjoueur = scanner.nextInt();
		scanner.close();

		// test de jeu
		TourInfernale ti = null;
		try
		{
			ti = new TourInfernale(nbjoueur);
		}
		catch(NombreDeJoueursException e){}
		finally
		{
			if(nbjoueur < 0)
			{
				System.out.println("Sélection automatique : 2 joueurs.");
				ti = new TourInfernale();
			}
			System.out.println(ti.p.toString());
		}
		
		
		
		//Début partie
		boolean jeu = false;
		int[] jPerdant = new int[ti.getTiNbJoueurs()-1];
		for(int i=0; i<jPerdant.length; i++)
			jPerdant[i] = -1;
		while(jeu == false)
		{
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
				System.out.println("Joueur " + ti.listeJ.get(numJoueur).getNom() + ", vous êtes actuellement sur une case bonus. Vous avez donc la possibilit� de jouer une seconde fois.");
			}
			
			// choix d'une case à griser
			System.out.println("Joueur " + ti.listeJ.get(numJoueur).getNom() + ", choisissez une case à griser.");
			int cgh = scanner.nextInt();
			int cgv = scanner.nextInt();
			ti.p.griserCase(cgh,cgv);
			
			// deplacement du joueur
			System.out.println("Joueur " + ti.listeJ.get(numJoueur).jName + ", veuillez vous déplacer.");
			int M = 0;
			// implementer un fonction lireDeplacement dans Joueur
			M = ti.lireEntier();
			
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
				if(ti.listeJ.get(i).jHor != 0 && ti.listeJ.get(i).jHor != ti.p.abs  && ti.listeJ.get(i).jVer == ti.p.ord   
						&& ti.p.getEtat(ti.listeJ.get(i).jHor+1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor-1, ti.listeJ.get(i).jVer)!=0
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
				else if(     ti.listeJ.get(i).jHor != 0 && ti.listeJ.get(i).jVer != 0 && ti.listeJ.get(i).jHor != ti.p.abs && ti.listeJ.get(i).jVer != ti.p.ord   
						&& ti.p.getEtat(ti.listeJ.get(i).jHor+1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor-1, ti.listeJ.get(i).jVer)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer+1)!=0
						&& ti.p.getEtat(ti.listeJ.get(i).jHor, ti.listeJ.get(i).jVer-1)!=0){
					joueurElimine++;
					System.out.println(ti.listeJ.get(i).jName + " a perdu !");
				}				
			}			
			
			//On vérifie si nbjoueur-1 ont perdu
			jeu = ti.testVictoire(joueurElimine);
		}
	}	
}