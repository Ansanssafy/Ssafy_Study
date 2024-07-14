# VLSM

## 1. VLSM (Variable Length Subnet Mask, 가변길이 서브넷 마스크)

- VLSM은 서브넷 마스크를 수정하여 IP 주소를 더 작은 크기로 분할하여 사용할 수 있는 방법이다.
- 모두 같은 크기로 서브넷을 생성하면 IP 낭비 현상이 발생하는 문제가 있다.
- 반면, VLSM에서는 각 서브넷마다 필요한 크기에 맞춰서 IP 주소를 분할하여 사용할 수 있으므로 **IP 주소의 낭비를 막을 수 있다!**

### (1) 예제

- 상황
    - 회사가 201.102.1.0/24 네트워크를 사용한다고 하자. (C class : 24비트의 네트워크 비트와 8비트의 호스트 비트)
    - 회사에 A, B, C 3개의 부서가 있다고 하자.
    - A 부서에는 120개, B 부서에는 60개, C 부서에는 10개의 컴퓨터가 있다고 하자.
- 필요한 개수가 많은 네트워크부터 서브네팅한다.
- A 부서
    - 120 ≤ 2^n - 2 ⇒ n = 7
    - 201.102.1.[0]0000000 ~ 201.102.1.[0]1111111 ⇒ 201.102.1.0 ~ 201.102.1.127
    - 서브넷 마스크 : 255.255.255.128 (네트워크 부분을 1로, 호스트 부분을 0으로 채운 것)
    - 네트워크 ID : 201.102.1.0 (호스트 부분을 전부 0으로 채운 것)
    - Broadcast 주소 : 201.102.1.127 (호스트 부분을 전부 1로 채운 것)
    - 사용 가능한 IP 범위 : 201.102.1.1 ~ 201.102.1.126
- 즉, 201.102.1.0/25 네트워크를 A 부서에 할당하고, 나머지 201.102.1.128/25 네트워크는 다른 조건에 맞춰서 다시 서브네팅한다.
- B 부서
    - 60 ≤ 2^n - 2 ⇒ n = 6
    - 201.102.1.1[0]000000 ~ 201.102.1.1[0]111111 ⇒ 201.102.1.128 ~ 201.102.1.191
    - 서브넷 마스크 : 255.255.255.192
    - 네트워크 ID : 201.102.1.128
    - Broadcast 주소 : 201.102.1.191
    - 사용 가능한 IP 범위 : 201.102.1.129 ~ 201.102.1.190
- 즉, 201.102.1.128/26 네트워크를 B 부서에 할당하고, 나머지 201.102.1.192/26 네트워크는 다른 조건에 맞춰서 다시 서브네팅한다.
- C 부서
    - 10 ≤ 2^n - 2 ⇒ n = 4
    - 201.102.1.110[0]0000 ~ 201.102.1.110[0]1111 ⇒ 201.102.1.192 ~ 201.102.1.207
    - 서브넷 마스크 : 255.255.255.240
    - 네트워크 ID : 201.102.1.192
    - Broadcast 주소 : 201.102.1.207
    - 사용 가능한 IP 범위 : 201.102.1.193 ~ 201.102.1.206
- 즉, 201.102.1.192/28 네트워크를 C 부서에 할당한다.
- 모두 할당하고 나면 201.102.1.224/27 네트워크와 201.102.1.208/28 네트워크가 남는다. 이는 추후에 사용가능한 네트워크가 된다.