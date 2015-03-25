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
	
	// main
	public static void main(String[] args)
	{
		// Initialisation de la partie
		Scanner scanner = new Scanner(System.in);
		System.out.println("Combien y a-t-il de joueurs ?");
		int nbjoueur = scanner.nextInt();
		
 		// creation de l'objet de type TourInfernale
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
 				System.out.println("Sélection automatique : 2 joueurs, plateau 9x9");
 				ti = new TourInfernale();
 			}
 		}
		
//		TourInfernale ti = new TourInfernale();
 		
 		// tableau des joueurs perdants
		// chaque joueur a à son numéro soit 0 si il joue soit 1 si il a perdu
 		int[] jPerdant = new int[ti.getTiNbJoueurs()];
 		for(int i:jPerdant)
 			jPerdant[i] = 0;
 		
 		// boucle de jeu
 		boolean jeu = true;
 		while(jeu)
 		{
 			// selection du tour
 			int numJoueur = ti.getTiNbTours()%ti.getTiNbJoueurs();
 			
 			// affichage du plateau
 			System.out.print(ti);
 			
 			// choix d'une case à griser
 			System.out.println("Joueur " + ti.getNomJoueur(numJoueur) + ", choisissez une case à griser.");
 			ti.griserCase();
 			
 			// affichage du plateau
 			System.out.print(ti);
 			
 			// deplacement du joueur
 			System.out.println("Joueur " + ti.getNomJoueur(numJoueur) + ", veuillez vous déplacer.");
 			
 			int[] valDeplacement ={2,4,6,8};
 			int deplacement = ti.lireEntier(valDeplacement);
 			ti.deplacer(numJoueur, deplacement);
 			System.out.print(ti);
 			
 			boolean bonus = false;
 			// incrementation du nb de tours
 			if(bonus == true){
 				ti.setTiNbTours(ti.getTiNbTours());
 				bonus = false;
 			}
 			else{
 				ti.setTiNbTours(ti.getTiNbTours()+1);
 			}
 			
 			// On vérifie si nbjoueur-1 ont perdu
 			// attention si tout les joueurs perdent au même tour :
 			// le test fonctionne mais le joueur victorieux est faux
 			// corriger l'usage "abusif" de condition de verifPerdant
 			jeu = ti.testVictoire(ti.verifPerdant());
 		}
	}
	
	public TourInfernale()
	{
		nbTours = 0;
		nbJoueurs = 2;
		p = new Plateau(9,9);
		listeJ.add(new Humain(2,3,"jojo"));
		listeJ.add(new Humain(5,6,"jerem"));
		this.setJoueur(0, 2, 3);
		this.setJoueur(1, 5, 6);
	}
	
	public TourInfernale(int nbJoueurs)
	throws NombreDeJoueursException
	{
		if(nbJoueurs<0)
			throw new NombreDeJoueursException();
		else
			this.nbJoueurs = nbJoueurs;
	
		this.nbTours = 0;
		this.nbJoueurs = nbJoueurs;
		
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
			
			jAbs = lireEntier("Saisissez l'abscisse.",0,this.p.getLargeur());
			jOrd = lireEntier("Saisissez l'ordonnée.",0,this.p.getHauteur());			
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
	
	public String getNomJoueur(int numJoueur)
	{
		return this.listeJ.get(numJoueur).getNom();
	}
	
	public int[] getJoueur(int numJoueur)
	{
		return this.listeJ.get(numJoueur).getJoueur();
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
			valInt = lireEntier();
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
			return false;
		}
		else return true;
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
		scanner.reset();
		
		if (texteUtilisateur.isEmpty() || !verifEntier(entier))
		{
	        //System.out.println("Veuillez renseigner votre choix svp.");
	        return lireEntier(texteUtilisateur);
	    }
		else
		{
	        return Integer.parseInt(entier);
	    }
	}
	
	public int lireEntier(String texteUtilisateur, int min, int max)
	{
		int entier = lireEntier(texteUtilisateur);
		if(entier<min || entier>max)
		{
			System.out.println("L'entier doit être entre "+min+" et "+max+".");
			return lireEntier(texteUtilisateur, min, max);
		}
		else return entier;		
	}
	
	public int lireEntier(int[] valEntier)
	{
		String message = "";
		message += "Veuillez saisir un entier parmi : ";
		for(int i:valEntier)
			message += valEntier[i] + ",";
		message += "\n";
		System.out.print(message);
		
		int entier = lireEntier();
		
		for (int i:valEntier)
			if(entier!=valEntier[i])
				return lireEntier(valEntier);
		return entier;
	}
	
	// methode de verification de chaine de caractères
	// on s'assure que la chaine fournie suit une expression regex donnée en argument
	public String lireTexte(String regex)
	{
		String s = "";
		
		// lecture de la chaine fournie par l'utilisateur
		s=scanner.next();
		scanner.reset();
		if(s.matches(regex))
			return s;
		else return lireTexte(regex);		
	}
	
	// saisie de caractères alphabétiques uniquement
	public String lireTexte()
	{
		return lireTexte("^[a-zA-Z]*$");
	}
	
	public String toString()
	{
		String message = "";
		
		for (Joueur i:listeJ)
		{
			message += i.toString();
		}
		
		message += "\n";
		
		message += p.toString();
		
		return message;
	}
	
	public int verifPerdant()
	{
		int joueurElimine = 0;
		for(int i=0; i<this.nbJoueurs; i++){
			if(this.listeJ.get(i).jHor != 0 && this.listeJ.get(i).jHor != this.p.abs  && this.listeJ.get(i).jVer == this.p.ord   
					&& this.p.getEtat(this.listeJ.get(i).jHor+1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor-1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer-1)!=0){
				joueurElimine++;
				System.out.println(this.listeJ.get(i).jName + " a perdu !");
			}
			else if(this.listeJ.get(i).jHor == 0 && this.listeJ.get(i).jVer == 0   
					&& this.p.getEtat(this.listeJ.get(i).jHor+1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer+1)!=0){
				joueurElimine++;
				System.out.println(this.listeJ.get(i).jName + " a perdu !");
			}
			else if(this.listeJ.get(i).jHor == 0 && this.listeJ.get(i).jVer == this.p.ord   
					&& this.p.getEtat(this.listeJ.get(i).jHor+1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer-1)!=0){
				joueurElimine++;
				System.out.println(this.listeJ.get(i).jName + " a perdu !");
			}
			else if(this.listeJ.get(i).jHor == this.p.abs && this.listeJ.get(i).jVer == this.p.ord   
					&& this.p.getEtat(this.listeJ.get(i).jHor-1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer-1)!=0){
				joueurElimine++;
				System.out.println(this.listeJ.get(i).jName + " a perdu !");
			}
			else if(this.listeJ.get(i).jHor == this.p.abs && this.listeJ.get(i).jVer == 0   
					&& this.p.getEtat(this.listeJ.get(i).jHor-1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer+1)!=0){
				joueurElimine++;
				System.out.println(this.listeJ.get(i).jName + " a perdu !");
			}
			else if(this.listeJ.get(i).jHor == 0 && this.listeJ.get(i).jVer != 0 && this.listeJ.get(i).jVer != this.p.ord 
					&& this.p.getEtat(this.listeJ.get(i).jHor+1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer+1)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer-1)!=0){
				joueurElimine++;
				System.out.println(this.listeJ.get(i).jName + " a perdu !");
			}
			else if(this.listeJ.get(i).jHor == this.p.abs && this.listeJ.get(i).jVer != 0 && this.listeJ.get(i).jVer != this.p.ord   
					&& this.p.getEtat(this.listeJ.get(i).jHor-1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer+1)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer-1)!=0){
				joueurElimine++;
				System.out.println(this.listeJ.get(i).jName + " a perdu !");
			}
			else if(this.listeJ.get(i).jHor != 0 && this.listeJ.get(i).jHor != this.p.abs  && this.listeJ.get(i).jVer == 0   
					&& this.p.getEtat(this.listeJ.get(i).jHor+1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor-1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer+1)!=0){
				joueurElimine++;
				System.out.println(this.listeJ.get(i).jName + " a perdu !");
			}
			else if(     this.listeJ.get(i).jHor != 0 && this.listeJ.get(i).jVer != 0 && this.listeJ.get(i).jHor != this.p.abs && this.listeJ.get(i).jVer != this.p.ord   
					&& this.p.getEtat(this.listeJ.get(i).jHor+1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor-1, this.listeJ.get(i).jVer)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer+1)!=0
					&& this.p.getEtat(this.listeJ.get(i).jHor, this.listeJ.get(i).jVer-1)!=0){
				joueurElimine++;
				System.out.println(this.listeJ.get(i).jName + " a perdu !");
			}				
		}
		
		return joueurElimine;
	}
	
	public int griserCase()
	{
		return p.griserCase(lireEntier(), lireEntier());
	}

	public void setJoueur(int numJoueur, int abs, int ord)
	{
		listeJ.get(numJoueur).setJoueur(abs, ord);
		
	}

	public boolean deplacer(int numJoueur, int deplacement)
	{
		int[] nouvellePosition = this.p.deplacer(deplacement, this.getJoueur(numJoueur)[0], this.getJoueur(numJoueur)[1]);
		
		int etat = this.p.getEtat(nouvellePosition[0], nouvellePosition[1]);
		if(etat==-1 || etat==1 || etat==2)
			return false;
		else
		{
			this.setJoueur(numJoueur,nouvellePosition[0],nouvellePosition[1]);
			return true;
		}
	}
}