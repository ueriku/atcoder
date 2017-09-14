package abc071.d;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class Main {
    FastScanner in = new FastScanner(System.in);
    PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        new Main().calc();
    }
    
    public void calc() {
        int N = in.nextInt();
        char[] S1 = in.next().toCharArray();
        char[] S2 = in.next().toCharArray();
        final int MOD = 1000000007;

        long ans = 0;
        // 左横のドミノが縦向きならtrueをとる
        boolean portrait = false;
        
        // S1について1文字ずつ見ていく
        for (int i = 0; i < S1.length; i++) {
            char c = S1[i];

            // ドミノが縦向きなら
            if (c == S2[i]) {
                if (i == 0) {
                    // 左端のドミノが縦向きなら最初の塗り方は3通り
                    ans = 3;
                }
                else {
                    if (portrait) {
                        // 縦 -> 縦なら*2通りする
                        ans = ((ans % MOD) * 2) % MOD;
                    }
                    else {
                        // 横 -> 縦なら1通りしかないので何もしない
                    }
                }
                // 前のドミノを縦向きにする
                portrait = true;
            }
            // ドミノが横向きなら
            else {
                if (i == 0) {
                    // 左端のドミノが横向きなら最初の塗り方は6通り
                    ans = 6;
                }               
                else {
                    if (portrait) {
                        // 縦 -> 横なら*2通りする
                        ans = ((ans % MOD) * 2) % MOD;
                    }
                    else {
                        // 横 -> 横なら*3通りする
                        ans = ((ans % MOD) * 3) % MOD;                        
                    }
                }
                // 前のドミノを横向きに設定して1つ多く進む
                portrait = false;
                i++;
            }
        }
        
        out.println(ans);
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
