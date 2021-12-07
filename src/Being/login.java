package Being;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.Font;

public class login extends JFrame{

    private JFrame frame;
    private JTextField LOGIN_ID_INSERT;
    private JPasswordField LOGIN_PW_INSERT;
    private Font f1;
    private ImageIcon icon;
    ImageIcon img = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\cancel.png");
    ImageIcon img2 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\Minimize2.png");
    ImageIcon img3 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\Logo2.png");

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
        frame.setUndecorated(true);
        frame.setBounds(100, 100, 1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        f1 = new Font("이사만루체 Medium",Font.PLAIN,14);


        JPanel LOGINPAGE = new JPanel();

        LOGINPAGE.setBackground(new Color(247,241,255));

        LOGINPAGE.setBounds(0, 0, 1000, 600); // 로그인 페이지 ui
        frame.getContentPane().add(LOGINPAGE);
        LOGINPAGE.setLayout(null);

        RoundedButton btnLOGIN = new RoundedButton("LOGIN");
        btnLOGIN.setForeground(Color.WHITE);
        btnLOGIN.setBackground(new Color(99,0,238));
        btnLOGIN.setFont(f1);

        btnLOGIN.setBounds(737, 452, 170, 30);
        LOGINPAGE.add(btnLOGIN);

        JLabel LOGIN_ID = new JLabel("ID");
        LOGIN_ID.setForeground(Color.BLACK);
        LOGIN_ID.setFont(f1);
        LOGIN_ID.setBounds(735, 327, 70, 23);
        LOGINPAGE.add(LOGIN_ID);

        JLabel LOGIN_PW = new JLabel("PASSWORD");
        LOGIN_PW.setForeground(Color.BLACK);
        LOGIN_PW.setFont(f1);
        LOGIN_PW.setBounds(735, 382, 100, 23);
        LOGINPAGE.add(LOGIN_PW);

        LOGIN_ID_INSERT = new JTextField();
        LOGIN_ID_INSERT.setBounds(737, 352, 170, 25);
        LOGIN_ID_INSERT.setFont(f1);
        LOGIN_ID_INSERT.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        LOGIN_ID_INSERT.setOpaque(false);
        LOGINPAGE.add(LOGIN_ID_INSERT);
        LOGIN_ID_INSERT.setColumns(10);

        LOGIN_PW_INSERT = new JPasswordField();
        LOGIN_PW_INSERT.setBounds(737, 407, 170, 25);
        LOGIN_PW_INSERT.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        LOGIN_PW_INSERT.setOpaque(false);
        LOGINPAGE.add(LOGIN_PW_INSERT);
        LOGIN_PW_INSERT.setColumns(10);

        JButton btnLOGINtoMEMBERSHIP = new JButton("Sign Up");
        btnLOGINtoMEMBERSHIP.setFont(f1);
        btnLOGINtoMEMBERSHIP.setForeground(Color.BLACK);
        btnLOGINtoMEMBERSHIP.setBorderPainted(false);
        btnLOGINtoMEMBERSHIP.setContentAreaFilled(false);
        btnLOGINtoMEMBERSHIP.setBounds(820, 492, 100, 25);
        LOGINPAGE.add(btnLOGINtoMEMBERSHIP);

        JButton Minimize = new JButton(img2);
        Minimize.setBounds(912, 10, 32, 32);
        Minimize.setBorderPainted(false);
        Minimize.setFocusPainted(false);
        Minimize.setContentAreaFilled(false);
        LOGINPAGE.add(Minimize);

        JButton exit = new JButton(img);
        exit.setBounds(956, 10, 32, 32);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);
        LOGINPAGE.add(exit);

        JLabel BTS = new JLabel(img3);
        BTS.setBounds(72, 10, 512, 512);
        LOGINPAGE.add(BTS);

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        Minimize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setState(Frame.ICONIFIED);
            }
        });

        btnLOGIN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(DBHandle(LOGIN_ID_INSERT.getText(), LOGIN_PW_INSERT.getText())==1){
                    SaveIDtoFile(whatsyourname(LOGIN_ID_INSERT.getText()));
                    menu r = new menu();
                    r.setVisible(true);
                    frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "로그인에 실패했습니다");
                }
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

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM example.bts");

            while(resultSet.next()) {
                String dbID = resultSet.getString("ID");
                String dbPW = resultSet.getString("PW");
                if (ID.equals(dbID) && PW.equals((dbPW))) {
                    System.out.println("로그인 성공");
                    System.out.println(dbID + ", " + dbPW);
                    return 1;
                }
            }
            resultSet.close();
            statement.close();
            con.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return -1; //로그인 실패
    }

    private String whatsyourname(String ID){
        String nickname = null;
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

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM example.bts");

            while(resultSet.next()) {
                String dbID = resultSet.getString("ID");
                String dbNick = resultSet.getString("nickname");
                if (ID.equals(dbID)) {
                    nickname = dbNick;
                }
            }
            resultSet.close();
            statement.close();


            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nickname;
    }


    private void SaveIDtoFile(String text) {
        Scanner scanner = new Scanner(System.in);
        FileWriter fout = null;
        String pilePath = "C:\\Users\\ancx1\\BTS\\src\\nowID.txt";//BTS-main안 텍스트파일 생성
        try {
            fout = new FileWriter(pilePath);
            fout.write(text, 0, text.length());
            fout.write("\r\n", 0, 2);
            fout.close();
        } catch (IOException e) {
            System.out.println("입출력 오류");
        }
        scanner.close();
    }
}
