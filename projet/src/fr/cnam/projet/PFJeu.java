package fr.cnam.projet;

import java.util.*;
import java.text.*;
import java.io.*;
import java.net.*;

import fr.cnam.util.*;
import fr.cnam.ihm.*;
import fr.cnam.projet.*;

// Classe de definition d'une plate-forme de jeu
//   Cette plate-fome de jeu permet de jouer a un jeu de grille (ex othello)
//    et gère les joueurs et les parties jouees
//
public class PFJeu
{
  //private Othello  jeu;      // Le jeu (classe de modelisation du jeu)
  //private Morpion  jeu;      // Le jeu (classe de modelisation du jeu)
  private Jeu  jeu;      // Le jeu (classe de modelisation du jeu)

  private IHMPFJeu ihm;      // Ihm de la plate-forme de jeu

  private ArrayList<Joueur> joueurs;  // La collection des joueurs
  private ArrayList<AbstractPartie> parties;  // La collection des parties jouees

  private String identJoueurCourant;  // L'identification du joueur courant
  private String identAdversaire;     // L'identification du joueur adverse

  private int portServeur;  //  Port du serveur de socket
  private int portLocal;    //  Port local
  private int portRemote;   //  Port du joueur distant

  // Constructeur de la plate-forme de jeu :
  //   initialisation par défaut des attributs de la classe
  //
  public PFJeu(int portServeur,int portLocal,int portRemote)
  {
    joueurs = new ArrayList<Joueur>();
    parties = new ArrayList<AbstractPartie>();
    identJoueurCourant = "";
    identAdversaire = "";
    this.portServeur = portServeur;
    this.portLocal = portLocal;
    this.portRemote = portRemote;
  }

  // Initialisation de la plate-forme de jeu.
  // Cette methode est appelle par l'IHM
  //  une fois l'ihm initialise 
  //
  public void initialiser()
  {
	  
	  
    ihm.AR("Initialisation de la plate-forme de jeu :");   
    
    //Récupération des noms des fichiers Parties et Joueurs   
    //
    String joueursTxt = ihm.form.getValeurChamp("SAISIR_JOUEURS");
    String partiesTxt = ihm.form.getValeurChamp("SAISIR_PARTIES");
    
    // Lecture du fichier des joueurs inscrits
    //
    ihm.AR("Lecture du fichier des joueurs inscrits");
    String[] lignes = Terminal.lireFichierTexte("data/"+joueursTxt);
    if (lignes == null) 
      {
        ihm.AR("Impossible de lire le fichier data/"+joueursTxt);
      }
    else
      {
        // Parcours de chaque ligne d'un joueur
        //
        for(String ligne:lignes)
          {
            String[] champs = ligne.split(";",2);
            String ident = champs[0];
            String mdp   = champs[1];
            Joueur joueur = new Joueur(ident,mdp);
            joueurs.add(joueur);
          }
      }

    // Lecture du fichier des parties jouees
    //
    ihm.AR("Lecture du fichier des partie jouees");
    lignes = Terminal.lireFichierTexte("data/"+partiesTxt);
    if (lignes == null) 
      {
        ihm.AR("Impossible de lire le fichier data/"+partiesTxt);
      }
    else
      {
        // Parcours de chaque ligne d'une partie
        for(String ligne:lignes)
          {
            String[] champs = ligne.split(";",14);
            int    numero           = Integer.parseInt(champs[0]);
            String identJoueur1      = champs[1];
            String identJoueur2      = champs[2];
            String date             = champs[3];
            String nomJeu           = champs[4];
            int    termineeI         = Integer.parseInt(champs[5]);
            boolean partieTerminee        = termineeI==1?true:false;
            int    joueur1GagneI    = Integer.parseInt(champs[6]);
            boolean joueur1Gagne    = joueur1GagneI==1?true:false;
            int    joueur2GagneI    = Integer.parseInt(champs[7]);
            boolean joueur2Gagne    = joueur2GagneI==1?true:false;
            int    nbPionRetourneJoueur1    = Integer.parseInt(champs[8]);
            int    nbPionRetourneJoueur2    = Integer.parseInt(champs[9]);
            int    nbPionPrisJoueur1        = Integer.parseInt(champs[10]);
            int    nbPionPrisJoueur2        = Integer.parseInt(champs[11]);
            int    nbCaseTerritoireJoueur1  = Integer.parseInt(champs[12]);
            int    nbCaseTerritoireJoueur2  = Integer.parseInt(champs[13]);
            
            
            if ("morpion".equalsIgnoreCase(nomJeu)) 
            	{ 
            	PartieMorpion partie = 
            	new PartieMorpion(numero, identJoueur1, identJoueur2, date, nomJeu, partieTerminee, joueur1Gagne, joueur2Gagne) {};
            	parties.add(partie);
            	}
            
            else if ("go".equalsIgnoreCase(nomJeu)) 
            	{
            	PartieGo partie = 
            	new PartieGo(numero, identJoueur1, identJoueur2, date, nomJeu, partieTerminee, joueur1Gagne, joueur2Gagne,nbPionPrisJoueur1,nbPionPrisJoueur2,nbCaseTerritoireJoueur1,nbCaseTerritoireJoueur2) {};
            	parties.add(partie);
            	}
            		
            else if ("othello".equalsIgnoreCase(nomJeu))
            	{
            	PartieOthello partie = 
        		new PartieOthello(numero, identJoueur1, identJoueur2, date, nomJeu, partieTerminee, joueur1Gagne, joueur2Gagne,nbPionRetourneJoueur1,nbPionRetourneJoueur2) {};
        		parties.add(partie);
            	}
            
            else continue;
//            		//new AbstractPartie(numero,
//                                       identJoueur1,
//                                       identJoueur2,
//                                       date,
//                                       nomJeu,
//                                       terminee,
//                                       joueur1Gagne,
//                                       joueur2Gagne,
//                                       nbPionRetourneJoueur1,
//                                       nbPionRetourneJoueur2,
//                                       nbPionPrisJoueur1,
//                                       nbPionPrisJoueur2,
//                                       nbCaseTerritoireJoueur1,
//                                       nbCaseTerritoireJoueur2);
            
          }
      }

  }
    
  // Methode qui retourne sous la forme d'une chaine de caractere
  //  tous les joueurs
  //
  public void listerTousJoueurs()
  {
    ihm.AR("");
    for(Joueur j : joueurs)
      ihm.AR(j.toString());
  }
  
  // Methode qui retourne sous la forme d'une chaine de caractere
  //  toutes les parties
  //
  public void listerToutesParties()
  {
    ihm.AR("");
    for(AbstractPartie p : parties)
      ihm.AR(p.toString());
  }

  // Methode qui realise l'inscription d'un nouveau joueur
  //
  public void inscrire(String ident,String mdp)
  {
    if (ident.equals("")) 
      {
        ihm.AR("L'identifiant saisi est vide");
        return;
      }

    if (ident.indexOf(' ')!=-1)
      {
        ihm.AR("L'identifiant contient un caractère blanc");
        return;
      }

    for(Joueur j:joueurs)
      if (j.getIdent().equals(ident))
        {
          ihm.AR("L'identifiant existe deja.");
          return;
        }

    if (mdp.equals(""))
      {
        ihm.AR("Le mot de passe saisi est vide");
        return;
      }

    if (mdp.indexOf(' ')!=-1)
      {
        ihm.AR("Le mot de passe contient un caractère blanc");
        return;
      }

    if (mdp.length()>15)
      {
        ihm.AR("Le mot de passe doit etre inferieur ou egal a 15 caracteres");
        return;
      }

    // Ajout du nouveau joueur
    joueurs.add(new Joueur(ident,mdp));
    
    ihm.AR("Le nouveau joueur est inscrit");

  }


  // Methode qui realise l'identification d'un joueur
  //
  public void identifier(String ident,String mdp)
  {
    Joueur joueur = null;
    for(Joueur j:joueurs)
      if (j.getIdent().equals(ident)) 
        joueur=j;
    
    if (joueur==null)
      {
        ihm.AR("L'identifiant saisie n'existe pas");
        return;
      }

    if (! mdp.equals(joueur.getMdp()))
      {
        ihm.AR("Le mot de passe saisi n'est pas correct");
        return;
      }

    identJoueurCourant = ident;

    // On met a jour le nom du joueur courant dans l'ihm
    ihm.getForm().setValeurChamp("JOUEUR",ident);

    ihm.AR("Idenitification de "+ident+" ok.");
  }

  // Methode qui affiche les parties jouees du joueur
  //
  public void partiesJoueur()
  {
    if (identJoueurCourant.equals(""))
      {
        ihm.AR("Pas de joueur courant");
        return;
      }
    
    boolean ok=false;
    for(AbstractPartie p : parties)
      if ( (p.getIdentJoueur1().equals(identJoueurCourant)) ||
           (p.getIdentJoueur2().equals(identJoueurCourant)) )
        {
          ihm.AR(p.toString());
          ok=true;
        }
    if (!ok) ihm.AR("Le joueur "+identJoueurCourant+" n'a pas de partie jouee");
  }

  // Choix de l'adversaire
  //
  public void choisirAdversaire(String ident)
  {
    identAdversaire = ident;
    ihm.getForm().setValeurChamp("ADVERSAIRE",ident);
    ihm.AR("Choix de l'adversaire : "+ident);
  }

  // Selection d'une case dans la grille
  //
  public boolean selectionnerCase(int xCase,int yCase)
  {
    return jeu.selectionnerCase(xCase,yCase);
  }

  // Demarrer une partie
  //  et creation du jeu (qui cree la grille de jeu)
  //
  public void demarrer(String jeuChoisi)
  {
    if ( (jeu!=null) && jeu.getDemarree())
      {
        ihm.AR("Jeu deja demarre.");
        return;
      }

    if (identJoueurCourant.equals(""))
      {
        ihm.AR("Pas de joueur courant identifie");
        return;
      }

    if (identAdversaire.equals(""))
      {
        ihm.AR("Pas d'adversaire.");
        return;
      }

    
    // Choix du jeu en fonction du parametre de la méthode
    //
    if (jeuChoisi.equals("othello"))   		
    		jeu = new Othello(this);
    		   
    else if (jeuChoisi.equalsIgnoreCase("morpion"))  	    
            jeu = new Morpion(this);
    	    
    else 	jeu = new Go(this);
    jeu.demarrer();
    
    ihm.AR("Partie demarree");
  }

  // Methode qui arrete une partie et memorise les scores
  //  de la partie
  //
  public void arreter()
  {
    // Il faut que la partie soit demarree
    if ( (jeu==null) || (! jeu.getDemarree()))
        {
          ihm.AR("La partie n'est pas demarree");
          return;
        }

    // La partie s'arrete
    //
    jeu.setDemarree(false);

    // On memorise la partie jouee par les deux joueurs
    //
    AbstractPartie p = jeu.getPartie(numeroPartie(),
                             identJoueurCourant,
                             identAdversaire);
    parties.add(p);
    ihm.AR("Ajout de la partie");

    ihm.AR("Arret de la partie");
  }

  // Methode de test de la communication socket
  //
  public void tester()
  {
    try{
      Socket soc;

      if (identAdversaire.equals("localhost"))
        soc = new Socket(Inet4Address.getLocalHost(),portRemote,
                         Inet4Address.getLocalHost(),portLocal);
      else
          soc = new Socket(Inet4Address.getByName(identAdversaire),portRemote,
                           Inet4Address.getLocalHost(),portLocal);
      soc.setSoLinger(true,0);
      InputStream is = soc.getInputStream();
      OutputStream os = soc.getOutputStream();
      DataOutputStream dos = new DataOutputStream(os);
      DataInputStream dis = new DataInputStream(is);
      
      dos.writeUTF("REQUETE_DE_TEST");
      String reponse = dis.readUTF();
      ihm.AR(reponse);
      soc.close();
    }
    catch(Exception ex){ex.printStackTrace();}
  }


  // Methode qui retourne un nouveau numero de partie
  //  = le numero max +1
  //
  private int numeroPartie()
  {
    int max=0;
    for(AbstractPartie p:parties)
      if (p.getNumero()>max) max=p.getNumero();
    return max+1;
  }


  // Getteurs
  public IHMPFJeu getIhm(){return ihm;}
  public void setIhm(IHMPFJeu ihm){this.ihm=ihm;}
  public ArrayList<Joueur> getJoueurs(){return joueurs;}
}