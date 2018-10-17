package kr.co.radesk.admin.adstadium;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.radesk.admin.adstadium.AdStadiumDTO;
import kr.co.radesk.admin.adground.AdgroundDAO;
import kr.co.radesk.admin.adground.AdgroundDTO;
import kr.co.radesk.member.MemberDTO;
import net.utility.Paging;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class AdStadiumCont {
	@Autowired
	AdStadiumDAO dao;

	@Autowired
	AdgroundDAO adgdao;

	private ServletRequest req;

	// --------------------------------------------------------------------------
	// 기본 생성자
	public AdStadiumCont() {
		System.out.println("구장 관리 CONT 생성 완료");
	}// AdnoticeCont()
		// 구장
		// 등록---------------------------------------------------------------------------

	@RequestMapping(value = "/admin/stadium/stadiumForm.do", method = RequestMethod.GET)
	public ModelAndView createForm(AdStadiumDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/stadium/stadiumForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("stacode", dto.getStacode());
		return mav;
	}// createForm() end

	@RequestMapping(value = "/admin/stadium/create.do", method = RequestMethod.POST)
	public ModelAndView createProc(AdStadiumDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/msgView");
		mav.addObject("root", Utility.getRoot());
		// -------------------------------------------------------------
		// 전송된 파일이 저장되는 실제 경로
		String basePath = req.getRealPath("admin/stadium/storage");

		// 1) <input type='file' name='posterMF'>
		MultipartFile posterMF1 = dto.getPosterMF1();
		String poster1 = UploadSaveManager.saveFileSpring30(posterMF1, basePath);
		MultipartFile posterMF2 = dto.getPosterMF2();
		String poster2 = UploadSaveManager.saveFileSpring30(posterMF2, basePath);
		MultipartFile posterMF3 = dto.getPosterMF3();
		String poster3 = UploadSaveManager.saveFileSpring30(posterMF3, basePath);
		MultipartFile posterMF4 = dto.getPosterMF4();
		String poster4 = UploadSaveManager.saveFileSpring30(posterMF4, basePath);
		MultipartFile posterMF5 = dto.getPosterMF5();
		String poster5 = UploadSaveManager.saveFileSpring30(posterMF5, basePath);
		
		dto.setPoster1(poster1); // 파일명
		dto.setPoster2(poster2); // 파일명
		dto.setPoster3(poster3); // 파일명
		dto.setPoster4(poster4); // 파일명
		dto.setPoster5(poster5); // 파일명
		// ------------------------------------------------------------
		int cnt = dao.create(dto);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>구장 등록 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시 시도' onclick='javascript:history.back();'>");
			mav.addObject("link2", "<input type='button' value='구장 목록' onclick='location.href=\"./stadiumlist.do\"'>");
		} else {
			mav.setViewName("redirect:/admin/stadium/stadiumlist.do");
		}

		return mav;
	}

	// 구장
	// 리스트--------------------------------------------------------------------------

	@RequestMapping("admin/stadium/stadiumlist.do")
	public ModelAndView list(AdStadiumDTO dto, String col, String word, HttpServletRequest req,HttpSession session) {
		ModelAndView mav = new ModelAndView();

		col = Utility.checkNull(col);
		word = Utility.checkNull(word);
		int recordPerPage=2;
		int totalRecord=dao.count(col, word);
		int totalRecord2 = dao.count2(col, word,  (String)session.getAttribute("s_id"));
		int nowPage=1;
		if(req.getParameter("nowPage")!=null){
			nowPage=Integer.parseInt(req.getParameter("nowPage"));
		}//if end
		
		String paging = new Paging().paging3(totalRecord, nowPage, recordPerPage, col, word, "./stadiumlist.do");
		String paging2 = new Paging().paging3(totalRecord2, nowPage, recordPerPage, col, word, "./stadiumlist.do");
		
		mav.addObject("list", dao.list(dto,col,word,nowPage,recordPerPage));
		mav.addObject("paging", paging);
		mav.addObject("paging2", paging2);
		mav.addObject("root", Utility.getRoot()); // /mymelon
		mav.setViewName("admin/stadium/stadiumlist");

		return mav;
	}

	// 상세보기
	// ------------------------------------------------------------------------------
	@RequestMapping(value = "/admin/stadium/stadiumread.do", method = RequestMethod.GET)
	public ModelAndView staread(int stacode) { // 또는 dto, req 사용 가능
		ModelAndView mav = new ModelAndView();
		AdStadiumDTO dto = dao.read(stacode);

		if (dto != null) {
			mav.setViewName("/admin/stadium/stadiumread");

		} // if end
		mav.addObject("groundlist", adgdao.list(stacode));
		mav.addObject("root", Utility.getRoot());
		mav.addObject("stareaddto", dto);
		return mav;
	}// read() end
	
	// 구장 이용자 집계
		@RequestMapping(value = "/admin/stadium/ToRe.do", method = RequestMethod.GET)
		public ModelAndView tore(int stacode) { // 또는 dto, req 사용 가능
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/admin/stadium/tore");
			
			mav.addObject("root", Utility.getRoot());
			mav.addObject("list", dao.tore(stacode));

			return mav;
		}// read() end
	
		// 수정 및
		// 삭제--------------------------------------------------------------------------------



	// ------------------------------------------------------------------------
	// 수정 페이지로 이동
	@RequestMapping(value = "/admin/stadium/stadiumUpdateForm.do", method = RequestMethod.GET)
	public ModelAndView updateForm(int stacode) { // 또는 dto, req 사용 가능
		ModelAndView mav = new ModelAndView();
		AdStadiumDTO dto = dao.read(stacode);
		if (dto != null) {
			mav.setViewName("/admin/stadium/stadiumUpdateForm");
		} // if end
		mav.addObject("root", Utility.getRoot());
		mav.addObject("stareaddto", dto);
		return mav;
	}// read() end

	// 수정 시작
	@RequestMapping(value = "/admin/stadium/update.do", method = RequestMethod.POST)
	public ModelAndView updateProc(AdStadiumDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/msgView");
		mav.addObject("root", Utility.getRoot());

		AdStadiumDTO oldDTO = dao.read(dto.getStacode());
		//--------------------------------------------------------
		String basePath =req.getRealPath("/admin/stadium/storage");

		// 파일을 수정할 것인지?
	    //1)
	    MultipartFile posterMF1 = dto.getPosterMF1();
	    MultipartFile posterMF2 = dto.getPosterMF2();
	    MultipartFile posterMF3 = dto.getPosterMF3();
	    MultipartFile posterMF4 = dto.getPosterMF4();
	    MultipartFile posterMF5 = dto.getPosterMF5();
	    
	    if(posterMF1.getSize()>0){ // 수정할 파일이 존재하는 경우
	      // 기존 파일 삭제
	      UploadSaveManager.deleteFile(basePath, oldDTO.getPoster1());
	      UploadSaveManager.deleteFile(basePath, oldDTO.getPoster2());
	      UploadSaveManager.deleteFile(basePath, oldDTO.getPoster3());
	      UploadSaveManager.deleteFile(basePath, oldDTO.getPoster4());
	      UploadSaveManager.deleteFile(basePath, oldDTO.getPoster5());
	      
	      // 신규 파일 저장
	      String poster1 = UploadSaveManager.saveFileSpring30(posterMF1, basePath);
	      String poster2 = UploadSaveManager.saveFileSpring30(posterMF2, basePath);
	      String poster3 = UploadSaveManager.saveFileSpring30(posterMF3, basePath);
	      String poster4 = UploadSaveManager.saveFileSpring30(posterMF4, basePath);
	      String poster5 = UploadSaveManager.saveFileSpring30(posterMF5, basePath);
	      
	      dto.setPoster1(poster1);
	      dto.setPoster2(poster2);
	      dto.setPoster3(poster3);
	      dto.setPoster4(poster4);
	      dto.setPoster5(poster5);
	    }
	    else {
	      dto.setPoster1(oldDTO.getPoster1()); // 기존 파일명 재사용
	      dto.setPoster2(oldDTO.getPoster2()); // 기존 파일명 재사용
	      dto.setPoster3(oldDTO.getPoster3()); // 기존 파일명 재사용
	      dto.setPoster4(oldDTO.getPoster4()); // 기존 파일명 재사용
	      dto.setPoster5(oldDTO.getPoster5()); // 기존 파일명 재사용
	      
	    }

		
		int cnt = dao.staupdate(dto);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>구장 수정 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시 시도' onclick='javascript:history.back();'>");
			mav.addObject("link2", "<input type='button' value='구장 목록' onclick='location.href=\"./stadiumlist.do\"'>");
		} else {
			mav.setViewName("redirect:/admin/stadium/stadiumlist.do");
		}

		return mav;
	}// deleteProc() end

	// -------------------------------------------------------------------------

	// 삭제 시작
	@RequestMapping(value = "/admin/stadium/stadiumdelete.do", method = RequestMethod.GET)
	public ModelAndView deleteProc(AdStadiumDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/msgView");
		mav.addObject("root", Utility.getRoot());
		AdStadiumDTO oldDTO = dao.read(dto.getStacode());
		int cnt = dao.stadelete(dto);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>구장 삭제 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시 시도' onclick='javascript:history.back();'>");
			mav.addObject("link2", "<input type='button' value='구장 목록' onclick='location.href=\"./stadiumlist.do\"'>");
		} else {
			
			  //관련 파일 삭제
		      String basePath = req.getRealPath("/admin/stadium/storage");
		      UploadSaveManager.deleteFile(basePath, oldDTO.getPoster1());
		      UploadSaveManager.deleteFile(basePath, oldDTO.getPoster2());
		      UploadSaveManager.deleteFile(basePath, oldDTO.getPoster3());
		      UploadSaveManager.deleteFile(basePath, oldDTO.getPoster4());
		      UploadSaveManager.deleteFile(basePath, oldDTO.getPoster5());
			
			mav.setViewName("redirect:/admin/stadium/stadiumlist.do");
		}

		return mav;
	}
}// class end
