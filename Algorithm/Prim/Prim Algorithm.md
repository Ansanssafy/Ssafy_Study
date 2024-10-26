# Prim Algorithm

## 1. 최소 신장 트리란?

- 프림 알고리즘은 무향 연결 그래프가 주어질 떄, MST라고 부르는 서브 그래프를 찾는 알고리즘이다.

- MST (Minimum Spanning Tree)란?
    1. 신장 트리란?
        - 신장 트리(Spanning Tree)는 주어진 그래프의 모든 정점을 포함하면서, 사이클(순환)이 없고, 모든 정점이 연결된 트리이다.

        - 연결 그래프에서만 신장 트리를 만들 수 있으며, 연결되지 않은 그래프는 신장 트리가 존재하지 않는다.

        - N개의 정점을 가지는 그래프의 신장 트리는 항상 N-1개의 간선을 포함한다.

    2. 최소 신장 트리란?
        - 최소 신장 트리는 신장 트리 중에서도 가중치의 합이 최소인 신장 트리를 의미한다.

        - 그래프의 가중치는 간선에 부여된 값이며, 이 값은 보통 거리, 비용, 시간을 나타낸다.

        - 최소 신장 트리는 여러 분야에서 최적의 연결 구조를 찾는 문제를 해결하는데 유용하다.


## 2. 프림 알고리즘이란?

- 프림 알고리즘은 정점 중심적인 방식으로 작동한다.

- 한 정점에서 시작해, 현재 트리에 가장 가까운 정점을 하나씩 추가하면서 트리를 확장해 나간다.

- 매번 가장 작은 가중치를 가진 간선을 선택해서 트리에 추가한다.

- 트리가 점진적으로 커지며, 모든 정점이 트리에 포함될 때까지 이 과정을 반복한다.

1. 특징
    - 정점 기반으로 진행하므로, 항상 트리에 새로운 정점을 추가하면서 트리를 확장한다.

    - 시간 복잡도
        - 인접 행렬을 사용하면 O(V²),
        
        - 인접 리스트와 우선순위 큐(힙)를 사용하면 O(E log V).


```python
import heapq as hq

def prim(start):
    heap = list()
    connected = [False] * (NODE_CNT + 1)
    
    sum_w = 0  # 최소 신장 트리의 가중치 합
    
    # 시작 노드를 우선순위 큐에 넣기 (가중치 0, 시작 노드)
    hq.heappush(heap, (0, start))

    # 우선순위 큐에 요소가 있는 동안 계속 반복
    while heap:
        # 가중치가 가장 작은 간선 선택
        weight, v = hq.heappop(heap)

        # 선택된 노드가 그래프에 포함되지 않았다면
        if not connected[v]:
            # 해당 노드를 최소 신장 트리에 포함시킴
            connected[v] = True
            sum_w += weight  # 가중치 합에 추가

            print('Connected Nodes:', v, 'Weight:', weight)

            # 인접한 모든 노드들에 대해 큐에 추가
            for i in range(1, NODE_CNT + 1):
                # 연결된 간선이 있을 경우
                if graph[v][i] != 0 and not connected[i]:
                    hq.heappush(heap, (graph[v][i], i))

    print(sum_w)
    

if __name__ == "__main__":
    NODE_CNT = 5
    graph = [[0] * (NODE_CNT + 1) for _ in range(NODE_CNT + 1)]

    graph[1][2], graph[1][3], graph[1][4] = 1, 8, 3
    graph[2][1], graph[2][4], graph[2][5] = 1, 2, 7
    graph[3][1], graph[3][4], graph[3][5] = 8, 4, 5
    graph[4][1], graph[4][2], graph[4][3], graph[4][5] = 3, 2, 4, 6
    graph[5][2], graph[5][3], graph[5][4] = 7, 5, 6

    prim(1)

```