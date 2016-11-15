package gof.future.my;

public class Client {

	public Data requestData() {
			//立即返回RealData的代理对象FutureData
			 final FutureData futureData = new FutureData();
			 new Thread(new Runnable() {
					public void run() {
						   RealData realData = new RealData();
						   futureData.setRealData(realData);
					}
			}).start();
			 
			 return futureData;
	}

}
