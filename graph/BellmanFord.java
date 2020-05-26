import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class BellmaxFord {
    static int INF = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        int start = sc.nextInt();
        Vertex[] graph = new Vertex[V]; // 隣接リスト
        for (int i = 0; i < V; i++) {
            graph[i] = new Vertex();
        }
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            // グラフの頂点が0始まりか、1始まりかによって数字を1増やしたり減らしたりし、適宜調整
            graph[from].add(to, cost);
        }

        int[] distance = BellmanFord(graph, V, start);

        for (int i = 0; i < V; i++) {
            if (distance[i] >= INF) {
                System.out.println("INF");
            } else if (distance[i] < INF) {
                System.out.println(distance[i]);
            }
        }
    }

    public static int[] BellmanFord(Vertex[] graph, int V, int start) {
        int[] distance = new int[V];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        for (int i = 0; i < V; i++) { // 閉路検出のときにV回回す
            boolean update = false;
            for (int j = 0; j < V; j++) { // 隣接リストの特徴上こうする
                for (int k = 0; k < graph[j].size(); k++) { // 次数
                    if (distance[j] != INF && distance[graph[j].getKey(k)] > distance[j] + graph[j].getValue(k)) {
                        distance[graph[j].getKey(k)] = distance[j] + graph[j].getValue(k);
                        update = true;
                    }
                }
            }
            if (update == true) {
                if (i == V - 1) {
                    System.out.println("NEGATIVE CYCLE");
                    System.exit(0);
                } else {
                    // 続行
                }
            } else {
                // 更新が全て終わってるなら終了
                break;
            }
        }
        return distance;
    }

    public static final class Pair<K, V> { // powered by mikit
        public K key;
        public V value;

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
        ArrayList<Pair<Integer, Integer>> rinsetu = new ArrayList<Pair<Integer, Integer>>();

        public void add(int V, int cost) {
            rinsetu.add(new Pair<Integer, Integer>(V, cost));
        }

        public int getValue(int i) {
            return Pair.getValue(rinsetu.get(i)); // Valueを取り出す
        }

        public int getKey(int i) {
            return Pair.getKey(rinsetu.get(i)); // Valueを取り出す
        }

        public int size() { // 次数（無向なら） でていく数（有向なら）を返す
            return rinsetu.size();
        }
    }
}