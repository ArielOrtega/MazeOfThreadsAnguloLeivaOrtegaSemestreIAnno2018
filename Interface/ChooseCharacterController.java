/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class ChooseCharacterController implements Initializable {
    @FXML
    private ComboBox<String> cbx_player1Charact;
    @FXML
    private ComboBox<String> cbx_player2Charact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> personajeFast = FXCollections.observableArrayList("Adventurer","Car","Sonic");
        ObservableList<String> personajeFurious = FXCollections.observableArrayList("Hulk","Axe","Donkey");
        ObservableList<String> personajeSmart = FXCollections.observableArrayList("Kirby","Dinosaur","Batman");
        
        cbx_player1Charact.setValue("Adventurer");
        cbx_player1Charact.setItems(personajeFast);
        
        cbx_player2Charact.setValue("Kirby");
        cbx_player2Charact.setItems(personajeSmart);
    }    
    
}
