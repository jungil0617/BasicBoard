# BASIC 게시판 만들기(API SERVER)

## 커밋 컨벤션
- Feat: 기능 구현의 경우
- Chore: 코드에 유의미한 영향을 주지 않는 변경
  - ex) 사용하지 않는 import 제거, 공백 추가(제거) 등 Fix: 버그 수정
- Refactor: 리펙토링
  - 기능에 유의미한 변경이 있는 경우
- Docs: READEME.md 와 같은 문서 수정 시 사용
- Setting: 프로젝트 설정, bulid.gradle, 인프라 관련 등 설정 파일에 대한 변경이 있는 경우
  - ex) Setting: jpa 엔티티 기본 세팅, Chore: 불필요한 import 제거

## 규칙
- 커밋은 잘게 하나의 기능 단위로 진행
- 각 메소드는 하나의 기능만 담당하도록 구현
- ERD 그려오기 erdcloud 등 툴 사용
- 커밋 메시지는 컨벤션을 참고하여 어떤 행위를 진행 했는지 포함 되도록 구현
- 변수, 클래스, 패키지 등의 네이밍은 명확히 의미를 포함하도록 진행
- 지피티 사용은 최대한 지양하기(아무래도 배우는 과정이니 조금 오래 걸려도 생각 해보고 작성해보기)
  - 차라리 운영진이나 주변의 선배와 같은 분들에게 물어보는게 좋을 것 같습니다.
- 굳이 필요없는 주석은 지양하기 코드를 보고 한번에 유추가 되는 부분에 주석을 달 필요는 없다고 생각합니다.
  - 또한 무분별한 주석은 Clean code라는 유명한 책에서도 하나의 안티패턴으로 분류하는 행위입니다.
- 각 클래스 별로 정확히 담당 해야할 행위만 갖도록 설계
- 자바 코드 포맷팅 지키기
- READEME.md에 본인이 구현 할 내용과 TASK(작업의 순서)을 자세히 작성 후 개발 진행

## 프로그래밍 요구 사항
- http 상태 코드 정확히 사용하기
- api 문서화 진행하기 (swagger 사용)
- rest api 규칙 지키기
- 예외처리에는 정확한 Exception 사용
  - 자바 기본 익셉션이 애매 하다면 커스텀 익셉션 사용
- jpa에서 양방향 연관관계 금지
- 무분별한 setter, getter 지양
  - @setter, @Data는 금지
- 각 메소드의 depth는 2 depth 까지만 허용
- 하드코딩된 숫자 또는 문자열을 사용하지 않는다. (상수와 이넘을 활용)
- 접근제어자를 명확히 구분하고, 필요하지 않은 경우 public 사용을 지양한다.

## ERD

<img alt="Image" src="https://github.com/user-attachments/assets/bec94f7e-ff7c-48b7-94e5-a1ebbd557350" />

## Swagger
<img width="1312" alt="Image" src="https://github.com/user-attachments/assets/53a31d62-c9a6-4bee-9434-60dd814d9c82" />

## 기능 요구 사항
게시판
- 글 작성, 수정, 삭제, 조회
- 게시글 조회수 표시
- 글 목록 표시(페이징)
  - 제목, 생성일, 글 번호, 조회수 4가지 카테고리로 페이징 가능
- 좋아요 기능
- 저장 기능

댓글
- 작성, 수정, 삭제, 조회

회원
- 닉네임 변경

로그인 / 로그아웃

회원 가입

## 예외 처리 방식

- UserException
  - USER_NOT_FOUND: 존재하지 않는 사용자입니다.
  - DUPLICATE_USERNAME: 이미 존재하는 사용자명입니다.
  - PASSWORD_MISMATCH: 비밀번호가 일치하지 않습니다.
- PostException
  - POST_NOT_FOUND: 존재하지 않는 게시글입니다.
  - UNAUTHORIZED_ACCESS: 게시글에 대한 권한이 없습니다.
- CommentException
  - COMMENT_NOT_FOUND: 존재하지 않는 댓글입니다.
  - UNAUTHORIZED_ACCESS: 댓글에 대한 권한이 없습니다.

## TASK 설계
- 프로젝트 초기 세팅
  - build.gradle 의존성 설정
  - H2, JPA 설정 및 테스트 DB 연결 확인
  - JWT 및 Security 설정
  - Swagger 설정

- 회원 기능
  - 회원 가입 기능
  - 로그인, 로그아웃 기능
  - 회원 정보 조회 및 닉네임 변경 기능

- 게시글 기능
  - 작성
  - 조회 + 조회수 증가
  - 수정
  - 삭제
  - 게시글 목록 조회 (페이징)

- 좋아요 및 저장 기능
  - 게시글 좋아요 / 취소 기능
  - 게시글 저장 / 취소 기능
  - 저장한 게시글 조회

- 댓글 기능
  - 작성
  - 조회
  - 수정
  - 삭제

- Swagger API 문서화

## 향후 개선점 및 리팩토링 고려사항
- ✅ 토큰 재발급 API 추가 필요
- ✅ 빌더 패턴 학습 필요
- ✅ 정적 메서드보다는 객체를 통해 직접 처리하는 방식 권장
- ✅ Repository에서 직접 가져온 엔티티를 기반으로 검증 로직 처리
- ✅ DTO를 record로 정의하는 방식