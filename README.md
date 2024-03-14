# 2022_java_sugang-system

# 1. 작품명

수강신청 프로그램

# 2. 과제 제안 및 배경

수업시간에 배운 것을 최대한 다양하게 활용하여 만들 수 있는 아이디어가 무엇이 있을까 여러 가지 프로그램들을 생각하던 중 우연히 대학교 수강신청 프로그램이 떠올랐다. 이거라면 GUI와 DB를 연동해 재미있게 만들어 볼 수 있지 않을까 생각이 들어 수강신청 프로그램으로 결정하게 되었다.

# 3. 과제의 목표

1. GUI와 DB를 연동해 테이블들 사이의 삽입, 삭제, 수정 등을 가능하게 구현하기
2. DB에서 원하는 값만 가져올 수 있게 구현하기
3. DB에서 중복되는 값을 가져오거나 삭제시킬 수 있게 구현하기

# 4. 프로그램 소개

수강신청 프로그램을 통해 각각의 학생들이 등록된 강의를 수강신청, 수강취소를 가능하게 한다. 또한 관리자 계정을 통해 강의를 등록, 삭제도 가능하게 구현 하였다.

# 5. 과제 수행 일정

![image](https://github.com/lsp6359/2022_java_sugang-system/assets/130120597/a83e3696-807f-49d8-bbfb-ff72917c60e5)

# 6. 알고리즘

![image](https://github.com/lsp6359/2022_java_sugang-system/assets/130120597/ad3bdae1-16a9-4d45-a4f0-d85bead2767d)

# 7. 실행 화면

1. 로그인 화면
   ![image](https://github.com/lsp6359/2022_java_sugang-system/assets/130120597/fb1bf4ab-caf2-495d-97fa-de16158b8e3a)
2. 수강신청 화면
   ![image](https://github.com/lsp6359/2022_java_sugang-system/assets/130120597/69981790-f7ab-4158-80ff-26f678c835b1)
3. 관리자 계정 화면 (강의를 추가, 삭제시키는 장면)
   ![image](https://github.com/lsp6359/2022_java_sugang-system/assets/130120597/00355c23-4175-4bae-b743-ab9ad8922714)
4. 관리자 계정 화면 (가입된 계정을 조회, 제거 시킬 수 있는 화면)
   ![image](https://github.com/lsp6359/2022_java_sugang-system/assets/130120597/167dbc60-9926-438c-981f-a40a8793d602)

# 8. 프로그램 설명

Eclipse IDE 개발
Application_for_classes : 프로그램 실행소스
requesLec : 수강신청테이블
Lecture : 강의테이블
Login : 학생들의 아이디, 패스워드가 들어가있는 테이블

# 9. etc

mysql을 넣어놓지 않았기 때문에 실행시키기 위해선 mysql의 test 데이터베이스가 필요하다. (password : 1234)
