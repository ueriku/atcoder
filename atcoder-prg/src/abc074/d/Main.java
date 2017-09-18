package abc074.d;


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
        int N = in.nextInt();
        int[][] A = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = in.nextInt();
            }
        }

        // グラフAとなる道路の構造が存在しなければtrue
        boolean notExist = false;
        // 答えとなる道路の長さの和(存在しない場合-1)
        long ans = 0;

        // A[i][j]について調べる
        for (int i = 0; i < N; i++) {
            if (notExist) {
                break;
            }
            for (int j = i + 1; j < N; j++) {           
                if (notExist) {
                    break;
                }                
                // 道路の長さの和に加える必要がある場合true
                boolean add = true;
                // 各A[i][j]について，経由地kをとった場合を見ていく
                for (int k = 0; k < N; k++) {
                    // i == kまたはj == kの場合はカウントしない
                    if (i != k && j != k) {
                        // kを経由した際，そちらの方がA[i][j]より短い距離で行けてしまう道が存在する場合，
                        // このグラフAを満たす道路の組み合わせは存在しない                        
                        if (A[i][j] > A[i][k] + A[k][j]) {
                            notExist = true;
                            break;
                        }
                        // kを経由した際の距離がA[i][j]と等しいkが存在する場合，
                        // その距離A[i][j]は答えに加算しない(他の道路の距離を足し合わせることでカウントされるため)
                        else if (A[i][j] == A[i][k] + A[k][j]) {
                            add = false;
                        }
                    }
                }
                if (add) {
                    ans += A[i][j];
                }
            }   
        }
        // 存在しない場合はansに-1を入れる
        if (notExist) {
            ans = -1;
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
