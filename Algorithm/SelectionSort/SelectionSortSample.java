import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
5
3 9 1 7 5
 */

public class SelectionSortSample {

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





        // 선택 정렬
        for (int i = 0; i < n - 1; i++) {
            int minNumber = numbers[i];
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (numbers[j] < minNumber) {
                    minNumber = numbers[j];
                    minIndex = j;
                }
            }

            swap(numbers, i, minIndex);
            System.out.printf("%d번째 원소 선택 -> %d번째 원소와 교환%n", i, minIndex);
            System.out.println(Arrays.toString(numbers));
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
