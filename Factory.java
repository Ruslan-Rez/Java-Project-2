package Project2;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

class Factory   implements Runnable{
    private String name;
    private Thread t;
    private boolean suspendFlag;
    private AtomicInteger counter= new AtomicInteger(0);
    private long n = 1000 ;
    private Storage storage;
    private JProgressBar bar;
    private JLabel count;
    private MyPanel panel;



    Factory(String threadname,Storage storage,JProgressBar bar,JLabel count,MyPanel panel){
        this.panel = panel;
        this.bar = bar;
        this.count = count;
        this.storage = storage;
        name = threadname;
        t = new Thread(this,name);
        suspendFlag = false;
        t.start();
    }


    //@Override
    public void run() {
        int i = 1;
        try {
            while (true){


                Baloon b = new Baloon();
                b.setNumber(i);
                b.setColor(BaloonColor.values()[(int)(Math.random()*16)]);
                storage.insertSt(b);
                if (storage.getBaloonList().size() == 90){
                    panel.timer.start();
                    storage.emptyStorage();

                }

                synchronized (this) {

                    count.setText("Count: "+counter);
                    count.repaint();
                    count.revalidate();
                    counter.getAndIncrement();
                }


                Thread.sleep(n);
                synchronized (this){
                    while (suspendFlag) {
                        wait();
                    }

                }
                //for (int j =0; j< baloonList.size();j++) {
                System.out.println(name+" : " + storage.getBaloonList().indexOf(b));//.get(j).getNumber());
                bar.setValue(storage.getBaloonList().size());
                //bar.repaint();
                //bar.revalidate();
                //}
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setn(long n){
        this.n = n;
    }

    synchronized void mysuspend(){
        suspendFlag = true;
    }
    synchronized void myresume(){
        suspendFlag = false;
        notify();
    }
}

