package TourInfernale;
import java.awt.event.KeyEvent;

/**
 * 
 * @author ulysse
 * @name PLateau
 * @descr gestion du plateau
 * 
 */
public class Plateau
{
	private int[][] terrain;
	public int abs, ord;
	
	/* Constructeur a deux arguments de la classe
	 * Il prend en paramètres le nombre de cases horizontales et verticales
	 */
	public Plateau (int hor, int ver)
	{
		this.abs = hor;
		this.ord = ver;
		terrain = new int[this.abs][this.ord];
		
		for(int i = 0; i < this.abs; i++)
		{
			for (int j = 0; j < this.ord; j++)
			{
				terrain[i][j] = 0;
			}
		}
		
		//gestion du bonus
		int bonus = (int)(hor*ver/10);
		
		for(int i=0; i<bonus; i++)
		{
			int aleaHor = (int)(Math.random()*this.abs);
			int aleaVer = (int)(Math.random()*this.ord);
			
			// pas de gestion de conflits puisque le terrain est vide
			// un bonus peut �tre remplac� par un bonus
			terrain[aleaHor][aleaVer] = 3;
		}
	}
	
	// surdefinition du constructeur
	public Plateau(int[] pVal)
	{
		this(pVal[0],pVal[1]);
	}
	
	
	public int getEtat(int hor, int ver)
	{
		return terrain[hor][ver];
	}
	
	/* methode permettant de fixer un etat à une case du plateau
	 * 0 : case libre
	 * 1 : case grisée
	 * 2 : case joueur présent
	 * 3 : case bonus
	 */
	public boolean setEtat(int hor, int ver, int etat)
	{
		if(etat>=0 || etat<=3)
		{
			terrain[hor][ver] = etat;
			
			return true;
		}
		
		return false;
	}
	
	public boolean setEtat(int[] joueur)
	{
		return this.setEtat(joueur[0], joueur[1], joueur[2]);
	}
	
	// methode indiquant les cases du plateau
		public String toString()
		{
			String message = "";
			message += "Le plateau est occupé :\n";
			message += "    -par une case grisée en : \n";
			
			for(int i = 0; i < this.abs; i++)
			{
				for (int j = 0; j < this.ord; j++)
				{
					if(terrain[i][j] == 1)
					{
						message += "(" + i+","+j+ ") ";
					}
				}
			}
			
			message += "    -par un joueur en :"; 
			
			for(int i = 0; i < this.abs; i++)
			{
				for (int j = 0; j < this.ord; j++)
				{
					if(terrain[i][j] == 2)
					{ 
						message += "(" + i+","+j+ ") ";
					}			
				}
			}
			message += "\n    -par une case bonus en :\n"; 
			for(int i = 0; i < this.abs; i++)
			{
				for (int j = 0; j < this.ord; j++)
				{
					if(terrain[i][j] == 3)
					{ 
						message += "(" + i+","+j+ ") ";
					}
				}
			}
			message += "\n  ";
			
			// affichage du tableau
			for(int i=0; i<this.abs; i++)
				message += i;
			message += "\n";
			for(int j = this.ord-1; j >= 0 ; j=j-1)
			{
				message += j+" ";
				for(int i = 0; i < this.abs; i++)
				{
					message += terrain[i][j];
				}
				message += "\n";
			}
			
			return message;
		}
		
		public int[] deplacer(int M, int jHor, int jVer)
		{
			/* methode permettant de déplacer un joueur d'une case du plateau
			 * 8 : avance d'une case
			 * 2 : recule d'une case
			 * 4 : se décale d'une case à gauche
			 * 6 : se décale d'une case à droite
			 * trackhash
			 */
			
			int ancienneAbs = jHor;
			int ancienneOrd = jVer;
			int[] nouvellePosition = new int[2];
			
			switch(M)
			{
				case 8: jVer++;
					break;
				case 2: jVer--;
					break;
				case 4: jHor--;
					break;
				case 6: jHor++;
					break;
			}
			
			if(jVer >= 0 && jHor >= 0 && jVer < ord && jHor < abs){
				terrain[ancienneAbs][ancienneOrd]= 0;
				terrain[jHor][jVer]= 2;
				nouvellePosition[0] = jHor;
				nouvellePosition[1] = jVer;
				System.out.println("J'étais en postion : (" + ancienneAbs + "," + ancienneOrd + ") et je suis désormais en : (" + jHor + "," + jVer + ").");
			}
			else{
				System.out.println("Mouvement impossible : sortie de plateau");
				nouvellePosition[0] = -1;
				nouvellePosition[1] = -1;
			}
			
			return nouvellePosition;
			
		}
		
	// methode permettant de griser une case du plateau
	public int griserCase(int abs, int ord)
	{
		if(abs > this.abs || ord > this.ord || abs < 0 || ord < 0){
			System.out.println("Veuillez griser une case comprise dans un plateau de taille["+this.abs+","+this.ord+"].");
		}
		if (this.terrain[abs][ord]==0||this.terrain[abs][ord]==3)
		{
			this.terrain[abs][ord] = 1;
			
			// si ok
			return 0;
		}
		else return this.terrain[abs][ord];
	}
}