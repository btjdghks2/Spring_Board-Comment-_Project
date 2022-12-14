# Spring_Board-Comment-_Project

스프링부트를 활용한 게시판입니다

이번 프로젝트는 팀업이 아닌 개인 프로젝트입니다

이번 프로젝트의 포인트는 기본적인 CRUD 구현과 각 게시글에 댓글 기능을 구현해 보았습니다

마지막으로 docker-compose를 활용한 배포까지 진행하였으나 오류가 발견되어 수정중에 있습니다


### 개발환경
-----------

+ h2 database
+ PostgreSQL
+ Docker-Compose
+ Window11-wsl2-ubuntu22.04
+ Intellij IDEA community 2022.3
+ Java 11
+ Gradle 7.5.1
+ Spring Boot 2.6.13


### 라이브러리
-----------

+ Spring Web
+ Mustache
+ Spring-Data-JPA
+ Lombok
+ Spring-boot-Devtool
+ Spring-boot-Starter
+ JUnit5
+ Mockito



첫번째로 홈화면 입니다

![화면 캡처 2022-12-03 163202](https://user-images.githubusercontent.com/83440667/206548251-10dcd105-85ca-41d9-a03e-b3a0457249f4.jpg)

좌측과 상단에 카테고리를 만들수 있는 네비게이션바를 배치해 봤지만 새로운 기능을 추가하는 것에 아이디어가 떠오르지 않아 일단은 견본으로 처리 했습니다

두번째로 글쓰기 페이지입니다

![화면 캡처 2022-12-03 163219](https://user-images.githubusercontent.com/83440667/206548639-846f1493-aa59-4a32-a5bc-0826e29f9b04.jpg)

글쓰기 페이지는 DTO로 from 데이터를 받아서 entity 클래스로가 데이터 베이스에 저장되는 형식입니다

세번째로 만들어진 게시글의 상세 페이지 입니다

![화면 캡처 2022-12-03 163325](https://user-images.githubusercontent.com/83440667/206548936-1aceccff-c388-4e37-ab91-eb1d89506ac3.jpg)

상세페이지에서는 수정기능과 게시글 삭제 버튼을 넣어두었습니다

그리고 추가적으로 게시글아래 닉네임과 댓글을 달 수 있는 공간을 만들어 놓았습니다
 
 ![화면 캡처 2022-12-03 163342](https://user-images.githubusercontent.com/83440667/206549249-cf3d7cb8-66d0-4dae-aacf-9b8881b7dea7.jpg)

댓글 작성시 작성에 성공하면 alter 창이 뜨게됩니다

![화면 캡처 2022-12-03 163509](https://user-images.githubusercontent.com/83440667/206549562-a2176a8f-e05f-46f1-bc71-e7a912f20127.jpg)

댓글또한 수정과 삭제 기능을 넣어놓았는데 댓글의 수정창은 모달창으로 만들었습니다

스프링 부트 게시판의 디비 erd 입니다

게시글 작성을 위한 테이블과 jpa를 활용한 다대일 단방향 매핑을 활용해서 만들게 되었습니다

![화면 캡처 2022-12-03 163845](https://user-images.githubusercontent.com/83440667/206549822-5ce4d170-6c5d-4474-b20a-d50e3f4f156f.jpg)

각 기능들을 만들고 이것을 단순히 controller 로 만드는 것이 아니라 데이터를 json으로 활용할 수 있는 방안인 RestAPI로 작성도 하였으나 당장은 화면 구성을 할것이 떠오르지 않기 때문에
차후 프로젝트 보강시 RestController와 RestService를 사용해서 RestAPI 구현할 생각입니다

![화면 캡처 2022-12-08 035141](https://user-images.githubusercontent.com/83440667/206550592-803726a4-98ce-43f3-82a8-7cb5342d966d.png)

마지막으로 docker-compose를 활용하여 프로젝트를 이미지화해서 배포도 가능할 수 있도록 제작중에 있습니다


