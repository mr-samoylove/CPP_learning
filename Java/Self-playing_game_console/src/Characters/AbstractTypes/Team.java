package Characters.AbstractTypes;

import Map.Constants;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Team implements Iterable<BaseNpc> {
    public static final Team THE_KILLED = new Team(Constants.AnsiColors.ANSI_RED);
    public final ArrayList<BaseNpc> team = new ArrayList<>();
    public final String teamColor;


    public Team(String teamColor) {
        this.teamColor = teamColor;
    }

    // region ---main methods---
    public void add(BaseNpc npc) {
        team.add(npc);
    }

    public void remove(BaseNpc npc) {
        team.remove(npc);
    }

    public BaseNpc get(int x) { return team.get(x); }

    public BaseNpc getNpcWithLeastHP() {
        BaseNpc toHeal = team.get(0);
        for (BaseNpc teammate : team) {
            if (teammate.getHealth() / teammate.getMaxHealth() < toHeal.getHealth() / toHeal.getMaxHealth()) {
                toHeal = teammate;
            }
        }
        return toHeal;
    }

    public BaseNpc getReadyPeasant() {
        for (BaseNpc teammate : team) {
            if (teammate.getClass().getSimpleName().equals("Peasant") && teammate.status.equals("ready"))
                return teammate;
        }
        return null;
    }

    public BaseNpc getClosest(Coordinates coordsOfTheOneLooking) {
        double minDist = Integer.MAX_VALUE;
        double currentDist = 0.0;
        BaseNpc res = null;

        for (BaseNpc teammate : team) {
            currentDist = Coordinates.getDistance(coordsOfTheOneLooking, teammate.coordinates);
            if (currentDist < minDist) {
                minDist = currentDist;
                res = teammate;
            }
        }
        return res;
    }

    public static ArrayList<BaseNpc> createTurnsOrder(Team team1, Team team2){
        ArrayList<BaseNpc> order = new ArrayList<>(team1.team);
        order.addAll(team2.team);
        order.sort(Comparator.comparing(BaseNpc::getOrderPriority).thenComparing(BaseNpc::getSpeed));
        return order;
    }


    // endregion

    // region ---auxiliary methods---
    public void printAll() {
        team.forEach(System.out::println);
    }

    @Override
    public Iterator<BaseNpc> iterator() {
        return team.iterator();
    }
    // endregion
}
