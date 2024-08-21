# [MUSINSA] Java(Kotlin) Backend Engineer - 과제

## 사용 기술
1. Language : JAVA
2. FrameWork : Spring Boot 3.3.2
3. DB : H2 DataBase
4. SQL Mapper : MyBatis
5. BuildTool : gradle

## 빌드 및 실행 방법
1. ./gradlew clean build
2. cd build/libs
3. java -jar musinsa-api-0.0.1-SNAPSHOT.jar


## Swagger
- http://localhost:8080/swagger-ui/index.html

## 과제
### 구현 1) - 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
+ Curl : curl -X 'GET' \
  'http://localhost:8080/api/search/category/lowest-price' \
  -H 'accept: */*'
+ Request URL : http://localhost:8080/api/search/category/lowest-price
+ Response body :
```
{
  "code": 200,
  "httpStatus": "OK",
  "data": {
    "상품": [
      {
        "브랜드": "C",
        "카테고리": "상의",
        "가격": "10,000"
      },
      {
        "브랜드": "F",
        "카테고리": "악세서리",
        "가격": "1,900"
      },
      {
        "브랜드": "A",
        "카테고리": "가방",
        "가격": "2,000"
      },
      {
        "브랜드": "I",
        "카테고리": "양말",
        "가격": "1,700"
      },
      {
        "브랜드": "D",
        "카테고리": "모자",
        "가격": "1,500"
      },
      {
        "브랜드": "D",
        "카테고리": "바지",
        "가격": "3,000"
      },
      {
        "브랜드": "E",
        "카테고리": "아우터",
        "가격": "5,000"
      },
      {
        "브랜드": "G",
        "카테고리": "스니커즈",
        "가격": "9,000"
      }
    ],
    "총액": "34,100"
  }
}
```
### 구현 2) - 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
+ Curl : curl -X 'GET' \
  'http://localhost:8080/api/search/brand/lowest-price' \
  -H 'accept: */*'
+ Request URL : http://localhost:8080/api/search/brand/lowest-price
+ Response body :
  ```
  {
    "code": 200,
    "httpStatus": "OK",
    "data": {
      "최저가": {
        "브랜드": "D",
        "카테고리": [
          {
            "카테고리": "상의",
            "가격": "10,100"
          },
          {
            "카테고리": "아우터",
            "가격": "5,100"
          },
          {
            "카테고리": "바지",
            "가격": "3,000"
          },
          {
            "카테고리": "스니커즈",
            "가격": "9,500"
          },
          {
            "카테고리": "가방",
            "가격": "2,500"
          },
          {
            "카테고리": "모자",
            "가격": "1,500"
          },
          {
            "카테고리": "양말",
            "가격": "2,400"
          },
          {
            "카테고리": "악세서리",
            "가격": "2,000"
          }
        ],
        "총액": "36,100"
      }
    }
  }
  ```
### 구현 3) - 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
+ pathVariable : 카테고리명
+ Curl : curl -X 'GET' \
  'http://localhost:8080/api/search/category/price-range/TOP' \
  -H 'accept: */*'
+ Request URL : http://localhost:8080/api/search/category/price-range/TOP
+ Response body :
```
{
  "code": 200,
  "httpStatus": "OK",
  "data": {
    "카테고리": "상의",
    "최저가": [
      {
        "브랜드": "C",
        "가격": "10,000"
      }
    ],
    "최고가": [
      {
        "브랜드": "I",
        "가격": "11,400"
      }
    ]
  }
}
```

### 구현 4) 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
+ GOODS - GoodsController
  + 모든 상품 조회 
    - GET /api/goods
  + 상품 조회 
    - GET /api/goods/{id}
  + 상품 등록 
    - POST /api/goods
  + 상품 수정 
    - PUT /api/goods
  + 상품 삭제 
    - DELETE /api/goods/{id}

+ BRAND - BrandController
  + 모든 브랜드 조회 
    - GET /api/brand
  + 브랜드 id 조회 
    - GET /api/brand/id/{id}
  + 브랜드 명 조회 
    - GET /api/brand/name/{name}
  + 브랜드 등록 
    - POST /api/brand
  + 브랜드 수정 
    - PUT /api/brand
  + 브랜드 삭제 
    - DELETE /api/brand
