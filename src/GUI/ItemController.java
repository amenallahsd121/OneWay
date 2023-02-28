
package GUI;

import entity.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.utilisateurService;


public class ItemController implements Initializable {

    
    private Parent root;
    
    @FXML
    private HBox itemC;
    @FXML
    private Label nameitem;
    @FXML
    private Label lastnameitem;
    @FXML
    private Label emailitem;
    @FXML
    private Label adresseitem;
    @FXML
    private Label typeitem;
    @FXML
    private Label birthdateitem;
    @FXML
    private Label passitem;
    @FXML
    private Label nbPointitem;
    
     private Stage stage;
    private Scene scene;
    private Parent shit;
    

     utilisateur user = new utilisateur();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    }    
    public void Setutilisateur(utilisateur L)
    {
       
        nameitem.setText(L.getName());
        lastnameitem.setText(L.getLastname());
        emailitem.setText(L.getEmail());
        adresseitem.setText(L.getAdresse());        
        typeitem.setText(L.getType());        
        birthdateitem.setText(L.getBirthdate().toString());
        passitem.setText(L.getPassword());
        nbPointitem.setText(Integer.toString(L.getNbr_point()));
        System.out.println(L.getEmail());
        
        user=L;
        
    }

    @FXML
    private void change(ActionEvent event) throws IOException {
        
    FXMLLoader loader = new FXMLLoader ();
    loader.setLocation(getClass().getResource("modifier.fxml"));
    loader.load();

    ModifierController modifierController = loader.getController();
        System.out.println(user.getId());        
        System.out.println(user.getName());        
        System.out.println(user.getLastname());     
        System.out.println(user.getType());
    modifierController.setTextField(user.getId(),user.getName(), user.getLastname(),
            user.getEmail(),user.getAdresse(),user.getBirthdate(),user.getPassword(),user.getType() ,user.getNbr_point());
            System.out.println("fuck");

    Parent parent = loader.getRoot();
    Stage stage = new Stage();
           // System.out.println("fuck");

    stage.setScene(new Scene(parent));
    stage.initStyle(StageStyle.UTILITY);
    stage.show();
    
    
    
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException, IOException {
        utilisateurService us = new utilisateurService();
        us.supprimer(user);
         shit = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(shit);
        stage.setScene(scene);
        stage.show();     
    }
    
}
