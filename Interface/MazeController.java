/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Maria
 */
public class MazeController implements Initializable {

    @FXML
    private GridPane gp_maze;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    //Agregar y quitar paredes
    private void handleClick(MouseEvent event) {

        if (event.getTarget() instanceof Rectangle) {
            Rectangle cell = (Rectangle) event.getTarget();

            if (cell.getFill() == Color.WHITE) {
                cell.setStyle("-fx-fill: #1e92ffb2;"
                        + "-fx-stroke: #217bd0;");

            } else {
                cell.setFill(Color.WHITE);
                cell.setStroke(Color.WHITE);

                

            }

        }//end if
        else if (event.getTarget() instanceof GridPane) {
            Rectangle cell = new Rectangle();
            cell.setStyle("-fx-fill: #1e92ffb2;"
                    + "-fx-stroke: #217bd0;");

            cell.setWidth(58);
            cell.setHeight(53);

            int xOffset = 0;
            int xIndex = (int) ((event.getX() - xOffset) / (686 / 12));
            int yOffset = 0;
            int yIndex = (int) ((event.getY() - yOffset) / (631 / 12));

            gp_maze.add(cell, xIndex, yIndex);
        }

    }//end handleClick
    
    
}
