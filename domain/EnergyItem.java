/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class EnergyItem extends Thread {

   private int p1,p2;
   private Image bar;

    public EnergyItem(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
  
    public Image getImage() throws FileNotFoundException {
        this.bar = new Image(new FileInputStream("src/assets/bar.png"));
        return this.bar;
    }

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }
     
    @Override
    public void run() {

        int init = getP1(), end = getP2();
        long random= (long) Math.floor(Math.random()*(5-20+1)+20);
        
        while (true) {
            try {
                for (int i = init; i <= end; i += 2) {
                    Thread.sleep(random);
                    this.setP1(i);

                    if (i == getP2()) {
                       Thread.sleep(random);
                       
                        for (int j = end; j >= init; j -= 2) {
                            Thread.sleep(random);
                            this.setP1(j);
                        }
                    }
                }//end for
            } catch (InterruptedException ex) {
            }
        }
    }
}

