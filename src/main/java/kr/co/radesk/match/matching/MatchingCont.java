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
		System.out.println("MatchingCont() ����");
	}// MatchingCont() end

	// �� ���
	@RequestMapping(value = "match/matching/match.do", method = RequestMethod.GET)
	public ModelAndView match(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		// select �±׿� ���� ���� �� ����Ʈ ���
		String id = (String) (session.getAttribute("s_id"));
		mav.addObject("teamlist", dao.teamList(id));

		// tcode �޾ƿͼ� ���õ� �� ���
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

	// ���˻�
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

	// ��ġ��û
	@RequestMapping(value = "match/matching/comeon.do", method = RequestMethod.GET)
	public ModelAndView comeon(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String str = "";
		String homecode = req.getParameter("homecode");
		String awaycode = req.getParameter("awaycode");

		int res = dao.comeon(homecode, awaycode);
		if (res == 1) {
			str += "<script>";
			str += "		alert('��û�� �Ϸ�Ǿ����ϴ�.')";
			str += "</script>";
			mav.addObject("alert", str);
		} else {
			str += "<script>";
			str += "		alert('�����Ͽ����ϴ�. \n �ٽýõ����ּ���')";
			str += "</script>";
			mav.addObject("alert", str);
		}
		mav.setViewName("match/matching/match");
		String id = (String) (session.getAttribute("s_id"));
		mav.addObject("teamlist", dao.teamList(id));
		return mav;
	}// comeon() end

	// ��ġ ���� ������
	@RequestMapping(value = "match/matching/agreematch.do", method = RequestMethod.GET)
	public ModelAndView agreematch(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();

		// select �±׿� ���� ���� �� ����Ʈ ���
		String id = (String) (session.getAttribute("s_id"));
		mav.addObject("agreelist", dao.agreeList(id));

		// tcode �޾ƿͼ� ���õ� �� ���
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

	// ��û �� ��ġ ����
	@RequestMapping(value = "match/matching/refuse.do", method = RequestMethod.GET)
	public ModelAndView refuse(HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String home = req.getParameter("home");
		String away = req.getParameter("away");

		String str = "";
		str += "<script>";
		str += "		alert('��û �ź� �ƽ��ϴ�.')";
		str += "</script>";

		int res = dao.refuse(home, away);

		mav.setViewName("redirect:/match/matching/match.do");
		return mav;
	}// refuse() end

	// ��û �� ��ġ �³�
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
