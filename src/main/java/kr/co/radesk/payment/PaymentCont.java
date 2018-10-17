package kr.co.radesk.payment;

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

import kr.co.radesk.admin.adstadium.AdStadiumDTO;
import kr.co.radesk.basket.BasketDTO;
import kr.co.radesk.admin.adground.AdgroundDAO;
import kr.co.radesk.admin.adground.AdgroundDTO;
import kr.co.radesk.member.MemberDTO;
import net.utility.Paging;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class PaymentCont {
	@Autowired
	PaymentDAO dao;

	@Autowired
	AdgroundDAO adgdao;
	
	

	private ServletRequest req;

	

	//결제 하는 페이지(장바구니 상세보기에서 결제하여 넘어가는거)
	@RequestMapping(value = "/stadium/payment/paymentplay.do", method = RequestMethod.POST)
	public ModelAndView createProc(BasketDTO dto, HttpServletRequest req,int bno,String pid) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());
		int cnt = dao.create(dto,bno,pid);
		mav.setViewName("stadium/payment/paymentplay");
		return mav;
	}
	
	//결제한거 리스트
	@RequestMapping(value = "/stadium/payment/paymentlist.do", method = RequestMethod.GET)
	public ModelAndView list(String pid, HttpSession session,BasketDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot()); // /mymelon
		mav.setViewName("stadium/payment/paymentlist");

		ArrayList<PaymentDTO> plist = dao.paymentlist((String)session.getAttribute("s_id"));
		
		mav.addObject("paymentlist", plist);
		mav.addObject("stadiuminfo", dao.callstadiuminfo());
		mav.addObject("groundinfo", dao.callgroundinfo());
		return mav;
	}
	
	
	




	// ------------------------------------------------------------------------
	}// class end
