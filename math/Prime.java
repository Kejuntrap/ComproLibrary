
class Prime {
    public static void main(String[] args) {
        System.out.println(Prime(9722117)); // -> true
    }

    public static boolean Prime(int a) {

        if (a < 4) {

            if (a == 2 || a == 3) {
                return true;
            } else {
                return false;
            }
        } else {
            for (int j = 2; j * j <= a; j++) {
                if (a % j == 0) {
                    return false;
                }
                if (a % j != 0 && (j + 1) * (j + 1) > a) {
                    return true;
                }
            }
            return true;
        }
    }

    public static boolean Prime(long a) {

        if (a < 4) {

            if (a == 2 || a == 3) {
                return true;
            } else {
                return false;
            }
        } else {
            for (long j = 2; j * j <= a; j++) {
                if (a % j == 0) {
                    return false;
                }
                if (a % j != 0 && (j + 1) * (j + 1) > a) {
                    return true;
                }
            }
            return true;
        }
    }
}