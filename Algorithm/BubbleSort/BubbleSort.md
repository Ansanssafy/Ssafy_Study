# 버블 정렬 (Bubble Sort)

- 각 반복마다 정렬되지 않은 원소들에 대해 앞에서부터 "서로 인접한 두 원소"를 비교하여 정렬하는 알고리즘이다.

- 이 방식에서는 각 반복마다 가장 큰 값이 정렬되지 않은 원소들 중 맨 뒤쪽으로 가게 된다.

- 따라서 정렬된 원소들은 뒤쪽에 차례로 쌓이게 된다.

<img src="img/BubbleSort.jpg">

## 시간복잡도 : O(n^2)

- 0번째 반복에서 n - 1번의 비교 연산 수행

- 1번째 반복에서 n - 2번의 비교 연산 수행

- 즉, 버블 정렬에서는 총 (n - 1) + (n - 2) + ... + 2 + 1 = n * (n - 1) / 2 번의 비교 연산이 수행된다.

- 따라서 최선, 평균, 최악의 경우 모두 시간복잡도는 O(n^2)이 된다.

## 공간복잡도 : O(n)

- 주어진 배열 안에서 교환(swap)을 통해 정렬이 수행되므로 추가적인 메모리 공간이 필요하지 않다.

## 장점

- 알고리즘이 단순하다.

- 공간복잡도가 O(n)이며, 추가적인 메모리 공간을 필요로 하지 않는다.

- 안정 정렬(Stable sort)이다.

## 단점

- 시간복잡도가 O(n^2)으로, 비효율적이다.

- 최악의 경우, 정렬되어있지 않은 원소가 정렬되었을 때의 자리로 가기 위해서 교환 연산(swap)이 많이 일어나게 된다.