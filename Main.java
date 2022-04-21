package Project2;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends JFrame {
    static JFrame f;
    static JLabel stat;
    static JSlider sl;
    static MyPanel panel;

    public static void main(String[] args){
        f = new JFrame("Frame");
        Storage st = new Storage();
        JProgressBar pb = new JProgressBar();
        WaitTime wait = new WaitTime("wait",st);
        pb.setMaximum(90);
        pb.setMinimum(0);
        pb.setStringPainted(true);
        pb.setForeground(Color.GREEN);
        pb.setValue(st.getBaloonList().size());
        panel = new MyPanel(pb);
        JPanel pc = new JPanel(new BorderLayout());
        pc.add(panel);
        JLabel faccounter = new JLabel();
        JLabel trstat = new JLabel();



        JPanel le = new JPanel(new GridLayout(0,1));
        JButton g = new JButton("Add");
        JPanel btm = new JPanel();
        AtomicInteger i = new AtomicInteger(1);
        g.addActionListener(e -> {
            Factory fac  = new Factory("Factory"+i,st,pb,faccounter,panel);
            final JButton newButton = new JButton("Factory "+i);
            newButton.addActionListener(e1 -> {
                JFrame s = new JFrame("Factory ");
                s.setLayout(new GridLayout(0,1));
                JButton del = new JButton("delete");
                sl = new JSlider(100,3000,1000);
                sl.setPaintTrack(true);
                sl.setPaintTicks(true);
                sl.setPaintLabels(true);
                sl.setMajorTickSpacing(2900);
                sl.addChangeListener(e22 -> {
                    stat.setText("value of Slider is =" + sl.getValue());
                    fac.setn(sl.getValue());
                });
                fac.setn(sl.getValue());
                stat = new JLabel();
                stat.setText("value of Slider is =" + sl.getValue());
                del.addActionListener(e2 -> {
                    le.remove(newButton);
                    fac.mysuspend();
                    le.revalidate();
                    le.repaint();
                    s.dispose();
                });
                s.setSize(400,250);
                s.setVisible(true);
                s.add(faccounter);
                s.add(stat);
                s.add(sl);
                s.add(del);
            });
            le.add(newButton);
            le.revalidate();
            le.repaint();
            i.getAndIncrement();
        });
        btm.add(g);
        JPanel pl = new JPanel(new BorderLayout());
        pl.add(btm , BorderLayout.PAGE_END);
        pl.add(le, BorderLayout.NORTH );
        JScrollPane scroller = new JScrollPane(pl,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);





        JButton x = new JButton("Add");
        JPanel tr = new JPanel(new GridLayout(0,1));
        JPanel bt = new JPanel();
        AtomicInteger j = new AtomicInteger(1);
        x.addActionListener(e -> {final JButton nButton = new JButton("Transport "+j);
            Transporter tran = new Transporter("Transporter "+j,st,pb,trstat);
            nButton.addActionListener(e1 -> {
                JFrame t = new JFrame("Transporter  ");
                JButton dl = new JButton("Stop");
                JButton rm = new JButton("Start");

                dl.addActionListener(e7 -> {
                    tran.mysuspend();
                });
                rm.addActionListener(e9 -> {
                    tran.myresume();
                });
                t.setSize(300,150);
                t.setVisible(true);
                JPanel wy = new JPanel(new GridLayout(1,0));
                wy.add(dl);
                wy.add(rm);
                t.add(wy,BorderLayout.SOUTH);
                t.add(trstat,BorderLayout.NORTH);


            });
            tr.add(nButton);
            tr.revalidate();
            tr.repaint();
            j.getAndIncrement();
        });
        bt.add(x);
        JPanel pr = new JPanel(new BorderLayout());
        pr.add(tr, BorderLayout.NORTH);
        pr.add(bt, BorderLayout.SOUTH);
        JScrollPane scroll = new JScrollPane(pr,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);




        JSplitPane s1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroller, pc);
        s1.setDividerLocation(140);
        s1.setDividerSize(2);
        JSplitPane s2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT , s1 , scroll);
        s2.setDividerLocation(460);
        s2.setDividerSize(2);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(s2,BorderLayout.CENTER);
        f.setSize(600, 300);
        f.setVisible(true);

    }






}
