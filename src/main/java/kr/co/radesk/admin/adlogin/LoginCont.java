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
		System.out.println("LoginCont() ��ü ����");
	}//LoginCont() end
	
	//���Ȯ��
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
		
		if(mlevel==null) {  //�α��� ���� ��
			mav.addObject("msg1", "Id�� Password�� Ȯ�����ּ���");
			mav.addObject("msg2", "(������, �����ָ� �α��� �����մϴ�.)");
			mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick='location.href=\"./login.do\"' class=\"button\">");
			mav.setViewName("admin/msgView");	
		}else if(mlevel.equals("A")) {
			//session ����
			mlevel="������";
			session.setAttribute("s_id", id);
			session.setAttribute("s_pw", pw);
			session.setAttribute("s_mlevel", mlevel);
			
			mav.setViewName("redirect:/admin/admin.jsp");
		}else if(mlevel.equals("B")) {
			//session ����
			mlevel="������";
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
