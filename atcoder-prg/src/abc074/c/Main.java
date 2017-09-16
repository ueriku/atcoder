package abc074.c;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

public class Main {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        new Main().calc();
    }
    
    public void calc() {
        int A = in.nextInt();
        int B = in.nextInt();
        int C = in.nextInt();
        int D = in.nextInt();
        int E = in.nextInt();
        int F = in.nextInt();

        // 答えとなる水の量と砂糖の量，砂糖水の濃度。
        // 砂糖の量は0グラムになる可能性があるが，水は0グラムだと不正解になる
        // Fは100*Aよりは大きいという制限があるので，初期値として100*Aを入れておく
        int water = 100 * A;
        int sugar = 0;
        double noudo = 0.0;
        // Eから導出される，答えとなる濃度の最大値。
        // これを越えてしまうと不正解。
        double maxn = 100.0 * E / (100.0 + E);
        
        // 各操作を何回行うかをi1, i2, i3, i4として全ての場合を求める
        for (int i1 = 0; i1 <= F / A * 100; i1++) {
            for (int i2 = 0; i2 <= F / B * 100; i2++) {
                // この組み合わせの時点で総量がFグラムを越えていたらスキップする
                if (i1 * A * 100 + i2 * B * 100 > F) {
                    break;
                }
                
                for (int i3 = 0; i3 <= F / C; i3++) {
                    // この組み合わせの時点で総量がFグラムを越えていたらスキップする
                    if (i1 * A * 100 + i2 * B * 100 + i3 * C > F) {
                        break;
                    }                    
                    
                    for (int i4 = 0; i4 <= F / D; i4++) {
                        // i1~i4の組み合わせでの水と砂糖の量を計算する                        
                        int w = 100 * A * i1 + 100 * B * i2;
                        int s = C * i3 + D * i4;
                        // Fを越えていたらスキップ
                        if (w + s > F) {
                            break;
                        }
                        // 濃度を求める
                        double n = 100.0 * s / (w + s);
                        // 濃度がEから求められる最大値を越えておらず，
                        // 今までの計算の中での最大値より大きければその答えを保持する
                        if (n <= maxn && noudo < n) {
                            noudo = n;
                            water = w;
                            sugar = s;
                        }
                    }                       
                }
            }           
        }
            
        out.println((water + sugar) + " " + sugar);
        out.close();
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
