package cn.wangtao.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HandlerError implements ErrorController {
	
	 @RequestMapping("/error")
	 @ResponseBody
	    public String handleError(HttpServletRequest request){
		 ModelAndView mv=new ModelAndView();
	        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	        String returnMessage="错误代码："+statusCode;
	        if(statusCode == 401){
	        	System.out.println("401错误");
	            return returnMessage;
	        }else if(statusCode == 404){
	        	System.out.println("404错误");
	        	return returnMessage;
	        }else if(statusCode == 403){
	        	System.out.println("403错误");
	        	return returnMessage;
	        }else{
	        	return returnMessage;
	        }
	    }
	
	@Override
	public String getErrorPath() {
		 return "/error";
	}
	
}
