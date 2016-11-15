package filter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestParameter;
import controller.UserController;
import listener.MyContextLoaderListener;

@WebFilter("/MyDispatcherFilter")
public class MyDispatcherFilter implements Filter {

    public MyDispatcherFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//解析url取出后面的东西
		HttpServletRequest req=null;
		HttpServletResponse resp=null;
		try {
				req=(HttpServletRequest) request;
				resp=(HttpServletResponse) response;
				
				String servletPath = req.getServletPath();//  /user/addUser
				if(!servletPath.endsWith(".do")){//不需要拦截
					 chain.doFilter(req, resp);
					 return;
				}
				String[] split = servletPath.split("/");
				String urlOnClass = split[1];
				String urlOnMethod = split[2];
				
				
				HashMap<String,HashMap<String,Object>> outerMap=MyContextLoaderListener.map;
				HashMap<String, Object> innerMap = outerMap.get(urlOnClass);
				Object object = innerMap.get("controllerObject");//此object即为提供服务的controller对象
				Method method = (Method) innerMap.get(urlOnMethod);
		       
				
				
				
				ArrayList<Object> paramList = new ArrayList<Object>();
				Map<String, String[]> parameterMap = req.getParameterMap();
				
				Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			    for (Annotation[] annotations : parameterAnnotations) {
				    	for (Annotation annotation : annotations) {
					    		RequestParameter requestParameter=(RequestParameter) annotation;
					    		String argKey = requestParameter.value();
					    		if(argKey.equals("request")){
					    				paramList.add(req);
					    		}else if(argKey.equals("response")){
					    			 	paramList.add(resp);
					    		}else{
					    			    String[] values = parameterMap.get(argKey);
					    			    if(values!=null){
					    			    	  if(values.length==1){
					    			    		  paramList.add(values[0]);
					    			    	  }else{
					    			    		  paramList.add(values);
					    			    	  }
					    			    }
					    		}
						}
				}
				Object[] params = paramList.toArray();
				//
				String returnAddr = (String) method.invoke(object, params);//将request接收的参数璨递给controller
				
				if(returnAddr!=null&&returnAddr.trim().length()>0){
						//去掉首位上的/（如果是的话）
						returnAddr=returnAddr.replaceAll("^/", "");
						//根据returnValue确定具体跳转到哪里
						req.getRequestDispatcher("/jsp/"+returnAddr+".jsp").forward(req, resp);
				}
		} catch (Exception e) {
			    StringBuilder sb = new StringBuilder();
			    sb.append("<font color='red'>");
			    sb.append(e.toString());
			    sb.append("</font><br/>");
				StackTraceElement[] stackTrace = e.getStackTrace();
				for (StackTraceElement stackTraceElement : stackTrace) {
						System.out.println(stackTraceElement.toString());
						String str = stackTraceElement.toString();
						String reg="(\\()(.*)(\\))";
						Pattern pattern = Pattern.compile(reg);
						Matcher matcher = pattern.matcher(str);
						String bracketsStr="";
						while(matcher.find()){
							bracketsStr=matcher.group(2);
						}
						str=str.replaceAll(reg, "<font color='blue'>("+bracketsStr+")</font>");
						sb.append(str);
						sb.append("<br/>");
				}
				resp.getWriter().write(sb.toString());
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	
	public static void main(String[] args) throws Exception, SecurityException {
					Class clazz=UserController.class;
					Method method = clazz.getMethod("addUser",HttpServletRequest.class,HttpServletResponse.class,String.class,String.class);
					Annotation[][] parameterAnnotations = method.getParameterAnnotations();
				// addUser(HttpServletRequest req,HttpServletResponse resp,@RequestParameter("name") String name,String age){
					
				for (Annotation[] annotations : parameterAnnotations) {
					for (Annotation annotation : annotations) {
						RequestParameter anno=(RequestParameter) annotation;
						String value = anno.value();
					}
				}
		
	}

}
