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
	private int abs, ord;
	
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
		
//		// gestion du bonus
//		int bonus = (int)(hor*ver/10);
//		
//		for(int i=0; i<bonus; i++)
//		{
//			int aleaHor = (int)(Math.random()*this.abs);
//			int aleaVer = (int)(Math.random()*this.ord);
//			
//			// pas de gestion de conflits puisque le terrain est vide
//			// un bonus peut être remplacé par un bonus
//			terrain[aleaHor][aleaVer] = 3;
//		}
	}
	
	// surdefinition du constructeur
	public Plateau(int[] pVal)
	{
		this(pVal[0],pVal[1]);
	}
	
	// constructeur par défaut de la classe
	public Plateau()
	{
		this(10,10);
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
			System.out.println("Le plateau est occupé :");
			System.out.print("    -par une case grisée en : "); 
			for(int i = 0; i < this.abs; i++)
			{
				for (int j = 0; j < this.ord; j++)
				{
					if(terrain[i][j] == 1)
					{
						System.out.print("(" + i+","+j+ ") ");
					}
				}
			}
			System.out.println("");
			System.out.print("    -par un joueur en :"); 
			for(int i = 0; i < this.abs; i++)
			{
				for (int j = 0; j < this.ord; j++)
				{
					if(terrain[i][j] == 2)
					{ 
						System.out.print("(" + i+","+j+ ") ");
					}			
				}
			}
			System.out.println("");
			System.out.print("    -par une case bonus en :"); 
			for(int i = 0; i < this.abs; i++)
			{
				for (int j = 0; j < this.ord; j++)
				{
					if(terrain[i][j] == 3)
					{ 
						System.out.print("(" + i+","+j+ ") ");
					}
				}
			}
			System.out.println("");
			return message;
		}
	
	// methode permettant de griser une case du plateau
	public int griserCase(int abs, int ord)
	{
		if(abs > this.abs || ord > this.ord){
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
