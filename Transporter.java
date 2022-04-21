package Project2;

import javax.swing.*;

public class Transporter implements Runnable {
    private String name;
    private Thread t;
    private boolean suspendFlag;
    private Storage storage;
    private JProgressBar bar;
    private JLabel status;
    //Thread.State ts = t.getState();
    Transporter(String threadname,Storage storage,JProgressBar bar,JLabel status){
        this.status = status;
        this.bar = bar;
        this.storage = storage;
        name = threadname;
        t = new Thread(this,name);
        suspendFlag = false;
        t.start();
    }
    public void run() {

        while (true) {
            if (storage.getwait() == 1) {



                if (storage.getBaloonList().size() < 10) {
                    try {
                        System.out.println("Storage is empty");
                        Thread.sleep(3000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    storage.setwait(0);
                    for (int i = 0; i < 10; i++) {
                        status.setText("Loading");
                        storage.deleteST();
                        System.out.println(name);
                        bar.setValue(storage.getBaloonList().size());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    synchronized (this) {
                        while (suspendFlag) {
                            status.setText("Stopped");
                            try {

                                wait();

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                    status.setText("Delivering");

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                status.setText("Waiting");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    synchronized void mysuspend(){
        suspendFlag = true;
    }
    synchronized void myresume() {
        suspendFlag = false;
        notify();
    }
}
