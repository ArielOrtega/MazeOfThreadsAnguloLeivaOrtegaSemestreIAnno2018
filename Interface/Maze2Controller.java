package Interface;

import domain.EnergyItem;
import domain.FastCharacter;
import domain.FuriousCharacter;
import domain.SmartCharacter;
import domain.SynchronizedBuffer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.BufferOverflowException;
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

public class Maze2Controller implements Initializable, Runnable {

    private Thread thread;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private Image image;
    SynchronizedBuffer synchronizedB = new SynchronizedBuffer();

    //personajes
    private FastCharacter faC;
    private FuriousCharacter fuC;
    private SmartCharacter saC;
    
    //items
    EnergyItem item, item2, item3;
    
    @FXML
    private Canvas canvas_maze;
    @FXML
    private Label lb_goBack;
    @FXML
    private Label lb_goBack1;
    @FXML
    private Label lb_goBack11;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            this.canvas = new Canvas(canvas_maze.getWidth(), canvas_maze.getHeight());
            this.image = new Image(new FileInputStream("src/assets/Background.png"));

            //Inicializar item
            this.item = new EnergyItem(180, 280);
            this.item.start();

            this.item2 = new EnergyItem(170, 390);
            this.item2.start();
            
            this.item3 = new EnergyItem(65, 300);
            this.item3.start();            
            
            //Inicializar personajes
            this.faC = new FastCharacter(600, 50, 0, "braid");
            this.faC.start();

            this.fuC = new FuriousCharacter(-50, 500, 0);
            this.fuC.start();

            this.saC = new SmartCharacter(450, 370, 2);
            this.saC.start();

            this.thread = new Thread(this);
            this.thread.start();

        } catch (FileNotFoundException | BufferOverflowException ex) {
        }
    }

    @Override
    public void run() {

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
                Logger.getLogger(Maze2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void draw(GraphicsContext gc) throws FileNotFoundException {
        
        //Dibujar laberinto
        for (int i = 0; i < synchronizedB.maze2.length; i++) {
            for (int j = 0; j < synchronizedB.maze2[0].length; j++) {

                if (synchronizedB.maze2[i][j] == 0 || synchronizedB.maze2[i][j] == 2 || synchronizedB.maze2[i][j] == 9) {
                    gc.setFill(Color.WHITE);

                } else {
                    gc.setFill(Color.CADETBLUE);
                }
                gc.fillRect(i * 55, j * 55, 55, 55);
            }
        }
        //Dibujar items
        gc.drawImage(this.item.getImage(),this.item.getP1(), 60);
        gc.drawImage(this.item2.getImage(), 447, this.item2.getP1());
        gc.drawImage(this.item3.getImage(),this.item3.getP1(), 610);        
        
        //Dibujar personajes
        gc.drawImage(this.faC.getImage(), this.faC.getX(), this.faC.getY());
        gc.drawImage(this.fuC.getImage(), this.fuC.getX(), this.fuC.getY());
        gc.drawImage(this.saC.getImage(), this.saC.getX(), this.saC.getY());

    }

    @FXML
    //Agregar, quitar paredes
    private void handleClick(MouseEvent event) {

        int xOffset = 0;
        int xIndex = (int) ((event.getX() - xOffset) / (770 / 14));
        int yOffset = 0;
        int yIndex = (int) ((event.getY() - yOffset) / (715 / 13));
        //System.out.println("(" + xIndex + " , " + yIndex + ")");

        if (synchronizedB.maze[xIndex][yIndex] == 1) {
            synchronizedB.maze[xIndex][yIndex] = 0;
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
}
