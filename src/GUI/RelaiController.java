
package GUI;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entity.relais;
import entity.utilisateur;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.relaisService;
import service.utilisateurService;


public class RelaiController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;
    private List<relais> Liv = new VirtualFlow.ArrayLinkedList<>();
     relaisService LS = new relaisService();
     
     private Stage stage;
    private Scene scene;
    private Parent root;

  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        try { 
            Liv = LS.recuperer();

        } catch (SQLException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.afficherRelai();
    }    

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
         if (event.getSource() == btnCustomers) {
             root = FXMLLoader.load(getClass().getResource("Relaiview.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
        }
        if (event.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (event.getSource() == btnOverview) {
            root = FXMLLoader.load(getClass().getResource("home.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
        }
        if(event.getSource()==btnOrders)
        {
            root = FXMLLoader.load(getClass().getResource("adminview.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
        }
    }
    public void afficherRelai(){
        //System.out.println(Liv.size());
    for(int i=0 ; i<Liv.size() ; i++)
    {
        FXMLLoader fxmlloader = new FXMLLoader();
        fxmlloader.setLocation(getClass().getResource("../GUI/itemRelai.fxml"));
        
        try {
            HBox hbox = fxmlloader.load();
            itemRelaiController Lvc = fxmlloader.getController();
            System.out.println(Liv.get(i).getId());
            Lvc.SetRelai(Liv.get(i));
            hbox.setOnMouseEntered(event -> {
                    hbox.setStyle("-fx-background-color : #0A0E3F");
                });
                hbox.setOnMouseExited(event -> {
                    hbox.setStyle("-fx-background-color : #02030A");
                });
            pnItems.getChildren().add(hbox);
            
            
        }
        catch(IOException e)
                {
                }
       
    }
   

}

    @FXML
    private void refresh(ActionEvent event) throws IOException {
         
     root = FXMLLoader.load(getClass().getResource("Relaiview.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();     
      
    }

    @FXML
    private void AddAdmin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader ();
    loader.setLocation(getClass().getResource("addRelai.fxml"));
    loader.load();
    
    Parent parent = loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(parent));
    stage.initStyle(StageStyle.UTILITY);
    stage.show();
    }
        
}
