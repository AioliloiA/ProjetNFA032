// Projet 1 NFA 031 2017-2018
// 
// LAFORGUE Jacques
//
package fr.cnam.projet;

import java.io.*;
import java.util.*;

import fr.cnam.util.*;

// Classe principale d'execution du projet
//
public class Projet
{
  public static void main(String a_args[])
  {
    // A cause du firewall present en salle de TP
    //System.setSecurityManager(new SecurityManager());

    // Les ports utilises
    //
    int portServeur = 9100;  // Valeur par defaut
    int portRemote  = 9100;  // Valeur par defaut
    int portLocal   = 9101;  // Valeur par defaut
    try{
      portServeur = Integer.parseInt(a_args[0]);  
      portLocal   = Integer.parseInt(a_args[1]);  
      portRemote  = Integer.parseInt(a_args[2]);  
    }catch(Exception ex) 
      {System.out.println("Valeur par defaut des ports : "+portRemote+" "+portLocal);}
    


    Terminal.ecrireStringln("Execution du projet par J. LAFORGUE");

    // Creation de la plate-forme de Jeu (Partie metier ou applicatif)
    PFJeu pfjeu = new PFJeu(portServeur,portLocal,portRemote);

    // Creation de l'IHM de la plate-forme de Jeu qui prend en entree la
    //  plate-forme de Jeu
    IHMPFJeu ihm = new IHMPFJeu(pfjeu);

    // Creation du serveur de socket
    //
    System.out.println("Execution du serveur de socket sur le port "+portRemote);
    new ServeurSocketPFJeu(portServeur,pfjeu);
  }
}
