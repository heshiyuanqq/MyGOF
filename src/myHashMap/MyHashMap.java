package myHashMap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
public class MyHashMap {
	 private int bucketCounts=5000;
	 
	 private Integer[] locks=new Integer[bucketCounts];
	 public MyHashMap() {
		 		for(int i=0;i<bucketCounts;i++){
		 			locks[i]=new Integer(1);
		 		}
	 }
	 private int size=0;
	 
	 
	 public int size(){
		 return this.size;
	 }
	 private LinkedList[] buckets=new LinkedList[bucketCounts];
	 
     public synchronized void put_safe_slow(String key,String value){//锁的力度比较粗,只要put操作就占锁（经过分析知道其实put的不是一个bucket其实是不需要加锁的）
    	     int bucketIndex = key.hashCode()%bucketCounts;
    	     if(bucketIndex<0){
    	    	 	bucketIndex=bucketIndex*-1;
    	     }
    	     LinkedList<MyEntry> list = buckets[bucketIndex];
    	     if(list==null){
    	    	   list=new LinkedList<MyEntry>();
    	    	   buckets[bucketIndex]=list;
    	     }
    	     
    	     
    	     //将该key和value封装成MyEntry装入list
    	     boolean keyExist=false;
    	     for (MyEntry myEntry : list) {
				   if(myEntry.getKey().equals(key)){
					       myEntry.setValue(value);
						   keyExist=true;
						   break;
				   }
			 }
    	     
    	     
    	     if(!keyExist){
    	    	 MyEntry entry = new MyEntry();
    	    	 entry.setKey(key);
    	    	 entry.setValue(value);
    	    	 list.add(entry);
    	    	 size++;
    	     }
     }
     public  void put_safe_quickly(String key,String value){//对同一个bucket加锁,相当于更细粒度的锁，性能会有很大提高
		    	 int bucketIndex = key.hashCode()%bucketCounts;
		    	 if(bucketIndex<0){
		    		 bucketIndex=bucketIndex*-1;
		    	 }
		    	
		    	 synchronized (locks[bucketIndex]) {
		    		 		 LinkedList<MyEntry> list = buckets[bucketIndex];
					    	 if(list==null){
					    		 list=new LinkedList<MyEntry>();
					    		 buckets[bucketIndex]=list;
					    	 }
					    	 
					    	 
					    	 //将该key和value封装成MyEntry装入list
					    	 boolean keyExist=false;
					    	 for (MyEntry myEntry : list) {
					    		 if(myEntry.getKey().equals(key)){
					    			 myEntry.setValue(value);
					    			 keyExist=true;
					    			 break;
					    		 }
					    	 }
					    	 
					    	 
					    	 if(!keyExist){
					    		 MyEntry entry = new MyEntry();
					    		 entry.setKey(key);
					    		 entry.setValue(value);
					    		 list.add(entry);
					    		 synchronized (this) {
					    			 size++;
								 }
					    	 }
				}
     }
     
     public String get(String key){
	    	 //计算key的hashCode定位到指定bucket，遍历其中的list找到相应entry，找到它的value
	    	 int bucketIndex=key.hashCode()%bucketCounts;
	    	 if(bucketIndex<0){
    	    	 bucketIndex=bucketIndex*-1;
    	     }
	    	 LinkedList<MyEntry> list = this.buckets[bucketIndex];
	    	 if(list==null){
	    		 	return null;
	    	 }
	    	 
	    	 for (MyEntry entry : list) {
				   if(entry.getKey().equals(key)){
					   	return entry.getValue();
				   }
			 }
	    	 return null;
     }
     
     public static void main(String[] args) {
    	
    	 
    	 
    	     long t1 = System.currentTimeMillis();
	    	 for(int a=0;a<1;a++){
			    		// final MyHashMap map = new MyHashMap();
			    		// final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String,String>();
			    		 final HashMap<String, String> map = new HashMap<String, String>();
				 	       int count=100000;
				 	       final CountDownLatch latch = new CountDownLatch(count);
				 	       for(int i=0;i<count;i++){
				 	    	     final int j=i;
				 	    	     new Thread(new Runnable() {
										public void run() {
												//map.put_safe_slow(j+"",j+"");
												//map.put_safe_quickly(j+"",j+"");
											synchronized (MyHashMap.class) {
												map.put(j+"", j+"");
											}
												
												latch.countDown();
										}
								}).start();
				 	       }
				 	      
				 	      
				 	     try {
							latch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				 	    if(map.size()==count){
				 	    	System.out.print("y");
				 	    }else{
				 	    	System.out.print("n");
				 	    }
	    	 }
	    	 System.out.println();
	    	 long t2 = System.currentTimeMillis();
	    	 System.out.println("耗时："+(t2-t1)+"毫秒！");
	}
}
