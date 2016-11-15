package gof.future.my;

public class RealData implements Data{
	
	
	public RealData() {
			for(int i=0;i<10;i++){
					System.out.println("真实数据构造中"+i+"...");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
	}

	@Override
	public String getResult() {
		
		return "我是真实数据！！！";
	}


}
