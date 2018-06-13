package fr.cnam.projet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.*;

// Classe de definition d'une partie
//
public class PartieOthello extends AbstractPartie
{


  private int     nbPionRetourneJoueur1; // Othello
  private int     nbPionRetourneJoueur2; // Othello


  // Constructeur
  //
  public PartieOthello(int numero,
                String identJoueur1,
                String identJoueur2,
                String date,
                String nomJeu,
                boolean partieTerminee,
                boolean joueur1Gagne,
                boolean joueur2Gagne,
                int nbPionRetourneJoueur1,
                int nbPionRetourneJoueur2
)
  {
	super(numero, identJoueur1, identJoueur2, date, nomJeu, partieTerminee, joueur1Gagne, joueur2Gagne);
	this.nbPionRetourneJoueur1=nbPionRetourneJoueur1;
    this.nbPionRetourneJoueur2=nbPionRetourneJoueur2;

  }

  // Partie en chaine
  //
  
  public PartieOthello() {
	// TODO Auto-generated constructor stub
}

public String toString()
  {
    return String.format("%3d %-15s %-15s %-19s %10s %5s %5s %5s %2d %2d  ",
    		getNumero(),
            getIdentJoueur1(),
            getIdentJoueur2(),
            getDate(),
            getNomJeu(),
            isPartieTerminee(),
            isJoueur1Gagne(),
            isJoueur2Gagne(),
                         nbPionRetourneJoueur1,
                         nbPionRetourneJoueur2);


  }
  
  public void write(DataOutputStream dos) throws Exception {
	  
	  
	  
	  super.write(dos);
	  dos.writeInt(nbPionRetourneJoueur1);
	  dos.writeInt(nbPionRetourneJoueur2);
	  
	  
  }
  
  public void read(DataInputStream dis) throws Exception {

	  super.read(dis);
	  nbPionRetourneJoueur1 = dis.readInt();
	  nbPionRetourneJoueur2 = dis.readInt();
	  System.out.println("Création partie Othello :"+this.toString());
  }

  // Getteurs
  //

}