package fr.cnam.projet;

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