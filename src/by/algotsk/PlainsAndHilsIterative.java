package by.algotsk;

import java.util.*;

public class PlainsAndHilsIterative {
    // Направления: вверх, вниз, влево, вправо
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static List<Integer> getMountainSizes(int[][] field) {
        int[][] visited = new int[field.length][field[0].length];
        List<Integer> mountains = new ArrayList<>();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 1 && visited[i][j] == 0) {
                    mountains.add(count(i, j, field, visited));
                }
            }
        }

        return mountains.stream().sorted().toList();
    }

    private static int count(int i, int j, int[][] field, int[][] visited) {
        Queue<Cell> buffer = new LinkedList<>();
        buffer.offer(new Cell(i, j));
        visited[i][j] = -1;
        int size = 1;

        while (!buffer.isEmpty()) {
            Cell cell = buffer.poll();

            for (int c = 0; c < 4; c++) {

                int nextRow = cell.i + DR[c];
                int nextColumn = cell.j + DC[c];

                if ((nextRow < field.length && nextRow >= 0)
                        && (nextColumn < field[i].length && nextColumn >= 0)
                        && field[nextRow][nextColumn] == 1
                        && visited[nextRow][nextColumn] != -1) {

                    buffer.offer(new Cell(nextRow, nextColumn));
                    visited[nextRow][nextColumn] = -1;
                    size++;

                }
            }
        }

        return size;
    }

    static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 0}
        };

        List<Integer> result = getMountainSizes(grid);
        System.out.println(result);
    }

    record Cell(int i, int j) {
    }

}
