package Characters;

import Characters.AbstractTypes.Coordinates;
import Characters.AbstractTypes.Healers;
import Characters.AbstractTypes.Team;

public class Wizard extends Healers {
    public Wizard(int amount, int line, int column, Team allies, Team enemies) {
        super(new Coordinates(line, column), 'W', 17, 12, new int[]{-5, -5}, 30, 9, allies, enemies, amount);
    }
}
