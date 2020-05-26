import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

class Dijkstratrace {
    static int INF = 1000000007; // 十分に大きな定数

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(); // 頂点数
        int E = sc.nextInt(); // 辺の数
        int start = sc.nextInt(); // スタート地点の頂点
        int[] distance = new int[V]; // 各頂点の最短距離

        Vertex[] graph = new Vertex[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new Vertex();
        }
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            graph[from].add(to, cost);
        }

        distance = Dijkstra(graph, start);

        for (int i = 0; i < V; i++) {
            if (distance[i] >= INF) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
                ArrayList<Integer> path = DijkstraTrace(start, i, graph); // start to graph
                System.out.println(path.toString());
            }
        }
    }

    public static int[] Dijkstra(Vertex[] graph, int start) {
        int[] distance = new int[graph.length];
        PriorityQueue<Pair<Integer, Integer>> que = new PriorityQueue<Pair<Integer, Integer>>(
                Comparator.comparing(v -> v.value));
        Arrays.fill(distance, INF); // 距離が未確定なのでスタート地点からの距離は無限遠
        distance[start] = 0; // スタート地点からスタート地点までの距離は当然0
        Pair<Integer, Integer> s = new Pair<Integer, Integer>(start, 0); // コスト、頂点の番号
        que.add(s); // スタート地点の情報をPriortyQueueに入れる
        Pair<Integer, Integer> tmp = new Pair<Integer, Integer>(0, 0); // 一時的な変数

        while (que.size() > 0) {
            tmp = que.poll();
            int vertex = Pair.getKey(tmp); // 頂点の番号を取得
            if (distance[vertex] < Pair.getValue(tmp)) { // キューに入っていた頂点とその頂点までの距離が現在の最短距離の値よりも大きい時、その値を用いて最短距離を更新することはできないので、キューから新しい情報を得るためにこのループを終了させる
                continue;
            } else {// 最短経路を更新できる可能性があるときは、
                for (int i = 0; i < graph[vertex].size(); i++) {
                    tmp = graph[vertex].get(i);
                    int to = Pair.getKey(tmp);
                    int cost = Pair.getValue(tmp);
                    if (distance[to] > distance[vertex] + cost) {
                        distance[to] = distance[vertex] + cost;
                        tmp = new Pair<Integer, Integer>(to, distance[to]);
                        que.add(tmp);
                    }
                }
            }
        }
        return distance;
    }

    public static ArrayList<Integer> DijkstraTrace(int from, int to, Vertex[] graph) { // 経路を探索
        int[] prev = new int[graph.length];
        int[] dist = new int[graph.length];
        Arrays.fill(dist, INF); // INF
        Arrays.fill(prev, -1); // INF
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<Pair<Integer, Integer>>(
                Comparator.comparing(v -> v.value));
        dist[from] = 0;
        // つまり値はスタートからの距離の値といまいる点を知りたい
        Pair<Integer, Integer> po = new Pair<Integer, Integer>(from, dist[from]);
        pq.add(po);
        while (pq.size() > 0) {
            Pair<Integer, Integer> res = pq.poll();
            int v = Pair.getKey(res);
            int p = Pair.getValue(res);
            if (dist[v] < p) {
                continue;
            } else {
                for (int i = 0; i < graph[v].size(); i++) {
                    int cost = graph[v].getValue(i);
                    int tmpv = graph[v].getKey(i);
                    if (dist[tmpv] > dist[v] + cost) {
                        dist[tmpv] = dist[v] + cost;
                        Pair<Integer, Integer> tmp = new Pair<Integer, Integer>(tmpv, dist[tmpv]);
                        prev[tmpv] = v;
                        pq.add(tmp);
                    }
                }
            }
        }
        ArrayList<Integer> p = new ArrayList<Integer>();
        for (int cur = to; cur != -1; cur = prev[cur]) {
            p.add(cur);
        }
        Collections.reverse(p);
        return p;
    }

    public static final class Pair<K, V> { // powered by mikit
        // Pair<任意の型、任意の型>だが、下の機能すべて使うには、Pair<Integer,Integer>でないと不可能
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public static int getValue(Pair<Integer, Integer> a) {
            return a.value;
        }

        public static int getKey(Pair<Integer, Integer> a) {
            return a.key;
        }
    }

    public static class Vertex {
        private ArrayList<Pair<Integer, Integer>> rinsetu = new ArrayList<Pair<Integer, Integer>>();

        public void add(int V, int cost) {
            rinsetu.add(new Pair<Integer, Integer>(V, cost));
        }

        public int getValue(int i) {
            return Pair.getValue(rinsetu.get(i)); // Valueを取り出す
        }

        public Pair<Integer, Integer> get(int i) {
            return rinsetu.get(i); // Pairをぶっこ抜く
        }

        public int getKey(int i) {
            return Pair.getKey(rinsetu.get(i)); // Valueを取り出す
        }

        public int size() { // 次数を返す
            return rinsetu.size();
        }
    }
}
