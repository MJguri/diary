# Jsp&Sevlet을 활용하여 만든 Diary프로젝트를 <br> Spring 프레임워크를 활용하여 만들어보기
* 2022-01-29
   * Pom.xml 설정
      * java, springframework 버전 설정
      * log4j
      * ojdbc8 (https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc8)
      * c3p0 (https://mvnrepository.com/artifact/com.mchange/c3p0)
      * spring jdbc (https://mvnrepository.com/artifact/org.springframework/spring-jdbc)
      * servlet-api (https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api)
      * servlet.jsp-api (https://mvnrepository.com/artifact/javax.servlet.jsp/javax.servlet.jsp-api)
* 2022-01-31
   * IdCheckController (회원가입시, 중복아이디 확인 기능) 추가
   * LoginController (로그인버튼 클릭시, 로그인 폼으로 이동기능) 추가
   * MemberJoinController ( 회원가입 버튼 클릭시, 회원가입 폼으로 이동기능) 추가
   * 위의 3개의 Controller를 xml 설정파일에 빈객체로 생성
   * MemberVO, MeberDAO 객체 생성 후 xml에 빈 객체로 생성 
* 2022-02-01 
   * 회원가입 기능 구현
   * 로그인 기능 구현 
   * DiaryListController (로그인시 자신의 일기 LIST 불러오기 기능) 추가
   * DiaryVO, DiaryDAO 객체 생성 후 xml에 빈객체로 생성
* 2022-02-02
   * Diary 게시판 CRUD 기능 구현
   * 자동주입기능 사용   
