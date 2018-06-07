package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

public class FastCharacter extends Entity {

    private String name;
    SynchronizedBuffer synchronizedB;

    public FastCharacter(int x, int y, int num, String name) throws FileNotFoundException {
        super(x, y, num);
        this.name = name;
        this.synchronizedB = new SynchronizedBuffer();
//        this.mazeT = maze;
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

            while (true) {
                recorrer(1, 1);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(FastCharacter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(toString());

    }

    public boolean recorrer(int fil, int col) throws InterruptedException {

        Thread.sleep(150);
        int tam = SynchronizedBuffer.maze1.length;

        //comprobar si es solucion
        if (SynchronizedBuffer.maze1[fil][col] == 10) {
            System.out.println("gano");
            return true;
        }

        if (SynchronizedBuffer.maze1[fil][col] == 1 || SynchronizedBuffer.maze1[fil][col] == 2) { // si llegamos a una pared o al mismo punto,
            System.out.println("no se puede");
            return false; // entonces el laberinto no puede resolverse y termina.
        }

        super.setImage(sprite.get(1));
        super.setY(col * 55);
        super.setX(fil * 55);

        
        SynchronizedBuffer.maze1[fil][col] = 2;
        

//        if ((col + 1 <= tam - 1 && fil + 1 <= tam - 1 &&
//                fil - 1 >= 0 && col - 1 >= 0 &&
//                SynchronizedBuffer.maze1[fil][col + 1] == 0
//                && SynchronizedBuffer.maze1[fil + 1][col] == 0
//                && SynchronizedBuffer.maze1[fil - 1][col] == 0
//                && SynchronizedBuffer.maze1[fil][col - 1] == 0)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 4 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col + 1);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil + 1, col);
//                    break;
//                case 3:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil - 1, col);
//                    break;
//                case 4:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col - 1);
//                    break;
//            }
//
//        }
//        if ( (fil - 1 >= 0 && col - 1 >= 0 && fil + 1 <= tam - 1 &&
//                SynchronizedBuffer.maze1[fil][col + 1] == 1
//                && SynchronizedBuffer.maze1[fil + 1][col] == 0
//                && SynchronizedBuffer.maze1[fil - 1][col] == 0
//                && SynchronizedBuffer.maze1[fil][col - 1] == 0)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 3 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col - 1);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil + 1, col);
//                    break;
//                case 3:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil - 1, col);
//                    break;
//            }
//
//        }
//
//        if ((col + 1 <= tam - 1 && fil - 1 >= 0 && col - 1 >= 0 
//                && SynchronizedBuffer.maze1[fil][col + 1] == 0
//                && SynchronizedBuffer.maze1[fil + 1][col] == 1
//                && SynchronizedBuffer.maze1[fil - 1][col] == 0
//                && SynchronizedBuffer.maze1[fil][col - 1] == 0)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 3 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col + 1);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil - 1, col);
//                    break;
//                case 3:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col - 1);
//                    break;
//            }
//
//        }
//
//        if ((col + 1 <= tam - 1 && fil + 1 <= tam - 1 && col - 1 >= 0
//                && SynchronizedBuffer.maze1[fil][col + 1] == 0
//                && SynchronizedBuffer.maze1[fil + 1][col] == 0
//                && SynchronizedBuffer.maze1[fil - 1][col] == 1
//                && SynchronizedBuffer.maze1[fil][col - 1] == 0)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 3 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col + 1);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil + 1, col);
//                    break;
//                case 3:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col - 1);
//                    break;
//            }
//
//        }
//
//        if ((col + 1 <= tam - 1 && fil + 1 <= tam - 1 && fil - 1 >= 0 &&
//                SynchronizedBuffer.maze1[fil][col + 1] == 0
//                && SynchronizedBuffer.maze1[fil + 1][col] == 0
//                && SynchronizedBuffer.maze1[fil - 1][col] == 0
//                && SynchronizedBuffer.maze1[fil][col - 1] == 1)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 3 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col + 1);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil + 1, col);
//                    break;
//                case 3:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil - 1, col);
//                    break;
//            }
//
//        }

//        if ((fil - 1 >= 0 && col - 1 >= 0 && 
//                SynchronizedBuffer.maze1[fil][col + 1] == 1
//                && SynchronizedBuffer.maze1[fil + 1][col] == 1
//                && SynchronizedBuffer.maze1[fil - 1][col] == 0
//                && SynchronizedBuffer.maze1[fil][col - 1] == 0)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 2 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col - 1);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil - 1, col);
//                    break;
//            }
//
//        }
//
//        if ((col - 1 >= 0 && fil + 1 <= tam - 1 && SynchronizedBuffer.maze1[fil][col + 1] == 1
//                && SynchronizedBuffer.maze1[fil + 1][col] == 0
//                && SynchronizedBuffer.maze1[fil - 1][col] == 1
//                && SynchronizedBuffer.maze1[fil][col - 1] == 0)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 2 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col - 1);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil + 1, col);
//                    break;
//            }
//
//        }
//
//        if ((fil - 1 >= 0 && fil + 1 <= tam - 1 && SynchronizedBuffer.maze1[fil][col + 1] == 1
//                && SynchronizedBuffer.maze1[fil + 1][col] == 0
//                && SynchronizedBuffer.maze1[fil - 1][col] == 0
//                && SynchronizedBuffer.maze1[fil][col - 1] == 1)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 2 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil - 1, col);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil + 1, col);
//                    break;
//            }
//
//        }
//
//        if ((col + 1 <= tam - 1 && col - 1 >= 0 && SynchronizedBuffer.maze1[fil][col + 1] == 0
//                && SynchronizedBuffer.maze1[fil + 1][col] == 1
//                && SynchronizedBuffer.maze1[fil - 1][col] == 1
//                && SynchronizedBuffer.maze1[fil][col - 1] == 0)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 2 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col + 1);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col - 1);
//                    break;
//            }
//
//        }
//
//        if ((col + 1 <= tam - 1 && fil - 1 >= 0 && SynchronizedBuffer.maze1[fil][col + 1] == 0
//                && SynchronizedBuffer.maze1[fil + 1][col] == 1
//                && SynchronizedBuffer.maze1[fil - 1][col] == 0
//                && SynchronizedBuffer.maze1[fil][col - 1] == 1)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 2 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col + 1);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil - 1, col);
//                    break;
//            }
//
//        }
//
//        if ((col + 1 <= tam - 1 && fil + 1 <= tam - 1 
//                && SynchronizedBuffer.maze1[fil][col + 1] == 0
//                && SynchronizedBuffer.maze1[fil + 1][col] == 0
//                && SynchronizedBuffer.maze1[fil - 1][col] == 1
//                && SynchronizedBuffer.maze1[fil][col - 1] == 1)
//                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {
//
//            int random = (int) (Math.random() * 2 + 1);
//            switch (random) {
//                case 1:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil, col + 1);
//                    break;
//                case 2:
//                    System.out.println(col + ", " + fil);
//                    recorrer(fil + 1, col);
//                    break;
//            }
//
//        }
        if (col + 1 <= tam - 1 
                && SynchronizedBuffer.maze1[fil][col + 1] == 0                
                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {

            System.out.println(col + ", " + fil);
            
            recorrer(fil, col + 1);

        }

        if (fil + 1 <= tam - 1                 
                && SynchronizedBuffer.maze1[fil + 1][col] == 0               
                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {

            System.out.println(col + ", " + fil);
            
            recorrer(fil + 1, col);
            
        }

        if ( fil - 1 >= 0 
                && SynchronizedBuffer.maze1[fil - 1][col] == 0
                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {

            System.out.println(col + ", " + fil);
            
            recorrer(fil - 1, col);

        }

        if ((col - 1 >= 0
                && SynchronizedBuffer.maze1[fil][col - 1] == 0)
                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {

            System.out.println(col + ", " + fil);
            
            recorrer(fil, col - 1);

        }  
        
        if(SynchronizedBuffer.maze1[fil][col - 1] == 1 && SynchronizedBuffer.maze1[fil][col + 1] == 1
                && SynchronizedBuffer.maze1[fil + 1][col] == 1){
            
        }

        SynchronizedBuffer.maze1[fil][col] = 0;
        return false;

    }
}
