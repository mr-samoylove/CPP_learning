package Map;

import Characters.AbstractTypes.BaseNpc;
import Characters.AbstractTypes.Coordinates;
import Characters.AbstractTypes.Team;

public class MapMatrix {
    public static BaseNpc[][] mapMatrix = new BaseNpc[Constants.MAP_SIZE][Constants.MAP_SIZE];

    public void placeTeamOnTheMap(Team team) {
        for (BaseNpc player : team) {
            mapMatrix[player.getCoordinates().getX()][player.getCoordinates().getY()] = player;
        }
    }

    public static boolean isFree(int x, int y){
        return mapMatrix[x][y] == null || mapMatrix[x][y].getTypeSymbol() == '_' || mapMatrix[x][y].getStatus().equals("dead");
    }

    public static void movePlayer(BaseNpc player, Coordinates newCoord){
        int oldX = player.getCoordinates().getX();
        int oldY = player.getCoordinates().getY();
        mapMatrix[oldX][oldY] = null;
        player.setCoordinates(newCoord);
        mapMatrix[newCoord.getX()][newCoord.getY()] = player;
    }

    public static void movePlayer(BaseNpc player, String direction){
        int x = player.getCoordinates().getX();
        int y = player.getCoordinates().getY();
        mapMatrix[x][y] = null;
        switch (direction){
            case "right" -> player.setCoordinates(x, ++y);
            case "left"  -> player.setCoordinates(x, --y);
            case "down"  -> player.setCoordinates(++x, y);
            case "up"    -> player.setCoordinates(--x, y);
        }
        mapMatrix[x][y] = player;
    }
}
