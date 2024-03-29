/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Participation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import services.EvenementService;
import services.ParticipationService;

/**
 * FXML Controller class
 *
 * @author Meddeb sofien
 */
public class ParticipationcontrolController implements Initializable {

    @FXML
    private Label ideventlabel;
    @FXML
    private Label iduserlabel;
    @FXML
    private Label nomevent;
    @FXML
    private Label id_label;
    @FXML
    private Label idus;
    
     Participation p = new Participation();
    ParticipationService ps =new ParticipationService();
    EvenementService es = new EvenementService();
     int   idt= ps.getidclientt();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setParticipationn(Participation  E) {
        String s1 = es.getNomEvent(E.getId_event());
         String nomPart = ps.getNomParticpant(E.getId_user());
         id_label.setText(String.valueOf(E.getId_participation()));
         idus.setText(String.valueOf(E.getId_user()) );
         iduserlabel.setText(nomPart );
         nomevent.setText(s1 );
         ideventlabel.setText(String.valueOf(E.getId_event()));
        
      p=E;
       
    }
    

    @FXML
    private void AnnulerParticipation(ActionEvent event) throws IOException {
           try {
        Participation a =new Participation();
         a.setId_participation(Integer.parseInt(id_label.getText()));
       if(idt== Integer.parseInt(idus.getText())){
            
              Alert alert = new Alert(Alert.AlertType.WARNING);

              alert.setTitle("confirmation Dialog");

              alert.setHeaderText(null);

              alert.setContentText("Voulez vous Annuler cet Participation!");
              ButtonType cancelBtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
             alert.getButtonTypes().setAll(cancelBtn, ButtonType.OK);
              Optional<ButtonType> result = alert.showAndWait();
           
           if (result.isPresent() && result.get() == ButtonType.OK) {
    // User clicked OK 
           ps.supprimer(a);
} else {
    // User clicked cancel or closed the dialog
               System.out.println("Suppression Annuler");
    
}
       
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherParticipation.fxml"));
            id_label.getScene().setRoot(loader);
       }
       else {
           Alert alert = new Alert(Alert.AlertType.ERROR);

              alert.setTitle("Information Dialog");

              alert.setHeaderText(null);

              alert.setContentText("tu ne peut pas supprimer une participation d'un autre utilisateur ");

              alert.show();
           
       }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
