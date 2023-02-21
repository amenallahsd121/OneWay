/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
public class TrajetService implements IService<TrajetOffre> {

    Connection cnx;

    public TrajetService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(TrajetOffre t) throws SQLException {
        String req = "INSERT INTO TrajetOffre( LimiteKmOffre,AddArriveOffre, AddDepartOffre,description,  NbreEscaleOffre,nbreOffre) VALUES("
                + "'" + t.getLimiteKmOffre() + "','" + t.getAddArriveOffre() + "','" + t.getAddDepartOffre() + "','" + t.getDescription() + "','" + t.getNbreEscaleOffre() + "'," + t.getNbreOffre() + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(TrajetOffre t) throws SQLException {
        String req = "UPDATE TrajetOffre SET IdTrajetOffre = ?,LimiteKmOffre = ?,NbreEscaleOffre = ? ,nbreOffre=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setLong(1, t.getIdTrajetOffre());
        ps.setInt(2, t.getLimiteKmOffre());
        ps.setInt(3, t.getNbreEscaleOffre());
        ps.setInt(4, t.getNbreOffre());

        ps.executeUpdate();
        
    }

    @Override
    public void supprimer(TrajetOffre t) throws SQLException {
        String querry = "DELETE FROM TrajetOffre WHERE IdTrajetOffre = '" + t.getIdTrajetOffre() + "'";
        Statement stm = cnx.createStatement();

        stm.executeUpdate(querry);
    }

    @Override
    public List<TrajetOffre> recuperer() throws SQLException {
        List<TrajetOffre> TrajetOffres = new ArrayList<>();
        String s = "select * from TrajetOffre";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            TrajetOffre p = new TrajetOffre();

            p.setLimiteKmOffre(rs.getInt("LimiteKmOffre"));
            p.setNbreEscaleOffre(rs.getInt("NbreEscaleOffre"));
            p.setAddArriveOffre(rs.getString("AddArriveOffre"));
            p.setAddDepartOffre(rs.getString("AddDepartOffre"));
            p.setNbreOffre(rs.getInt("NbreOffre"));

            TrajetOffres.add(p);

        }
        return TrajetOffres;
    }

}
