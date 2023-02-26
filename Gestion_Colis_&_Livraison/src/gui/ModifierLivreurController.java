/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Colis;
import entities.Livreur;
import services.LivreurService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import static javax.swing.JOptionPane.showMessageDialog;
import services.LivraisonService;

/**
 * FXML Controller class
 *
 * @author amens
 */
public class ModifierLivreurController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private  TextField cintf;
    @FXML
    private  TextField prenomtf;
    @FXML
    private  TextField nomtf;
    @FXML
    private  TextField vehiculetf;


    
    private static int id;
    // LivreurService LSS = new LivreurService();
    Livreur liv = new Livreur();
    
    LivreurService LS = new LivreurService();
    Livreur livreur = new Livreur();
    
   


   
    
    /////////////////////////////////////////////////////////////////// GET ID LIVREUR FUNCTION //////////////////////////////////////////////////////////////
    
     public static int getIdd(Livreur l) {
        
        id = l.getId_livreur();
          System.out.println(id);
   
        return id;
        
    }
     
     /////////////////////////////////////////////////////////////// CHECK INTEGER FUNCTION /////////////////////////////////////////////////////////////////
     
      boolean checkifstringisnumber (String s){
        try {
    int i;        
    i = Integer.parseInt(s);
    return true;
    } 
        catch (NumberFormatException e) {
    System.out.println("Input String cannot be parsed to Integer.");
    }
        return false;
    }
     
     // ****************************************************************** INITIALAISE **********************************************************************
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        liv=LS.TrouverById(id);
        
        System.out.println(id);
   
      
    }    

    
    //////////////////////////////////////////////////////////////////// AFFICHER AFFECTER COLIS /////////////////////////////////////////////////
    
    @FXML
    private void AfficherAfeecterColis(ActionEvent event) {
    }
    
    //////////////////////////////////////////////////////////////////// AFFICHER LIVREUR ///////////////////////////////////////////////////////
    
    @FXML
    private void AfficherLivreur(ActionEvent event) {
    }

    
    /////////////////////////////////////////////////////////////////////// UPDATE LIVREUR ///////////////////////////////////////////////////////
    
    @FXML
    private void ModifierLivreur(ActionEvent event) {
        
      try {
           
            livreur.setId_livreur(id);      
            livreur.setCin_livreur(cintf.getText());
            livreur.setPrenom(prenomtf.getText());
            livreur.setNom(nomtf.getText());
            livreur.setVehicule(vehiculetf.getText());
             if(cintf.getText().isEmpty() || prenomtf.getText().isEmpty() || nomtf.getText().isEmpty() || vehiculetf.getText().isEmpty())
            {
                
                showMessageDialog(null, "Vérifier Vos Champs" ); 
            }
            else
            if(cintf.getText().length()!=8)
                {
                     showMessageDialog(null, "Le Numéro de CIN Doit Etre Composer de 8 Chiffres"); 
                }
             else
            if(this.checkifstringisnumber(cintf.getText()) == false)
                {
                     showMessageDialog(null, "Le Numéro de CIN Doit Etre Contenir Des Chiffres Seulement"); 
                }
            else
            {
            LS.modifier(livreur);
            showMessageDialog(null, "Livreur Modifier Avec Succès");
            }
                    
          
            
            
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        
    }
    
    
    

    
    //////////////////////////////////////////////////////////////////// AFFICHER LIVREUR /////////////////////////////////////////////////////////////////
    
     

    @FXML
    private void AnnulerLivreur(ActionEvent event) {
           
           
          try {
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherLivreur.fxml"));
            Parent root = (Parent)loader.load();
            AfficherLivreurController controller = (AfficherLivreurController)loader.getController();
            cintf.getScene().setRoot(root);
             
        
        } catch (Exception e) {
             System.out.println(e);
                
        }
    }
    
    
    
    
    void SetData () {
        
        
       // String cin;
      //  cin=liv.getCin_livreur();
       // cintf.setPromptText(cin);
        
        
    }
    
    
}
