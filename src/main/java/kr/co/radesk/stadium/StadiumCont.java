package kr.co.radesk.stadium;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import kr.co.radesk.admin.adground.AdgroundDAO;
import kr.co.radesk.admin.adground.AdgroundDTO;
import kr.co.radesk.admin.adstadium.AdStadiumDAO;
import kr.co.radesk.admin.adstadium.AdStadiumDTO;
import kr.co.radesk.payment.PaymentDAO;
import kr.co.radesk.payment.PaymentDTO;
import kr.co.radesk.stadiumrating.StadiumRatingDAO;
import kr.co.radesk.stadiumrating.StadiumRatingDTO;
import net.utility.Paging;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class StadiumCont {
	@Autowired
	AdStadiumDAO dao;

	@Autowired
	AdgroundDAO adgdao;

	@Autowired
	StadiumDAO RVdao;
	
	@Autowired
	PaymentDAO paydao;
	
	@Autowired
	StadiumRatingDAO ratingdao;

	private ServletRequest req;

	// --------------------------------------------------------------------------
	// 기본 생성자
	public StadiumCont() {
		System.out.println("stadium 생성 완료");
	}// AdnoticeCont()

	// 리스트--------------------------------------------------------------------------
	@RequestMapping("/stadium/stadium/stalist.do")
	public ModelAndView list(AdStadiumDTO dto, String col, String word, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();

		col = Utility.checkNull(col);
		word = Utility.checkNull(word);
		int recordPerPage=3;
		int totalRecord=dao.count(col, word);
		int nowPage=1;
		if(req.getParameter("nowPage")!=null){
			nowPage=Integer.parseInt(req.getParameter("nowPage"));
		}//if end
		
		String paging = new Paging().paging3(totalRecord, nowPage, recordPerPage, col, word, "./stalist.do");
		
		mav.addObject("list", dao.list(dto,col,word,nowPage,recordPerPage));
		mav.addObject("paging", paging);
		mav.addObject("root", Utility.getRoot()); // /mymelon
		mav.setViewName("stadium/stadium/stalist");

		return mav;
	}

	// 구장 상세보기 및 구장에 대한 경기장
	@RequestMapping(value = "/stadium/stadium/grounddate.do", method = RequestMethod.POST)
	public ModelAndView groundDate(int grcode, int stacode, HttpServletRequest req) { // 또는 dto, req 사용 가능
		ModelAndView mav = new ModelAndView();
		AdgroundDTO dto = RVdao.grRVread(grcode);
		AdStadiumDTO stadto =  RVdao.staRVread(stacode);
		String date = "";
		date += req.getParameter("year");
		date += req.getParameter("month");
		String day = req.getParameter("day");
		if(day.length()<2) {
			day="0"+day;
		}
		date += day;

		ArrayList<PaymentDTO> ptimelist = paydao.dateRead(stacode, grcode, date);

		if (ptimelist == null) {
			mav.addObject("grRVlist", dto);
		} else {
			String[] grtime = dto.getGrtime().split(",");//11,13,15
			String iposstime = "";
			for (int idx = 0; idx < ptimelist.size(); idx++) {
				PaymentDTO paymentDTO = ptimelist.get(idx);
				String str = paymentDTO.getPtime();
				String[] ptime = str.split(",");
				
				for (int i = 0; i < ptime.length; i++) {
					iposstime += ptime[i];
					iposstime += ",";
				}
			}//for end
			String[] ipt = iposstime.substring(0, iposstime.length() - 1).split(",");
			String possible = "";
			for (int i = 0; i < grtime.length; i++) {
				boolean flag = false;
				for (int j = 0; j < ipt.length; j++) {
					if (grtime[i].equals(ipt[j])) {
						flag = true;
						break;
					}
				}
				if (flag == false) {
					possible += grtime[i];
					possible += ",";
				}
			}

			if (possible == "") {
				dto.setGrtime(null);
			} else {
				dto.setGrtime(possible.substring(0, possible.length() - 1));
			}
			mav.addObject("grRVlist", dto);
		}//if end

		mav.setViewName("/stadium/stadium/groundDate");

		mav.addObject("year", req.getParameter("year"));
		mav.addObject("month", req.getParameter("month"));
		mav.addObject("day", req.getParameter("day"));

		mav.addObject("staRVlist", stadto);
		mav.addObject("root", Utility.getRoot());
		
		return mav;
	}// read() end
	
	// 리드-----------------------------------------------------------------------------------
		@RequestMapping(value = "/stadium/stadium/staread.do", method = RequestMethod.GET)
		public ModelAndView staread(int stacode) { // 또는 dto, req 사용 가능
			ModelAndView mav = new ModelAndView();
			AdStadiumDTO dto = dao.read(stacode);

			if (dto != null) {
				mav.setViewName("/stadium/stadium/staread");

			} // if end
			mav.addObject("groundlist", adgdao.list(stacode));
			mav.addObject("root", Utility.getRoot());
			mav.addObject("stareaddto", dto);
			mav.addObject("totalpoint",RVdao.totalpoint(stacode));
			return mav;
		}// read() end
	
}// class end
