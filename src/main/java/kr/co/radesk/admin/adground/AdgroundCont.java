package kr.co.radesk.admin.adground;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

/*import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;*/
import org.springframework.web.servlet.ModelAndView;

import kr.co.radesk.admin.adground.AdgroundDTO;
import kr.co.radesk.admin.adstadium.AdStadiumDTO;
/*import net.utility.Paging;*/
import net.utility.Utility;

@Controller
public class AdgroundCont {
	@Autowired
	AdgroundDAO dao;
	AdgroundDTO dto;

	public AdgroundCont() {
		System.out.println("경기장 관리 cont 생성");
	}

	// --------------------------------------------------------------------------

	// 경기장 입력 폼으로 이동
	@RequestMapping(value = "/admin/ground/groundForm.do", method = RequestMethod.GET)
	public ModelAndView groundForm(AdgroundDTO dto,AdStadiumDTO stadiumdto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/ground/groundForm");
		mav.addObject("stacode", dto.getStacode());
		
		//stadium에서 stacode에 따른 staopen,staclose 가져오기
		mav.addObject("stadiumdto", stadiumdto);
		
		
		
		mav.addObject("root", Utility.getRoot());
		
		
		
		
		return mav;
	}

	// ------------------------------------------------------------------------------------
	// 경기장 등록
	@RequestMapping(value = "admin/ground/adcreate.do", method = RequestMethod.POST)
	public ModelAndView createProc(AdgroundDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/msgView");
		mav.addObject("root", Utility.getRoot());
		System.out.println(dto);
		int cnt = dao.create(dto);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>경기장 등록 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
		} else {
			mav.addObject("msg1", "<p>경기장 등록 성공</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link1",
					"<input type='button' value='구장 목록' onclick='location.href=\"../stadium/stadiumlist.do\"'>");

		} // if end

		return mav;
	}// createProc() end
	// ------------------------------------------------------------------------------------

	// 경기장 리스트는 구장 CONT단에 작성함

	// 경기장 수정하기 위한 리스트
	@RequestMapping("admin/ground/groundlist.do")
	public ModelAndView UDlist(int stacode) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot()); // /mymelon
		mav.setViewName("admin/ground/groundlist");
		mav.addObject("grlist", dao.Updatelist(stacode));
		return mav;
	}

	    // ------------------------------------------------------------------------------------------------
	// 수정 페이지로 이동
	@RequestMapping(value = "/admin/ground/groundupdateForm.do", method = RequestMethod.GET)
	public ModelAndView staread(int grcode,AdStadiumDTO stadiumdto,int stacode) { // 또는 dto, req 사용 가능
		ModelAndView mav = new ModelAndView();
		AdgroundDTO dto = dao.grread(grcode);

		if (dto != null) {
			mav.setViewName("/admin/ground/groundupdateForm");

		} // if end
		mav.addObject("groundlist", dao.list(grcode));
		mav.addObject("root", Utility.getRoot());
		mav.addObject("grUdto", dto);

		//stadium에서 stacode에 따른 staopen,staclose 가져오기
		mav.addObject("stadiuminfo", dao.stadiuminfo(stacode));

		
		return mav;
	}// read() end

	// 수정 시작
	@RequestMapping(value = "/admin/ground/grupdate.do", method = RequestMethod.POST)
	public ModelAndView grupdateProc(AdgroundDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/msgView");
		mav.addObject("root", Utility.getRoot());

		System.out.println(dto.getGrcode());
		System.out.println(dto.getStacode());
		System.out.println(dto.getGrname());
		System.out.println(dto.getGrday());
		System.out.println(dto.getGrtime());
		System.out.println(dto.getGrlevel());
		int cnt = dao.grupdate(dto);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>경기장 수정 실패</p>");
			mav.addObject("img", "<img src='../../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시 시도' onclick='javascript:history.back();'>");
			mav.addObject("link2",
					"<input type='button' value='경기장 목록' onclick='location.href=\"../stadium/stadiumlist.do\"'>");
		} else {
			mav.addObject("msg1", "<p>경기장 수정 성공</p>");
			mav.addObject("img", "<img src='../../images/sound.png'>");
			mav.addObject("link1",
					"<input type='button' value='구장 목록' onclick='location.href=\"../stadium/stadiumlist.do\"'>");
		}

		return mav;
	}

	
	//----------------------------------------------------------------------------------------------

	// 삭제 시작
	@RequestMapping(value = "/admin/ground/grounddelete.do", method = RequestMethod.GET)
	public ModelAndView deleteProc(AdgroundDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:../stadium/stadiumlist.do");
		mav.addObject("root", Utility.getRoot());

		 dao.grdelete(dto);

		return mav;
	}
}
	
	