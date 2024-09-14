# Bellman Ford Algorithm

## 1. 벨만 포드 알고리즘이란?

- 벨만-포드 알고리즘은 한 노드에서 다른 노드까지의 최단 거리를 구하는 알고리즘

- 간선의 가중치가 음수일 때도 최단 거리를 구할 수 있다.

- 경로 계산 방식에는 아래와 같은 종류가 있으며, 밸만-포드 알고리즘은 2번쨰 유형임

  1. (One-To-One) 한 지점에서 다른 특정 지점까지의 최단경로 구하기

  2. (One-To-All) 한 지점에서 다른 모든 지점까지의 최단경로 구하기

  3. (All-To-All) 모든 지점에서 모든 지점까지의 최단경로 구하기

- 다익스트라는 밸만-포드보다 빠르지만 음수의 가중치가 있는 그래프에서 사용할 수 없음

|특징|다익스트라|밸만-포드|
|--|--|--|
|방식|매번 방문하지 않은 노드 중에서 최단거리가 가장 짧은 노드 선택| 매 단계마다 모든 간선을 전부 확인하면서 모든 노드 간의 최단 거리를 선택|
|사용처|양수만 있을 때 사용 가능|음수가 있어도 사용 가능|
|시간복잡도|O(E * logV)|O(V * E)|
||||



## 2. 밸만-포드 알고리즘 구현 원리

1. 출발 노드를 설정

2. 최단 거리 테이블을 초기화

3. 다음의 과정을 (정점 -1)번 반복
  1. 모든 간선 E개를 하나씩 확인
  2. 각 간선을 거쳐 다른 노드로 가는 비용을 계산하여 최단 거리 테이블을 갱신

- 만약, 음수 간선 순환이 발생하는 지 체크하고 3번 과정을 한 번 더 수행
  - 이때 최단 거리 테이블이 갱신된다면 음수 간선 순환이 존재함을 의미

![alt text](/img//img2.PNG)

![alt text](/img//img3.PNG)

---

## 3. 구현 코드

https://www.acmicpc.net/problem/11657

```python
import sys
I = sys.stdin.readline

def bellmanFord(start):
    # 시작 노드에 대해서 초기화
    dist[start] = 0
    
    # 전체 N번의 반복
    for i in range(N):
        # 매 반복마다 모든 간선을 확인
        for j in range(M):
            cur = edges[j][0]
            next_node = edges[j][1]
            cost = edges[j][2]

            # 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
            if dist[cur] != float('inf') and dist[next_node] > dist[cur] + cost:
                dist[next_node] = dist[cur] + cost
                
                # N 번째 라운드에서도 값이 갱신된다면 음수 순환이 존재
                if i == N-1:
                    return True
    return False

# 노드의 개수, 간선의 개수를 입력 받기
N, M = map(int, I().split())

# 모든 간선에 대한 정보를 담는 리스트 만들기
edges = []

# 최단 거리 테이블을 모두 무한으로 초기화
dist = [float('inf')] * (N+1)

# 모든 간선 정보를 입력받기
for _ in range(M):
    a, b, c = map(int, I().split())
    # a번 노드에서 b번 노드로 가는 비용이 c
    edges.append((a, b, c))

# 밸만-포드 알고리즘 수행
negative_cycle = bellmanFord(1)

if negative_cycle:
    print(-1)
else:
    # 1번 노드를 제외한 다른 모든 노드로 가기 위한 최단 거리 출력
    for i in range(2, N+1):
        # 도달할 수 없는 경우, -1을 출력
        if dist[i] == float('inf'):
            print(-1)
        else:
            print(dist[i])
```