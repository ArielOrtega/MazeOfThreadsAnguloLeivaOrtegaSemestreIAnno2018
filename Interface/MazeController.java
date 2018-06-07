package Interface;

import domain.EnergyItem;
import domain.Entity;
import domain.FastCharacter;
import domain.FuriousCharacter;
import domain.SmartCharacter;
import domain.SynchronizedBuffer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MazeController implements Initializable, Runnable {

    private Thread thread;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private Image image;
    SynchronizedBuffer synchronizedB = new SynchronizedBuffer();
    //ChooseCharacterController control= new ChooseCharacterController();
    private boolean pause= false;

    //personajes
    private FastCharacter faC;
    private FuriousCharacter fuC;
    private SmartCharacter saC;
    
    //items
    EnergyItem item, item2;
    
    //equipos
    ArrayList<Entity> team1, team2;

    @FXML
    private Canvas canvas_maze;
    @FXML
    private Label lb_goBack;
    @FXML
    private Label lb_goBack11;
    @FXML
    private Label lb_pause;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        try {
            
            this.canvas = new Canvas(canvas_maze.getWidth(), canvas_maze.getHeight());
            this.image = new Image(new FileInputStream("src/assets/Background.png"));
            
            //Inicializar items
            this.item = new EnergyItem(300, 600);
            this.item.start();

            this.item2 = new EnergyItem(550, 670);
            this.item2.start();
            
            //Inicializar personajes 
            
            //Equipo1
            if (!ChooseCharacterController.getTeamArray1().isEmpty()) {
                for (int i = 0; i < ChooseCharacterController.getTeamArray1().size(); i++) {
                    System.out.println("Name " + ChooseCharacterController.getTeamArray1().get(i).getName());
                    ChooseCharacterController.getTeamArray1().get(i).start();
                }

            }
            
            //Equipo2
            if (!ChooseCharacterController.getTeamArray2().isEmpty()) {
                for (int i = 0; i < ChooseCharacterController.getTeamArray2().size(); i++) {
                    System.out.println("Name " + ChooseCharacterController.getTeamArray2().get(i).getName());
                    ChooseCharacterController.getTeamArray2().get(i).start();
                }

            }            

            this.thread = new Thread(this);
            this.thread.start();

        } catch (FileNotFoundException | BufferOverflowException ex) {
        }
        
    }

    @Override
    public void run() {
        while(!pause){

        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000 / fps;

        while (true) {
            try {
                start = System.nanoTime();
                elapsed = System.nanoTime() - start;
                wait = time - elapsed / 1000000;
                Thread.sleep(100);
                GraphicsContext gc = this.canvas_maze.getGraphicsContext2D();
                draw(gc);
            } catch (InterruptedException ex) {
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MazeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }

    private void draw(GraphicsContext gc) throws FileNotFoundException {

        //Dibujar laberinto
        for (int i = 0; i < synchronizedB.maze1.length; i++) {
            for (int j = 0; j < synchronizedB.maze1[0].length; j++) {

                if (synchronizedB.maze1[i][j] == 0 || synchronizedB.maze1[i][j] == 2 || synchronizedB.maze1[i][j] == 9) {
                    gc.setFill(Color.WHITE);

                } else {
                    gc.setFill(Color.CADETBLUE);
                }
                gc.fillRect(i * 55, j * 55, 55, 55);
            }
        }
        //Dibujar items
        gc.drawImage(this.item.getImage(), 60, this.item.getP1());
        gc.drawImage(this.item2.getImage(), this.item2.getP1(), 60);
        
        //Dibujar personajes
        //Equipo1
        if (!ChooseCharacterController.getTeamArray1().isEmpty()) {
            for (int i = 0; i < ChooseCharacterController.getTeamArray1().size(); i++) {
                gc.drawImage(ChooseCharacterController.getTeamArray1().get(i).getImage(), ChooseCharacterController.getTeamArray1().get(i).getX(), ChooseCharacterController.getTeamArray1().get(i).getY());
            }

        }
        
        //Equipo2
        if (!ChooseCharacterController.getTeamArray1().isEmpty()) {
            for (int i = 0; i < ChooseCharacterController.getTeamArray2().size(); i++) {
                gc.drawImage(ChooseCharacterController.getTeamArray2().get(i).getImage(), ChooseCharacterController.getTeamArray2().get(i).getX(), ChooseCharacterController.getTeamArray2().get(i).getY());
            }

        }

    }

    @FXML
    //Agregar, quitar paredes
    private void handleClick(MouseEvent event) {

        int xOffset = 0;
        int xIndex = (int) ((event.getX() - xOffset) / (770 / 14));
        int yOffset = 0;
        int yIndex = (int) ((event.getY() - yOffset) / (715 / 13));
        //System.out.println("(" + xIndex + " , " + yIndex + ")");

        int contador = 0;
        String result = "";
        if (synchronizedB.maze[xIndex][yIndex] == 1) {
            synchronizedB.maze[xIndex][yIndex] = 0;
            for (int i = 0; i < synchronizedB.maze.length; i++) {
                for (int j = 0; j < synchronizedB.maze[0].length; j++) {
                    
                    if(contador == 13){
                        result += "\n";
                        contador=0;
                    }
                    result += synchronizedB.maze[i][j];
                    contador++;
                }
                
            }
            System.out.println(result);
        } else {
            synchronizedB.maze[xIndex][yIndex] = 1;
        }
        
    }//end handleClick

    @FXML
    private void goBackAction(MouseEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/Interface/ChooseCharacter.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
    }

    @FXML
    private void pauseAction(MouseEvent event) {
        pause= true;
    }
}
