import java.util.Scanner;

// AOJ DSL_2
class SegTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        Segtree sg = new Segtree(N);
        sg.fill(Integer.MAX_VALUE);
        for (; Q > 0; Q--) {
            int com = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (com == 0) {
                sg.update(x, y);
            } else if (com == 1) {
                System.out.println(sg.getmin(x, y, 0, 0, (sg.length() + 1) / 2)); // 始点、終点、０，０、セグ木の領域
            }
        }
    }

    static class Segtree {
        int[] Segtree;

        public Segtree(int vol) {
            int truevol = calc_vol(vol); // vol以上で最も小さい2ベキを求める
            Segtree = new int[truevol * 2 - 1];
            for (int i = 0; i < truevol * 2 - 1; i++) {
                Segtree[i] = 0;
            }
            // pl(truevol*2-1);
        }

        public void fill(int num) { // 特定の値にフィルする
            for (int i = 0; i < Segtree.length; i++) {
                Segtree[i] = num;
                // pl(i+" "+Segtree[i]);
            }
        }

        public void update(int elem, int num) {
            int leaf = elem - 1 + (length() + 1) / 2; // 更新する要素の添字
            Segtree[leaf] = num;
            boolean zero = false; // 0までたどり着いたか
            while (leaf > 0) {
                leaf = getParent(leaf); // 上に（根の方）近づく
                Segtree[leaf] = Math.min(Segtree[leaf * 2 + 1], Segtree[leaf * 2 + 2]);
            }
        }

        public int getmin(int a, int b, int k, int l, int r) { // デフォルトは 始点 , 終点 , 0 , 0 , (sg.length() + 1) / 2
            // pl(a+" "+b+" "+k+" "+l+" "+r);
            if (r <= a || b < l)
                return Integer.MAX_VALUE; // 区間が交わっていないなら0
            if (a <= l && r - 1 <= b)
                return Segtree[k]; // [l,r)が完全に[a,b)に内包されているならそのノードを返す
            else {
                int vl = getmin(a, b, k * 2 + 1, l, (l + r) / 2);
                int vr = getmin(a, b, k * 2 + 2, (l + r) / 2, r);
                return Math.min(vl, vr);
            }
        }

        public int calc_vol(int vol) { // 2のべき乗の値のサイズにする
            int beki = 0;
            int tmp = 1;
            while (vol >= tmp) {
                tmp *= 2;
                if (tmp == vol) {
                    break;
                }
                beki++;
            }
            return tmp;
        }

        public int getParent(int num) { // 親取得
            return (num - 1) / 2;
        }

        public int getChild(int num) { // 子取得
            return num * 2 + 1; // 子は2つあるが、そのうち数が若いほう
        }

        public void add(int elem, int num) { // elem番目の要素にnum足す(elemは0オリジン)
            int leaf = elem - 1 + (length() + 1) / 2; // 更新するセグ木の末端
            boolean zero = false; // 0までたどり着いたか
            while (!zero && leaf >= 0) {
                // pl(leaf);
                Segtree[leaf] += num;
                if (leaf == 0) {
                    zero = true;
                }
                leaf = getParent(leaf); // 上に（根の方）近づく
            }
        }

        public int sum(int a, int b, int k, int l, int r) {// sum(a, b, 0, 0, seg.tree.length/2)
            // [a,b]の要素の和を求める
            // pl(a+" "+b+" "+k+" "+l+" "+r);
            if (r <= a || b < l)
                return 0; // 区間が交わっていないなら0
            if (a <= l && r - 1 <= b)
                return Segtree[k]; // [l,r)が完全に[a,b)に内包されているならそのノードを返す
            else {
                int vl = sum(a, b, k * 2 + 1, l, (l + r) / 2);
                int vr = sum(a, b, k * 2 + 2, (l + r) / 2, r);
                return vl + vr;
            }
        }

        public int length() {
            return Segtree.length;
        }
    }
}