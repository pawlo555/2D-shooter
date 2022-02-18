package game;

import utilities.BonusType;
import utilities.BotAI;
import utilities.EngineObserver;
import utilities.Vector2D;

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

    private final ArrayList<EngineObserver> observers = new ArrayList<>();
    private final Map map;
    private final CollisionEngine collisionEngine;

    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final ArrayList<Soldier> bots = new ArrayList<>();
    private final ArrayList<BotAI> botsAI = new ArrayList<>();
    private final ArrayList<Bonus> bonuses = new ArrayList<>();
    private final ArrayList<BonusType> possibleBonuses = new ArrayList<>();

    public Engine(Map map, Initialization init) {
        this.map = map;
        collisionEngine = new CollisionEngine();
        map.addObserver(collisionEngine);
        possibleBonuses.addAll(init.getPossibleBonuses());
    }

    public void nextTurn() throws IllegalStateException {
        if (isWPressed) {
            this.player1.moveBy(player1.getSpeed());
            if (collisionEngine.isCollision(player1) || map.isInMap(player1)) {
                this.player1.moveBy(-player1.getSpeed());
            }
        }
        if (isSpacePressed) {
            gunShoot(player1);
        }

        if (isSPressed) {
            this.player1.moveBy(-player1.getSpeed());
            if (collisionEngine.isCollision(player1) || map.isInMap(player1)) {
                this.player1.moveBy(player1.getSpeed());
            }
        }
        if (isAPressed) {
            player1.turnBy(358);
        }
        if (isDPressed) {
            player1.turnBy(2);
        }
        if (isUpPressed) {
            this.player2.moveBy(player2.getSpeed());
            if (collisionEngine.isCollision(player2) || map.isInMap(player2)) {
                this.player2.moveBy(-player2.getSpeed());
            }
        }
        if (isDownPressed) {
            this.player2.moveBy(-player2.getSpeed());
            if (collisionEngine.isCollision(player2) || map.isInMap(player2)) {
                this.player2.moveBy(player2.getSpeed());
            }
        }
        if (isLeftPressed) {
            player2.turnBy(358);
        }
        if (isRightPressed) {
            player2.turnBy(2);
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
                if (player1 == soldier && bots.size() == 0) {
                    throw new IllegalStateException("Second Player Won");
                }
                else if (player1 == soldier) {
                    throw new IllegalStateException("Bots Won");
                }
                else if (player2 == soldier) {
                    throw new IllegalStateException("First Player Won");
                }
                else {
                    bots.remove(soldier);
                    map.removeMovableElement(soldier);
                    for (BotAI ai: botsAI) {
                        if (ai.getBot() == soldier) {
                            botsAI.remove(ai);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void notifyObservers() {
        for (EngineObserver observer: observers) {
            observer.nextEpochRendered();
        }
    }

    public void setPlayer1(Soldier player) {
        this.player1 = player;
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
        if (Math.random() > 0.996 && !possibleBonuses.isEmpty()) {
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
                bulletsToRemove.add(bullet);
                map.removeMovableElement(bullet);
                Soldier hitSoldier = getHitSoldier(bullet);
                if (hitSoldier != null) {
                    hitSoldier.looseHP(bullet.getDamage());
                }
            }
            if (map.isInMap(bullet)) {
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
        ArrayList<Soldier> soldiers = new ArrayList<>(bots);
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

    private void moveBots() {
        for (BotAI ai: botsAI) {
            ai.moveBot();
        }
    }

    public Map getMap() {
        return this.map;
    }
}
