package DbTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Lecture {
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		try {
			System.out.println(DBCheck("113", "113"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
	
	public static void DeleteLecture(String lecNum) { //강좌코드를 불러와 삭제
		try {
			// DB에 연결
			Connection con = getConnection();
			// sql문을 이용해 내용을 Lecture테이블에서 입력받은값과 lecNum이 같으면 데이터를 제거 후 결과를 delete에 전달
			PreparedStatement delete = con.prepareStatement("DELETE FROM Lecture WHERE lecNum = '" + lecNum + "'");
			//delete 실행
			delete.execute();
			System.out.println("데이터가 삭제되었습니다..");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public static String[][] getLecture(){ // 데이터 불러오기
		try {
			Connection con = getConnection();
			// sql문을 이용해 Lectrue에서 모든 값을 가져온다
			PreparedStatement statement = con.prepareStatement("SELECT lecNum, lecName, lecDiv, lecCredit, lecPro FROM Lecture");
			ResultSet results = statement.executeQuery(); // 결과값 저장
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()) {
				list.add(new String[] {
						results.getString("lecNum"),
						results.getString("lecName"),
						results.getString("lecDiv"),
						results.getString("lecCredit"),
						results.getString("lecPro")
						});				
			}
			System.out.println("데이터를 불러왔습니다.");
			
			String[][] arr = new String[list.size()][5];
			
			return list.toArray(arr);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	public static void insertLecture(String lecNum, String lecName, String lecDiv, String lecCredit, String lecPro) { // 테이블에 데이터 삽입
		try {
			Connection con = getConnection();
			// INSERT를 이용해 Lecture에 입력받은 데이터 삽입
			PreparedStatement insert = con.prepareStatement(
					"INSERT INTO Lecture"
							+ "(lecNum, lecName, lecDiv, lecCredit, lecPro) "
							+ "VALUE "
							+ "('"+lecNum+"','"+lecName+"','"+lecDiv+"','"+lecCredit+"','"+lecPro+"')");
				   insert.executeUpdate();
				   System.out.println("데이터가 추가 되었습니다.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void createTable() { // 테이블 생성
		try {
			Connection con = getConnection();
			
			PreparedStatement create = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS "
					+ "Lecture(id INT NOT NULL AUTO_INCREMENT," 
					+ "lecNum VARCHAR(255) NOT NULL,"	//강의코드
					+ "lecName VARCHAR(255) NOT NULL,"	//강좌명
					+ "lecDiv VARCHAR(255) NOT NULL,"	//분반
					+ "lecCredit VARCHAR(255) NOT NULL,"	//학점
					+ "lecPro VARCHAR(255) NOT NULL,"	//담당교수
					+ "PRIMARY KEY(id, lecNum))");
			create.execute();
			System.out.println("테이블 생성 완료");
			
		}catch(Exception e) {
			System.out.println("테이블 생성 안됌");
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
	
	public static int DBCheck(String lecNum, String lecDiv)throws Exception{ // 강의 추가시 강좌코드와 분반을 기준으로 중복체크
        
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
        String sql="";
        String exiteNum="";
        String exiteDIV="";
        int x2 = 0;
        
        try{
            conn = getConnection();
            // SELECT를 이용해 Lecture에서 강의코드와 분반 가져오기
            sql ="SELECT lecNum, lecDiv FROM Lecture";
            pstmt =conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            
            while(rs.next()){
            	exiteNum = rs.getString("lecNum"); 
            	exiteDIV = rs.getString("lecDiv");
                if(lecNum.equals(exiteNum) && lecDiv.equals(exiteDIV)) 
                {	// 등록하고싶은 강좌코드와 분반이 이미 존재하는지 체크
                    x2 = 1; //이미 존재
                    break;
                }else{
                	x2 = 0; //존재하지 않음            
                }
            }
        }catch(Exception e){
           // e.printStackTrace();
        }
        
        return x2;
    }

}
