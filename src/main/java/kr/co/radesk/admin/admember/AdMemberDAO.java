package kr.co.radesk.admin.admember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class AdMemberDAO {
	@Autowired
	DBOpen dbopen=null;
	@Autowired
	DBClose dbclose=null;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;
	ArrayList<AdMemberDTO> list = null;
	
	
	public AdMemberDAO() {
		System.out.println("AdMemberDAO() 생성");
	}//MemberDAO() end
	
	public ArrayList<AdMemberDTO> list(){
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel ");
			sql.append(" FROM member ");
			sql.append(" ORDER BY id ASC ");

			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<AdMemberDTO>();
				do {
					AdMemberDTO dto = new AdMemberDTO();
					dto.setId(rs.getString("id"));
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
					list.add(dto);
				}while(rs.next());
			}//if end			
			
		}catch (Exception e) {
			System.out.println("list() 실패!!" + e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}		
		return list;
	}//list() end
	
	public ArrayList<AdMemberDTO> list(String col, String word, int nowPage, int recordPerPage){
		int startRow = ((nowPage-1)*recordPerPage)+1;
		int endRow = nowPage *recordPerPage;
		word = word.trim();
		String str="";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			if(word.length()==0) {				
				sql.append(" SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel "); 
				sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel "); 
				sql.append(" 		FROM(SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel ");
				sql.append(" 				FROM member ");
				sql.append(" 				ORDER BY id ASC) A, (SELECT @ROWNUM :=0) ");
				sql.append(" B)C ");
				sql.append(" WHERE C.ROW>="+startRow+" AND C.ROW<="+endRow );
			}else {
				sql.append(" SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel "); 
				sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel "); 
				sql.append(" 		FROM(SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel ");
				sql.append(" 				FROM member ");
				if(col.equals("id")) {
					str+=" WHERE id LIKE '%"+word+"%' ";
				}else if(col.equals("name")){
					str+=" WHERE name LIKE '%"+word+"%' ";
				}else if (col.equals("mlevel")) {
					str+=" WHERE mlevel LIKE '%"+word+"%' ";
				}//if end
				sql.append(str);
				sql.append(" 				ORDER BY id DESC) A, (SELECT @ROWNUM :=0) ");
				sql.append(" B)C ");
				sql.append(" WHERE C.ROW>="+startRow+" AND C.ROW<="+endRow );
			}//if end	
			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<AdMemberDTO>();
				do {
					AdMemberDTO dto = new AdMemberDTO();
					dto.setId(rs.getString("id"));
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
					list.add(dto);
				}while(rs.next());
			}//if end			
			
		}catch (Exception e) {
			System.out.println("list() 실패!!" + e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}		
		return list;
	}//list() end
	
	public int memLevelProc(AdMemberDTO dto){
		int res=0;
		String str = "";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT mlevel FROM member ");
			sql.append(" WHERE id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				str=rs.getString("mlevel");
			}//if end
			
			sql.delete(0, sql.length());
			sql.append(" UPDATE member ");
			sql.append(" SET mlevel=? ");
			sql.append(" WHERE id=? AND mlevel=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMlevel());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, str);
			res=pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("memLevelProc 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}
		
		return res;
	}//memLevelProc() end
	
	public int memDelProc(AdMemberDTO dto) {
		int res=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" DELETE FROM member ");
			sql.append(" WHERE id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			res=pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("memDelProc() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt);
		}
		return res;
	}//memDeleteProc() end
	
	public int count(String col, String word) {
		int totalRecord=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT count(id) FROM member ");		
			
			if(word.length()>=1) {
				String search="";
				if(col.equals("id")) {
					search+=" WHERE id LIKE '%"+word+"%' ";
				}else if(col.equals("name")){
					search+=" WHERE name LIKE '%"+word+"%' ";
				}else if (col.equals("mlevel")) {
					search+=" WHERE mlevel LIKE '%"+word+"%' ";
				}//if end
				sql.append(search);
			}//if end

			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalRecord=rs.getInt("count(id)");
			}//if end
			
		}catch (Exception e) {
			System.out.println("count() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}
		
		return totalRecord;
	}//count() end
	
}//class end
