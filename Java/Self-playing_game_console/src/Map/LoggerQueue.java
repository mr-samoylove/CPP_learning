package Map;

import Characters.AbstractTypes.BaseNpc;
import Characters.AbstractTypes.Healers;

import java.util.ArrayDeque;
import java.util.Queue;

public class LoggerQueue {
    public static Queue<String> logQueue = new ArrayDeque<>();

    public static void logHit(BaseNpc attacker, BaseNpc defender, double damage) {
        logQueue.add(
                String.format(
                        "%s%c id:%-3d %s hit %s %c id:%-3d %s with %.1f damage",
                        attacker.getTeamColor(),
                        attacker.getTypeSymbol(), attacker.getNpcID(),
                        Constants.AnsiColors.ANSI_YELLOW, defender.getTeamColor(),
                        defender.getTypeSymbol(), defender.getNpcID(),
                        Constants.AnsiColors.ANSI_YELLOW, damage) +
                        Constants.AnsiColors.ANSI_RESET);
    }

    public static void logDead(BaseNpc player) {
        logQueue.add(
                String.format(
                        "%s%c id:%d %s is dead",
                        player.getTeamColor(),
                        player.getTypeSymbol(), player.getNpcID(),
                        Constants.AnsiColors.ANSI_RED) +
                        Constants.AnsiColors.ANSI_RESET);
    }

    public static void logHeal(Healers healer, BaseNpc toHeal) {
        logQueue.add(healer.getTeamColor() +
                String.format(
                        "%c id:%-3d healed %c id:%-3d",
                        healer.getTypeSymbol(), healer.getNpcID(), toHeal.getTypeSymbol(), toHeal.getNpcID()) +
                Constants.AnsiColors.ANSI_RESET);
    }

    public static void logResurrect(Healers healer, BaseNpc toHeal) {
        logQueue.add(healer.getTeamColor() +
                String.format(
                        "%c id:%-3d resurrected %c id:%-3d",
                        healer.getTypeSymbol(), healer.getNpcID(), toHeal.getTypeSymbol(), toHeal.getNpcID()) +
                Constants.AnsiColors.ANSI_RESET);
    }
}
