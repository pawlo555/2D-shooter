package Game;

import Utilities.Vector2D;

import javax.management.InvalidAttributeValueException;

public class Bonus extends StaticElement {
    private final int bonusType;
    private final double size;

    public Bonus(Vector2D center, int bonusType) {
        super(center);
        this.bonusType = bonusType;
        size = 15;
    }


    @Override
    public String getPathToJPG() {
        switch (bonusType) {
            case 1: return "C:\\Users\\spawe\\OneDrive\\Pulpit\\Studia\\ProgramowanieObiektowe\\Strzelanka2D\\src\\Images";
            default: return "BAD PATH";
        }
    }

    @Override
    public Vector2D getUpperLeftCorner() {
        return center.add(new Vector2D(size, size));
    }

    @Override
    public Vector2D getLowerRightCorner() {
        return center.add(new Vector2D(-size, -size));
    }

}
