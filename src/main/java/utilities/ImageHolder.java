package utilities;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class ImageHolder {
    private final HashMap<String, Image> imageViews = new HashMap<>();

    private boolean isInMap(String key) {
        return imageViews.containsKey(key);
    }

    private void addImage(String path) throws FileNotFoundException {
        System.out.println(path);
        FileInputStream input = new FileInputStream(path);
        Image image = new Image(input);
        imageViews.put(path,image);
    }

    private Image getImage(String key) {
        return imageViews.get(key);
    }

    public Image getImageFromPath(String path) throws FileNotFoundException {
        if (!isInMap(path)) {
            addImage(path);
        }
        return getImage(path);
    }
}
