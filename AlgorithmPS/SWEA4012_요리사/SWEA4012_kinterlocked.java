import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 요리사 문제
public class SWEA4012_kinterlocked {  
	static int N, ans;
    static int[][] arr;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
    	int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            
            ans = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N];
            arr = new int[N][N];
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0, 0); 
            System.out.println("#" + tc + " " + ans);
        }
    }
    
    public static void dfs(int depth, int Foodnum) {
        
        if (depth >= N)
            return;
        
        if (Foodnum == N / 2) {
            List<Integer> A = new ArrayList<>();
            List<Integer> B = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (visited[i])
                    A.add(i);
                else
                    B.add(i);
            }
            int a = 0, b = 0;
            for (int i = 0; i < A.size() - 1; i++) {
                for (int j = i + 1; j < A.size(); j++) {
                    a += arr[A.get(i)][A.get(j)];
                    b += arr[B.get(i)][B.get(j)];
                    a += arr[A.get(j)][A.get(i)];
                    b += arr[B.get(j)][B.get(i)];
                }
            }
            ans = Math.min(ans, Math.abs(a - b));
            return;
        }
        
        visited[depth] = true;
        dfs(depth + 1, Foodnum + 1);
        
        visited[depth] = false;
        dfs(depth + 1, Foodnum);
    }
}
