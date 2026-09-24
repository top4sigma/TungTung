import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Keyboard Input Detector");
        JTextField textField = new JTextField();

        // Add the key listener to the text component
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Detects physical key presses (works for action/modifier keys like Shift/Home)
                System.out.println("Key Pressed Code: " + e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Key Released Code: " + e.getKeyCode());
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Detects valid Unicode character generation
                System.out.println("Key Typed Char: " + e.getKeyChar());
            }
        });

        frame.add(textField);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
