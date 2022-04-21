package Project2;

import java.util.ArrayList;
import java.util.List;


public class Storage implements Runnable {

    public List<Baloon> baloonList = new ArrayList<>() ; //(new ArrayList<Baloon>(99));
    private int wait;

    public void setwait(int wait){
        this.wait = wait;
    }
    public int getwait(){
        return wait;
    }
    public List <Baloon> getBaloonList(){
        return baloonList;
    }
    public synchronized void emptyStorage(){
        baloonList.clear();
    }
    public synchronized void insertSt(Baloon b){
        baloonList.add(b);
    }
    public synchronized void deleteST(){
        baloonList.remove(0);
        System.out.println("Storage delete");
    }
    @Override
    public String  toString (){
        String result = "";
        for(int i = 0;i<10;i++){
            if(baloonList.get(i)==null){
                result =  result+ "NULL";
            }else{
                result =  result+(baloonList.get(i));
            }
        }
        return result;
    }

    @Override
    public void run() {

    }
}
