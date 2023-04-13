package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Score;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreRepositoryImpl {


    public Score getById(Long id){
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        Score score=session.get(Score.class,id);
        System.out.println("Score lu");
        return score;
    }

    public void create(Score score){
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(score);
        System.out.println("Score ajouté");
    }

    public void delete(Long id){

        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        Score score=session.get(Score.class,id);
        session.delete(score);
        System.out.println("Score supprimé");
    }

}
