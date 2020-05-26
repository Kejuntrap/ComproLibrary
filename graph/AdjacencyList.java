
class AdjacencyList{
    public static void main(String[] args){
        Vertex[] po=new Vertex[10];
        for(int i=0; i<10; i++){
            po[i]=new Vertex();
        }
    }
    public static final class Pair<K, V>{        //powered by mikit
		//Pair<任意の型、任意の型>だが、下の機能すべて使うには、Pair<Integer,Integer>でないと不可能
		public K key;
		public V value;
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public static int getValue(Pair<Integer,Integer> a) {
			return a.value;
		}
		public static int getKey(Pair<Integer,Integer> a) {
			return a.key;
		}
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Pair<?, ?> pair = (Pair<?, ?>) o;
			return Objects.equals(key, pair.key) &&
					Objects.equals(value, pair.value);
		}
		public int hashCode() {
			return Objects.hash(key, value);
		}
		public String toString() {
			return "Pair{" +
					"x=" + key +
					", y=" + value +
					'}';
		}
	}
	public static class Vertex{
		ArrayList <Pair<Integer,Integer>> rinsetu=new ArrayList<Pair<Integer,Integer>>();
		public void add(int V,int cost) {
			rinsetu.add(new Pair<Integer, Integer>(V,cost));
		}
		public void show() {        //隣接リストを出力
			for(int i=0; i<rinsetu.size(); i++) {
				System.out.println(rinsetu.get(i));
			}
		}
		public void sort_by_cost() {        //コストの低い順にソートします
			rinsetu.sort(Comparator.comparing(p -> p.value));
		}
		public void sort_by_Vertex() {      //向かう頂点の番号順にソートします
			rinsetu.sort(Comparator.comparing(p -> p.key));
		}
		public int getValue(int i) {
			return Pair.getValue(rinsetu.get(i));   //Valueを取り出す
		}
		public Pair<Integer,Integer> get(int i) {
			return rinsetu.get(i);   //Pairをぶっこ抜く
		}
		public int getKey(int i) {
			return Pair.getKey(rinsetu.get(i)); //Valueを取り出す
		}
		public void changeKey(int i,int value) {	//リストのペアの前の値（Key,Value）のKeyを書き換え
			Pair<Integer,Integer> tmp=new Pair<Integer, Integer>(value,Pair.getValue(rinsetu.get(i)));
			rinsetu.set(i, tmp);
		}
		public void changeValue(int i,int value) {	//リストのペアの後ろの値（Key,Value）のValueを書き換え
			Pair<Integer,Integer> tmp=new Pair<Integer, Integer>(Pair.getKey(rinsetu.get(i)),value);
			rinsetu.set(i, tmp);
		}
		public void changeKeyValue(int i,int key,int value) {	//リストのペアを書き換え（Key,Valueどちらも）
			Pair<Integer,Integer> tmp=new Pair<Integer, Integer>(key,value);
			rinsetu.set(i, tmp);
		}
		public int size() {     //次数を返す
			return rinsetu.size();
		}
		public int find_Vertex(int a) {     //ある頂点から本当に特定の辺に行くか？  逐次探索なので速さはお察し
			for(int i=0; i<rinsetu.size(); i++) {
				if(a==Pair.getKey(rinsetu.get(i))) {
					return i;
				}
			}
			return -1;
		}
    
            public int lowerBound(final int value) {

			int low = 0;

			int high = rinsetu.size();
			int mid;
			while (low < high) {
				mid = ((high - low) >>> 1) + low;    //(low + high) / 2 (オーバーフロー対策)
				if (Pair.getKey(rinsetu.get(mid)) < value) {
					low = mid + 1;
				} else {
					high = mid;
				}
			}
			if(low<rinsetu.size()) {
				return low;
			}
			else {
				return -1;
			}
		}
    }
}