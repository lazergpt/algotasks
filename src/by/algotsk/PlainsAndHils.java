package by.algotsk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlainsAndHils {
    static Set<Cell> checkedCells = new HashSet<>();
    static Integer counter = 0;

    static void main() {
       int[][] field = {
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

        System.out.println(checkCells(field));

    }

    static List<Integer> checkCells(int[][] field) {
        List<Integer> sizes = new ArrayList<>();

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Cell cell = new Cell(i, j);
                if (field[i][j] == 1 && !checkedCells.contains(cell)) {
                    checkedCells.add(cell);
                    checkCellAround(cell, field, checkedCells);
                    sizes.add(counter);
                    counter = 0;
                }
            }
        }

        return sizes.stream().sorted().toList();
    }

    static Integer checkCellAround(Cell cell, int[][] field, Set<Cell> checkedCells) {
        ++counter;
        if (hasNextRightCell(cell, field, checkedCells)) {
            Cell nextCell = new Cell(cell.i(), cell.j() + 1);
            checkedCells.add(nextCell);
            checkCellAround(nextCell, field, checkedCells);
        }

        if (hasNextBottomCell(cell, field, checkedCells)) {
            Cell nextCell = new Cell(cell.i() + 1, cell.j());
            checkedCells.add(nextCell);
            checkCellAround(nextCell, field, checkedCells);
        }

        if (hasNextLeftCell(cell, field, checkedCells)) {
            Cell nextCell = new Cell(cell.i(), cell.j() - 1);
            checkedCells.add(nextCell);
            checkCellAround(nextCell, field, checkedCells);
        }

        if (hasNextUpperCell(cell, field, checkedCells)) {
            Cell nextCell = new Cell(cell.i() - 1, cell.j());
            checkedCells.add(nextCell);
            checkCellAround(nextCell, field, checkedCells);
        }

        return counter;
    }

    static boolean hasNextRightCell(Cell cell, int[][] field, Set<Cell> checkedCells) {
        Cell nextCell = new Cell(cell.i, cell.j + 1);
        return (nextCell.j() < field[cell.i()].length) && field[nextCell.i()][nextCell.j()] == 1 && !checkedCells.contains(nextCell);
    }

    static boolean hasNextBottomCell(Cell cell, int[][] field, Set<Cell> checkedCells) {
        Cell nextCell = new Cell(cell.i + 1, cell.j);

        return nextCell.i() < field.length && field[nextCell.i()][nextCell.j()] == 1 && !checkedCells.contains(nextCell);
    }

    static boolean hasNextLeftCell(Cell cell, int[][] field, Set<Cell> checkedCells) {
        Cell nextCell = new Cell(cell.i, cell.j - 1);

        return nextCell.j() >= 0 && field[nextCell.i()][nextCell.j()] == 1 && !checkedCells.contains(nextCell);
    }

    static boolean hasNextUpperCell(Cell cell, int[][] field, Set<Cell> checkedCells) {
        Cell nextCell = new Cell(cell.i - 1, cell.j);

        return nextCell.i() >= 0 && field[nextCell.i()][nextCell.j()] == 1 && !checkedCells.contains(nextCell);
    }

    record Cell(int i, int j) {
    }

}
