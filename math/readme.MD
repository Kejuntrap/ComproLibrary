# math

- LCMGCD.java

  - lcm,gcd どちらもインポートしてください。
  - ユークリッドの互除法によって高速に gcd,lcm を求めることができます。
  - lcm,gcd ともに 2 つの正整数を引数にして渡すと、lcm,gcd を返します。

- nCr.java

  - Combi をインポートしてください。mod を取るものは別にあるのでそちらをインポートしてください。
  - 約分法によって比較的大きめな nCr を求めることができます。(66C33 程度まで)
  - Combi に n と r の 2 つの正整数を引数として渡すと、nCr を返します。

- Factor.java

  - Factor をインポートしてください。
  - 素因数分解をすることができます。
  - 答えを入れる ArrayList と求める数を渡すと、結果を ArrayList で返します。

- Prime.java

  - Prime をインポートしてください。
  - 素数判定ができます。O(sqrt(N))
  - 素数判定したい正の整数を引数として渡すと、素数かどうか(boolean)を返します。

- Root.java

  - Root をインポートしてください。
  - 開平法による平方根導出。与えられた数字以下の最大の平方根を返します。
  - 平方根を求める値を引数として渡すと、平方根を返します。

- nCrmod.java

  - COMinit と COM といくつかの static 変数をインポートしてください。
  - nCr mod p を計算します。
  - n,r,p を渡すと、nCr mod p を返します。

- Divisor.java

  - Factor,pow,Divrekkyo をインポートしてください。
  - 約数を全列挙します。列挙順はソートされていません。 long で表せる最も多い約数を持つ数の全列挙でも AtCoder で 90ms 程度と非常に高速です。
  - 約数全列挙した値を引数で渡すと、約数全列挙した ArrayList を返します。

- SegTree.java
  - SegTree クラスをインポートしてください。
  - RM(minimum)Q,RS(sum)Q のみ実装されている基本的なセグ木です。
  - RMQ や RSQ のクエリを投げるとそれに相当する結果を返します。
