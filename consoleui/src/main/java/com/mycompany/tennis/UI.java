package com.mycompany.tennis;

import com.mycompany.tennis.controller.EpreuveController;
import com.mycompany.tennis.controller.MatchController;
import com.mycompany.tennis.controller.ScoreController;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Match;

public class UI {

    public static void main(String... args){

        ScoreController controller=new ScoreController();
        controller.supprimerScore();
    }

}
