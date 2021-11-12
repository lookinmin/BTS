
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// 기본적으로 대문자로 버튼및 레이블 주석을 달았고
// MAINTOTREND는 메인에서 트렌드보는창으로 넘어간다는 뜻
public class main {

    private JFrame frame;
    private JTextField LOGIN_ID_INSERT;
    private JTextField LOGIN_PW_INSERT;
    private JTextField txtwriting;
    private ImageIcon icon;
    Date today = new Date();
    private JTextField JOINPAGE_NAME_INSERT;
    private JTextField JOINPAGE_ID_INSERT;
    private JTextField JOINPAGE_NICKNAME_INSERT;
    private JTextField JOINPAGE_PW_INSERT;
    private JTextField JOINPAGE_PWCHECK_INSERT;
    private JLabel JOINPAGE_PWCHECK;
    private ButtonGroup gender;
    private ButtonGroup age;
    private Object go;
    private static ResultSet rs;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    main window = new main();
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
    public main() {
        initialize();
    }

    public static int DBHandle(String ID, String PW) {
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "example"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "minsu0418"; // MySQL 서버 비밀번호
        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement("select * from bts where ID = ? and PW = ?"); //db에서 id와 pw 테이블에 값이 존재하는지 확인
            pstmt.setString(1, ID); //첫번째 ?에 넣음
            pstmt.setString(2, PW); //두번째 ?에 넣음

            rs = pstmt.executeQuery();

            if(rs.next()) { //rs의 next에 값이 있으면 일치한다는 뜻
                System.out.println("로그인 성공");
                return 1;   //로그인 성공
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return -1; //로그인 실패
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Being Trend Setter");
        frame.setBounds(100, 100, 800, 520);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        //배경
        icon = new ImageIcon("C:\\Users\\thrry\\Desktop\\LoginPage.jpg");//배경이미지

        JPanel CODYLINE = new JPanel();  // 코디 한줄평 업로드 ui 설정
        CODYLINE.setBounds(0, 0, 800, 500);
        frame.getContentPane().add(CODYLINE);
        CODYLINE.setLayout(null);

        JButton btnWriting = new JButton("\uAE00 \uC791\uC131\uD558\uAE30");
        btnWriting.setBounds(377, 408, 160, 36);
        CODYLINE.add(btnWriting);

        JLabel lblcodyline = new JLabel("\uCF54\uB514 \uD55C\uC904 \uD3C9");
        lblcodyline.setFont(new Font("굴림", Font.PLAIN, 18));
        lblcodyline.setBounds(42, 349, 163, 49);
        CODYLINE.add(lblcodyline);

        txtwriting = new JTextField();
        txtwriting.setBounds(239, 349, 480, 49);
        CODYLINE.add(txtwriting);
        txtwriting.setColumns(10);

        JButton btnCODYTOMAIN = new JButton("\uB3CC\uC544\uAC00\uAE30");
        CODYLINE.setVisible(false);

        btnCODYTOMAIN.setBounds(560, 408, 160, 36);
        CODYLINE.add(btnCODYTOMAIN);

        JPanel MAINMENU = new JPanel() { // 메인메뉴 ui
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);

                setOpaque(false);
                super.paintComponent(g);
            }
        };
        MAINMENU.setBounds(0, 0, 800, 500);
        frame.getContentPane().add(MAINMENU);
        MAINMENU.setLayout(null);

        JPanel MAINSUB = new JPanel(); //메인메뉴에서 옆에 자투리(트렌드보기, 글작성하기) 메뉴 나오게 끔 패널 설정
        MAINSUB.setBounds(600, 0, 200, 500);
        MAINMENU.add(MAINSUB);
        MAINSUB.setLayout(null);

        JButton btnMAINTOCODY = new JButton("\uAE00 \uC791\uC131\uD558\uAE30");

        btnMAINTOCODY.setBounds(22, 46, 148, 44);
        MAINSUB.add(btnMAINTOCODY);

        JButton btnMAINTOTREND = new JButton("\uD2B8\uB79C\uB4DC \uBCF4\uAE30");

        btnMAINTOTREND.setBounds(22, 363, 148, 44);
        MAINSUB.add(btnMAINTOTREND);
        MAINMENU.setVisible(false);

        JPanel LOGINPAGE = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };

        LOGINPAGE.setBounds(0, 0, 800, 500); // 로그인 페이지 ui
        frame.getContentPane().add(LOGINPAGE);
        LOGINPAGE.setLayout(null);

        JButton btnLOGIN = new JButton("LOGIN");

        btnLOGIN.setBounds(648, 400, 128, 38);
        LOGINPAGE.add(btnLOGIN);

        JLabel LOGIN_ID = new JLabel("ID");
        LOGIN_ID.setBounds(501, 320, 70, 23);
        LOGINPAGE.add(LOGIN_ID);

        JLabel LOGIN_PW = new JLabel("PASSWORD");
        LOGIN_PW.setBounds(501, 354, 70, 23);
        LOGINPAGE.add(LOGIN_PW);

        LOGIN_ID_INSERT = new JTextField();
        LOGIN_ID_INSERT.setBounds(583, 321, 164, 23);
        LOGINPAGE.add(LOGIN_ID_INSERT);
        LOGIN_ID_INSERT.setColumns(10);

        LOGIN_PW_INSERT = new JTextField();
        LOGIN_PW_INSERT.setBounds(583, 354, 164, 23);
        LOGINPAGE.add(LOGIN_PW_INSERT);
        LOGIN_PW_INSERT.setColumns(10);

        JButton btnLOGINtoMEMBERSHIP = new JButton("\uD68C\uC6D0\uAC00\uC785");
        btnLOGINtoMEMBERSHIP.setBounds(501, 400, 128, 38);
        LOGINPAGE.add(btnLOGINtoMEMBERSHIP);
        LOGINPAGE.setVisible(true);


        JPanel TREND = new JPanel();    // 트랜드보기 ui 설정
        TREND.setBounds(0, 0, 800, 500);
        frame.getContentPane().add(TREND);
        TREND.setLayout(null);
        JButton btnTRENDTOMAIN = new JButton("\uBA54\uC778\uBA54\uB274\uB85C \uB3CC\uC544\uAC00\uAE30");
        btnTRENDTOMAIN.setBounds(598, 417, 175, 23);
        TREND.add(btnTRENDTOMAIN);
        TREND.setVisible(false);

        // 각각버튼을 눌렀을떄 어느 화면으로 가는지에 대한 버튼 설정
        // MAINTOTREND는 메인에서 트렌드보는창으로 넘어간다는 뜻

        btnLOGIN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CODYLINE.setVisible(false);
                LOGINPAGE.setVisible(false);
                MAINMENU.setVisible(true);
                TREND.setVisible(false);
            }
        });
        btnLOGINtoMEMBERSHIP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == btnLOGINtoMEMBERSHIP) {
                    Membership r = new Membership();
                    r.setVisible(true);

                }

            }
        });


        btnCODYTOMAIN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LOGINPAGE.setVisible(false);
                CODYLINE.setVisible(false);
                MAINMENU.setVisible(true);
                TREND.setVisible(false);
            }
        });
        btnTRENDTOMAIN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LOGINPAGE.setVisible(false);
                CODYLINE.setVisible(false);
                MAINMENU.setVisible(true);
                TREND.setVisible(false);
            }
        });
        btnMAINTOCODY.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LOGINPAGE.setVisible(false);
                CODYLINE.setVisible(true);
                MAINMENU.setVisible(false);
                TREND.setVisible(false);
            }
        });
        btnMAINTOTREND.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LOGINPAGE.setVisible(false);
                CODYLINE.setVisible(false);
                MAINMENU.setVisible(false);
                TREND.setVisible(true);
            }
        });


    }
}