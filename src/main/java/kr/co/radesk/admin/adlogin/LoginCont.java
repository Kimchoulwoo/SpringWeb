package kr.co.radesk.admin.adlogin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginCont {
	
	@Autowired
	private LoginDAO dao;

	public LoginCont() {
		System.out.println("LoginCont() 객체 생성");
	}//LoginCont() end
	
	//결과확인
	//http://localhost:9090/radesk/admin/login.do
	
	@RequestMapping(value="/admin/login.do", method=RequestMethod.GET)
	public ModelAndView loginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/loginForm");      //   admin/loginForm.jsp
		return mav;   
	}//loginForm() end

	@RequestMapping(value="/admin/login.do", method=RequestMethod.POST)
	public ModelAndView loginProc(String id, String pw, HttpSession session){
		
		ModelAndView mav = new ModelAndView();
		String mlevel = dao.login(id, pw);
		
		if(mlevel==null) {  //로그인 실패 시
			mav.addObject("msg1", "Id와 Password를 확인해주세요");
			mav.addObject("msg2", "(관리자, 구장주만 로그인 가능합니다.)");
			mav.addObject("link1", "<input type='button' value='다시 시도' onclick='location.href=\"./login.do\"' class=\"button\">");
			mav.setViewName("admin/msgView");	
		}else if(mlevel.equals("A")) {
			//session 영역
			mlevel="관리자";
			session.setAttribute("s_id", id);
			session.setAttribute("s_pw", pw);
			session.setAttribute("s_mlevel", mlevel);
			
			mav.setViewName("redirect:/admin/admin.jsp");
		}else if(mlevel.equals("B")) {
			//session 영역
			mlevel="구장주";
			session.setAttribute("s_id", id);
			session.setAttribute("s_pw", pw);
			session.setAttribute("s_mlevel", mlevel);
			
			mav.setViewName("redirect:/admin/admin.jsp");
		}//if end
		
		return mav;
	}//loginProc() end	
	
	@RequestMapping("admin/logout.do")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.removeAttribute("s_id");
		session.removeAttribute("s_pw");
		session.removeAttribute("s_mlevel");
		
		mav.setViewName("redirect:/admin/login.do");
		
		return mav;
	}//logout() end

}//class end
