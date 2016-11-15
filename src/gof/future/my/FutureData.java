package gof.future.my;

public class FutureData implements Data{
	private RealData realData =null;
	
	
	

	@Override
	public synchronized String getResult() {
			//暂时不能这样搞，因为readData不一定创建了,会包空指针异常
		    while(this.realData==null){
			    	try {
							this.wait();
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
		    }
		    return this.realData.getResult();
	}

	public synchronized void setRealData(RealData realData) {
			this.realData=realData;
			this.notifyAll();
	}

}
