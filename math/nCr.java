import java.util.Scanner;

class nCr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long ans = Combi(66, 33);
        System.out.println(ans);
    }

    public static long Combi(int n, int r) { // nCr
        if (r == 0 || r == n)
            return 1L;

        if (r > n - r) {
            r = n - r;
        }

        int[] bunsi = new int[r];
        int[] bunbo = new int[r];

        for (int k = 0; k < r; k++) {
            bunsi[k] = n - r + k + 1;
            bunbo[k] = k + 1;
        }

        for (int p = 2; p <= r; p++) {
            int pivot = bunbo[p - 1];
            if (pivot > 1) {
                int offset = (n - r) % p;
                for (int k = p - 1; k < r; k += p) {
                    bunsi[k - offset] /= pivot;
                    bunbo[k] /= pivot;
                }
            }
        }

        long kotae = 1;

        for (int k = 0; k < r; k++) {
            if (bunsi[k] > 1) {
                kotae *= bunsi[k];
            }
        }
        return kotae;
    }
}