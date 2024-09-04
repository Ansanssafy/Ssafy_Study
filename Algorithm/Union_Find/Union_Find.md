# Union-Find Algorithm

## 1. 유니온 파인드 알고리즘이란?

- 유니온 파인드(Union-Find) 알고리즘은 **상호 배타적 집합(Disjoint Set)**을 효율적으로 관리하는 자료 구조와 알고리즘이다.

- 이 알고리즘은 집합들의 연결 관계를 추적하는 데 사용되며, 주로 그래프 관련 문제에서 연결요소(Connected Components)를 찾거나 사이클을 감지하는 등의 작업에 사용된다.

#### 상호 배타적 집합(Disjoint Set)이란?

![alt text](/img/img1.png)

- 상호 배타적 집합이란 서로 겹치지 않는(교집합이 없는) 여러 개의 집합을 의미한다.

  1. 서로 겹치지 않음 : 어떤 요소도 두 개 이상의 집합에 속할 수 없다. 즉, 각 요소는 오직 하나의 집합에만 속한다.

  2. 분할 : 전체 원소의 집합을 여러 개의 상호 배타적 부분 집합으로 분하라는 것을 의미한다.


---

## 2. 유니온 파인드 연산 및 자료 구조

### 1. 기본 연산

1. Union 연산 : 두 집합을 하나의 집합으로 합치는 연산으로 두 요소가 각각 다른 집합에 속할 때, 이 두 집합을 병합하여 하나의 집합으로 만든다.

2. Find 연산 : 특정 요소가 속한 집합의 대표(루트)를 찾는 연산으로 주로 두 요소가 같은 집합에 속하는지를 확인하는 데 사용된다.

### 2. 집합을 표현하는 방법

- 연결 리스트

- 트리

1. 부모 배열(Parent Array) : 각 요소는 다른 요소를 가리키는 포인터 역할을 한다. 처음에는 자기 자신을 가리키지만, 'union' 연산이 일어나면 한 요소가 다른 요소를 가르키도록 변경된다. 이때, 집합의 대표는 해당 집합의 루트 노드로, 그 자신을 가리킨다.

2. 랭크 배열(Rank Array) or 크기 배열(Size Array) : 트리의 깊이를 관리하는 데 사용된다. 이를 통해 두 집합을 병합할 때 더 얕은 트리를 깊은 트리에 붙여 전체 트리의 높이를 최소화한다.

---

## 3. 연산 코드


1. make_set

- 자기자신이 대표인 데이터가 리스트로 생성됨

```python
def make_set(n):
  return [i for i in range(n)]
```

2. find_set

- 자기 자신이 대표자 일 때까지 탐색

```python
def find_set(x):
  if parents[x] == x:
    return x
  
  # 위의 조건이 걸리지 않아서 부모를 계속 찾음
  return find_set(parents[x])
```

3. union
```python
def union(x, y):
  x = find_set(x)
  y = find_set(y)

  # 이미 같은 집합에 속해있다면 continue
  if x == y:
    return
  
  # 다른 집합이라면 합침
    # 예 ) 더 작은 루트노드에 합쳐야 함
  if x < y :
    parents[y] = x
  else:
    parents[x] = y
```


4. 경로 압축(Path Compression) : 'Find' 연산을 수행할 때, 탐색하는 모든 노드가 직접 루트 노드를 가리키도록 트리 구조를 평탄화한다. 이를 통해 다음 'Find' 연산에서 더 빠르게 루트에 도달할 수 있다.


5. 유니온 바이 랭크 또는 크기(Union by Rank or Size) : 'Union' 연산 시, 두 트리를 병합할 때 작은 트리를 큰 트리 아래에 붙인다. 이렇게 하면 트리의 높이가 불필요하게 커지는 것을 방지할 수 있다.

```python
parents = [i for i in range(10)]

def find_set(x):
  '''
  경로 압축 코드
  '''
  if parents[x] != x:
    parents[x] = find_set(parents[x])
  return parents[x]

def union(x, y):
  '''
  유니온 바이 랭크
  '''
  rootX = find(x)
  rootY = find(y)

  if rootX != rootY:
    if rank[rootX] > rank[rootY]:
      parents[rootY] = rootX
    elif rank[rootX] < rank[rootY]:
      parents[rootX] = rootY
    else:
      parents[rootY] = rootX
      rank[rootX] += 1
```

6. 시간 복잡도

- 유니온 파인드 알고리즘의 시간 복잡도는 아커만 함수의 역함수로 표현되는 매우 느리게 증가하는 함수인 O(a(n))에 비례하며, 실질적으로 a(n)은 매우 작은 값이기 때문에 유니온 파인드 알고리즘은 거의 상수 시간에 동작한다고 볼 수 있다.

7. 응용 알고리즘

- 그래프의 연결 요소 (Connected Components) 찾기

- 최소 신장 트리 (Minimum Spanning Tree, MST) 생성 (크루스칼 알고리즘)

- 동일성 검사 (Equivalence checking) - 여러 개의 집합이 동일한지 확인

---

## 4. 예제 문제
- 백준 1717번 (골드 5)

```python
import sys
sys.setrecursionlimit(10**6)

I = sys.stdin.readline

def make_set(x):
    return [i for i in range(x+1)]

def find_set(x):
    if arr[x] != x:
        arr[x] = find_set(arr[x])
    return arr[x]

def union(x, y):
    rootX = find_set(x)
    rootY = find_set(y)
    
    if rootX != rootY:
        if arr[rootX] <= arr[rootY]:
            arr[rootY] = rootX
        elif arr[rootX] > arr[rootY]:
            arr[rootX] = rootY

def check_set(x, y):
    rootX = find_set(x)
    rootY = find_set(y)
    
    if rootX == rootY:
        return "YES"
    else:
        return "NO"


N, M = map(int, I().split())

arr = make_set(N)

for _ in range(M):
    check, a, b = map(int, I().split())
    
    if not check:
        union(a, b)

    elif check:
        result = check_set(a, b)
        print(result)

```

































