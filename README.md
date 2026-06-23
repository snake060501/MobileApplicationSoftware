====================================================================
============
[모바일 응용 소프트웨어 기말 프로젝트 제출]
학번: 251416
이름: 이승민
분반: 2분반
====================================================================
============
1. 앱 기획 및 테마 설명
- 앱 이름: 내 손안의 북마켓
- 테마: AI 및 LLM을 활용한 바이브 코딩 관련 전문 서적 쇼핑몰
- 기획 의도: 최신 개발 트렌드인 AI 관련 도서를 쉽게 탐색하고 장바구니에 담을 수 있는 직관적인 모바일 쇼핑물을 기획함
2. 개발 및 실행 환경
- IDE: Android Studio Panda 2 | 2025.3.2
- 언어: Kotlin
- Min SDK: 26
- Target SDK: 36
* build.gradle.kts(Module :app)에서 android { 다음 줄에 viewBinding.enable=true 삽입하고 실행 안될 시 compileSdk 부분을 compileSdk=37로 바꾼다.
3. 주요 액티비티(Activity) 및 XML 구성
본 앱은 기능별로 클래스와 레이아웃이 철저히 분리되어 있습니다.
[인트로 및 메인 허브]
- SplashActivity: 앱 최초 실행 시 나타나는 시작 화면. Handler를 사용하여 2.5초 지연 후 메인 화면으로 자동 전환됩니다.
- MainActivity: 앱 로고와 환영 문구가 표시되는 메인 허브 화면. 상단 앱바(옵션 메뉴)와 좌측 슬라이드 방식의 내비게이션 드로어(NavigationView)를 지원하여 다른 기능으로 쉽게 이동할 수 있습니다.
 (관련 XML: activity_splash.xml, activity_main.xml, drawer_menu.xml, toolbar_menu.xml)
[도서 상점 및 구매 기능]
- BookListActivity : 판매 중인 책 목록 제공. RecyclerView와 BookAdapter,
BookData 클래스를 이용해 목록 구현 (item_book.xml)
- BookDetailActivity: 선택한 도서의 상세 정보(표지, 가격, 출판일 등)를 확인하고 장바구니에 담는 기능. 데이터는 Serializable 인터페이스를 통해 안전하게 전달됩니다.
- 도서 검색 기능 (book_list_menu.xml)
 (관련 XML: activity_cart.xml, activity_my_page.xml, item_cart.xml)

[장바구니 및 마이페이지]
- CartActivity: 장바구니 화면. CartManager 싱글톤 객체(object)를 통해 화면 이동 간에도 데이터를 안전하게 보존하며, 총 결제 금액을 실시간으로 계산해 보여줍니다.
- MyPageActivity: 마이페이지 로그인 기능. SharedPreferences를 활용해 비밀번호 유효성 검사를 거친 후 사용자 계정 정보를 로컬에 저장하여, 앱을 재실행해도 로그인 세션이 유지됩니다.
 (관련 XML: activity_diary.xml, activity_bucketlist.xml)

4. 앱 실행 및 조작 방법
1) Android Studio의 File->New->New Project->Empty Views Activity->name->BookMarket
2) 폴더 AndroidStudioProjects->BookMarket->app->src->main에 다운로드한 main을 덮어쓰기 복사
3) 하단 상태 표시줄에서 Gradle Sync가 완료될 때까지 대기합니다.
4) 앱의 첫 시작점인 SplashActivity를 기준으로 앱을 실행(Run) 합니다.
5) 스플래시 화면을 거쳐 MainActivity가 열리면, 좌측 상단의 햄버거 메뉴(≡)를 누르거나 화면의 왼쪽 끝에서 오른쪽으로 스와이프하여 내비게이션 드로어를 엽니다.
6) 상단 툴바 우측의 옵션 메뉴를 통해서도 마이페이지 등으로 빠르게 이동할 수 있습니다.
7) '도서 목록 보러가기' 버튼을 클릭 후, 상단 돋보기 아이콘을 눌러 실시간 검색 기능을 테스트해 볼 수 있습니다.
8) 도서 상세 화면에서 장바구니 담기를 누르면 AlertDialog 알림창이 표시되며, 즉시 장바구니로 이동하여 금액을 확인할 수 있습니다.
====================================================================
============
