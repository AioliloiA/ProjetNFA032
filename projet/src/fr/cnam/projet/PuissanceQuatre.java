package fr.cnam.projet;

import fr.cnam.ihm.*;
import java.awt.Point;
import java.util.*;
import fr.cnam.util.*;
import fr.cnam.projet.*;

/* Classe de definition du jeu de Morpion
    La regle de selection d'une case est tres simple :
      chaque joueur joue a tour de role sur n'importe quelle case libre.

    Le premier qui arrive à aligner 5 lignes horizontale, verticale ou 
      diagonale, gagne la partie qui sont rayees au fur et a mesure.

    On ne peut pas re-utiliser les lignes rayees.
 */
public class PuissanceQuatre implements Jeu
{
  // La taille de la grille d'un jeu de morpion
  final private static int NB_LIGNE=6;
  final private static int NB_COLONNE=7;
  // Les couleurs des joueurs
  final private static int COULEUR_JOUEUR_1=1;
  final private static int COULEUR_JOUEUR_2=9;
  // Couleur des pions rayes des joueurs
  final private static int COULEUR_RAYE_JOUEUR_1=4;
  final private static int COULEUR_RAYE_JOUEUR_2=7;
  // Nombre max de ligne rayees qu'il faut atteindre
  final private static int MAX_T = 1;

  private PFJeu       pfjeu;         // La plate-forme de jeu
  private CanvasIHM   grille;        // La grille du jeu
  private int         couleurJoueur1;// La couleur du 1er joueur
  private int         couleurJoueur2;// La couleur du 2eme joueur
  private int         couleurRayeJoueur1;// La couleur d'un pion raye du joueur 1
  private int         couleurRayeJoueur2;// La couleur d'un pion raye du joueur 2
  private int         joueurCourant; // Le numero (1 ou 2) du joueur courant
  private boolean     demarree;      // etat de la partie : demarree ou non
  private int         nbLignesRayesJoueur1; // nbre lignes rayes du joueur 1
  private int         nbLignesRayesJoueur2; // nbre lignes rayes du joueur 2
  private boolean     terminee;      // partie terminee (pas interrompue)
  
  // Constructeur
  //
  public PuissanceQuatre(PFJeu pfjeu)
  {
    this.pfjeu=pfjeu;
    this.couleurJoueur1=COULEUR_JOUEUR_1;
    this.couleurJoueur2=COULEUR_JOUEUR_2;
    this.couleurRayeJoueur1=COULEUR_RAYE_JOUEUR_1;
    this.couleurRayeJoueur2=COULEUR_RAYE_JOUEUR_2;
    this.grille = null;
    this.joueurCourant=1;
    this.demarree=false;
    this.nbLignesRayesJoueur1=0;
    this.nbLignesRayesJoueur2=0;
    this.terminee=false;
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
    
    // Un joueur ne peut pas jouer sur une case en l'air
    
    if (y != 5) {
    	if (grille.siCaseLibre(x, y+1)  ) {
        	pfjeu.getIhm().AR("La case choisie est en l'air");
        	return false;
        }
    }
    
    
    // Chaque joueur joue a tour de role
    //
    if (joueurCourant==1) 
      {
        // marquer le coup et rayer les pions
        grille.setMarque(couleurJoueur1,x,y);
        rayerPions(couleurJoueur1,x,y);
        // Si le nbre de ligne rayes du joueur est atteint alors la partie est terminee
        if (nbLignesRayesJoueur1==MAX_T) 
          {
            terminee=true;
            return true;
          }
        joueurCourant=2;
        pfjeu.getIhm().AR("Au joueur 2 de jouer");
      }
    else    // C'est le meme algorithme que precedemment mais  avecc le joueur 2
      {
        grille.setMarque(couleurJoueur2,x,y);
        rayerPions(couleurJoueur2,x,y);
        if (nbLignesRayesJoueur2==MAX_T) 
          {
            terminee=true;
            return true;
          }
        joueurCourant=1;
        pfjeu.getIhm().AR("Au joueur 1 de jouer");
      }

    return false;  // La partie continue
  }

  // Demarrage d'une partie de morpion
  //   et creation de la grille du jeu
  //
  public void demarrer()
  {
    pfjeu.getIhm().AR("Initialisation du jeu Puissance4");

    // Creation de la grille de jeu
    //
    grille = pfjeu.getIhm().creerGrille(NB_COLONNE,NB_LIGNE,25);

    // On demarre
    demarree=true;

    // Le premier joueur a jouer est le joueur 1
    joueurCourant = 1;  

    nbLignesRayesJoueur1=0;
    nbLignesRayesJoueur2=0;
  }

  // Methode qui cree les parties du jeu d'othello
  //
  public PartiePuissanceQuatre getPartie(int numeroPartie,
                          String identJoueurCourant,
                          String identAdversaire)
  {
    String date;
    boolean joueur1Gagne;
    boolean joueur2Gagne;

    date = DateString.dateCourante();
    joueur1Gagne = false;
    joueur2Gagne = false;
    if (terminee)
      {
        if (nbLignesRayesJoueur1==MAX_T) joueur1Gagne=true;
        if (nbLignesRayesJoueur2==MAX_T) joueur2Gagne=true;
      }

    PartiePuissanceQuatre p = new PartiePuissanceQuatre(numeroPartie,
                          identJoueurCourant,
                          identAdversaire,
                          date,
                          "puissance4",
                          terminee,
                          joueur1Gagne,
                          joueur2Gagne);                          
    return p;
  }
  
  // Getteurs et Setteur de demaree
  //
  public boolean getDemarree(){return demarree;}
  public void setDemarree(boolean d) {demarree=d;}
  



  // =======================  PRIVATE ===================================

  // Methode qui raye les pions si ligne > 5
  //
  private void rayerPions(int couleurJoueur,int x, int y)
  {

    // pour chacune des 4 directions (horizontales, verticales et diagonales)
    //
    pionsARayerLigne(-1,-1,couleurJoueur,x,y);
    pionsARayerLigne(-1,0,couleurJoueur,x,y);
    pionsARayerLigne(-1,1,couleurJoueur,x,y);
    pionsARayerLigne(0,-1,couleurJoueur,x,y);
  }

  // Methode qui raye les pions d'une ligne si > 4
  //
  private void pionsARayerLigne(int sensX,int sensY,
                                int couleurJoueur, 
                                int x, int y)
  {
    ArrayList<Point> tmp = new ArrayList<Point>();
    int i=x;
    int j=y;
    boolean fini = false;
        
    // On cherche le debut de la ligne
    while (!fini)
      {
        // Si je sors de la grille alors j'ai fini
        if (i>=NB_COLONNE) fini=true;
        else if (j>=NB_LIGNE) fini=true;
        else if (i<0) fini=true;
        else if (j<0) fini=true;
        else 
          // Si je rencontre un pion du joueur on continue
          if (grille.getMarque(i,j)==couleurJoueur)
          {
            i=i+sensX;
            j=j+sensY;
          }
          else fini=true;
      }
    // On parcours la ligne dans le sens oppose
    sensX=sensX*(-1);
    sensY=sensY*(-1);
    i=i+sensX;
    j=j+sensY;
    fini = false;
    while (!fini)
      {
        // Si je sors de la grille alors j'ai fini 
        if (i>=NB_COLONNE) fini=true;
        else if (j>=NB_LIGNE) fini=true;
        else if (i<0) fini=true;
        else if (j<0) fini=true;
        else 
          // Si je rencontre un pion du joueur on continue et on ajoute
          if (grille.getMarque(i,j)==couleurJoueur)
            {
              tmp.add(new Point(i,j));
              i=i+sensX;
              j=j+sensY;
            }
          else fini=true;
      }
    // Si la ligne trouvee est de longueur >= 4 alors
    //  on raye la ligne et on finit le jeu
    //
    if (tmp.size()>=4)
      {
        for(Point p:tmp)
          {
            if (couleurJoueur==COULEUR_JOUEUR_1) 
              grille.setMarque(couleurRayeJoueur1,p.x,p.y);
            else
              grille.setMarque(couleurRayeJoueur2,p.x,p.y);
            try{Thread.sleep(250);}catch(Exception ex){};
          }
        if (couleurJoueur==COULEUR_JOUEUR_1) nbLignesRayesJoueur1++;
        if (couleurJoueur==COULEUR_JOUEUR_2) nbLignesRayesJoueur2++;
      }
  }


}