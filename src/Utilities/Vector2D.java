package Utilities;

public class Vector2D {
    private final double x;
    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D second) {
        return new Vector2D(this.getX()+ second.getX(), this.getY() + second.getY());
    }

    public Vector2D multiply(double x) {
        return new Vector2D(this.getX()*x, this.getY()*x);
    }

    public double distanceBetweenPoints(Vector2D other) {
        return Math.sqrt(Math.pow(this.getX()+other.getX(),2) + Math.pow(this.getY()+other.getY(),2));
    }

    public boolean follows(Vector2D other) {
        return this.getX() >= other.getX() && this.getY() >= other.getY();
    }

    public boolean precedes(Vector2D other) {
        return this.getX() <= other.getX() && this.getY() <= other.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
