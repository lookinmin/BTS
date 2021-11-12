package Being;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class trand extends JFrame{

    private JFrame frame;
    private JTextField LOGIN_ID_INSERT;
    private JTextField LOGIN_PW_INSERT;

    private ImageIcon icon;



    Date today = new Date();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    trand window = new trand();
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
    public trand() {
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


        JPanel TREND = new JPanel();    // 트랜드보기 ui 설정
        TREND.setBounds(0, 0, 800, 500);
        frame.getContentPane().add(TREND);
        TREND.setLayout(null);
        JButton btnTRENDTOMAIN = new JButton("\uBA54\uC778\uBA54\uB274\uB85C \uB3CC\uC544\uAC00\uAE30");
        btnTRENDTOMAIN.setBounds(598, 417, 175, 23);
        TREND.add(btnTRENDTOMAIN);

        btnTRENDTOMAIN.addActionListener(new ActionListener() {
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
