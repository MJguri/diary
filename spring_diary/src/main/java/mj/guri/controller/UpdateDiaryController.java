package mj.guri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mj.guri.dao.DiaryDAO;
import mj.guri.vo.DiaryVO;
import mj.guri.vo.UpdateDiaryVO;

@Controller
public class UpdateDiaryController {
	
	@Autowired
	private DiaryDAO dao;
	
//	public void setDao(DiaryDAO dao) {
//		this.dao = dao;
//	}

	@RequestMapping("/updateDiary")
	public String updateDiary(UpdateDiaryVO udVO) {
		
		dao.updateDiary(udVO);
		
		return "redirect:DLS";
	}

}
