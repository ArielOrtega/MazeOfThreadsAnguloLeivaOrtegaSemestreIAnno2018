/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import domain.Entity;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML
    private ImageView iv_player1;
    @FXML
    private ImageView iv_player2;
    @FXML
    private AnchorPane cb_furious;
    @FXML
    private RadioButton p1_fast;
    @FXML
    private RadioButton p1_furious;
    @FXML
    private RadioButton p1_smart;
    @FXML
    private Spinner<Integer> p1_qty;
    @FXML
    private Spinner<Integer> p2_qty;
    @FXML
    private RadioButton p2_fast;
    @FXML
    private RadioButton p2_furious;
    @FXML
    private RadioButton p2_smart;
    @FXML
    private RadioButton m_easy;
    @FXML
    private RadioButton m_medium;
    @FXML
    private RadioButton m_hard;
    @FXML
    private ImageView iv_maze;
    @FXML
    private Label lb_start;

    //needed
    public static int p1Quantity, p2Quantity, p1Type, p2Type;
    public String p1Name, p2Name;
    ToggleGroup mazeGroup, player1Group, player2Group;
    @FXML
    private Label lb_excepcion;
    
    private Entity[] charactersPlayer1 = new Entity[5];
    private Entity[] charactersPlayer2 = new Entity[5];

    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // Maze group
        mazeGroup = new ToggleGroup();
        m_easy.setToggleGroup(mazeGroup);
        m_medium.setToggleGroup(mazeGroup);
        m_hard.setToggleGroup(mazeGroup);
        mazeGroup.selectToggle(m_easy);

        // player1 group
        player1Group = new ToggleGroup();
        p1_fast.setToggleGroup(player1Group);
        p1_furious.setToggleGroup(player1Group);
        p1_smart.setToggleGroup(player1Group);
        player1Group.selectToggle(p1_fast);

        // p2Name group
        player2Group = new ToggleGroup();
        p2_fast.setToggleGroup(player2Group);
        p2_furious.setToggleGroup(player2Group);
        p2_smart.setToggleGroup(player2Group);
        player2Group.selectToggle(p2_fast);
        
        
        
        //set Quantitys
        p1_qty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1));
        p2_qty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1));
        
        ObservableList<String> personajeFast = FXCollections.observableArrayList("bird", "girl", "megaman","scott");
        ObservableList<String> personajeFurious = FXCollections.observableArrayList("bear", "jugger", "caveman", "skeleton");
        ObservableList<String> personajeSmart = FXCollections.observableArrayList("braid", "detective", "mario","teacher");

        //cbx_player1Charact.setValue("Braid");
        cbx_player1Charact.setItems(personajeFast);
        cbx_player1Charact.setValue(cbx_player1Charact.getItems().get(0));

        //cbx_player2Charact.setValue("Kirby");
        cbx_player2Charact.setItems(personajeFast);
        cbx_player2Charact.setValue(cbx_player2Charact.getItems().get(0));
        
        //muestra en el img view una imagen del personaje
        iv_player1.setImage(new Image("/assets/"+cbx_player1Charact.getItems().get(0)+"1.png"));
        //muestra en el img view una imagen del personaje
        iv_player2.setImage(new Image("/assets/"+cbx_player2Charact.getItems().get(0)+"1.png"));
        
        //si el radio button fast es seleccionado despliega personajes fast
        p1_fast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player1Charact.setItems(personajeFast);
                cbx_player1Charact.setValue(cbx_player1Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player1.setImage(new Image("/assets/"+cbx_player1Charact.getItems().get(0)+"1.png"));
            }
        });
        
        //si el radio button fast es seleccionado despliega personajes furious
        p1_furious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player1Charact.setItems(personajeFurious);
                cbx_player1Charact.setValue(cbx_player1Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player1.setImage(new Image("/assets/"+cbx_player1Charact.getItems().get(0)+"1.png"));
            }
        });
        
        //si el radio button fast es seleccionado despliega personajes smart
        p1_smart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player1Charact.setItems(personajeSmart);
                cbx_player1Charact.setValue(cbx_player1Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player1.setImage(new Image("/assets/"+cbx_player1Charact.getItems().get(0)+"1.png"));
            }
        });
        
        //si el radio button fast es seleccionado despliega personajes fast
        p2_fast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player2Charact.setItems(personajeFast);
                cbx_player2Charact.setValue(cbx_player2Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player2.setImage(new Image("/assets/"+cbx_player2Charact.getItems().get(0)+"1.png"));
            }
        });
        
        //si el radio button fast es seleccionado despliega personajes furious
        p2_furious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player2Charact.setItems(personajeFurious);
                cbx_player2Charact.setValue(cbx_player2Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player2.setImage(new Image("/assets/"+cbx_player2Charact.getItems().get(0)+"1.png"));
            }
        });
        
        //si el radio button fast es seleccionado despliega personajes smart
        p2_smart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player2Charact.setItems(personajeSmart);
                cbx_player2Charact.setValue(cbx_player2Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player2.setImage(new Image("/assets/"+cbx_player2Charact.getItems().get(0)+"1.png"));
            }
        });
        
        //se despliega la imagen del personaje cuando elige en el comboBox
        cbx_player1Charact.valueProperty().addListener(new ChangeListener<String>(){
            @Override public void changed(ObservableValue ov, String t, String t1) {
                try{
                    iv_player1.setImage(new Image("/assets/"+t1+"1.png"));
                }catch(IllegalArgumentException e){
                    iv_player1.setImage(new Image("/assets/"+cbx_player1Charact.getItems().get(0)+"1.png"));
                }
        }
        });
        
        //se despliega la imagen del personaje cuando elige en el comboBox
        cbx_player2Charact.valueProperty().addListener(new ChangeListener<String>(){
            @Override public void changed(ObservableValue ov, String t, String t1) {
            try{
                iv_player2.setImage(new Image("/assets/"+t1+"1.png"));
            }catch(IllegalArgumentException e){
                iv_player2.setImage(new Image("/assets/"+cbx_player2Charact.getItems().get(0)+"1.png"));
            }
        }
        });
    }

    @FXML
    private void initGame(MouseEvent event) throws IOException {

        if (!(mazeGroup.getSelectedToggle() == null || player1Group.getSelectedToggle() == null || player2Group.getSelectedToggle() == null)) {

            //Set maze
            if (m_easy.isSelected()) {
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Interface/Maze.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();

            }

            if (m_medium.isSelected()) {
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Interface/Maze2.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();

            }

            if (m_hard.isSelected()) {
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Interface/Maze3.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();

            }

            //Set 1st player
            p1Quantity = p1_qty.getValue();
            p1Name = cbx_player1Charact.getValue().trim().toLowerCase();

            //Set 2nd player
            p2Quantity = p2_qty.getValue();
            p2Name = cbx_player2Charact.getValue().trim().toLowerCase();

        } else {
            lb_excepcion.setText("Must complete all spaces!.");
        }

    }

}
