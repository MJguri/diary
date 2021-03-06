package mj.guri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mj.guri.dao.MemberDAO;
import mj.guri.vo.MemberVO;

@Controller
public class MemberJoinController {
	
	private MemberDAO dao;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/join")
	public String form() {
		
		return "login/join";
	}
	
	@RequestMapping("/joinMember")
	public String memberJoin(MemberVO mVo, Model model) {
		
		int result = dao.insertMember(mVo);
		
		if(result!=1) {
			model.addAttribute("message", "회원 가입에 실패했습니다.");
		}
		
		return "login/login";
	}

}
