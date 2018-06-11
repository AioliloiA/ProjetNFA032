package fr.cnam.projet;

import java.util.*;

// Classe de definition d'une partie
//
public class PartieMorpion extends AbstractPartie
{



  // Constructeur
  //
  public PartieMorpion(int numero,
                String identJoueur1,
                String identJoueur2,
                String date,
                String nomJeu,
                boolean partieTerminee,
                boolean joueur1Gagne,
                boolean joueur2Gagne)
  {
	  super(numero, identJoueur1, identJoueur2, date, nomJeu, partieTerminee, joueur1Gagne, joueur2Gagne);
	  
  }

  // Partie en chaine
  //
  
  public String toString()
  {
    return String.format("%3d %-15s %-15s %-19s %10s %5s %5s %5s ",
                         getNumero(),
                         getIdentJoueur1(),
                         getIdentJoueur2(),
                         getDate(),
                         getNomJeu(),
                         isPartieTerminee(),
                         isJoueur1Gagne(),
                         isJoueur2Gagne());

  }

  // Getteurs
  //

}