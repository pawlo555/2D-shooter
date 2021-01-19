package Game;

import GUI.MapVisualizer;
import Utilities.BonusType;
import Utilities.BotAI;
import Utilities.EngineObserver;
import Utilities.Vector2D;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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

    private ArrayList<EngineObserver> observers = new ArrayList<>();
    private Map map;
    private CollisionEngine collisionEngine;

    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Soldier> bots = new ArrayList<>();
    private ArrayList<BotAI> botsAI = new ArrayList<>();
    private ArrayList<Bonus> bonuses = new ArrayList<>();
    private ArrayList<BonusType> possibleBonuses = new ArrayList<>();

    public Engine(Map map, Initialization init) {
        this.map = map;
        collisionEngine = new CollisionEngine();
        map.addObserver(collisionEngine);
        possibleBonuses.addAll(init.getPossibleBonuses());
    }

    public void nextTurn() throws FileNotFoundException, IllegalStateException {

        if (isWPressed) {
            this.player1.moveBy(5);
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
            this.player2.moveBy(5);
            if (collisionEngine.isCollision(player2) || !map.isInMap(player2)) {
                this.player2.moveBy(-5);
            }
        }
        if (isDownPressed) {
            this.player2.moveBy(-5);
            if (collisionEngine.isCollision(player2) || !map.isInMap(player2)) {
                this.player2.moveBy(5);
            }
        }
        if (isLeftPressed) {
            player2.turnBy(350);
        }
        if (isRightPressed) {
            player2.turnBy(10);
        }
        if (isEnterPressed) {
            gunShoot(player2);
        }

        updateBullets();
        updateSoldiers();
        checkBonuses();
        trySpawnBonus();
        moveBots();
        notifyObservers();
    }

    private void checkBonuses() {
        ArrayList<Soldier> soldiers = getSoldierList();
        for (Soldier soldier: soldiers) {
            Bonus bonus = bonusInArea(soldier);
            if (bonus != null) {
                soldier.getBonus(bonus);
                bonuses.remove(bonus);
                map.removeStaticElement(bonus);
            }
        }
        ArrayList<Bonus> bonusesToRemove = new ArrayList<>();
        for (Bonus bonus: bonuses) {
            if (!bonus.nextTurn()) {
                bonusesToRemove.add(bonus);
                map.removeStaticElement(bonus);
            }
        }
        bonuses.removeAll(bonusesToRemove);

    }

    private Bonus bonusInArea(Soldier soldier) {
        for (Bonus bonus: bonuses) {
            if (bonus.getCenter().distanceBetweenPoints(soldier.getCenter()) < 45) {
                System.out.println("Mam bonus!");
                return bonus;
            }
        }
        return null;
    }

    private void updateSoldiers() throws IllegalStateException {
        ArrayList<Soldier> soldiers = getSoldierList();
        for (Soldier soldier: soldiers) {
            if (soldier.getCurrentHP() <= 0) {
                map.removeMovableElement(soldier);
                if (player1 == soldier) {
                    throw new IllegalStateException("Second Player Won");
                }
                else if (player2 == soldier) {
                    throw new IllegalStateException("First Player Won");
                }
                else {
                    botDies(soldier);
                    if (allBotsAreDead()) {
                        throw  new IllegalStateException("First Player Won");
                    }
                }
            }
        }

    }

    private boolean allBotsAreDead() {
        for (Soldier soldier: bots) {
            if (soldier.getCurrentHP() > 0) {
                return false;
            }
        }
        return true;
    }

    public void notifyObservers() {
        for (EngineObserver observer: observers) {
            observer.nextEpochRendered();
        }
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

    public void gunShoot(Soldier soldier) {
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
        if (Math.random() > 0.99 && !possibleBonuses.isEmpty()) {
            System.out.println("Spawn a Bonus");
            BonusType bonusType = possibleBonuses.get(ThreadLocalRandom.current().nextInt(possibleBonuses.size()));
            double x = Math.random()*map.getWidth();
            double y = Math.random()*map.getWidth();
            Bonus bonus = new Bonus(new Vector2D(x,y), bonusType);
            map.addStaticElement(bonus);
            bonuses.add(bonus);
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
        botsAI.add(new BotAI(this, collisionEngine, bot, player1));
    }

    private ArrayList<Soldier> getSoldierList() {
        ArrayList<Soldier> soldiers = new ArrayList<>();
        soldiers.addAll(bots);
        soldiers.add(player1);
        if (player2 != null)
            soldiers.add(player2);
        return soldiers;
    }

    public void addObserver(EngineObserver observer) {
        observers.add(observer);
    }

    public String getPlayer1HPInfo() {
        return player1.getHPInfo();
    }

    public String getPlayer2HPInfo() {
        return player2.getHPInfo();
    }

    public ArrayList<String> getBotsHPInfo() {
        ArrayList<String> botsHPInfo = new ArrayList<>();
        for (Soldier bot: bots) {
            botsHPInfo.add(bot.getHPInfo());
        }
        while (botsHPInfo.size() < 3) {
            botsHPInfo.add("DEAD");
        }
        return botsHPInfo;
    }

    private void botDies(Soldier deadBot) {
        for (Soldier bot: bots) {
            if (bot == deadBot) {
                for (BotAI ai: botsAI) {
                    if (ai.getBot() == bot) {
                        botsAI.remove(ai);
                        break;
                    }
                }
                bots.remove(bot);
                return;
            }
        }
    }

    private void moveBots() {
        for (BotAI ai: botsAI) {
            ai.moveBot();
        }
    }

    public Map getMap() {
        return this.map;
    }
}
