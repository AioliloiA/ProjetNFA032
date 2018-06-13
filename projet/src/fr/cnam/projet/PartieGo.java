package fr.cnam.projet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.*;

// Classe de definition d'une partie
//
public class PartieGo extends AbstractPartie
{


  private int     nbPionPrisJoueur1; // Go
  private int     nbPionPrisJoueur2; // Go
  private int     nbCaseTerritoireJoueur1; // Go
  private int     nbCaseTerritoireJoueur2; // Go

  // Constructeur
  //
	public PartieGo(int numero, 
			String identJoueur1, 
			String identJoueur2, 
			String date, 
			String nomJeu,
			boolean partieTerminee, 
			boolean joueur1Gagne, 
			boolean joueur2Gagne, 
			int nbPionPrisJoueur1,
			int nbPionPrisJoueur2, 
			int nbCaseTerritoireJoueur1, 
			int nbCaseTerritoireJoueur2)
  
  {	
	super(numero, identJoueur1, identJoueur2, date, nomJeu, partieTerminee, joueur1Gagne, joueur2Gagne);
  	this.nbPionPrisJoueur1=nbPionPrisJoueur1;
    this.nbPionPrisJoueur2=nbPionPrisJoueur2;
    this.nbCaseTerritoireJoueur1=nbCaseTerritoireJoueur1;
    this.nbCaseTerritoireJoueur2=nbCaseTerritoireJoueur2;
  }

  // Partie en chaine
  //
  
  public PartieGo() {
		// TODO Auto-generated constructor stub
	}

public String toString()
  {
    return String.format("%3d %-15s %-15s %-19s %10s %5s %5s %5s %2d %2d %2d %2d",
    		getNumero(),
            getIdentJoueur1(),
            getIdentJoueur2(),
            getDate(),
            getNomJeu(),
            isPartieTerminee(),
            isJoueur1Gagne(),
            isJoueur2Gagne(),
                         nbPionPrisJoueur1,
                         nbPionPrisJoueur2,
                         nbCaseTerritoireJoueur1,
                         nbCaseTerritoireJoueur2);


  }
  
  public void write(DataOutputStream dos) throws Exception {

	  super.write(dos);
	  dos.writeInt(nbPionPrisJoueur1);
	  dos.writeInt(nbPionPrisJoueur2);
	  dos.writeInt(nbCaseTerritoireJoueur1);
	  dos.writeInt(nbCaseTerritoireJoueur2);
	  
	  
  }
  
  public void read(DataInputStream dis) throws Exception {

	  super.read(dis);
	  nbPionPrisJoueur1= dis.readInt();
	  nbPionPrisJoueur2= dis.readInt();
	  nbCaseTerritoireJoueur1= dis.readInt();
	  nbCaseTerritoireJoueur1= dis.readInt();
	  
  }

  // Getteurs
  //

}