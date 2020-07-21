package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int size;
    private int count;
    private boolean[][] site;
    private WeightedQuickUnionUF full;
    private int ceiling;
    private int ground;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Invalid Size");
        }
        size = N;
        count = 0;
        site = new boolean[size][size];
        full = new WeightedQuickUnionUF(size*size + 2);
        ceiling = size*size;
        ground = size*size + 1;
    }

    private int convert(int row, int col) {
        return row * size + col;
    }

    private void checkIdx(int idx) throws IndexOutOfBoundsException {
        if (idx < 0 || idx >= size) {
            throw new java.lang.IndexOutOfBoundsException("Invalid Index");
        }
    }

    private void link(int row, int col) {
        int idx = convert(row, col);
        if (row == 0) full.union(ceiling, idx);
        if (row == size-1) full.union(ground, idx);
        if (row > 0 && isOpen(row-1, col)) full.union(idx, idx - size);
        if (row < size-1 && isOpen(row+1, col)) full.union(idx, idx + size);
        if (col > 0 && isOpen(row, col-1)) full.union(idx, idx - 1);
        if (col < size-1 && isOpen(row, col+1)) full.union(idx, idx + 1);
    }

    public void open(int row, int col) {
        checkIdx(row);
        checkIdx(col);
        if (!isOpen(row, col)) {
            site[row][col] = true;
            count += 1;
            link(row, col);
        }
    }

    public boolean isOpen(int row, int col) {
        checkIdx(row);
        checkIdx(col);
        return site[row][col];
    }

    public boolean isFull(int row, int col) {
        checkIdx(row);
        checkIdx(col);
        int idx = convert(row, col);
        return full.find(ceiling) == full.find(idx);
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return full.find(ceiling) == full.find(ground);
    }

    public static void main(String[] args) {

    }
}
