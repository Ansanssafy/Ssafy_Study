# REST & REST API

## REST란?
![DP](./img/1.png)
- Representational State Transfer
- 월드 와이드 웹(www)과 같은 분산 하이퍼미디어 시스템을 위한 소프트웨어 아키텍처의 한 형식
- REST는 네트워크 상에서 Client와 Server 사이의 통신 방식 중 하나
- 웹의 기존 기술과 HTTP 프로토콜을 그대로 활용하기 때문에 웹의 장점을 최대한 활용할 수 있는 아키텍처 스타일 

## REST 구성 요소
### (1) 자원(Resource): URI

- 모든 자원에 고유한 ID가 존재하고, 이 자원은 Server에 존재
- 자원을 구별하는 ID는 ‘/groups/:group_id’와 같은 HTTP URI 
- Client는 URI를 이용해서 자원을 지정하고 해당 자원의 상태(정보)에 대한 조작을 Server에 요청

### (2) 행위(Verb): HTTP Method

- HTTP 프로토콜의 Method를 사용
- HTTP 프로토콜은 GET, POST, PUT, DELETE 와 같은 메서드를 제공

### (3) 표현(Representation of Resource)

- Client가 자원의 상태(정보)에 대한 조작을 요청하면 Server는 적절한 응답을 보냄
- REST에서 하나의 자원은 JSON, XML, TEXT, RSS 등 여러 형태의 응답을 표현 가능
- JSON 혹은 XML를 통해 데이터를 주고 받는 것이 일반적

## REST의 특징
### (1) Server-Client(서버-클라이언트 구조)
### (2) Stateless(무상태)
### (3) Cacheable(캐시 처리 가능)
### (4) Layered System(계층화)
### (5) Uniform Interface(인터페이스 일관성)



# REST API
![DP](./img/2.png)
## REST API란
- REST 기반으로 서비스 API를 구현한 것
- 최근 OpenAPI, 마이크로 서비스 등을 제공하는 업체 대부분은 REST API를 제공

 
## REST API의 특징
- REST 기반으로 시스템을 분산해 확장성과 재사용성을 높여 유지보수 및 편리한 운용 가능
- REST는 HTTP 표준을 기반으로 구현하므로, HTTP를 지원하는 프로그램 언어로 클라이언트, 서버를 구현 가능

## REST API 설계 예시
### 1. URI는 동사보다는 명사를, 대문자보다는 소문자를 사용
> 나빠요 : http://yeondong.com/Running/ <br>
> 좋아요 : http://yeondong.com/run/ 

### 2. 마지막에 슬래시 (/)를 사용금지
> 나빠요 : http://yeondong.com/test/  <br>
> 좋아요 : http://yeondong.com/test

### 3. 언더바 대신 하이폰을 사용
> 나빠요 : http://yeondong.com/test_blog <br>
> 좋아요 : http://yeondong.com/test-blog   

### 4. 파일확장자는 URI에 포함금지
> 나빠요 : http://yeondong.com/photo.jpg <br>
> 좋아요 : http://yeondong.com/photo 

### 5. 행위를 포함 금지
> 나빠요 : http://yeondong.com/delete-post/1  <br>
> 좋아요 : http://yeondong.com/post/1  

## RESTful이란?
![DP](./img/3.png)
- RESTful은 일반적으로 REST라는 아키텍처를 구현하는 웹 서비스를 나타내기 위해 사용되는 용어
- REST 원리를 따르는 시스템은 RESTful이란 용어로 지칭



