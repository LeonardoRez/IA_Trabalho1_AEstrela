package ia_t1_tspcomaestrela;

import java.util.ArrayList;
import java.util.Collections;

class Edge implements Comparable {

    public String v1;
    public String v2;
    public int cost;

    public Edge(String v1, String v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Object t) {
        return this.cost - ((Edge) t).cost;
    }

}

public class AStar {

    private ArrayList<String> V;
    private ArrayList<Edge> E;

    public AStar() {
        V = new ArrayList<>();
        E = new ArrayList<>();
    }

    public int MST() {
        Collections.sort(E);
        ArrayList<Edge> S = new ArrayList<>();
        for (Edge e : E) {
            if (!bfs(e.v1, e.v2)) {
                S.add(e);
            }
        }
        int total = 0;
        for (Edge e : S) {
            total += e.cost;
        }
        return total;

    }

    private boolean bfs(String v, String dest) throws IllegalAccessError {
        ArrayList<Edge> L = new ArrayList<>();
        ArrayList<Edge> T = new ArrayList<>();
        int[] explorados = new int[E.size()];
        for (int i = 0; i < explorados.length; i++) {
            explorados[i] = 0;
        }
        markExplorado(v, explorados);

        searchBFS(v, L);
        while (!L.isEmpty()) {
            Edge aux = L.get(0);
            L.remove(0);
            if (explorados[getListaIndex(aux.v1)] == 0) {
                T.add(aux);
                searchBFS(aux.v1, L);
                explorados[getListaIndex(aux.v1)] = 1;
            }
            if (explorados[getListaIndex(aux.v2)] == 0) {
                T.add(aux);
                searchBFS(aux.v2, L);
                explorados[getListaIndex(aux.v2)] = 1;
            }

        }
        for (Edge e : T) {
            if (strIgual(e.v1, dest) || strIgual(e.v2, dest)) {
                return true;
            }
        }
        return false;

    }

    private void searchBFS(String v, ArrayList<Edge> L) {
        for (Edge x : E) {
            if (strIgual(v, x.v1) || strIgual(v, x.v2)) {
                L.add(x);
            }
        }
    }

    private boolean strIgual(String s1, String s2) {
        if (s1.compareTo(s2) == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void markExplorado(String v, int[] explorados) {
        int temp = getListaIndex(v);
        if (temp >= 0) {
            explorados[temp] = 1;
        } else {
            throw new IllegalAccessError("O vértice não foi encontrado!");
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
}
