package Map;

import Characters.AbstractTypes.BaseNpc;

import java.util.ArrayDeque;

public class ViewConsole {
    public static int step = 0;
    public void draw() {
        System.out.println(Constants.AnsiColors.ANSI_RED + "Step: " + step++ + Constants.AnsiColors.ANSI_RESET);
        System.out.println(Constants.top10);

        // region ---отрисовка---
        ArrayDeque<BaseNpc> playersFoundInTheRow = new ArrayDeque<>();
        for (int i = 0; i < Constants.MAP_SIZE; i++) {
            for (int j = 0; j < Constants.MAP_SIZE; j++) {
                BaseNpc player = MapMatrix.mapMatrix[i][j];
                if (player == null) System.out.print("|   ");
                else {
                    playersFoundInTheRow.add(player);
                    System.out.print("| " + player.getTeamColor() + player.getTypeSymbol() + " " + Constants.AnsiColors.ANSI_RESET);
                }
            }
            System.out.print("|  ");
            while (!playersFoundInTheRow.isEmpty()) {
                BaseNpc player = playersFoundInTheRow.pop();
                String playerName = player.getClass().getSimpleName();
                System.out.print(player.getTeamColor() + String.format("%-9s", playerName));
                System.out.print("x" + String.format("%-5d", player.getAmount()));
                System.out.print("id=" + String.format("%-3d", player.getNpcID()));
                System.out.print("hp=" + String.format("%4.1f/%-4.1f    ", player.getHealth(), player.getMaxHealth()) + Constants.AnsiColors.ANSI_RESET);
            }
            System.out.println();

            if (i != Constants.MAP_SIZE - 1)
                System.out.println(Constants.mid10);
            else System.out.println(Constants.bottom10);
        }
        // endregion
    }
}
