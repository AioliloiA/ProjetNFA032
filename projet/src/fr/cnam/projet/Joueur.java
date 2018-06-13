package fr.cnam.projet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.*;

// Classe de definition d'un joueur
//
public class Joueur
{
  private String  ident;      // identificateur (nom ou pseudo)
  private String  mdp;        // mot de passe

  // Constructeur
  //
  public Joueur(String ident,
                String mdp)
  {
    this.ident=ident;
    this.mdp = mdp;
  }

  public Joueur() {
	// TODO Auto-generated constructor stub
}

// Joueur en chaine
  //
  public String toString()
  {
    return String.format("%-30s %-20s ",
                         ident,mdp);
  }
  
  public void write(DataOutputStream dos) throws Exception {
		dos.writeUTF(ident);
		dos.writeUTF(mdp);
		
	}
  
  public void read(DataInputStream dis) throws Exception {
		
	  ident = dis.readUTF();
	  mdp = dis.readUTF();
		
	}

  // Getteurs
  public String getIdent(){return ident;}
  public String getMdp(){return mdp;}




}