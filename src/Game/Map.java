package Game;

import Utilities.Settings;
import Utilities.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private int height;
    private int width;

    ArrayList<StaticElement> staticElements = new ArrayList<>();
    ArrayList<MovableElement> movableElements = new ArrayList<>();
    private final Background background;

    public Map(Settings settings) {
        if (settings.isFirstOrSecondMap()) {
            LoadFirstMap();
        }
        else
            LoadSecondMap();
        this.background = (new Background(height, width));
    }

    public void addStaticElement(StaticElement staticElement) {
        staticElements.add(staticElement);
    }

    public void addMovableElement(MovableElement movableElement) {
        movableElements.add(movableElement);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<StaticElement> getStaticElements() {
        return this.staticElements;
    }

    public List<MovableElement> getMovableElements() {
        return this.movableElements;
    }

    public Background getBackground() {
        return this.background;
    }

    private void LoadFirstMap() {
        System.out.println("FirstMap");
        this.height = 500;
        this.width = 600;
        addStaticElement(new Obstacle(new Vector2D(130,100), 30));
        addStaticElement(new Obstacle(new Vector2D(130,400), 30));
        addStaticElement(new Obstacle(new Vector2D(470,100), 30));
        addStaticElement(new Obstacle(new Vector2D(470,400), 30));
    }

    private void LoadSecondMap() {
        System.out.println("SecondMap");
        this.height = 600;
        this.width = 800;
        addStaticElement(new Obstacle(new Vector2D(370,100), 30));
        addStaticElement(new Obstacle(new Vector2D(370,200), 30));
        addStaticElement(new Obstacle(new Vector2D(370,300), 30));
        addStaticElement(new Obstacle(new Vector2D(370,400), 30));
        addStaticElement(new Obstacle(new Vector2D(370,500), 30));
    }
}
