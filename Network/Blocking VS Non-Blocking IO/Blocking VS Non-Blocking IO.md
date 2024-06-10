# Blocking I/O VS Non-Blocking I/O

## 1. Blocking I/O VS Non-Blocking I/O

- 제어권을 넘기지 않는지 VS 넘기는지

### (1) Blocking I/O

- I/O 작업이 진행되는 동안 I/O 작업을 호출한 프로세스가 자신의 작업을 중단한 채로 I/O 작업이 끝날 때까지 대기하는 방식이다.
- 즉, 호출된 함수 B가 호출한 함수 A에게 **제어권을 바로 넘기지 않는 방식**으로, **B의 수행이 끝나야 A가 수행**될 수 있는 방식이다.

### (2) Non-Blocking I/O

- I/O 작업을 호출한 프로세스가 I/O 작업이 완료될 때까지 자신의 작업을 중단하지 않고 I/O 호출에 대해 즉시 리턴한 뒤, 해당 프로세스가 이어서 다른 I/O 작업을 수행할 수 있도록 하는 방식이다.
- 즉, 호출된 함수 B가 호출한 함수 A에게 **제어권을 바로 넘기는 방식**으로, **B가 수행되는 동안에도 A가 수행**될 수 있도록 하는 방식이다.

## 2. 동기 (Synchronous) VS 비동기 (Asynchronous)

- 모든 요청과 응답의 순서가 보장되는지 VS 보장되지 않는지

### (1) 동기 (Synchronous)

- 요청한 작업에 대해 완료 여부를 따져서 순차적으로 처리하는 방식이다.
- 즉, **요청과 응답의 순서가 보장된다.**

### (2) 비동기 (Asynchronous)

- 요청한 작업에 대해 완료 여부를 따지지 않는 방식이다.
- 즉, **요청과 응답의 순서가 보장되지 않는다.**

## 3. 동기/비동기 + 블로킹/논블로킹 조합

![blocking nonblocking sync async](./img/blocking%20nonblocking%20sync%20async.png)

### (1) Sync + Blocking

- 흔히 볼 수 있는 형태이다.
    - (우리가 평소 동기로 알고 있는 방식)
- 다른 작업이 진행되는 동안 자신의 작업을 처리하지 않고(Blocking), 다른 작업의 완료 여부를 받아서 순차적으로 처리하는(Sync) 방식이다.
- 다른 작업의 결과가 자신의 작업에 영향을 주는 경우에 활용할 수 있다.

### (2) Async + Blocking

- 실무에서 마주하기 쉽지 않으며 다룰 일이 거의 없는 형태이다.
    - (사실상 위의 Sync + Blocking이랑 같아서 비동기가 의미 없어지는 방식)
- 다른 작업이 진행되는 동안 자신의 작업을 처리하지 않고(Blocking), 다른 작업의 결과를 바로 처리하지 않아 순서대로 작업을 수행하지 않는(Async) 방식이다.
- 호출한 함수 A가 호출된 함수 B의 작업 결과에 관심이 없음에도 불구하고 호출된 함수 B의 결과를 기다리고 있는 형태이다.
- Sync Blocking의 그림과 큰 차이가 없어보이는데, 실제로 개념적으로만 차이가 있을 뿐 성능적으로는 차이가 없다.

### (3) Sync + Non-Blocking

- (좀 특이한 방식)
- 다른 작업이 진행되는 동안에도 자신의 작업을 처리하고(Non-Blocking), 다른 작업의 결과를 바로 처리하여 작업을 순차적으로 수행하는(Sync) 방식이다.
- 호출된 함수 B가 바로 제어권을 돌려주면서 호출한 함수 A는 다른 작업을 수행할 수 있게 된다. 호출한 함수 A는 호출된 함수 B의 결과를 처리해야 하므로 **A는 B가 완료되었는지 반복적으로 물어봐야 한다.**
- 호출한 함수 A가 다른 작업을 수행할 수 있었음에도 불구하고 여전히 호출된 함수 B의 결과에만 신경쓰고 있기 때문에 A는 제 할 일을 못하게 되는 형태이다.

### (4) Async + Non-Blocking

- 흔히 볼 수 있는 형태이다.
    - (우리가 평소 비동기로 알고 있는 방식)
- 다른 작업이 진행되는 동안에도 자신의 작업을 처리하고(Non-Blocking), 다른 작업의 결과를 바로 처리하지 않아서 작업의 순서가 지켜지지 않는(Async) 방식이다.
- 다른 작업의 결과가 자신의 작업에 영향을 주지 않는 경우에 활용할 수 있다.

## 0. 내용 출처

- [https://inpa.tistory.com/entry/👩‍💻-동기비동기-블로킹논블로킹-개념-정리](https://inpa.tistory.com/entry/%F0%9F%91%A9%E2%80%8D%F0%9F%92%BB-%EB%8F%99%EA%B8%B0%EB%B9%84%EB%8F%99%EA%B8%B0-%EB%B8%94%EB%A1%9C%ED%82%B9%EB%85%BC%EB%B8%94%EB%A1%9C%ED%82%B9-%EA%B0%9C%EB%85%90-%EC%A0%95%EB%A6%AC)
- [https://velog.io/@maketheworldwise/SyncAsync-BlockingNon-Blocking-무슨-차이일까](https://velog.io/@maketheworldwise/SyncAsync-BlockingNon-Blocking-%EB%AC%B4%EC%8A%A8-%EC%B0%A8%EC%9D%B4%EC%9D%BC%EA%B9%8C)
- https://jindevelopetravel0919.tistory.com/95
