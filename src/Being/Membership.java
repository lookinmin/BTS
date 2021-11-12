package Being;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class Membership {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private ButtonGroup gender;

    public static String InputID;
    public static String InputPW;
    public static String InputNick;
    public static String InputSex;
    public static String InputAge;
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
        frame.setBounds(100, 100, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        JLabel lblNewLabel = new JLabel("\uC774\uB984");
        lblNewLabel.setBounds(33, 80, 88, 22);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514");
        lblNewLabel_1.setBounds(33, 130, 88, 22);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("\uB2C9\uB124\uC784");
        lblNewLabel_2.setBounds(33, 180, 88, 22);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("\uBE44\uBC00\uBC88\uD638");
        lblNewLabel_3.setBounds(33, 230, 88, 22);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
        lblNewLabel_4.setBounds(33, 280, 88, 22);
        frame.getContentPane().add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("\uC131\uBCC4");
        lblNewLabel_5.setBounds(33, 330, 88, 22);
        frame.getContentPane().add(lblNewLabel_5);

        JLabel lblNewLabel_5_1 = new JLabel("\uB098\uC774");
        lblNewLabel_5_1.setBounds(33, 380, 88, 22);
        frame.getContentPane().add(lblNewLabel_5_1);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(128, 77, 220, 29);
        frame.getContentPane().add(textField);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(128, 127, 220, 29);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(128, 177, 220, 29);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(128, 227, 220, 29);
        frame.getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(128, 277, 220, 29);
        frame.getContentPane().add(textField_4);


        JRadioButton MAN = new JRadioButton("\uB0A8");
        MAN.setBounds(129, 329, 70, 25);
        frame.getContentPane().add(MAN);

        JRadioButton WOMAN = new JRadioButton("\uC5EC");
        WOMAN.setBounds(203, 329, 70, 25);
        frame.getContentPane().add(WOMAN);

        gender = new ButtonGroup();
        gender.add(MAN);
        gender.add(WOMAN);


        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(128, 373, 70, 29);
        frame.getContentPane().add(textField_5);

        JLabel lblNewLabel_5_1_1 = new JLabel("\uC138");
        lblNewLabel_5_1_1.setBounds(203, 380, 88, 22);
        frame.getContentPane().add(lblNewLabel_5_1_1);

        JButton Finish = new JButton("\uC644\uB8CC");
        Finish.setBounds(24, 517, 97, 23);
        frame.getContentPane().add(Finish);

        JButton eXIT = new JButton("\uB3CC\uC544\uAC00\uAE30");
        eXIT.setBounds(251, 517, 97, 23);
        frame.getContentPane().add(eXIT);

        eXIT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        Finish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InputID = textField.getText();
                InputPW = textField_1.getText();
                InputNick = textField_2.getText();
                InputSex = rdbtnNewRadioButton.getText();
                InputAge = textField_3.getText();
                DB_handle();
                setVisible(false);
                frame.dispose();
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


    public void setVisible(boolean b){
        frame.setVisible(b);
    }
}
