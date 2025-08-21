# 영화 예매 관리 시스템

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"> <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white"> <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> <img src="https://img.shields.io/badge/SQL%20Developer-F80000?style=for-the-badge&logo=oracle&logoColor=white">

데이터베이스를 연결하여 고객 관리 및 영화 예매 관리를 할 수 있는 JDBC 프로그램입니다. <br>
고객 추가, 수정, 영화 예매 및 다양한 영화 관련 기능을 제공합니다.

## 목차
- [주요 기능](#-주요-기능)
- [시스템 흐름도](#-시스템-흐름도)
- [기능 상세 설명](#-기능-상세-설명)
- [데이터베이스 구조](#-데이터베이스-구조)
- [설치 및 실행](#-설치-및-실행)

## 주요 기능

### 고객 관리
- **고객 추가**: 새로운 고객 정보를 시스템에 등록
- **고객 수정**: 기존 고객의 정보 (이름, 전화번호) 수정
- **고객 삭제**: 고객 정보 삭제

### 영화 예매 시스템
- **영화 예매**: 원하는 영화의 상영시간과 좌석 선택하여 예매
- **예매 조회**: 고객 ID로 영화 예매 내역 확인
- **예매 정보 수정**: 예매번호로 상영시간, 좌석 변경
- **예매 취소**: 예매번호로 예매한 영화 취소

### 관리자 모드
- **영화 관리**: 영화 정보 추가, 수정
- **예매 내역 관리**: 전체 예매 내역 조회
- **고객 목록 관리**: 등록된 고객 명단 조회
- **영화 목록 관리**: 상영 중인 영화 목록 조회

## 시스템 흐름도

### 일반 사용자 흐름
```
회원 여부 확인 (Y/N)
├── N (비회원)
│   ├── 신규 고객 정보 입력
│   ├── customer2 테이블에 INSERT
│   └── 영화 예매 진행
└── Y (기존 회원)
    ├── 고객 ID 확인
    └── 영화 예매 진행
```

### 관리자 흐름
```
관리자 비밀번호 입력
├── 영화 관리
├── 예매 내역 조회
├── 고객 목록 조회
└── 영화 목록 조회
```

## 기능 상세 설명

### 1. 영화 예매
**신규 고객 (N 선택)**
- 고객 정보 입력 → customer2 테이블에 INSERT
- 영화번호, 상영시간, 예매 좌석 선택 → bookings 테이블에 INSERT

**기존 회원 (Y 선택)**
- 고객 ID, 영화번호, 상영시간, 예매 좌석 선택 → bookings 테이블에 INSERT

### 2. 예매 조회
- 고객 ID 입력
- customer2, movies, bookings 테이블 조인하여 SELECT

### 3. 예매 취소
- 고객 ID, 전화번호 뒤 4자리, 예매번호 입력
- bookings 테이블에서 DELETE

### 4. 예매 정보 수정
- 고객 ID, 전화번호 뒤 4자리, 예매번호 입력
- 새로운 상영시간, 좌석 정보 입력
- bookings 테이블 UPDATE

### 5. 관리자 기능
**영화 정보 수정**
- 영화 번호 입력 후 제목, 장르, 개봉일 수정

**영화 추가**
- 영화 제목, 장르, 개봉일 입력하여 신규 영화 추가

**데이터 조회**
- 전체 영화 예매 내역 출력
- customer2 테이블의 고객 명단 출력  
- movies 테이블의 영화 목록 출력

## 데이터베이스 구조

### 주요 테이블
- **customer2**: 고객 정보 (고객ID, 이름, 전화번호)
- **movies**: 영화 정보 (영화번호, 제목, 장르, 개봉일)
- **bookings**: 예매 정보 (예매번호, 고객ID, 영화번호, 상영시간, 좌석)

### 테이블 관계
```
customer2 (1) ──── (N) bookings (N) ──── (1) movies
```

---
**개발 환경**: Java + Eclipse + Oracle Database + SQL Developer 
