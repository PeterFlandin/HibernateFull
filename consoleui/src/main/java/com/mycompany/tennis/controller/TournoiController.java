package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.Scanner;

public class TournoiController {

    private TournoiService tournoiService;

    public TournoiController(){
        this.tournoiService=new TournoiService();
    }

    public void afficheDetailsTournoi(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du tournoi dont vous voulez afficher les informations ?");
        long identifiant=scanner.nextLong();
        TournoiDto tournoi=tournoiService.getTournoi(identifiant);
        System.out.println("Le tournoi sélectionné s'appelle "+tournoi.getNom());
    }

    public void creerTournoi(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est le nom du tournoi ?");
        String nom=scanner.nextLine();
        System.out.println("Quel est le code du tournoi ?");
        String code=scanner.nextLine();

        TournoiDto tournoi=new TournoiDto();
        tournoi.setNom(nom);
        tournoi.setCode(code);

        tournoiService.createTournoi(tournoi);
    }


    public void supprimeTournoi(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du tournoi à supprimer ?");
        long identifiant=scanner.nextLong();

        tournoiService.deleteTournoi(identifiant);

    }

}
