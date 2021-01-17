package Utilities;

import Game.Bonus;

public enum BonusType {
    FIRST_AID_KIT,
    FASTER_MOVEMENT,
    STRONGER_BULLETS,
    FASTER_RELOAD;

    public String getPath() {
        switch (this) {
            case FIRST_AID_KIT:
                return "C:\\Users\\spawe\\OneDrive\\Pulpit\\Studia\\ProgramowanieObiektowe\\Strzelanka2D\\src\\Images\\firstAidKit.png";
            case FASTER_MOVEMENT:
                return "C:\\Users\\spawe\\OneDrive\\Pulpit\\Studia\\ProgramowanieObiektowe\\Strzelanka2D\\src\\Images\\fasterMovement.png";
            case STRONGER_BULLETS:
                return "C:\\Users\\spawe\\OneDrive\\Pulpit\\Studia\\ProgramowanieObiektowe\\Strzelanka2D\\src\\Images\\strongerBullets.png";
            case FASTER_RELOAD:
                return "C:\\Users\\spawe\\OneDrive\\Pulpit\\Studia\\ProgramowanieObiektowe\\Strzelanka2D\\src\\Images\\fasterReload.png";
        }
        return null;
    }

    public static BonusType getBonusType(double randValue) {
        if (randValue < 0.25)
            return BonusType.FIRST_AID_KIT;
        else if (randValue < 0.5)
            return BonusType.FASTER_MOVEMENT;
        else if (randValue < 0.75)
            return BonusType.STRONGER_BULLETS;
        else
            return BonusType.FASTER_RELOAD;
    }

}
