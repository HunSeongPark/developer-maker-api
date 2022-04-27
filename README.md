# developer-maker-api
기본적인 CRUD 기능 구현 API - 개발자 내맘대로 고용하고 퇴직시키기                       
강의 : 

## Project Goal
Spring을 활용한 API 설계 및 구현 / 예외, 오류 처리 익혀보기

## Project Features
- 고용 중인 모든 개발자 조회 : `GET /developers`
- 개발자 추가 : `POST /create-developer`
- 개발자 상세 조회 : `GET /developer/{memberId}`
- 개발자 수정 : `PUT /developer/{memberId}`
- 개발자 삭제 : `DELETE /developer/{memberId}`

## Project Setting              
* Spring Boot version : 2.6.7                   
* Dependencies
  - Spring Web
  - Lombok      
  - Spring Data JPA
  - Validation
  - H2 Database   
