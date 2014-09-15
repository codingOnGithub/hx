package demo;

import java.util.concurrent.TimeUnit;

public class StopThread {  
	  
    private static volatile  boolean stopRequested;  // value: false  
    
    private static volatile int i = 0;  
      
    public static void main(String... args) throws InterruptedException {  
          
        Thread backgroundThread = new Thread(new Runnable() {  
              
            @Override  
            public void run() {  
               
                while(!stopRequested)  
                    i++;  
            }  
        });  
          
        backgroundThread.start();  
          
        TimeUnit.SECONDS.sleep(10);  
        stopRequested = true;  
        System.out.println("i:" + i);
    }  
}  
