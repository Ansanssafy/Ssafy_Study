import sys
sys.stdin = open('요리사_input.txt')

# 음식 A의 식재료 조합찾기
def find_comb(s, lst):
    if s == N:
        if len(lst) == N//2 and len(foods_A) < k: # N//2 만큼 찾는다. and 찾은 조합의 숫자가 k보다 작다면
            foods_A.append((set(lst))) # 식재료의 조합을 foods_A 리스트에 set type으로 추가
        return

    find_comb(s+1, lst + [food_list[s]]) # 식재료를 리스트에 추가할 경우
    find_comb(s+1, lst)              # 식재료를 리스트에 추가하지 않을 경우

# 음식 조합의 시너지를 구하는 함수
def find_value(Food):
    value = 0
    for i in range(len(Food)):
        for j in range(i+1, len(Food)):
            value += (arr[Food[i]][Food[j]]+arr[Food[j]][Food[i]])
    return value

T = int(input())

for tc in range(1, T+1):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
    min_value = float('inf')

    # 순열 조합 중 중복 없애기
    # k로 만들 수 있는 순열의 중복을 없애기 ...
    # 진짜 바보 같이 k 구하기 ...
    k = 1
    for i in range(N, N//2, -1):
        k *= i
    for j in range(N//2, 0, -1):
        k //= j
    k //= 2
    # 6일 때 20개 6 x 5 x 4 / (3 * 2)
    # 4일 때 6개 4x3/2

    food_list = [i for i in range(N)]
    foods_A = [] # 여기에 A 음식을 만들 식재료의 조합이 추가됨
    find_comb(0, []) # A 식재료 조합 찾는 함수 실행

    # for 문 돌리면서 B 음식 식재료 조합 만들기
    all_food_set = {i for i in range(N)}  # 식재료의 전체 index
    for j in foods_A:
        food_B = all_food_set - j
        # j 가 푸드 A의 식재료 / food_B 가 푸드 B의 식재료
        comb_value = abs(find_value(list(j)) - find_value(list(food_B)))
        if min_value > comb_value:
            min_value = comb_value

    print(f'#{tc}', min_value)