package Being;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;


import java.util.List;
import javax.swing.border.LineBorder;

public class cody extends JFrame implements DropTargetListener {

    JFrame frame;

    DropTarget dropTarget;
    JPanel picPanel;
    JLabel picLabel;
    JTextField txtwriting;
    private Font f1;
    ImageIcon img = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\cancel.png");
    ImageIcon img2 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\minimize2.png");
    ImageIcon cody = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\cody1.png");
    ImageIcon trend = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\trend1.png");
    ImageIcon home = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\home1.png");
    ImageIcon MainLogo = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\MainLogo.png");
    ImageIcon dragpic = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\dragpic2.png");
    ImageIcon post = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\post.png");
    File pilePath;

    String imgEncode;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    cody window = new cody();
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
    public cody() {
        initialize();
    }

    private void InitDragAndDrop() {
        picPanel = new JPanel();

        picLabel = new JLabel(dragpic);
        picLabel.setBounds(0,0,512,512);
        picPanel.add(picLabel);


        dropTarget = new DropTarget(picPanel, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null);
        frame.getContentPane().add(picPanel, BorderLayout.CENTER);
        picPanel.setSize(512, 512);
        picPanel.setBounds(444, 150, 512, 512);
        picPanel.setBackground(Color.WHITE);
        picPanel.setLayout(null);

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

        f1 = new Font("이사만루체 Medium",Font.PLAIN,14);

        InitDragAndDrop();//drag and drop 되게 설정

        JPanel CODYLINE = new JPanel();  // 코디 한줄평 업로드 ui 설정
        CODYLINE.setBounds(0, 0, 1400, 860);
        CODYLINE.setBackground(new Color(250, 250, 250));
        frame.getContentPane().add(CODYLINE);
        CODYLINE.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.GRAY));
        panel.setBackground(new Color(244, 238, 255));
        panel.setBounds(1, 0, 1398, 70);
        CODYLINE.add(panel);
        panel.setLayout(null);

        JPanel BottomPanel = new JPanel();
        BottomPanel.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(128, 128, 128)));
        BottomPanel.setBackground(new Color(247, 241, 255));
        BottomPanel.setBounds(1, 830, 1398, 30);
        CODYLINE.add(BottomPanel);

        JLabel Logo = new JLabel(MainLogo);
        Logo.setBounds(128, 12, 200, 46);
        panel.add(Logo);


        JButton Home = new JButton(home);
        Home.setBounds(613, 12, 50, 50);
        panel.add(Home);
        Home.setFocusPainted(false);
        Home.setContentAreaFilled(false);
        Home.setBorderPainted(false);

        JButton Go_Cody = new JButton(cody);
        Go_Cody.setBounds(675, 12, 50, 50);
        panel.add(Go_Cody);
        Go_Cody.setBorderPainted(false);
        Go_Cody.setFocusPainted(false);
        Go_Cody.setContentAreaFilled(false);


        JButton GO_Trend = new JButton(trend);
        GO_Trend.setBounds(737, 12, 50, 50);
        panel.add(GO_Trend);
        GO_Trend.setBorderPainted(false);
        GO_Trend.setFocusPainted(false);
        GO_Trend.setContentAreaFilled(false);

        JButton Minimize = new JButton(img2);
        Minimize.setBounds(1311, 19, 32, 32);
        panel.add(Minimize);
        Minimize.setBorderPainted(false);
        Minimize.setFocusPainted(false);
        Minimize.setContentAreaFilled(false);

        JButton exit = new JButton(img);
        exit.setBounds(1355, 19, 32, 32);
        panel.add(exit);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(443, 100, 514, 700);
        CODYLINE.add(panel_1);
        panel_1.setLayout(null);

        txtwriting = new JTextField();
        txtwriting.setBounds(24, 573, 471, 44);
        panel_1.add(txtwriting);
        txtwriting.setFont(f1);
        txtwriting.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
        txtwriting.setColumns(10);

        JLabel Name = new JLabel("ID");
        Name.setFont(f1);
        Name.setBounds(24, 10, 164, 34);
        panel_1.add(Name);
        Name.setHorizontalAlignment(SwingConstants.LEFT);

        JButton Post = new JButton(post);
        Post.setBounds(438, 626, 64, 64);
        panel_1.add(Post);
        Post.setFocusPainted(false);
        Post.setContentAreaFilled(false);
        Post.setBorderPainted(false);

        Post.addActionListener(new ActionListener() {//글작성하기 버튼
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null, "업로드 완료");
                    DBHandle(txtwriting.getText());
                    System.out.println("post");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

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

        Home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == Home) {
                    menu r = new menu();
                    r.setVisible(true);
                    frame.dispose();
                }

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
    }

    private void DBHandle(String postText) throws IOException {
        String pilePath = "C:\\Users\\ancx1\\BTS\\src\\nowID.txt";//BTS-main안 텍스트파일
        String userID = "";
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(pilePath));
            String sLine = null;
            while( (sLine = inFile.readLine()) != null )
                userID = sLine;

        } catch (IOException e) {
            System.out.println("입출력 오류");
        }


        SimpleDateFormat timeformat = new SimpleDateFormat("yy.MM.dd-hh.mm.ss");
        Date time = new Date();
        String postTime = timeformat.format(time);
        System.out.println(postTime);

        int like = 0;

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


            String sql = "insert into  `example`.`post` values(?, ?, ?, ?, ?)";

            PreparedStatement pstmt = null;

            try {
                pstmt = con.prepareStatement(sql);
                //name, text, pic, date, like 순서로 저장

                pstmt.setString(1, userID);
                pstmt.setString(2, postText);
                pstmt.setString(3, imgEncode);
                pstmt.setString(4, postTime);
                pstmt.setInt(5, like);

                // 업데이트
                int result = pstmt.executeUpdate();
                if(result==1) {
                    System.out.println("Board데이터 삽입 성공!");
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

//            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM example.post");
//
//
//            while(resultSet.next()){
//                String id = resultSet.getString("name");
//                String text = resultSet.getString("text");
//                String pic = resultSet.getString("picture");
//                String date = resultSet.getString("date");
//                int Like = resultSet.getInt("like");
//                decodeImg = pic;
//            }
//
//            resultSet.close();
//            statement.close();
//            con.close();
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

    public void setVisible(boolean b){
        frame.setVisible(b);
    }

    private void EncodeImgFile() throws IOException {
        File f = new File(String.valueOf(pilePath));
        String strBase64 = null;
        if (f.exists() && f.isFile() && f.length() > 0) {
            byte[] bt = new byte[(int) f.length()];

            try (FileInputStream fis = new FileInputStream(f)) {
                fis.read(bt);
                strBase64 = new String(Base64.getEncoder().encode(bt));
            }
        }

        imgEncode = strBase64;//base64에 이미지 파일 인코딩한거 저장
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        System.out.println("drop");
        if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
            dtde.acceptDrop(dtde.getDropAction());
            Transferable tr = dtde.getTransferable();

            try {
                //파일명 얻어오기
                List list = (List) tr.getTransferData(DataFlavor.javaFileListFlavor);

                BufferedImage image; //로컬 파일을 사용하는 경우

                image = ImageIO.read((File) list.get(0));

                pilePath = (File) list.get(0);

                picLabel.setIcon(new ImageIcon(image));//picLabel에 드래그한 사진 띄움
                frame.getContentPane().add(picPanel);

                frame.setVisible(true);

                EncodeImgFile();//이미지 파일 인코딩
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        System.out.println("dragEnter");
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        System.out.println("dragOver");
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        System.out.println("dragActionChanged");
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        System.out.println("dragExit");
    }
}


