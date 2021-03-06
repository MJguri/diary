package mj.guri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mj.guri.dao.DiaryDAO;
import mj.guri.vo.DiaryVO;

@Controller
public class ReadDiaryController {
	
	@Autowired
	public DiaryDAO dao;
	
//	public void setDao(DiaryDAO dao) {
//		this.dao = dao;
//	}



	@RequestMapping("/readDiary")
	public String readDiary(@RequestParam(value="diaryCode") int diaryCode, Model model) {
		
		DiaryVO dVo = dao.selectDiaryByCode(diaryCode);
		
		model.addAttribute("dVo", dVo);
		
		return "diary/read_diary";
	}
}
