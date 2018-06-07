/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import domain.Score;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ScoreTableController implements Initializable {

    @FXML
    private TableView<Score> tbv_score;
    @FXML
    private TableColumn<Score, Integer> clm_pos;
    @FXML
    private TableColumn<Score, String> clm_name;
    @FXML
    private TableColumn<Score, String> clm_time;
    
    private ObservableList<Score> scores = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void FillTable(int pos, String name, String time){
        scores.add(new Score(pos, name, time));
        tbv_score.setItems(scores);
        clm_pos.setCellValueFactory(new PropertyValueFactory<Score, Integer>("pos"));
        clm_name.setCellValueFactory(new PropertyValueFactory<Score, String>("name"));
        clm_time.setCellValueFactory(new PropertyValueFactory<Score, String>("time"));
    }
    
}
