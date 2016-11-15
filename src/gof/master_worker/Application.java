package gof.master_worker;

import java.util.Map;
import java.util.Set;

public class Application {  
	    public static void main(String[] args) {  
	    	
	    	long t1 = System.currentTimeMillis();
	    	    int correctCount=0;
	    	    int errorCount=0;
		    	for(int i=0;i<50;i++){
			    		int taskNum=100;
				        Master master = new Master(new PlusWorker(), 4);  //往workerThreadMap中加入4个线程
				        for(int ii=1; ii<=taskNum; ii++){ //提交100个子任务  
				            	master.submitATask(ii);  //taskQueue.add(job);  
				        }
				        master.execute(); //开始计算(即start上面4个线程)
				        Map<String, Object> resultMap = master.getResultMap();  
				          
				        int re = 0;  //最终计算结果保存在此  
				        //不需要等待所有Worker都执行完即可  
				        while(true) {  
						            Set<String> keys = resultMap.keySet();  //开始计算最终结果  
						            String key = null;  
						            for(String k : keys) {  
							                key = k;  
							                break;  
						            }  
						            Integer iii = null;  
						            if(key != null){
						            	 	iii = (Integer)resultMap.get(key);  
						            }  
						               
						            if(iii != null)  {
						            	  	re += iii; //最终结果  
						            }
						              
						            if(key != null){
						            		resultMap.remove(key); //移除已被计算过的项目  
						            }  
						                
						            if(master.isComplete() && resultMap.size()==0)  {
						            		break;  
						            }
					     }  
						  if(re!=25502500){
							  errorCount++;
							  System.out.print("n");
						  }else{
							  System.out.print("y");
						  }
		    	}
		    	
		    	
		    	System.out.println("错误次数："+errorCount);
		    	long t2 = System.currentTimeMillis();
		    	System.out.println("耗时："+(t2-t1)+"毫秒！");
	    }  
}  