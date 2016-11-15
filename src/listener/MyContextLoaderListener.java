package listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import controller.UserController;
import annotation.Controller;
import annotation.RequestMapping;
import util.ClassUtil;



/**
 * 此监听器只执行一次（容器启动时执行,扫描controller包下所有的类）
 * @author Administrator
 *
 */

public class MyContextLoaderListener implements ServletContextListener{

	public static HashMap<String,HashMap<String,Object>> map=new HashMap<String, HashMap<String,Object>>();
	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//扫描controller下面所有的类
		List<Class<?>> classes = ClassUtil.getClasses("controller");
		if(classes!=null&&classes.size()>0){
				for (Class<?> clazz : classes) {
					//判断头上是否有"Controller"注解,有的话，再取出“RequestMapping”注解(如果有的话)
					Controller controller = clazz.getAnnotation(Controller.class);
					if(controller!=null){
						  RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
						  if(requestMapping!=null){
							  String urlOnClass = requestMapping.value();
							  urlOnClass=urlOnClass.replaceAll("/", "");
							  System.out.println(urlOnClass);
							  //将请求url和controller关联起来
							  HashMap<String, Object> url_method_map = new HashMap<String, Object>();
							  try {
									  url_method_map.put("controllerObject", clazz.newInstance());
									  map.put(urlOnClass, url_method_map);
									  
									  //遍历该class的方法集合，将url和方法对象存储到map中
									  Method[] methods = clazz.getMethods();
									  for (Method method : methods) {
										  RequestMapping reqMapp_meth = method.getAnnotation(RequestMapping.class);
										  if(reqMapp_meth!=null){
											  	String urlOnMethod = reqMapp_meth.value();
											  	urlOnMethod=urlOnMethod.replaceAll("/", "");
											  	System.out
														.println(urlOnMethod);
											  	url_method_map.put(urlOnMethod, method);
										  }
									  }
								} catch (InstantiationException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}
						  }
					}
				}
		}
	}
	
	
	public static void main(String[] args) {
		Class<UserController> clazz=UserController.class;
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			RequestMapping annotation = method.getAnnotation(RequestMapping.class);
			if(annotation!=null){
				String value = annotation.value();
				System.out.println(value);
			}
		}
		

	}

}
