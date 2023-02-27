/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/*import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;*/
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Afficher_reclamationController implements Initializable {

    @FXML
    private Label uwelcomeLb;
    @FXML
    private Button retour_btn;
    @FXML
    private GridPane gridpane;
    ReclamationService Rs = new ReclamationService();
    Reclamation c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
             
            List<Reclamation> reclamation = Rs.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < reclamation.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Rec.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                RecController controller = loader.getController();
                controller.setReclamation(reclamation.get(i));
                

                gridpane.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }

    }    

    @FXML
    private void retournerHome(ActionEvent reclamation) {
        try {

            Parent loader = FXMLLoader.load(getClass().getResource("Ajouter_reclamation.fxml"));
           gridpane.getScene().setRoot(loader);
       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    
}