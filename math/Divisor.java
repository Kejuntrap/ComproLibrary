import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

class Divisor {
    public static void main(String[] args) {
        ArrayList<Integer> rek = Divrekkyo(1102701600); // -> #1440
        System.out.println(rek.size());
        ArrayList<Long> rekZ = Divrekkyo(897612484786617600L); // -> #103680
        System.out.println(rekZ.size());
    }

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

    public static ArrayList<Integer> Divrekkyo(int a) {
        ArrayList<Integer> p = new ArrayList<Integer>();
        Factor(a, p);
        HashMap<Integer, Integer> yakkaz = new HashMap<Integer, Integer>();
        for (int num : p) {
            if (yakkaz.containsKey(num)) {
                yakkaz.put(num, yakkaz.get(num) + 1);
            } else {
                yakkaz.put(num, 1);
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(1);
        for (int keys : yakkaz.keySet()) {
            ArrayList<Integer> subset = new ArrayList<Integer>();
            for (int i = 1; i <= yakkaz.get(keys); i++) {
                subset.add(pow(keys, i));
            }
            int vol = res.size();
            for (int j = 0; j < subset.size(); j++) {
                for (int k = 0; k < vol; k++) {
                    res.add(res.get(k) * subset.get(j));
                }
            }
        }
        return res;
    }

    public static ArrayList<Long> Divrekkyo(long a) {
        ArrayList<Long> p = new ArrayList<Long>();
        Factor(a, p);
        HashMap<Long, Long> yakkaz = new HashMap<Long, Long>();
        for (long num : p) {
            if (yakkaz.containsKey(num)) {
                yakkaz.put(num, yakkaz.get(num) + 1L);
            } else {
                yakkaz.put(num, 1L);
            }
        }
        ArrayList<Long> res = new ArrayList<Long>();
        res.add(1L);
        for (long keys : yakkaz.keySet()) {
            ArrayList<Long> subset = new ArrayList<Long>();
            for (long i = 1; i <= yakkaz.get(keys); i++) {
                subset.add(pow(keys, i));
            }
            int vol = res.size();
            for (int j = 0; j < subset.size(); j++) {
                for (int k = 0; k < vol; k++) {
                    res.add(res.get(k) * subset.get(j));
                }
            }
        }
        return res;
    }

    public static int pow(int a, int b) { // a^b
        if (b == 0) {
            return 1;
        } else if (b == 1) {
            return a;
        } else {
            if (b % 2 == 0) {
                return pow(a, b / 2) * pow(a, b / 2);
            } else if (b % 2 == 1) {
                return pow(a, b - 1) * a;
            }
        }
        return 1;
    }

    public static long pow(long a, long b) { // a^b
        if (b == 0) {
            return 1L;
        } else if (b == 1) {
            return (long) a;
        } else {
            if (b % 2 == 0) {
                return pow(a, b / 2) * pow(a, b / 2);
            } else if (b % 2 == 1) {
                return pow(a, b - 1) * ((long) a);
            }
        }
        return 1L;
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
