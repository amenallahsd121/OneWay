/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Meddeb sofien
 */
public class EvenementService implements IService<Evenement>{

    
     Connection cnx = MyDB.getInstance().getCnx(); 
     
    @Override
    public void ajouter(Evenement t) throws SQLException {
         try {
   String req = "INSERT INTO evenement (nom,description,date_debut_event,date_fin_event,awards) VALUES("
                + "'" + t.getNom()+ "','" + t.getDescription()+ "','" + t.getDate_debut()+ "','" + t.getDate_fin() + "','" +t.getAwards()+ "'" + ")";
        
        
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
             System.out.println("Evenement Ajoute avec succes ");
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Evenement t) throws SQLException {
       try {
           String req = " UPDATE evenement SET nom = ? ,description = ? , date_debut_event = ? , date_fin_event = ?  , awards = ?   where id_event = ?    ";
            PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setString(2, t.getDescription());
        ps.setString(3, t.getDate_debut());
        ps.setString(4, t.getDate_fin());
       
        ps.setString(5, t.getAwards());
        ps.setInt(6, t.getId_event());
        ps.executeUpdate();
      System.out.println("Evenement Modifier avec succes ");
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void supprimer(Evenement t) throws SQLException {

   try{
            String req = " DELETE FROM evenement where id_event = ?   ";
       
             PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, t.getId_event());
             ps.executeUpdate();  
             System.out.println("Suppresion de l'evenement se fait");
    }catch (SQLException ex){
        System.out.println("Erreu"+ ex.getMessage());
       
   }
    }

    @Override
    public List<Evenement> recuperer(Evenement t) throws SQLException {
List<Evenement> event = new ArrayList<>();
        try {
            
            String req = "SELECT * FROM evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {                
                Evenement E = new Evenement();
                E.setId_event(rs.getInt(1));
                E.setNom(rs.getString(2));
                E.setDescription(rs.getString(3));
                E.setDate_debut(rs.getString(4));
                E.setDate_fin(rs.getString(5));
               
                E.setAwards(rs.getString(6));
                
                event.add(E);
            }
            
        } catch (SQLException ex) {
            System.out.println("ERREUR" + ex.getMessage());
        }
        
        return event;
    }  
   
}