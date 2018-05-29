/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import domain.FastCharacter;
import domain.FuriousCharacter;
import domain.SmartCharacter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.BufferOverflowException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maria
 */
public class MazeController implements Initializable, Runnable {
    
    private Thread thread;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private Image image;
    
    //personajes
    private FastCharacter faC;
    private FuriousCharacter fuC;
    private SmartCharacter saC;
    int[][] matriz = {{0,0,0,1,0,0,0,0,0,0,0,0}
                     ,{0,1,0,1,1,1,0,1,1,1,1,0}
                     ,{0,0,0,0,0,1,0,1,0,0,0,0}
                     ,{0,1,1,1,0,0,0,1,0,1,1,1}
                     ,{0,1,0,1,1,1,0,0,0,1,0,0}
                     ,{0,1,0,0,0,1,1,1,1,1,0,1}
                     ,{0,1,1,1,0,0,0,0,0,0,0,0}
                     ,{0,0,0,1,0,1,1,1,0,1,1,1}
                     ,{1,1,0,1,0,1,0,1,0,1,0,0}
                     ,{0,0,0,1,0,1,0,1,0,1,1,0}
                     ,{0,1,1,1,0,1,0,1,0,0,0,0}
                     ,{0,0,0,0,0,0,0,1,0,1,1,0}};
    
    @FXML
    private Canvas canvas_maze;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                try {
                    
//            int[][] matriz = {{0,0,0,0,1,1,1,0,1,0,1,1}
//                             ,{1,0,1,1,0,0,0,0,0,0,0,1}
//                             ,{1,0,0,1,1,1,0,0,1,1,0,1}
//                             ,{0,1,0,0,1,1,1,0,0,1,0,1}
//                             ,{0,1,1,0,0,1,1,0,0,1,0,1}
//                             ,{0,1,0,0,0,0,1,0,0,1,0,1}
//                             ,{1,1,0,0,1,0,1,0,0,1,0,1}
//                             ,{1,1,0,0,1,0,0,0,0,1,0,1}
//                             ,{1,1,0,0,1,1,1,0,0,1,0,1}
//                             ,{1,1,0,0,1,1,1,0,0,0,0,0}
//                             ,{1,0,0,0,1,1,1,0,0,1,1,1}
//                             ,{0,1,0,0,1,1,1,0,0,1,0,1}};
                    
            this.pane = new Pane();
            this.scene = new Scene(this.pane, canvas_maze.getWidth(), canvas_maze.getHeight());
            this.canvas = new Canvas(canvas_maze.getWidth(), canvas_maze.getHeight());
            this.image = new Image(new FileInputStream("src/assets/Background.png"));
            
            this.pane.getChildren().add(this.canvas);
            this.pane.setStyle("-fx-background: BLACK");
            


            //primaryStage.setScene(this.scene);
            //p_maze.add(this.pane,0,0);
//            Scene home_page_scene = this.scene;
//            //Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Stage app_stage = new Stage();
//                app_stage.hide(); //optional
//                app_stage.setScene(home_page_scene);
//                app_stage.show(); 
            
            //Inicializamos cada hilo (personaje) y lo iniciamos
            this.faC = new FastCharacter(600, 50, 0);
            this.faC.start();
            
            this.fuC = new FuriousCharacter(-50, 500, 0);
            this.fuC.start();
            
            this.saC = new SmartCharacter(450, 370, 2);
            this.saC.start();
            
            this.thread = new Thread(this);
            this.thread.start();

        } 
        catch (FileNotFoundException ex){}
        catch (BufferOverflowException ex){}
    }    

    private void handleClick(MouseEvent event) {
//
//        if (event.getTarget() instanceof Rectangle) {
//            Rectangle cell = (Rectangle) event.getTarget();
//
//            if (cell.getFill() == Color.WHITE) {
//                cell.setStyle("-fx-fill: #1e92ffb2;"
//                        + "-fx-stroke: #217bd0;");
//                System.out.println("1");
//
//            } else {
//                cell.setFill(Color.WHITE);
//                cell.setStroke(Color.WHITE);
//                //System.out.println("0");
//
//                
//
//            }
//
//        }//end if
//        else if (event.getTarget() instanceof GridPane) {
//            Rectangle cell = new Rectangle();
//            cell.setStyle("-fx-fill: #1e92ffb2;"
//                    + "-fx-stroke: #217bd0;");
//
//            cell.setWidth(58);
//            cell.setHeight(53);
//            
//
//            int xOffset = 0;
//            int xIndex = (int) ((event.getX() - xOffset) / (686 / 12));
//            int yOffset = 0;
//            int yIndex = (int) ((event.getY() - yOffset) / (631 / 12));
//
//            gp_maze.add(cell, xIndex, yIndex);
//            System.out.println("0");
//        }

    }//end handleClick

    @Override
    public void run() {
        
        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000/fps;

        while(true){
            try {
                start=System.nanoTime();
                elapsed=System.nanoTime()-start;                    
                wait = time-elapsed/1000000;
                Thread.sleep(100);
                GraphicsContext gc = this.canvas_maze.getGraphicsContext2D();
                draw(gc);
            } 
            catch (InterruptedException ex) {}
        }
    }
    
        private void draw(GraphicsContext gc){
        
        for (int i = 0;i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if(matriz[i][j]==0){
                    gc.setFill(Color.ANTIQUEWHITE);
                }else{
                    gc.setFill(Color.CADETBLUE);
                }
                //gc.rect(j, i, 55, 55);
                gc.fillRect(j*55, i*55, 55, 55);
            }
        }
        
        //gc.clearRect(0, 0, canvas_maze.getWidth(), canvas_maze.getHeight());
        //gc.drawImage(this.image, 0, 0);
        gc.drawImage(this.faC.getImage(), this.faC.getX(), this.faC.getY());
        gc.drawImage(this.fuC.getImage(), this.fuC.getX(), this.fuC.getY());
        gc.drawImage(this.saC.getImage(), this.saC.getX(), this.saC.getY());
//            for (int i = 0; i < canvas_maze.getWidth(); i+=55) {
//                gc.strokeLine(0, i, canvas_maze.getWidth(), i);
//            }
//            
//            for (int i = 0; i < canvas_maze.getHeight(); i+=55) {
//                gc.strokeLine(i, 0, i, canvas_maze.getHeight());
//            }
            
    }

}
