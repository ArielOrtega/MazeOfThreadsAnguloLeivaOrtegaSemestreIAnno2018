
package domain;


public abstract class Entity {
    private String name;
    private int energy;
    private boolean ate;

    public Entity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Character" + "\nName:" + name + "\nEnergy: " + energy + "\nAte: " + ate;
    }
    
    
}
