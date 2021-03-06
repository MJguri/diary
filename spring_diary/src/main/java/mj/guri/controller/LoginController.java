package mj.guri.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mj.guri.dao.MemberDAO;
import mj.guri.vo.MemberVO;

@Controller
public class LoginController {
	
	private MemberDAO dao;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/login")
	public String form() {
		return "login/login";
	}
	
	@RequestMapping("/doLogin")
	public String login(MemberVO mVo, HttpSession session, Model model) {
		
		int result = dao.userCheck(mVo.getUserid(), mVo.getUserpwd());
		
		if(result == 1) {
			session.setAttribute("loginUser", mVo);
		
		}else if(result == 0) { //비밀번호가 틀린 경우
			
			model.addAttribute("message", "비밀번호가 맞지 않습니다.");
			return "login/login";
			
		}else if(result == -1) {// 아이디가 없는 경우
			
			model.addAttribute("message", "존재하지 않는 회원입니다.");
			return "login/login";
			
		}
//		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//		dispatcher.forward(request, response);
		
		return "redirect:DLS";
	}

}
