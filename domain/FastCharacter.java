package domain;

import Interface.MazeController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FastCharacter extends Entity {

    private final String name;
    int contador = 0;
    SynchronizedBuffer syncBff = new SynchronizedBuffer();

    public FastCharacter(int x, int y, int num, String name) throws FileNotFoundException {
        super(x, y, num, name);
        this.name = name;
        setSprite();
    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 1; i < 5; i++) {
            sprite.add(new Image(new FileInputStream("src/assets/" + name + "" + i + ".png")));
        }
        super.setSprite(sprite);
    }

    @Override
    public void run() {
        try {

            while (!walking(1, 1)) {

            }
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean walking(int fil, int col) throws InterruptedException, IOException {

        if (syncBff.maze1[fil][col] == 2 
                || syncBff.maze1[fil][col-1]==2
                || syncBff.maze1[fil][col+1]==2
                || syncBff.maze1[fil+1][col]==2
                || syncBff.maze1[fil-1][col]==2) {
            syncBff.maze1[fil][col] = 0;
            Thread.sleep(150);
            super.setImage(sprite.get(1));
            for (int i = 1; i <= 55; i++) {
                super.setY(col * i);
                super.setX(fil * i);
            }
        }
        
        
        Thread.sleep(10);//150

        int tam = syncBff.maze1.length;

        //comprobar si es solucion
        if (syncBff.maze1[fil][col] == 10) {
            return true;
        }

        if (syncBff.maze1[fil][col] == 1 || syncBff.maze1[fil][col] == 2
                || syncBff.maze1[fil][col] == 3) { // si llegamos a una pared o al mismo punto,
            System.out.println("no se puede");
            return false; // entonces el laberinto no puede resolverse y termina.
        }

        if (contador == 3) {
            contador = 1;
        }
        super.setImage(sprite.get(contador++));
        for (int i = 1; i <= 55; i++) {
            super.setY(col *i);
            super.setX(fil *i);
        }

        syncBff.maze1[fil][col] = 2;

        if (col + 1 <= tam - 1
                && (syncBff.maze1[fil][col + 1] == 0
                || syncBff.maze1[fil][col + 1] == 10)) {
            
            walking(fil, col + 1);

        }

        if (fil + 1 <= tam - 1
                && (syncBff.maze1[fil + 1][col] == 0
                || syncBff.maze1[fil + 1][col] == 10)) {

            walking(fil + 1, col);

        }

        if (fil - 1 >= 0
                && (syncBff.maze1[fil - 1][col] == 0
                || syncBff.maze1[fil - 1][col] == 10)) {

            walking(fil - 1, col);

        }

        if ((col - 1 >= 0
                && (syncBff.maze1[fil][col - 1] == 0)
                || syncBff.maze1[fil][col - 1] == 10)) {

            walking(fil, col - 1);

        }
        if(syncBff.maze1[fil-1][col] == 10){
            System.out.println("GANO!");
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("ScoreTable.fxml"));
            Parent root1= (Parent)fxmlLoader.load();
 
            //Creame un nuevo Stage o para que nos entendamos una nueva ventana windows vacía
            Stage stage= new Stage();

            //Y ahora dentro del Stage me metes la escena que anteriormente hemos leído y metido en root1
            stage.setScene(new Scene(root1));

            // Y ahora le digo que me muestres el stage
            stage.show();
            this.interrupt();
            return true;
        }

        syncBff.maze1[fil][col] = 0;
        Thread.sleep(50);
        if (contador == 3) {
            contador = 1;
        }
        Thread.sleep(10);//150
        super.setImage(sprite.get(contador++));
        for (int i = 1; i <= 55; i++) {
            super.setY(col *i);
            super.setX(fil *i);
        }

        return false;

    }
    
}
