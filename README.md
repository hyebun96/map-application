#  🗺 지역화폐 지도 어플리케이션
경기지역화폐는 2019년에 처음 정책이 시행되었다. 처음에 정책이 시행 될 때 백화점이나 대형마트, 인터넷 쇼핑몰, 유흥주점 등에서는 사용이 어렵기 때문에 그 외 소상공인 업체 가맹점 수가 많지 않아 사용자가 많이 사용을 할 수 있을지에 대한 우려가 생겼다. 하지만 우려와는 다르게 빠르게 경기지역화폐 산후조리, 청년배당과 같은  정책으로 대상들에게 빠르게 퍼져나가면서 이용자가 늘어났다. 이용자가 늘어가면서 만족도는 높아 졌지만 불편한 점들이 생겨나기 시작했다. 문제는 경기지역화폐 가맹점을 찾는 것이 불편하다는 점이었다. 경기지역화폐 가맹점 Application이 있었지만 그 Application들은 지역을 선택 후, 정확한 가맹점명을 검색하도록 구현되어있었다. 그것은 가맹점 명을 모른다면 Application을 사용할 수 없는 치명적인 단점을 담고 있었다. 기존 Application의 장점과 단점을 분석하고 논문하여 사용자들이 좀 더 편리하게 사용하여 지역 경제 활성화에 도움이 되는 Application을 구현하는 것이 목적이다.

<br/>

## 구현 화면
### - 메인 화면
![image](https://user-images.githubusercontent.com/58923654/91039523-aeec2700-e647-11ea-92c0-de1a197919ea.png)
<br/>

### - 메뉴 기능
![image](https://user-images.githubusercontent.com/58923654/91039598-cc20f580-e647-11ea-9b17-b4d27b00fea8.png)
<br/>

### - 검색 기능
![image](https://user-images.githubusercontent.com/58923654/91039675-f377c280-e647-11ea-8645-b534c75b59bb.png)
<br/>

## 참고
[경기지역화폐](http://www.gmoney.or.kr/)<br/>
[논문 사이트](https://www.data.go.kr/)
<br/>

## 목차
1. 기능
2. 기술사양
<br/>

## 1.기능

* openAPI_GPS
  - Open API인 경기지역화폐 가맹점 안양시의 데이터를 가져와 Application에 데이터베이스로 연결하여 제공
* 메뉴 기능
  - 지역화폐 사용자들이 많이 찾는 업종 5가지로 분류  
  - 음식점 / 카페 / 빵집 / 병원 / 약국
  - 클릭시, 관련 업종만 지도 속 아이콘
* 검색 기능
  - 상단의 검색 메뉴로 가맹점 상호명 검색
  - 지도상에서 가맹점 위치 아이콘 표시
  - 검색한 가맹점 상세 정보 확인(매장명, 매장타입, 상세주소, 전화번호)

</br>

## 2.기술사양
 Android Studio<br/>
 Java<br/>
 Google open API

