package Being;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.ImageIcon;

public class menu extends JFrame {

    private JFrame frame;
    private ImageIcon image;
    Date today = new Date();

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
        frame.setBounds(100, 100, 800, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);



        image = new ImageIcon("C:\\Users\\sdjmc\\OneDrive\\바탕화면\\LoginPage.jpg");//배경이미지
        JPanel MAINMENU = new JPanel() {
            public void paintComponent(Graphics l) {

                l.drawImage(image.getImage(), 0, 0, null);

                setOpaque(false);
                super.paintComponent(l);
            }


        };
        MAINMENU.setBounds(0, 0, 800, 500);
        frame.getContentPane().add(MAINMENU);
        MAINMENU.setLayout(null);

        JPanel MAINSUB = new JPanel(); //메인메뉴에서 옆에 자투리(트렌드보기, 글작성하기) 메뉴 나오게 끔 패널 설정
        MAINSUB.setBounds(600, 0, 200, 500);
        MAINMENU.add(MAINSUB);
        MAINSUB.setLayout(null);

        JButton btnMAINTOCODY = new JButton("\uAE00 \uC791\uC131\uD558\uAE30");

        btnMAINTOCODY.setBounds(22, 46, 148, 44);
        MAINSUB.add(btnMAINTOCODY);

        JButton btnMAINTOTREND = new JButton("\uD2B8\uB79C\uB4DC \uBCF4\uAE30");

        btnMAINTOTREND.setBounds(22, 363, 148, 44);
        MAINSUB.add(btnMAINTOTREND);

        btnMAINTOCODY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == btnMAINTOCODY) {
                    cody r = new cody();
                    r.setVisible(true);

                }

            }
        });
        btnMAINTOTREND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                if(obj == btnMAINTOTREND) {
                    trend r = null;
                    try {
                        r = new trend();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    r.setVisible(true);

                }

            }
        });


    }

    public void setVisible(boolean b){
        frame.setVisible(b);
    }
}