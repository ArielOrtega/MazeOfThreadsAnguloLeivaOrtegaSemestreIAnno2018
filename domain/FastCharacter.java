package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

public class FastCharacter extends Entity {

    private final String name;
    int contador = 0;

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
        } catch (InterruptedException ex) {
            Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean walking(int fil, int col) throws InterruptedException {

        
        Thread.sleep(150);

        int tam = SynchronizedBuffer.maze1.length;

        //comprobar si es solucion
        if (SynchronizedBuffer.maze1[fil][col] == 10) {
            return true;
        }

        if (SynchronizedBuffer.maze1[fil][col] == 1 || SynchronizedBuffer.maze1[fil][col] == 2
                || SynchronizedBuffer.maze1[fil][col] == 3) { // si llegamos a una pared o al mismo punto,
            System.out.println("no se puede");
            return false; // entonces el laberinto no puede resolverse y termina.
        }

        if (contador == 3) {
            contador = 1;
        }
        super.setImage(sprite.get(contador++));
        for (int i = 1; i <= 55; i++) {
            super.setY(col * i);
            super.setX(fil * i);
        }

        SynchronizedBuffer.maze1[fil][col] = 2;

        if (col + 1 <= tam - 1
                && (SynchronizedBuffer.maze1[fil][col + 1] == 0
                || SynchronizedBuffer.maze1[fil][col + 1] == 10)) {
            
            walking(fil, col + 1);

        }

        if (fil + 1 <= tam - 1
                && (SynchronizedBuffer.maze1[fil + 1][col] == 0
                || SynchronizedBuffer.maze1[fil + 1][col] == 10)) {

            walking(fil + 1, col);

        }

        if (fil - 1 >= 0
                && (SynchronizedBuffer.maze1[fil - 1][col] == 0
                || SynchronizedBuffer.maze1[fil - 1][col] == 10)) {

            walking(fil - 1, col);

        }

        if ((col - 1 >= 0
                && (SynchronizedBuffer.maze1[fil][col - 1] == 0)
                || SynchronizedBuffer.maze1[fil][col - 1] == 10)) {

            walking(fil, col - 1);

        }

        SynchronizedBuffer.maze1[fil][col] = 0;
        Thread.sleep(150);
        if (contador == 3) {
            contador = 1;
        }
        Thread.sleep(150);
        super.setImage(sprite.get(contador++));
        for (int i = 1; i <= 55; i++) {
            super.setY(col * i);
            super.setX(fil * i);
        }

        return false;

    }
}
