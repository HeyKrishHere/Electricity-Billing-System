package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener {

    JButton back;
    Paytm() {
        setTitle("Paytm Web Page"); // Set a title for the JFrame
        setSize(800, 600);
        setLocation(400, 150);
        
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try {
            j.setPage("https://paytm.com/online-payments");//this website will open
        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not Load</html>");
        }

        JScrollPane scrollPane = new JScrollPane(j); // Add the JEditorPane to the JScrollPane
        add(scrollPane); // Add the JScrollPane to the frame
        
       
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
       
    }

    public static void main(String args[]) {
     
         new Paytm();
        
    }
}
