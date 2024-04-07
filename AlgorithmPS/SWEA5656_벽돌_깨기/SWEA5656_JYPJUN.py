import sys

sys.stdin = open('벽돌깨기_input.txt')

from collections import deque

dirs = [(0,1), (1,0), (-1,0), (0,-1)]

# 벽돌을 아래로 내리는 함수
def down_block(x, y, lst):
    for i in range(W):
        for j in range(H-1, -1, -1):
            if lst[j][i] == 0 :
                for a in range(j-1, -1, -1):
                    if lst[a][i] != 0:        # 아래서부터 찾을 때 0이 있다면
                        lst[j][i] = lst[a][i] # 자리를 바꾸고 바꾼 자리는 0으로 만들기
                        lst[a][i] = 0
                        break
                else:                         # 0인 지점에서 for문을 다 돌았는데 위로 0이외에 다른 숫자가 없다면 
                    break                     # 그 열은 그만 찾고 다음 열로 넘어가기
    return

# 구슬을 다 쓸 때까지 재귀함수 실행
def break_bricks(s, lst):
    global min_cnt
    if s == N:
        cnt = W*H
        for m in range(H):
            cnt -= lst[m].count(0) # 0인 부분을 전부 찾아서 전체와 빼기
        min_cnt = min(cnt, min_cnt) # 최솟값만 갱신
        return

    for i in range(W): # 구슬을 던질 열을 선택
        for j in range(H): # 구슬을 던진 곳에서 위에서부터 아래로 탐색하면서 벽돌 찾기
            next_block = [sub_map[:] for sub_map in lst] # 전체 열을 복사해서 받아오기
            if next_block[j][i] == 1: # 1이라면 해당 값만 0으로 만들고 break
                next_block[j][i] = 0
                break
            elif next_block[j][i] not in (0, 1): # 다른 블록까지 영향을 줄 수 있는 인자라면
                q = deque([(j, i)]) # q 에 넣고 다른 영향있는 블록을 전부 추가하면서 연쇄 폭발로 인한 벽돌 제거
                while q:
                    a, b = q.popleft()
                    k_num = next_block[a][b] # directions를 돌 때 영향받은 블록을 추가할 상수값
                    next_block[a][b] = 0
                    for k in range(1, k_num): # dirs에 곱하기
                        for d in dirs: # 4방향 영향받은 구간 찾기
                            ni, nj = a+d[0]*k, b+d[1]*k
                            if 0 <= ni < H and 0 <= nj < W:
                                if next_block[ni][nj] in (0, 1): # 0 또는 1 이라면 q에는 추가하지 않음
                                    next_block[ni][nj] = 0
                                else:                            # 이외의 다른 블록에 영향을 줄 수 있는 값이라면 q에 추가
                                    q.append((ni, nj))
                # 블록을 제거했다면 내리는 작업 실행
                # 위의 if 문은 1일 경우 다른 블록에는 영향을 주지 않으므로 블록을 내리지 않아도 됨
                down_block(0, 0, next_block)
                break
        # 재귀함수를 타서 다음 구슬로 이동
        # 변경한 리스트를 지속적으로 끌고 다니기
        break_bricks(s+1, next_block)

T = int(input())

for tc in range(1, T+1):
    # 구슬의 수, W 가로, H 세로
    N, W, H = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(H)]

    min_cnt = W * H

    break_bricks(0, arr) # 벽돌 부수기 시작

    print(f'#{tc}', min_cnt)

























