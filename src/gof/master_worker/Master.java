package gof.master_worker;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;



/**
 * 发现入队主要做两件事情，第一是将入队节点设置成当前队列尾节点的下一个节点。
 * 第二是更新tail节点，如果tail节点的next节点不为空，则将入队节点设置成tail节
 * 点，如果tail节点的next节点为空，则将入队节点设置成tail的next节点，所以tail
 * 节点不总是尾节点，理解这一点对于我们研究源码会非常有帮助。
 * @author Administrator
 *
 */
public class Master {  
    protected Queue<Object> taskQueue =  new ConcurrentLinkedQueue<Object>();  //任务队列   
    protected Map<String, Thread> workerThreadMap =   new HashMap<String, Thread>();   //Worker进程队列  
    protected Map<String, Object> resultMap =  new ConcurrentHashMap<String, Object>();   //子任务处理结果集  
      
    public Master(Worker worker, int threadCounter) {  //worker：用来工作的工人对象，threadCounter:开启线程的个数
		        worker.setWorkQueue(taskQueue);  //？？？
		        worker.setResultMap(resultMap);  //？？？
		        for(int i=0; i<threadCounter; i++) {  //其中 Integer.toString(i)为线程名称
		        		workerThreadMap.put(Integer.toString(i),  new Thread(worker, Integer.toString(i)));  
		        }  
    }  
      
    //是否所有的子任务都提交结果了  
    public boolean isComplete() {  
	        for(Map.Entry<String, Thread> entry : workerThreadMap.entrySet()) {  
		            if(entry.getValue().getState() != Thread.State.TERMINATED)  { //存在为完成的线程  
		            	 	return false;  
		            }
	        }  
	        return true;  
    }  
      
    //提交一个子任务  
    public void submitATask(Object job) {  
    		taskQueue.add(job);  
    }  
      
    //返回子任务结果集  
    public Map<String, Object> getResultMap() {  
        	return resultMap;  
    }  
      
  
    public void execute() {    //执行所有Worker进程，进行处理  
	        for(Map.Entry<String, Thread> entry : workerThreadMap.entrySet()) {  
	            entry.getValue().start();  
	        }  
    }  
}  