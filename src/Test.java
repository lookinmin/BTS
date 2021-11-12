import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    private JPanel test1;
    private JButton btn_testButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();                            //프레임안에서 여러가지를 띄울 수 있는 칸 ( div )
        JPanel btnPannel = new JPanel();                        //버튼이 들어갈 패널

        JLabel label = new JLabel("들어갈 내용 입력");        //글자를 표시하는 기능만 있음
        JButton btn1 = new JButton("btn text");            // 버튼1
        JButton btn2 = new JButton("Exit");                // 버튼2
        JTextArea txtArea = new JTextArea();                    //여러줄 입력가능, textfield는 한줄
        JTextField txtField = new JTextField(200);      //숫자는 입력값 제한

        btnPannel.add(btn1);
        btnPannel.add(btn2);
        panel.setLayout(new BorderLayout());                    //테두리가 있는 layout
        panel.add(label, BorderLayout.NORTH);
        panel.add(btnPannel, BorderLayout.WEST);
        panel.add(txtArea, BorderLayout.CENTER);
        //panel.add(new JLabel("Welcome"));                     //panel안에 label을 추가

        btn1.addActionListener(new ActionListener() {           //버튼1의 기능 추가
            @Override
            public void actionPerformed(ActionEvent e) {        //클릭 기능 Syntax
                //txtArea.append("you are amazing\n");            //txtArea에 text 출력
                label.setText(txtArea.getText());
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.add(panel);                                       //프레임에 panel을 add

        frame.setResizable(false);                              //ui화면 크기를 바꿀 수 있냐 -> true or false
        frame.setVisible(true);                                 //ui 실행창 on
        frame.setPreferredSize(new Dimension(840,840/12*9));
        frame.setSize(1400, 800);               //실행창 크기
        frame.setLocationRelativeTo(null);                      //윈도우 어디서 실행건지 null이면 가운데
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //실행창 끄면 같이 꺼짐
//        JFrame frame = new JFrame("Test");
//        JButton btn = new JButton("btn_testButton");
//        frame.setContentPane(new Test().test1);
//        btn.setSize(400, 200);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setSize(1400,800);
//        frame.setVisible(true);
    }
}
