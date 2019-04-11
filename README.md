# 주택금융 서비스 API 개발

### 카카오페이 사전과제 : 주택금융 공급현황 분석 서비스

**[개발환경]**
```
- Backend
	- Spring 
	- JAVA8
	- Spring Boot 1.5.10
	- JPA
	- Hibernate
	- Gradle
		- Lombok [Core]
		- Web [Web]
		- JPA [SQL]
		- H2 [SQL]

- Frontend 
  - Jquery 3.3.1
  - Bootstrap 3
```

<br>
## 1. 요구조건 및 제약사항
#### *주어진 **주택금융 공급현황 분석 데이터**를 기반으로 주택금융 공급기관(은행) 지원금액에 대한 통계를 제공하는 API를 개발한다.*

<br>
## 1.1 요구조건
- 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 개발

- 주택금융 공급 금융기관(은행) 목록을 출력하는 API 개발

- 년도별 각 금융기관의 지원금액 합계를 출력하는 API 개발

- 각 년도별 각 기관의 전체 지원금액 중에서 가장 큰 금액의 기관명을 출력하는 API 개발 

- 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API 개발

- (선택) 특정 은행의 특정 달에 대해서 2018년도 해당 달에 금융지원 금액을 예측하는 API 개발

<br>
## 1.2 제약사항

- **ORM(Object Relational Mapping)**을 사용하여 엔티티 정의 및 레퍼지토리 개발
	- Java 의 경우 **JPA**
	- 주택금융 공급기관(은행)은 독립 엔티티(기관명과 기관코드)로 디자인

- 단위테스트 개발 및 검증

- 모든 입출력은 JSON 형태

- README.md 작성(개발 프레임워크, 문제해결 전략, 빌드 및 실행방법)

<br><br>
## 2. 분석 및 설계 (문제해결 전략)
#### _원하는 개발환경을 위한 프로젝트 생성 및 설정을 하고, 제공받은 .csv 파일을 읽어와서 생성한 데이터베이스에 접속, model(Entity)의 table에 저장하고, controller-service-query 순서로 호출하여 객체 형태로 데이터를 return 시킨다_

<br>
## 2.1 프로젝트 생성
*Spring Initializr*로 원하는 개발환경을 설정하여 프로젝트를 생성
```
- String Boot 2.2.0
- JAVA
- Gradle Project
- Dependencies
	- Lombok [Core]
	- Web [Web]
	- JPA [SQL]
	- H@ [SQL]
```

<br>
## 2.2 .csv 파일 읽어오기
- BufferedReader 이용
- 머리행 한글 처리를 위해 EUC-KR로 Encoding 설정

<br>
## 2.3 데이터베이스 연결 및 저장
**H2 DB** 를 연결하기 위한 application.properties 설정
```
- DB 연결 설정 : url, username, password
- H2 접속 설정 : path
- Hibernate 설정 : update, create-drop
```

<br>
## 2.4 Entity 생성

.csv *년월별 금융기관 지원금액*에 대한 SupplyData 엔티티 생성
- ~~id, year, month, molitFd, kbBank, wrBank, shBank, sitiBank, hnBank, nhBank, kebBank, etcBank~~
- ~~id, year, month, bank, amount~~
- 객체 그대로의 JSON 형태로 return하기 위해, 고유 id 없이 ** year, month, bank, amount ** 컬럼으로 저장한다.

제약사항에 해당하는 *금융기관*에 대한 InstituteDate 엔티티 생성
- **기관명, 기관코드** 컬럼

<br>
## 2.5 insert 및 API 호출 select 쿼리 실행
- JpaRepository에서 제공하는 save() 내장메소드 사용
- 각 API 호출시 return 해야할 데이터 별로 entity와 JpaRepository 만들어서 사용



