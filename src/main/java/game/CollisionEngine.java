package game;

import utilities.Collider;
import utilities.MapObserver;
import utilities.Vector2D;

import java.util.ArrayList;

public class CollisionEngine implements MapObserver {
    private final ArrayList<Collider> colliders = new ArrayList<>();

    @Override
    public void ElementAddToMap(MapElement element) {
        colliders.add(element.getCollider());
    }

    @Override
    public void ElementDeleteFromMap(MapElement element) {
        colliders.remove(element.getCollider());
    }

    public boolean isCollision(MapElement element) {
        Collider colliderToCheck = element.getCollider();
        for (Collider collider: colliders) {
            if (!collider.equals(colliderToCheck)) {
                Vector2D colliderCenter = collider.getCenter();
                Vector2D colliderToCheckCenter = colliderToCheck.getCenter();
                double distanceBetweenColliders = colliderCenter.distanceBetweenPoints(colliderToCheckCenter);
                double colliderLength = collider.lengthToEnd(colliderToCheckCenter);
                double colliderToCheckLength = colliderToCheck.lengthToEnd(colliderCenter);
                if (distanceBetweenColliders < colliderLength + colliderToCheckLength) {

                    return true;
                }
            }
        }
        return false;
    }
}
