package GUI;

import Game.Background;
import Game.Map;
import Game.MapElement;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MapVisualizer extends Canvas {
    private Map map;

    public void setMap(Map map) throws FileNotFoundException {
        this.map = map;
        this.setHeight(map.getHeight());
        this.setWidth(map.getWidth());
        Visualize();
    }

    public void Visualize() throws FileNotFoundException {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());
        VisualizeBackground();
        for (MapElement element: map.getStaticElements()) {
            VisualizeElement(gc, element);
        }
        for (MapElement element: map.getMovableElements()) {
            VisualizeElement(gc, element);
        }
    }

    private void VisualizeBackground() throws FileNotFoundException {
        Background background = this.map.getBackground();
        GraphicsContext gc = this.getGraphicsContext2D();
        Image image = new Image(new FileInputStream(background.getPathToJPG()));
        gc.drawImage(image, 0, 0, this.getWidth(), this.getHeight());
    }

    private void VisualizeElement(GraphicsContext gc, MapElement element) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(element.getPathToJPG()));
        double x = element.getUpperLeftCorner().getX();
        double y = element.getUpperLeftCorner().getY();
        double x2 = element.getLowerRightCorner().getX();
        double y2 = element.getLowerRightCorner().getY();
        gc.drawImage(image, x, y, x2-x, y2-y);
    }
}
