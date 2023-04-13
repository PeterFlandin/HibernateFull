package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.service.EpreuveService;
import com.mycompany.tennis.core.service.MatchService;

import java.util.Scanner;

public class MatchController {

    private MatchService matchService;

    public MatchController(){
        this.matchService=new MatchService();
    }

    public void supprimerMatch(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du match que vous voulez supprimer ?");
        long identifiant=scanner.nextLong();
        matchService.deleteMatch(identifiant);
    }

    public void tapisVert(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du match que vous voulez annuler ?");
        long identifiant=scanner.nextLong();
        matchService.tapisVert(identifiant);
    }

    public void ajouterMatch(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'epreuve  ?");
        long epreuveId=scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est l'identifiant du vainqueur ?");
        long vainqueurId=scanner.nextLong();
        scanner.nextLine();
        System.out.println("Quel est l'identifiant du finaliste ?");
        long finalisteId=scanner.nextLong();
        scanner.nextLine();

        MatchDto dto=new MatchDto();
        dto.setEpreuve(new EpreuveFullDto());
        dto.getEpreuve().setId(epreuveId);
        dto.setFinaliste(new JoueurDto());
        dto.getFinaliste().setId(finalisteId);
        dto.setVainqueur(new JoueurDto());
        dto.getVainqueur().setId(vainqueurId);

        System.out.println("Quel est la valeur du 1er set ?");
        byte set1=scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 2eme set ?");
        byte set2=scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 3eme set ?");
        byte set3=scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 4eme set ?");
        byte set4=scanner.nextByte();
        scanner.nextLine();
        System.out.println("Quel est la valeur du 5eme set ?");
        byte set5=scanner.nextByte();
        scanner.nextLine();

        ScoreFullDto scoreDto=new ScoreFullDto();
        scoreDto.setSet1(set1);
        scoreDto.setSet2(set2);
        scoreDto.setSet3(set3);
        scoreDto.setSet4(set4);
        scoreDto.setSet5(set5);

        dto.setScore(scoreDto);
        scoreDto.setMatch(dto);

        matchService.createMatch(dto);

    }

    public void afficheDetailsMatch(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant du match dont vous voulez afficher les informations ?");
        long identifiant=scanner.nextLong();
        MatchDto dto=matchService.getMatch(identifiant);
        System.out.println("Il s'agit d'un match de "+dto.getEpreuve().getAnnee()+" qui s'est déroulé à "+dto.getEpreuve().getTournoi().getNom());
        System.out.println("Le nom et le prenom du vainqueur sont "+dto.getVainqueur().getNom()+" "+dto.getVainqueur().getPrenom());
        System.out.println("Le nom et le prenom du finaliste sont "+dto.getFinaliste().getNom()+" "+dto.getFinaliste().getPrenom());
        System.out.println("Les sets du score sont");
        System.out.println(dto.getScore().getSet1());
        System.out.println(dto.getScore().getSet2());
        if (dto.getScore().getSet3()!=null) {
            System.out.println(dto.getScore().getSet3());
        }
        if (dto.getScore().getSet4()!=null) {
            System.out.println(dto.getScore().getSet4());
        }
        if (dto.getScore().getSet5()!=null) {
            System.out.println(dto.getScore().getSet5());
        }
    }

}
