package mj.guri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mj.guri.dao.DiaryDAO;
import mj.guri.vo.CreateDiaryVO;

@Controller
public class DiaryCreateController {
	
	@Autowired
	private DiaryDAO dao;
	
//	public void setDao(DiaryDAO dao) {
//		this.dao = dao;
//	}

	@RequestMapping("/createDiaryForm")
	public String createDiaryForm() {
		return "diary/create_diary";
	}
	
	@RequestMapping("/createDiary")
	public String createDiary(CreateDiaryVO cdVo, Model model) {
		
		int result = dao.insertDiary(cdVo);
		
		if(result==1) {
			model.addAttribute("message", "일기가 작성되었습니다.");
			
		}else {
			model.addAttribute ("message", "일기 작성이 실패하였습니다.");
		}
		
		
		return "redirect:DLS";
	}

}
