package Project2;

public class WaitTime implements Runnable{
    private String name;
    private Storage storage;
    private Thread t;
    WaitTime(String name,Storage storage){
        this.name = name;
        t = new Thread(this,name);
        this.storage = storage;
        t.start();
    }


    @Override
    public void run() {
        while(true){
            if(storage.getwait() == 0){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                storage.setwait(1);
                System.out.println("open");
            }else {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
