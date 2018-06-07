package Interface;

import domain.Entity;
import domain.FastCharacter;
import domain.FuriousCharacter;
import domain.SmartCharacter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    private int p1Quantity, p2Quantity, p1Type, p2Type;
    private String p1Name, p2Name;
    ToggleGroup mazeGroup, player1Group, player2Group;
    
    //Equipos
    public static ArrayList<Entity> team1, team2;   
    
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
    @FXML
    private Label lb_excepcion;
    
    public static ArrayList<Entity> getTeamArray1() {
        if (team1 == null) {
            team1 = new ArrayList<Entity>();
        }
        return team1;
    }
    
    public static ArrayList<Entity> getTeamArray2() {
        if (team2 == null) {
            team2 = new ArrayList<Entity>();
        }
        return team2;
    }    
   
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

        ObservableList<String> characterFast = FXCollections.observableArrayList("bird", "girl", "megaman", "scott");
        ObservableList<String> characterFurious = FXCollections.observableArrayList("bear", "jugger", "caveman", "skeleton");
        ObservableList<String> characterSmart = FXCollections.observableArrayList("braid", "detective", "mario", "teacher");

        //cbx_player1Charact.setValue("Braid");
        cbx_player1Charact.setItems(characterFast);
        cbx_player1Charact.setValue(cbx_player1Charact.getItems().get(0));

        //cbx_player2Charact.setValue("Kirby");
        cbx_player2Charact.setItems(characterFast);
        cbx_player2Charact.setValue(cbx_player2Charact.getItems().get(0));

        //muestra en el img view una imagen del personaje
        iv_player1.setImage(new Image("/assets/" + cbx_player1Charact.getItems().get(0) + "1.png"));
        //muestra en el img view una imagen del personaje
        iv_player2.setImage(new Image("/assets/" + cbx_player2Charact.getItems().get(0) + "1.png"));

        //si el radio button fast es seleccionado despliega personajes fast
        p1_fast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player1Charact.setItems(characterFast);
                cbx_player1Charact.setValue(cbx_player1Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player1.setImage(new Image("/assets/" + cbx_player1Charact.getItems().get(0) + "1.png"));
            }
        });

        //si el radio button fast es seleccionado despliega personajes furious
        p1_furious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player1Charact.setItems(characterFurious);
                cbx_player1Charact.setValue(cbx_player1Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player1.setImage(new Image("/assets/" + cbx_player1Charact.getItems().get(0) + "1.png"));
            }
        });

        //si el radio button fast es seleccionado despliega personajes smart
        p1_smart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player1Charact.setItems(characterSmart);
                cbx_player1Charact.setValue(cbx_player1Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player1.setImage(new Image("/assets/" + cbx_player1Charact.getItems().get(0) + "1.png"));
            }
        });

        //si el radio button fast es seleccionado despliega personajes fast
        p2_fast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player2Charact.setItems(characterFast);
                cbx_player2Charact.setValue(cbx_player2Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player2.setImage(new Image("/assets/" + cbx_player2Charact.getItems().get(0) + "1.png"));
            }
        });

        //si el radio button fast es seleccionado despliega personajes furious
        p2_furious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player2Charact.setItems(characterFurious);
                cbx_player2Charact.setValue(cbx_player2Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player2.setImage(new Image("/assets/" + cbx_player2Charact.getItems().get(0) + "1.png"));
            }
        });

        //si el radio button fast es seleccionado despliega personajes smart
        p2_smart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cbx_player2Charact.setItems(characterSmart);
                cbx_player2Charact.setValue(cbx_player2Charact.getItems().get(0));
                //muestra en el img view una imagen del personaje
                iv_player2.setImage(new Image("/assets/" + cbx_player2Charact.getItems().get(0) + "1.png"));
            }
        });

        //se despliega la imagen del personaje cuando elige en el comboBox
        cbx_player1Charact.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                try {
                    iv_player1.setImage(new Image("/assets/" + t1 + "1.png"));
                } catch (IllegalArgumentException e) {
                    iv_player1.setImage(new Image("/assets/" + cbx_player1Charact.getItems().get(0) + "1.png"));
                }
            }
        });

        //se despliega la imagen del personaje cuando elige en el comboBox
        cbx_player2Charact.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                try {
                    iv_player2.setImage(new Image("/assets/" + t1 + "1.png"));
                } catch (IllegalArgumentException e) {
                    iv_player2.setImage(new Image("/assets/" + cbx_player2Charact.getItems().get(0) + "1.png"));
                }
            }
        });
    }

    @FXML
    private void initGame(MouseEvent event) throws IOException {
        
        //Establecer personaje
        p1Name = cbx_player1Charact.getValue().toLowerCase().trim();
        p2Name = cbx_player2Charact.getValue().toLowerCase().trim();
        
        //Establecer cantidad
        p1Quantity = p1_qty.getValue();
        p2Quantity = p2_qty.getValue();
        
        //Establecer equipos
        team1= new ArrayList<>();
        for (int i = 0; i < p1Quantity; i++) {
           
            if (p1_fast.isSelected()) {
                   team1.add(new FastCharacter(600, 50, 0, p1Name));
              
            } else if (p1_furious.isSelected()) {
               team1.add(new FuriousCharacter(-50, 500, 0, p1Name));

            } else if (p1_smart.isSelected()) {
               team1.add(new SmartCharacter(450, 370, 2, p1Name));
            }
        }
        
        team2= new ArrayList<>();
        for (int i = 0; i < p2Quantity; i++) {
           
            if (p2_fast.isSelected()) {
               team2.add(new FastCharacter(600, 50, 0, p2Name));
              
            } else if (p2_furious.isSelected()) {
               team2.add(new FuriousCharacter(-50, 500, 0, p2Name));

            } else if (p2_smart.isSelected()) {
               team2.add(new SmartCharacter(450, 370, 2, p2Name));
            }
        }        
               
        
        //Establecer laberinto
        if (m_easy.isSelected()) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Interface/Maze.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();

        }else if (m_medium.isSelected()) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Interface/Maze2.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();

        }else if (m_hard.isSelected()) {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Interface/Maze3.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();

        }
    }
}
