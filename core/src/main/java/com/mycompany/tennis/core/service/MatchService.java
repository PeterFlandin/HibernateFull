package com.mycompany.tennis.core.service;


import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    private EpreuveRepositoryImpl epreuveRepository;
    private JoueurRepositoryImpl joueurRepository;

    public MatchService(){
        this.scoreRepository=new ScoreRepositoryImpl();
        this.matchRepository=new MatchRepositoryImpl();
        this.epreuveRepository=new EpreuveRepositoryImpl();
        this.joueurRepository=new JoueurRepositoryImpl();
    }

    public void enregistrerNouveauMatch(Match match){
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
    }

    public void deleteMatch(Long id){
        Session session=null;
        Transaction tx=null;
        try {

            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();

            matchRepository.delete(id);

            tx.commit();
        }
        catch (Exception e){
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }
    }

    public void createMatch(MatchDto dto){
        Session session=null;
        Transaction tx=null;
        Match match=null;
        try {

            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();

            match=new Match();
            match.setEpreuve(epreuveRepository.getById(dto.getEpreuve().getId()));
            match.setVainqueur(joueurRepository.getById(dto.getVainqueur().getId()));
            match.setFinaliste(joueurRepository.getById(dto.getFinaliste().getId()));
            Score score=new Score();
            score.setMatch(match);
            match.setScore(score);
            score.setSet1(dto.getScore().getSet1());
            score.setSet2(dto.getScore().getSet2());
            score.setSet3(dto.getScore().getSet3());
            score.setSet4(dto.getScore().getSet4());
            score.setSet5(dto.getScore().getSet5());

            matchRepository.create(match);

            tx.commit();
        }
        catch (Exception e){
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }
    }

    public void tapisVert(Long id){
        Session session=null;
        Transaction tx=null;
        Match match=null;
        try {

            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            match=matchRepository.getById(id);

            Joueur ancienVainqueur=match.getVainqueur();
            match.setVainqueur(match.getFinaliste());
            match.setFinaliste(ancienVainqueur);

            match.getScore().setSet1((byte)0);
            match.getScore().setSet2((byte)0);
            match.getScore().setSet3((byte)0);
            match.getScore().setSet4((byte)0);
            match.getScore().setSet5((byte)0);

            tx.commit();
        }
        catch (Exception e){
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }
    }

    public MatchDto getMatch(Long id){
        Session session=null;
        Transaction tx=null;
        Match match=null;
        MatchDto dto=null;
        try {

            session= HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            match=matchRepository.getById(id);

            dto=new MatchDto();
            dto.setId(match.getId());

            JoueurDto finalisteDto=new JoueurDto();
            finalisteDto.setId(match.getFinaliste().getId());
            finalisteDto.setNom(match.getFinaliste().getNom());
            finalisteDto.setPrenom(match.getFinaliste().getPrenom());
            finalisteDto.setSexe(match.getFinaliste().getSexe());
            dto.setFinaliste(finalisteDto);
            JoueurDto vainqueurDto=new JoueurDto();
            vainqueurDto.setId(match.getVainqueur().getId());
            vainqueurDto.setNom(match.getVainqueur().getNom());
            vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
            vainqueurDto.setSexe(match.getVainqueur().getSexe());
            dto.setVainqueur(vainqueurDto);

            EpreuveFullDto epreuveDto=new EpreuveFullDto();
            epreuveDto.setId(match.getEpreuve().getId());
            epreuveDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
            TournoiDto tournoiDto=new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            epreuveDto.setTournoi(tournoiDto);

            dto.setEpreuve(epreuveDto);

            ScoreFullDto scoreDto=new ScoreFullDto();
            scoreDto.setId(match.getScore().getId());
            scoreDto.setSet1(match.getScore().getSet1());
            scoreDto.setSet2(match.getScore().getSet2());
            scoreDto.setSet3(match.getScore().getSet3());
            scoreDto.setSet4(match.getScore().getSet4());
            scoreDto.setSet5(match.getScore().getSet5());

            dto.setScore(scoreDto);
            scoreDto.setMatch(dto);

            tx.commit();
        }
        catch (Exception e){
            if (tx!=null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if (session!=null){
                session.close();
            }
        }
        return dto;
    }

}
