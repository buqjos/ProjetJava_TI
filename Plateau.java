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
	public void setEtat(int hor, int ver, int etat)
	{
		// prevoir verif
		terrain[hor][ver] = etat;
	}
	
	public void setEtat(int[] joueur)
	{
		this.setEtat(joueur[0], joueur[1], joueur[2]);
	}
	
	// methode indiquant les cases occupées du plateau
	public String toString()
	{
		String message = "Je suis occupé en position :\n";
		
		for(int i = 0; i < this.abs; i++)
		{
			for (int j = 0; j < this.ord; j++)
			{
				if(terrain[i][j]!=0)
				{
					message += i+","+j+"\n";
				}
			}
		}
		
		return message;
	}

}
