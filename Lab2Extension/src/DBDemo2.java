import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

public class DBDemo2 extends JFrame implements ActionListener {
    private JLabel countLabel;
    private JButton button;
    private int count = 0; 

    public DBDemo2() {
        countLabel = new JLabel(Integer.toString(count), JLabel.CENTER);
        button = new JButton("Click me");
        add(countLabel, BorderLayout.SOUTH);
        add(button, BorderLayout.NORTH);
        
        // the class is an action listener
        button.addActionListener(this);
    }
    
    // what happens after you click the button
    public void actionPerformed(ActionEvent e) {
        count++;
        countLabel.setText(Integer.toString(count));
    }
    
   public static void main(String[] args) {
        DBDemo2 f = new DBDemo2();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
 } 