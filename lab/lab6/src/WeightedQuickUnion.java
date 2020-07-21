public class WeightedQuickUnion {
    private int[] parent;
    private int size;
    public WeightedQuickUnion(int n) {
        parent = new int[n];
        size = n;
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    public void validate(int v1) throws IllegalAccessException {
        if (v1 < 0 || v1 >= size) {
            throw new IllegalAccessException("Invalid Index");
        }
    }

    public int parent(int v1) {
        return parent[v1];
    }

    public int find(int v1) {
        if (parent[v1] < 0) {
            return v1;
        } else {
            return find(parent[v1]);
        }
    }

    public int sizeOf(int v1) {
        int root = find(v1);
        return -parent[root];
    }

    public boolean connected(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        return root1 == root2;
    }

    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 == root2) {
            return;
        }
        if (parent[root1] < parent[root2]) {
            parent[root1] += parent[root2];
            parent[root2] = root1;
        } else {
           parent[root2] += parent[root1];
           parent[root1] = root2;
        }
    }
}
