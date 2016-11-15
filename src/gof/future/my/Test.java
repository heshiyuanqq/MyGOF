package gof.future.my;

public class Test {
		public static void main(String[] args) throws Exception {
					Client client = new Client();
					final Data data=client.requestData();
				/*	
					for(int i=0;i<5;i++){
						System.out.println("        等待期间做其他事情"+i+"...");
						Thread.sleep(500);
					}
					*/
					
					
					
					for(int i=0;i<10;i++){
						
						new Thread(new Runnable() {
								public void run() {
										System.out.println("取得真实数据："+data.getResult()+"!");
								}
						}).start();
					}
		}
}
