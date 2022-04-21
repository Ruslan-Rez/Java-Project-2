package Project2;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener{

    Image enemy;
    Timer timer;
    int yVelocity = 1;
    int x = 0;
    int y = 300;
    JProgressBar bar;


    MyPanel(JProgressBar bar){
        this.bar = bar;

        this.setLayout(new BorderLayout());
        this.add(bar,BorderLayout.SOUTH);
        enemy = new ImageIcon("Balloons.png").getImage();
        timer = new Timer(5, this);

    }

    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(enemy, x, y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( y<0) {
            y = 600;
            timer.stop();


        }
        y = y - yVelocity;
        repaint();

    }
}