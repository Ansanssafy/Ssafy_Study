# SQL Query
## DDL(Data Definition Language)
- 데이터 정의어
- 데이터를 담는 테이블을 정의하는 언어
- 특정 구조를 생성, 변경,삭제, 이름을 바꾸는 데이터 구조와 관련된 명령어

### DDL 명령어
- CREATE : 생성 <br>
- ALTER : 수정  <br>
- DROP : 삭제 <br>
- TRUNCATE : 초기화 <br>
- RENAME : 이름 변경

## DML(Data Manipulation Language)
- 데이터 조작어
- 데이터베이스에 저장된 자료들을 입력, 수정, 삭제, 조회하는 언어
  
### DML 명령어
- DELETE : 삭제 <br>
- INSERT : 삽입 <br>
- SELECT : 읽기 <br>
- UPDATE : 갱신 <br>

## DCL(Data Control Language)
- DB관리자가 보안, 무결성, 병행제어, 회복을 위해 사용하는 제어영 언어

### DCL명령어
- GRANT : 권한 부여 <br>
- REVOKE : 권한 회수 <br>


## TCL(Transaction Control Language)
COMMIT : 살행한 쿼리를 최종 반영 <br>
ROLLBACK : 실행한 쿼릴를 마지막 커밋 전으로 취소<br>

## 명령문 예시
```sql
-- 데이터베이스(스키마) 생성
CREATE DATABASE IF NOT EXISTS Ansanssafy;

SELECT * FROM member;

USE Ansanssafy;
-- 테이블 생성

CREATE TABLE IF NOT EXISTS member(
    -- 컬럼 데이터타입
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    location VARCHAR(20)
);
-- id, name, location 존재 / id는 자동 증가

-- DML: 데이터 삽입
INSERT INTO member (name, location) VALUES 
    ('홍창기', 'Seoul'),
    ('박준영', 'Daejeon'),
    ('서진경', 'Seoul'),
	('김연동', 'Daejeon');

-- DML: 데이터 갱신
UPDATE member SET name = '갓창기' WHERE id = 1;
-- DML: 중복으로 지우기

-- DML: 데이터 삭제
DELETE FROM member WHERE id = 4;
-- 김연동 삭제
-- DELETE FROM member WHERE IN (1,2,3); => 중복으로도 삭제 가능 

-- 그룹바이를 사용한 집계 함수 적용
SELECT location, COUNT(*) AS '교육생 수' FROM member GROUP BY location;
-- location으로 그룹화하여 인원수 세기

-- HAVING절로 데이터 필터링
SELECT name, location FROM member HAVING location = 'Seoul';

-- 수정을 통해 컬럼 추가
ALTER TABLE member ADD email VARCHAR(50);

-- DDL: 컬럼 수정
ALTER TABLE member MODIFY COLUMN name VARCHAR(30);

-- DDL: 컬럼 삭제
ALTER TABLE member DROP COLUMN email;

-- DDL: DROP을 통한 테이블 삭제
DROP TABLE IF EXISTS member;

-- DCL: 권한 부여
GRANT SELECT, INSERT ON Ansanssafy.member TO '김연동'@'localhost';
-- 김연동에게 Ansanssafy의 member 테이블에 대한 SELECT, INSERT 권한 부여

-- DCL: 권한 박탈
REVOKE SELECT,INSERT ON Ansanssafy.member FROM '김연동'@'localhost';
-- 압수
```