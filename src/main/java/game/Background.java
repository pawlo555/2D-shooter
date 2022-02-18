package game;

public class Background {

    public String getPathToJPG() {
        return this.getClass().getResource("../images/background.jpg").getPath();
    }
}
