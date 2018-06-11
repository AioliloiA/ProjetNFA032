package fr.cnam.projet;

import fr.cnam.ihm.*;
import java.awt.Point;
import java.util.*;
import fr.cnam.util.*;
import fr.cnam.projet.*;

/* Classe de definition du jeu d'othello
   Cette classe modelise les regles du jeu d'othello
*/
public class Othello implements Jeu
{
  // La taille de la grille d'un jeu d'hotello
  final private static int NB_LIGNE=8;
  final private static int NB_COLONNE=8;
  // Les couleurs des joueurs
  final private static int COULEUR_JOUEUR_1=1;
  final private static int COULEUR_JOUEUR_2=5;

  private PFJeu       pfjeu;         // La plate-forme de jeu
  private CanvasIHM   grille;        // La grille du jeu
  private int         couleurJoueur1;// La couleur du 1er joueur
  private int         couleurJoueur2;// La couleur du 2eme joueur
  private int         joueurCourant; // Le numero (1 ou 2) du joueur courant
  private boolean     demarree;      // etat de la partie : demarree ou non
  private boolean     terminee;      // partie terminee (pas interrompue)

  // Constructeur
  //
  public Othello(PFJeu pfjeu)
  {
    this.pfjeu=pfjeu;
    this.couleurJoueur1=COULEUR_JOUEUR_1; 
    this.couleurJoueur2=COULEUR_JOUEUR_2; 
    this.grille=null;
    this.joueurCourant=1;
    this.demarree=false;
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
    
    
    // Chaque joueur joue a tour de role
    //
    if (joueurCourant==1) 
      {
        // Calcul de la liste des pions pouvant etre retournees
        ArrayList<Point> lp = pionsARetourner(couleurJoueur1,couleurJoueur2,x,y);
        
        // S'il n'y a pas de pions a retourner alors le coup est invalide
        if (lp.size()==0)
          {
            pfjeu.getIhm().AR("Coup non valide");
          }
        else // Sinon
          {
            // le coup est valide: marquer le coup et retourner les pions adverses
            grille.setMarque(couleurJoueur1,x,y);
            retournerPions(lp,couleurJoueur1);

            // Si le joueur adverse ne peut plus jouer
            if (! peutJouer(couleurJoueur2,couleurJoueur1))
              {	
                // et si je ne peux pas jouer non plus alors la parie s'arrete
            	if (! peutJouer(couleurJoueur1,couleurJoueur2))
                  {
                    terminee = true;
                    return true;
                  }
            	else // sinon je ocntinue a jouer
                  pfjeu.getIhm().AR("Le joueur 2 ne peut pas jouer. \nAu joueur 1 de rejouer");
              }
            else // Sinon le joueur adverse peut jouer
              {
                joueurCourant=2;
                pfjeu.getIhm().AR("Au joueur 2 de jouer");
              }
          }
      }
    else    // C'est le meme algorithme que precedemment mais  avecc le joueur 2
      {
        ArrayList<Point> lp = pionsARetourner(couleurJoueur2,couleurJoueur1,x,y);
        if (lp.size()==0)
          {
            pfjeu.getIhm().AR("Coup non valide");
          }
        else
          {
            grille.setMarque(couleurJoueur2,x,y);
            retournerPions(lp,couleurJoueur2);
            if (! peutJouer(couleurJoueur1,couleurJoueur2))
              {	
            	if (! peutJouer(couleurJoueur2,couleurJoueur1))
                  {
                    return true;
                  }
            	else
                  pfjeu.getIhm().AR("Le joueur 1 ne peut pas jouer. \nAu joueur 2 de rejouer");
              }
            else 
              {
                joueurCourant=1;
                pfjeu.getIhm().AR("Au joueur 1 de jouer");
              }
          }
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
    grille = pfjeu.getIhm().creerGrille(NB_COLONNE,NB_LIGNE,40);

    // On initialise la grille avec la configuration de depart
    //
    grille.setMarque(couleurJoueur1,3,3);
    grille.setMarque(couleurJoueur1,4,4);
    grille.setMarque(couleurJoueur2,3,4);
    grille.setMarque(couleurJoueur2,4,3);
    
    // On demarre
    demarree=true;

    // Le premier joueur a jouer est le joueur 1
    joueurCourant = 1;  
  }

  // Methode qui cree et retourne la partie
  //
  public PartieOthello getPartie(int numeroPartie,
                          String identJoueurCourant,
                          String identAdversaire)
  {
    int nbPionsJoueur1;
    int nbPionsJoueur2;
    String date;
    boolean joueur1Gagne;
    boolean joueur2Gagne;
    
    nbPionsJoueur1 = nbPionsJoueur1();
    nbPionsJoueur2 = nbPionsJoueur2();
    date = DateString.dateCourante();
    if (! terminee)
      {
        joueur1Gagne = false;
        joueur2Gagne = false;
      }
    else
      {
        if ( nbPionsJoueur1==nbPionsJoueur2)
          {
            joueur1Gagne = true;
            joueur2Gagne = true;
          }
        else if(nbPionsJoueur1>nbPionsJoueur2)
          {
            joueur1Gagne = true;
            joueur2Gagne = false;
          }
        else
          {
            joueur1Gagne = false;
            joueur2Gagne = true;
          }
      }    
    PartieOthello p = new PartieOthello(numeroPartie,
                          identJoueurCourant,
                          identAdversaire,
                          date,
                          "othello",
                          terminee,
                          joueur1Gagne,
                          joueur2Gagne,
                          nbPionsJoueur1,
                          nbPionsJoueur2                         
                          );
    return p;
  }
  
  // Getteurs et Setteur de demaree
  //
  public boolean getDemarree(){return demarree;}
  public void setDemarree(boolean d) {demarree=d;}
  



  // =======================  PRIVATE ===================================
  
  // Methode qui retourne le nombre de pions de la couleur du joueur 1
  //
  private int nbPionsJoueur1()
  {
    int nb=0;
    for(int i=0;i<NB_COLONNE;i++)
      for(int j=0;j<NB_LIGNE;j++)
        if (grille.getMarque(i,j)==couleurJoueur1) nb++;
    return nb;
  }

  // Methode qui retourne le nombre de pions de la couleur du joueur 2
  //
  private int nbPionsJoueur2()
  {
    int nb=0;
    for(int i=0;i<NB_COLONNE;i++)
      for(int j=0;j<NB_LIGNE;j++)
        if (grille.getMarque(i,j)==couleurJoueur2) nb++;
    return nb;
  }
  
  // Methode privee qui determine si le joueur de couleurJoueur
  //  peut jouer ou non.
  //
  private boolean peutJouer(int couleurJoueur,int couleurJoueurAdverse)
  {
    // Pour toutes les cases vides de la grille on determine si 
    //   le joueur de couleurJoueur peut jouer
    //
    for(int i=0;i<NB_COLONNE;i++)
      for(int j=0;j<NB_LIGNE;j++)
        {
          if (grille.siCaseLibre(i,j))
            {
              // Si en jouant en i,j le joueur peut retourner des pions adverses alors il peut jouer
              ArrayList<Point> lp = pionsARetourner(couleurJoueur,couleurJoueurAdverse,i,j);
              if (lp.size()!=0)return true;
            }
        }
    return false;
  }

  // Methode qui retourne la liste des coordonnees des pions qu'un joueur peut
  //  retoruner en jouant en x,y
  //
  private ArrayList<Point> pionsARetourner(int couleurJoueur, int couleurAdverse,int x, int y)
  {
    // La liste est au depart vide
    ArrayList<Point> lpar = new ArrayList<Point>();

    // pour chacune des 8 directions (horizontales, verticales et diagonales)
    //

    pionsARetourner(-1,-1,couleurJoueur,couleurAdverse,x,y,lpar);
    pionsARetourner(-1,0,couleurJoueur,couleurAdverse,x,y,lpar);
    pionsARetourner(-1,1,couleurJoueur,couleurAdverse,x,y,lpar);
    pionsARetourner(0,-1,couleurJoueur,couleurAdverse,x,y,lpar);
    pionsARetourner(0,1,couleurJoueur,couleurAdverse,x,y,lpar);
    pionsARetourner(1,-1,couleurJoueur,couleurAdverse,x,y,lpar);
    pionsARetourner(1,0,couleurJoueur,couleurAdverse,x,y,lpar);
    pionsARetourner(1,1,couleurJoueur,couleurAdverse,x,y,lpar);

    return lpar;
  }

  // Methode qui ajoute a la lispe lp les coordonnees des pions qu'un joueur
  //  peut retourner en jouant en x,y dans la direction sensX,sensY
  //
  private void pionsARetourner(int sensX,int sensY,
                               int couleurJoueur, 
                               int couleurJoueurAdverse,
                               int x, int y,ArrayList<Point> lp)
  {
    // Dans la direction donnee, on va mettre dans une liste temporaire
    //  tous les pions de l'adversaire jusqu'a rencontrer un de ses pions,
    //  une case vide ou sortir de la grille
    //  Si on a recontre un de ses pions alors on puet ajouter a lp 
    //  la liste temporaire.
    //

    ArrayList<Point> tmp = new ArrayList<Point>();
    int i=x+sensX;
    int j=y+sensY;
    boolean fini = false;
    while (!fini)
      {
        // Si je sors de la grille alors j'ai fini et je n'ajoute rien
        if (i>=NB_COLONNE) fini=true;
        else if (j>=NB_LIGNE) fini=true;
        else if (i<0) fini=true;
        else if (j<0) fini=true;
        else 
          // Si je rencontre un pion adverse alors je l'ajoute a la iiste temporaire
          if (grille.getMarque(i,j)==couleurJoueurAdverse)
            {
              tmp.add(new Point(i,j));
              i=i+sensX;
              j=j+sensY;
            }
          else 
            // Si je rencontre un de mes pions alors j'ai fini et j'ajoute a la liste lp
            //  les elements de la liste temporaire
            if (grille.getMarque(i,j)==couleurJoueur)
              {
                fini=true;
                for(Point p:tmp) lp.add(p);
              }
        // Sinon j'ai rencontre une case vide don j'ai fini et je n'ajoute rien
            else fini=true;
      }
  }

  // Methode privee elementaire qui retourne les pions de l'adversaire
  //
  private void retournerPions(ArrayList<Point> lp,int couleurJoueur)
  {
    for(Point p:lp)
      {
        grille.setMarque(couleurJoueur,p.x,p.y);
        try{Thread.sleep(250);}catch(Exception ex){};
      }
  }


}