/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author Pablo
 */
public class MainClass extends Application{

    /**
     * @param args the command line arguments
     */
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
