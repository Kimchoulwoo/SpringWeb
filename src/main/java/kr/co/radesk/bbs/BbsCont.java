package kr.co.radesk.bbs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.radesk.admin.adnotice.*;
import kr.co.radesk.bbs.*;
import net.utility.*;

@Controller
public class BbsCont {
	@Autowired
	BbsDAO dao;
	
	@Autowired
	AdNoticeDAO notice;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	private int nowPage;
	private String col;
	private String word;

	public BbsCont() {
		System.out.println("BbsCont() 객체 생성 ok");
	}
	
	public int nowPage() {
		this.nowPage = 1;

		if (request.getParameter("nowPage") != null)
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		return nowPage;
	}
	
	public String col() {
		this.col = request.getParameter("col"); // 검색칼럼
		col = Utility.checkNull(col);
		return col;
	}
	
	public String word() {
		this.word = request.getParameter("word"); // 검색어
		word = Utility.checkNull(word);
		return word;
	}

	// 첫 페이지 http://localhost:9090/radesk/bbs/list.do

	// 결과 확인
	// http://localhost:9090/radesk/bbs/create.do
	@RequestMapping(value = "/bbs/create.do", method = RequestMethod.GET)
	public String createForm() {
		return "bbs/createForm"; // /bbs/createForm.jsp
	}

	@RequestMapping(value = "/bbs/create.do", method = RequestMethod.POST)
	/*
	 * 1) public mav createProc(HttpServletRequest req) {}
	 */

	/*
	 * 2) public mav createProc(@RequestParam("title") String title) {}
	 */

	/*
	 * 3) public mav createProc(String title) {}
	 */

	// 게시글 작성
	public ModelAndView createProc(BbsDTO dto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/bbs/list.do");
		String ip = request.getRemoteAddr();
		String id = (String)session.getAttribute("s_id");
		dto.setIp(ip);
		dto.setId(id);

		int res = dao.create(dto);
		mav.addObject("dto", dto);

		return mav;
	}

	// 리스트
	@RequestMapping("/bbs/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bbs/list");
		
		int recordPerPage = 10;
		nowPage(); col(); word();

		int record = dao.count(col, word);		
		int boardcount = dao.boardcount();
		String today = Utility.getDate();
		String paging = new Paging().paging3(record, nowPage, recordPerPage, col, word, "./list.do");

		// mav.addObject("list", dao.list());
		mav.addObject("bbslist", dao.list(col, word, nowPage, recordPerPage));
		mav.addObject("noticelist", notice.list());
		mav.addObject("boardcount", boardcount);
		mav.addObject("root", Utility.getRoot()); // /radesk
		mav.addObject("col", col);
		mav.addObject("word", word);
		mav.addObject("nowPage", nowPage);
		mav.addObject("paging", paging);
		mav.addObject("today", today);
		
		return mav;
	}

	// 상세 조회
	@RequestMapping("/bbs/read.do")
	public ModelAndView read(int bbsno) { // 또는 dto, req
		ModelAndView mav = new ModelAndView();
		dao.incrementCnt(bbsno);
		BbsDTO dto = dao.read(bbsno);

		mav.addObject("root", Utility.getRoot());
		mav.setViewName("bbs/read"); // /bbs/read.jsp
		mav.addObject("dto", dto);
		mav.addObject("col", col);
		mav.addObject("word", word);
		mav.addObject("nowPage", nowPage);
		
		return mav;
	}
	
	@RequestMapping("/bbs/noticeread.do")
	public ModelAndView noticeRead(int noticeno) { // 또는 dto, req
		ModelAndView mav = new ModelAndView();
		notice.incrementCnt(noticeno);
		AdNoticeDTO ntc = notice.read(noticeno);
		
		mav.setViewName("bbs/noticeread"); // /bbs/noticeread.jsp
		mav.addObject("ntc", ntc);

		return mav;
	}

	@RequestMapping("/bbs/delete.do")
	public ModelAndView deleteProc(BbsDTO dto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int res = dao.delete(dto);

		mav.setViewName("redirect:/bbs/list.do");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("col", this.col);
		mav.addObject("word", this.word);
		mav.addObject("nowPage", this.nowPage);

		return mav;
	}

	@RequestMapping(value="/bbs/updateform.do", method=RequestMethod.GET)
	public ModelAndView updateForm(BbsDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());
		mav.addObject("col", this.col);
		mav.addObject("word", this.word);
		mav.addObject("nowPage", this.nowPage);
		mav.addObject("dto", dao.read(dto.getBbsno()));

		mav.setViewName("bbs/updateForm");

		return mav;
	}

	@RequestMapping(value="/bbs/update.do", method=RequestMethod.POST)
	public ModelAndView updateProc(BbsDTO dto) {
		ModelAndView mav = new ModelAndView();
		String ip = request.getRemoteAddr();
		dto.setIp(ip);

		int res = dao.update(dto);

		mav.setViewName("redirect:/bbs/read.do?bbsno=" + dto.getBbsno());
		mav.addObject("col", this.col);
		mav.addObject("word", this.word);
		mav.addObject("nowPage", this.nowPage);

		return mav;
	}

	// 답변 작성
	@RequestMapping(value = "/bbs/reply.do", method = RequestMethod.GET)
	public ModelAndView replyForm(BbsDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bbs/reply");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dao.read(dto.getBbsno()));
		mav.addObject("col", this.col);
		mav.addObject("word", this.word);
		mav.addObject("nowPage", this.nowPage);

		return mav; // /bbs/reply.jsp
	}
	
	@RequestMapping(value = "/bbs/reply.do", method = RequestMethod.POST)
	public ModelAndView replyProc(BbsDTO dto, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/bbs/list.do");
		mav.addObject("col", this.col);
		mav.addObject("word", this.word);
		mav.addObject("nowPage", this.nowPage);
		
		String ip = request.getRemoteAddr();
		dto.setIp(ip);
		String id = (String)session.getAttribute("s_id");
		dto.setId(id);

		int res = dao.reply(dto);

		return mav;
	}
}
