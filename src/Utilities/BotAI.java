package Utilities;

import Game.CollisionEngine;
import Game.Engine;
import Game.Soldier;

public class BotAI {
    private final Engine engine;
    private final CollisionEngine collisionEngine;
    private final Soldier bot;
    private final Soldier player;

    public BotAI(Engine engine, CollisionEngine collisionEngine, Soldier bot, Soldier player) {
        this.engine = engine;
        this.collisionEngine = collisionEngine;
        this.bot = bot;
        this.player = player;
    }

    private double angle() {
        Vector2D botPosition = bot.getCenter();
        Vector2D secondPosition = botPosition.add(bot.getAngle().toUnitVector());
        Vector2D playerPosition = player.getCenter();
        double a  = botPosition.distanceBetweenPoints(secondPosition);
        double b = botPosition.distanceBetweenPoints(playerPosition);
        double c = secondPosition.distanceBetweenPoints(playerPosition);
        double cos = (a*a+b*b-c*c)/(2*a*b);
        return Math.acos(cos);
    }

    private double turnLeftOrRight() {
        double firstAngle = angle();
        bot.turnBy(5);
        double secondAngle = angle();
        bot.turnBy(355);
        if (secondAngle - firstAngle < 0.01) {
            return 0;
        }
        else
            return (secondAngle - firstAngle);
    }

    private void turn() {
        if (turnLeftOrRight() > 0) {
            bot.turnBy(350);
            System.out.println("Turned Left");
        }
        else if (turnLeftOrRight() < 0) {
            bot.turnBy(10);
            System.out.println("Turned Right");
        }
    }

    private void move() {
        if (turnLeftOrRight() == 0) {
            bot.moveBy(5);
            if (collisionEngine.isCollision(bot) || engine.getMap().isInMap(bot)) {
                bot.moveBy(-5);
            }
        }
    }

    private void shoot() {
        if (turnLeftOrRight() == 0) {
            engine.gunShoot(bot);
        }
    }

    public void moveBot() {
        turn();
        move();
        shoot();
    }

    public Soldier getBot() {
        return bot;
    }
}
