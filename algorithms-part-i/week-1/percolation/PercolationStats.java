import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCEINTV95 = 1.96;
    private final int trials;
    private final double[] gridValue;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        this.trials = trials;
        gridValue = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int cellOne = StdRandom.uniform(1, n + 1);
                int cellTwo = StdRandom.uniform(1, n + 1);
                if (!perc.isOpen(cellOne, cellTwo))
                    perc.open(cellOne, cellTwo);
            }
            gridValue[i] = (double) perc.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(gridValue);
    }

    public double stddev() {
        return StdStats.stddev(gridValue);
    }

    public double confidenceLo() {
        return mean() - (CONFIDENCEINTV95 * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + (CONFIDENCEINTV95 * stddev() / Math.sqrt(trials));
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percStats = new PercolationStats(n, trials);
        System.out.printf("%-23s = %f%n", "mean", percStats.mean());
        System.out.printf("%-23s = %f%n", "stddev", percStats.stddev());
        System.out.printf("%-23s = [%f, %f]", "95% confidence interval", percStats.confidenceLo(),
                          percStats.confidenceHi());
    }
}
