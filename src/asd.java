
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class asd {

    private JFrame frame;
    private JTextField textField;
    private ImageIcon image;
    private ImageIcon one;
    private ImageIcon two;
    private ImageIcon three;
    private ImageIcon four;
    private ImageIcon five;
    private ImageIcon six;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    asd window = new asd();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     Create the application.
     */
    public asd() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1400, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(1, 1, 1200, 800);
        frame.getContentPane().add(panel);

        one = new ImageIcon("C:\\Users\\ybh82\\Desktop\\one.jpg");
        two = new ImageIcon("C:\\Users\\ybh82\\Desktop\\t.png");
        three = new ImageIcon("C:\\Users\\ybh82\\Desktop\\two.jpg");
        four = new ImageIcon("C:\\Users\\thrry\\Desktop\\LoginPage.jpg");//����̹���
        five = new ImageIcon("C:\\Users\\thrry\\Desktop\\LoginPage.jpg");//����̹���
        six = new ImageIcon("C:\\Users\\thrry\\Desktop\\LoginPage.jpg");//����̹���


        JScrollPane sp = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setLayout(null);

        JLabel pone = new JLabel(one);
        pone.setBounds(68, 77, 229, 222);
        panel.add(pone);

        JLabel ptwo = new JLabel(two);
        ptwo.setBounds(384, 77, 229, 222);
        panel.add(ptwo);

        JLabel pthree = new JLabel(three);
        pthree.setBounds(700, 77, 229, 222);
        panel.add(pthree);

        JLabel lblNewLabel_2 = new JLabel(one);
        lblNewLabel_2.setBounds(68, 442, 229, 222);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_2_1 = new JLabel("New label");
        lblNewLabel_2_1.setBounds(384, 442, 229, 222);
        panel.add(lblNewLabel_2_1);

        JLabel lblNewLabel_2_2 = new JLabel("New label");
        lblNewLabel_2_2.setBounds(700, 442, 229, 222);
        panel.add(lblNewLabel_2_2);

        JLabel lblcodyline2 = new JLabel("OOTD");
        lblcodyline2.setHorizontalAlignment(SwingConstants.CENTER);
        lblcodyline2.setBounds(384, 314, 213, 36);
        panel.add(lblcodyline2);

        JLabel pname_1 = new JLabel("\uC724\uBCD1\uD601");
        pname_1.setBounds(74, 40, 57, 24);
        panel.add(pname_1);

        JLabel pname_2 = new JLabel("\uC870\uBBFC\uC218");
        pname_2.setBounds(381, 43, 57, 24);
        panel.add(pname_2);

        JLabel pname_3 = new JLabel("\uAE40\uAD11\uBAA8");
        pname_3.setBounds(701, 43, 57, 24);
        panel.add(pname_3);

        JLabel pname_4 = new JLabel("\uC774\uC5F0\uADDC");
        pname_4.setBounds(74, 404, 57, 24);
        panel.add(pname_4);

        JLabel pname_5 = new JLabel("\uC724\uBCD1\uD601");
        pname_5.setBounds(381, 404, 57, 24);
        panel.add(pname_5);

        JLabel pname_6 = new JLabel("\uC724\uBCD1\uD601");
        pname_6.setBounds(701, 404, 57, 24);
        panel.add(pname_6);

        JLabel lblcodyline1 = new JLabel("OOTD");
        lblcodyline1.setHorizontalAlignment(SwingConstants.CENTER);
        lblcodyline1.setBounds(68, 314, 213, 36);
        panel.add(lblcodyline1);

        JLabel lblcodyline3 = new JLabel("OOTD");
        lblcodyline3.setHorizontalAlignment(SwingConstants.CENTER);
        lblcodyline3.setBounds(700, 314, 213, 36);
        panel.add(lblcodyline3);

        JLabel lblcodyline4 = new JLabel("OOTD");
        lblcodyline4.setHorizontalAlignment(SwingConstants.CENTER);
        lblcodyline4.setBounds(68, 688, 213, 36);
        panel.add(lblcodyline4);

        JLabel lblcodyline5 = new JLabel("OOTD");
        lblcodyline5.setHorizontalAlignment(SwingConstants.CENTER);
        lblcodyline5.setBounds(384, 688, 213, 36);
        panel.add(lblcodyline5);

        JLabel lblcodyline6 = new JLabel("OOTD");
        lblcodyline6.setHorizontalAlignment(SwingConstants.CENTER);
        lblcodyline6.setBounds(700, 688, 213, 36);
        panel.add(lblcodyline6);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(50, 384, 266, 350);
        lblNewLabel.setOpaque(true);
        lblNewLabel.setBackground(Color.white);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setBackground(Color.WHITE);
        lblNewLabel_1.setBounds(366, 384, 266, 350);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setOpaque(true);
        lblNewLabel_3.setBackground(Color.WHITE);
        lblNewLabel_3.setBounds(682, 384, 266, 350);
        panel.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setOpaque(true);
        lblNewLabel_4.setBackground(Color.WHITE);
        lblNewLabel_4.setBounds(50, 20, 266, 350);
        panel.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setOpaque(true);
        lblNewLabel_5.setBackground(Color.WHITE);
        lblNewLabel_5.setBounds(366, 20, 266, 350);
        panel.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setOpaque(true);
        lblNewLabel_6.setBackground(Color.WHITE);
        lblNewLabel_6.setBounds(682, 20, 266, 350);
        panel.add(lblNewLabel_6);
        //JScrollPane sp = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setLocation(0, 0);
        sp.setSize(1000, 750);

        frame.getContentPane().add(sp);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(1045, 80, 97, 73);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("New button");
        btnNewButton_1.setBounds(1045, 455, 97, 63);
        frame.getContentPane().add(btnNewButton_1);
    }
}
