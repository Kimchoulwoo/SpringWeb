package kr.co.radesk.basket;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import kr.co.radesk.admin.adground.AdgroundDAO;
import kr.co.radesk.admin.adground.AdgroundDTO;
import kr.co.radesk.admin.adstadium.AdStadiumDAO;
import kr.co.radesk.admin.adstadium.AdStadiumDTO;
import kr.co.radesk.stadium.StadiumDAO;
/*import net.utility.Paging;*/
import net.utility.Utility;

@Controller
public class BasketCont {
	@Autowired
	BasketDAO dao;
	BasketDTO dto;
	@Autowired
	StadiumDAO RVdao;
	AdStadiumDAO stadao2;
	StadiumDAO grdao2;

	public BasketCont() {
		System.out.println("경기장 관리 cont 생성");
	}

	// --------------------------------------------------------------------------
	//홈에서 바로 가는 결제 리스트    

	
	//경기장 예약을 위한 경기장 상세보기 페이지
	@RequestMapping(value = "/stadium/stadium/groundRV.do", method = RequestMethod.GET)
	public ModelAndView RVread(int grcode,int stacode) { // 또는 dto, req 사용 가능
		ModelAndView mav = new ModelAndView();
		AdgroundDTO dto = RVdao.grRVread(grcode);
		AdStadiumDTO stadto =  RVdao.staRVread(stacode);

		if (dto != null) {
			mav.setViewName("/stadium/stadium/groundRV");

		} // if end
		mav.addObject("grRVlist", dto);
		mav.addObject("root", Utility.getRoot());
		mav.addObject("staRVlist", stadto);
		return mav;
	}// read() end
	// ------------------------------------------------------------------------------------
	// 장바구니 등록
	@RequestMapping(value = "/stadium/basket/basketRV.do", method = RequestMethod.GET)
	public ModelAndView createProc(BasketDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());
		
		String pdate="";
		String pyear=req.getParameter("pyear");
		String pmonth=req.getParameter("pmonth");
		String pday=req.getParameter("pday");
		if(pday.length()<2) {
			pday="0"+pday;
		}
		pdate=pyear+pmonth+pday;
		dto.setPdate(pdate);
		
		int cnt = dao.create(dto);
		if (cnt == 0) {
			mav.setViewName("stadium/basket/fail");
		} else {
			mav.setViewName("stadium/basket/ok");
			mav.addObject("stacode", req.getParameter("stacode"));
			mav.addObject("grcode", req.getParameter("grcode"));
		} // if end

		return mav;
	}
		// ------------------------------------------------------------------------------------

	//장바구니 리스트
	@RequestMapping(value = "stadium/basket/basketlist.do", method = RequestMethod.GET)
	public ModelAndView list(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot()); // /mymelon
		mav.setViewName("stadium/basket/basketlist");
		mav.addObject("baslist", dao.basketlist((String)session.getAttribute("s_id")));
		//mav.addObject("namelist", dao.basketlist());
		mav.addObject("stadiuminfo", dao.callstadiuminfo());
		mav.addObject("groundinfo", dao.callgroundinfo());
		return mav;
	}
	
	
	@RequestMapping(value = "/stadium/basket/basketread.do", method = RequestMethod.GET)
	public ModelAndView staread(int bno) { // 또는 dto, req 사용 가능
		ModelAndView mav = new ModelAndView();
		BasketDTO dto = dao.basketread(bno);

		if (dto != null) {
			mav.setViewName("stadium/basket/basketread");

		} // if end
		
		mav.addObject("root", Utility.getRoot());
		mav.addObject("Basketdto", dto);
		System.out.println("dto : "+dto);
		return mav;
	}
	



}// class end
