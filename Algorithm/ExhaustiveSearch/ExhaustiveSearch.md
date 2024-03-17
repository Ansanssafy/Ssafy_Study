# 완전 탐색 알고리즘
## 완전 탐색 알고리즘이란?

- 문제의 해결책을 찾기 위해 모든 가능한 경우의 수를 나열하고 확인하는 방법
- 모든 경우의 수를 시도하여 문제의 해결책을 찾는 방식으로, 경우의 수가 적을 때 유용
- 브루트 포스 (Brute Force) 알고리즘이라고도 불림 
- 다양한 기법을 포함하는 일반적인 개념

## 장점 
- 상대적으로 쉽게 알고리즘이 구현 가능하다 
- 복잡한 알고리즘이 없는 빠르 구현
  
## 단점
- 비효율적인 메모리 사용 : 모든 경우의 수를 탐색
- 실행 시간이 상대적으로 오래 걸림

## 완전 탐색 알고리즘의 종류
1. 브루트 포스 ( Brute Force ) 

- 반복, 조건문을 통해 가능한 모든 방법을 찾는 경우

ex) 4자리 비밀번호 0000 ~ 9999 까지 모두 탐색


2. 비트 마스크 ( Bit mask )

3. 재귀 ( Recursion )

4. 백트래킹( Backtracking )

5. 순열 ( Permutation )

6. 재귀 ( Recursion )

7. 동적 계획법 ( Dynamic Programming )

8. 그리디 ( Greedy )

9. 순차 ( Sequential )

10. BFS / DFS 





브루트포스 예시코드 ( swea - String )
```java
public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int a = sc.nextInt();
			String pattern = sc.next();
			String text = sc.next();
			
			
			char[] textArr = text.toCharArray();
			char[] patternArr = pattern.toCharArray();

			int count = 0;
			start: for (int i = 0; i < textArr.length-(patternArr.length-1); i++) {
				pattern: for (int j = 0; j < patternArr.length; j++) {
					if (textArr[i + j] != patternArr[j]) {
						continue start;
					}
				}
				count++;
			}
			System.out.println("#" + a + " " + count);
		}

	}
```