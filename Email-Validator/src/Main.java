import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Component implements ActionListener {
    static JFrame frame = new JFrame("Email Validator");
    static JLabel heading = new JLabel("Email-Validator");
    static JLabel paragraph = new JLabel();
    static JTextField tf = new JTextField();
    static JTextArea ta1 = new JTextArea();
    static JButton btn = new JButton("Validate");
    static Container container = new Container();
    static Color darkblue = new Color(0, 30, 54);
    public static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(color);
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = insets.top = insets.bottom = radius;
            return insets;
        }

    }

    public static void main(String[] args) {
        Main obj = new Main();
        Color beige = new Color(245, 245, 220);
        Font head = new Font("Times New Roman", Font.BOLD, 40);
        Font para = new Font("Times New Roman", Font.ITALIC, 15);
        frame.getContentPane().setBackground(beige);
        frame.setResizable(false);
        frame.setSize(700, 500);
        frame.setLocation(400, 200);
        frame.setLayout(null);

        heading.setBounds(10, 10, 700, 60);
        heading.setFont(head);
        frame.add(heading);

        paragraph.setText("( Here you can validate your email )");
        paragraph.setBounds(10, 60, 700, 60);
        paragraph.setFont(para);
        frame.add(paragraph);

        ta1.setBackground(Color.WHITE);
        ta1.setBounds(15, 110, 650, 250);
        ta1.setLineWrap(true);
        ta1.setWrapStyleWord(true);
        ta1.setBorder(new RoundedBorder(20, Color.BLACK));
        ta1.setEditable(false);
        frame.add(ta1);

        tf.setBackground(Color.WHITE);
        tf.setBounds(15, 400, 500, 40);
        frame.add(tf);

        obj.btnClick();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        container.add(frame);

    }
        public void btnClick(){
        btn.setBackground(darkblue);
        btn.setBounds(550, 400, 100, 40);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        frame.add(btn);
    }

    public static boolean valEmail(String input) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(input);
        return matcher.find();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            String email = tf.getText();
            if(valEmail(email) == true){
                ta1.setText(" Congratulations ! \n The provided email is valid.");
            } else if (valEmail(email) == false) {
                ta1.setText(" !!! INVALID EMAIL PROVIDED !!! \n !!! The provided email is invalid !!! \n !!! Check the provided email once more !!! ");
            }
            else{
                JOptionPane.showMessageDialog(this,"Enter a valid email address","Error",JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}