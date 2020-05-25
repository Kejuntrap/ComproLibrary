
class nCrmod {
    // 宣言しておく MAXはｎCrのn最大値みたい ちなみに、nは10^7くらいまで大きくできるみたい。
    static int max = 114600; // nCr の n の最大値?
    static int MOD = 1000000007;
    static long[] fac = new long[max];
    static long[] finv = new long[max];
    static long[] inv = new long[max];

    public static void main(String[] args) {
        COMinit(); // 初期化
        long ans = COM(114514, 81019); // 114514 C 81019 の 10^9+7で割ったあまり
        System.out.println(ans); // -> 599343096
    }

    static void COMinit() { // テーブルを作るみたい nCrの または 階乗の？
        fac[0] = fac[1] = 1;
        finv[0] = finv[1] = 1;
        inv[1] = 1;
        for (int i = 2; i < max; i++) {
            fac[i] = fac[i - 1] * i % MOD;
            inv[i] = MOD - inv[MOD % i] * (MOD / i) % MOD;
            finv[i] = finv[i - 1] * inv[i] % MOD;
        }
    }

    static long COM(int n, int k) { // 二項係数の計算
        if (n < k)
            return 0;
        if (n < 0 || k < 0)
            return 0;
        return fac[n] * (finv[k] * finv[n - k] % MOD) % MOD;
    }
}