package kr.co.radesk.match.team;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.radesk.match.team.TMemberDTO;
import kr.co.radesk.payment.PaymentDTO;
import net.utility.Paging;
import net.utility.Utility;

@Controller
public class TeamCont {

	@Autowired
	TeamDAO dao;

	public TeamCont() {
		System.out.println("TeamCont() ����!!");
	}// TeamCont() end
	
	//http://localhost:9090/radesk/match/team/teamCreate.do
	@RequestMapping(value="match/team/teamCreate.do", method=RequestMethod.GET)
	public ModelAndView create(HttpSession session, PaymentDTO pdto) {
		ModelAndView mav = new ModelAndView();
		
		String ccode=null;
		ccode = dao.ccode((String)session.getAttribute("s_id"));
				
		if(ccode.equals("C00000000000")==true) { //Ŭ�� ���� default��
			String script="";
			script+="<script>";
			script+=" alert('���Ե� Ŭ���� �־�� �� ������ �����մϴ�.');";
			script+="</script>";

			mav.addObject("msg1", script);
			mav.setViewName("match/team/msgView");
			mav.addObject("root", Utility.getRoot());	
		}else {		
			ArrayList<PaymentDTO> plist = new ArrayList<PaymentDTO>();
			
			plist=dao.plist((String)(session.getAttribute("s_id")));
	        mav.addObject("plist", plist);       
			mav.setViewName("match/team/pcodeList");
			mav.addObject("root", Utility.getRoot());			
		}
		return mav;
	} //�� ���� �� �ҷ�����
	
	@RequestMapping(value="match/team/teamForm.do", method=RequestMethod.GET)
	public ModelAndView create(HttpServletRequest req, PayDTO dto) {
	    ModelAndView mav = new ModelAndView();
		
		String pcode=req.getParameter("pcode");
		dto = dao.payread(pcode);
		
		mav.addObject("pcode", pcode);
		mav.addObject("dto", dto);
		mav.addObject("root", Utility.getRoot());
		mav.setViewName("match/team/teamForm");
		
		return mav;
	} //�� ���� �� �ҷ�����
	
	@RequestMapping(value="match/team/teamCreate.do", method=RequestMethod.POST)
	public ModelAndView createProc(TeamDTO dto, TMemberDTO tmdto, HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("root", Utility.getRoot());
		
		String tarea = "";
		tarea += req.getParameter("wr_sido");
		tarea += " ";
		tarea += req.getParameter("wr_gugun");
		dto.setTarea(tarea);       
		
		String ttime = "";
		ttime += req.getParameter("year");
		ttime += req.getParameter("month");
		String day=req.getParameter("day");
		if(day.length()<2) {
			day="0"+day;
		}
		ttime += day;
		ttime += req.getParameter("time");
		dto.setTtime(ttime);
		
		int memcnt = Integer.parseInt(req.getParameter("memcnt"));
		
		dto.setPlayer(memcnt);	
		
		String tcode = dao.createTeam(dto);
		if(tcode.equals("")) {
			 mav.addObject("msg1", "<p>�� ������ �����Ͽ����ϴ�.</p>");
			 mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascript:history.back()'>");
		}else {
			String script="";
			script+="<script>";
			script+=" alert('���� �����Ͽ����ϴ�.');";
			script+=" location.href=\"./teamlist.do\"";
			script+="</script>";

			mav.addObject("msg1", script);
		}//if end
		
		for(int i=1; i<=memcnt; i++) {
			String tid = "";
			tid = req.getParameter("player"+i);
			
			if(tid!="") {
				tmdto.setTid(tid);				
				tmdto.setTcode(tcode);

				int num = dao.insertMember(tmdto);
				
				if(num==0) {
					 mav.addObject("msg1", "<p>���� �߰� �����Ͽ����ϴ�.</p>");
					 mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascript:history.back()'>");
				/*}else {
					String script="";
					script+="<script>";
					script+=" alert('���� �߰� �����ϼ̽��ϴ�.');";
					script+=" location.href=\"../index.do\"";
					script+="</script>";
					
					mav.addObject("msg2", script);*/
			    }
			}
		}
		mav.setViewName("match/team/msgView");
		
		return mav;
	} //�� ����

	@RequestMapping(value="match/team/searchID.do", method=RequestMethod.GET)
	public ModelAndView searchID(HttpServletRequest req, HttpSession session ) {
		ModelAndView mav = new ModelAndView();
		String s_id=(String)session.getAttribute("s_id");
		
		mav.addObject("root", Utility.getRoot());		
		
		mav.addObject("list", dao.findCmember(s_id));
		mav.addObject("player", req.getParameter("player"));
		
		mav.setViewName("match/team/cmemList");		
		
		return mav;
	} //Ŭ�� ��� ����Ʈ	

	@RequestMapping("match/team/teamlist.do")
	public ModelAndView list(TeamDTO dto, TeammemDTO tmdto, String col, String word, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		

		// ���ѽ�ũ��
		// String str+=""; �� �� �������� �ҽ��� �� �ҷ��ͼ� �Ѱ��ִ� ������� ¥�� �ذ�� ��
		// append

		// �˻� ���ڿ� null�˻�
		col = Utility.checkNull(col);
		word = Utility.checkNull(word);

		// ����¡
		int recordPerPage = 3;
		int totalRecord = dao.count(col, word);
		int nowPage = 1;
		if (req.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(req.getParameter("nowPage"));
		} // if end
		String paging = new Paging().paging3(totalRecord, nowPage, recordPerPage, col, word, "./teamlist.do");

		// �� ����Ʈ
		ArrayList<TeamDTO> list = dao.list(dto, col, word, nowPage, recordPerPage);

		// ���� ����Ʈ
		ArrayList<TeammemDTO> tm_list = new ArrayList<TeammemDTO>();
		ArrayList<ArrayList<TeammemDTO>> tm = new ArrayList<ArrayList<TeammemDTO>>();
		ArrayList<Integer> teamavg = new ArrayList<>();
		if(list!=null) {
			for (int i = 0; i < list.size(); i++) {
				tm_list = dao.teammemlist(list.get(i).getTcode()); // �ɹ��� �ҷ����� ���� ���ڵ� �ֱ�
				teamavg.add(i, dao.teamavg(list.get(i).getTcode())); // �� ���� ������ ���ϱ� ���� ���ڵ� �ֱ�
				tm.add(tm_list); // ���� arraylist
			} // for end
			mav.addObject("totalRecord", totalRecord);
			mav.addObject("list", list); // �� ����Ʈ
			mav.addObject("tm", tm); // �� ���� �ɹ� ����Ʈ
			mav.addObject("teamavg", teamavg); // �� ���� ����
			mav.addObject("paging", paging);
		}
		mav.addObject("list", list); // �� ����Ʈ
		mav.setViewName("match/team/teamList");	
		mav.addObject("root", Utility.getRoot());
		return mav;
	}// list end

	// �� ��ȸ
		@RequestMapping(value = "/match/team/teamread.do", method = RequestMethod.GET)
		public ModelAndView read(HttpServletRequest req, HttpSession session) { // �Ǵ� dto, req
			String tcode=req.getParameter("tcode");
			ModelAndView mav = new ModelAndView();
			String ccode = dao.cCodeCheck((String) session.getAttribute("s_id"));
			TeamDTO teamLeader = dao.teamLeader(tcode);
			int tmcount = dao.tmCount(tcode);

			mav.addObject("root", Utility.getRoot());
			mav.setViewName("match/team/read");
			mav.addObject("teamLeader", teamLeader);
			mav.addObject("ccode", ccode);
			mav.addObject("tcode", tcode);
			mav.addObject("tmcount", tmcount);
			mav.addObject("alreadyjoin", dao.joinIdCheck(tcode, (String) session.getAttribute("s_id")));
			mav.addObject("tmlist", dao.read(teamLeader.getTid(), tcode));
			mav.addObject("player", teamLeader.getPlayer());

			return mav;
		}// read() end

		// �� ����
		@RequestMapping("/match/team/teamjoin.do")
		public ModelAndView join(String tcode, HttpSession session) {
			ModelAndView mav = new ModelAndView();
			TMemberDTO tmemdto = new TMemberDTO();
			tmemdto.setTid((String) session.getAttribute("s_id"));
			tmemdto.setTcode(tcode);
			dao.tMemInsert(tmemdto);
			String ccode = dao.cCodeCheck((String) session.getAttribute("s_id"));

			//mav.setViewName("match/team/read");
			mav.setViewName("redirect:/match/team/teamread.do");
			
			mav.addObject("root", Utility.getRoot()); // /radesk
			TeamDTO teamLeader = dao.teamLeader(tcode);
			int tmcount = dao.tmCount(tcode);
			mav.addObject("teamLeader", teamLeader);
			mav.addObject("tcode", teamLeader.getTcode());
			mav.addObject("ccode", ccode);
			mav.addObject("tmcount", tmcount);
			mav.addObject("alreadyjoin", dao.joinIdCheck(tcode, (String) session.getAttribute("s_id")));
			mav.addObject("tmlist", dao.read(teamLeader.getTid(), tcode));
			mav.addObject("player", teamLeader.getPlayer());

			return mav;
		}// join() end

		// Ż��
		@RequestMapping("/match/team/teamban.do")
		public ModelAndView ban(String tcode, String tid, String tlid, int tmemno, HttpSession session) {
			ModelAndView mav = new ModelAndView();
			String ccode = dao.cCodeCheck((String) session.getAttribute("s_id"));
			if (tlid.equals((String) session.getAttribute("s_id")) || tid.equals((String) session.getAttribute("s_id"))) {
				dao.tMemDelete(tmemno);
			}
			mav.setViewName("redirect:/match/team/teamread.do");

			mav.addObject("root", Utility.getRoot()); // /radesk
			TeamDTO teamLeader = dao.teamLeader(tcode);
			int tmcount = dao.tmCount(tcode);
			mav.addObject("teamLeader", teamLeader);
			mav.addObject("tcode", teamLeader.getTcode());
			mav.addObject("ccode", ccode);
			mav.addObject("tmcount", tmcount);
			mav.addObject("alreadyjoin", dao.joinIdCheck(tcode, (String) session.getAttribute("s_id")));
			mav.addObject("tmlist", dao.read(teamLeader.getTid(), tcode));
			mav.addObject("player", teamLeader.getPlayer());

			return mav;
		}// ban() end

		// �� ����
		@RequestMapping("/match/team/teamdelete.do")
		public ModelAndView tdelete(String tcode, HttpSession session) {
			ModelAndView mav = new ModelAndView();
			TeamDTO teamLeader = dao.teamLeader(tcode);
			if (teamLeader.getTid().equals((String) session.getAttribute("s_id"))) {
				dao.tMemDelete(tcode);
				dao.tDelete(tcode);
			}
			mav.setViewName("match/team/list");
			mav.addObject("root", Utility.getRoot()); // /radesk

			mav.setViewName("redirect:/match/team/teamlist.do");
			return mav;
		}// tdelete() end
		
		// �� ��Ī ���
		@RequestMapping("/match/team/ready.do")
		public ModelAndView ready(String tcode, HttpSession session) {
			ModelAndView mav = new ModelAndView();
			int tmatch = dao.tmatch(tcode);
			int mbasket = dao.mbasket(tcode);
			String ccode = dao.cCodeCheck((String) session.getAttribute("s_id"));
			TeamDTO teamLeader = dao.teamLeader(tcode);
			int tmcount = dao.tmCount(tcode);

			mav.addObject("root", Utility.getRoot());
			mav.addObject("teamLeader", teamLeader);
			mav.addObject("ccode", ccode);
			mav.addObject("tcode", tcode);
			mav.addObject("tmcount", tmcount);
			mav.addObject("alreadyjoin", dao.joinIdCheck(tcode, (String) session.getAttribute("s_id")));
			mav.addObject("tmlist", dao.read(teamLeader.getTid(), tcode));
			mav.addObject("player", teamLeader.getPlayer());

			mav.addObject("root", Utility.getRoot()); // /radesk

			mav.setViewName("redirect:/match/team/teamread.do");

			return mav;
		}// tdelete() end
		

}// class end
