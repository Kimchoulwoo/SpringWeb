package kr.co.radesk.admin.adnotice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class AdNoticeDAO {
	@Autowired
	DBOpen dbopen;
	@Autowired
	DBClose dbclose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	StringBuffer sql = null;
	ResultSet rs = null;
	ArrayList<AdNoticeDTO> list=null;
	
	
	public AdNoticeDAO() {
		System.out.println("AdnoticeDAO() 생성");
	}//AdnoticeDao() end
	
	public ArrayList<AdNoticeDTO> list(String col, String word, int nowPage, int recordPerPage){
		int startRow = ((nowPage-1)*recordPerPage)+1;
		int endRow = nowPage *recordPerPage;
		word = word.trim();
		String str="";
		try {			
			con = dbopen.getConnection();
			sql = new StringBuffer();
			if(word.length()==0) {				
				sql.append(" SELECT noticeno, subject, content, readcnt,regdt "); 
				sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, noticeno, subject, content, readcnt,regdt "); 
				sql.append(" 		FROM(SELECT noticeno,subject,content,readcnt,regdt ");
				sql.append(" 				FROM notice ");
				sql.append(" 				ORDER BY noticeno DESC) A, (SELECT @ROWNUM :=0) ");
				sql.append(" B)C ");
				sql.append(" WHERE C.ROW>="+startRow+" AND C.ROW<="+endRow );
			}else {
				sql.append(" SELECT noticeno, subject, content, readcnt,regdt "); 
				sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, noticeno, subject, content, readcnt,regdt "); 
				sql.append(" 		FROM(SELECT noticeno,subject,content,readcnt,regdt ");
				sql.append(" 				FROM notice ");
				if(col.equals("id")) {
					str+=" WHERE subject LIKE '%"+word+"%' ";
				}else if(col.equals("content")){
					str+=" WHERE content LIKE '%"+word+"%' ";
				}else if (col.equals("subject_content")) {
					str+=" WHERE subject LIKE '%"+word+"%' ";
					str+=" OR content LIKE '%"+word+"%' ";
				}//if end
				sql.append(str);
				sql.append(" 				ORDER BY noticeno DESC) A, (SELECT @ROWNUM :=0) ");
				sql.append(" B)C ");
				sql.append(" WHERE C.ROW>="+startRow+" AND C.ROW<="+endRow );
			}//if end	
			
			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<AdNoticeDTO>();
				do {
					AdNoticeDTO dto = new AdNoticeDTO();
					dto.setNoticeno(rs.getInt("noticeno"));
					dto.setSubject(rs.getString("subject"));
					dto.setContent(rs.getString("content"));
					dto.setReadcnt(rs.getInt("readcnt"));
					dto.setRegdt(rs.getString("regdt"));
					list.add(dto);
				}while(rs.next());
			}//if end
			
		}catch (Exception e) {
			System.out.println("noticelist() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}		
		return list;
	}//list() end

	public int create(String subject, String content) {
		int res=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" INSERT INTO notice(subject, content, regdt) ");
			sql.append(" VALUE(?,?,now()) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			res=pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("공지사항 create() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt);
		}		
		return res;
	}//create() end
	
	public AdNoticeDTO read(int noticeno) {
		AdNoticeDTO dto =null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT noticeno, subject, content, readcnt, regdt "); 
			sql.append(" FROM notice "); 
			sql.append(" WHERE noticeno=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, noticeno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto = new AdNoticeDTO();
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setRegdt(rs.getString("regdt"));
				dto.setNoticeno(rs.getInt("noticeno"));
			}//if end
		}catch (Exception e) {
			System.out.println("공지사항 read() 실패!!"+ e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}
		return dto;
	}//read() end
	
	public int delete(int noticeno) {
		int res=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" DELETE FROM notice WHERE noticeno=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, noticeno);
			res=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("delete() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt);
		}
		return res;
	}//delete() end
	
	public int update(AdNoticeDTO dto) {
		int res=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" UPDATE notice  ");
			sql.append(" SET subject=?, content=? ");
			sql.append(" WHERE noticeno=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNoticeno());
			res=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("delete() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt);
		}
		
		return res;
	}//update() end
	
	public int count(String col, String word) {
		int totalRecord=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT count(noticeno) FROM notice ");		
			
			if(word.length()>=1) {
				String search="";
				if(col.equals("subject")) {
					search+=" WHERE subject LIKE '%"+word+"%' ";
				}else if(col.equals("content")) {
					search+=" WHERE content LIKE '%"+word+"%' ";
				}else if(col.equals("subject_content")) {
					search+=" WHERE subject LIKE '%"+word+"%' ";
					search+=" OR content LIKE '%"+word+"%' ";
				}//if end
				sql.append(search);
			}//if end

			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalRecord=rs.getInt("count(noticeno)");
			}//if end
			
		}catch (Exception e) {
			System.out.println("count() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}
		
		return totalRecord;
	}//count() end
	
	public void incrementCnt(int noticeno) {
		try {
	    	con = dbopen.getConnection();
	    	sql=new StringBuffer();
	    	sql.append("UPDATE notice SET readcnt = readcnt + 1");
	    	sql.append(" WHERE noticeno = ?");

	    	pstmt=con.prepareStatement(sql.toString());
	    	pstmt.setInt(1, noticeno);
	    	pstmt.executeUpdate();
		}catch (Exception e) {
	    	System.out.println("공지사항 조회수 실패 : " + e);
	    }finally {
	    	dbclose.close(con, pstmt);
	    }
	}//incrementCnt() end
	


	public ArrayList<AdNoticeDTO> list(){
		try {			
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT noticeno, subject, content, readcnt,regdt "); 
			sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, noticeno, subject, content, readcnt,regdt "); 
			sql.append(" 		FROM(SELECT noticeno,subject,content,readcnt,regdt ");
			sql.append(" 				FROM notice ");
			sql.append(" 				ORDER BY noticeno DESC) A, (SELECT @ROWNUM :=0) ");
			sql.append(" B)C ");
			sql.append(" WHERE C.ROW>= 1 AND C.ROW<= 3");	
			
			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<AdNoticeDTO>();
				do {
					AdNoticeDTO dto = new AdNoticeDTO();
					dto.setNoticeno(rs.getInt("noticeno"));
					dto.setSubject(rs.getString("subject"));
					dto.setContent(rs.getString("content"));
					dto.setReadcnt(rs.getInt("readcnt"));
					dto.setRegdt(rs.getString("regdt"));

					list.add(dto);
				}while(rs.next());
			}//if end
		}catch (Exception e) {
			System.out.println("noticelist() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}		
		return list;
	}//list() end
	
	
}//class end
