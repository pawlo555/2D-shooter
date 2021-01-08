package GUI;

import Game.Map;
import Game.MapElement;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MapVisualizer extends Canvas {
    private Map map;

    public void setMap(Map map) {
        this.map = map;
        this.setHeight(map.getHeight()*10);
        this.setWidth(map.getWidth()*10);
        VisualizeElements();
    }

    public void VisualizeElements() {
        GraphicsContext gc = this.getGraphicsContext2D();
        for (MapElement element: map.getStaticElements()) {
            VisualizeElement(gc, element);
        }
        for (MapElement element: map.getMovableElements()) {
            VisualizeElement(gc, element);
        }
    }


    public void VisualizeElement(GraphicsContext gc, MapElement element) {
        Image image = new Image(element.getPathToJPG());
        double x = element.getUpperLeftCorner().getX();
        double y = element.getUpperLeftCorner().getY();
        double x2 = element.getLowerRightCorner().getX();
        double y2 = element.getLowerRightCorner().getY();
        gc.drawImage(image, x, y, x2-x, y2-y);
    }
}
