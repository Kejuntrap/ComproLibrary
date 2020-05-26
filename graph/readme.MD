# graph

- AdjacencyList.java

  - 隣接リストです。
  - 他で使われている隣接リストは一部機能がオミットされていますが、ここに書いてあるのはフル機能です。
  - Vertex と Pair は依存関係があります。

- DijkstraTrace.java
  - Dijkstra と DijkstraTrace と Pair と Vertex をインポートしてください。
  - Dijkstra 法で最短距離を求めます。
  - Dijkstra はある始点から全ノードに対しての最短距離の配列 int[]を返します。DijkstraTrace は経路を ArrayList で返します。負の辺があると作動しません。
