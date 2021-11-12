import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.*;
import java.util.Scanner;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JRadioButton;

public class SignUp {

    private JFrame frame;
    private JTextField textField;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JTextField textField_1;
    private JTextField textField_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JRadioButton rdbtnNewRadioButton_1;
    private JLabel lblNewLabel_5;
    private JTextField textField_3;
    private JLabel lblNewLabel_6;
    private JButton btnNewButton;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_8;
    private JLabel lblNewLabel_9;
    public static String InputID;
    public static String InputPW;
    public static String InputNick;
    public static String InputSex;
    public static String InputAge;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUp window = new SignUp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void DB_handle(){
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "example"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "minsu0418"; // MySQL 서버 비밀번호
        int InAge = 0;
        int InSex = 0;
        try{
            InAge = Integer.parseInt(InputAge);      //10대 : 1, 20대 : 2, 30대 : 3, 40대 : 4, 50대 : 5
        } catch (NumberFormatException e){
        } catch (Exception e){
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

            if(InputSex.equals("남"))
                InSex = 1;
            else
                InSex = 2;

            if(10<=InAge && InAge <=19)
                InAge = 1;
            else if(20<=InAge && InAge <=29)
                InAge = 2;
            else if(30<=InAge && InAge <=39)
                InAge = 3;
            else if(40<=InAge && InAge <=49)
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
                if(result==1) {
                    System.out.println("Board데이터 삽입 성공!");
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM example.bts");

            while(resultSet.next()){
                String ID2 = resultSet.getString("ID");
                String pW = resultSet.getString("PW");
                String Nick = resultSet.getString("nickname");
                int sex1 = resultSet.getInt("sex");
                int age1 = resultSet.getInt("age");
                System.out.println(ID2+", "+pW+", "+Nick+", "+sex1+", "+age1);
            }
            resultSet.close();
            statement.close();
            con.close();
        } catch(SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }

        // 3.해제
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {}
    }

    /**
     * Create the application.
     */
    public SignUp() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame =  new JFrame();
        frame.setTitle("Register");
        frame.getContentPane().setForeground(Color.BLACK);
        frame.setBounds(100, 100, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        textField = new JTextField(10);
        textField.setBounds(94, 85, 150, 30);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("* \uC77C\uBC18\uD68C\uC6D0 \uAC00\uC785\uC2E0\uCCAD");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        lblNewLabel.setBounds(12, 10, 187, 30);
        frame.getContentPane().add(lblNewLabel);

        lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setBounds(12, 80, 70, 30);
        frame.getContentPane().add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("PW");
        lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setBounds(12, 125, 70, 30);
        frame.getContentPane().add(lblNewLabel_2);

        textField_1 = new JTextField(6);
        textField_1.setColumns(10);
        textField_1.setBounds(94, 125, 150, 30);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField(10);
        textField_2.setColumns(10);
        textField_2.setBounds(94, 165, 150, 30);
        frame.getContentPane().add(textField_2);

        lblNewLabel_3 = new JLabel("\uB2C9\uB124\uC784");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(12, 165, 70, 30);
        frame.getContentPane().add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("\uC131\uBCC4");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(12, 205, 70, 30);
        frame.getContentPane().add(lblNewLabel_4);

        JRadioButton rdbtnNewRadioButton = new JRadioButton(" \uB0A8");
        rdbtnNewRadioButton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnNewRadioButton.setBounds(94, 206, 80, 30);
        frame.getContentPane().add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_1 = new JRadioButton(" \uC5EC");
        rdbtnNewRadioButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnNewRadioButton_1.setBounds(178, 206, 80, 30);
        frame.getContentPane().add(rdbtnNewRadioButton_1);

        lblNewLabel_5 = new JLabel("\uB098\uC774");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        lblNewLabel_5.setBounds(12, 245, 70, 30);
        frame.getContentPane().add(lblNewLabel_5);

        textField_3 = new JTextField(3);
        textField_3.setColumns(10);
        textField_3.setBounds(94, 250, 49, 30);
        frame.getContentPane().add(textField_3);

        lblNewLabel_6 = new JLabel("\uC138");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_6.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        lblNewLabel_6.setBounds(146, 250, 39, 30);
        frame.getContentPane().add(lblNewLabel_6);

        btnNewButton = new JButton("OK");
        btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        btnNewButton.setBounds(270, 500, 95, 30);
        frame.getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputID = textField.getText();
                InputPW = textField_1.getText();
                InputNick = textField_2.getText();
                InputSex = rdbtnNewRadioButton.getText();
                InputAge = textField_3.getText();
                DB_handle();
            }
        });

        lblNewLabel_7 = new JLabel("(\uCD5C\uB300 10\uC790\uB9AC)");
        lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_7.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        lblNewLabel_7.setBounds(256, 85, 109, 30);
        frame.getContentPane().add(lblNewLabel_7);

        lblNewLabel_8 = new JLabel("(\uCD5C\uB300 6\uC790\uB9AC)");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_8.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        lblNewLabel_8.setBounds(256, 125, 109, 30);
        frame.getContentPane().add(lblNewLabel_8);

        lblNewLabel_9 = new JLabel("(\uCD5C\uB300 10\uC790\uB9AC)");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_9.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        lblNewLabel_9.setBounds(256, 165, 109, 30);
        frame.getContentPane().add(lblNewLabel_9);



    }
};

   