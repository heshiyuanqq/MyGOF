package gof.future.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {
		public static void main(String[] args) throws Exception {
					final FutureTask<String> futureTask = new FutureTask<String>(new RealData());
					
					new Thread(futureTask).start();
				 	System.out.println("干其他事情");
				 	Thread.sleep(400);
				 	System.out.println("干其他事情");
				 	Thread.sleep(400);
				 	System.out.println("干其他事情");
				 	Thread.sleep(400);
				 	System.out.println("干其他事情");
				 	Thread.sleep(400);
					System.out.println("     取得数据:"+futureTask.get());	
				
		}
}
