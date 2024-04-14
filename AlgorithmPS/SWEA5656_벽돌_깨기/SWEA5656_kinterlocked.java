import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 벽돌 문제
public class SWEA5656_kinterlocked {

	static int N, W, H, res;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			int total = 0;
			int[][] map = new int[H][W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] > 0)
						total++;
				}
			}

			res = Integer.MAX_VALUE;
			process(0, total, map);

			System.out.println("#" + tc + " " + res);
		}
	}

	public static boolean process(int cnt, int remain, int[][] map) {
		if (remain == 0) {
			res = 0;

			return true;
		}
		if (cnt == N) {
			res = Math.min(res, remain);

			return false;
		}
		int[][] newMap = new int[H][W];

		for (int c = 0; c < W; c++) {
			int r = 0;
			while (r < H && map[r][c] == 0) {
				++r;
			}
			if (r == H) {
				continue;
			}
			copy(map, newMap);

			int burstCnt = burst(newMap, r, c);

			down(newMap);

			if (process(cnt + 1, remain - burstCnt, newMap))
				return true;
		}
		return false;
	}

	private static void down(int[][] map) {

		for (int c = 0; c < W; c++) {
			int r = H - 1;
			while (r > 0) {
				if (map[r][c] == 0) {
					int nr = r - 1;

					while (nr > 0 && map[nr][c] == 0) {
						nr--;
					}
					map[r][c] = map[nr][c];
					map[nr][c] = 0;
				}
				r--;
			}
		}
	}

	public static int burst(int[][] map, int r, int c) {

		int cnt = 0;
		Deque<Node> q = new ArrayDeque<>();

		if (map[r][c] > 1)
			q.offerLast(new Node(r, c, map[r][c]));

		map[r][c] = 0;
		cnt++;

		while (!q.isEmpty()) {
			Node now = q.pollFirst();

			for (int d = 0; d < 4; d++) {
				int rr = now.r;
				int cc = now.c;

				for (int k = 0; k < now.cnt - 1; k++) {
					rr += dr[d];
					cc += dc[d];

					if (rr < 0 || rr >= H || cc < 0 || cc >= W || map[rr][cc] == 0)
						continue;
					if (map[rr][cc] > 1)
						q.offerLast(new Node(rr, cc, map[rr][cc]));

					map[rr][cc] = 0;
					cnt++;
				}
			}
		}

		return cnt;
	}

	public static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}
}
class Node {
	int r;
	int c;
	int cnt;

	public Node (int r, int c, int cnt) {
		this.r = r;
		this.c = c;
		this.cnt = cnt;
	}
}