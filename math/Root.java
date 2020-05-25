class Root {
    public static void main(String[] args) {
        System.out.println(Root(114514)); // -> 338
        System.out.println(Root(1125899906842625L)); // -> 33554432
    }

    static int Root(int a) {
        int ketasu = 0;
        int tmp = a;
        while (tmp > 0) {
            ketasu++;
            tmp /= 10;
        }
        int constant = (ketasu + 1) / 2; // よく使うので定数化
        if (ketasu >= 1) {
            int[] suuji = new int[constant];
            tmp = a;
            for (int i = 0; i < constant; i++) {
                suuji[constant - 1 - i] = a % 100;
                a /= 100;
            }
            int ans = 0;
            int kai = 0;
            int mae = 0;
            if (constant <= 1) {
                for (int i = 10; i >= 0; i--) {
                    if (suuji[0] >= i * i) {
                        return i;
                    }
                }
            } else {
                for (int i = 0; i < constant; i++) {
                    mae += kai * 2;
                    for (int j = 9; j >= 0; j--) {
                        if ((mae * 10 + j) * j <= suuji[i]) {
                            ans = ans * 10 + j;
                            mae *= 10;
                            kai = j;
                            if (i + 1 < constant) {
                                suuji[i + 1] += (suuji[i] - (mae + j) * j) * 100;
                            }
                            break;
                        }
                    }
                }
            }
            return ans;
        } else {
            return 0;
        }
    }

    static long Root(long a) {
        int ketasu = 0;
        long tmp = a;
        while (tmp > 0) {
            ketasu++;
            tmp /= 10L;
        }
        int constant = (ketasu + 1) / 2; // よく使うので定数化
        if (ketasu >= 1) {
            long[] suuji = new long[constant];
            tmp = a;
            for (int i = 0; i < constant; i++) {
                suuji[constant - 1 - i] = a % 100L;
                a /= 100L;
            }
            long ans = 0;
            long kai = 0;
            long mae = 0;
            if (constant <= 1) {
                for (long i = 10L; i >= 0L; i--) {
                    if (suuji[0] >= i * i) {
                        return i;
                    }
                }
            } else {
                for (long i = 0L; i < constant; i++) {
                    mae += kai * 2L;
                    for (long j = 9L; j >= 0L; j--) {
                        if ((mae * 10 + j) * j <= suuji[(int) i]) {
                            ans = ans * 10 + j;
                            mae *= 10;
                            kai = j;
                            if (i + 1 < constant) {
                                suuji[(int) i + 1] += (suuji[(int) i] - (mae + j) * j) * 100;
                            }
                            break;
                        }
                    }
                }
            }
            return ans;
        } else {
            return 0L;
        }
    }
}