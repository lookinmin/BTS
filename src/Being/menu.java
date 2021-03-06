package Being;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class menu extends JFrame {

    private JFrame frame;
    private ImageIcon image;
    private Font f1;
    ImageIcon img = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\cancel.png");
    ImageIcon img2 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\minimize2.png");
    ImageIcon MainLogo = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\MainLogo.png");
    ImageIcon like = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\like.png");
    ImageIcon cody = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\cody1.png");
    ImageIcon trend = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\trend1.png");
    ImageIcon home = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\home1.png");
    ImageIcon redHeart = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\heart.png");

    ArrayList<PostData> postData = new ArrayList<>();
    ArrayList<JPanel> postPanel = new ArrayList<>();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    menu window = new menu();
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
    public menu() {
        ReadMySQL();
        initialize();
    }

    private void ReadMySQL() {
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

        String decodeImg = null;
        // 2.연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("정상적으로 연결되었습니다.");

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM example.post");


            while(resultSet.next()){
                String date = resultSet.getString("date");
                String id = resultSet.getString("name");
                String text = resultSet.getString("text");
                String pic = resultSet.getString("picture");
                int Like = resultSet.getInt("like");
                postData.add(new PostData(date, id, text, pic, Like));
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
        } catch (SQLException ignored) {

        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1400, 860);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        f1 = new Font("이사만루체 Medium",Font.PLAIN,17);

        JPanel MAINMENU = new JPanel();
        MAINMENU.setBounds(0, 0, 1400, 860);
        frame.getContentPane().add(MAINMENU);
        MAINMENU.setLayout(null);
        MAINMENU.setBackground(new Color(247,241,255));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 70, 1400, 760);
        scrollPane.getVerticalScrollBar().setUI(new PlayListScrollBarUI());
        MAINMENU.add(scrollPane);
        scrollPane.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(192, 192, 192)));

        JPanel panel = new JPanel();
        panel.setBorder(new MatteBorder(0, 1, 0, 1, (Color) new Color(128, 128, 128)));
        scrollPane.setViewportView(panel);
        panel.setBackground(new Color(250,250,250));
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] {400,400,400};
        int[] rowNum = new int[postData.size()/3+1];
        for(int i=0;i<postData.size()/3+1;i++)
            rowNum[i] = 520;
        gbl_panel.rowHeights = rowNum;
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0};
        gbl_panel.rowWeights = new double[]{};
        panel.setLayout(gbl_panel);

        for(int i=0;i<postData.size();i++){
            postPanel.add(MakeNewPanel(panel, i));
            makeimg(postPanel.get(i),postData.get(postData.size()-(i+1)).getPic(),postData.get(postData.size()-(i+1)).getId(),
                    postData.get(postData.size()-(i+1)).getText(),postData.get(postData.size()-(i+1)).getLike());

        }

        JPanel TopBar = new JPanel();
        TopBar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
        TopBar.setBounds(0, 0, 1400, 70);
        TopBar.setBackground(new Color(247,241,255));
        MAINMENU.add(TopBar);
        TopBar.setLayout(null);

        JButton Home = new JButton(home);
        Home.setBounds(613, 10, 50, 50);
        TopBar.add(Home);

        Home.setFocusPainted(false);
        Home.setContentAreaFilled(false);
        Home.setBorderPainted(false);

        JButton Go_Cody = new JButton(cody);
        Go_Cody.setBounds(675, 10, 50, 50);
        TopBar.add(Go_Cody);
        Go_Cody.setBorderPainted(false);
        Go_Cody.setFocusPainted(false);
        Go_Cody.setContentAreaFilled(false);

        JButton GO_Trend = new JButton(trend);
        GO_Trend.setBounds(737, 10, 50, 50);
        TopBar.add(GO_Trend);
        GO_Trend.setBorderPainted(false);
        GO_Trend.setFocusPainted(false);
        GO_Trend.setContentAreaFilled(false);


        JButton Minimize = new JButton(img2);
        Minimize.setBounds(1312, 19, 32, 32);
        TopBar.add(Minimize);
        Minimize.setBorderPainted(false);
        Minimize.setFocusPainted(false);
        Minimize.setContentAreaFilled(false);

        JButton exit = new JButton(img);
        exit.setBounds(1356, 19, 32, 32);
        TopBar.add(exit);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);

        JLabel Logo = new JLabel(MainLogo);
        Logo.setBounds(128, 12, 200, 46);
        TopBar.add(Logo);

        JPanel BottomPanel = new JPanel();
        BottomPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
        BottomPanel.setBackground(new Color(247,241,255));
        BottomPanel.setBounds(0, 830, 1400, 30);
        MAINMENU.add(BottomPanel);


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

        GO_Trend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == GO_Trend) {
                    trend r = new trend();
                    r.setVisible(true);
                    frame.dispose();
                }

            }
        });

        Go_Cody.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == Go_Cody) {
                    cody r = new cody();
                    r.setVisible(true);
                    frame.dispose();
                }

            }
        });

        Home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

    }

    private JPanel MakeNewPanel(JPanel panel, int i) {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
        newPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(20, 20, 20, 35);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = i%3;
        gbc_panel.gridy = i/3;
        panel.add(newPanel, gbc_panel);
        return newPanel;
    }

    public void setVisible(boolean b){
        frame.setVisible(b);
    }

    public void makeimg(JPanel panel,byte[] pic,String Nickname,String Comment,int likenum ) {
        ImageIcon icon = new ImageIcon(pic);
        Image image = icon.getImage();
        Image changeImg = image.getScaledInstance(344, 344, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);

        JLabel Picture = new JLabel(changeIcon); //올린 사진
        Picture.setBounds(1, 46, 344, 344);
        panel.add(Picture);

        JLabel Name = new JLabel(Nickname);//닉네임
        Name.setHorizontalAlignment(SwingConstants.LEFT);
        Name.setFont(f1);
        Name.setBounds(22, 0, 323, 47);
        panel.add(Name);

        JButton Like = new JButton(like); //좋아요 버튼
        Like.setBorderPainted(false);
        Like.setFocusPainted(false);
        Like.setContentAreaFilled(false);
        Like.setBounds(15, 400, 24, 24);
        panel.add(Like);

        String strLike = Integer.toString(likenum);
        JLabel Likenum = new JLabel(strLike);
        Likenum.setFont(new Font("이사만루체 Medium", Font.PLAIN, 10));
        Likenum.setBounds(51, 400, 41, 24);
        panel.add(Likenum);

        JLabel oneline = new JLabel(Comment);
        oneline.setFont(new Font("이사만루체 Medium", Font.PLAIN, 12));
        oneline.setBounds(22, 434, 311, 34);
        panel.add(oneline);

        Like.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int newLike = Integer.parseInt(Likenum.getText())+1;
                Likenum.setText(Integer.toString(newLike));
                panel.add(Likenum);
                Like.setIcon(redHeart);
                panel.add(Like);

                Connection con = null;
                String server = "localhost"; // MySQL 서버 주소
                String database = "example"; // MySQL DATABASE 이름
                String user_name = "root"; //  MySQL 서버 아이디
                String password = "minsu0418"; // MySQL 서버 비밀번호

                // 1.드라이버 로딩
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException err) {
                    System.err.println(" !! <JDBC 오류> Driver load 오류: " + err.getMessage());
                    err.printStackTrace();
                }

                // 2.연결
                try {
                    con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
                    System.out.println("정상적으로 연결되었습니다.");

                    String sql = "UPDATE `example`.`post` SET `like`=? where `name`=?";

                    PreparedStatement pstmt = null;

                    try {
                        pstmt = con.prepareStatement(sql);

                        pstmt.setInt(1, newLike);
                        pstmt.setString(2, Name.getText());
                        // 업데이트
                        int result = pstmt.executeUpdate();
                        if(result==1) {
                            System.out.println("Board데이터 삽입 성공!");
                        }

                    } catch (SQLException err) {
                        // TODO Auto-generated catch block
                        err.printStackTrace();
                    }
                } catch(SQLException err) {
                    System.err.println("con 오류:" + err.getMessage());
                    err.printStackTrace();
                }

                // 3.해제
                try {
                    if(con != null)
                        con.close();
                } catch (SQLException ignored) {

                }
            }
        });
    }

    public final class PlayListScrollBarUI extends BasicScrollBarUI {
        protected void configureScrollBarColors() {
            thumbRect.width = 5;
            trackRect.width = 5;

            thumbColor = new Color(202, 173, 255);
            thumbDarkShadowColor = new Color(131, 148, 255);
            thumbHighlightColor = new Color(207, 180, 255);
            thumbLightShadowColor = new Color(131, 148, 255);
            trackColor = new Color(247,241,255);
            trackHighlightColor = new Color(250,250,250);

        }
        protected JButton createDecreaseButton(int orientation) {
            JButton button = new BasicArrowButton(orientation);
            button.setBackground(new Color(247,241,255));
            button.setForeground(new Color(247,241,255));
            return button;
        }
        protected JButton createIncreaseButton(int orientation) {
            JButton button = new BasicArrowButton(orientation);
            button.setBackground(new Color(247,241,255));
            button.setForeground(new Color(247,241,255));
            return button;
        }
        @Override
        protected Dimension getMaximumThumbSize() {
            // TODO Auto-generated method stub
            return new Dimension(10, 20);
        }
    }

}