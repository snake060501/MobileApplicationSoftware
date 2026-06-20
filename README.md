# MobileApplicationSoftware
모바일응용소프트웨어 과제 251416 이승민
프로젝트 실행 방법
File->New->New Project->Empty Views Activity->name->BookMarket
AndroidStudioProjects->BookMarket->app->src->main에 다운로드한 main을 덮어쓰기 복사
build.gradle.kts(Module :app)에서 android { 다음 줄에 viewBinding.enable=true 삽입하고 compileSdk 부분을 compileSdk=37로 바꾼다.
