package Characters;

import Characters.AbstractTypes.Coordinates;
import Characters.AbstractTypes.Shooters;
import Characters.AbstractTypes.Team;

public class Xbowman extends Shooters {
    public Xbowman(int amount, int line, int column, Team allies, Team enemies) {
        super(new Coordinates(line, column), 'X', 6, 3, new int[]{2, 3}, 10, 4, allies, enemies, amount);
        this.shots = 16;
    }
}
