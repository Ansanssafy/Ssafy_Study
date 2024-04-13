package Solo;

import java.io.*;
import java.util.*;

public class swea_5644_무선충전 {
    static BufferedReader br;
    static StringTokenizer st, st2;
    static StringBuilder sb = new StringBuilder();
    static int[] A, B, dr, dc, battery;
    static int[][] board;
    static String[][] check;
    static Queue<int[]> queue;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        // X 상 우 하 좌
        dr = new int[]{0, -1, 0, 1, 0};
        dc = new int[]{0, 0, 1, 0, -1};
        
        for(int t=1; t<=T; t++) {
            board = new int[10][10];
            check = new String[10][10];
            for(int i=0; i<10; i++) {
                Arrays.fill(check[i], "");
            }
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            A = new int[M+1];
            B = new int[M+1];
            st = new StringTokenizer(br.readLine());
            st2 = new StringTokenizer(br.readLine());
            for(int i=1; i<=M; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                B[i] = Integer.parseInt(st2.nextToken());
            }
            battery = new int[N+1];
            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken())-1;
                int r = Integer.parseInt(st.nextToken())-1;
                int cv = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                if(!check[r][c].contains(i+"")) check[r][c] += i;
                queue = new LinkedList<>();
                queue.add(new int[] {r, c});
                bfs(cv, i);
                battery[i] = p;
            }
            
            // p순에 맞게 정렬
            ArraySort();
            
            int Ar = 0; int Ac = 0;
            int Br = 9; int Bc = 9;
            int res =0;
            for(int i=0; i<=M; i++) {
            	Ar += dr[A[i]]; Ac += dc[A[i]];
            	Br += dr[B[i]]; Bc += dc[B[i]];
                if(board[Ar][Ac]==0 && board[Br][Bc]==0) continue;
                if(board[Ar][Ac]==board[Br][Bc]) {
                    if(check[Ar][Ac].length()==1 && check[Br][Bc].length()==1) {
                        res+=battery[board[Ar][Ac]];
                    } else if (check[Ar][Ac].length()>1 && check[Br][Bc].length()>1){
                        res+=battery[board[Ar][Ac]];
                        int a = check[Ar][Ac].charAt(1)-'0';
                        int b = check[Br][Bc].charAt(1)-'0';
                        if(battery[a]<battery[b]) {
                            res+=battery[b];
                        } else {
                            res+=battery[a];
                        }
                    } else if (check[Ar][Ac].length()==1 && check[Br][Bc].length()>1){
                        res+=battery[board[Ar][Ac]];
                        res+=battery[check[Br][Bc].charAt(1)-'0'];
                    } else if (check[Ar][Ac].length()>1 && check[Br][Bc].length()==1){
                        res+=battery[check[Ar][Ac].charAt(1)-'0'];
                        res+=battery[board[Br][Bc]];
                    } 
                    
                } else {
                    res+=battery[board[Ar][Ac]];
                    res+=battery[board[Br][Bc]];
                }
            }
            sb.append("#").append(t).append(" ").append(res).append("\n");
        }
        System.out.println(sb);
    }
    
    public static void bfs(int cv, int bn) {
        for(int i=0; i<cv; i++) {
            int size = queue.size();
            for(int j=0; j<size; j++) {
                int[] now = queue.poll();
                for(int d=1; d<5; d++) {
                    int nr = now[0]+dr[d];
                    int nc = now[1]+dc[d];
                    if(nr>=0 && nr<10 && nc>=0 && nc<10) {
                        if(!check[nr][nc].contains(bn+"")) check[nr][nc] += bn;
                        queue.add(new int[] {nr, nc});
                    }
                }
            }
        }
        
    }
    
    public static void ArraySort() {
        for(int r=0; r<10; r++) {
            for(int c=0; c<10; c++) {
                if(check[r][c].length()>0) {
                    Integer[] tmp = new Integer[check[r][c].length()];
                    for(int b=0; b<check[r][c].length(); b++) {
                        tmp[b] = check[r][c].charAt(b)-'0';
                    }
                    Arrays.sort(tmp, new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return battery[o2]-battery[o1];
                        }
                    });
                    
                    StringBuilder sb2 = new StringBuilder();
                    for (int num : tmp) {
                        sb2.append(num);
                    }
                    check[r][c] = sb2.toString();
                    board[r][c] = check[r][c].charAt(0)-'0';
                }
            }
        }
    }
//    
//    public static int findMax(int r, int c) {
//        int max = Integer.MIN_VALUE;
//        int maxIdx = -1;
//        for(int i=0; i<check[r][c].length(); i++) {
//            int now = check[r][c].charAt(i)-'0';
//            if(battery[now][3]>max) {
//                max = battery[now][3];
//                maxIdx = now;
//            }
//        }
//        return maxIdx;
//    }
}