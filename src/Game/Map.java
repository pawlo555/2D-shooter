package Game;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final int height;
    private final int width;

    List<StaticElement> staticElements = new ArrayList<>();
    List<MovableElement> movableElements = new ArrayList<>();
    private final Background background;

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
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
}
