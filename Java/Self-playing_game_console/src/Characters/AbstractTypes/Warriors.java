package Characters.AbstractTypes;

import Map.MapMatrix;
import Map.DirectionSeeker;

public abstract class Warriors extends BaseNpc {
    public Warriors(Coordinates coordinates, char typeSymbol, int attack, int protection, int[] damage, double maxHealth, int speed, Team allies, Team enemies, int amount) {
        super(coordinates, typeSymbol, attack, protection, damage, maxHealth, speed, allies, enemies, amount);
    }

    @Override
    public void step() {
        BaseNpc target = enemies.getClosest(this.coordinates);
        if (target != null) {
            if (target.coordinates.isClose(this.coordinates)) {
                target.takeDamage(calculateDamage(this, target), this);
            } else {
                DirectionSeeker direction = new DirectionSeeker(this.coordinates, target.coordinates);
                if (direction.computeDist() != -1) {
                    MapMatrix.movePlayer(this, direction.getFreeCellCloserToEnemy());
                }
            }
        }
    }
}
