
package GUI;

import entities.utilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.utilisateurService;


public class ModifierAdminController implements Initializable {

    @FXML
    private TextField lastnameM;
    @FXML
    private TextField emailM;
    @FXML
    private TextField adresseM;
    @FXML
    private TextField passM;
    @FXML
    private TextField nameM;
    @FXML
    private DatePicker birthdateM;
    @FXML
    private TextField idM;
    @FXML
    private TextField typeM;
    @FXML
    private TextField pointsM;
        utilisateur userM = new utilisateur();
        utilisateurService us = new utilisateurService();
    @FXML
    private Button closebutton;

        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void setTextField(int id ,String name, String lastname, String email, String adresse,LocalDate birthdate,String password,String type,int nb_point) {

        nameM.setText(name);
        birthdateM.setValue(birthdate);
        passM.setText(password);
        adresseM.setText(adresse);        
        emailM.setText(email);
        lastnameM.setText(lastname);
        idM.setText(Integer.toString(id));
        idM.setVisible(false);
        typeM.setText(type);
        typeM.setVisible(false);
        pointsM.setText(Integer.toString(nb_point));

    }
    public void getData() {

        
        userM.setName(nameM.getText());
        userM.setBirthdate(birthdateM.getValue());
        userM.setPassword(passM.getText());
        userM.setAdresse(adresseM.getText());        
        userM.setEmail(emailM.getText());
        userM.setLastname(lastnameM.getText());
        userM.setId(Integer.parseInt(idM.getText()));
        userM.setType(typeM.getText());
        userM.setNbr_point(Integer.parseInt(pointsM.getText()));
        
        
        
        
        
        
    }

    @FXML
    private void savechanges(ActionEvent event) throws SQLException {
         getData();
        us.modifier(userM);
        Stage stage = (Stage) closebutton.getScene().getWindow();
    // do what you have to do
    stage.close();    
        
    }

    
}
