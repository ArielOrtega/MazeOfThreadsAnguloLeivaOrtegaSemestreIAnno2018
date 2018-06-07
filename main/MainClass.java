package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainClass extends Application{

    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/Interface/Maze.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/Interface/Maze2.fxml"));
       /// Parent root = FXMLLoader.load(getClass().getResource("/Interface/Maze3.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("CLOSING");            
            }
        });
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
    
}
