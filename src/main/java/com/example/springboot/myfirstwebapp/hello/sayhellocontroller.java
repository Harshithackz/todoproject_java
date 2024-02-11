package com.example.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class sayhellocontroller {
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello, what are you doing today!!";
	}
	@RequestMapping("/hellohtml")
	@ResponseBody
	public String hellohtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>First Web app</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("Body of first HTML web page here");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	@RequestMapping("/hellojsp")
	public String hellojsp() {
		return "sayhello";
	}
	

}
