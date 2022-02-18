package utilities;

public class Angle {
    private int angle;

    public Angle() {
        angle = 0;
    }

    public Angle(int angle) {
        this.angle = Math.abs(angle%360);
    }

    public void turnByAngle(int angle) {
        this.angle =Math.abs((this.angle+angle))%360;
    }

    public Vector2D toUnitVector() {
        double angleInRadians = angle*Math.PI/180;
        return new Vector2D(Math.sin(angleInRadians),Math.cos(angleInRadians-Math.PI));
    }

    public int getAngle() {
        return angle;
    }
}
