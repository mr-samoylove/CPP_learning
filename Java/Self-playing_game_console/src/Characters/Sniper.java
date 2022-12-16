package Characters;

import Characters.AbstractTypes.Coordinates;
import Characters.AbstractTypes.Shooters;
import Characters.AbstractTypes.Team;

public class Sniper extends Shooters {
    public Sniper(int amount, int line, int column, Team allies, Team enemies) {
        super(new Coordinates(line, column), 'S', 4, 5, new int[]{1, 3}, 10, 4, allies, enemies, amount);
        this.shots = 16;
    }
}
