package fr.cnam.projet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import fr.cnam.ihm.*;

// Classe de definition de l'IHM principale du projet
// Cette ihm gere la plate-forme de jeu.
//  L'heritage de AdaptaterControlesCanvasIHM permet de gerer le clic dans une case
//    de la grille de jeu
//  L'interface FormulaireInt permet de gerer les actions dans les objets d'ihm
//
public class IHMPFJeu extends AdaptaterControlesCanvasIHM implements FormulaireInt
                                                                     // 
{
  private PFJeu pfjeu;    // La plate forme de jeu
  CanvasIHM grille;       // La grille de jeu
  Formulaire form;        // Le formulaire de l'ihm principale
  public final static String[] jeux = {"othello","morpion","go"}; // Liste des jeuw
  // Constructeur
  //
  public IHMPFJeu(PFJeu pfjeu)
  {
    // L'applicatif de l'IHM
    //
    this.pfjeu = pfjeu;
    pfjeu.setIhm(this);
    
    // Creation du formulaire
    //
    this.form = new Formulaire("Plate-forme de jeu",this,1500,780);
        
    //  Creation des elements de l'IHM
    //
    form.addButton("AFF_JOUEURS","Afficher tous les joueurs");
    form.addButton("AFF_PARTIES","Afficher toutes les parties");
    form.addText("IDENT","Ident",true,"localhost");
    form.addText("MDP","Mot de passe",true,"mdp");
    form.addButton("INSCRIRE","Inscrire");
    form.addButton("IDENTIFIER","Identifier");
    form.addButton("PARTIES_JOUEUR","Parties joueur courant");
    form.addListScroll("ADVERSAIRES","Adversaires",true,null,200,100);
    form.addButton("CHOISIR_ADVERSAIRE","Valider adversaire");
    form.setPosition(300,0);
    form.addButton("DEMARRER","Demarrer");
    form.addButton("ARRETER","Arreter");
    form.addLabel("");
    form.addText("JOUEUR","Joueur",false,"");
    form.addText("ADVERSAIRE","Adversaire",false,"");
    form.addButton("TESTER","Tester");
    // Création des champs qui permettent de d'importer les fichiers parties et joueurs
    form.addText("SAISIR_PARTIES", "Fichier des parties", true, "Parties.txt");
    form.addText("SAISIR_JOUEURS", "Fichier des joueurs", true, "Joueurs.txt");
    form.addButton("IMPORT","Importer");
    form.addButton("SAUVEGARDER","Sauvegarder");
    form.addButton("CHARGER","Charger");
    
    form.addListeChoix("LISTE_JEUX", "Choisissez un jeu",jeux, true, jeux[2]);
    form.setPosition(10,500);
    form.addZoneText("RESULTATS","Resultats",
                     true,
                     "",
                     500,250);
    
    

    grille = null;  // La grille est cree par le jeu (ex: Othello)

    // Affichage du formulaire
    //
    form.afficher();
    
  }


  // Methode appellee quand on clique dans un objet d'ihm
  //
  public void submit(Formulaire form,String nomSubmit)
  {

    // Affichage de tous les produits du stock
    //
    if (nomSubmit.equals("AFF_JOUEURS"))
      {
        pfjeu.listerTousJoueurs();
      }

    // Affichage de toutes les commandes
    //
    if (nomSubmit.equals("AFF_PARTIES"))
      {
        pfjeu.listerToutesParties();
      }

    // Inscription d'un nouveau joueur
    //
    if (nomSubmit.equals("INSCRIRE"))
      {
        String ident = form.getValeurChamp("IDENT");
        String mdp   = form.getValeurChamp("MDP");
        pfjeu.inscrire(ident,mdp);

        // On met a jour la liste des adversaires de l'IHM
        //  puisqu'un nouveau joueur c'est inscrit
        ArrayList<Joueur> joueurs = pfjeu.getJoueurs();
        String[] tab = new String[joueurs.size()];
        for(int i=0;i<tab.length;i++) tab[i]=joueurs.get(i).getIdent();
        form.setListData("ADVERSAIRES",tab);
      }

    // Identifier un joueur
    //
    if (nomSubmit.equals("IDENTIFIER"))
      {
        String ident = form.getValeurChamp("IDENT");
        String mdp   = form.getValeurChamp("MDP");
        pfjeu.identifier(ident,mdp);
      }

    // Afficher les parties du joueur
    //
    if (nomSubmit.equals("PARTIES_JOUEUR"))
      {
        pfjeu.partiesJoueur();
      }

    // Choisir un adversaire
    //
    if (nomSubmit.equals("CHOISIR_ADVERSAIRE"))
      {
        String adversaire = form.getValeurChamp("ADVERSAIRES");
        pfjeu.choisirAdversaire(adversaire);
      }

    // Demarrer la partie
    //
    if (nomSubmit.equals("DEMARRER"))
      {
    	String jeuChoisi = form.getValeurChamp("LISTE_JEUX");
        pfjeu.demarrer(jeuChoisi);
      }

    // Arreter la partie
    //
    if (nomSubmit.equals("ARRETER"))
      {
        pfjeu.arreter();
      }

    // Pour tester la communication socket
    if (nomSubmit.equals("TESTER"))
      {
        pfjeu.tester();
      }
    
    // Pour importer les données du fichire Joueurs.txt
    if (nomSubmit.equals("IMPORT"))
    {
    	
        
        // Initialisation de l'applicatif
        //  On affiche le resultat de l'initialisation
        //
        pfjeu.initialiser();

        // On initialise la liste des joueurs
        //
        ArrayList<Joueur> joueurs = pfjeu.getJoueurs();
        String[] tab = new String[joueurs.size()];
        for(int i=0;i<tab.length;i++) tab[i]=joueurs.get(i).getIdent();
        form.setListData("ADVERSAIRES",tab);
        
        
        
    }
    
    if (nomSubmit.equals("SAUVEGARDER"))
    {
    	AR("bouton save cliqué IHM");
    	System.out.println("methode pfjeu save");
      try {
    	
		pfjeu.sauvegarder();
	} catch (Exception e) {
		System.out.println("Je n'ais pas pu sauvegarder");
		e.printStackTrace();
	}
    }
    
    if (nomSubmit.equals("CHARGER"))
    {
    	AR("bouton cherger cliqué IHM");
      try {
		pfjeu.charger();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    // Pour choisir un jeu dans la liste de jeux
  }

  // Methode appellee quand on clique dans une case de la grille
  //
  public void pointerCaseGrille(int xCase,int yCase,CanvasIHM ihm)
  {
    // Selection d'une case dans un jeu
    //  Si la mehode selectionnerCase retourne vrai alors cela veut dire
    //   que la partie est terminee
    //
    if (pfjeu.selectionnerCase(xCase,yCase))
    	pfjeu.arreter();
  }

  // Methode elementaire permettant d'afficher une ligne de texte
  //  dans la zone de resultats de l'ihm principale
  //  Si res est "" alors la zone resultat est effacee.
  //
  public void AR(String res)
  {
    if (res.equals("")) form.setValeurChamp("RESULTATS","");
    else form.setValeurChamp("RESULTATS",form.getValeurChamp("RESULTATS")+"\n"+res);
  }

  // Getteurs
  //
  public Formulaire getForm(){return form;}

  // Methode permettant de creer la grille d'un jeu dans l'ihm
  //
  public CanvasIHM creerGrille(int nbX,int nbY,int tailleCase)
  {
    
    if (grille!=null) form.supprimerGrille(grille);
    form.setPosition(550,0);
    grille = form.addGrilleIHM(nbX,nbY,tailleCase,this);
    return grille;
  }
}