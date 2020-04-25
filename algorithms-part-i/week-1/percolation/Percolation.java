import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private final int topRow;
    private final int bottomRow;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF gridCheck;
    private boolean[][] cells;
    private int numOpenSites;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        this.n = n;
        numOpenSites = 0;
        grid = new WeightedQuickUnionUF(n * n + 2);
        gridCheck = new WeightedQuickUnionUF(n * n + 2);
        topRow = 0;
        bottomRow = n * n;
        cells = new boolean[n][n];
    }

    public void open(int row, int col) {
        if (row <= 0 || row >= n || col <= 0 || col >= n)
            throw new IllegalArgumentException();
        if (!isOpen(row, col)) {
            numOpenSites++;
            cells[row - 1][col - 1] = true;

            if (row == 1) {
                grid.union(topRow, dimReducer(row, col));
                gridCheck.union(topRow, dimReducer(row, col));
            }
            if (row == n)
                grid.union(bottomRow, dimReducer(row, col));
            if (row > 1 && isOpen(row - 1, col)) {
                grid.union(dimReducer(row, col), dimReducer(row - 1, col));
                gridCheck.union(dimReducer(row, col), dimReducer(row - 1, col));
            }
            if (row < n && isOpen(row + 1, col)) {
                grid.union(dimReducer(row, col), dimReducer(row + 1, col));
                gridCheck.union(dimReducer(row, col), dimReducer(row + 1, col));
            }
            if (col > 1 && isOpen(row, col - 1)) {
                grid.union(dimReducer(row, col), dimReducer(row, col - 1));
                gridCheck.union(dimReducer(row, col), dimReducer(row, col - 1));
            }
            if (col < n && isOpen(row, col + 1)) {
                grid.union(dimReducer(row, col), dimReducer(row, col + 1));
                gridCheck.union(dimReducer(row, col), dimReducer(row, col + 1));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row >= n || col <= 0 || col >= n)
            throw new IllegalArgumentException();
        return cells[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row >= n || col <= 0 || col >= n)
            throw new IllegalArgumentException();
        return (isOpen(row, col) && gridCheck.connected(topRow, dimReducer(row, col)));
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

    public boolean percolates() {
        return grid.connected(topRow, bottomRow);
    }

    private int dimReducer(int row, int col) {
        return (row - 1) * n + col;
    }
}
