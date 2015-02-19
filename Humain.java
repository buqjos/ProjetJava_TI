
public class Humain extends Joueur
{
	public Humain(int abs, int ord)
	{
		super(abs, ord);
	}

	public String toString()
	{		
		return "Je suis un joueur "+this.getClass()+" en position : "+super.getJoueur()[0]+","+super.getJoueur()[1]+"\n";
	}
}
