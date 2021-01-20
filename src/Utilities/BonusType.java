package Utilities;

public enum BonusType {
    FIRST_AID_KIT,
    FASTER_MOVEMENT,
    STRONGER_BULLETS,
    FASTER_RELOAD;

    public String getPath() {
        return switch (this) {
            case FIRST_AID_KIT -> "src/Images/firstAidKit.png";
            case FASTER_MOVEMENT -> "src/Images/fasterMovement.png";
            case STRONGER_BULLETS -> "src/Images/strongerBullets.png";
            case FASTER_RELOAD -> "src/Images/fasterReload.png";
        };
    }
}
