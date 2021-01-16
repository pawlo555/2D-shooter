package Game;

import GUI.MapVisualizer;
import Utilities.Vector2D;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Engine {
    public boolean isWPressed = false;
    public boolean isSPressed = false;
    public boolean isAPressed = false;
    public boolean isDPressed = false;
    public boolean isUpPressed = false;
    public boolean isDownPressed = false;
    public boolean isLeftPressed = false;
    public boolean isRightPressed = false;
    public boolean isSpacePressed = false;
    public boolean isEnterPressed = false;
    private Soldier player1;
    private Soldier player2;

    private MapVisualizer visualizer;
    private Map map;
    private CollisionEngine collisionEngine;

    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Soldier> bots = new ArrayList<>();
    private ArrayList<Bonus> bonuses = new ArrayList<>();

    public Engine(Map map) {
        this.map = map;
        collisionEngine = new CollisionEngine();
        map.addObserver(collisionEngine);
    }

    public void nextTurn() throws FileNotFoundException {
        if (isWPressed) {
            this.player1.moveBy(5);
            System.out.println(collisionEngine.isCollision(player1));
            if (collisionEngine.isCollision(player1) || !map.isInMap(player1)) {
                this.player1.moveBy(-5);
            }
        }
        if (isSpacePressed) {
            gunShoot(player1);
        }

        if (isSPressed) {
            this.player1.moveBy(-5);
            if (collisionEngine.isCollision(player1) || !map.isInMap(player1)) {
                this.player1.moveBy(5);
            }
        }
        if (isAPressed) {
            player1.turnBy(350);
        }
        if (isDPressed) {
            player1.turnBy(10);
        }
        if (isUpPressed) {
            player2.moveBy(5);
        }
        if (isDownPressed) {
            player2.moveBy(-5);
        }
        if (isLeftPressed) {
            player2.turnBy(350);
        }
        if (isRightPressed) {
            player2.turnBy(10);
        }
        updateBullets();
        updateSoldiers();
        trySpawnBonus();
        visualizer.Visualize();
    }

    private void updateSoldiers() {
        ArrayList<Soldier> soldiers = getSoldierList();
        for (Soldier soldier: soldiers) {
            if (soldier.getCurrentHP() <= 0) {
                map.removeMovableElement(soldier);
                if (player1 == soldier) {
                    System.out.println("Player1 died!!!");
                }
                else if (player2 == soldier) {
                    System.out.println("Player2 died!!!");
                }
                else {
                    bots.remove(soldier);
                }
            }
        }

    }

    public void setVisualizer(MapVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void setPlayer1(Soldier player) {
        System.out.println(player);
        this.player1 = player;
        System.out.println(this.player1);
        System.out.println(player1.getLowerRightCorner().toString());
    }

    public void setPlayer2(Soldier soldier) {
        this.player2 = soldier;
    }

    private void gunShoot(Soldier soldier) {
        if (soldier.getGun().isCanShoot()) {
            Vector2D bulletPosition = soldier.getCenter().add(soldier.getAngle().toUnitVector().multiply(40));
            double velocity = soldier.getGun().getBulletsVelocity();
            int damage = soldier.getGun().getDamage();
            Bullet bullet = new Bullet(bulletPosition, soldier.getAngle(), velocity, damage);
            bullets.add(bullet);
            map.addMovableElement(bullet);
            soldier.getGun().shoot();
        }
    }

    private void trySpawnBonus() {
        if (Math.random() > 0.9) {
            System.out.println("Spawn a Bonus");
            int bonusType = (int) (Math.random()*4) + 1;
            System.out.println("BonusType: " + bonusType);
        }
    }

    private void updateBullets() {
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet: bullets) {
            bullet.moveBy(bullet.getVelocity());
            if (collisionEngine.isCollision(bullet)) {
                System.out.println("Boom");
                bulletsToRemove.add(bullet);
                map.removeMovableElement(bullet);
                Soldier hitSoldier = getHitSoldier(bullet);
                if (hitSoldier != null) {
                    System.out.println("Bullet hit soldier");
                    hitSoldier.looseHP(bullet.getDamage());
                }
            }
            if (!map.isInMap(bullet)) {
                System.out.println("Not in Map");
                bulletsToRemove.add(bullet);
                map.removeMovableElement(bullet);
            }
        }
        bullets.removeAll(bulletsToRemove);
    }

    private Soldier getHitSoldier(Bullet bullet) {
        Vector2D bulletCenter = bullet.getCenter();
        double bulletRadius = bullet.getCollider().lengthToEnd(new Vector2D(0,0));
        ArrayList<Soldier> soldiers = getSoldierList();
        for (Soldier soldier: soldiers) {
            double distance = soldier.getCenter().distanceBetweenPoints(bulletCenter);
            double soldierRadius = soldier.getCollider().lengthToEnd(new Vector2D(0,0));
            if (distance < bulletRadius + soldierRadius) {
                return soldier;
            }
        }
        return null;
    }

    public void addBot(Soldier bot) {
        bots.add(bot);
    }

    private ArrayList<Soldier> getSoldierList() {
        ArrayList<Soldier> soldiers = new ArrayList<>();
        soldiers.addAll(bots);
        soldiers.add(player1);
        if (player2 != null)
            soldiers.add(player2);
        return soldiers;
    }
}
