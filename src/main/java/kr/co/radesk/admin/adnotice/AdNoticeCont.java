package kr.co.radesk.admin.adnotice;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Paging;
import net.utility.Utility;

@Controller
public class AdNoticeCont {
	@Autowired
	AdNoticeDAO dao;

	public AdNoticeCont() {
		System.out.println("AdnoticeCont() 생성");
	}//AdnoticeCont()
	
	@RequestMapping("/admin/notice/noticelist.do")
	public ModelAndView noticelist(AdNoticeDTO dto, String col, String word, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/notice/noticelist");
		//글갯수
		col = Utility.checkNull(col);
		word = Utility.checkNull(word);
		int recordPerPage=10;
		int totalRecord=dao.count(col, word);
		int nowPage=1;
		if(req.getParameter("nowPage")!=null){
			nowPage=Integer.parseInt(req.getParameter("nowPage"));
		}//if end
		
		String paging = new Paging().paging3(totalRecord, nowPage, recordPerPage, col, word, "./noticelist.do");
		
		mav.addObject("list", dao.list(col,word,nowPage,recordPerPage));
		mav.addObject("paging", paging);
		mav.addObject("root", Utility.getRoot());  //radesk
		return mav;
	}//noticelist() end
	
	@RequestMapping(value="/admin/notice/create.do", method=RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/notice/noticeForm");
		mav.addObject("root", Utility.getRoot());
		return mav;
	}//create() end

	@RequestMapping(value = "/admin/notice/create.do", method = RequestMethod.POST)
    public ModelAndView createProc(String subject, String content) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/msgView");
		mav.addObject("root", Utility.getRoot());
		System.out.println(content);
		int cnt = dao.create(subject, content);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>공지사항 등록 실패</p>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back();'>");
			mav.setViewName("admin/msgView");
		} else {
			mav.setViewName("redirect:/admin/notice/noticelist.do");
		}//if end        
        return mav;
    }//createProc() end
	
	@RequestMapping(value="/admin/notice/read.do", method=RequestMethod.GET)
	public ModelAndView read(int noticeno) {
		ModelAndView mav = new ModelAndView();
		AdNoticeDTO dto = dao.read(noticeno);
		if(dto==null) {
			mav.addObject("msg1", "<p>상세보기 실패</p>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back();'>");
			mav.setViewName("admin/msgView");
		}else {
			mav.setViewName("admin/notice/noticeRead");
			mav.addObject("root", Utility.getRoot());
			mav.addObject("content", Utility.convertChar(dto.getContent()));
			mav.addObject("dto", dto);
		}//if end
		
		return mav;
	}//read() end
	
	@RequestMapping(value="/admin/notice/delete.do", method=RequestMethod.GET)
	public ModelAndView delete(int noticeno) {
		ModelAndView mav = new ModelAndView();
		int res = dao.delete(noticeno);
		if(res==0) {
			mav.addObject("msg1", "<p>공지사항 삭제 실패</p>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back();'>");
			mav.setViewName("admin/msgView");
		}else {
			mav.setViewName("redirect:/admin/notice/noticelist.do");
		}//if end		
		return mav;
	}//delete() end
	
	@RequestMapping(value="/admin/notice/update.do", method=RequestMethod.GET)
	public ModelAndView confirm(int noticeno) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/notice/noticeupdateForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dao.read(noticeno));   //수정관련 정보		
		return mav;
	}//update() end	
	
	
	@RequestMapping(value="/admin/notice/update.do", method=RequestMethod.POST)
	public ModelAndView updateProc(AdNoticeDTO dto) {
		ModelAndView mav = new ModelAndView();
		int res = dao.update(dto);
		if(res==0) {
			mav.addObject("msg1", "<p>공지사항 수정 실패</p>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back();'>");
			mav.setViewName("admin/msgView");
		}else {
			mav.setViewName("redirect:/admin/notice/noticelist.do");
		}
		return mav;
	}//updateProc end
    
}//class end
