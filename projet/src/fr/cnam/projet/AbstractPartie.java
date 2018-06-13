package fr.cnam.projet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

// Classe de definition d'une partie
//
public abstract class AbstractPartie
{
  private int     numero;
  private String  identJoueur1;
  private String  identJoueur2;
  private String  date;
  private String  nomJeu;
  private boolean partieTerminee;
  // si les deux à 1 alors partie nulle si 0 indetermine
  private boolean joueur1Gagne;  
  private boolean joueur2Gagne;  

 
  // Constructeur
  //
  
  
  public AbstractPartie(int numero,
                String identJoueur1,
                String identJoueur2,
                String date,
                String nomJeu,
                boolean partieTerminee,
                boolean joueur1Gagne,
                boolean joueur2Gagne)
  {
    this.numero=numero;
    this.identJoueur1=identJoueur1;
    this.identJoueur2=identJoueur2;
    this.date=date;
    this.nomJeu=nomJeu;
    this.partieTerminee=partieTerminee;
    this.joueur1Gagne=joueur1Gagne;
    this.joueur2Gagne=joueur2Gagne;

  }

  


public AbstractPartie() {
	// TODO Auto-generated constructor stub
}




// Partie en chaine
  //
  public String toString()
  {
    return String.format("%3d %-15s %-15s %-19s %10s %5s %5s %5s %2d %2d %2d %2d %2d %2d  ",
                         numero,
                         identJoueur1,
                         identJoueur2,
                         date,
                         nomJeu,
                         partieTerminee,
                         joueur1Gagne,
                         joueur2Gagne);
                         

  }

  public void write(DataOutputStream dos) throws Exception {

	  dos.writeInt(numero);
	  dos.writeUTF(identJoueur1);
	  dos.writeUTF(identJoueur2);
	  dos.writeUTF(date);
	  dos.writeUTF(nomJeu);
	  dos.writeBoolean(partieTerminee);
	  dos.writeBoolean(joueur1Gagne);
	  dos.writeBoolean(joueur2Gagne);
	  
  }




	public void read(DataInputStream dis) throws Exception {
		
		numero = dis.readInt();
		identJoueur1 = dis.readUTF();
		identJoueur2 = dis.readUTF();
		date = dis.readUTF();
		nomJeu = dis.readUTF();
		partieTerminee = dis.readBoolean();
		joueur1Gagne = dis.readBoolean();
		joueur2Gagne = dis.readBoolean();
		
		
	}
  
  // Getteurs
  //
  public String getIdentJoueur1(){return identJoueur1;}
  public String getIdentJoueur2(){return identJoueur2;}
  public int getNumero(){return numero;}
  public String getDate() {return date;}
  public String getNomJeu() {return nomJeu;}
  public boolean isPartieTerminee() {return partieTerminee;}
  public boolean isJoueur1Gagne() {return joueur1Gagne;}
  public boolean isJoueur2Gagne() {return joueur2Gagne;}





  
}