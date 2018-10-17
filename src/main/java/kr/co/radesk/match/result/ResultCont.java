package kr.co.radesk.match.result;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.radesk.match.team.TMemberDTO;
import kr.co.radesk.match.team.TeamDTO;
import net.utility.Paging;
import net.utility.Utility;
import net.utility.Paging;

@Controller
public class ResultCont {
	
	@Autowired
	ResultDAO dao;
	
	public ResultCont() {
		System.out.println("ResultCont() 성공");
	}
	
	@RequestMapping(value="match/result/mymatch.do", method=RequestMethod.GET)
	public ModelAndView mymatch(HttpSession session, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String id=(String)session.getAttribute("s_id");
		ArrayList<Map<String, String>> mlist = dao.matchlist(id);
		
		double totalavg = dao.totalavg(id);
		
		int recordPerPage = 5;
		int totalRecord = dao.count(id);
		int nowPage = 1;
		if (req.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(req.getParameter("nowPage"));
		} // if end
		String paging = new Paging().paging2(totalRecord, nowPage, recordPerPage, "./mymatch.do");

		// 평점 리스트
		ArrayList<Map<String, String>> myavg = dao.myavg(id, nowPage, recordPerPage);
		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("paging", paging);
		mav.addObject("totalavg", totalavg);
		mav.addObject("myavg", myavg);
		mav.addObject("mlist", mlist);
		mav.setViewName("match/result/myMatch");		
		
		return mav;
	}
	
	@RequestMapping(value="match/result/insertresult.do", method=RequestMethod.GET)
	public ModelAndView insertresult(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String mcode = req.getParameter("mcode");
		mav.addObject("mcode", mcode);
		mav.addObject("root", Utility.getRoot());		
		mav.setViewName("match/result/insertResult");	
		return mav;
	}
	
	@RequestMapping(value="match/result/insertresult.do", method=RequestMethod.POST)
	public ModelAndView insertresult(MatchResultDTO dto) {
		ModelAndView mav = new ModelAndView();		
		mav.addObject("root", Utility.getRoot());
		int cnt = dao.insertResult(dto);
		   
		if(cnt!=0) {
			String script="";
			script+="<script>";
			script+=" alert('점수입력을 완료 하였습니다.');";
			script+=" opener.parent.location.reload;";
			script+=" window.close()";
			script+="</script>";

			mav.addObject("msg1", script);
			
		}else {
		      mav.addObject("msg1", "<p>평가를 실패하였습니다.</p>");
		      mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
		      mav.addObject("link2", "<input type='button' value='취소' onclick='javascript:window.close()'>");      
		}
		mav.setViewName("match/result/msgView");
		
		return mav;
	}
	
	@RequestMapping(value="match/result/matchresult.do", method=RequestMethod.GET)
	public ModelAndView mymatch(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String mcode = req.getParameter("mcode");
		Map<String, String> teams = dao.teamsRead(mcode);
		String home = teams.get("home");
		String away = teams.get("away");
		
		Map<String, String> point = dao.pointRead(mcode);
		String hpoint = point.get("hpoint");
		String apoint = point.get("apoint");
		
		
		
		TeamDTO homeinfo = dao.teaminfo(home);
		TeamDTO awayinfo = dao.teaminfo(away);
		
		ArrayList<TMemberDTO> homemem = dao.tmeminfo(home);
		ArrayList<TMemberDTO> awaymem = dao.tmeminfo(away);
		
		mav.addObject("hpoint", hpoint);
		mav.addObject("apoint", apoint);
		mav.addObject("mcode", mcode);
		mav.addObject("home", home);
		mav.addObject("away", away);
		mav.addObject("homeinfo", homeinfo);
		mav.addObject("awayinfo", awayinfo);
		mav.addObject("homemem", homemem);
		mav.addObject("awaymem", awaymem);
		
		mav.addObject("root", Utility.getRoot());		
		
		
		mav.setViewName("match/result/matchResult");		
		
		return mav;
	}
	
	@RequestMapping(value="match/result/insertavg.do", method=RequestMethod.GET)
	public ModelAndView insertavg(HttpServletRequest req, HttpSession session, PlayerAvgDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());
		String player = req.getParameter("id");
		int mcode = Integer.parseInt(req.getParameter("mcode"));
		String id=(String)session.getAttribute("s_id");
		int cnt=dao.avgcount(mcode, player, id);
		if(id.equals(player)) {
			String script="";
			script+="<script>";
			script+=" alert('자기 자신은 평가할 수 없습니다.');";
			script+=" window.close();";
			script+="</script>";
			
			mav.addObject("msg1", script);
			
			mav.setViewName("member/msgView");
		}else {
			if(cnt==0) {
				mav.addObject("player", player);
				mav.addObject("id", id);
				mav.addObject("mcode", mcode);
				
				mav.setViewName("match/result/insertAvg");	
			}else {
				String script="";
				script+="<script>";
				script+=" alert('이미 평가한 선수입니다.');";
				script+=" window.close();";
				script+="</script>";
				
				mav.addObject("msg1", script);
				
				mav.setViewName("match/result/msgView");
			}
		}
		
		return mav;
	}
	
	@RequestMapping(value="match/result/insertavg.do", method=RequestMethod.POST)
	public ModelAndView insertavg(PlayerAvgDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/msgView");
		mav.addObject("root", Utility.getRoot());
		int cnt = dao.insertAvg(dto);
		
		if(cnt!=0) {
			String script="";
			script+="<script>";
			script+=" alert('평가를 완료 하였습니다.');";
			script+=" opener.location.reload;";
			script+=" window.close()";
			script+="</script>";

			mav.addObject("msg1", script);
		}else {
		      mav.addObject("msg1", "<p>평가 하기 실패하셨습니다.</p>");
		      mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
		      mav.addObject("link2", "<input type='button' value='취소' onclick='javascript:window.close()'>");      
		}
		
		return mav;
	}
	
	@RequestMapping(value="match/result/playeravg.do", method=RequestMethod.GET)
	public ModelAndView playeravg(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("root", Utility.getRoot());
		String id = req.getParameter("id");
		int mcode = Integer.parseInt(req.getParameter("mcode"));
		ArrayList<Map<String, String>> playeravg = dao.playeravg(id, mcode);
		mav.addObject("playeravg", playeravg);
		
		mav.setViewName("match/result/playerAvg");	
		return mav;
	}


}
