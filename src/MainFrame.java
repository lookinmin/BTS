import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel panel;
    private JTextField textField1;
    private JButton LoginBtn;
    private JPasswordField passwordField1;

    public MainFrame(){
        setContentPane(panel);
//        panel.add(txtFirstName);
//        panel.add(passwordField1);
//        panel.add(button1Button);
//        panel.add(button2Button);
        setTitle("welcome");
        setSize(1000, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

    }
}