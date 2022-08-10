package csproblem.injava.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Maze {

    private final int rows;
    private final int columns;
    private final MazeLocation start;
    private final MazeLocation goal;

    private final Cell[][] grid;

    public Maze(int rows, int columns, MazeLocation start, MazeLocation goal, double sparseness) {
        this.rows = rows;
        this.columns = columns;
        this.start = start;
        this.goal = goal;

        grid = new Cell[rows][columns];
        for (Cell[] row : grid) {
            Arrays.fill(row, Cell.EMPTY);
        }
        randomlyFill(sparseness);
        grid[start.row][start.column] = Cell.START;
        grid[goal.row][goal.column] = Cell.GOAL;
    }

    public Maze() {
        this(10,
                10,
                new MazeLocation(0, 0),
                new MazeLocation(9, 9),
                0.2);
    }

    private void randomlyFill(double sparseness) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (Math.random() < sparseness) {
                    grid[row][column] = Cell.BLACKED;
                }
            }
        }
    }

    public boolean goalTest(MazeLocation location) {
        return goal.equals(location);
    }

    public List<MazeLocation> successors(MazeLocation ml) {
        List<MazeLocation> locations = new ArrayList<>();
        if (canMoveDown(ml)) {
            locations.add(new MazeLocation(ml.row + 1, ml.column));
        }
        if (canMoveUp(ml)) {
            locations.add(new MazeLocation(ml.row - 1, ml.column));
        }
        if (canMoveRight(ml)) {
            locations.add(new MazeLocation(ml.row, ml.column + 1));
        }
        if (canMoveLeft(ml)) {
            locations.add(new MazeLocation(ml.row, ml.column - 1));
        }
        return locations;
    }

    private boolean canMoveDown(MazeLocation ml) {
        return ml.row + 1 < rows && grid[ml.row + 1][ml.column] != Cell.BLACKED;
    }

    private boolean canMoveUp(MazeLocation ml) {
        return ml.row - 1 >= 0 && grid[ml.row - 1][ml.column] != Cell.BLACKED;
    }

    private boolean canMoveRight(MazeLocation ml) {
        return ml.column + 1 < columns && grid[ml.row][ml.column + 1] != Cell.BLACKED;
    }

    private boolean canMoveLeft(MazeLocation ml) {
        return ml.column - 1 >= 0 && grid[ml.row][ml.column - 1] != Cell.BLACKED;
    }

    public void mark(List<MazeLocation> path) {
        for (MazeLocation ml : path) {
            grid[ml.row][ml.column] = Cell.PATH;
        }
        resetStartAndGoal();
    }

    private void resetStartAndGoal() {
        grid[start.row][start.column] = Cell.START;
        grid[goal.row][goal.column] = Cell.GOAL;
    }

    public void clear(List<MazeLocation> path) {
        for (MazeLocation ml : path) {
            grid[ml.row][ml.column] = Cell.EMPTY;
        }
        resetStartAndGoal();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                sb.append(cell.toString());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Maze maze = new Maze();
        System.out.println(maze);

        GenericSearch.Node<MazeLocation> solution = GenericSearch.dfs(maze.start, maze::goalTest, maze::successors);
        printSolution(maze, solution);

        GenericSearch.Node<MazeLocation> solutionTwo = GenericSearch.bfs(maze.start, maze::goalTest, maze::successors);
        printSolution(maze, solutionTwo);
    }

    private static void printSolution(Maze maze, GenericSearch.Node<MazeLocation> solution) {
        if (solution == null) {
            System.out.println("No solution found using depth-first search!");
        } else {
            List<MazeLocation> path = GenericSearch.nodeToPath(solution);
            maze.mark(path);
            System.out.println(maze);
            maze.clear(path);
        }
    }

    public enum Cell {
        EMPTY(" "),
        BLACKED("X"),
        START("S"),
        GOAL("G"),
        PATH("*");

        private final String code;

        Cell(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public static class MazeLocation {
        private final int row;
        private final int column;

        public MazeLocation(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MazeLocation that = (MazeLocation) o;
            return row == that.row && column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}
