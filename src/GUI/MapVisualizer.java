package GUI;

import Game.Background;
import Game.Map;
import Game.MapElement;
import Game.MovableElement;
import Utilities.Angle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

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
        VisualizeBackground();
        GraphicsContext gc = this.getGraphicsContext2D();
        for (MapElement element: map.getStaticElements()) {
            VisualizeElement(gc, element);
        }
        for (MapElement element: map.getMovableElements()) {
            VisualizeElement(gc, element);
        }
    }

    private void VisualizeBackground() throws FileNotFoundException {
        GraphicsContext gc = this.getGraphicsContext2D();
        Background background = this.map.getBackground();
        Image image = new Image(new FileInputStream(background.getPathToJPG()));
        gc.drawImage(image,0,0,map.getWidth(), map.getWidth());
    }

    private void VisualizeElement(GraphicsContext gc,MapElement element) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(element.getPathToJPG()));
        Angle angle = new Angle(180);
        if (element instanceof MovableElement) {
            MovableElement movableElement = (MovableElement) element;
            angle = movableElement.getAngle();
        }
        double x = element.getUpperLeftCorner().getX();
        double y = element.getUpperLeftCorner().getY();
        double x2 = element.getLowerRightCorner().getX();
        double y2 = element.getLowerRightCorner().getY();
        drawRotatedImage(gc, image,  angle.getAngle(),   x,   y, x2-x,y2-y);
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double x, double y, double dx, double dy) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, x + dx/2 , y + dy/2);
        gc.drawImage(image, x, y, dx, dy);

        gc.restore(); // back to original state (before rotation)
    }

}
