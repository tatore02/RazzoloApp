package it.unisa.razzolo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import it.unisa.razzolo.model.Point;

public class Algorithms {

    public static List<Point> bfs(char[][] matrix, int i, int j){
        final boolean[][] isValid = new boolean[][]{{true,true,true,true},{true,true,true,true},{true,true,true,true},{true,true,true,true}};
        final List<Point> l = new ArrayList<>();

        final Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i,j, matrix[i][j]));
        isValid[i][j] = false;

        while(!queue.isEmpty()){
            final Point point = queue.poll();
            l.add(point);

            int x = point.getI();
            int y = point.getJ();
            // check adjacent cells
            for(int k=0; k<8; k++){
                int deltaX = x + dX[k];
                int deltaY = y + dY[k];
                if(checkCells(isValid, deltaX, deltaY)){
                    queue.add(new Point(deltaY, deltaY, matrix[deltaX][deltaY]));
                    isValid[deltaX][deltaX] = false;
                }
            }
        }
        return l;
    }

    private static boolean checkCells(boolean[][] isValid, int i, int j){
        if((i>=0 && i<=4) && (j>=0 && j<=4))
            return isValid[i][j];
        return false;
    }

    // all possible directions
    private static final int[] dX = { -1, 0, 1, 0, -1, 1, 1, -1};
    private static final int[] dY = { 0, 1, 0, -1, -1, -1, 1, 1};
}
