package kr.co.radesk.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class MemberDAO {
	  
	  @Autowired
	  DBOpen  dbopen;
	  
	  @Autowired
	  DBClose dbclose;
	 
	 	  
	  Connection con=null;
	  PreparedStatement pstmt=null;
	  ResultSet rs=null;
	  StringBuffer sql=null;
	  
	  public MemberDAO() {
		    System.out.println("----- MemberDAO 객체 생성됨...");  
	  }
	  
	  public int insert(MemberDTO dto) {
		  int res=0;
	    	
		  try{
	   	      con = dbopen.getConnection();
	   	      sql = new StringBuffer();  
	   	      sql.append(" INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)");
	   	      sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, 'C00000000000', 'D')");
	   	      pstmt=con.prepareStatement(sql.toString());
	   	      pstmt.setString(1, dto.getId()); 
	   	      pstmt.setString(2, dto.getPw());
	   	      pstmt.setString(3, dto.getName());
	   	      pstmt.setString(4, dto.getBirth());
	   	      pstmt.setString(5, dto.getTel());
	   	      pstmt.setString(6, dto.getMail());
	   	      pstmt.setString(7, dto.getZipcode());
	   	      pstmt.setString(8, dto.getAddress1());
	   	      pstmt.setString(9, dto.getAddress2());
	   	      res=pstmt.executeUpdate();
	   	      
	    	  }catch(Exception e) {
	    	      System.out.println("회원가입 실패 : "+e);
	    	    }finally{
	    	      dbclose.close(con, pstmt, rs);
	    	    }    
	    	 return res;
	     }
	  
	  public int duplicateID(String id) {
		  int cnt = 0;
		  try {
		      con=dbopen.getConnection();
		      sql=new StringBuffer();
		      
		      sql.append(" SELECT COUNT(id) as cnt");
		      sql.append(" FROM member");
		      sql.append(" WHERE id=?");      
		      pstmt=con.prepareStatement(sql.toString());
		      pstmt.setString(1, id);
		      
		      rs=pstmt.executeQuery();      
		      
		      if(rs.next()) {
		          cnt=rs.getInt("cnt");
		      }
		      
		  }catch (Exception e) {
		      System.out.println("아이디 중복 확인 실패 : " + e);
		  }finally {
		      dbclose.close(con, pstmt, rs);
		  }
		  return cnt;
      } //duplecateID() end
	  
	  public String loginProc(String id, String pw) {
			String mlevel = "";
			try {
				con = dbopen.getConnection();
				sql = new StringBuffer();
				sql.append(" SELECT mlevel FROM member ");
				sql.append(" WHERE id=? AND pw=? ");
				sql.append(" AND mlevel IN ('A', 'B', 'C', 'D') ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				rs = pstmt.executeQuery();
				if(rs.next()==true) {
					mlevel=rs.getString("mlevel");					
				}else {
					mlevel=null;
				}
			}catch (Exception e) {
				System.out.println("login() 실패!!!!"+e);
			}finally {
				dbclose.close(con,pstmt,rs);
			}			
			return mlevel;
	  } //loginProc() end
	  
	  public MemberDTO select(MemberDTO dto){
			try {
				con=dbopen.getConnection();
		        sql=new StringBuffer();
		        sql.append(" SELECT name, birth, tel, mail, zipcode, address1, address2, ccode");
		        sql.append(" FROM member WHERE id=? AND pw=?");

		        pstmt=con.prepareStatement(sql.toString());
		        pstmt.setString(1, dto.getId());
		        pstmt.setString(2, dto.getPw());
		        
		        rs=pstmt.executeQuery();
		        
		        if(rs.next()){
		        	dto.setName(rs.getString("name"));
		        	dto.setBirth(rs.getString("birth"));
		        	dto.setTel(rs.getString("tel"));
		    		dto.setMail(rs.getString("mail"));
		    		dto.setZipcode(rs.getString("zipcode"));
		    		dto.setAddress1(rs.getString("address1"));
		    		dto.setAddress2(rs.getString("address2"));
		        }else{
		            dto=null;
		        }
		    }catch(Exception e) {
		    	System.out.println("회원정보 불러오기 실패 : " + e);
		    }finally {
		    	dbclose.close(con, pstmt, rs);
		    }
			//System.out.println(dto);
		    return dto;
		  } //select() end
	  
	  public MemberDTO read(String id) {
		  MemberDTO dto = null;
		  try{
		      con = dbopen.getConnection();
		      sql = new StringBuffer();
		      sql.append(" SELECT pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel");
		      sql.append(" FROM member");
		      sql.append(" WHERE id = ?");
		      pstmt = con.prepareStatement(sql.toString());
		      pstmt.setString(1, id);
		      rs = pstmt.executeQuery();
		      if(rs.next()){
		    	  dto = new MemberDTO();
		    	  dto.setPw(rs.getString("pw"));
		          dto.setName(rs.getString("name"));
		          dto.setBirth(rs.getString("birth"));
		          dto.setTel(rs.getString("tel"));
		          dto.setMail(rs.getString("mail"));
		          dto.setZipcode(rs.getString("zipcode"));
		          dto.setAddress1(rs.getString("address1"));
		          dto.setAddress2(rs.getString("address2"));
		          dto.setCcode(rs.getString("ccode"));
		          dto.setMlevel(rs.getString("mlevel"));		         
		      }
		  }catch(Exception e){
			  System.out.println(e.toString());
		  }finally{
		      dbclose.close(con, pstmt, rs);
		  }
		  return dto;
	  } //read() end
	  
	  public int update(MemberDTO dto) {
		  int cnt = 0;
		  try {
		      con = dbopen.getConnection();
		      sql = new StringBuffer();
		      sql.append(" UPDATE member");
		      sql.append(" SET name=?, pw=?, birth=?, tel=?, mail=?, zipcode=?, address1=?, address2=?");
		      sql.append(" WHERE id = ?"); 
		      pstmt = con.prepareStatement(sql.toString());
		      pstmt.setString(1, dto.getName());
		      pstmt.setString(2, dto.getPw());
		      pstmt.setString(3, dto.getBirth());
		      pstmt.setString(4, dto.getTel());
		      pstmt.setString(5, dto.getMail());
		      pstmt.setString(6, dto.getZipcode());
		      pstmt.setString(7, dto.getAddress1());
		      pstmt.setString(8, dto.getAddress2());
		      pstmt.setString(9, dto.getId());
		      cnt = pstmt.executeUpdate();

		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      dbclose.close(con, pstmt, rs);
		    }
		    return cnt;
	  } //update() end
	  
	  public int withdraw(MemberDTO dto) {
		  int cnt = 0;
		  try {
		      con = dbopen.getConnection();
		      sql = new StringBuffer();
		      sql.append(" UPDATE member");
		      sql.append(" SET mlevel='E'");
		      sql.append(" WHERE id = ?"); 
		      
		      pstmt = con.prepareStatement(sql.toString());		      
		      pstmt.setString(1, dto.getId());
		      cnt = pstmt.executeUpdate();

		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      dbclose.close(con, pstmt, rs);
		    }
		    return cnt;
	  } //update() end
	  
	  public String clubname(MemberDTO dto) {
		  String cname="";
		  try {
				con = dbopen.getConnection();
				sql = new StringBuffer();
				sql.append(" SELECT cname FROM club ");
				sql.append(" WHERE ccode = ?");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, dto.getCcode());
				rs = pstmt.executeQuery();
				if(rs.next()==true) {
					cname=rs.getString("cname");		
				}else {
					cname="가입된 클럽 없음";
				}
			}catch (Exception e) {
				System.out.println("clubname() 실패!!!!"+e);
			}finally {
				dbclose.close(con,pstmt,rs);
			}			
		  
		  return cname;
	  }
	  
	  public String findIdpw(MemberDTO dto) {
	    	String id = null;
	         try {
	               //DB연결
	               con=dbopen.getConnection();   //DB연결
	               sql=new StringBuffer();
	               sql.append(" SELECT id");
				   sql.append(" FROM member WHERE name=? AND birth=?");

	               
	               pstmt=con.prepareStatement(sql.toString()); //sql 문 변환
	               pstmt.setString(1, dto.getName());
	               pstmt.setString(2, dto.getBirth());
	               
			       rs=pstmt.executeQuery();	   
			       
			       if(rs.next()) {
			             id = rs.getString("id");      
			       }else {
			    	   id=null;
			       }
	             } catch (Exception e) {
	                     System.out.println("실패 : " + e);
	             }finally {               //자원반납 할때 순서 거꾸로 닫아야함
	                     dbclose.close(con, pstmt, rs);
	             }       
	                 return id;

	         }// end
	  
	  public int pwReset(MemberDTO dto) {
		  int cnt = 0;
	      try {
	      con=dbopen.getConnection();  //DB연결
	      sql=new StringBuffer();
	      
	      sql.append(" UPDATE member SET pw=? WHERE id=? ");

	      pstmt=con.prepareStatement(sql.toString());
	      pstmt.setString(1, dto.getPw());
	      pstmt.setString(2, dto.getId()); 

	      cnt = pstmt.executeUpdate();

	      } catch (Exception e) {
	         System.out.println("실패 : " + e);
	      }finally { 
	         dbclose.close(con,pstmt);
	      }//end  
	      return cnt;

	   }//pwReset end
} //class end
