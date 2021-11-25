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
import java.util.logging.SimpleFormatter;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.List;

public class cody extends JFrame implements DropTargetListener {

    JFrame frame;

    DropTarget dropTarget;
    JPanel picPanel;
    JLabel picLabel;
    JTextField txtwriting;

    File pilePath;

    String imgEncode;

    Date today = new Date();
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

        dropTarget = new DropTarget(picPanel, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null);
        frame.add(picPanel, BorderLayout.CENTER);
        picPanel.setSize(512, 512);
        picPanel.setBounds(333, 50, 512, 512);
        picPanel.setBackground(Color.gray);

        picLabel = new JLabel("사진을 드래그 해주세요");

        picPanel.setLayout(new BorderLayout());
        picPanel.add(picLabel, BorderLayout.CENTER);

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        InitDragAndDrop();//drag and drop 되게 설정

        JPanel CODYLINE = new JPanel();  // 코디 한줄평 업로드 ui 설정
        CODYLINE.setBounds(0, 0, 1200, 800);
        frame.getContentPane().add(CODYLINE);
        CODYLINE.setLayout(null);

        JButton btnWriting = new JButton("\uAE00 \uC791\uC131\uD558\uAE30");
        btnWriting.setBounds(800, 600, 160, 36);
        CODYLINE.add(btnWriting);

        JLabel lblcodyline = new JLabel("\uCF54\uB514 \uD55C\uC904 \uD3C9");
        lblcodyline.setFont(new Font("굴림", Font.PLAIN, 18));
        lblcodyline.setBounds(42, 600, 163, 49);
        CODYLINE.add(lblcodyline);

        txtwriting = new JTextField();
        txtwriting.setBounds(239, 600, 480, 49);
        CODYLINE.add(txtwriting);
        txtwriting.setColumns(10);

        btnWriting.addActionListener(new ActionListener() {//글작성하기 버튼
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

        JButton btnCODYTOMAIN = new JButton("\uB3CC\uC544\uAC00\uAE30");

        btnCODYTOMAIN.setBounds(1000, 600, 160, 36);
        CODYLINE.add(btnCODYTOMAIN);

        btnCODYTOMAIN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu r = new menu();
                r.setVisible(true);
                frame.dispose();
            }
        });

    }

    private void DBHandle(String postText) throws IOException {
        String pilePath = "C:\\Users\\sdjmc\\IdeaProjects\\BTS-main\\src\\nowID.txt";//BTS-main안 텍스트파일
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
        String password = "qwer1234"; // MySQL 서버 비밀번호

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


            String sql = "insert into  `example`.`post` values(?, ?, ?, ?, ?)";

            PreparedStatement pstmt = null;

            try {
                pstmt = con.prepareStatement(sql);
                //name, text, pic, date, like 순서로 저장

                pstmt.setString(1, postTime);
                pstmt.setString(2, userID);
                pstmt.setString(3, postText);
                pstmt.setString(4, imgEncode);
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

//                BufferedImage image; //로컬 파일을 사용하는 경우
//
//                image = ImageIO.read((File) list.get(0));

                pilePath = (File) list.get(0);

                ImageIcon icon = new ImageIcon(String.valueOf(pilePath));
                Image img = icon.getImage();
                Image changeImg = img.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
                ImageIcon changeIcon = new ImageIcon(changeImg);

                picLabel.setIcon(changeIcon);//picLabel에 드래그한 사진 띄움

                frame.add(picPanel);

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

