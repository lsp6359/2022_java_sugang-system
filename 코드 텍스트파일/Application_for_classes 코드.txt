package App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DbTable.Lecture;
import DbTable.LogIn;
import DbTable.requestLec;


public class Application_for_classes {

	private JFrame frame;
	private static JTextField loginTxt;
	private JPasswordField pwTxt;
	private JTextField registHakbunTxt;
	private JTextField registPwTxt;
	private JTextField txtlecNum;
	private JTextField txtlecName;
	private JTextField txtlecDiv;
	private JTextField txtlecCredit;
	private JTextField txtlecPro;
	private JTextField lecNum1;
	private JTextField lecDiv1;
	
	static JPanel requestPanel;
	static JPanel tablepanel;
	static JPanel AdminPanel;
	static JPanel LoginPanel;
	private JTextField txtdelregist;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { // 메인
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application_for_classes window = new Application_for_classes();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application_for_classes() {
		initialize();
		
	}

	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		Lecture lecture = new Lecture();
		
		LogIn login = new LogIn();
		
		Lecture.createTable();
		LogIn.createTable_Login();
		requestLec.createTable();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("수강신청 시스템"); //프레임 타이틀
		frame.setBounds(100, 100, 992, 635);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임을 닫으면 실행 종료
		frame.getContentPane().setLayout(null);
		
		//로그인 테이블에 들어가야할 자료와 헤더
		String[][] data = LogIn.getLogin(); 
		String[] headers = new String[] {"학번","패스워드"};		
		
		//강의 테이블에 들어가야할 자료와 헤더
		String[][] data_class = Lecture.getLecture();
		String[] headers_class = new String[] {"강의코드","과목명","분반","학점","담당교수"};		
		
		//수강신청 테이블에 들어가야할 자료와 헤더
		String[][] data_myclass = requestLec.getreqLec();
		String[] headers_myclass = new String[] {"학번","강의코드","과목명","분반","학점","담당교수"};
		
		requestPanel = new JPanel();
		requestPanel.setBounds(0, 0, 986, 590);
		requestPanel.setVisible(true);
		JTable reqTable = new JTable(data_class, headers_class);
		requestPanel.setLayout(null);
		JTable myTable = new JTable(data_myclass, headers_myclass);
		
		myTable.setRowHeight(30); //테이블 칸마다 크기조절
		myTable.setFont(new Font("Senerif",Font.BOLD,15)); //폰트 설정
		myTable.setAlignmentX(0);
		myTable.setPreferredScrollableViewportSize(new Dimension(200,100)); //mytable 자체 크기
		
		JScrollPane scrollPane2 = new JScrollPane(myTable);
		scrollPane2.setBounds(92, 210, 802, 128);
		requestPanel.add(scrollPane2);
		
		scrollPane2.setVisible(false);
		
		AdminPanel = new JPanel();
		AdminPanel.setBounds(0, 0, 986, 590);
		AdminPanel.setVisible(true);
		tablepanel = new JPanel();
		tablepanel.setBounds(0, 0, 986, 590);
		tablepanel.setVisible(false);
		tablepanel.setLayout(null);
		JTable table = new JTable(data, headers);
		
		table.setRowHeight(30); //칸마다 크기조절
		table.setFont(new Font("Senerif",Font.BOLD,15));
		table.setAlignmentX(0); //정렬
		table.setPreferredScrollableViewportSize(new Dimension(800,400)); //테이블크기
		
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(92, 5, 802, 428);
		tablepanel.add(scrollPane_1);
		
		frame.getContentPane().add(tablepanel);
		
		// 등록 제거버튼을 누르면 실행된다
		JButton btnDelRegist = new JButton("등록 제거");
		btnDelRegist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deltxt = txtdelregist.getText(); //txtdelregist에 입력된 값을 deltxt에 넣고
				LogIn.Deletehakbun(deltxt); //Deletehakbun 함수를 호출해 그안에 deltxt를 집어넣는다.
				txtdelregist.setText("");  //실행 후 txtdelregits가 비어있으면 좋겠다 싶어서 만든것
				JOptionPane.showMessageDialog(null, "계정 삭제 완료"); //삭제 성공 메시지를 띄운다
				}
		});
		btnDelRegist.setFont(new Font("굴림", Font.BOLD, 15));
		btnDelRegist.setBounds(528, 454, 169, 51);
		tablepanel.add(btnDelRegist);
		
		txtdelregist = new JTextField();
		txtdelregist.setBounds(301, 454, 215, 51);
		tablepanel.add(txtdelregist);
		txtdelregist.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("제거하고싶은 학번을 입력해 주세요 : ");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_3.setBounds(26, 454, 305, 51);
		tablepanel.add(lblNewLabel_3);
		
		JButton btnDelRegist_ref = new JButton("새로고침"); //새로고침 버튼을 누르면 실행되는 곳
		btnDelRegist_ref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tmd에 새롭게 테이블모델 자료를 넣는다
				TableModel tmd = new DefaultTableModel(LogIn.getLogin(), headers);
				//tmd를 table에 넣어주고
				table.setModel(tmd);
				//다시 창을 띄운다
				table.repaint();
			}
		});
		btnDelRegist_ref.setFont(new Font("굴림", Font.BOLD, 15));
		btnDelRegist_ref.setBounds(709, 454, 169, 51);
		tablepanel.add(btnDelRegist_ref);
		
		
		
		
		
		tablepanel.setVisible(false);
		
		
		
		AdminPanel.setLayout(null);
		
		JTable admintable = new JTable(data_class, headers_class);
		admintable.setRowHeight(30);
		admintable.setFont(new Font("Senerif",Font.BOLD,15));
		admintable.setAlignmentX(0);
		admintable.setPreferredScrollableViewportSize(new Dimension(800,400));
		JScrollPane admin_scrollPane = new JScrollPane(admintable);
		admin_scrollPane.setBounds(92, 5, 802, 342);
		AdminPanel.add(admin_scrollPane);
		
		frame.getContentPane().add(AdminPanel);
		
		
		
		
		JButton btnAddClass = new JButton("강의개설");
		btnAddClass.addActionListener(new ActionListener() { // 강의개설 버튼을 눌렀을 때 필드에 적어놓은 데이터들이 삽입된다
			public void actionPerformed(ActionEvent e) {
						
				String lecnum = txtlecNum.getText();  //각 텍스트필드에 쓴내용을 각각의 변수에 넣는다
				String lecname = txtlecName.getText();
				String lecdiv = txtlecDiv.getText();
				String leccredit = txtlecCredit.getText();
				String lecpro = txtlecPro.getText();
				try {
					if(Lecture.DBCheck(lecnum, lecdiv) == 0) { // 강좌코드와 분반을 기준으로 중복체크를해 같은게 없다면 강의 추가, 같은게 있다면 추가 거부						
						Lecture.insertLecture(lecnum, lecname, lecdiv, leccredit, lecpro);
						JOptionPane.showMessageDialog(null, "강의 추가 완료");
					}else {
						JOptionPane.showMessageDialog(null, "강의코드와 분반이 같은 강좌가 이미 존재 합니다.");
					}
							
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
						
			}
					
		});
		btnAddClass.setFont(new Font("굴림", Font.BOLD, 15));
		btnAddClass.setBounds(92, 444, 259, 52);
		AdminPanel.add(btnAddClass);
		
		txtlecNum = new JTextField();
		txtlecNum.setFont(new Font("굴림", Font.PLAIN, 15));
		txtlecNum.setBounds(90, 394, 157, 30);
		AdminPanel.add(txtlecNum);
		txtlecNum.setColumns(10);
		
		txtlecName = new JTextField();
		txtlecName.setFont(new Font("굴림", Font.PLAIN, 15));
		txtlecName.setColumns(10);
		txtlecName.setBounds(254, 394, 157, 30);
		AdminPanel.add(txtlecName);
		
		txtlecDiv = new JTextField();
		txtlecDiv.setFont(new Font("굴림", Font.PLAIN, 15));
		txtlecDiv.setColumns(10);
		txtlecDiv.setBounds(423, 394, 157, 30);
		AdminPanel.add(txtlecDiv);
		
		txtlecCredit = new JTextField();
		txtlecCredit.setFont(new Font("굴림", Font.PLAIN, 15));
		txtlecCredit.setColumns(10);
		txtlecCredit.setBounds(592, 394, 157, 30);
		AdminPanel.add(txtlecCredit);
		
		txtlecPro = new JTextField();
		txtlecPro.setFont(new Font("굴림", Font.PLAIN, 15));
		txtlecPro.setColumns(10);
		txtlecPro.setBounds(757, 394, 157, 30);
		AdminPanel.add(txtlecPro);
		
		JLabel lblLecNum = new JLabel("강의코드");
		lblLecNum.setFont(new Font("굴림", Font.BOLD, 15));
		lblLecNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblLecNum.setBounds(92, 357, 157, 27);
		AdminPanel.add(lblLecNum);
		
		JLabel lblLecName = new JLabel("과목명");
		lblLecName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLecName.setFont(new Font("굴림", Font.BOLD, 15));
		lblLecName.setBounds(254, 357, 157, 27);
		AdminPanel.add(lblLecName);
		
		JLabel lblLecDiv = new JLabel("분반");
		lblLecDiv.setHorizontalAlignment(SwingConstants.CENTER);
		lblLecDiv.setFont(new Font("굴림", Font.BOLD, 15));
		lblLecDiv.setBounds(423, 357, 157, 27);
		AdminPanel.add(lblLecDiv);
		
		JLabel lblLecCredit = new JLabel("학점");
		lblLecCredit.setHorizontalAlignment(SwingConstants.CENTER);
		lblLecCredit.setFont(new Font("굴림", Font.BOLD, 15));
		lblLecCredit.setBounds(592, 357, 157, 27);
		AdminPanel.add(lblLecCredit);
		
		JLabel lblLecPro = new JLabel("담당교수");
		lblLecPro.setHorizontalAlignment(SwingConstants.CENTER);
		lblLecPro.setFont(new Font("굴림", Font.BOLD, 15));
		lblLecPro.setBounds(757, 357, 157, 27);
		AdminPanel.add(lblLecPro);
		
		JLabel lblinput = new JLabel("자료 입력:");
		lblinput.setHorizontalAlignment(SwingConstants.CENTER);
		lblinput.setFont(new Font("굴림", Font.BOLD, 15));
		lblinput.setBounds(0, 394, 92, 30);
		AdminPanel.add(lblinput);
		
		JButton btnrefresh = new JButton("새로고침");
		btnrefresh.addActionListener(new ActionListener() { //새로고침 버튼을 누를시 작동
			public void actionPerformed(ActionEvent e) {
				//강의테이블의 정보를 tmd에 저장
				TableModel tmd = new DefaultTableModel(Lecture.getLecture(), headers_class);
				//어드민테이블에 tmd정보를 셋팅
				admintable.setModel(tmd);
				//어드민테이블 새로고침
				admintable.repaint();
						
			}
		});
		btnrefresh.setFont(new Font("굴림", Font.BOLD, 15));
		btnrefresh.setBounds(371, 444, 259, 52);
		AdminPanel.add(btnrefresh);
		
		JButton btndelete = new JButton("삭제");
		btndelete.addActionListener(new ActionListener() {//삭제 버튼을 누를시 작동
			public void actionPerformed(ActionEvent e) {	
				String lecNum = txtlecNum.getText(); //텍스트필드에 입력받은 값을 넘겨준다
				Lecture.DeleteLecture(lecNum); //넘겨받은 텍스트필드를 이용해 DeleteLecture를 실행한다.
			}
		});
			
		btndelete.setFont(new Font("굴림", Font.BOLD, 15));
		btndelete.setBounds(655, 509, 259, 52);
		AdminPanel.add(btndelete);
		
		JButton btnrefresh_1 = new JButton("가입된 계정 조회");
		btnrefresh_1.addActionListener(new ActionListener() { // 계정 조회 버튼을 클릭 시 작동
			public void actionPerformed(ActionEvent e) {
				LoginPanel.setVisible(false);						
				AdminPanel.setVisible(false);
				tablepanel.setVisible(true);	//가입된 정보들이 들어가있는 테이블을 보여주는 역할
			}
		});
		btnrefresh_1.setFont(new Font("굴림", Font.BOLD, 15));
		btnrefresh_1.setBounds(654, 444, 259, 52);
		AdminPanel.add(btnrefresh_1);
		
		JLabel lblNewLabel_4 = new JLabel("제거하고싶은 강의코드를 작성 후 버튼 클릭 =>");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(292, 512, 363, 46);
		AdminPanel.add(lblNewLabel_4);
		AdminPanel.setVisible(false);
		
		reqTable.setRowHeight(30);
		reqTable.setFont(new Font("Senerif",Font.BOLD,15));
		reqTable.setAlignmentX(0);
		reqTable.setPreferredScrollableViewportSize(new Dimension(800,400));
		
		JScrollPane scrollPane = new JScrollPane(reqTable);
		scrollPane.setBounds(92, 26, 802, 174);
		requestPanel.add(scrollPane);
		
		frame.getContentPane().add(requestPanel);
		
		JButton show = new JButton("신청한 강의 보기");  
		show.setFont(new Font("굴림", Font.BOLD, 15));
		show.addActionListener(new ActionListener() { // 버튼을 누르면 로그인할 때 입력한 학번이 수강한 강좌들만 나오게 함
			public void actionPerformed(ActionEvent e) {
				String ID = loginTxt.getText(); //텍스트필드의 값을 가져온다
				//getreqLec_u를 이용해 같은 학번인 자료들만 select해서 tmd에 집어넣는다
				TableModel tmd = new DefaultTableModel(requestLec.getreqLec_u(ID), headers_myclass);
				myTable.setModel(tmd); //tmd를 이용해 myTable에 셋팅한다
				myTable.repaint(); //myTable을 새로고침한다.
				
				scrollPane2.setVisible(true);

			}
		});
		

		show.setBounds(726, 414, 168, 45);
		requestPanel.add(show);
		
		JButton btnreq = new JButton("수강신청");
		btnreq.addActionListener(new ActionListener() { //수강신청 버튼 클릭 시 작동
			public void actionPerformed(ActionEvent e) {
				//텍스트필드에서 학번, 강의코드, 분반을 가져온다
				String ID = loginTxt.getText(); 
				String lecnum = lecNum1.getText();
				String lecdiv = lecDiv1.getText();
				
				try {
					// 강의코드와 분반을 이용해 이미 신청한 자료면 실패, 없는자료면 값을 집어넣는다
					requestLec.insertRequestLec2(ID, lecnum, lecdiv);
					JOptionPane.showMessageDialog(null, "수강신청 성공");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "수강신청 실패");
					
					e1.printStackTrace();
				}
				
				
			}
		});
		btnreq.setFont(new Font("굴림", Font.BOLD, 15));
		btnreq.setBounds(92, 414, 144, 45);
		requestPanel.add(btnreq);
		
		JButton btncancle = new JButton("수강취소");
		btncancle.addActionListener(new ActionListener() { //수강취소 버튼을 눌렀을 시 작동
			public void actionPerformed(ActionEvent e) {
				//텍스트필드에서 값을 가져온다
				String lecNum = lecNum1.getText();
				String lecDiv = lecDiv1.getText();
				//DeleteReqLec 함수를 이용해 텍스트필드에서 가져온 값을 가지고 비교해 제거한다
				requestLec.DeleteReqLec(lecNum, lecDiv);
	
			}
		});
		btncancle.setFont(new Font("굴림", Font.BOLD, 15));
		btncancle.setBounds(303, 414, 144, 45);
		requestPanel.add(btncancle);
		
		JButton btnrefresh1 = new JButton("새로고침");
		btnrefresh1.addActionListener(new ActionListener() { //새로고침 버튼을 누를 시 작동
			public void actionPerformed(ActionEvent e) {
				//텍스트필드에서 작성한 내용을 가져온다
				String ID = loginTxt.getText();
				//getreqLec_u를 이용해 같은 학번인 자료들만 select해서 tmd에 집어넣는다
				TableModel tmd = new DefaultTableModel(requestLec.getreqLec_u(ID), headers_myclass);
				myTable.setModel(tmd); //tmd를 이용해 myTable에 집어넣는다
				myTable.repaint(); //myTable을 새로고침 한다.
			}
		});
		btnrefresh1.setFont(new Font("굴림", Font.BOLD, 15));
		btnrefresh1.setBounds(527, 414, 144, 45);
		requestPanel.add(btnrefresh1);
		
		lecNum1 = new JTextField();
		lecNum1.setBounds(92, 368, 144, 36);
		requestPanel.add(lecNum1);
		lecNum1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("강의 코드");
		lblNewLabel_2.setBounds(102, 343, 74, 15);
		requestPanel.add(lblNewLabel_2);
		
		lecDiv1 = new JTextField();
		lecDiv1.setColumns(10);
		lecDiv1.setBounds(352, 368, 144, 36);
		requestPanel.add(lecDiv1);
		
		JLabel lblNewLabel_2_1 = new JLabel("분반");
		lblNewLabel_2_1.setBounds(352, 343, 50, 15);
		requestPanel.add(lblNewLabel_2_1);
		

		requestPanel.setVisible(false);
		JPanel signUpPanel = new JPanel();
		
		LoginPanel = new JPanel(); //로그인패널
		LoginPanel.setBackground(new Color(255, 255, 255));
		LoginPanel.setBounds(0, 0, 986, 590);
		frame.getContentPane().add(LoginPanel);
		LoginPanel.setLayout(null);
						
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// 목원대학교 로고가 저장된 위치
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Owner\\eclipse-workspace\\JAVA_PROJECT\\ImageFolder\\목원대로고.jpg"));
		lblNewLabel.setBounds(299, 45, 346, 118);
		LoginPanel.add(lblNewLabel);
			
		loginTxt = new JTextField();
		loginTxt.setFont(new Font("굴림", Font.PLAIN, 20));
		loginTxt.setBounds(334, 201, 276, 49);
		LoginPanel.add(loginTxt);
		loginTxt.setColumns(10);
			
		pwTxt = new JPasswordField();
		pwTxt.setFont(new Font("굴림", Font.PLAIN, 20));
		pwTxt.setBounds(334, 280, 276, 49);
		LoginPanel.add(pwTxt);
			
		JLabel IDlabel = new JLabel("학번 :");
		IDlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		IDlabel.setFont(new Font("바탕체", Font.BOLD, 20));
		IDlabel.setBounds(213, 207, 94, 36);
		LoginPanel.add(IDlabel);
			
		JLabel pwLabel = new JLabel("패스워드 :");
		pwLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pwLabel.setFont(new Font("바탕체", Font.BOLD, 20));
		pwLabel.setBounds(193, 286, 114, 36);
		LoginPanel.add(pwLabel);
			
		JButton btnIdRegist = new JButton("가입");
		btnIdRegist.addActionListener(new ActionListener() { //가입 버튼을 누를 시 작동
			public void actionPerformed(ActionEvent e) {
				signUpPanel.setVisible(true);	// 회원가입 패널을 가져온다
				
			}
		});
		
		btnIdRegist.setFont(new Font("굴림", Font.BOLD, 20));
		btnIdRegist.setBounds(334, 349, 276, 49);
		LoginPanel.add(btnIdRegist);
			
		JButton btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("굴림", Font.BOLD, 20));
		btnLogin.setBounds(334, 420, 276, 49);
		LoginPanel.add(btnLogin);
			
			
		signUpPanel.setBackground(new Color(255, 255, 255));
		signUpPanel.setForeground(new Color(0, 0, 0));
		signUpPanel.setBounds(657, 31, 262, 438);
		LoginPanel.add(signUpPanel);
		signUpPanel.setLayout(null);
			
		JLabel hakbunLbl = new JLabel("학번 :");
		hakbunLbl.setFont(new Font("굴림", Font.BOLD, 15));
		hakbunLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		hakbunLbl.setBounds(0, 115, 99, 33);
		signUpPanel.add(hakbunLbl);
			
		JLabel pwLbl = new JLabel("패스워드 :");
		pwLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		pwLbl.setFont(new Font("굴림", Font.BOLD, 15));
		pwLbl.setBounds(0, 158, 99, 33);
		signUpPanel.add(pwLbl);
			
		registHakbunTxt = new JTextField();
			
		registHakbunTxt.setBounds(111, 121, 109, 21);
		signUpPanel.add(registHakbunTxt);
		registHakbunTxt.setColumns(10);
		
		registPwTxt = new JTextField();
		registPwTxt.setBounds(111, 164, 109, 21);
		signUpPanel.add(registPwTxt);
		registPwTxt.setColumns(10);
			
		JLabel lblNewLabel_1 = new JLabel("회원가입");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 58, 217, 47);
		signUpPanel.add(lblNewLabel_1);
			
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() { // 확인버튼을 누를 시 작동
			public void actionPerformed(ActionEvent e) {
				//텍스트필드에 적은 학번을 가져온다
				int hakbun = Integer.parseInt(registHakbunTxt.getText()); 
				//텍스트필드에 적은 비밀번호를 가져온다
				String password = registPwTxt.getText();
				try {
					// 중복체크를 해 중복되는 학번이 없다면 가입 성공 메시지를 띄우고 가입을 시킨다,
					// 중복되는 학번이 있다면 이미 존재하는 학번이라고 띄움
					if(LogIn.DBCheck(registHakbunTxt.getText()) == 0) {
						LogIn.insertLogin(hakbun, password);
						JOptionPane.showMessageDialog(null, "가입 성공");
					}else {
						JOptionPane.showMessageDialog(null, "이미 존재하는 학번입니다.");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.setBounds(12, 201, 238, 47);
		signUpPanel.add(btnNewButton);
		signUpPanel.setVisible(false);
			
		btnLogin.addActionListener(new ActionListener() { //로그인 버튼을 누르면 작동	
			public void actionPerformed(ActionEvent e) {
				//텍스트필드에 적은 학번과 비밀번호를 가져온다
				String ID = loginTxt.getText();
				String PW = new String(pwTxt.getPassword());
				try {
					 // 학번과 패스워드가 999면 관리자 계정
					if(ID.equals("999") && PW.equals("999")){
						AdminPanel.setVisible(true);
						LoginPanel.setVisible(false);
						requestPanel.setVisible(false);
						tablepanel.setVisible(false);
						JOptionPane.showMessageDialog(null, "관리자 계정");	
					}else if(LogIn.userCheck(ID, PW) == 1) {	
						//학번과 비밀번호가 일치하면 로그인 성공
						LoginPanel.setVisible(false);
						AdminPanel.setVisible(false);
						requestPanel.setVisible(true);
						tablepanel.setVisible(false);
						JOptionPane.showMessageDialog(null, "로그인 성공");
					}else if(LogIn.userCheck(ID, PW) == 0) {
						//일치하지 않는다면 비밀번호가 정확하지 않습니다 메시지를 띄움
						JOptionPane.showMessageDialog(null, "비밀번호가 정확하지 않습니다.");
					}else if(LogIn.userCheck(ID, PW)==-1) {
						//DB에 일치하는 학번이 존재하지 않는다면 해당 아이디는 존재하지 않습니다 메시지를 띄움
						JOptionPane.showMessageDialog(null, "해당 아이디는 존재하지 않습니다.");
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}	
		});
			LoginPanel.setVisible(true);
		requestPanel.setVisible(false);
		frame.setJMenuBar(menuBar());
		menuBar();
	}	


	public JMenuBar menuBar() {	// 메뉴바 버튼
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("설정");
		
		
		bar.add(fileMenu);
	
		JMenuItem gotoLogin = new JMenuItem("로그인화면으로");
		
		
		JMenuItem exit = new JMenuItem("종료");
		
		fileMenu.add(gotoLogin);
		fileMenu.addSeparator(); //사이에 줄 생김
		fileMenu.add(exit);
		
		gotoLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 기능구현
				LoginPanel.setVisible(true);
				AdminPanel.setVisible(false);
				requestPanel.setVisible(false);
				tablepanel.setVisible(false);
			}
		});
		
		exit.addActionListener(new ActionListener() { // 프로그램 종료
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		return bar;
	}
}
	


class ImagePanel extends JPanel{
	private Image img;
	
	public ImagePanel(Image img) {
		this.img = img;
		//setSize(new Dimension(img.getWidth(null),img.getHeight(null))); //setSize는 그 바로위에 있는 객체의 레이아웃에 따라 변할수도 안변할수도 있음
		setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null))); //setPre 는 최소사이즈와 최대 사이즈를 설정해서 레이아웃과 관계없이 사이즈를 설정함
		setLayout(null);
	}
	
	public int getWidth() { //이미지 가로넓이 구하기
		return img.getWidth(null);
	}
	
	public int getHeight() { //이미지 높이 구하기
		return img.getHeight(null);
	}
	
	public void paintComponent(Graphics g) { //페인트컴포넌츠 : 패널을 열었을 때 자동으로 이미지를 비춰준다
		g.drawImage(img, 0, 0, null);
		
	}
	
}






