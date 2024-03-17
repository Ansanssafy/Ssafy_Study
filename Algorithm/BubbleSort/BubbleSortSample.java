import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
4
3 2 4 1
 */

public class BubbleSortSample {

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }



        System.out.println("최초 배열");
        System.out.println(Arrays.toString(numbers));
        System.out.println();



        // 버블 정렬
        for (int i = 0; i < n - 1; i++) {
            System.out.println(i + "번째 반복 진행");

            for (int j = 1; j < n - i; j++) {
                if (numbers[j - 1] > numbers[j]) {
                    swap(numbers, j - 1, j);
                }

                System.out.printf("%d번째와 %d번째 교환 시도%n", j - 1, j);
                System.out.println(Arrays.toString(numbers));
            }

            System.out.println();
        }

        System.out.println("최종 결과");
        System.out.println(Arrays.toString(numbers));
        br.close();
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
