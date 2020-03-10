## API 규격

### 메인화면 관련

#### 회원정보 관련
`POST /api/v1/login`
```json
{"id" : "dfasdfdas", "password" : "adsfhjasdfhaskjdfhkl"}

```
- response body
```json
{"result" : "ok", "statusCode" : 200, "token" : (JWT Token)} 
```

#### 위치기반 리스트 조회 API
`POST /api/v1/location`

- request body 
```json

{
  "since" : "2020-01-01 15:00:00",
  "until" : "2020-01-02 12:00:00",
  "latitude" : "37.24314134134",
  "longitude" : "127.13413412312",
  "townName" : (Optional)"정자동"
}
```

- response body
```json
{
  Array<Studios> 
}
```

#### 스튜디오 단건 조회 API
`GET /api/v1/studios/{studioId}`

- response: 하단 Studios 참고

#### 스튜디오 검색 API
`POST /api/v1/studios/search`
- request body
```json
{"latitude" : 37.12123, "longitude": 127.3184134, "townName(optional)": "정자동"}
```

- response body
```json
{"studios" : Array<Studios>
```

### 엔티티
- Lessons

```json
{
  "name" : 강좌의 이름,
  "allSessions" : 등록된 모든 수업시간
  "instructorName" : 강사 이름
  "studioInfo" : 스튜디오 정보
} 
```

- Studios
```json
{
  "studioId" : 스튜디오 고유 번호,
  "name" : 스튜디오 명칭,
  "ratings" : 별점(0.5점 단위),
  "location" : 등록된 지역명(주소 아님),
  "introduction" : {
    "detailedAddress" : 상세주소,
    "contact" : 대표연락처,
    "socialAddress" : {
        "kakaoId"
        "blogAddress"
        "instagramAddress"
        "homepage"
        "facebookAddress"
      }
  },
  "reviews" : Array<Review>,
  "reviewCount" : 리뷰의 갯수  
}
```

- Review

```json

{
"id" : 고유번호,
"content" : 리뷰 본문,
"starPoint" : 별점(0.5점 단위),
"ownersReply" : 사장님 댓글
}
```