package Being;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class login extends JFrame{

    private static ResultSet rs;
    private JFrame frame;
    private JTextField LOGIN_ID_INSERT;
    private JTextField LOGIN_PW_INSERT;

    private ImageIcon icon;



    Date today = new Date();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login window = new login();
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
    public login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);



        icon = new ImageIcon("C:\\Users\\ancx1\\Desktop\\bts.jpg");//배경이미지
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
        btnLOGIN.setBackground(Color.GRAY);
        btnLOGIN.setForeground(Color.WHITE);
        btnLOGIN.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        btnLOGIN.setBounds(562, 360, 170, 30);
        LOGINPAGE.add(btnLOGIN);

        JLabel LOGIN_ID = new JLabel("ID");
        LOGIN_ID.setForeground(Color.WHITE);
        LOGIN_ID.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        LOGIN_ID.setBounds(560, 235, 70, 23);
        LOGINPAGE.add(LOGIN_ID);

        JLabel LOGIN_PW = new JLabel("PASSWORD");
        LOGIN_PW.setForeground(Color.WHITE);
        LOGIN_PW.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        LOGIN_PW.setBounds(560, 290, 100, 23);
        LOGINPAGE.add(LOGIN_PW);

        LOGIN_ID_INSERT = new JTextField();
        LOGIN_ID_INSERT.setBounds(562, 260, 170, 25);
        LOGINPAGE.add(LOGIN_ID_INSERT);
        LOGIN_ID_INSERT.setColumns(10);

        LOGIN_PW_INSERT = new JTextField();
        LOGIN_PW_INSERT.setBounds(562, 315, 170, 25);
        LOGINPAGE.add(LOGIN_PW_INSERT);
        LOGIN_PW_INSERT.setColumns(10);

        JButton btnLOGINtoMEMBERSHIP = new JButton("Sign Up");
        btnLOGINtoMEMBERSHIP.setForeground(Color.WHITE);
        btnLOGINtoMEMBERSHIP.setBorderPainted(false);
        btnLOGINtoMEMBERSHIP.setContentAreaFilled(false);
        btnLOGINtoMEMBERSHIP.setBounds(657, 400, 80, 25);
        LOGINPAGE.add(btnLOGINtoMEMBERSHIP);


        btnLOGIN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu r = new menu();
                r.setVisible(true);
                frame.dispose();
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
    }


    public void setVisible(boolean b){
        frame.setVisible(b);
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
}