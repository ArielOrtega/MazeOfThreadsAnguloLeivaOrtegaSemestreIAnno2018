
package domain;

import java.util.ArrayList;
import javafx.scene.image.Image;


public class Entity extends Thread{
    
    private String imgName;
    private int energy;
    private boolean ate;
    private int x;
    private int y;
    private int imgNum;
    private Image image;
    ArrayList<Image> sprite;

    public Entity(int x, int y, int imgNum, String imgName) {
        this.x = x;
        this.y = y;
        this.imgNum = imgNum;
        this.imgName= imgName;
        this.sprite = new ArrayList<Image>();
    }
    
    public Entity() {
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
    
    public int getEnergy() {
        return energy;
    }
 
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isAte() {
        return ate;
    }

    public void setAte(boolean ate) {
        this.ate = ate;
    }  

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getImgNum() {
        return imgNum;
    }

    public void setImgNum(int imgNum) {
        this.imgNum = imgNum;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ArrayList<Image> getSprite() {
        return sprite;
    }

    public void setSprite(ArrayList<Image> sprite) {
        this.sprite = sprite;
    }
    
    
}
