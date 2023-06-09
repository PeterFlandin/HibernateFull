package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mysql.cj.protocol.ResultStreamer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur){

        Session session=HibernateUtil.getSessionFactory().getCurrentSession();

            session.persist(joueur);

            System.out.println("Joueur créé");

    }

    

    public void delete(Long id){

        Joueur joueur=new Joueur();
        joueur.setId(id);

        Session session=HibernateUtil.getSessionFactory().getCurrentSession();

        session.delete(joueur);

            System.out.println("Joueur supprimé");

    }

    public Joueur getById(Long id){

        Joueur joueur=null;
        Session session=null;

        session=HibernateUtil.getSessionFactory().getCurrentSession();
        joueur=session.get(Joueur.class,id);
        System.out.println("Joueur lu");

        return joueur;
    }

    public List<Joueur> list(){
        Connection conn = null;
        List<Joueur> joueurs=new ArrayList<>();
        try {
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            conn=dataSource.getConnection();

            PreparedStatement preparedStatement=conn.prepareStatement("SELECT ID,NOM,PRENOM,SEXE FROM JOUEUR");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Joueur joueur=new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
                joueurs.add(joueur);
            }


            System.out.println("Joueurs lus");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn!=null) conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return joueurs;
    }
}
