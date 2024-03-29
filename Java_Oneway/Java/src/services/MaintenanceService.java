/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Houda
 */
import entities.Maintenance;
import entities.Vehicule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;
public class MaintenanceService  { 
    Connection cnx;

    public MaintenanceService() {
        cnx = MyDB.getInstance().getCnx();
    }
     
    public void ajouter(Maintenance t) throws SQLException {
       /* String req = "INSERT INTO maintenance(etat,nom_sos_rep) VALUES("
                + "'" + t.getEtat()+ "','" + t.getNom_sos_rep()+ "'" + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);*/
       
       String req = "INSERT INTO maintenance(etat,nom_sos_rep,id_vehicule) VALUES("
                + "'" + t.getEtat()+ "','" + t.getNom_sos_rep()+ "','" +t.getId_vehicule() + "'" +")";
          
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    
    public void modifier(Maintenance t) throws SQLException {
        String req = "UPDATE maintenance SET etat = ?,nom_sos_rep = ? where id_maintenance = ?";
        PreparedStatement vm = cnx.prepareStatement(req);
        vm.setString(1, t.getEtat());
        vm.setString(2, t.getNom_sos_rep());
        vm.setInt(3, t.getId_maintenance());
        vm.executeUpdate();
        
    }
    
    
    public boolean supprimer(Maintenance t) throws SQLException {
       boolean ok = false;
       try {
       String req = " DELETE FROM maintenance where id_maintenance = ?   ";
         
            PreparedStatement vm = cnx.prepareStatement(req);
            vm.setInt(1, t.getId_maintenance());
            vm.executeUpdate();
            ok= true;
            }
        catch ( SQLException ex){
            System.out.println("error in delete"+ex);
       
           
        }
        return ok;
            
            
    }
    
    
    
    
    public List<Maintenance> recuperer() throws SQLException {
        List<Maintenance> maintenance = new ArrayList<>();
        String s = "select m.etat, m.nom_sos_rep, m.id_maintenance, v.matricule from maintenance m INNER JOIN vehicule v WHERE v.id_vehicule = m.id_vehicule";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Maintenance m = new Maintenance();
            m.setEtat(rs.getString("m.etat"));
            m.setNom_soc_rep(rs.getString("m.nom_sos_rep"));
            m.setId_maintenance(rs.getInt("m.id_maintenance"));
           // m.setId_vehicule(rs.getInt("id_vehicule"));
            m.setVehicule(rs.getString("v.matricule"));
            
             maintenance.add(m);
            
        }
        return  maintenance;
    }
    
    
    public List<Maintenance> recupererTrier() throws SQLException {
        List<Maintenance> maintenance = new ArrayList<>();
        String s = "select m.etat, m.nom_sos_rep, m.id_maintenance, v.matricule from maintenance m INNER JOIN vehicule v WHERE v.id_vehicule = m.id_vehicule ORDER BY m.etat";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Maintenance m = new Maintenance();
            m.setEtat(rs.getString("m.etat"));
            m.setNom_soc_rep(rs.getString("m.nom_sos_rep"));
            m.setId_maintenance(rs.getInt("m.id_maintenance"));
           // m.setId_vehicule(rs.getInt("id_vehicule"));
            m.setVehicule(rs.getString("v.matricule"));
            
             maintenance.add(m);
            
        }
        return  maintenance;
    }
    
    
    
    public List<Maintenance> recuperer(int sizee,String word)  throws SQLException{
         List<Maintenance> maintenances = new ArrayList<>();
        String s = "select m.etat, m.nom_sos_rep, m.id_maintenance, v.matricule from maintenance m INNER JOIN vehicule v WHERE v.id_vehicule = m.id_vehicule";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
           Boolean find = true;
            Maintenance m = new Maintenance();
            m.setId_maintenance(rs.getInt("m.id_maintenance"));
            m.setEtat(rs.getString("m.etat"));
            m.setNom_sos_rep(rs.getString("m.nom_sos_rep"));
            m.setVehicule(rs.getString("v.matricule"));
            
            //System.out.println(word );           
            System.out.println(sizee );

            //String ww = word + "hhh";
            //System.out.println(ww.charAt(2) + " chhh");       
            //if(sizee == 1)
            {
             for(int i=0; i<sizee && find == true ; i++)
             {
                System.out.println(word.charAt(i));          
                System.out.println(word);
                 if(m.getEtat().charAt(i) == word.charAt(i))
                 {
                     System.out.println("in");
                     find = true;
                 }
                 else
                 {
                     System.out.println("out");
                    find =false;
                 }

             }
                System.out.println(find);
             if(find == true)
             {
                 maintenances.add(m);
             }
             
             
            }
            
            
        }
        return maintenances;
    }
    
   
    
    
     public Maintenance TrouverById(int id) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        
        Maintenance m = null;
        String Req = "select * from maintenance where id_maintenance= " + id + "";
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(Req); //recherche
            while (res.next()) {

               m = new Maintenance (res.getInt("id_maintenance"), res.getString("etat"), res.getString("nom_sos_rep") , res.getInt("id_vehicule"));
            }

        } catch (SQLException ex) {
          //  Logger.getLogger(LService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    
}
     
}
