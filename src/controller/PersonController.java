package controller;

import annotation.Controller;
import annotation.RequestMapping;


@Controller
@RequestMapping("/person")
public class PersonController {
	@RequestMapping("/delPerson.do")
	public String delPerson(){
		System.out.println(" this is delPerson");
		return "/person/delPerson";//只需要加webContent后面的部分即可（默认是转发）
	}
}
