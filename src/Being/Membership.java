package Being;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class Membership extends JFrame{

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JRadioButton[] radio;
    private Font f1;

    public static String InputID;
    public static String InputPW;
    public static String InputNick;
    public static String InputSex;
    public static String InputAge;

    ImageIcon img = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\trend.png");
    ImageIcon img2 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\mem_cancel.png");
    ImageIcon img3 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\mem_clear1.png");



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Membership window = new Membership();
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
    public Membership() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(247,241,255));
        frame.setUndecorated(true);
        frame.setBounds(100, 100, 450, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        f1 = new Font("이사만루체 Medium",Font.PLAIN,14);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setFont(f1);
        lblNewLabel.setBounds(63, 200, 88, 30);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("NickName");
        lblNewLabel_1.setFont(f1);
        lblNewLabel_1.setBounds(63, 260, 88, 30);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("PW");
        lblNewLabel_2.setFont(f1);
        lblNewLabel_2.setBounds(63, 320, 88, 30);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("PW Check");
        lblNewLabel_3.setFont(f1);
        lblNewLabel_3.setBounds(63, 380, 88, 30);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Sex");
        lblNewLabel_4.setFont(f1);
        lblNewLabel_4.setBounds(63, 440, 88, 30);
        frame.getContentPane().add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Age");
        lblNewLabel_5.setFont(f1);
        lblNewLabel_5.setBounds(63, 500, 88, 30);
        frame.getContentPane().add(lblNewLabel_5);



        textField = new JTextField();
        textField.setColumns(10);
        textField.setFont(f1);
        textField.setOpaque(false);
        textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        textField.setBounds(158, 200, 220, 29);
        frame.getContentPane().add(textField);

        textField_1 = new JTextField();
        textField_1.setFont(f1);
        textField_1.setColumns(10);
        textField_1.setOpaque(false);
        textField_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        textField_1.setBounds(158, 260, 220, 29);
        frame.getContentPane().add(textField_1);

        textField_2 = new JPasswordField();
        textField_2.setColumns(10);
        textField_2.setOpaque(false);
        textField_2.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        textField_2.setBounds(158, 320, 220, 29);
        frame.getContentPane().add(textField_2);

        textField_3 = new JPasswordField();
        textField_3.setColumns(10);
        textField_3.setOpaque(false);
        textField_3.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        textField_3.setBounds(158, 380, 220, 29);
        frame.getContentPane().add(textField_3);


        radio = new JRadioButton[2];
        String[] radio_name = {"남성", "여성"};
        // ButtonGroup group = new ButtonGroup();
        for(int i=0; i<radio.length; i++){
            radio[i] = new JRadioButton(radio_name[i]);
            //group.add(radio[i]);
            frame.getContentPane().add(radio[i]);
        }
        radio[0].setFont(f1);
        radio[1].setFont(f1);

        radio[0].setBounds(158, 440, 70, 25);
        radio[1].setBounds(232, 440, 70, 25);

        radio[0].setBorderPainted(false);
        radio[0].setFocusPainted(false);
        radio[0].setContentAreaFilled(false);

        radio[1].setBorderPainted(false);
        radio[1].setFocusPainted(false);
        radio[1].setContentAreaFilled(false);

        radio[0].setSelected(false);
        radio[1].setSelected(true);


        textField_5 = new JTextField();
        textField_5.setFont(f1);
        textField_5.setHorizontalAlignment(JTextField.TRAILING);
        textField_5.setColumns(10);
        textField_5.setOpaque(false);
        textField_5.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        textField_5.setBounds(158, 503, 50, 30);
        frame.getContentPane().add(textField_5);

        JLabel lblNewLabel_5_1_1 = new JLabel("years");
        lblNewLabel_5_1_1.setFont(f1);
        lblNewLabel_5_1_1.setBounds(213, 500, 88, 30);
        frame.getContentPane().add(lblNewLabel_5_1_1);

        JButton Finish = new JButton(img3);
        Finish.setBounds(63, 606, 64, 64);
        Finish.setBorderPainted(false);
        Finish.setFocusPainted(false);
        Finish.setContentAreaFilled(false);
        frame.getContentPane().add(Finish);

        JButton eXIT = new JButton(img2);
        eXIT.setBounds(314, 606, 64, 64);
        eXIT.setBorderPainted(false);
        eXIT.setFocusPainted(false);
        eXIT.setContentAreaFilled(false);
        frame.getContentPane().add(eXIT);

        JLabel Logo = new JLabel(img);
        Logo.setBounds(37, 43, 376, 82);
        frame.getContentPane().add(Logo);

        eXIT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        Finish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textField.getText().length() == 0 || textField_1.getText().length() == 0 || textField_2.getText().length() == 0 || textField_5.getText().length() == 0){
                    JOptionPane.showMessageDialog(null, "모든 칸을 입력해주세요.");
                }
                else{
                    if(textField_2.getText().equals(textField_3.getText())){
                        InputID = textField.getText();
                        InputPW = textField_2.getText();
                        InputNick = textField_1.getText();
                        if(radio[0].isSelected()){
                            InputSex = "남";
                        }
                        else if(radio[1].isSelected()){
                            InputSex = "여";
                        }
                        InputAge = textField_5.getText();
                        try {
                            DB_handle();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        setVisible(false);
                        frame.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "기존에 있는 ID 입니다.");
                    }
                }
            }
        });
    }

    public static void DB_handle() throws SQLException {
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "example"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "minsu0418"; // MySQL 서버 비밀번호
        int InAge = 0;
        int InSex = 0;
        try {
            InAge = Integer.parseInt(InputAge);      //10대 : 1, 20대 : 2, 30대 : 3, 40대 : 4, 50대 : 5
        } catch (NumberFormatException e) {
        } catch (Exception e) {
        }
        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("정상적으로 연결되었습니다.");

            if (InputSex.equals("남"))
                InSex = 1;
            else
                InSex = 2;

            if (10 <= InAge && InAge <= 19)
                InAge = 1;
            else if (20 <= InAge && InAge <= 29)
                InAge = 2;
            else if (30 <= InAge && InAge <= 39)
                InAge = 3;
            else if (40 <= InAge && InAge <= 49)
                InAge = 4;
            else
                InAge = 5;

            String sql = "insert into `bts` values(?, ?, ?, ?, ?)";
            PreparedStatement pstmt = null;
            try {
                pstmt = con.prepareStatement(sql);

                pstmt.setString(1, InputID);
                pstmt.setString(2, InputPW);
                pstmt.setString(3, InputNick);
                pstmt.setInt(4, InSex);
                pstmt.setInt(5, InAge);
                // 업데이트
                int result = pstmt.executeUpdate();
                if (result == 1) {
                    System.out.println("Board데이터 삽입 성공!");
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM example.bts");

                while (resultSet.next()) {
                    String ID2 = resultSet.getString("ID");
                    String pW = resultSet.getString("PW");
                    String Nick = resultSet.getString("nickname");
                    int sex1 = resultSet.getInt("sex");
                    int age1 = resultSet.getInt("age");
                    System.out.println(ID2 + ", " + pW + ", " + Nick + ", " + sex1 + ", " + age1);
                }
                resultSet.close();
                statement.close();
                con.close();
            } catch (SQLException e) {
                System.err.println("con 오류:" + e.getMessage());
                e.printStackTrace();
            }

            // 3.해제
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void setVisible(boolean b){
        frame.setVisible(b);
    }
}
