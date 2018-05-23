package fr.cnam.projet;

import fr.cnam.ihm.*;
import java.awt.Point;
import java.util.*;
import fr.cnam.util.*;
import fr.cnam.projet.*;

/* Classe de definition du jeu de Go
   La regle de selection d'une case est tres simple :
      chaque joueur joue a tour de role sur n'importe quelle case libre.
   Remarque : il existe un cas particulier pour lequel on ne peut pas jouer 
    en position de Ko que nous ne coderons pas pour simplifier le programme.

   Le decompte se fait en comptant les prisonniers et la surface des territoires
     de chaque joueur. Celui qui a le meilleur compte gagne.
   
 */
public class Go
{
	
  // La taille de la grille d'un jeu de morpion
  final private static int NB_LIGNE=19;
  final private static int NB_COLONNE=19;
  // Les couleurs des joueurs
  final private static int COULEUR_JOUEUR_1=1;
  final private static int COULEUR_JOUEUR_2=9;

  private PFJeu       pfjeu;         // La plate-forme de jeu
  private CanvasIHM   grille;        // La grille du jeu
  private int         couleurJoueur1;// La couleur du 1er joueur
  private int         couleurJoueur2;// La couleur du 2eme joueur
  private int         joueurCourant; // Le numero (1 ou 2) du joueur courant
  private boolean     demarree;      // etat de la partie : demarree ou non
  private boolean     terminee;      // partie terminee (pas interrompue)
  private int         nbPionsPrisJoueur1; // nbre pions pris par le joueur 1
  private int         nbPionsPrisJoueur2; // nbre de pions pris par le joueur 2
  
  // Constructeur
  public Go(PFJeu pfjeu)
  {
    this.pfjeu=pfjeu;
    this.couleurJoueur1=COULEUR_JOUEUR_1;
    this.couleurJoueur2=COULEUR_JOUEUR_2;
    this.grille = null;
    this.joueurCourant=1;
    this.demarree=false;
    this.terminee=false;
    this.nbPionsPrisJoueur1=0;
    this.nbPionsPrisJoueur2=0;
  }

  // Traitement du coup d'un joueur
  //  return true si la partie est terminee
  //
  //  Cette methode prend en entree les coordonnees de la case
  //   selectionnee par le joueur
  //
  public boolean selectionnerCase(int x,int y)
  {
    if (! demarree)
      {
        pfjeu.getIhm().AR("La partie n'est pas demarree.");
        return false;
      }
	  
    // Un joueur ne peut pas jouer sur une case vide
    //
    if (! grille.siCaseLibre(x,y)){
      pfjeu.getIhm().AR("La case n'est pas libre");
      return false;
    }
    
    // Chaque joueur joue a tour de role
    //
    if (joueurCourant==1) 
      {
        // Marquer le coup
        grille.setMarque(couleurJoueur1,x,y);
        // Effacer  les pions du joueuer adverse pris (etouffes)
        // A FAIRE

        // Effacer les pions du joueur courant pris (etouffes)
        // A FAIRE

        joueurCourant=2;
        pfjeu.getIhm().AR("Au joueur 2 de jouer");
      }
    else    // C'est le meme algorithme que precedemment mais  avecc le joueur 2
      {
        grille.setMarque(couleurJoueur2,x,y);

        // Effacer  les pions du joueuer adverse pris (etouffes)
        // A FAIRE

        // Effacer les pions du joueur courant pris (etouffes)
        // A FAIRE

        joueurCourant=1;
        pfjeu.getIhm().AR("Au joueur 1 de jouer");
      }

    return false;  // La partie continue
  }

  // Demarrage d'une partie d'othello
  //   et creation de la grille du jeu
  //
  public void demarrer()
  {
    pfjeu.getIhm().AR("Initialisation du jeu d'Othello:");

    // Creation de la grille de jeu
    //
    grille = pfjeu.getIhm().creerGrille(NB_COLONNE,NB_LIGNE,30);

    // On demarre
    demarree=true;

    // Le premier joueur a jouer est le joueur 1
    joueurCourant = 1;  
  }

  // Methode qui cree la partie du jeu d'othello
  //
  public Partie getPartie(int numeroPartie,
                          String identJoueurCourant,
                          String identAdversaire)
  {
    String date;
    boolean joueur1Gagne;
    boolean joueur2Gagne;

    date = DateString.dateCourante();
    joueur1Gagne = false;
    joueur2Gagne = false;

    if (nbPionsPrisJoueur1>nbPionsPrisJoueur2)
      joueur1Gagne = true;
    else if (nbPionsPrisJoueur2>nbPionsPrisJoueur1)
      joueur2Gagne = true;
    else {joueur1Gagne=true;joueur2Gagne=true;}//egalite
      
    // Creation de la partie
    Partie p = new Partie(numeroPartie,
                          identJoueurCourant,
                          identAdversaire,
                          date,
                           "go",
                          true,
                          joueur1Gagne,
                          joueur2Gagne,
                          0,0,
                          nbPionsPrisJoueur1,
                          nbPionsPrisJoueur2,
                          0,0);
    return p;
  }
  
  // Getteurs et Setteur de demaree
  //
  public boolean getDemarree(){return demarree;}
  public void setDemarree(boolean d) {demarree=d;}
  



  // =======================  PRIVATE ===================================
  
}
