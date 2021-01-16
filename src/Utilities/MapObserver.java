package Utilities;

import Game.MapElement;

public interface MapObserver {

    void ElementAddToMap(MapElement element);

    void ElementDeleteFromMap(MapElement element);
}
