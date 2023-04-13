package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.service.EpreuveService;

import java.util.Scanner;

public class EpreuveController {

    private EpreuveService epreuveService;

    public EpreuveController(){
        this.epreuveService=new EpreuveService();
    }

    public void afficheDetailsEpreuve(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'epreuve dont vous voulez afficher les informations ?");
        long identifiant=scanner.nextLong();
        EpreuveFullDto epreuve=epreuveService.getEpreuveDetaillee(identifiant);
        System.out.println("Le nom du tournoi est "+epreuve.getTournoi().getNom());
        for (JoueurDto joueurDto : epreuve.getParticipants()){
            System.out.println(joueurDto.getPrenom()+" "+joueurDto.getPrenom());
        }

    }

    public void afficheRolandGarros(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Quel est l'identifiant de l'epreuve dont vous voulez afficher les informations ?");
        long identifiant=scanner.nextLong();
        EpreuveLightDto epreuve=epreuveService.getEpreuveSansTournoi(identifiant);
    }

}
