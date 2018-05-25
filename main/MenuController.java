/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class MenuController implements Initializable {
    @FXML
    private Label lbl_nGame;
    @FXML
    private Label lbl_options;
    @FXML
    private Label lbl_exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void lblNewGame(MouseEvent event) {
        //lbl_nGame.setFont(new Font("Open Sans",,45));
        lbl_nGame.setScaleX(1.3);
        lbl_nGame.setScaleY(1.3);
    }

    @FXML
    private void lblNewGameOut(MouseEvent event) {
        //lbl_nGame.setFont(new Font("Open Sans",37));
        lbl_nGame.setScaleX(1);
        lbl_nGame.setScaleY(1);
    }

    @FXML
    private void lblOptionsOut(MouseEvent event) {
        lbl_options.setScaleX(1);
        lbl_options.setScaleY(1);
    }

    @FXML
    private void lblOptions(MouseEvent event) {
        lbl_options.setScaleX(1.3);
        lbl_options.setScaleY(1.3);
    }

    @FXML
    private void lblExitOut(MouseEvent event) {
        lbl_exit.setScaleX(1);
        lbl_exit.setScaleY(1);
    }

    @FXML
    private void lblExit(MouseEvent event) {
        lbl_exit.setScaleX(1.3);
        lbl_exit.setScaleY(1.3);
    }

    @FXML
    private void newGameStart(MouseEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Interface/ChooseCharacter.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            
    }
    
}
