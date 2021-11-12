package Being;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class cody extends JFrame{

    private JFrame frame;
    private JTextField txtwriting;



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

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);



        JPanel CODYLINE = new JPanel();  // 코디 한줄평 업로드 ui 설정
        CODYLINE.setBounds(0, 0, 800, 500);
        frame.getContentPane().add(CODYLINE);
        CODYLINE.setLayout(null);

        JButton btnWriting = new JButton("\uAE00 \uC791\uC131\uD558\uAE30");
        btnWriting.setBounds(377, 408, 160, 36);
        CODYLINE.add(btnWriting);

        JLabel lblcodyline = new JLabel("\uCF54\uB514 \uD55C\uC904 \uD3C9");
        lblcodyline.setFont(new Font("굴림", Font.PLAIN, 18));
        lblcodyline.setBounds(42, 349, 163, 49);
        CODYLINE.add(lblcodyline);

        txtwriting = new JTextField();
        txtwriting.setBounds(239, 349, 480, 49);
        CODYLINE.add(txtwriting);
        txtwriting.setColumns(10);

        JButton btnCODYTOMAIN = new JButton("\uB3CC\uC544\uAC00\uAE30");

        btnCODYTOMAIN.setBounds(560, 408, 160, 36);
        CODYLINE.add(btnCODYTOMAIN);


        btnCODYTOMAIN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu r = new menu();
                r.setVisible(true);
                frame.dispose();
            }
        });

    }


    public void setVisible(boolean b){
        frame.setVisible(b);
    }
}



