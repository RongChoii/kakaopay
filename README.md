# 주택금융 서비스 API 개발

### 카카오페이 사전과제 : 주택금융 공급현황 분석 서비스

**[개발환경]**
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


## 1. 요구조건 및 제약사항
#### *주어진 **주택금융 공급현황 분석 데이터**를 기반으로 주택금융 공급기관(은행) 지원금액에 대한 통계를 제공하는 API를 개발한다.*

### 1.1 요구조건
```
- 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 개발

- 주택금융 공급 금융기관(은행) 목록을 출력하는 API 개발

- 년도별 각 금융기관의 지원금액 합계를 출력하는 API 개발

- 각 년도별 각 기관의 전체 지원금액 중에서 가장 큰 금액의 기관명을 출력하는 API 개발

- 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API 개발

- (선택) 특정 은행의 특정 달에 대해서 2018년도 해당 달에 금융지원 금액을 예측하는 API 개발
```

### 1.2 제약사항
```
- **ORM(Object Relational Mapping)** 을 사용하여 엔티티 정의 및 레퍼지토리 개발
	- Java 의 경우 **JPA**
	- 주택금융 공급기관(은행)은 독립 엔티티(기관명과 기관코드)로 디자인

- 단위테스트 개발 및 검증

- 모든 입출력은 JSON 형태

- README.md 작성(개발 프레임워크, 문제해결 전략, 빌드 및 실행방법)
```

## 2. 분석 및 설계
> 원하는 개발환경을 위한 프로젝트 생성 및 설정을 하고, 제공받은 .csv 파일을 읽어와서 생성한 데이터베이스에 접속, model(Entity)의 table에 저장하고, controller-service-query 순서로 호출하여 객체 형태로 데이터를 return 시킨다

### 2.1 프로젝트 생성
*Spring Initializr*로 원하는 개발환경을 설정하여 프로젝트를 생성
```
- Spring Boot 2.2.0
- JAVA
- Gradle Project
- Dependencies
	- Lombok [Core]
	- Web [Web]
	- JPA [SQL]
	- H@ [SQL]
```

### 2.2 .csv 파일 읽어오기
- BufferedReader 이용
- 머리행 한글 처리를 위해 EUC-KR로 Encoding 설정

### 2.3 데이터베이스 연결 및 저장
**H2 DB** 를 연결하기 위한 application.properties 설정
```
- DB 연결 설정 : url, username, password
- H2 접속 설정 : path
- Hibernate 설정 : update / create-drop
```

### 2.4 Entity 생성

.csv *년월별 금융기관 지원금액*에 대한 SupplyData 엔티티 생성
```
- 고유 id를 포함한 ~~id, year, month, molitFd, kbBank, ..., kebBank, etcBank~~ 컬럼으로 생성하려 했지만,
- 객체 그대로의 JSON 형태로 return하기 위해, 고유 id 없이 **year, month, bank, amount** 컬럼으로 생성
```
제약사항에 해당하는 *금융기관*에 대한 InstituteDate 엔티티 생성
```
- 기관명, 기관코드
```

### 2.5 insert 및 API 호출
- JpaRepository에서 제공하는 save() 내장메소드 사용
- 각 API 호출시 return 해야할 데이터 별로 entity와 JpaRepository 만들어서 사용

### 2.6 문제 해결 전략
(1) **데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API**
> .CSV파일의 한 row씩 읽을때 년, 월 레코드를 제외한 모든 column에서 save를 실행
>
> [YEAR, MONTH, BANK, AMOUNT] 테이블구조로 insert
<br>

(2) **주택금융 공급 금융기관(은행) 목록을 출력하는 API**
>  Enum 클래스에 생성된 은행기관코드 입력 후 조회 쿼리 호출
>
> `SELECT BANK FROM SUPPLY_DATA;`
<br>

(3) **년도별 각 금융기관의 지원금액 합계를 출력하는 API**
> return 해야할 json 형태의 하위구조에 따라 객체 list에 for문을 이용해서 하위 객체 생성
>
> 존재하는 데이터 연도를 조회하여 다음 쿼리로 연도 전송
>
> `SELECT YEAR, SUM(AMOUNT) AS AMOUNT FROM SUPPLY_DATA GROUP BY YEAR ORDER BY YEAR ASC`
>
> 연도별 금융기관 별 합계 조회 쿼리를 호출
>
> `SELECT BANK, SUM(AMOUNT) AS SUM_AMOUNT FROM SUPPLY_DATA WHERE YEAR = #{year} GROUP BY YEAR, BANK`
<br>

(4) **각 년도별 각 기관의 전체 지원금액 중에서 가장 큰 금액의 기관명을 출력하는 API**
> 연도 및 은행명으로 그룹화하여 합계금액을 조회 후 로직에서 처리
>
> `SELECT SUM(AMOUNT) AS AMOUNT, YEAR, BANK FROM SUPPLY_DATA GROUP BY YEAR, BANK;`
<br>

(5) **전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API**
> 외환은행을 파라미터로 받아 연도와 은행으로 그룹화한 금액의 최대 / 최소의 차이를 로직에서 비교 후 리턴
>
> `SELECT AVG(AMOUNT) AS AMOUNT, YEAR, BANK FROM SUPPLY_DATA WHERE BANK='외환은행' GROUP BY YEAR, BANK;`
<br>

(선택) **특정 은행의 특정 달에 대해서 2018년도 해당 달에 금융지원 금액을 예측하는 API**
> 외환은행을 파라미터로 받아 연도와 은행으로 그룹화한 금액의 최대 / 최소의 차이를 로직에서 비교 후 리턴
>
> `SELECT AVG(AMOUNT) AS AMOUNT, YEAR, BANK FROM SUPPLY_DATA WHERE BANK='외환은행' GROUP BY YEAR, BANK;`
<br>


## 3. 데이터베이스
*.csv 파일을 읽어와서 데이터가 저장되는 두 가지 테이블*

### 3.1 모든 API의 기본 테이블
- SupplyData

| 컬럼  | Type   |
| :---- | :----- |
| year | integer |
| month | integer |
| bank | String |
| amount | integer |

### 3.2 금융기관 : InstituteData
- InstituteData

| 컬럼  | Type   |
| :---- | :----- |
| instituteName | String |
| instituteCode | String |
<br>


## 4. API 정보 및 실행 방법

- **com.kakaopay.finance.service의 FinanceSevice의 49 line의 CSV 경로 수정**
`String csvPath = "C:/Users/rong/Desktop/카카오페이/2019경력공채_개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv";`

- **application.properties 의 2 line 의 DB 경로 수정**
`spring.datasource.url=jdbc:h2:file:C:/Users/rong/eclipse-workspace/financeDB;AUTO_SERVER=TRUE;`

- **메인화면 접속 (localhost)**

- **[데이터 저장] 버튼으로 csv파일을 DB에 저장한 뒤 각 API 에 따라 통계 데이터(JSON 형태) 확인**


### 4.1 데이터 저장 API
> URL : /f/insert
>
> Method : GET

- 저장 Table : SupplyData

| 컬럼  | Type   |
| :---- | :----- |
| year | integer |
| month | integer |
| bank | String |
| amount | integer |

### 4.2 은행 목록 출력 API
> URL : /f/bankList
>
> Method : GET

- JSON 리턴 객체 : List<Bank>
- Bank

| 컬럼  | Type   |
| :---- | :----- |
| bank | String |

### 4.3 년도별 각 금융기관의 지원금액 합계 출력 API
> URL : /f/sumPerBankPerYear
>
> Method : GET

- JSON 리턴 객체 : SupplyListTotal
- SupplyListTotal

| 컬럼  | Type   |
| :---- | :----- |
| id | integer |
| name | String |
| list | List<SupplyList> |

- SupplyList

| 컬럼  | Type   |
| :---- | :----- |
| year | String |
| total_amount | int |
| detail_amount | Map<String, Object> |


### 4.4 각 년도별 각 금융기관의 전체 지원금액 중에서 가장 큰 금액의 년도와 금융기관 출력 API
> URL : /f/maxPerData
>
> Method : GET

- JSON 리턴 객체 : BestBank

| 컬럼  | Type   |
| :---- | :----- |
| year | integer |
| bank | String |

### 4.5 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API
> URL : /f/maxMinForKeb
>
> Method : GET

- JSON 리턴 객체 : BankStatistics

| 컬럼  | Type   |
| :---- | :----- |
| bank | String |
| support_amount | List<YearAmount> |

- YearAmount

 | 컬럼  | Type   |
| :---- | :----- |
| year | integer |
| amount | integer |

### 4.6 특정 은행의 특정 달에 대해서 2018년도 해당 달에 금융지원 금액을 예측 API
> URL : /f/estimateValue
>
> Method : GET

> 입력

| 컬럼  | Type   |
| :---- | :----- |
| bank | String |
| month | integer |

> 출력

| 컬럼  | Type   |
| :---- | :----- |
| bankCode | String |
| year | integer |
| month | integer |
| amount | integer |
