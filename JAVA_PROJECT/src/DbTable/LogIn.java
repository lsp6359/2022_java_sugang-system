package DbTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LogIn {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public static void Deletehakbun(String hakbun) { //가입된 학번 삭제
		try {
			Connection con = getConnection_Login();
			//DELETE를 이용해 학번이 입력받은 학번과 같다면 데이터 삭제
			PreparedStatement delete = con.prepareStatement("DELETE FROM Login WHERE hakbun = '" + hakbun + "'");
			delete.execute();
			System.out.println("데이터가 삭제되었습니다..");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void insertLogin(int hakbun, String password) { // 테이블에 데이터 삽입
		try {
			Connection con = getConnection_Login();
			//INSERT를 이용해 로그인테이블의 학번과 패스워드에 입력받은 학번과 패스워드를 넣어준다
			PreparedStatement insert = con.prepareStatement(""
				     + "INSERT INTO Login"
				     + "(hakbun, password) "
				     + "VALUE "
				     + "('"+hakbun+"','"+password+"')");
				   insert.executeUpdate();
				   System.out.println("데이터가 추가 되었습니다.");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String[][] getLogin(){ // 데이터 불러오기
		try {
			Connection con = getConnection_Login();
			//SELECT를 이용해 로그인테이븗의 학번과 패스워드를 가져온다
			PreparedStatement statement = con.prepareStatement("SELECT hakbun, password FROM Login");
			ResultSet results = statement.executeQuery(); // 결과값 저장
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()) {
				list.add(new String[] {
						results.getString("hakbun"),
						results.getString("password"),
					
						});				
			}
			System.out.println("데이터를 불러왔습니다.");
			
			String[][] arr = new String[list.size()][2];
			
			return list.toArray(arr);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static void createTable_Login() { // 테이블 생성
		try {
			Connection con = getConnection_Login();
			
			PreparedStatement create = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS "
					+ "Login(id int NOT NULL AUTO_INCREMENT," 
					+ "hakbun INT(10) NOT NULL," //학번
					+ "password VARCHAR(255) NOT NULL,"	//비밀번호
					+ "PRIMARY KEY(id,hakbun))");
			create.execute();
			System.out.println("테이블 생성 완료");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			System.out.println("테이블이 생성되었습니다.");
		}
	}
		
	public static Connection getConnection_Login() { // DB 연결
		
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			
			String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, "root", "1234");
			System.out.println("mysql 데이터베이스에 성공적으로 접속했습니다.");
		
			return con;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}	
	}
	
	
	public static int userCheck(String id, String passwd)throws Exception{ // 로그인 과정
        
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
        String sql="";
        String dbpasswd="";
        int x = -1;
        
        try{
            conn =getConnection_Login();
            sql ="SELECT password FROM Login WHERE hakbun = ?";
            pstmt =conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs=pstmt.executeQuery();
            
            if(rs.next()){
            	dbpasswd =rs.getString("password");
                if(dbpasswd.equals(passwd)) {
                    x=1; //인증성공
                }
                else {
                    x=0; //비밀번호 틀림                 
                }
            }else {
                x=-1; //해당 아이디 없음              
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return x;
    }
	
public static int DBCheck(String id)throws Exception{ // 가입시 학번중복 체크
        
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
        String sql="";
        String exiteID="";
        int x = 0;
        
        try{
            conn =getConnection_Login();
            //SELECT를 사용해 로그인테이블의 학번을 가져온다
            sql ="SELECT hakbun FROM Login";
            pstmt =conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            
            while(rs.next()){	//rs가 한칸씩 증가하면서 더이상 데이터가 없을때 까지 반복
            	//rs의 학번을 존재여부를 판단하는 exiteID에 넣는다
            	exiteID = rs.getString("hakbun"); 
            	
                if(id.equals(exiteID)) {	//가입하고싶은 학번이 이미 존재할경우
                    x = 1; //이미 존재
                    break;
                }
                else {
                	x = 0; //존재하지 않음            
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return x;
    }
	
}
