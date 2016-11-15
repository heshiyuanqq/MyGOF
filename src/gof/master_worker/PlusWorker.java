package gof.master_worker;

import java.util.Random;

public class PlusWorker extends Worker { //求立方和  
	    @Override  
	    public Object handle(Object input) {  
	    	   Random r = new Random();
	    	   try {
						Thread.sleep(5);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
		        int i = (Integer)input;  
		        return i * i * i;  
	    }  
}  