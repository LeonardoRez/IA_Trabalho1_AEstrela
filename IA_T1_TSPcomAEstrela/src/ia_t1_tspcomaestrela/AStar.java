package ia_t1_tspcomaestrela;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

class Edge implements Comparable {

    public int v1;
    public int v2;
    public int cost;

    public Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Object t) {
        return this.cost - ((Edge) t).cost;
    }

}

class No implements Comparable {

    public int ID;
    public int cost;
    public int pai;

    public No(int ID, int pai) {
        this.ID = ID;
        this.pai = pai;
        cost = 0;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int compareTo(Object t) {
        return this.cost - ((No) t).cost;
    }

}

class UnionFind {

    private int[] p, rank;

    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
        }
    }

    public int findSet(int i) {
        return (p[i] == i) ? i : (p[i] = findSet(p[i]));
    }

    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int x = findSet(i), y = findSet(j);
            if (rank[x] > rank[y]) {
                p[y] = x;
            } else {
                p[x] = y;
                if (rank[x] == rank[y]) {
                    rank[y]++;
                }
            }
        }
    }
}

public class AStar {

    private ArrayList<String> V;
    private ArrayList<Edge> E;

    public AStar() {
        V = new ArrayList<>();
        E = new ArrayList<>();
    }

    public void calcAStar(String inicio) {

        PriorityQueue<No> open = new PriorityQueue<>();
        open.add(new No(getListaIndex(inicio), 0));
        ArrayList<No> closed = new ArrayList<>();

        boolean[] naoEXP = new boolean[V.size()];
        for (int i = 0; i < V.size(); i++) {
            naoEXP[i] = true;
        }
        naoEXP[getListaIndex(inicio)] = false;

        while (!open.isEmpty()) {
            No q = open.poll();
            ArrayList<No> sucessores = new ArrayList<>();
            for (Edge e : E) {
                if (q.ID == e.v1) {
                    sucessores.add(new No(e.v2, q.ID));
                }
                if (q.ID == e.v2) {
                    sucessores.add(new No(e.v1, q.ID));
                }
            }
            for (No n : sucessores) {
//                n.cost = 
            }
        }

    }

    public int MST() {
        Collections.sort(E);
        ArrayList<Edge> S = new ArrayList<>();
        UnionFind UF = new UnionFind(V.size());
        int mst_cost = 0;

        for (Edge e : E) {
            if (!UF.isSameSet(e.v1, e.v2)) {
                mst_cost += e.cost;
                UF.unionSet((e.v1), (e.v2));
                S.add(e);
            }
        }
        return mst_cost;
    }

    private boolean strIgual(String s1, String s2) {
        if (s1.compareTo(s2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private int getListaIndex(String v) {
        for (int i = 0; i < V.size(); i++) {
            if (strIgual(v, V.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private int getListaIndex(String v, ArrayList<String> L) {
        for (int i = 0; i < L.size(); i++) {
            if (strIgual(v, L.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> p;
        p = new ArrayList<Integer>();
        for (int i : p) {
            System.out.println("" + i);
        }
    }

}
