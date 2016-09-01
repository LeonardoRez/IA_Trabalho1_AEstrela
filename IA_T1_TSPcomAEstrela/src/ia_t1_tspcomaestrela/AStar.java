package ia_t1_tspcomaestrela;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

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
    public void calcAStar() {
        
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        
//        queue.add()
    }

    public void calcCaminho(String cityInicial) {
        ArrayList<String> visitados = new ArrayList<>();
        ArrayList<String> naoVisit = new ArrayList<>();
        
        ArrayList<String> result = new ArrayList<>();
        result.add(cityInicial);
        
        
        for (String v : V) {
            if(!strIgual(v, cityInicial))
                naoVisit.add(v);
        }
        
        String cAtual = cityInicial;
        while(!naoVisit.isEmpty()){
           int[] pesos = new int[naoVisit.size()];
           for(int i=0;i<naoVisit.size();i++){
               
           }
        }

    }
    


    public int MST() {
        Collections.sort(E);
        ArrayList<Edge> S = new ArrayList<>();
        UnionFind UF = new UnionFind(V.size());
        int mst_cost = 0;

        for (Edge e : E) {
            if (!UF.isSameSet(getListaIndex(e.v1), getListaIndex(e.v2))) {
                mst_cost += e.cost;
                UF.unionSet(getListaIndex(e.v1), getListaIndex(e.v2));
                S.add(e);
            }
        }
        return mst_cost;
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

    private int getListaIndex(String v,ArrayList<String> L) {
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
