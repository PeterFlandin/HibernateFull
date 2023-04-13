package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchRepositoryImpl {


    public Match getById(Long id){
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        Match match=session.get(Match.class,id);
        System.out.println("Match lu");
        return match;
    }

    public void create(Match match){
        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(match);
        System.out.println("Match ajouté");
    }

    public void delete(Long id){

        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        Match match=session.get(Match.class,id);
        session.delete(match);
        System.out.println("Match supprimé");
    }


}
