package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.ScoreService;

import java.util.Scanner;

public class ScoreController {

    private ScoreService scoreService;

    public ScoreController(){
        this.scoreService=new ScoreService();
    }

    public void supprimerScore(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du score que vous voulez supprimer ?");
        long identifiant=scanner.nextLong();
        scoreService.deleteScore(identifiant);
    }

    public void afficheDetailsScore(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du score dont vous voulez afficher les informations ?");
        long identifiant=scanner.nextLong();
        ScoreFullDto score=scoreService.getScore(identifiant);
        System.out.println("Les sets du score sont");
        System.out.println(score.getSet1());
        System.out.println(score.getSet2());
        if (score.getSet3()!=null) {
            System.out.println(score.getSet3());
        }
        if (score.getSet4()!=null) {
            System.out.println(score.getSet4());
        }
        if (score.getSet5()!=null) {
            System.out.println(score.getSet5());
        }

        System.out.println("Il s'agit du tournoi "+score.getMatch().getEpreuve().getTournoi().getNom());
        System.out.println("L'épreuve s'est déroulée en "+score.getMatch().getEpreuve().getAnnee()+" et il s'agissait d'une épreuve "+(score.getMatch().getEpreuve().getTypeEpreuve().charValue()=='H' ? "Homme" : "Femme"));

    }

}
