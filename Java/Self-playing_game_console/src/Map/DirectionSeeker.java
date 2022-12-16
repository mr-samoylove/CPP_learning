package Map;

import Characters.AbstractTypes.Coordinates;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DirectionSeeker {
    private final Coordinates startPoint;
    private final Coordinates finishPoint;
    private final LinkedHashMap<String, Coordinates> previousPoints = new LinkedHashMap<>();

    public DirectionSeeker(Coordinates startPoint, Coordinates finishPoint) {
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
    }

    /**
     * @return the distance between the start point and the finish point
     * or -1 if a path isn't found.
     */
    public int computeDist() {
        int[][] dist = new int[Constants.MAP_SIZE][Constants.MAP_SIZE];
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        ArrayList<Coordinates> oldFront = new ArrayList<>();
        ArrayList<Coordinates> newFront = new ArrayList<>();

        int currentDist = 0;
        for (int i = 0; i < Constants.MAP_SIZE; i++)
            for (int j = 0; j < Constants.MAP_SIZE; j++) {
                dist[i][j] = MapMatrix.isFree(i, j) ? Integer.MAX_VALUE : -1;
            }
        oldFront.add(startPoint);
        dist[startPoint.getX()][startPoint.getY()] = 0;
        dist[finishPoint.getX()][finishPoint.getY()] = 0;
        while (true) {
            currentDist++;
            for (Coordinates p : oldFront) {
                for (int[] dir : directions) {
                    int newX = p.getX() + dir[0];
                    int newY = p.getY() + dir[1];
                    Coordinates newPoint = new Coordinates(newX, newY);

                    if (newPoint.isEqual(finishPoint)) {
                        previousPoints.put(newPoint.toString(), p);
                        return currentDist;
                    } else if
                        (newX < 0 || newX >= Constants.MAP_SIZE || newY < 0 || newY >= Constants.MAP_SIZE // if out of border
                                  || dist[newX][newY] <= currentDist) { // or if already checked
                        continue;
                    }
                    dist[newX][newY] = currentDist;
                    previousPoints.put(newPoint.toString(), p);
                    newFront.add(newPoint);
                }
            }
            if (newFront.isEmpty()) return -1;
            oldFront = new ArrayList<>(newFront);
            newFront.clear();
        }
    }


    public Coordinates getFreeCellCloserToEnemy() {
        if (!previousPoints.containsKey(finishPoint.toString()))
            return null;
        Coordinates lastPoint = finishPoint;
        ArrayList<Coordinates> path = new ArrayList<>();
        while (!lastPoint.equals(startPoint)) {
            path.add(lastPoint);
            lastPoint = previousPoints.get(lastPoint.toString());
        }
        path.add(startPoint);
        return path.get(path.size() - 2);
    }
}
