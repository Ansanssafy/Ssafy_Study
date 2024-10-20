# 덱 (Deque)

## 1. 덱 (Deque)

- 덱(Deque)은 Double-Ended Queue의 약자로, 큐와 달리 양쪽 끝에서 삽입과 삭제가 모두 가능한 자료 구조이다.
- 덱은 스택과 큐의 기능을 모두 지원하는 자료 구조이다. 즉, 스택이나 큐를 사용해야 하는 상황에서 스택과 큐 대신 덱으로 대체하여 사용하는 것도 가능하다.

### (1) 덱의 연산

> C++의 deque 기준으로 설명

- `push_front(value)` : 덱의 앞쪽에 요소를 추가한다.
- `push_back(value)` : 덱의 뒤쪽에 요소를 추가한다.
- `pop_front()` : 덱의 앞쪽 요소를 제거한다.
- `pop_back()` : 덱의 뒤쪽 요소를 제거한다.
- `front()` : 덱의 앞쪽 요소를 반환한다.
- `back()` : 덱의 뒤쪽 요소를 반환한다.
- `empty()` : 덱이 비어 있으면 `true` 를, 그렇지 않으면 `false` 를 반환한다.
- `size()` : 덱에 있는 요소의 개수를 반환한다.

## 2. 덱의 구현

- 덱(Deque)은 배열이나 연결 리스트(Linked List)로 구현할 수 있다.
- 배열 기반 덱 : 고정 크기 배열이나 동적 배열을 사용하여 덱을 구현할 수 있다.
    - 동적 배열을 사용할 경우, 배열의 크기를 동적으로 조정할 수 있으므로 메모리 효율성을 높일 수 있다. 하지만 요소를 앞쪽에 삽입하거나 삭제할 때는 배열의 요소를 이동시켜야 하므로 시간 복잡도가 `O(N)` 이 될 수 있다.
- 연결 리스트 기반 덱 : 양방향 연결 리스트를 사용하면 덱의 양쪽 끝에서 삽입과 삭제를 `O(1)` 의 시간 복잡도로 처리할 수 있다.

### (1) C++에서의 Deque

- C++에서는 표준 라이브러리인 `<Deque>` 헤더를 통해 덱을 사용할 수 있다.
- 예제 코드
    ```cpp
    #include <iostream>
    #include <deque>
    using namespace std;
    
    int main() {
        deque<int> dq;
    
        // 요소 추가
        dq.push_back(10);  // { 10 }
        dq.push_back(20);  // { 10, 20 }
        dq.push_front(30); // { 30, 10, 20 }
    
        // 요소 접근
        cout << "Front : " << dq.front() << endl; // 30
        cout << "Back : " << dq.back() << endl; // 20
    
        // 요소 삭제
        dq.pop_front(); // { 10, 20 }
        dq.pop_front(); // { 20 }
        dq.pop_back();  // { }
    
        // 덱이 비어 있는지 확인
        if (dq.empty()) {
            cout << "Deque is empty!" << endl;
        }
        
        return 0;
    }
    ```

### (2) Java에서의 Deque

- Java에서는 `java.util.Deque` 인터페이스를 통해 덱을 사용할 수 있으며, `ArrayDeque` 클래스와 `LinkedList` 클래스로 두 가지 주요 구현체가 있다. 이 두 클래스는 각각 배열 기반 덱과 연결 리스트 기반 덱의 특징을 가지고 있다.
    - `ArrayDeque` 의 특징
        - 동적 배열을 사용하여 덱을 구현하며, 자동으로 크기를 조정한다.
        - thread-safe하지 않으므로, 멀티스레드 환경에서는 별도의 동기화가 필요하다.
        - 크기 제한이 없으며, 필요할 때 자동으로 크기를 늘린다.
        - 양쪽 끝에서의 삽입과 삭제가 `O(1)` 의 시간 복잡도를 갖는다.
    - `LinkedList` 의 특징
        - 양방향 연결 리스트를 기반으로 덱을 구현한다.
        - 메모리를 연속하여 사용하지 않으므로 메모리 사용이 분산될 수 있다.
        - 추가 메모리 사용이 발생할 수 있으며, 메모리 관리가 필요할 때 성능에 영향을 줄 수 있다.
- 예제 코드
    ```java
    import java.util.Deque;
    import java.util.ArrayDeque;
    
    public class Main {
        public static void main(String[] args) {
            Deque<Integer> deque = new ArrayDeque<>();
    
            // 요소 추가
            deque.addLast(10);  // { 10 }
            deque.addLast(20);  // { 10, 20 }
            deque.addFirst(30); // { 30, 10, 20 }
    
            // 요소 접근
            System.out.println("Front : " + deque.peekFirst()); // 30
            System.out.println("Back : " + deque.peekLast());   // 20
    
            // 요소 삭제
            deque.removeFirst(); // { 10, 20 }
            deque.removeFirst(); // { 20 }
            deque.removeLast();  // { }
    
            // 덱이 비어 있는지 확인
            if (deque.isEmpty()) {
                System.out.println("Deque is empty!");
            }
        }
    }
    ```
- 참고 : Java의 Collection 구조 (이미지 출처 : https://bangu4.tistory.com/194 )
    ![java collection](./img/java%20collection.jpg)
