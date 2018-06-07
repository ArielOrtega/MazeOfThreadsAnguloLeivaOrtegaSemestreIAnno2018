
package domain;


public class Score {
    private int pos;
    private String name;
    private String time;

    public Score(int pos, String name, String time) {
        this.pos = pos;
        this.name = name;
        this.time = time;
    }

    public int getPosition() {
        return pos;
    }

    public void setPosition(int position) {
        this.pos = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Score " + "\nposition #" + pos + ", name:" + name + ", time: " + time;
    }
    
    
}
