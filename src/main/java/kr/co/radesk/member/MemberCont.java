package kr.co.radesk.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Utility;


@Controller
public class MemberCont {

	@Autowired
	MemberDAO dao;
	
	public MemberCont() {
		System.out.println("---MemberCont() 객체 생성됨...");
	}
	
	//http://localhost:9090/radesk/member/agreement.do
		@RequestMapping(value="member/agreement.do", method=RequestMethod.GET)
		public ModelAndView agree() {
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("member/agreement");
			mav.addObject("root", Utility.getRoot());
		    
			return mav;
		} //agree() end
		
		@RequestMapping(value="member/join.do", method=RequestMethod.GET)
		public ModelAndView memberform() {
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("member/memberForm");
			mav.addObject("root", Utility.getRoot());
		    
			return mav;
		} //memberform() end
		
		@RequestMapping(value="member/join.do", method=RequestMethod.POST)
		public ModelAndView insertProc(HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			
			MemberDTO dto = new MemberDTO();
			String birth = "";
			birth += req.getParameter("year");
			birth += req.getParameter("month");
			birth += req.getParameter("date");
			dto.setId(req.getParameter("id"));
			dto.setName(req.getParameter("name"));
			dto.setPw(req.getParameter("pw"));
			dto.setBirth(birth);
			dto.setTel(req.getParameter("tel"));
			dto.setMail(req.getParameter("mail"));
			dto.setZipcode(req.getParameter("zipcode"));
			dto.setAddress1(req.getParameter("address1"));
			dto.setAddress2(req.getParameter("address2"));
			
			mav.setViewName("member/msgView");
			mav.addObject("root", Utility.getRoot());
				
			int cnt = dao.insert(dto);
			if(cnt==0) {
				 mav.addObject("msg1", "<p>회원가입 실패하였습니다.</p>");
				 mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			}else {
				String script="";
				script+="<script>";
				script+=" alert('회원가입을 환영합니다.');";
				script+=" location.href=\"../index.do\"";
				script+="</script>";

				mav.addObject("msg1", script);
			}
			return mav;
		} //insertProc() end
		
		@RequestMapping(value="member/duplicateID.do", method=RequestMethod.GET)
	    public ModelAndView duplicateID(MemberDTO dto) {
	    	ModelAndView mav= new ModelAndView();
	    	
	    	mav.setViewName("member/idCheckForm");
	    	mav.addObject("root", Utility.getRoot());
	    	
	    	return mav;
	    } //duplicateID() end
		
		@RequestMapping(value="member/duplicateID.do", method=RequestMethod.POST)
		public ModelAndView duplicateIDProc(MemberDTO dto) {
			   ModelAndView mav = new ModelAndView();
			   mav.setViewName("member/msgView");
			   mav.addObject("root", Utility.getRoot());
			   
			   int cnt = dao.duplicateID(dto.getId());
			   
			   if(cnt==0) {
			      mav.addObject("msg1", "<p>사용 가능한 아이디 입니다.</p>");
			      mav.addObject("link1", "<input type='button' value='사용하기' onclick='javascript:apply(\"" + dto.getId() +"\")'>");
			      mav.addObject("link2", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			      mav.addObject("link3", "<input type='button' value='취소' onclick='javascript:window.close()'>"); 
			}else {
			      mav.addObject("msg1", "<p>이미 사용중인 아이디 입니다.</p>");
			      mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			      mav.addObject("link2", "<input type='button' value='취소' onclick='javascript:window.close()'>");      
			}
			
			return mav;
		} //duplicateProc() end
			
		@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
		public ModelAndView loginForm() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("member/loginForm");  
			return mav;   
		}//loginForm() end

		@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
		public ModelAndView loginProc(String id, String pw, HttpSession session){
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("member/msgView");
			String mlevel = dao.loginProc(id, pw);		
			
			if(mlevel==null) {  //로그인 실패 시
				String script="";
				script+="<script>";
				script+=" alert('ID와 PASSWORD를 확인해주세요.');";
				script+=" location.href=\"./login.do\"";
				script+="</script>";

				mav.addObject("msg1", script);
			}else if(mlevel.equals("E")) {
				String script="";
				script+="<script>";
				script+=" alert('탈퇴한 회원입니다. \n 다시 가입 후 이용하세요.');";
				script+=" location.href=\"./login.do\"";
				script+="</script>";

				mav.addObject("msg1", script);
			}else {
				session.setAttribute("s_id", id);
				session.setAttribute("s_pw", pw);
				session.setAttribute("s_mlevel", mlevel);
				
				mav.setViewName("redirect:/index.do");
			}
			return mav;
		} //loginProc() end	
		
		@RequestMapping("member/logout.do")
		public ModelAndView logout(HttpSession session) {
			ModelAndView mav = new ModelAndView();
			session.removeAttribute("s_id");
			session.removeAttribute("s_pw");
			session.removeAttribute("s_mlevel");
			
			mav.setViewName("redirect:/member/login.do");
			
			return mav;
		}//logout() end
		
		
		
		//http://localhost:9090/radesk/member/myinfo.do
		@RequestMapping(value="member/myinfo.do", method=RequestMethod.GET)
		public ModelAndView read(HttpSession session) {
			ModelAndView mav = new ModelAndView();
			MemberDTO dto = dao.read((String)session.getAttribute("s_id"));
			String cname = dao.clubname(dto);
			mav.setViewName("member/myinfo");
			mav.addObject("root", Utility.getRoot());
		    mav.addObject("dto", dto);
		    mav.addObject("cname", cname);
			
			return mav;
		} //read() end
		
		@RequestMapping(value="member/myinfoUpdate.do", method=RequestMethod.GET)
		public ModelAndView myinfoUpdate() {
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("member/myinfoUpdate");
			mav.addObject("root", Utility.getRoot());
		    
			return mav;
		} //myinfoUpdate() end
		
		@RequestMapping(value="member/myinfoUpdate.do", method=RequestMethod.POST)
		public ModelAndView update(MemberDTO dto, String pw, HttpSession session) {
				ModelAndView mav = new ModelAndView();
				dto.setId((String)session.getAttribute("s_id"));
				dto.setPw(pw);

				dto=dao.select(dto);
				if(dto==null) {
					mav.addObject("msg1", "비밀번호가 맞지않습니다.");
					mav.addObject("link1", "<input type='button' value='다시 시도' onclick='javascript:history.back()'>");
					
					mav.setViewName("member/msgView");
				}else {
				    mav.setViewName("member/myinfoUpdateForm");	
				    mav.addObject("root", Utility.getRoot());
				    mav.addObject("dto", dto);
				}		
				return mav;
			} //update() end
		
		@RequestMapping(value="member/myinfoUpdateProc.do", method=RequestMethod.POST)
		public ModelAndView updateProc(HttpServletRequest req, HttpSession session) {
			ModelAndView mav = new ModelAndView();
			
			MemberDTO dto = new MemberDTO();
			String birth = "";
			birth += req.getParameter("year");
			birth += req.getParameter("month");
			birth += req.getParameter("date");
			dto.setName(req.getParameter("name"));
			dto.setPw(req.getParameter("pw"));
			dto.setBirth(birth);
			dto.setTel(req.getParameter("tel"));
			dto.setMail(req.getParameter("mail"));
			dto.setZipcode(req.getParameter("zipcode"));
			dto.setAddress1(req.getParameter("address1"));
			dto.setAddress2(req.getParameter("address2"));
			
			mav.setViewName("member/msgView");
			mav.addObject("root", Utility.getRoot());
			
			dto.setId((String)session.getAttribute("s_id"));
			
			int cnt = dao.update(dto);
			
			if(cnt == 0) {
			      mav.addObject("msg1", "회원정보 수정 실패");
			      mav.addObject("img",  "<img src='../images/fail.png'>");
			      mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			      mav.addObject("link2", "<input type='button' value='취소' onclick='location.href=\"./myinfo.do\"'>");
			    }
			    else {
			    	String script="";
			    	script+="<script>";
			    	script+="   alert('회원정보가 수정 되었습니다.');";
			    	script+="   location.href=\"./myinfo.do\"";
			    	script+="</script>";
			      mav.addObject("msg1", script);
			    }		
			return mav;	
		} //end updateProc()
		
		//http://localhost:9090/radesk/member/myinfoUpdateForm.do
		@RequestMapping(value="member/myinfoUpdateForm.do", method=RequestMethod.GET)
		public ModelAndView updateForm(MemberDTO dto) {
			ModelAndView mav = new ModelAndView();
			dto = dao.read(dto.getId());
			
			mav.setViewName("member/myinfoUpdateForm");	
			mav.addObject("root", Utility.getRoot());
		    mav.addObject("dto", dto);
			
			return mav;
		} //updateForm() end
		
		@RequestMapping(value="member/withdraw.do", method=RequestMethod.GET)
		public ModelAndView withdraw() {
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("member/withdraw");
			mav.addObject("root", Utility.getRoot());
		    
			return mav;
		} //withdraw() end
		
		@RequestMapping(value="member/withdraw.do", method=RequestMethod.POST)
		public ModelAndView withdraw(MemberDTO dto, String pw, HttpSession session) {
				ModelAndView mav = new ModelAndView();
				mav.addObject("root", Utility.getRoot());
				dto.setId((String)session.getAttribute("s_id"));
				dto.setPw(pw);

				dto=dao.select(dto);
				if(dto==null) {
					mav.addObject("msg1", "비밀번호가 맞지않습니다.");
					mav.addObject("link1", "<input type='button' value='다시 시도' onclick='javascript:history.back()'>");
					
					mav.setViewName("member/msgView");
				}else {
				    mav.setViewName("member/withdrawProc");	
				    
				    mav.addObject("dto", dto);
				}		
				return mav;
			} //withdraw() end
		
		@RequestMapping(value="member/withdrawProc.do", method=RequestMethod.POST)
		public ModelAndView withdrawProc(MemberDTO dto, HttpSession session) {
			ModelAndView mav = new ModelAndView();
					
			mav.setViewName("member/msgView");
			mav.addObject("root", Utility.getRoot());
			
			dto.setId((String)session.getAttribute("s_id"));
			
			int cnt = dao.withdraw(dto);
			
			if(cnt == 0) {
				mav.addObject("msg1", "탈퇴에 실패하였습니다.");
			    mav.addObject("img",  "<img src='../images/fail.png'>");
			    mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascript:history.back()'>");
			    mav.addObject("link2", "<input type='button' value='취소' onclick='location.href=\"./myinfo.do\"'>");
			}else {
			    session.removeAttribute("s_id");
				session.removeAttribute("s_pw");
				session.removeAttribute("s_mlevel");
				String script="";
	            script+="<script>";
	            script+=" alert('탈퇴가 완료되었습니다.');";
	            script+=" location.href=\"../index.do\"";
	            script+="</script>";
	            mav.addObject("msg1", script);				      
			}		
			return mav;	
		} //end withdrawProc()
		
		@RequestMapping(value="member/findIdpw.do", method=RequestMethod.GET)
		public ModelAndView findIdpw() {
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("member/findIdpw");
			mav.addObject("root", Utility.getRoot());
		    
			return mav;
		} //findIdpw() end	
		
		@RequestMapping(value="member/findIdpw.do", method=RequestMethod.POST)
		public ModelAndView findIdpwProc(MemberDTO dto, HttpServletRequest req) {
				ModelAndView mav = new ModelAndView();
				mav.addObject("root", Utility.getRoot());
							
				String birth = "";
				birth += req.getParameter("birth_y");
				birth += req.getParameter("birth_m");
				birth += req.getParameter("birth_d");

				dto.setId(req.getParameter("id").trim());
				dto.setName(req.getParameter("name").trim());
				dto.setBirth(birth);

				String id=dao.findIdpw(dto);
				dto.setId(id);
				
				if(id==null) {
					mav.addObject("msg1", "일치하는 아이디가 없습니다.");
					mav.addObject("link1", "<input type='button' value='다시 시도' onclick='javascript:history.back()'>");
					
					mav.setViewName("member/msgView");
				}else {
					mav.addObject("dto", dto);
					mav.addObject("id", id);
					mav.addObject("msg1", "당신의 아이디는 "+id+"입니다.");
					mav.addObject("link1", "<input type='submit' method='get' value='비밀번호 변경' onclick='location.href=\"./pwReset.do?id="+ id +"\"'>");
				    mav.setViewName("member/msgView");
				}		
				return mav;
			} //findIdpw() end
		
		@RequestMapping(value="member/pwReset.do", method=RequestMethod.GET)
		public ModelAndView pwreset(MemberDTO dto) {
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("member/pwReset");
			mav.addObject("root", Utility.getRoot());
			mav.addObject("id", dto.getId());
		    
			return mav;
		} //pwReset() end
		
		@RequestMapping(value="member/pwReset.do", method=RequestMethod.POST)
		public ModelAndView pwReset(MemberDTO dto, HttpServletRequest req) {
				ModelAndView mav = new ModelAndView();
				mav.addObject("root", Utility.getRoot());
				
				dto.setPw(req.getParameter("pw"));
				
				int cnt = dao.pwReset(dto);
				System.out.println(cnt);						
				if(cnt==0) {
					mav.addObject("msg1", "비밀번호 변경을 실패했습니다..");
					mav.addObject("link1", "<input type='button' value='다시 시도' onclick='javascript:history.back()'>");
					
					mav.setViewName("member/msgView");
				}else {
					String script="";
		            script+="<script>";
		            script+=" alert('비밀번호가 변경되었습니다.');";
		            script+=" location.href='./login.do'";
		            script+="</script>";
		            mav.addObject("msg1", script);
		            mav.setViewName("member/msgView");
				}		
				return mav;
			} //pwReset() end
	
	

} //class end
