package kr.co.radesk.admin.adlogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class LoginDAO {
	
	@Autowired
	private DBOpen dbopen = null;
	
	@Autowired
	private DBClose dbclose = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;
	
	public LoginDAO() {
		System.out.println("LoginDAO() 객체 생성");
	}//LoginDAO end
	
	public String login(String id, String pw) {
		String mlevel = "";
		try {
		con = dbopen.getConnection();
		sql = new StringBuffer();
		sql.append(" SELECT mlevel FROM member ");
		sql.append(" WHERE id=? AND pw=? ");
		sql.append(" AND mlevel IN ('A','B') ");
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		rs = pstmt.executeQuery();
		if(rs.next()==true) {
			mlevel=rs.getString("mlevel");			
		}else{
			mlevel=null;
		}//if end
		
		}catch (Exception e) {
			System.out.println("login() 실패!!!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}
		
		return mlevel;
	}

}//class end
