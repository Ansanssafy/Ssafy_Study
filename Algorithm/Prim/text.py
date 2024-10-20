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