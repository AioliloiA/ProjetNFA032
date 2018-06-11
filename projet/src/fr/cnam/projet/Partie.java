package fr.cnam.projet;

import java.util.*;

// Classe de definition d'une partie
//
public class Partie
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

  private int     nbPionRetourneJoueur1; // Othello
  private int     nbPionRetourneJoueur2; // Othello
  private int     nbPionPrisJoueur1; // Go
  private int     nbPionPrisJoueur2; // Go
  private int     nbCaseTerritoireJoueur1; // Go
  private int     nbCaseTerritoireJoueur2; // Go

  // Constructeur
  //
  public Partie(int numero,
                String identJoueur1,
                String identJoueur2,
                String date,
                String nomJeu,
                boolean partieTerminee,
                boolean joueur1Gagne,
                boolean joueur2Gagne,
                int nbPionRetourneJoueur1,
                int nbPionRetourneJoueur2,
                int nbPionPrisJoueur1,
                int nbPionPrisJoueur2,
                int nbCaseTerritoireJoueur1,
                int nbCaseTerritoireJoueur2)
  {
    this.numero=numero;
    this.identJoueur1=identJoueur1;
    this.identJoueur2=identJoueur2;
    this.date=date;
    this.nomJeu=nomJeu;
    this.partieTerminee=partieTerminee;
    this.joueur1Gagne=joueur1Gagne;
    this.joueur2Gagne=joueur2Gagne;
    this.nbPionRetourneJoueur1=nbPionRetourneJoueur1;
    this.nbPionRetourneJoueur2=nbPionRetourneJoueur2;
    this.nbPionPrisJoueur1=nbPionPrisJoueur1;
    this.nbPionPrisJoueur2=nbPionPrisJoueur2;
    this.nbCaseTerritoireJoueur1=nbCaseTerritoireJoueur1;
    this.nbCaseTerritoireJoueur2=nbCaseTerritoireJoueur2;
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
                         joueur2Gagne,
                         nbPionRetourneJoueur1,
                         nbPionRetourneJoueur2,
                         nbPionPrisJoueur1,
                         nbPionPrisJoueur2,
                         nbCaseTerritoireJoueur1,
                         nbCaseTerritoireJoueur2);


  }

  // Getteurs
  //
  public String getIdentJoueur1(){return identJoueur1;}
  public String getIdentJoueur2(){return identJoueur2;}
  public int getNumero(){return numero;}
}