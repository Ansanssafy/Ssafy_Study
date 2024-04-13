package Solo;

import java.io.*;
import java.util.*;
 
public class swea_4012_요리사2 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[][] synergy;
    static int[] A, B;
    static int N, asize, bsize, ascore, bscore, min;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            synergy = new int[N][N];
            for(int r=0; r<N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<N; c++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    synergy[r][c] += tmp;
                    synergy[c][r] += tmp;
                }
            }
            A = new int[N/2];
            B = new int[N/2];
            asize = bsize = ascore = bscore = 0;
            A[asize++] = 0;
            min = Integer.MAX_VALUE;
            Ingredient(1);
             
             
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
     
    // idx번째 원소를 선택할지 말지
    public static void Ingredient(int idx) {
        // 기저 조건
        // 식재료가 0부터 N-1까지 있으므로
        if(idx >= N) {
            min = Math.min(min, Math.abs(ascore-bscore));
            return;
        }
         
        // 선택하는 경우 > 식재료 A에 넣음
        if(asize < N/2) {
             
            int addScore = 0;
            for(int i=0; i<asize; i++) {
                addScore+=synergy[A[i]][idx];
            }
             
            A[asize++] = idx;
            ascore += addScore;
            Ingredient(idx+1); 
            asize--;
            ascore -= addScore;
        }
         
        // 선택하지 않는 경우 > 식재료 B에 넣음
        if(bsize < N/2) {
            int addScore = 0;
            for(int i=0; i<bsize; i++) {
                addScore+=synergy[B[i]][idx];
            }
            B[bsize++] = idx;
            bscore += addScore;
            Ingredient(idx+1);
            bsize--;
            bscore -= addScore;
        }
    }
}