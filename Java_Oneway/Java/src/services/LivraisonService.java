/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Livraison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;
/**
 *
 * @author amens
 */
public class LivraisonService implements IServices<Livraison>{

    
    Connection cnx;
    public LivraisonService() {
        
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Livraison t) throws SQLException {
       
        
         String req = "INSERT INTO livraison (etat,id_colis,id_livreur) VALUES("
                + "'" + t.getEtat() + "','" + t.getId_colis() + "','" + t.getId_livreur()+ "'" + ")";
        
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        
        
    }

    @Override
    public boolean modifier(Livraison t) throws SQLException {
      
         boolean ok = false;
        try {
         String req = " UPDATE livraison SET  etat = ? , id_colis = ? , id_livreur = ?  where id_livraison = ?    ";
         PreparedStatement ps = cnx.prepareStatement(req);
         ps.setString(1, t.getEtat());
         ps.setInt(2, t.getId_colis());
         ps.setInt(3, t.getId_livreur());
         ps.setInt(4, t.getId_livraison());
         ps.executeUpdate();
         ok = true;
        } catch (SQLException ex) {
            System.out.println("error in update " + ex);
        }
        return ok;  

    }

         
    

    @Override
    public boolean supprimer(Livraison t) throws SQLException {
      
           boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from livraison where id_livraison = ? ");
            req.setInt(1, t.getId_livraison());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;  
        
    }

    @Override
    public List<Livraison> recuperer() throws SQLException {
        
        
          List<Livraison> Livraison = new ArrayList<>();
        String s = "select * from livraison ";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Livraison Liv = new Livraison();
            
             Liv.setId_livraison(rs.getInt("id_livraison"));
             Liv.setEtat(rs.getString("etat"));
             Liv.setId_livreur(rs.getInt("id_livreur"));
             Liv.setId_colis(rs.getInt("id_colis"));
           
      
            
            
            Livraison.add(Liv);
            
        }
        return Livraison;
    }
    
    
    
    //////////////////////////////////////// ID CLIENT FROM COLIS ////////////////////////////////////////
    
      public int TrouverIdClientByColisId(int id) {
        Integer C = null;
        String Req = "select id_client from colis where id_colis = " + id + "";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(Req); //recherche
            while (res.next()) {

             C=res.getInt("id_client");
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return C;
    }
     
      
      
      
      //////////////////////////////////////// NOM LIVREUR AVEC ID LIVREUR ////////////////////////////////////////
      
      
      public String TrouverLivreurNameByColisId(int id) {
        String C = null;
        String Req = "select prenom from livreur where id_livreur  = " + id + "";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(Req); //recherche
            while (res.next()) {

             C=res.getString("prenom");
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return C;
    }
      
      
      
      //////////////////////////////////////// PRIX FROM  ID COLIS ////////////////////////////////////////
      
      
      
      public float TrouverPrixByIdColis(int id) {
        float C = 0;
        String Req = "select prix from colis where id_colis = " + id + "";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(Req); //recherche
            while (res.next()) {

             C=res.getFloat("prix");
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return C;
    }
     
    
    //////////////////////////////////////// NOM CLIENT FROM ID CLIENT ////////////////////////////////////////
      
      
        public String TrouverClientNameById(int id) {
        String C = null;
        String Req = "select name from utilisateur where id = " + id + "";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(Req); //recherche
            while (res.next()) {

             C=res.getString("name");
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return C;
    }
     
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
          public int TrouverLivreurIDByName(String name) {
        Integer C = null;
        String Req = "select id_livreur from livreur where prenom like '%"+ name +"%'" ;
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(Req); //recherche
            while (res.next()) {

             C=res.getInt(1);
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return C;
    }
        
      
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          
          public ObservableList<String> getlivreurname() {
       
        String Req = "select prenom from livreur ";
                  
      ObservableList<String> l = FXCollections.observableArrayList();
        try {
            
           Statement ste = cnx.createStatement();
           ResultSet res =  ste.executeQuery(Req); //recherche
            while (res.next()) {
                l.add(res.getString("prenom"));
                
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
          
          
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
     
      public Livraison TrouverLivraisonById(int id) {
        Livraison Liv = null;
        String Req = "select * from livraison where id_livraison = " + id + "";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(Req); //recherche
            while (res.next()) {

             Liv = new Livraison (res.getInt(1),res.getInt(3),res.getInt(4), res.getString(2) );
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Liv;
    }
 
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      
         public ObservableList<Integer> GetALLColislivraisonID() {
       
        String Req = "select id_colis from livraison";
                  
      ObservableList<Integer> l = FXCollections.observableArrayList();
        try {
            
           Statement ste = cnx.createStatement();
           ResultSet res =  ste.executeQuery(Req); //recherche
            while (res.next()) {
                l.add(res.getInt(1));
                
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return l;
    }
         
         
         
         //////////////////////////////////////////////////////////////////////////
    
}

    
    
            