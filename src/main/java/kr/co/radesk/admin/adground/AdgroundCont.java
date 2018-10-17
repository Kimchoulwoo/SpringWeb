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
		System.out.println("����� ���� cont ����");
	}

	// --------------------------------------------------------------------------

	// ����� �Է� ������ �̵�
	@RequestMapping(value = "/admin/ground/groundForm.do", method = RequestMethod.GET)
	public ModelAndView groundForm(AdgroundDTO dto,AdStadiumDTO stadiumdto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/ground/groundForm");
		mav.addObject("stacode", dto.getStacode());
		
		//stadium���� stacode�� ���� staopen,staclose ��������
		mav.addObject("stadiumdto", stadiumdto);
		
		
		
		mav.addObject("root", Utility.getRoot());
		
		
		
		
		return mav;
	}

	// ------------------------------------------------------------------------------------
	// ����� ���
	@RequestMapping(value = "admin/ground/adcreate.do", method = RequestMethod.POST)
	public ModelAndView createProc(AdgroundDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/msgView");
		mav.addObject("root", Utility.getRoot());
		System.out.println(dto);
		int cnt = dao.create(dto);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>����� ��� ����</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
		} else {
			mav.addObject("msg1", "<p>����� ��� ����</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link1",
					"<input type='button' value='���� ���' onclick='location.href=\"../stadium/stadiumlist.do\"'>");

		} // if end

		return mav;
	}// createProc() end
	// ------------------------------------------------------------------------------------

	// ����� ����Ʈ�� ���� CONT�ܿ� �ۼ���

	// ����� �����ϱ� ���� ����Ʈ
	@RequestMapping("admin/ground/groundlist.do")
	public ModelAndView UDlist(int stacode) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("root", Utility.getRoot()); // /mymelon
		mav.setViewName("admin/ground/groundlist");
		mav.addObject("grlist", dao.Updatelist(stacode));
		return mav;
	}

	    // ------------------------------------------------------------------------------------------------
	// ���� �������� �̵�
	@RequestMapping(value = "/admin/ground/groundupdateForm.do", method = RequestMethod.GET)
	public ModelAndView staread(int grcode,AdStadiumDTO stadiumdto,int stacode) { // �Ǵ� dto, req ��� ����
		ModelAndView mav = new ModelAndView();
		AdgroundDTO dto = dao.grread(grcode);

		if (dto != null) {
			mav.setViewName("/admin/ground/groundupdateForm");

		} // if end
		mav.addObject("groundlist", dao.list(grcode));
		mav.addObject("root", Utility.getRoot());
		mav.addObject("grUdto", dto);

		//stadium���� stacode�� ���� staopen,staclose ��������
		mav.addObject("stadiuminfo", dao.stadiuminfo(stacode));

		
		return mav;
	}// read() end

	// ���� ����
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
			mav.addObject("msg1", "<p>����� ���� ����</p>");
			mav.addObject("img", "<img src='../../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick='javascript:history.back();'>");
			mav.addObject("link2",
					"<input type='button' value='����� ���' onclick='location.href=\"../stadium/stadiumlist.do\"'>");
		} else {
			mav.addObject("msg1", "<p>����� ���� ����</p>");
			mav.addObject("img", "<img src='../../images/sound.png'>");
			mav.addObject("link1",
					"<input type='button' value='���� ���' onclick='location.href=\"../stadium/stadiumlist.do\"'>");
		}

		return mav;
	}

	
	//----------------------------------------------------------------------------------------------

	// ���� ����
	@RequestMapping(value = "/admin/ground/grounddelete.do", method = RequestMethod.GET)
	public ModelAndView deleteProc(AdgroundDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:../stadium/stadiumlist.do");
		mav.addObject("root", Utility.getRoot());

		 dao.grdelete(dto);

		return mav;
	}
}
	
	