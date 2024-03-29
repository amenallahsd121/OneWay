/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Offre;
import entities.TrajetOffre;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author utilisateur
 */
public class OffreService {
    
    Connection cnx;
    
    public OffreService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
 
    public void ajouter(Offre t) throws SQLException {
        
        String req = "INSERT INTO Offre( IdCatColis, CatOffreId,IdUser, DescriptionOffre, MaxRetard, prixOffre,IdTrajetOffre,Etat,DateOffre,DateSortieOffre,nbreDemande) VALUES("
                + "'" + t.getIdCatColis() + "','" + t.getCatOffreId() + "','" + t.getIdUser() + "','" + t.getDescriptionOffre() + "','" + t.getMaxRetard() + "','" + t.getPrixOffre() + "','" + t.getIdTrajetOffre() + "','" + t.getEtat() + "','" + t.getDateOffre() + "','" + t.getDateSortieOffre() + "'," + t.getNbreDemande() + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    
 
    public void modifier(Offre t) throws SQLException {
        String req = "UPDATE Offre SET IdOffre = ?,IdCatColis = ? ,CatOffreId = ? ,DescriptionOffre = ?,MaxRetard = ?,prixOffre = ?,IdTrajetOffre = ?,Etat = ?,DateSortieOffre = ? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdOffre());
        ps.setFloat(2, t.getIdCatColis());
        ps.setString(3, t.getCatOffreId());
        ps.setString(4, t.getDescriptionOffre());
        ps.setString(5, t.getMaxRetard());
        ps.setFloat(6, t.getPrixOffre());
        ps.setString(7, t.getIdTrajetOffre());
        ps.setString(8, t.getEtat());
        
        ps.setString(9, t.getDateSortieOffre());
        
        ps.executeUpdate();
        
    }
    
 
    public void supprimer(Offre t) throws SQLException {
        String querry = "DELETE FROM Offre WHERE IdOffre = '" + t.getIdOffre() + "'";
        Statement stm = cnx.createStatement();
        
        stm.executeUpdate(querry);
    }
    
  
    public List<Offre> recuperer() throws SQLException {
        List<Offre> Offres = new ArrayList<>();
        String s = "select * from Offre";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Offre p = new Offre();
            
            p.setMaxRetard(rs.getString("MaxRetard"));
            p.setIdOffre(rs.getInt("IdOffre"));
            p.setIdCatColis(rs.getInt("IdCatColis"));
            p.setCatOffreId(rs.getString("CatOffreId"));
            p.setDescriptionOffre(rs.getString("DescriptionOffre"));
            p.setIdTrajetOffre(rs.getString("IdTrajetOffre"));
            p.setDateSortieOffre(rs.getString("DateSortieOffre"));
            p.setPrixOffre(rs.getFloat("PrixOffre"));
            p.setIdUser(rs.getInt("IdUser"));
            Offres.add(p);
            
        }
        return Offres;
    }
}
