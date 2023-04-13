package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;

import java.util.Scanner;

public class JoueurController {

    private JoueurService joueurService;

    public JoueurController(){
        this.joueurService=new JoueurService();
    }

    public void afficheDetailsJoueur(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur dont vous voulez afficher les informations ?");
        long identifiant=scanner.nextLong();
        Joueur joueur=joueurService.getJoueur(identifiant);
        System.out.println("Le joueur sélectionné s'appelle "+joueur.getPrenom()+" "+joueur.getNom());
    }

    public void creerJoueur(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est le nom du joueur ?");
        String nom=scanner.nextLine();
        System.out.println("Quel est le prenom du joueur ?");
        String prenom=scanner.nextLine();
        System.out.println("Quel est le sexe du joueur ?");
        char sexe=scanner.nextLine().charAt(0);
        Joueur joueur=new Joueur();
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setSexe(sexe);
        joueurService.createJoueur(joueur);
    }

    public void renommeJoueur(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur que vous voulez renommer ?");
        long identifiant=scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est est le nouveau nom ?");
        String nom=scanner.nextLine();
        joueurService.renomme(identifiant, nom);

    }
	
	public void changeSexeJoueur(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur qui change de sexe ?");
        long identifiant=scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est est le nouveau sexe ?");
        char sexe=scanner.nextLine().charAt(0);
        joueurService.changeSexe(identifiant, sexe);

    }

    public void supprimeJoueur(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur à supprimer ?");
        long identifiant=scanner.nextLong();

        joueurService.deleteJoueur(identifiant);

    }

}
