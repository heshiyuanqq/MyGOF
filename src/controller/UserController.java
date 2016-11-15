package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import pojo.User;
import annotation.Controller;
import annotation.RequestMapping;
import annotation.RequestParameter;


@Controller
@RequestMapping("/user")
public class UserController {
	
	private List<User> usersDB=new ArrayList<User>();

	@RequestMapping("/addUserPage.do")
	public String addUserPage(){
		System.out.println("this is addUserPage");
		
		return "/user/addUserPage";
	}
	
	@RequestMapping("/addUser.do")
	public String addUser(@RequestParameter("request") HttpServletRequest req,
							@RequestParameter("username") String name,
							@RequestParameter("age") String age,
							@RequestParameter("password") String psw,
							@RequestParameter("hobbys") String[] hobbies,
							@RequestParameter("address") String addr){
		
		   User user = new User();
		   user.setId(UUID.randomUUID().toString());
		   user.setUsername(name);
		   user.setAge(age);
		   user.setPassword(psw);
		   user.setHobbys(hobbies);
		   user.setAddress(addr);
		   
		   
		   usersDB.add(user);
		
		   req.setAttribute("userList", usersDB);
		   return "/user/listUser";
	}
	
	@RequestMapping("/delUser.do")
	public String delUser(){
		
		System.out.println(" this is delUser");
		return "/user/delUser";//只需要加webContent后面的部分即可（默认是转发）
	}
	
	
	@RequestMapping("/updateUser.do")
	public String updateUser(){
		System.out.println("this is updateUser");
		return "/user/updateUser";
	}
	@RequestMapping("/listUser.do")
	public String listUser(@RequestParameter("request") HttpServletRequest req){
		System.out.println("this is listUser");
		req.setAttribute("userList", usersDB);
		return "/user/listUser";
	}

}
