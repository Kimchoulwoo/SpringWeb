package kr.co.radesk.match.club;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.radesk.match.club.ClubDTO;
import kr.co.radesk.match.team.TMemberDTO;
import kr.co.radesk.match.team.TeamDTO;
import net.utility.Paging;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class ClubCont {

	@Autowired
	ClubDAO dao;

	public ClubCont() {
		System.out.println("ClubCont() 생성!!");
	}

	@RequestMapping("/match/club/clublist.do")
	public ModelAndView list(ClubDTO dto, String col, String word, HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		col = Utility.checkNull(col);
		word = Utility.checkNull(word);

		// 페이징
		int recordPerPage = 3;
		int totalRecord = dao.count(col, word);
		int nowPage = 1;
		if (req.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(req.getParameter("nowPage"));
		} // if end		
		String paging = new Paging().paging3(totalRecord, nowPage, recordPerPage, col, word, "./clublist.do");

		mav.addObject("club", dao.list(dto, col, word, nowPage, recordPerPage));
		mav.addObject("paging", paging);
		
		mav.setViewName("match/club/clubList");

		return mav;
	}// list() end

	@RequestMapping(value = "/match/club/createclub.do", method = RequestMethod.GET)
	public ModelAndView create(ClubDTO dto, HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String id = (String) session.getAttribute("s_id");
		String code = dao.ccode(id);		
		if (code.equals("C00000000000")) {
			mav.addObject("root", Utility.getRoot());
			mav.setViewName("match/club/clubForm");
		} else {
			String str = "";
			str += "<script>";
			str += "	alert('이미 클럽에 가입되어있습니다.'); location.href='./clublist.do'";
			str += "</script>";
			mav.addObject("str", str);
			mav.setViewName("match/club/clubList");
		}
		return mav;
	}// create() end

	@RequestMapping(value = "/match/club/createclub.do", method = RequestMethod.POST)
	public ModelAndView createProc(ClubDTO dto, HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());
		String id = (String) session.getAttribute("s_id");
		// -------------------------------------------------------------
		// 전송된 파일이 저장되는 실제 경로
		String basePath = req.getRealPath("/match/club/clubimages");

		// 1) <input type='file' name='posterMF'>
		MultipartFile posterMF = dto.getPosterMF();
		String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
		if (poster.length() == 0) {
			poster = "noimage.png";
		} // if end
		dto.setCposter(poster); // 파일명
		// ------------------------------------------------------------
		dto.setCid(id);
		int res= dao.create(dto);
		mav.setViewName("redirect:/match/club/clublist.do");
		return mav;
	}// createProc() end

	@RequestMapping(value = "/match/club/read.do")
	public ModelAndView read(ClubDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dao.read((String) req.getParameter("ccode")));
		mav.addObject("cmem", dao.cmread((String) req.getParameter("ccode")));

		mav.setViewName("match/club/clubRead");
		return mav;
	}// read() end

	// 탈퇴
	@RequestMapping("/match/club/clubban.do")
	public ModelAndView clubban(String ccode, String cid, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int res = dao.clubBan((String) session.getAttribute("s_id"));
		
		mav.setViewName("redirect:/match/club/read.do?ccode="+ccode);
		mav.addObject("root", Utility.getRoot()); // /radesk

		return mav;
	}// clubban() end
	
	@RequestMapping("/match/club/clubjoin.do")
	public ModelAndView join(HttpSession session, String ccode) {
		ModelAndView mav = new ModelAndView();
		
		String id= ((String)session.getAttribute("s_id")).trim();
		int no = dao.noclub(id);
		if(no==0) {
			int res=dao.clubJoin(id,ccode);
			mav.setViewName("redirect:/match/club/read.do?ccode="+ccode);
		}else {
			String str = "";
			str += "<script>";                        
			str += "	alert('이미 클럽에 가입되어있습니다.'); location.href='./clublist.do'";
			str += "</script>";
			mav.addObject("str", str);
			mav.setViewName("match/club/clubList");
		}		
		mav.addObject("root", Utility.getRoot()); // /radesk

		return mav;
	}// join() end

}// class end
