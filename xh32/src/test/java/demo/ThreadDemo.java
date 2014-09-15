package demo;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

 


public class ThreadDemo {
	 public static class MyTask implements Runnable {  
	        private static int id = 0;  
	  
	        private String name = "task-"+(++id);  
	        private int sleep;   
	  
	        public MyTask(int sleep) {  
	            super();  
	            this.sleep = sleep;  
	        }  
	  
	        public void run() {  
	            System.out.println(name+" -----start-----");  
//	            try {  
//	                、、 Thread.sleep(2000);
//	            } catch (InterruptedException e) {  
//	                e.printStackTrace();  
//	            }  
	            System.out.println(name+" -----end "+sleep+"-----");  
	        }  
	  
	    }  
	
	public static void main(String[] args) throws InterruptedException
    {
		System.out.println("==================start==================");  
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());  
        int n = 100;  
        int sleep = 1 * 1000;  //10s  
        Random rm = new Random();  
        for(int i=0; i<n; i++) {  
            executor.execute(new MyTask(rm.nextInt(sleep)+1));  
        }  
  
        executor.shutdown();  
  
        try {  
            boolean loop = true;   
            do {    //等待所有任务完成  
                loop = !executor.awaitTermination(1,TimeUnit.DAYS                		);
                System.out.println(loop);
            } while(loop);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
  
        System.out.println("==================end====================");  
    }
}
