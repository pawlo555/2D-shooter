package Game;

import Utilities.Settings;
import Utilities.Vector2D;

public class Initialization {
    private Map map;
    private Engine engine;

    public Initialization(Settings settings) {
        this.map = new Map(500, 500);
        this.engine = new Engine();
        map.addStaticElement(new Obstacle(new Vector2D(30,30), 30));
        map.addStaticElement(new Obstacle(new Vector2D(200,100), 40));
        map.addMovableElement(new Soldier(10, new Vector2D(300,300)));
    }

    public Map getMap() {
        return map;
    }

    public Engine getEngine() {
        return engine;
    }


}
