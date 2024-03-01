package 슬라이딩윈도우;

import java.io.*;
import java.util.*;

public class boj_21921_블로그 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		long window = 0;
		int[] blog = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			blog[i] = Integer.parseInt(st.nextToken());
			if(i<X) window += blog[i];
		}
		long max = window;
		int maxCnt = 1;
		
		for(int i=X; i<N; i++) {
			window = window + blog[i] - blog[i-X];
			if(max<=window) {
				if(max==window) maxCnt++;
				else {
					max = window;
					maxCnt = 1;
				}
			}
		}
		
		if(max==0) sb.append("SAD");
		else {
			sb.append(max).append("\n").append(maxCnt);
		}
		System.out.println(sb);
		
	}
}
