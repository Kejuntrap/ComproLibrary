# graph

- AdjacencyList.java

  - 隣接リストです。
  - 他で使われている隣接リストは一部機能がオミットされていますが、ここに書いてあるのはフル機能です。
  - Vertex と Pair は依存関係があります。

- DijkstraTrace.java

  - Dijkstra と DijkstraTrace と Pair と Vertex をインポートしてください。
  - Dijkstra 法で最短距離を求めます。
  - Dijkstra はある始点から全ノードに対しての最短距離の配列 int[]を返します。DijkstraTrace は経路を ArrayList で返します。負の辺があると作動しません。

- BellmanFord.java
  - BellmanFord と Pair と Vertex をインポートしてください。
  - Bellman-Ford 法で最短距離を求めます。負の辺があっても求まります。負の閉路も検出できます。しかし、負の無向辺は負の閉路になります。
  - BellmanFord はある始点から全ノードに対しての最短距離の配列 int[]を返します。 負の閉路がある場合終了するので適宜変更してください。
