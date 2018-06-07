
package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;


public class SmartCharacter extends Entity{

    public SmartCharacter(int x, int y, int num, String name) throws FileNotFoundException {
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
        ArrayList<Image> sprite = super.getSprite();
        super.setImage(sprite.get(1));
        int newX = 0;
        int imageNum = 1;
        while (true) {
            try {
                super.setX(newX+=5);
                super.setImage(sprite.get(imageNum++));
                Thread.sleep(50);
//                super.setX(newX++);
//                Thread.sleep(500);
//                super.setX(300);
//                Thread.sleep(500); //800
//                super.setX(400);
                if(newX>800){
                    newX=0;
                }
                if(imageNum==4){
                    imageNum=1;
                }
            } 
            catch (InterruptedException ex) {}
        }
    }
}
