# developer-maker-api
기본적인 CRUD 기능 구현 API - 개발자 내맘대로 고용하고 퇴직시키기                       
강의 : 

## Project Goal
Spring을 활용한 API 설계 및 구현 / 예외, 오류 처리 익혀보기

## Project Features
- 개발자 조회 : `GET /developers`
  - 고용 상태 개발자 조회 : `?status=EMPLOYED`
  - 휴직 상태 개발자 조회 : `?status=LEAVE`
  - 퇴직 상태 개발자 조회 : `?status=RETIRED`  
- 개발자 추가 : `POST /create-developer`
- 개발자 상세 조회 : `GET /developer/{id}`
- 개발자 수정 : `PUT /developer/{id}`
- 개발자 삭제 : `DELETE /developer/{id}`

## Project Setting              
* Spring Boot version : 2.6.7                   
* Dependencies
  - Spring Web
  - Lombok      
  - Spring Data JPA
  - Validation
  - H2 Database   
