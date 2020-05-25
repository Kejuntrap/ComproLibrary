import java.util.Arrays;
import java.util.Scanner;

class SAIS {
    static char saisyo = '!';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int[] res = SAISENGINE(s);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    public static int[] SAISENGINE(String s) {
        int[] str = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            str[i] = s.charAt(i) - saisyo + 1;
        }
        int[] result = SAIS(str, 127);
        return result;
    }

    public static int[] SAIS(int[] s, int spe) {
        if (s.length == 1) {
            return new int[] { 0 };
        } else {
            int len = s.length + 1; // 末尾文字も含めた文字数
            int[] str = new int[len];
            for (int i = 0; i < s.length; i++) {
                str[i] = s[i]; // 受け取った文字列の配列は末尾文字が含まれてないので新規に配列を作って代入する必要がある
            }
            str[str.length - 1] = 0;
            int[] LS = new int[len]; // L型S型を入れる配列 L=1 S=0
            int[] LMS = new int[len]; // LMSかどうかを入れる配列 LMSなら正の整数 そうでないなら-1
            int[] LMSsublen = new int[len]; // LMS部分文字列の長さを入れる配列
            int[] LMSlist; // LMSのインデックスのみを入れる配列（LMSの数は元の文字列の長さからわからないので、可変長配列のほうが楽）
            int LMScount = 0; // LMSの個数（LMSのみを集めたリストを可変長配列で持てばわざわざインデックス管理をしなくて済む）
            int[] used = new int[len]; // SuffixArrayに入れたかどうかの配列
            Arrays.fill(used, 0); // 初期化 使っていると1で使ってないと0を入れる
            SA[] sa = new SA[spe]; // SAがSuffixArrayと入れるインデックスを保持する
            int[] dict = new int[spe]; // どの文字が何回出てくるかを入れる配列
            for (int i = 0; i < len; i++) {
                dict[str[i]]++;
            }
            for (int i = 0; i < spe; i++) {
                sa[i] = new SA(dict[i]); // SuffixArrayの初期化
            }
            Arrays.fill(LMS, -1);
            Arrays.fill(LMSsublen, 0);
            LS[len - 1] = 0; // L型かS型の計算
            for (int i = len - 2; i >= 0; i--) { // 後ろから見ていく
                if (str[i] == str[i + 1]) {
                    LS[i] = LS[i + 1]; // 隣接する2文字が同じなら後ろの文字依存
                } else if (str[i] > str[i + 1]) {
                    LS[i] = 1; // L
                } else if (str[i] < str[i + 1]) {
                    LS[i] = 0; // S
                }
            }
            for (int i = 1; i < len; i++) { // LMSを調べる
                if (LS[i] == 0 && LS[i - 1] == 1) {
                    LMS[i] = 1; // LMS
                    LMScount++; // LMSの数をカウント
                } else {
                    LMS[i] = -1; // LMSで無いとき-1
                }
            }
            LMS[0] = -1; // 最初の文字はLMSにならない
            LMSlist = new int[LMScount]; // LMSのみの配列
            int tmpindex = 0;
            for (int i = 0; i < len; i++) {
                if (LMS[i] == 1) {
                    LMSlist[tmpindex] = i; // LMSのインデックスのみが入った配列を作る
                    tmpindex++;
                }
            }
            // ココまで確認済み5/13 標識番号1
            sa = resetindex(sa);
            for (int i = 0; i < LMSlist.length; i++) { // LMS部分文字列の長さを計算する
                if (LMSlist[i] != len - 1) {
                    LMSsublen[LMSlist[i]] = LMSlist[i + 1] - LMSlist[i]; // LMSとLMSの間の長さがLMS部分文字列の長さ
                } else {
                    LMSsublen[LMSlist[i]] = 1; // 最後のLMSは末尾文字のみとし、その長さは1となる
                }
            }
            sa = resetindex(sa);// 4ステップ終わり
            for (int i = 0; i < LMSlist.length; i++) { // 1ステップ目 LMSを入れる
                int index = LMSlist[i];
                sa[str[index]].addusirokara(index); // 後ろから見て値を末尾に追加し続ける＝前から見て後ろから前に入れる
                used[index] = 1; // SuffixArrayに入れたので使用済みフラグを立てる
            } // 1ステップ目終わり
            sa = resetindex(sa); // 配列のインデックスを再計算

            for (int i = 0; i < sa.length; i++) { // 2ステップ目 L型の文字を入れる
                for (int j = 0; j < sa[i].size(); j++) {
                    int value = sa[i].get(j) - 1;
                    if (value >= 0 && used[value] == 0 && LS[value] == 1) {
                        used[value] = 1;
                        sa[str[value]].addmaekara(value); // 前から後ろに値を入れる
                    }
                }
            }
            sa = resetindex(sa);// 3ステップ終わり
            for (int i = 1; i < sa.length; i++) { // 3ステップ
                for (int j = 0; j < sa[i].size(); j++) {
                    int value = sa[i].get(j);
                    if (value >= 0 && LMS[value] == 1) {
                        sa[i].set(j, -1); // LMSをSuffixArrayから消す
                        used[value] = 0; // SuffixArrayからLMSをとったので未使用になった
                    }
                }
            } // 3ステップ終わり
            sa = resetindex(sa);// 4ステップ終わり
            for (int i = sa.length - 1; i >= 0; i--) { // 4ステップ
                for (int j = sa[i].size() - 1; j >= 0; j--) {
                    if (sa[i].get(j) != -1) {
                        int index = sa[i].get(j) - 1;
                        if (index >= 0 && used[index] == 0 && LS[index] == 0) {
                            sa[str[index]].addusirokara(index); // 値を後ろから前に入れる
                            used[index] = 1;
                        }
                    }
                }
            }
            sa = resetindex(sa);// 4ステップ終わり
            int counter = 0;
            int[] nowsa = new int[1]; // 今見ているLMS部分文字列を入れる配列
            int[] oldsa = new int[1]; // 1つ前のLMS部分文字列を入れる配列
            Arrays.fill(oldsa, -1);
            int[] LMScounter = new int[len];
            for (int i = 0; i < sa.length; i++) { // まず間違えなくココ
                for (int j = 0; j < sa[i].size(); j++) {
                    if (LMS[sa[i].get(j)] != -1) { // LMSだったらそこからLMS部分文字列がはじまる
                        counter++;
                        LMScounter[sa[i].get(j)] = counter;
                        nowsa = new int[LMSsublen[sa[i].get(j)] + 1]; // 始まるLMSのインデックスによってLMS部分文字列の長さはわかる
                        Arrays.fill(nowsa, -1);
                        tmpindex = 0; // LMS部分文字列のインデックス
                        for (int k = sa[i].get(j); k < len; k++) {
                            nowsa[tmpindex] = str[k];
                            tmpindex++;
                            if (k != sa[i].get(j) && LMS[k] != -1) {
                                break; // 次のLMSが来たらLMS部分文字列は完結
                            }
                        }
                        if (equals(oldsa, nowsa)/* equals(oldsa, nowsa) */) { // 今見ているLMS部分文字列と1つ前が同じなら同じ部分文字列に同じ番号を振る
                            counter--;
                            LMScounter[sa[i].get(j)] = counter;
                        }
                        oldsa = clone(nowsa);
                    }
                }
            }
            int[] new_str = new int[LMSlist.length];
            int[] LMSindex = new int[LMSlist.length + 1];

            for (int i = 0; i < LMSlist.length; i++) { // LMS部分文字列の辞書順を再帰のSAISで求める
                new_str[i] = LMScounter[LMSlist[i]];
                LMSindex[i] = LMSlist[i];
            }
            int[] newLMSindex = SAIS(new_str, counter + 1);

            sa = resetindex(sa);
            Arrays.fill(used, 0); // 全部未使用に戻る
            sa = clear(sa); // 新しいLMS部分文字列の辞書順が分かったのでまた入れなおすためにSuffixArrayの初期化

            for (int i = newLMSindex.length - 1; i >= 0; i--) { // 新1段階目
                int value = LMSindex[newLMSindex[i]];
                sa[str[value]].addusirokara(value);
                used[value] = 1;
            }
            sa = resetindex(sa);
            // 2ステップ これ以降は上の3ステップ、4ステップと同様
            for (int i = 0; i < sa.length; i++) {
                for (int j = 0; j < sa[i].size(); j++) {
                    int value = sa[i].get(j) - 1;
                    if (value >= 0 && used[value] == 0 && LS[value] == 1) {
                        used[value] = 1;
                        sa[str[value]].addmaekara(value);
                    }
                }
            }
            sa = resetindex(sa);// 2ステップ終わり
            for (int i = 1; i < sa.length; i++) { // 2.5ステップ
                for (int j = 0; j < sa[i].size(); j++) {
                    int value = sa[i].get(j);
                    if (value >= 0 && LMS[value] >= 1) {
                        sa[i].set(j, -1); // 戻す
                        used[value] = 0;
                    }
                }
            } // 2.5ステップ終わり
              // 3ステップ
            for (int i = sa.length - 1; i >= 0; i--) {
                for (int j = sa[i].size() - 1; j >= 0; j--) {
                    if (sa[i].get(j) != -1) {
                        int index = sa[i].get(j) - 1;
                        if (index >= 0 && used[index] == 0 && LS[index] == 0) {
                            sa[str[index]].addusirokara(index);
                            used[index] = 1;
                        }
                    }
                }
            }
            sa = resetindex(sa);// 3ステップ終わり
            int[] ret = convert(sa, len - 1); // 末尾文字のインデックスを除く
            return ret;
        }
    }

    static boolean equals(int[] a1, int[] a2) {
        if (a1.length != a2.length) {
            return false;
        } else {
            for (int i = 0; i < a1.length; i++) {
                if (a1[i] != a2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    static SA[] resetindex(SA[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i].resetindex();
        }
        return a;
    }

    static SA[] clear(SA[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i].reset();
        }
        return a;
    }

    static class SA {
        private int[] ary;
        private int mae, ato;

        SA(int volume) {
            ary = new int[volume];
            Arrays.fill(ary, -1);
            mae = 0;
            ato = ary.length - 1;
        }

        void addmaekara(int value) {
            if (mae < ary.length) {
                ary[mae] = value;
                mae++;
            }
        }

        void addusirokara(int value) {
            if (ato >= 0) {
                ary[ato] = value;
                ato--;
            }
        }

        void resetindex() {
            mae = 0;
            ato = ary.length - 1;
        }

        void set(int index, int element) {
            ary[index] = element;
        }

        void reset() {
            Arrays.fill(ary, -1);
            resetindex();
        }

        int get(int index) {
            return ary[index];
        }

        int size() {
            return ary.length;
        }
    }

    static int[] convert(SA[] s, int len) {
        int[] ret = new int[len];
        int counter = 0;
        for (int i = 1; i < s.length; i++) {
            for (int j = 0; j < s[i].size(); j++) {
                ret[counter] = s[i].get(j);
                counter++;
            }
        }
        return ret;
    }

    public static int[] clone(int[] a) {
        int[] ret = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            ret[i] = a[i];
        }
        return ret;
    }
}