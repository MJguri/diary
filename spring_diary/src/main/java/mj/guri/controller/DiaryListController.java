package mj.guri.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import mj.guri.dao.DiaryDAO;
import mj.guri.vo.DiaryVO;
import mj.guri.vo.MemberVO;

@Controller
public class DiaryListController {
	
	@Autowired
	private DiaryDAO dao;
	
//	public void setDao(DiaryDAO dao) {
//		this.dao = dao;
//	}


    //method=RequestMethod.GET
	@RequestMapping(value="/DLS")
	public String diaryList(HttpServletRequest request) {
		//페이징 처리가 된 게시물 리스트 가져오기
		
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		
		int section = Integer.parseInt((_section==null)?"1":_section);
		int pageNum = Integer.parseInt((_pageNum==null)?"1":_pageNum);
		
		int totalCnt = dao.selectAllNumBoard();
		List<DiaryVO> dList = dao.selectTargetBoard(section, pageNum);
		
		request.setAttribute("totalCnt", totalCnt );
		request.setAttribute("section", section);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dList", dList);
		
		return "diary/diary_main";
	}
	
	@RequestMapping("/scroll")
	public String scrollList(MemberVO mVo, HttpSession session, Model model) {
		
		
		mVo = (MemberVO)session.getAttribute("loginUser");
		
		List<DiaryVO> dList = new ArrayList<>();
 		dList = dao.getDiary(mVo.getUserid());
 		
 		
 		Gson gson = new Gson();
		String dList_Json = gson.toJson(dList);
 		
		model.addAttribute("dList", dList_Json);
		
		return "diary/diary_main";
	}

}
