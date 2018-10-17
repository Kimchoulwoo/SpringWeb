package kr.co.radesk.admin.admember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Paging;
import net.utility.Utility;

@Controller
public class AdMemberCont {

	public AdMemberCont() {
		System.out.println("AdMemberCont() 생성");
	}//MemberCont() end
	
	@Autowired
	AdMemberDAO dao;
	
	//결과확인
	//http://localhost:9090/radesk/admin/member/memlist.do
	
	@RequestMapping("/admin/member/memlist.do")
	public ModelAndView list(AdMemberDTO dto, String col, String word, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		col = Utility.checkNull(col);
		word = Utility.checkNull(word);
		int recordPerPage=10;
		int totalRecord=dao.count(col, word);
		int nowPage=1;
		if(req.getParameter("nowPage")!=null){
			nowPage=Integer.parseInt(req.getParameter("nowPage"));
		}//if end
		
		String paging = new Paging().paging3(totalRecord, nowPage, recordPerPage, col, word, "./memlist.do");
		
		mav.addObject("list", dao.list(col,word,nowPage,recordPerPage));
		mav.addObject("paging", paging);
		mav.addObject("root", Utility.getRoot());  //radesk
		return mav;
	}//list() end
		
	@RequestMapping(value="/admin/member/memLevelProc.do", method=RequestMethod.GET)
	public ModelAndView memLevelProc(AdMemberDTO dto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int res= dao.memLevelProc(dto);
		if(res==1) {
			mav.setViewName("redirect:/admin/member/memlist.do");
		}else {

			mav.addObject("msg1", "등급 변경에 실패하였습니다.");
			mav.addObject("link1", "<input type='button' value='다시 시도' onclick='location.href=\"./memlist.do\"' class=\"button\">");
			mav.setViewName("admin/msgView");
		}
		return mav;
	}//memLevelProc() end
	
	@RequestMapping(value="/admin/member/memDelProc.do", method=RequestMethod.GET)
	public ModelAndView memDeleteProc(AdMemberDTO dto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int res= dao.memDelProc(dto);
		if(res==1) {
			mav.setViewName("redirect:/admin/member/memlist.do");
		}else {

			mav.addObject("msg1", "회원 삭제 실패하였습니다.");
			mav.addObject("link1", "<input type='button' value='다시 시도' onclick='location.href=\"./memlist.do\"' class=\"button\">");
			mav.setViewName("admin/msgView");
		}
		
		return mav;
	}
	

}//class end
