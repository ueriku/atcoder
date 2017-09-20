package abc069.d;


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
        int H = in.nextInt(), W = in.nextInt(), N = in.nextInt();
        int[] a = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        
        int[][] ans = new int[H][W];

        // 1 = 下/右方向 -1 = 上/左方向 0 = 移動しない
        int dirH = 1;
        int dirW = 0;
        // h, wの初期値
        int height = -1;
        int width = 0;

        // 各色について順番に塗っていく
        for (int color = 0; color < N; color++) {
            // 色の数a[color]だけ塗りつぶしていく
            for (int i = 0; i < a[color]; i++) {
                // 次のマスへ移動
                height += dirH;
                width += dirW;
                
                // H * Wのマス目を越えてしまっている，もしくは移動先が既に塗られている場合
                if (height < 0 || height >= H || width < 0 || width >= W || ans[height][width] != 0) {
                    // 移動する前に戻る
                    height -= dirH;
                    width -= dirW;
                    // 向きを変える(下 -> 右 -> 上 -> 左の順に向きを変えていく)
                    if (dirH == 0) {
                        dirH = -dirW;
                        dirW = 0;
                    }
                    else {
                        dirW = dirH;
                        dirH = 0;                        
                    }
                    // 今回のループでは何も塗らなかったので回数も戻す
                    i--;
                }
                // 普通に濡れる場合
                else {
                    // マス目を塗りつぶす(色は1オリジンなので1足す)
                    ans[height][width] = color + 1;
                }
            }
        }
        
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                out.print(ans[i][j] + " ");                
            }
            out.println();
        }

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
