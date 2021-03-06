package mj.guri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mj.guri.dao.DiaryDAO;

@Controller
public class DeleteDiaryController {
	
	@Autowired
	private DiaryDAO dao;
	
//	public void setDao(DiaryDAO dao) {
//		this.dao = dao;
//	}

	@RequestMapping("/deleteDiary")
	public String deleteDiary(@RequestParam(value="code") int diaryCode) {
		
		dao.deleteDiaryByCode(diaryCode);
		
		return "redirect:DLS";
	}

}
