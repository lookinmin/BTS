package Being;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class menu extends JFrame {

    private JFrame frame;
    private ImageIcon image;
    private Font f1;
    ImageIcon img = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\cancel.png");
    ImageIcon img2 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\minimize2.png");
    ImageIcon img3 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\pic.png");
    ImageIcon MainLogo = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\MainLogo.png");
    ImageIcon like = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\like.png");
    ImageIcon img4 = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\JJ.jpg");
    ImageIcon cody = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\cody1.png");
    ImageIcon trend = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\trend1.png");
    ImageIcon home = new ImageIcon("C:\\Users\\ancx1\\Desktop\\21년도 2학기\\오픈소스\\사용이미지\\home1.png");

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
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1200, 750);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        f1 = new Font("이사만루체 Medium",Font.PLAIN,17);

        JPanel MAINMENU = new JPanel();
        MAINMENU.setBounds(0, 0, 1200, 750);
        frame.getContentPane().add(MAINMENU);
        MAINMENU.setLayout(null);
        MAINMENU.setBackground(new Color(247,241,255));


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 70, 1200, 680);
        MAINMENU.add(scrollPane);
        scrollPane.setBorder(new MatteBorder(0, 0, 0, 0, (Color) Color.LIGHT_GRAY));

        JPanel panel = new JPanel();
        panel.setBorder(null);
        scrollPane.setViewportView(panel);
        panel.setBackground(new Color(250,250,250));
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] {400,400,400};
        gbl_panel.rowHeights = new int[]{520, 520};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0};
        gbl_panel.rowWeights = new double[]{0.0, 0.0};
        panel.setLayout(gbl_panel);

        JPanel panel1 = new JPanel();
        panel1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
        panel1.setBackground(Color.WHITE);
        GridBagConstraints gbc_panel1 = new GridBagConstraints();
        panel1.setLayout(null);
        gbc_panel1.insets = new Insets(30, 35, 10, 20);
        gbc_panel1.fill = GridBagConstraints.BOTH;
        gbc_panel1.gridx = 0;
        gbc_panel1.gridy = 0;
        panel.add(panel1, gbc_panel1);

        makeimg(panel1,img4,"Yaaan___","강","123");

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
        panel2.setBackground(Color.WHITE);
        GridBagConstraints gbc_panel2 = new GridBagConstraints();
        gbc_panel2.insets = new Insets(30, 30, 10, 25);
        gbc_panel2.fill = GridBagConstraints.BOTH;
        gbc_panel2.gridx = 1;
        gbc_panel2.gridy = 0;
        panel.add(panel2, gbc_panel2);

        makeimg(panel2,img4,"KimMo","재","123");


        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
        panel3.setBackground(Color.WHITE);
        GridBagConstraints gbc_panel3 = new GridBagConstraints();
        gbc_panel3.insets = new Insets(30, 20, 10, 35);
        gbc_panel3.fill = GridBagConstraints.BOTH;
        gbc_panel3.gridx = 2;
        gbc_panel3.gridy = 0;
        panel.add(panel3, gbc_panel3);

        makeimg(panel3,img4,"Beung","구","123");

        JPanel panel4 = new JPanel();
        panel4.setLayout(null);
        panel4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
        panel4.setBackground(Color.WHITE);
        GridBagConstraints gbc_panel4 = new GridBagConstraints();
        gbc_panel4.insets = new Insets(20, 35, 30, 20);
        gbc_panel4.fill = GridBagConstraints.BOTH;
        gbc_panel4.gridx = 0;
        gbc_panel4.gridy = 1;
        panel.add(panel4, gbc_panel4);

        makeimg(panel4,img4,"Say","시","123");


        JPanel panel5 = new JPanel();
        panel5.setLayout(null);
        panel5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
        panel5.setBackground(Color.WHITE);
        GridBagConstraints gbc_panel5 = new GridBagConstraints();
        gbc_panel5.insets = new Insets(20, 30, 30, 25);
        gbc_panel5.fill = GridBagConstraints.BOTH;
        gbc_panel5.gridx = 1;
        gbc_panel5.gridy = 1;
        panel.add(panel5, gbc_panel5);
        makeimg(panel5,img4,"MinSunHong","발","123");


        JPanel panel6 = new JPanel();
        panel6.setLayout(null);
        panel6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
        panel6.setBackground(Color.WHITE);
        GridBagConstraints gbc_panel6 = new GridBagConstraints();
        gbc_panel6.insets = new Insets(20, 20, 30, 35);
        gbc_panel6.fill = GridBagConstraints.BOTH;
        gbc_panel6.gridx = 2;
        gbc_panel6.gridy = 1;
        panel.add(panel6, gbc_panel6);
        makeimg(panel6,img4,"JoMinSu","..","123");

        JPanel TopBar = new JPanel();
        TopBar.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(128, 128, 128)));
        TopBar.setBounds(0, 0, 1200, 71);
        TopBar.setBackground(new Color(247,241,255));
        MAINMENU.add(TopBar);
        TopBar.setLayout(null);


        JButton Home = new JButton(home);
        Home.setBounds(481, 10, 50, 50);
        TopBar.add(Home);

        Home.setFocusPainted(false);
        Home.setContentAreaFilled(false);
        Home.setBorderPainted(false);

        JButton Go_Cody = new JButton(cody);
        Go_Cody.setBounds(543, 10, 50, 50);
        TopBar.add(Go_Cody);
        Go_Cody.setBorderPainted(false);
        Go_Cody.setFocusPainted(false);
        Go_Cody.setContentAreaFilled(false);

        JButton GO_Trend = new JButton(trend);
        GO_Trend.setBounds(605, 10, 50, 50);
        TopBar.add(GO_Trend);
        GO_Trend.setBorderPainted(false);
        GO_Trend.setFocusPainted(false);
        GO_Trend.setContentAreaFilled(false);


        JButton Minimize = new JButton(img2);
        Minimize.setBounds(1112, 10, 32, 32);
        TopBar.add(Minimize);
        Minimize.setBorderPainted(false);
        Minimize.setFocusPainted(false);
        Minimize.setContentAreaFilled(false);

        JButton exit = new JButton(img);
        exit.setBounds(1156, 10, 32, 32);
        TopBar.add(exit);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);

        JLabel Logo = new JLabel(MainLogo);
        Logo.setBounds(12, 0, 269, 67);
        TopBar.add(Logo);


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

    public void setVisible(boolean b){
        frame.setVisible(b);
    }

    public void makeimg(JPanel panel,ImageIcon img,String Nickname,String Comment,String likenum ) {
        JLabel Picture = new JLabel(img); //올린 사진
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

        JLabel Likenum = new JLabel(likenum);
        Likenum.setFont(new Font("이사만루체 Medium", Font.PLAIN, 10));
        Likenum.setBounds(51, 400, 41, 24);
        panel.add(Likenum);

        JLabel Undername = new JLabel(Nickname);
        Undername.setHorizontalAlignment(SwingConstants.LEFT);
        Undername.setFont(new Font("이사만루체 Medium", Font.BOLD, 12));
        Undername.setBounds(96, 400, 74, 24);
        panel.add(Undername);

        JLabel oneline = new JLabel("\uB09C... \u3131 \u314F\uB054... \r\n\uB208\uBB3C\uC744 \uD758\uB9B0 \u3137 \u314F .... \r\n\u3131 \u314F\uB054\uC740 \uB208\uBB3C\uC744 \uCC38\uC744 \uC218 \uC5C6\uB294 \uB0B4\uAC00 \uBCC4\uB8E8\u3137 \u314F... \r\n\uB9D8\uC774 \u3147 \u314F \u314D \u314F \uC11C.... \r\n\uC18C\u3139 \u3163\uCE58\uBA70... \uC6B8 \uC218 \uC788 \u3137\u314F\uB294\uAC74.... \r\n\uC88B\uC740\u3131 \u3153 \u3147 \u3151..... \r\n\u3141 \u3153... \uAF2D \uC2AC \u314D \u3153 \u3147 \u3151\uB9CC \uC6B0\uB294 \uAC74 \u3147 \u314F\uB2C8\uC796 \u3147 \u314F...^^ \r\n\uB09C... \uB208\uBB3C\u3147 \u3163 ....\uC88B\uB2E4..... \r\n\u3147 \u314F\uB2C8... \r\n\uBA38 \uB9AC\uAC00 \u3147 \u314F\uB2CC..... \r\n\uB9D8\uC73C\uB85C.....\uC6B0\uB294 \u3134 \u3150\u3131 \u314F \uC88B\u3137 \u314F.....\r\n \r\n\r\n-- By \uCC44\uC5F0");
        oneline.setFont(new Font("이사만루체 Medium", Font.PLAIN, 12));
        oneline.setBounds(22, 434, 311, 34);
        panel.add(oneline);


        Like.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
