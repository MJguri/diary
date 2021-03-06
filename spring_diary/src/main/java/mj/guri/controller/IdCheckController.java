package mj.guri.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mj.guri.dao.MemberDAO;

@Controller
public class IdCheckController {
	
	private MemberDAO dao;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}


	@RequestMapping(value="idCheck", method=RequestMethod.GET)
	public String idCheck(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		
		int result = dao.confirmID(userid);  
		
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		
		return "login/idCheck";
	}

}
