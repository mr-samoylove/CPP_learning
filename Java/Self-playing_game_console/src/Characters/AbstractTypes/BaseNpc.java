package Characters.AbstractTypes;

import Map.LoggerQueue;

import java.util.Arrays;

public abstract class BaseNpc implements BaseBehaviour {
    // region ---class fields---
    private static int staticCounterID = 1;
    protected char typeSymbol;
    protected Team allies;
    protected Team enemies;

    protected Coordinates coordinates;
    protected int orderPriority;
    protected int npcID;
    protected String status;

    protected int amount;
    protected int attack;
    protected int protection;
    protected int shots;
    protected int[] damage;
    protected double maxHealth;
    protected double health;
    protected int speed;

    // endregion

    // region ---class methods---
    public BaseNpc(Coordinates coordinates, char typeSymbol, int attack, int protection,
                   int[] damage, double maxHealth, int speed, Team allies, Team enemies, int amount) {
        this.npcID = staticCounterID++;
        this.typeSymbol = typeSymbol;
        this.coordinates = coordinates;

        this.amount = amount;
        this.attack = attack;
        this.protection = protection;
        this.shots = 0;
        this.damage = damage;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.speed = speed;
        this.status = "ready";

        this.allies = allies;
        allies.add(this);
        this.enemies = enemies;

        switch (typeSymbol) {
            case 'B', 'L' -> orderPriority = 1;
            case 'S', 'X' -> orderPriority = 2;
            case 'M', 'W' -> orderPriority = 3;
            case 'P' -> orderPriority = 4;
        }
    }

    public String getInfo() {
        return "npcID=" + npcID +
                ", type=" + this.getClass().getSimpleName() +
                ", attack=" + attack +
                ", protection=" + protection +
                ", shots=" + shots +
                ", damage=" + Arrays.toString(damage) +
                ", health=" + health +
                ", maxHealth=" + maxHealth +
                ", speed=" + speed;
    }

    @Override
    public String toString() {
        return this.getInfo();
    }

    public static double calculateDamage(BaseNpc attacker, BaseNpc defender) {
        double res = 0.0;
        int x = attacker.attack - defender.protection;
        if (x > 0) res = attacker.damage[1] * attacker.amount;
        else if (x < 0) res = attacker.damage[0] * attacker.amount;
        else res = ((double) (attacker.damage[0] + attacker.damage[1]) / 2) * attacker.amount;
        return res;
    }

    public void takeDamage(double damage, BaseNpc attacker) {
        double groupHP = (amount - 1) * maxHealth + health;
        groupHP -= damage;
        if (groupHP <= 0) {
            LoggerQueue.logDead(this);
            health = 0;
            amount = 0;
            status = "dead";
            typeSymbol = '_';
            allies.remove(this);
            allies = Team.THE_KILLED;
            Team.THE_KILLED.add(this);
        } else {
            amount = (int) (groupHP / maxHealth);
            if (groupHP % maxHealth != 0) {
                health = groupHP - amount * maxHealth;
                amount += 1;
            } else {
                health = maxHealth;
            }
            LoggerQueue.logHit(attacker, this, damage);
        }
    }

    // endregion


    //region ---Getters---


    public int getAmount() {
        return amount;
    }

    public int getOrderPriority() {
        return orderPriority;
    }

    public String getTeamColor() {
        return allies.teamColor;
    }

    public char getTypeSymbol() {
        return typeSymbol;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getNpcID() {
        return npcID;
    }

    public int getAttack() {
        return attack;
    }

    public int getProtection() {
        return protection;
    }

    public int getShots() {
        return shots;
    }

    public int[] getDamage() {
        return damage;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public String getStatus() {
        return status;
    }

    //endregion

    //region ---Setters---

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoordinates(int x, int y) {
        this.coordinates = new Coordinates(x, y);
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //endregion
}
