# 프로세스 동기화 (Process Synchronization)

## 1. 프로세스 동기화 (Process Synchronization)

- 프로세스 동기화는 **여러 프로세스가 공유하는 자원의 일관성을 유지하는 과정**이다.

- 프로세스는 동시에 실행되면서 서로 영향을 주고 받는데, 이 과정에서 **자원의 일관성이 보장되어야 한다.**

- 여러 프로세스가 공유자원을 사용하는 상황에서 race condition이 발생하면 공유자원의 신뢰성이 떨어진다. 이를 방지하기 위해 프로세스들이 공유자원을 사용할 때 특별한 규칙을 만드는 것이다.

### (1) Race Condition (경쟁 상태)

- Race condition은 여러 프로세스들이 공유자원에 동시에 접근하는 상황에서, **공유자원에 대한 접근 순서에 따라 실행 결과가 달라질 수 있는 상황**을 말한다.

- 즉, 공유자원에 동시에 접근할 때 자료의 일관성을 해치는 결과가 나올 수 있다.

### (2) Critical Section (임계구역)

- Critical section은 여러 프로세스가 자원을 공유하는 상황에서, **하나의 프로세스만 접근할 수 있도록 제한한 영역**을 말한다.

- Critical section은 race condition을 막을 수 있는 해결책이 될 수 있다.

## 2. 동기화 문제를 방지하기 위한 3가지 조건

### (1) Mutual Exclusion (상호 배제)

- **이미 한 프로세스가 critical section에서 실행되고 있다면, 다른 모든 프로세스는 critical section에 진입하지 않아야 한다.**

- 즉, 공유 자원에 동시에 접근할 수 있는 프로세스는 오직 하나 뿐이어야 한다.

### (2) Progress (진행)

- **Critical section에서 실행되고 있는 프로세스가 없다면**, critical section에 진입하려고 하는 프로세스가 존재하는 경우 **기다리지 않고 진입할 수 있어야 한다.**

### (3) Bounded waiting (한정 대기)

- 프로세스가 critical section에 들어가기 위해 요청한 후부터 그 요청이 허용될 때까지 **다른 프로세스들이 critical section에 들어가는 횟수에 한계가 있어야 한다.**

- 즉, critical section에 진입하려는 프로세스를 무한정 기다리게 해서는 안된다.

## 3. 동기화 문제를 해결하기 위한 방법

### (1) Hardware Atomic Instructions

- **하나의 명령어 단위로 되어있어서 atomic하게 동작**할 수 있는 방식이다.

- Spin lock 방식이므로 기다리는 동안 while 반복문을 계속 수행하는 busy waiting 상태가 된다는 단점이 있다.

- TestAndSet과 이를 인텔 방식으로 구현한 CompareAndSwap이 있다.

> 💡 **Spin Lock, Busy Waiting**
> 
> - Spin lock은 critical section에 진입할 수 없는 상황에서 진입이 가능할 때까지 **while 문을 끊임없이 수행하며 기다리는 상태**를 말한다.
> 
> - Spin lock은 critical section에 진입할 때까지 **CPU와 메모리를 계속 사용하는 busy waiting 상태**로 기다린다는 문제점이 있다. 하지만 더 큰 문제를 방지하기 위해 이를 감수하면서 spin lock 방식을 사용한다.
> 
> - 다만 악용을 방지하기 위해 일반 개발자에게 직접 제공되지는 않으며, 운영체제 전용으로만 사용한다. 대신 일반 개발자에게는 spin lock을 기초로 하여 만들어진 high-level synchronization tool이 제공된다. Spin lock은 다른 것들을 구현하기 위한 기초가 되므로 low-level mechanism에 해당한다.

### (2) Disabling Interrupts

- 소프트웨어적으로 처리하는 방법 중 **interrupt를 끄고 켜는 방식**이다.

- Critical section에 들어갈 때 interrupt를 disable 상태로 바꾸고 나올 때는 enable 상태로 바꿈으로써 **명령이 수행되는 동안 context switching이 일어나지 않도록 하는 방식**이다.

- Spin lock에서 발생할 수 있는 critical section을 방지할 수 있으며, 이를 **blocking lock**이라고 한다.

- 이 방식을 일반적으로 사용하게 되면 한 프로세스가 CPU를 독점하는 등 악용될 수 있기 때문에 일반 개발자가 이 방식을 직접 사용하지는 못한다.

- Spin lock과 마찬가지로 blocking lock 역시 high-level synchronization tool을 만드는 데 사용된다.

### (3) SW-only Algorithms

- **알고리즘을 이용**하여 소프트웨어적으로 해결하는 방법이다.

- **Peterson's Algorithm, Bakery Algorithm 등**이 있다.

## 4. Peterson's Algorithm

- **두 개의 프로세스가 존재할 때 사용하는 알고리즘**이다.

- 세 개 이상의 프로세스에서는 이 알고리즘을 적용할 수 없다는 단점이 있다.

### (1) 알고리즘에 대한 설명

- 우선 두 개의 프로세스 P_i과 P_j가 있고, 공유하는 공통 변수가 존재한다고 가정하자.

- **Algorithm 1**

    - **현재 critical section에 진입할 프로세스를 나타내는 하나의 변수** `turn` 을 두고, 일치하는 프로세스만 critical section에 진입하도록 하는 방식이다.

    ```cpp
    int turn; // turn == i이면 P_i는 critical section에 들어갈 수 있다.
    
    do {
        while (turn != i); // 본인의 차례가 아니라면 기다린다. (busy waiting)
        
        // critical section
        
        turn = j;
    } while (true);
    ```

    - 이 방식에서는 **mutual exclusion은 만족하지만 progress는 만족하지 못한다.**

    - 예를 들어, P_i가 turn = j를 수행하고 나서 P_j가 수행을 한 번 마치고 다시 critical section으로 진입하려는 경우, turn == i이기 때문에 P_j는 critical section에 진입할 수 없고 critical section에는 어떤 프로세스도 존재하지 않게 되기 때문이다.

- **Algorithm 2**

    - **특정 프로세스가 critical section에 진입할 준비가 되었다는 것을 나타내는 변수** `flag[]` 를 두고, 다른 프로세스가 critical section에 진입하려고 한다면 현재 프로세스는 기다리는 방식이다.

    ```cpp
    bool flag[2]; // flag[i] == true이면 P_i는 critical section에 들어갈 준비가 되었다.
    
    do {
        flag[i] = true;
        while (flag[j]); // 상대가 들어갈 준비가 되었다면 기다린다. (busy waiting)
        
        // critical section
        
        flag[i] = false;
    } while (true);
    ```

    - 이 방식에서도 **mutual exclusion은 만족하지만 progress는 만족하지 못한다.**

    - 두 프로세스가 각각 flag[i] = true, flag[j] = true를 수행하고 나면 두 프로세스 모두 critical section에 진입하지 못하고 무한히 기다리는 상황이 발생하게 되기 때문이다.

- **Algorithm 3 = Peterson's Algorithm**
    - Peterson's Algorithm은 `**turn` 변수와 `flag` 변수를 동시에 사용**하는, 위의 알고리즘 1과 2를 합쳐놓은 방식이다.

    ```cpp
    int turn;
    bool flag[2];

    do {
        flag[i] = true;
        turn = j;
        while (flag[j] && turn == j); // busy waiting
        
        // critical section
        
        flag[i] = false;
    } while (true);
    ```

    - P_i 프로세스가 flag[i] = true로 바꾸면서 critical section에 진입하려는 경우 우선 turn = j로 바꿔주면서 상대방이 들어갈 수 있도록 한다. 상대방이 critical section에 진입했거나 진입할 준비가 된 경우(즉 flag[j] == true인 경우)에는 P_j가 critical section에서 빠져나올 때까지 기다린다. 그렇지 않은 경우(즉 flag[j] == false인 경우)에는 critical section에 P_i가 진입한다.

    - P_i가 critical section에서 빠져나오면 flag[i] = false로 바꿔줌으로써 P_j가 critical section에 진입할 수 있도록 한다.

    - 이 방식에서는 mutual exclusion, progress, bounded waiting을 모두 만족한다!

    - 하지만 critical section 진입을 기다릴 때 CPU와 메모리를 계속 사용하는 busy waiting 상태가 된다는 문제점이 있다.

## 5. 뮤텍스 (Mutex)

- Critical Section Problem을 해결하기 위한 소프트웨어 도구 중 가장 단순한 방법으로 **Mutex(Mutual Exclusion) Locks**이 있다.

- Mutex locks는 lock이 하나만 존재할 수 있는 locking 메커니즘을 따른다. 즉, 이미 하나의 프로세스가 critical section에서 수행 중이면 다른 프로세스들은 critical section에 들어갈 수 없도록 한다.

- Lock을 걸고 푸는 동작은 하드웨어 방식처럼 atomic하게 수행된다.

```cpp
acquire() {
    while (!available); // busy waiting
    available = false;
}
```

```cpp
release() {
    available = true;
}
```

```cpp
do {
    acquire();
    // critical section
    release();
} while (true);
```

- 이 방식은 critical section 진입을 기다릴 때 **CPU와 메모리를 계속 사용하는 busy waiting 상태가 된다는 문제점**이 있다.

## 6. 세마포어 (Semaphore)

- 세마포어는 busy waiting이 없는 동기화 도구이며, 여러 프로세스나 스레드가 critical section에 진입할 수 있는 signaling 메커니즘이다.

- 세마포어는 **카운터(counter)를 이용하여 동시에 자원에 접근할 수 있는 프로세스를 제한**한다.

- 사용 가능한 자원의 개수를 의미하는 정수형 변수로 나타내며, 주로 S라고 한다.

- 세마포어 변수는 **오직 두 개의 atomic한 연산(P 연산 및 V 연산)**을 통해 접근할 수 있다. 즉, 한 프로세스가 세마포어 변수를 수정하는 동안 다른 프로세스는 같은 세마포어 변수를 수정할 수 없다.

- 우선 P 연산과 V 연산을 아래와 같이 정의한다. **P 연산은 공유자원을 획득하는 연산이고, V 연산은 공유자원을 반납하는 연산이다.**

    ```cpp
    wait(S) { // P(S)라고 한다.
        while (S <= 0); // busy waiting
        S--;
    }
    ```
    
    ```cpp
    signal(S) { // V(S)라고 한다.
        S++;
    }
    ```

- 위의 코드에서는 busy waiting이 발생하므로 비효율적이다. 따라서 **Block & Wakeup 방식**을 사용한다.

- Block & Wakeup 방식은 **critical section에 진입하지 못한 프로세스를 기다리게 하지 않고 block시킨 뒤 critical section에 자리가 나면 다시 깨워주는 방식**이다. 이를 통해 busy waiting 방식의 단점인 **CPU 낭비 문제를 해결**할 수 있다.

    - 일반적으로 busy waiting 방식이 block & wakeup 방식에 비해 비효율적이다. 하지만 critical section이 매우 짧은 경우에는 block & wakeup 방식에서의 오버헤드가 더 커질 수도 있다.

- Block & Wakeup 구현을 위해 세마포어를 아래와 같이 정의한다.

    ```cpp
    typedef struct{
        int value; // 세마포어 변수
        struct process *L; // block된 프로세스들이 기다리는 Queue
    } semaphore;
    ```

- 또한 wait 함수와 signal 함수를 아래와 같이 정의한다.

    ```cpp
    void wait(semaphore S) {
        S.value--;
        
        if (S.value < 0) {
            add this process to S.L; // 현재 프로세스를 Queue에 추가한다.
            block(); // block()을 수행하면 커널은 block을 호출한 프로세스를 suspend시키고 해당 프로세스의 PCB를 waiting queue에 넣는다.
        }
    }
    ```

    ```cpp
    void signal(semaphore S) {
        S.value++;
        
        if (S.value <= 0) {
            remove a process P from S.L; // Queue에서 프로세스를 꺼낸다.
            wakeup(P); // wakeup()을 수행하면 커널은 block된 프로세스 P를 깨운 다음 이 프로세스의 PCB를 ready queue로 이동시킨다.
        }
    }
    ```

- `block()` 을 수행하면 커널은 block을 호출한 프로세스를 suspend시키고 해당 프로세스의 PCB를 waiting queue에 넣는다.

- `wakeup()` 을 수행하면 커널은 block된 프로세스 P를 깨운 다음 이 프로세스의 PCB를 ready queue로 이동시킨다.

- `signal()` 에서 자원을 내놓으면서 세마포어 변수 `S.value` 를 증가시킨다. 자원을 내놓았는데도 세마포어 변수가 여전히 0 이하라면 현재 기다리고 있는 프로세스가 존재한다는 의미이므로 Queue에서 프로세스 하나를 꺼내어 다시 실행될 수 있도록 한다.

- 그러나 세마포어 역시 문제점을 가지고 있는데, 코딩하기가 힘들고 실수하기 쉬우며 정확성을 입증하기 어렵다는 문제점이 있다.

- 특히 잘못 사용되었을 경우 deadlock이 발생하거나 mutual exclusion이 깨질 수 있다.

- 따라서 세마포어의 단점을 보완하고 개발자들이 사용하기 쉽도록 하기 위해 컴파일러 단에서(언어 단에서) 지원하는 monitor가 있다.

## 7. 모니터 (Monitor)

- Monitor는 개발자들이 사용하기 쉽도록 하기 위해 컴파일러 단에서(언어 단에서) 지원하는 방식으로, 추상화되어있다는 특징이 있다.

- 모니터는 동시 수행 중인 프로세스 사이에서 추상 데이터의 안전한 공유를 보장하기 위한 **High-level 동기화 구조**이다.

- 모니터는 공유 데이터 구조, 공유 데이터에 대한 연산을 제공하는 procedure(프로시저), 현재 호출된 프로시저 간의 동기화를 캡슐화한 모듈이다.

    - 프로세스가 공유 데이터를 사용하기 위해서는 반드시 모니터 내의 procedure를 통해야 한다.

    - 동일한 시간엔 오직 하나의 프로세스 또는 스레드만 모니터에 들어갈 수 있게 되며, 모니터에 진입하지 못한 프로세스는 모니터 큐에서 기다리게 된다. 프로세스가 모니터 내에서 기다릴 수 있게 하도록 조건 변수(condition variable)가 사용된다.

    - 조건 변수는 오직 wait과 signal 연산만으로 사용될 수 있다. 세마포어 변수와 유사하지만 변수가 어떤 값을 가지는 것은 아니고, 자신의 큐에 프로세스를 매달아서 sleep시키거나 큐에서 프로세스를 깨우는 역할만 한다.

- 세마포어와의 가장 큰 차이점은 모니터에서는 lock을 걸 필요가 없다는 점이다. 세마포어에서는 프로그래머가 직접 wait과 signal을 사용하여 race condition을 해결해야 하는 반면, 모니터에서는 자체적인 기능으로 해결할 수 있다.

- Monitor는 **Java에서 제공하는 기능**이며, 따라서 C나 C++에서는 기존의 세마포어를 그대로 사용해야 한다는 단점이 있다.

## 0. 참고 및 출처

- https://rebro.kr/176

- [https://eunsolsblog.tistory.com/entry/운영체제-프로세스-동기화](https://eunsolsblog.tistory.com/entry/%EC%9A%B4%EC%98%81%EC%B2%B4%EC%A0%9C-%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4-%EB%8F%99%EA%B8%B0%ED%99%94)
