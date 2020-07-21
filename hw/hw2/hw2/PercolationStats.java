package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] estThreshold;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("Invalid Argument");
        }
        estThreshold = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation sites = pf.make(N);
            while (!sites.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                sites.open(row, col);
            }
            estThreshold[i] = (double) sites.numberOfOpenSites()/(N*N);
        }

    }

    public double mean() {
        return StdStats.mean(estThreshold);
    }

    public double stddev() {
        return StdStats.stddev(estThreshold);
    }

    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev() / estThreshold.length);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev() / estThreshold.length);
    }

}
