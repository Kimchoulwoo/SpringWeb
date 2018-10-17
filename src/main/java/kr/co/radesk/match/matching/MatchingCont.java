package kr.co.radesk.match.matching;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MatchingCont {

	@Autowired
	MatchingDAO dao;

	public MatchingCont() {
		System.out.println("MatchingCont() 생성");
	}// MatchingCont() end

	// 팀 출력
	@RequestMapping(value = "match/matching/match.do", method = RequestMethod.GET)
	public ModelAndView match(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		// select 태그에 내가 속한 팀 리스트 출력
		String id = (String) (session.getAttribute("s_id"));
		mav.addObject("teamlist", dao.teamList(id));

		// tcode 받아와서 선택된 팀 출력
		if (req.getParameter("tcode") != null) {
			String tcode = req.getParameter("tcode").trim();
			ArrayList<Map<String, String>> awaylist = new ArrayList<>();

			awaylist = dao.search(tcode);
			int awaycnt = 0;
			if (awaylist != null) {
				awaycnt = awaylist.size();
			}
			mav.addObject("awaycnt", awaycnt);
			mav.addObject("hometeam", dao.homeTeam(tcode));
		} // if end

		mav.setViewName("match/matching/match");
		return mav;
	}// match() end

	// 상대검색
	@RequestMapping(value = "match/matching/search.do", method = RequestMethod.GET)
	public ModelAndView search(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String tcode = req.getParameter("tcode");
		String id = (String) (session.getAttribute("s_id"));

		ArrayList<Map<String, String>> awaylist = new ArrayList<>();
		awaylist = dao.search(tcode);
		int awaycnt = awaylist.size();
		int now = Integer.parseInt(req.getParameter("now"));
		mav.addObject("now", now);
		mav.addObject("teamlist", dao.teamList(id));
		mav.addObject("awaylist", awaylist.get(now));
		mav.addObject("tcode", tcode);
		mav.addObject("hometeam", dao.homeTeam(tcode));
		mav.addObject("awaycnt", awaycnt);

		mav.setViewName("match/matching/match");
		return mav;
	}// search() end

	// 매치신청
	@RequestMapping(value = "match/matching/comeon.do", method = RequestMethod.GET)
	public ModelAndView comeon(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String str = "";
		String homecode = req.getParameter("homecode");
		String awaycode = req.getParameter("awaycode");

		int res = dao.comeon(homecode, awaycode);
		if (res == 1) {
			str += "<script>";
			str += "		alert('신청이 완료되었습니다.')";
			str += "</script>";
			mav.addObject("alert", str);
		} else {
			str += "<script>";
			str += "		alert('실패하였습니다. \n 다시시도해주세요')";
			str += "</script>";
			mav.addObject("alert", str);
		}
		mav.setViewName("match/matching/match");
		String id = (String) (session.getAttribute("s_id"));
		mav.addObject("teamlist", dao.teamList(id));
		return mav;
	}// comeon() end

	// 매치 수락 페이지
	@RequestMapping(value = "match/matching/agreematch.do", method = RequestMethod.GET)
	public ModelAndView agreematch(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		// select 태그에 내가 속한 팀 리스트 출력
		String id = (String) (session.getAttribute("s_id"));
		mav.addObject("agreelist", dao.agreeList(id));

		// tcode 받아와서 선택된 팀 출력
		if (req.getParameter("tcode") != null) {
			String tcode = req.getParameter("tcode").trim();

			ArrayList<Map<String, String>> awaylist = new ArrayList<>();
			// awaylist=dao.search(tcode);
			mav.addObject("myteam", dao.myTeam(tcode));
			mav.addObject("matchteam", dao.matchTeam(tcode));
		} // if end

		mav.setViewName("match/matching/agreematch");
		return mav;
	}// agree() end

	// 신청 온 매치 거절
	@RequestMapping(value = "match/matching/refuse.do", method = RequestMethod.GET)
	public ModelAndView refuse(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String home = req.getParameter("home");
		String away = req.getParameter("away");

		String str = "";
		str += "<script>";
		str += "		alert('신청 거부 됐습니다.')";
		str += "</script>";

		int res = dao.refuse(home, away);

		mav.setViewName("redirect:/match/matching/match.do");
		return mav;
	}// refuse() end

	// 신청 온 매치 승낙
	@RequestMapping(value = "match/matching/agree.do", method = RequestMethod.GET)
	public ModelAndView agree(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String home = req.getParameter("home");
		String away = req.getParameter("away");

		int res = dao.agree(home, away);

		mav.setViewName("redirect:/match/matching/match.do");
		return mav;
	}// refuse() end

}// class end
