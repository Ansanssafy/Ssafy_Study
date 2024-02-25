import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DivideAndConquer {

    static int n;
    static int[][] board;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        sb = new StringBuilder();

        for (int x = 0; x < n; x++) {
            String lineRead = br.readLine();

            for (int y = 0; y < n; y++) {
                board[x][y] = lineRead.charAt(y) - '0';
            }
        }

        recur(n, 0, 0);
        System.out.println(sb);
        br.close();
    }

    static void recur(int size, int xBegin, int yBegin) {
        boolean sameArea = true;

        for (int x = xBegin; x < xBegin + size; x++) {
            for (int y = yBegin; y < yBegin + size; y++) {
                if (board[x][y] != board[xBegin][yBegin]) {
                    sameArea = false;
                    break;
                }
            }

            if (!sameArea) break;
        }

        if (sameArea) {
            sb.append(board[xBegin][yBegin]);
        } else {
            sb.append("(");
            recur(size / 2, xBegin, yBegin);
            recur(size / 2, xBegin, yBegin + size / 2);
            recur(size / 2, xBegin + size / 2, yBegin);
            recur(size / 2, xBegin + size / 2, yBegin + size / 2);
            sb.append(")");
        }
    }

}
