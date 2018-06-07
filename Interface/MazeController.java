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

public class MazeController implements Initializable, Runnable {

    private Thread thread;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private Image image;
    SynchronizedBuffer synchronizedB = new SynchronizedBuffer();
    //ChooseCharacterController control= new ChooseCharacterController();
    private boolean pause= false; //controla la accion de pausa para que detenga o siga

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
    @FXML
    private Label lbl_begin;
    @FXML
    private Label lbl_chronometer;
    
    int miliseconds = 0;
    int seconds = 0;
    int minutes = 0;
    
    public static Timeline clock;
    //thread = new Thread(this);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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

        while (!pause) {
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

    private void draw(GraphicsContext gc) throws FileNotFoundException {

        //Dibujar laberinto
        for (int i = 0; i < SynchronizedBuffer.maze1.length; i++) {
            for (int j = 0; j < SynchronizedBuffer.maze1[0].length; j++) {

                switch (SynchronizedBuffer.maze1[i][j]) {
                    case 0:
                    case 2:
                        gc.setFill(Color.WHITE);
                        break;
                    case 3:
                    case 1:
                        gc.setFill(Color.CADETBLUE);
                        break;
                    case 10:
                        gc.setFill(Color.ROSYBROWN);
                        break;
                    default:
                        break;
                }
                gc.fillRect(i * 55, j * 55, 55, 55);
            }
        }
        //Dibujar items
        gc.drawImage(this.item.getImage(), 60, this.item.getP1());
        gc.drawImage(this.item2.getImage(), this.item2.getP1(), 60);
//        gc.drawImage(this.saC.getImage(), this.saC.getX(), this.saC.getY());
        
        //Dibujar personajes
        //Equipo1
        if (!ChooseCharacterController.getTeamArray1().isEmpty()) {
            for (int i = 0; i < ChooseCharacterController.getTeamArray1().size(); i++) {
                gc.drawImage(ChooseCharacterController.getTeamArray1().get(i).getImage(), ChooseCharacterController.getTeamArray1().get(i).getX(), ChooseCharacterController.getTeamArray1().get(i).getY());
            }

        }
        
        //Equipo2
        if (!ChooseCharacterController.getTeamArray2().isEmpty()) {
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
        switch (SynchronizedBuffer.maze1[xIndex][yIndex]) {
            case 0:
                SynchronizedBuffer.maze1[xIndex][yIndex] = 1;                
                break;
            case 1:
                SynchronizedBuffer.maze1[xIndex][yIndex] = 0;
                break;
            case 3:
                SynchronizedBuffer.maze1[xIndex][yIndex] = 3;
                break;            
            case 10:
                SynchronizedBuffer.maze1[xIndex][yIndex] = 10;
                break;
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
    private void pauseAction(MouseEvent event) throws InterruptedException {
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
            
            //Inicializar items
            this.item = new EnergyItem(300, 600);
            this.item.start();

            this.item2 = new EnergyItem(550, 670);
            this.item2.start();
            
//            this.saC = new SmartCharacter(0, 0, 0, "caveman");
//            this.saC.start();
            
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
        
        clock.playFromStart();
        lbl_begin.setDisable(true);
    }

    @FXML
    private void lbl_PauseOut(MouseEvent event) {
        lb_pause.setScaleX(1);
        lb_pause.setScaleY(1);
    }

    @FXML
    private void lbl_PauseIn(MouseEvent event) {
        lb_pause.setScaleX(1.5);
        lb_pause.setScaleY(1.5);
    }
}
