package gof.future.jdk;

import java.util.concurrent.Callable;

public class RealData implements Callable<String>{

	@Override
	public String call() throws Exception {
		for(int i=0;i<10;i++){
			System.out.println("缓慢构造真实数据中"+i+"...");
			Thread.sleep(500);
		}
		return "我是真实数据！";
	}
}
