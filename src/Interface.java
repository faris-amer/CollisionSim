import javax.swing.*;
import java.awt.*;

public class Interface extends JFrame {
    public Interface(){

        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setTitle("Falling Ball");
        add(new Core());
        setVisible(true);
    }

    public static void main(String[] args) {
        new Interface();
    }
}
