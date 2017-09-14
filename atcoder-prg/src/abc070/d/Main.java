package abc070.d;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.TreeMap;

public class Main {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        new Main().calc();
    }
    
    // 木構造を表すリスト。
    // i番目のリストに入っているMapは，町iから伸びる辺を著す。 
    // MapのKeyは辺が続く町で，Valueは距離を示す。
    ArrayList<TreeMap<Integer, Long>> treeList = new ArrayList<TreeMap<Integer,Long>>();
    // dist[i]: 町iから町Kへの最短距離
    long[] dist;

    public void calc() {
        int N = in.nextInt();
        int[] a = new int[N-1];
        int[] b = new int[N-1];
        long[] c = new long[N-1];

        for (int i = 0; i < N-1; i++) {
            a[i] = in.nextInt() - 1;
            b[i] = in.nextInt() - 1;
            c[i] = in.nextLong();
        }
        
        int Q = in.nextInt();
        int K = in.nextInt() - 1;
        int[] x = new int[Q];
        int[] y = new int[Q];

        for (int i = 0; i < Q; i++) {
            x[i] = in.nextInt() - 1;
            y[i] = in.nextInt() - 1;
        }

        // treeListの初期化
        for (int i = 0; i < N; i++) {
            treeList.add(new TreeMap<Integer, Long>());
        }
        
        // 道の情報をtreeList（の中のMap）に入れていく
        for (int i = 0; i < N-1; i++) {
            treeList.get(a[i]).put(b[i], c[i]);
            treeList.get(b[i]).put(a[i], c[i]);
        }
        
        dist = new long[N];
        // 各点からKへの距離を求める
        dfs(-1, K, 0);
        
        for (int i = 0; i < Q; i++) {
            out.println(dist[x[i]] + dist[y[i]]);
        }
             
        out.close();
    }
    
    // 町Kから順に深さ優先探索を実施して，Kから各町への最短距離を求める
    // 引数によって，前の町はfrom，現在の町はtoであり，ここまで距離len掛かっていることを表す。
    // 町Kから現在地toまでの距離dist[to]を距離lenで初期化し，
    // 次の町への道をtreeListから取得して再帰を行う。
    // 再帰を行う際に町fromへ戻ってしまうと無限ループに陥るので，その前に来た道を除外している。
    void dfs(int from, int to, long len) {
        // toへの距離をlenで初期化
        dist[to] = len;
        // K番目の町から伸びた道の情報が入ったMapを取得
        TreeMap<Integer, Long> map = treeList.get(to);
        // 来た道を消す（探索から除外する）
        map.remove(from);
        // 次の町toに着いたものとし，そこから再度dfsする。
        // その際，今来た町toをfromとし，fromから続く道をMapから取り出して，次の町とそこまでの距離を計算する
        map.forEach((k, v) -> dfs(to, k, len+v));
    }
    
    class FastScanner {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public FastScanner(InputStream stream) {
            this.stream = stream;
        }

        int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        boolean isEndline(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] nextIntArray(int n) {
            int[] array = new int[n];
            for (int i = 0; i < n; i++)
                array[i] = nextInt();

            return array;
        }

        int[][] nextIntMap(int n, int m) {
            int[][] map = new int[n][m];
            for (int i = 0; i < n; i++) {
                map[i] = in.nextIntArray(m);
            }
            return map;
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        long[] nextLongArray(int n) {
            long[] array = new long[n];
            for (int i = 0; i < n; i++)
                array[i] = nextLong();

            return array;
        }

        long[][] nextLongMap(int n, int m) {
            long[][] map = new long[n][m];
            for (int i = 0; i < n; i++) {
                map[i] = in.nextLongArray(m);
            }
            return map;
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        double[] nextDoubleArray(int n) {
            double[] array = new double[n];
            for (int i = 0; i < n; i++)
                array[i] = nextDouble();

            return array;
        }

        double[][] nextDoubleMap(int n, int m) {
            double[][] map = new double[n][m];
            for (int i = 0; i < n; i++) {
                map[i] = in.nextDoubleArray(m);
            }
            return map;
        }

        String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        String[] nextStringArray(int n) {
            String[] array = new String[n];
            for (int i = 0; i < n; i++)
                array[i] = next();

            return array;
        }

        String nextLine() {
            int c = read();
            while (isEndline(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndline(c));
            return res.toString();
        }
    }
}
