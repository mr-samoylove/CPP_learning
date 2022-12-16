package Characters.AbstractTypes;

import Map.LoggerQueue;

public abstract class Healers extends BaseNpc {
    public Healers(Coordinates coordinates, char typeSymbol, int attack, int protection, int[] damage, double maxHealth, int speed, Team allies, Team enemies, int amount) {
        super(coordinates, typeSymbol, attack, protection, damage, maxHealth, speed, allies, enemies, amount);
    }

    @Override
    public void step() {
        // 1. Сначала поиск тиммейта с наименьшим % хп, лечим если хп <75%
        BaseNpc toHeal = allies.getNpcWithLeastHP();
        if (toHeal != null && toHeal.health / toHeal.maxHealth <= 0.75) {
            toHeal.setHealth(toHeal.getHealth() - this.getDamage()[1]);
            // обработка случая, если прибавка здоровья вышла за границы максимального здоровья
            if (toHeal.getHealth() > toHeal.getMaxHealth()) {
                toHeal.setHealth(toHeal.getMaxHealth());
            }
            LoggerQueue.logHeal(this, toHeal);
        } else {
            // 2. Не нашли? Тогда поиск среди противников того, кого можно убить с одного выстрела
            for (BaseNpc target : enemies) {
                if (Math.abs(damage[0]) > target.health + target.maxHealth * target.amount) {
                    target.takeDamage(-Math.abs(damage[0]), this);
                    return;
                }
            }
            // 3. Не нашли? Тогда поиск того, кого можно воскресить
            for (BaseNpc target : Team.THE_KILLED) {
                char ch = target.getClass().getSimpleName().charAt(0);
                if (ch == 'B' || ch == 'S' || ch == 'L' || ch == 'X'){
                    target.amount = 1;
                    target.health = 1;
                    target.typeSymbol = ch;

                    allies.add(target);
                    target.allies = this.allies;
                    target.enemies = this.enemies;
                    LoggerQueue.logResurrect(this, target);

                    Team.THE_KILLED.remove(target);
                    return;
                }
            }
            // 4. Не нашли? Тогда поиск нанесение урона самому слабому врагу
            BaseNpc target = enemies.getNpcWithLeastHP();
            target.takeDamage(Math.abs(damage[0]), this);
            return;
        }
    }
}
