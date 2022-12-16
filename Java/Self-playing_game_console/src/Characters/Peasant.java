package Characters;

import Characters.AbstractTypes.BaseNpc;
import Characters.AbstractTypes.Coordinates;
import Characters.AbstractTypes.Team;

public class Peasant extends BaseNpc {

    public Peasant(int amount, int line, int column, Team allies, Team enemies) {
        super(new Coordinates(line, column), 'P', 1, 1, new int[]{1, 1}, 1, 3, allies, enemies, amount);
    }

    @Override
    public void step() {
        if (this.health != 0) status = "ready";
    }
}
