
package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;


public class FuriousCharacter extends Entity{

    public FuriousCharacter(int x, int y, int num, String name) throws FileNotFoundException {
        super(x,y,num,name);
        setSprite();
    }
    
    public void setSprite() throws FileNotFoundException{
        ArrayList<Image> sprite = super.getSprite();
        for(int i = 1; i < 5; i++){
            sprite.add(new Image(new FileInputStream("src/assets/"+super.getImgName()+""+i+".png")));
        }
        super.setSprite(sprite);
    }
    
     @Override
    public void run() {
        try {

            while (true) {
                walking(1, 1);
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
        
         if (col + 1 <= tam - 1 
                && SynchronizedBuffer.maze1[fil][col + 1] == 0                
                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {

            System.out.println(col + ", " + fil);
            
            walking(fil, col + 1);

        }

        if (fil + 1 <= tam - 1                 
                && SynchronizedBuffer.maze1[fil + 1][col] == 0               
                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {

            System.out.println(col + ", " + fil);
            
            walking(fil + 1, col);
            
        }

        if ( fil - 1 >= 0 
                && SynchronizedBuffer.maze1[fil - 1][col] == 0
                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {

            System.out.println(col + ", " + fil);
            
            walking(fil - 1, col);

        }

        if ((col - 1 >= 0
                && SynchronizedBuffer.maze1[fil][col - 1] == 0)
                || SynchronizedBuffer.maze1[fil][col + 1] == 10) {

            System.out.println(col + ", " + fil);
            
            walking(fil, col - 1);

        }                 

        SynchronizedBuffer.maze1[fil][col] = 0;
        Thread.sleep(150);
        super.setImage(sprite.get(1));
        super.setY(col * 55);
        super.setX(fil * 55);
        return false;
        
    }

}
