package DbTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import App.Application_for_classes;

public class requestLec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static void DeleteReqLec(String lecNum, String lecDiv) { //강좌코드를 불러와 삭제
		try {
			Connection con = getConnection();
			//DELETE를 사용해 강좌코드와 분반이 같은데이터를 삭제시킨다
			PreparedStatement delete = con.prepareStatement("DELETE FROM requestLec WHERE lecnum = '" + lecNum + "' AND lecDiv = '" + lecDiv + "'");
			delete.execute();
			System.out.println("데이터가 삭제되었습니다..");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String[][] getreqLec(){ // 데이터 불러오기
		try {
			Connection con = getConnection();
			//SELECT를 이용해 requestLec의 모든 데이터를 가져온다
			PreparedStatement statement = con.prepareStatement("SELECT * FROM requestLec");
			ResultSet results = statement.executeQuery(); // 결과값 저장
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()) {
				list.add(new String[] {
						results.getString("hakbun"),
						results.getString("lecNum"),
						results.getString("lecName"),
						results.getString("lecDiv"),
						results.getString("lecCredit"),
						results.getString("lecPro")
						});				
			}
			System.out.println("데이터를 불러왔습니다.");
			
			String[][] arr = new String[list.size()][6];
			
			return list.toArray(arr);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static String[][] getreqLec_u(String id){ // 모든 사용자가 신청한 목록에서 학번으로 비교해서 한명것만 가져오기
		try {
			//SELECT를 이용해 학번과 입력받은학번이 같은 데이터를 가져온다
			String sql = "SELECT * FROM requestLec WHERE hakbun = '" + id + "'"; 
			Connection con = getConnection();
			
			PreparedStatement statement = con.prepareStatement(sql);
			
			ResultSet results = statement.executeQuery(); // 결과값 저장
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()) {
				list.add(new String[] {
						results.getString("hakbun"),
						results.getString("lecNum"),
						results.getString("lecName"),
						results.getString("lecDiv"),
						results.getString("lecCredit"),
						results.getString("lecPro")
						});				
			}
			System.out.println("수강신청한 데이터를 불러왔습니다.");
			String[][] arr = new String[list.size()][6];
			return list.toArray(arr);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static void insertRequestLec1(String hakbun, String lecNum, String lecName, String lecDiv, String lecCredit, String lecPro) { // 테이블에 데이터 삽입
		String row2;
		int num = 0;
		try {
			Connection con = getConnection();
			//SELECT를 이용해 강의코드와 학번이 각각 입력받은 데이터와 일치한 데이터들을 뽑아 cnt에 몇개인지 전달한다
			PreparedStatement find = con.prepareStatement("SELECT COUNT(*) as cnt FROM requestLec WHERE lecNum = '" + lecNum + "' AND hakbun = '" + hakbun + "'");
			//rs에 결과값을 저장
			ResultSet rs = find.executeQuery();
            
            while(rs.next()){// rs의 데이터가 없어질때 까지 반복
            	//rs안에있는 cnt값을 row2에 전달
            	row2 = rs.getString("cnt"); 
            	//row2를 정수화 해 num에 전달
            	num = Integer.parseInt(row2);
            	
            }
		
			if(num == 0) { // num = 0 이라면(일치하는 row가 없다면) 중복되는 강의코드가 존재하지 않는다.
				//그러므로 데이터 삽입
				try {
					System.out.println("중복되는 강좌코드가 없다");
					PreparedStatement insert = con.prepareStatement(
							"INSERT INTO requestLec"
									+ "(hakbun, lecNum, lecName, lecDiv, lecCredit, lecPro) "
									+ "VALUE "
									+ "('"+hakbun+"','"+lecNum+"','"+lecName+"','"+lecDiv+"','"+lecCredit+"','"+lecPro+"')");
								   
					insert.executeUpdate();
					
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}else { // 아니면 중복되는 강의코드가 존재한다.
				System.out.println("데이터가 중복된다.");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	public static void createTable() { // 테이블 생성
		try {
			Connection con = getConnection();
			
			PreparedStatement create = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS "
					+ "requestLec(id int NOT NULL AUTO_INCREMENT," 
					+ "hakbun INT(10) NOT NULL," //학번
					+ "lecNum VARCHAR(255) NOT NULL,"	//강의코드
					+ "lecName VARCHAR(255) NOT NULL,"	//강좌명
					+ "lecDiv VARCHAR(255) NOT NULL,"	//분반
					+ "lecCredit VARCHAR(255) NOT NULL,"	//학점
					+ "lecPro VARCHAR(255) NOT NULL,"	//담당교수
					+ "PRIMARY KEY(id,hakbun,lecNum))");
					
			create.execute();
			System.out.println("테이블 생성 완료");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			System.out.println("테이블이 생성되었습니다.");
		}
	}
	
	public static Connection getConnection() { // DB 연결
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			
			String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, "root", "6359");
			System.out.println("mysql 데이터베이스에 성공적으로 접속했습니다.");
			
			
			return con;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}
	
	public static int insertRequestLec2(String hakbun, String lecNum, String lecDiv) throws Exception { // 테이블에 데이터 삽입
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
        String sql="";
        String exitelNum="";
        String exitelDiv="";
        String t1="";
        String t2="";
        String t3="";
      
        int x = 0;
        
        try{
            conn =getConnection();
            // SELECT를 이용해 Lecture에서 강의코드와 분반이 입력받은 각각의 데이터와 같은 데이터들을 가져온다
            sql ="SELECT * FROM Lecture where";
            sql += " lecNum = '" + lecNum +"'";
            sql += " and lecDiv = '"+ lecDiv + "'";
            pstmt =conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            
            while(rs.next()){
            	exitelNum = rs.getString("lecNum"); 
            	exitelDiv = rs.getString("lecDiv");
            	t1 = rs.getString("lecName");
            	t2 = rs.getString("lecCredit");
            	t3 = rs.getString("lecPro");
                	
            	// 강좌코드와 분반을 입력해 존재하는지 체크
            	x = 1; //이미 존재
            	
            	insertRequestLec1(hakbun,exitelNum,t1,exitelDiv,t2,t3);
            	System.out.println("등록완료");
            	break;
 
            }
          
        }catch(Exception e){
        	x = 0; //존재하지 않음     
            e.printStackTrace();
        } 
        return x;
  
	}

}



