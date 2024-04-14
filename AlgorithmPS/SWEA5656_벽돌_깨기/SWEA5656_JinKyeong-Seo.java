package Solo;

import java.io.*;
import java.util.*;

public class swea_5656_벽돌깨기 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, W, H, min;
    static int[] dr, dc, sel;
    static int[][] board, tmp;
    static Queue<int[]> queue;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        dr = new int[] {-1, 1, 0, 0};
        dc=  new int[] {0, 0, -1, 1};
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            board = new int[H][W];
            for(int r=0; r<H; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<W; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                }
            } // 입력 끝
            
            sel = new int[N];
            min = Integer.MAX_VALUE;
            perm(0);

            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
    
    public static void perm(int idx) {
    	if(idx==N) {
    		findMax(sel);
    		return;
    	}
    	
    	for(int i=0; i<W; i++) {
    		sel[idx] = i;
    		perm(idx+1);
    	}
    }
    
    public static void findMax(int[] sel) { 
    	tmp = new int[H][W];
    	for(int i=0; i<H; i++) {
    		tmp[i] = Arrays.copyOf(board[i], W);
    	}
    	
        for(int i=0; i<N; i++) {
        	int now = sel[i];
        	remove(now);
        	int res = 0;
        	for(int r=0; r<H; r++) {
        		for(int c=0; c<W; c++) {
        			if(tmp[r][c]!=0) res++;
        		}
        	} 
        	min = Math.min(res, min);
        	down();
        }
    }
    
    
    public static void remove(int c) {
        queue = new LinkedList<>();
        for(int j=0; j<H; j++) {
            if(tmp[j][c]!=0) {
                queue.add(new int[] {j, c});
                break;
            }
        }        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int size = tmp[now[0]][now[1]]-1;
            for(int d=0; d<4; d++) {
                int nr = now[0];
                int nc = now[1];
                for(int s=0; s<size; s++) {
                    nr+=dr[d];
                    nc+=dc[d];
                    if(nr>=0 && nr<H && nc>=0 && nc<W && tmp[nr][nc]!=0) {
                        queue.add(new int[] {nr, nc});
                    }
                }
            }
            tmp[now[0]][now[1]] = 0;
        }
    }
    
    public static void down() {
        for(int c=0; c<W; c++) {
            for(int r=H-2; r>=0; r--) {
                if(tmp[r][c]!=0) {
                    int tr = r;
                    int nr = r+1;
                    while(nr<H && tmp[nr][c]==0 ) {
                        int tmpV = tmp[nr][c];
                        tmp[nr][c] = tmp[tr][c];
                        tmp[tr][c] = tmpV;
                        tr++; nr++;
                    }
                }
            }
        }
    }
}