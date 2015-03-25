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

	public void deplacer(int M)
	{
		/* methode permettant de déplacer un joueur d'une case du plateau
		 * 8 : avance d'une case
		 * 2 : recule d'une case
		 * 4 : se décale d'une case à gauche
		 * 6 : se décale d'une case à droite
		 * trackhash
		 */
		
		int ancienneAbs = this.jHor;
		int ancienneOrd = this.jVer;
		
		switch(M)
		{
			case 8: this.jVer++;
				break;
			case 2: this.jVer--;
				break;
			case 4: this.jHor--;
				break;
			case 6: this.jHor++;
				break;
		}

		
		if(this.jVer < 10 && this.jHor < 10){
			System.out.println("J'étais en postion : (" + ancienneAbs + "," + ancienneOrd + ") et je suis désormais en : (" + this.jHor + "," + this.jVer + ").");
		}
		else{
			System.out.println("Mouvement impossible : sortie de plateau");
		}
		
	}
}