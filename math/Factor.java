import java.util.ArrayList;
import java.util.Scanner;

class Factor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> res1 = new ArrayList<Integer>();
        Factor(9240, res1);
        System.out.println(res1.toString()); // -> [2, 2, 2, 3, 5, 7, 11]
        ArrayList<Long> res2 = new ArrayList<Long>();
        Factor(304250263527210L, res2); // -> [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41]
        System.out.println(res2.toString());
    }

    // 総称型使えよ…
    public static void Factor(int a, ArrayList<Integer> p) {// int型用
        for (int i = 2; i * i <= a; i++) {
            if (a % i == 0) {
                p.add(i);
                Factor(a / i, p);
                return;
            }
        }
        p.add(a);
    }

    public static void Factor(long a, ArrayList<Long> p) {// long型用
        for (long i = 2; i * i <= a; i++) {
            if (a % i == 0) {
                p.add(i);
                Factor(a / i, p);
                return;
            }
        }
        p.add(a);
    }
}