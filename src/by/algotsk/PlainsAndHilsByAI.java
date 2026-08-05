package by.algotsk;

import java.util.*;

public class PlainsAndHilsByAI {
    // Направления: вверх, вниз, влево, вправо
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    /**
     * Возвращает размеры всех гор (связных областей из 1) в порядке возрастания.
     */
    public static List<Integer> getMountainSizes(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return Collections.emptyList();
        }

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        List<Integer> mountainSizes = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    int size = bfsCount(grid, visited, r, c, rows, cols);
                    mountainSizes.add(size);
                }
            }
        }

        Collections.sort(mountainSizes);
        return mountainSizes;
    }

    private static int bfsCount(int[][] grid, boolean[][] visited, int startR, int startC, int rows, int cols) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        int size = 0;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            size++;

            for (int i = 0; i < 4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && grid[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return size;
    }

    // Пример использования
    public static void main(String[] args) {
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

}
