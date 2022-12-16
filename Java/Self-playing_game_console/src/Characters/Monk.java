package Characters;

import Characters.AbstractTypes.Coordinates;
import Characters.AbstractTypes.Healers;
import Characters.AbstractTypes.Team;

public class Monk extends Healers {
    public Monk(int amount, int line, int column, Team allies, Team enemies) {
        super(new Coordinates(line, column), 'M', 12, 7, new int[]{-5, -5}, 30, 5, allies, enemies, amount);
    }
}
