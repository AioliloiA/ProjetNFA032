package fr.cnam.projet;

import java.io.*;
import java.net.*;
import java.util.*;

// Classe de definition du serveur de socket qui prend
//  en entree la plate-frome de jeu.
// Son role :
//    - recevoir des requetes
//    - en fonction de la requete appelle une methode de PFJeu
//
public class ServeurSocketPFJeu
{
  // Contructeur
  //   Le serveur de socket est execute dans le constructeur
  //   Il prend en entree le port de communication
  //   Il prend en entree la plate-forme de jeu
  //
  public ServeurSocketPFJeu(int port,PFJeu pfjeu)
  {
    try{
      // Creation du serveur de socket
      //
      ServerSocket ssoc;
      ssoc = new ServerSocket(port);
            
      // Boucle sur l'acceptation d'un socket
      //
      while(true)
        {
          // Attente d'acceptation d'un socket
          //
          System.out.println("En attente...");
          Socket soc = ssoc.accept();
          // Un socket a etabli une communication
          System.out.println("Socket accepte");
          // Recuperation des flots de lecture et d'ecriture du socket
          //
          InputStream is = soc.getInputStream();
          OutputStream os = soc.getOutputStream();
          DataInputStream dis = new DataInputStream(is);
          DataOutputStream dos = new DataOutputStream(os);

          // Traitement de la requete
          //

          String requete = (String)(dis.readUTF());
          pfjeu.getIhm().AR("REQUETE RECUE : "+requete);
          
          // METTRE ICI LE CODE POUR TRAITER LES REQUETES
          //    - decodage de la requete
          //    - appele d'une methode de pfjeu
          //    - ecriture du retour ou del'accusee de reception
          //

          dos.writeUTF("OK. REPONSE ");

        }
    }catch(Exception ex){
      System.out.println("ERREUR Serveur de socket");
      ex.printStackTrace();
    }
  }
}
