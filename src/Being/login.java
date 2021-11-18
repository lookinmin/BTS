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

public class login extends JFrame{

    private JFrame frame;
    private JTextField LOGIN_ID_INSERT;
    private JTextField LOGIN_PW_INSERT;

    private ImageIcon icon;

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

    public login() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        icon = new ImageIcon("");//배경이미지
        JPanel LOGINPAGE = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };

        LOGINPAGE.setBounds(0, 0, 1100, 700); // 로그인 페이지 ui
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
