package fr.cnam.projet;

public interface Jeu {

	public void demarrer();
	public boolean getDemarree();
	public void setDemarree(boolean demarree);
	public AbstractPartie getPartie(int numeroPartie,
									String identJoueurCourant,
									String identAdversaire);
	public boolean selectionnerCase(int x,int y);
}
