/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {

    private int n;

    public Percolation(int n) {
        this.n = n;
        if (n <= 0)
            throw new IllegalArgumentException();
    }

    public void open(int row, int col) throws IllegalArgumentException {
        if (row <= 0 || row >= n || col <= 0 || col >= n)
            throw new IllegalArgumentException();
    }

    public boolean isOpen(int row, int col) throws IllegalArgumentException {
        if (row <= 0 || row >= n || col <= 0 || col >= n)
            throw new IllegalArgumentException();
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row >= n || col <= 0 || col >= n)
            throw new IllegalArgumentException();
    }

    public int numberOfOpenSites() {

    }

    public boolean percolates() {

    }

    public static void main(String[] args) {

    }
}
