# REST API (Representational State Transfer API)

## 1. REST란?

- Representational State Tranfer의 약자로 자원을 이름으로 구분하여 해당 자원의 상태를 주고받는 모든 것을 의미한다.

- REST
    1. HTTP URL(Uniform Resource Identifier)를 통해 자원(Resource)을 명시하고,
    2. HTTP Method(POST, GET, PUT, DELETE, PATCH 등)를 통해
    3. 해당 자원(URL)에 대한 CRUD Operation을 적용하는 것을 의미한다.
        - CRUD : Create, Read, Update, DELETE

- REST는 웹 기반 시스템에서 Resource(데이터)를 효율적으로 주고받기 위한 아키텍처 스타일을 의미한다. REST는 인터넷의 구조에 맞춰 설계되었으며, 웹의 HTTP 프로토콜을 기반으로 간단하고 일관된 인터페이스를 제공하는 데 중점을 둔다.

- REST의 주요 원칙

    1. 클라이언트-서버 구조:
        - REST는 클라이언트와 서버를 명확히 분리하여 클라이언트가 리소스 요청을 보내면, 서버는 그에 대한 응답을 한다.
        - 이 구조는 클라이언트와 서버의 독립성을 보장해준다. 즉, 클라이언트와 서버가 독립적으로 개발되고 배포될 수 있다.
    
    2. 무상태성 (Statelessness):
        - REST의 모든 요청은 독립적이어야 하며, 서버는 클라이언트의 상태를 저장하지 않는다. 즉, 각 요청에는 필요한 모든 정보가 포함되어야 한다.
        - 이렇게 독립적으로 처리하면 서버가 클라이언트의 상태를 추적할 필요가 없어 서버의 부담이 줄어들고, 확장성이 좋아진다.
    
    3. 캐시 처리 가능 (Cacheable):
        - REST는 HTTP 캐싱 메커니즘을 통해 응답을 캐시할 수 있도록 한다.
        - 이를 통해 서버 부담이 줄어들고, 클라이언트는 자주 요청하는 데이터를 빠르게 가져올 수 있다.
    
    4. 계층화된 시스템 (Layered System):
        - REST는 중간 서버(ex. 프록시 게이트웨이)를 통해 요청을 전송할 수 있으며, 클라이언트는 이러한 중간 서버가 최종 서버인 것처럼 행동한다.
        - 이 구조는 시스템 보안을 강화하고, 로드 밸런싱과 같은 네트워크 확장성을 높이는 데 기여한다.
    
    5. 인터페이스 일관성 (Uniform Interface):
        - REST는 인터페이스를 일관되게 설계하는 것이 핵심으로 클라이언트가 어떤 서버와 통신하든 일관된 규칙을 따르므로, API가 서로 다른 시스템에 걸쳐 통일감을 유지할 수 있다.
        - 이를 위해 REST는 리소스를 URL로 식별하고, 리소스 조작을 HTTP 메서드로 정의한다.

- REST의 장점과 단점
    1. 장점:
        - 간단한 설계와 유연성으로 빠른 개발이 가능하다.
        - HTTP 기반으로 다른 네트워크나 시스템과의 통합이 용이하다.
        - 확장성 및 독립성 보장으로 시스템의 유지 보수가 용이하다.
    2. 단점:
        - 무상태성으로 인해 매 요청 시 필요한 모든 정보를 포함해야 하는 부담이 있다.
        - 트랜잭션 관리가 필요할 경우 복잡성이 증가할 수 있다.
        - REST는 특정한 규격이 아닌 아키텍처 스타일이기 때문에 API 설계에 있어 일관성 유지가 어려울 수 있다.


## 2. REST API란?

- REST API란 REST의 원리를 따르는 API를 의미한다.

- REST API 설계 예시
    1. URI는 동사보다는 명사를, 대문자보다는 소문자를 사용하여야 한다.
        ```
        - BAD : http://example.com/Running/
        - GOOD : http://example.com/run/
        ```
    
    2. 마지막에 슬래시를 포함하지 않는다.
        ```
        - BAD : http://example.com/test/
        - GOOD : http://example.com/test
        ```
    
    3. 언더바 대신 하이폰을 사용한다.
        ```
        - BAD : http://example.com/test_item
        - GOOD : http://example.com/test-item
        ```
    
    4. 파일확장자는 URI에 포함하지 않는다.
        ```
        - BAD : http://example.com/test.jpg
        - GOOD : http://example.com/test
        ```
    
    5. 행위를 포함하지 않는다.
        ```
        - BAD : http://example.com/delete-test/1
        - GOOD : http://example.com/test/1
        ```

## 3. RESTful API에 대해서..

- RESTful API라는 용어는 REST의 원칙과 설계를 정확히 따르는 API를 의미한다. REST와 RESTful은 같은 개념으로 혼동되기도 하지만, 실제로는 RESTful이 되기 위해 더 구체적인 설계 요구사항을 충족해야 한다.

- REST는 웹 아키텍처 스타일의 개념적 원칙이다. RESTful API는 REST의 규칙을 구체적으로 구현하여 일관성 있고 효율적으로 만든 API를 가르킨다. 따라서 단순히 REST의 규칙을 몇 가지 적용했다고 해서 반드시 API가 RESTful 한 것은 아니다.

- RESTful API가 되기 위한 조건
    - RESTful API가 되기 위해서는 REST의 원칙들을 충실하게 따르면서도 일관성과 가독성, 효율성을 가진 설계를 유지해야 한다.

    1. 리소스 기반의 URL 설계:
        - RESTful API에서 모든 리소스는 명확한 URL로 식별된다.
            ```
            https://api.example.com/users/123
            ```
        - 위의 URL은 123번 사용자를 가리키며, 사용자의 데이터에 대한 조작이 가능하다.
        - URL은 명확하게 리소서를 나타내야 하며, 불필요한 동사(getUser)나 비직관적인 URL을 포함해서는 안된다.
    
    2. HTTP 메서드 사용 원칙:
        - CRUD 작업은 HTTP 메서드에 따라 구분되어야 한다.
            - GET : 리소스 조회
            - POST : 새 리소스 생성
            - PUT : 리소스 수정
            - DELETE : 리소스 삭제
        - 만약 모든 요청을 GET이나 POST로만 처리하는 방식으로 설계했다면, 이는 RESTful하지 않다고 볼 수 있다.
    
    3. 무상태성 (Stateless):
        - 클라이언트와 서버 간의 요청을 독립적으로 처리하며, 상태 정보가 서버에 저장되지 않아야한다. 만약 서버가 세션을 통해 클라이언트 상태를 유지한다면, 이 API는 완전히 RESTful하다고 할 수 없다.
    
    4. 표준화된 응답 코드 사용:
        - RESTful API는 HTTP 상태 코드를 활용하여 요청 결과를 전달한다.
            - 200 OK, 201 Created, 400 Bad Request, 404 Not Found, 500 Internal Server Error 등의 표준 상태 코드를 사용하여 클라이언트가 요청 결과를 쉽게 이해할 수 있어야 한다.
        - 잘못된 요청에 대해 항상 200 OK를 반환하는 API는 RESTful API하지 않다.
    
    5. 표준 데이터 형식 사용 (JSON 권장):
        - 데이터 전송 형식으로 JSON이나 XML을 사용하지만, JSON이 더 간결하고 다루기 쉬워 JSON을 주로 사용한다.
        - JSON 응답 형식을 일관되게 유지하며, 필요한 경우 Content-Type 헤더를 통해 클라이언트가 형식을 지정할 수 있게 해주는 것이 좋은 사례이다.
    
    6. 버전 관리:
        - RESTful API는 일반적으로 URL 경로 또는 헤더에 버전을 포함시켜 관리한다. 예를 들어 https://api.example.com/v1/users 와 같은 방식으로 버전을 명시하여, 클라이언트가 특정 API 버전을 요청할 수 있도록 해야한다.