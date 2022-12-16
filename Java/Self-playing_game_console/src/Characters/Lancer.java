package Characters;

import Characters.AbstractTypes.Coordinates;
import Characters.AbstractTypes.Team;
import Characters.AbstractTypes.Warriors;

public class Lancer extends Warriors {
    public Lancer(int amount, int line, int column, Team allies, Team enemies) {
        super(new Coordinates(line, column),'L',4, 5,  new int[]{1,3}, 10, 4, allies, enemies, amount);
    }
}
