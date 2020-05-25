import java.util.Scanner;

class LCMGCD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long gcd = gcd(64, 28);
        System.out.println(gcd); // -> 4
        long lcm = lcm(64, 28);
        System.out.println(lcm); // ->448
    }

    public static long lcm(long m, long n) {
        return m / gcd(m, n) * n;
    }

    public static long gcd(long m, long n) {
        if (m < n)
            return gcd(n, m);
        if (n == 0)
            return m;
        return gcd(n, m % n);
    }
}