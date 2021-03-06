package Interface;

import static Interface.Maze2Controller.clock;
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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

public class Maze3Controller implements Initializable, Runnable {

    private Thread thread;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private Image image;
    SynchronizedBuffer synchronizedB = new SynchronizedBuffer();
    private boolean pause= false; //controla la accion de pausa para que detenga o siga

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
    @FXML
    private Label lbl_chronometer;
    @FXML
    private Label lbl_begin;
    
    int miliseconds = 0;
    int seconds = 0;
    int minutes = 0;
    
    static Timeline clock;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            
//            this.canvas = new Canvas(canvas_maze.getWidth(), canvas_maze.getHeight());
//            this.image = new Image(new FileInputStream("src/assets/Background.png"));
//            
//            //Inicializar item
//            this.item = new EnergyItem(180, 280);
//            this.item.start();
//
//            this.item2 = new EnergyItem(165, 525);
//            this.item2.start();
//            
//            this.item3 = new EnergyItem(350, 620);
//            this.item3.start();  
//            
//            //Inicializar personajes
//            //Equipo1
//            if (!ChooseCharacterController.getTeamArray1().isEmpty()) {
//                for (int i = 0; i < ChooseCharacterController.getTeamArray1().size(); i++) {
//                    System.out.println("Name " + ChooseCharacterController.getTeamArray1().get(i).getName());
//                    ChooseCharacterController.getTeamArray1().get(i).start();
//                }
//
//            }
//            
//            //Equipo2
//            if (!ChooseCharacterController.getTeamArray2().isEmpty()) {
//                for (int i = 0; i < ChooseCharacterController.getTeamArray2().size(); i++) {
//                    System.out.println("Name " + ChooseCharacterController.getTeamArray2().get(i).getName());
//                    ChooseCharacterController.getTeamArray2().get(i).start();
//                }
//
//            } 
//
//            this.thread = new Thread(this);
//            this.thread.start();
//
//        } catch (FileNotFoundException | BufferOverflowException ex) {
//        }

        //timeLine que ayuda a manejar el cronometro
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
        //condicionales para incrementar milisegundos, segundos y minutos respectivamente
        if(seconds==60){
           seconds = 0;
           minutes++;
        }
        if(miliseconds==99){
            miliseconds = 0;
            seconds++;
        }
        //condicionales para dar el formato de dos digitos
        if(minutes<10&&seconds<10){
            lbl_chronometer.setText("0"+minutes+":0"+seconds+":"+miliseconds);
        }else if(seconds<10){
            lbl_chronometer.setText(minutes+":0"+seconds+":"+miliseconds);
        }else{
            lbl_chronometer.setText("0"+minutes+":"+seconds+":"+miliseconds);
        }
        //se incrementan los milisegundos
        miliseconds++;
    
        }),
                new KeyFrame(Duration.seconds(0.01))
        );
        clock.setCycleCount(Animation.INDEFINITE);
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
                Logger.getLogger(Maze3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void draw(GraphicsContext gc) throws FileNotFoundException {
        
        //Dibujar laberinto
        for (int i = 0; i < synchronizedB.maze.length; i++) {
            for (int j = 0; j < synchronizedB.maze[0].length; j++) {

                if (synchronizedB.maze[i][j] == 0 || synchronizedB.maze[i][j] == 2 || synchronizedB.maze[i][j] == 9) {
                    gc.setFill(Color.WHITE);

                } 
                else {
                    gc.setFill(Color.CORNFLOWERBLUE);
                }
                gc.fillRect(i * 55, j * 55, 55, 55);
            }
        }
        
        //Dibujar item
        gc.drawImage(this.item.getImage(),this.item.getP1(), 60);
        gc.drawImage(this.item2.getImage(), 55, this.item2.getP1());
        gc.drawImage(this.item3.getImage(),this.item3.getP1(), 390);      
        
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
        int xIndex = (int) ((event.getX() - xOffset) / (990 / 18));
        int yOffset = 0;
        int yIndex = (int) ((event.getY() - yOffset) / (715 / 13));
        System.out.println("(" + xIndex + " , " + yIndex + ")");

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

    @FXML
    private void lbl_pauseOut(MouseEvent event) {
        lb_goBack1.setScaleX(1);
        lb_goBack1.setScaleY(1);
    }

    @FXML
    private void lbl_pauseIn(MouseEvent event) {
        lb_goBack1.setScaleX(1.5);
        lb_goBack1.setScaleY(1.5);
    }

    @FXML
    private void lbl_pauseClicked(MouseEvent event) {
        if(!pause){
            clock.pause();
            pause= true;
        }else{
            pause = false;
            clock.play();
        }
    }

    @FXML
    private void lbl_beginOut(MouseEvent event) {
        lbl_begin.setScaleX(1);
        lbl_begin.setScaleY(1);
    }

    @FXML
    private void lbl_beginIn(MouseEvent event) {
        lbl_begin.setScaleX(1.5);
        lbl_begin.setScaleY(1.5);
        
    }

    @FXML
    private void lbl_beginClicked(MouseEvent event) {
        try {
            
            this.canvas = new Canvas(canvas_maze.getWidth(), canvas_maze.getHeight());
            this.image = new Image(new FileInputStream("src/assets/Background.png"));
            
            //Inicializar item
            this.item = new EnergyItem(180, 280);
            this.item.start();

            this.item2 = new EnergyItem(165, 525);
            this.item2.start();
            
            this.item3 = new EnergyItem(350, 620);
            this.item3.start();  
            
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
        
        //inicia el cronometro y se desabilita el label
        clock.playFromStart();
        lbl_begin.setDisable(true);
    }
}
