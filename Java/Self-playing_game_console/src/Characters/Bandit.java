package Characters;

import Characters.AbstractTypes.Coordinates;
import Characters.AbstractTypes.Team;
import Characters.AbstractTypes.Warriors;

public class Bandit extends Warriors {
    public Bandit(int amount, int line, int column, Team allies, Team enemies) {
        super(new Coordinates(line, column), 'B', 8, 3, new int[]{2, 4}, 10, 6, allies, enemies, amount);
    }
}
