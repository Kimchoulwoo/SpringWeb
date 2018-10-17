package kr.co.radesk.stadiumrating;

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
import kr.co.radesk.basket.BasketDTO;
import kr.co.radesk.admin.adground.AdgroundDAO;
import kr.co.radesk.admin.adground.AdgroundDTO;
import kr.co.radesk.member.MemberDTO;
import net.utility.Paging;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class StadiumRatingCont {
	@Autowired
	StadiumRatingDAO dao;

	@Autowired
	AdgroundDAO adgdao;

	private ServletRequest req;

	// --------------------------------------------------------------------------
	// �⺻ ������
	public StadiumRatingCont() {
		System.out.println("���� ���� CONT ���� �Ϸ�");
	}// AdnoticeCont()
		// ����

	// ���� �� �������� �̵�

	@RequestMapping("/stadium/rating/ratingForm.do")
	public ModelAndView read(String pcode) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot());

		mav.setViewName("/stadium/rating/ratingForm");
		mav.addObject("paymentinfo", dao.ratingcallpaymentinfo(pcode));
		mav.addObject("stadiuminfo", dao.callstadiuminfo());

		return mav;
	}

	@RequestMapping(value = "/stadium/rating/ratingcreate.do", method = RequestMethod.POST)
	public ModelAndView createProc(StadiumRatingDTO dto, String pcode, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stadium/rating/msgView");
		mav.addObject("root", Utility.getRoot());
		int res = dao.ratingcreate(dto, pcode);
		if (res == 0) {
			mav.addObject("msg1", "<p>���� �� ����</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick='javascript:history.back();'>");
			mav.addObject("link2",
					"<input type='button' value='���� ����Ʈ' onclick='location.href=\"../payment/paylist.do\"'>");
		} else {
			mav.setViewName("redirect:../../index.do");
		}

		return mav;
	}
	

	
	

}// class end
