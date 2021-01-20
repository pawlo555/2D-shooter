package Game;

import Utilities.BonusType;
import Utilities.CircleCollider;
import Utilities.Vector2D;

public class Bonus extends StaticElement {
    private final BonusType bonusType;
    private final double size;
    private int turnsToEnd = 100;
    public Bonus(Vector2D center, BonusType bonusType) {
        super(center);
        this.bonusType = bonusType;
        size = 15;
        collider = new CircleCollider(center,0);
    }

    @Override
    public String getPathToJPG() {
        return bonusType.getPath();
    }

    @Override
    public Vector2D getUpperLeftCorner() {
        return center.add(new Vector2D(size, size));
    }

    @Override
    public Vector2D getLowerRightCorner() {
        return center.add(new Vector2D(-size, -size));
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public boolean nextTurn() {
        turnsToEnd = turnsToEnd - 1;
        return (turnsToEnd > 0);
    }
}
