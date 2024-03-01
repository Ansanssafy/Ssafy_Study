package 슬라이딩윈도우;

import java.util.*;

public class example {
    public static void main(String[] args) {
        // 연속된 3개 숫자의 최댓값
        int[] data = {1, 10, 30, 2, 44, 16, 8, 31, 22};
        int N = data.length; // 데이터 크기
        int K = 3; // 윈도우 크기
        int window = data[0]+data[1]+data[2]; // 윈도우
        int max = window; // 결과 변수

        for(int i=K; i<N; i++) {
            window  = window + data[i] - data[i-K]; 
            System.out.println("minus : " + (i-K) + " / plus : " + i + " / res : "+ (i-K+1) +"~"+i);
            max = Math.max(window, max);
        }
        System.out.println(max);
    }
}