package kr.co.radesk.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.radesk.bbs.BbsDTO;
import net.utility.*;

@Component
public class BbsDAO {
	@Autowired
	private DBOpen dbopen;

	@Autowired
	private DBClose dbcolse;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;
	ArrayList<BbsDTO> list = null;

	public BbsDAO() {
		System.out.println("BbsDAO 객체 생성 ok");
	}

	// 등록
	public int create(BbsDTO dto) {
		int res = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" INSERT INTO bbs(id, subject, content,");
			sql.append(" regdt, grpno, ip)");
		    sql.append(" VALUES(?, ?, ?, now(),");
		    sql.append(" (SELECT IFNULL(MAX(bbsno),0)+1 FROM bbs AS TB), ?)");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getIp());
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글 등록 실패 : " + e);
		} finally {
			dbcolse.close(con, pstmt);
		}
		return res;
	}

	// 목록
	public ArrayList<BbsDTO> list() {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT bbsno, subject, indent, id, readcnt, regdt");
			sql.append(" FROM bbs");
			sql.append(" ORDER BY grpno DESC, ansnum ASC");

			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<BbsDTO>();
				do {
					BbsDTO dto = new BbsDTO();
					dto.setBbsno(rs.getInt("bbsno"));
					dto.setSubject(rs.getString("subject"));
					dto.setIndent(rs.getInt("indent"));
					dto.setId(rs.getString("id"));
					dto.setRegdt(rs.getString("regdt"));
					dto.setReadcnt(rs.getInt("readcnt"));

					list.add(dto);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("게시판 목록 가져오기 실패 : " + e);
		} finally {
			dbcolse.close(con, pstmt, rs);
		}

		return list;
	}

	// 게시글 카운트
	public int boardcount() {
		int totalRecord = 0;

		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT COUNT(bbsno) AS count");
			sql.append(" FROM bbs");
			
			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			
			if(rs.next())
				totalRecord = rs.getInt("count");
		} catch(Exception e) {
			System.out.println("전체 글 수 가져오기 실패 : " + e);
		} finally {
			dbcolse.close(con, pstmt, rs);
		}
		
		return totalRecord;
	}
	
	public int count(String col, String word) {
		int record = 0;

		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT COUNT(bbsno) AS count");
			sql.append(" FROM bbs");
			if(word.length() >= 1) {
				String search = "";
				if(col.equals("id")) {
					search += " WHERE id LIKE '%" + word + "%'";
				} else if(col.equals("subject")) {
					search += " WHERE subject LIKE '%" + word + "%'";
				} else if(col.equals("content")) {
					search += " WHERE content LIKE '%" + word + "%'";
				}
				sql.append(search);
			}
			sql.append(" ORDER BY grpno DESC, ansnum ASC");
			
			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			
			if(rs.next())
				record = rs.getInt("count");
		} catch(Exception e) {
			System.out.println("현재 글 수 가져오기 실패 : " + e);
		} finally {
			dbcolse.close(con, pstmt, rs);
		}
		
		return record;
	}
	
	public void incrementCnt(int bbsno) {
		try {
	    	con = dbopen.getConnection();
	    	sql=new StringBuffer();
	    	sql.append("UPDATE bbs SET readcnt = readcnt + 1");
	    	sql.append(" WHERE bbsno = ?");

	    	pstmt=con.prepareStatement(sql.toString());
	    	pstmt.setInt(1, bbsno);
	    	pstmt.executeUpdate();
		}catch (Exception e) {
	    	System.out.println("조회수 실패 : " + e);
	    }finally {
	    	dbcolse.close(con, pstmt);
	    }
	}

	// 삭제
	public int delete(BbsDTO dto) {
		int res = 0;

		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("DELETE FROM bbs WHERE bbsno=? and id=?");

			pstmt = con.prepareStatement(sql.toString()); // 쿼리문 변환
			
			pstmt.setInt(1, dto.getBbsno()); // 1번째 물음표
			pstmt.setString(2, dto.getId()); // 2번째 물음표
			
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글 삭제 실패 : " + e);
		} finally {
			dbcolse.close(con, pstmt);
		}
		
		return res;
	}

	// 조회
	public BbsDTO read(int bbsno) {
		BbsDTO dto = new BbsDTO(); //한줄 dto객체에 담기

	    try {
	    	con = dbopen.getConnection();
	    	sql=new StringBuffer();
	    	sql.append("SELECT bbsno, id, subject, content, readcnt, regdt");
	    	sql.append(" FROM bbs");
	    	sql.append(" WHERE bbsno=?");

	    	pstmt = con.prepareStatement(sql.toString());
	    	pstmt.setInt(1, bbsno);
	    	
	    	rs = pstmt.executeQuery();

	    	if(rs.next()) {	
	    		dto.setBbsno(rs.getInt("bbsno"));
	    		dto.setId(rs.getString("id"));
	    		dto.setSubject(rs.getString("subject"));
	    		dto.setContent(rs.getString("content"));
	    		dto.setReadcnt(rs.getInt("readcnt"));
	    		dto.setRegdt(rs.getString("regdt"));
	    	} else {
	    		dto = null;
	    	}
	    }catch (Exception e) {
	    	System.out.println("게시판 상세 조회 실패 : " + e);
	    }finally {
	    	dbcolse.close(con, pstmt, rs);
	    }

	    return dto;
	}

	// 수정
	public int update(BbsDTO dto) {
		int res = 0;

		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("UPDATE bbs");
			sql.append(" SET subject=?, content=?, ip=?");
			sql.append(" WHERE bbsno=?");
			
			pstmt = con.prepareStatement(sql.toString()); // 쿼리문 변환
			
			pstmt.setString(1, dto.getSubject()); // 1번째 물음표
			pstmt.setString(2, dto.getContent()); // 2번째 물음표
			pstmt.setString(3, dto.getIp()); // 3번째 물음표
			pstmt.setInt(4, dto.getBbsno()); // 4번째 물음표
			
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시판 수정 실패 : " + e);
		} finally {
			dbcolse.close(con, pstmt);
		}
		
		return res;
	}

	// 답변 쓰기
	public int reply(BbsDTO dto) { // Call By Reference
		int res = 0;

		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();

			// 1) 부모글 정보가져오기(그룹번호, 들여쓰기, 글순서) select
			int grpno = 0, indent = 0, ansnum = 0;
			sql.append("SELECT grpno, indent, ansnum");
			sql.append(" FROM bbs");
			sql.append(" WHERE bbsno = ?");
			pstmt = con.prepareStatement(sql.toString()); // 쿼리문 변환
			pstmt.setInt(1, dto.getBbsno());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				grpno = rs.getInt("grpno"); // 부모글 그룹번호
				indent = rs.getInt("indent") + 1; // 부모글 들여쓰기 + 1
				ansnum = rs.getInt("ansnum") + 1; // 부모글 글순서 + 1
			}

			// 2) 글순서 재조정 update
			// 1)에서 사용한 sql 오브젝트를 초기화 해야 함
			sql.delete(0, sql.length()); // 또는 sql = new StringBuilder(); 로 새롭게 할당 받기
			sql.append("UPDATE bbs");
			sql.append(" SET ansnum = ansnum + 1");
			sql.append(" WHERE grpno = ? AND ansnum >= ?");
			pstmt = con.prepareStatement(sql.toString()); // 쿼리문 변환
			pstmt.setInt(1, grpno);
			pstmt.setInt(2, ansnum);
			pstmt.executeUpdate();

			// 3) 답변글 추가 insert
			sql.delete(0, sql.length()); // 또는 sql = new StringBuilder(); 로 새롭게 할당 받기
			sql.append("INSERT INTO");
			sql.append(" bbs(subject, id, content, ip, regdt, grpno, indent, ansnum)");
			sql.append(" VALUES('" + dto.getSubject() + "',");
			sql.append(" '" + dto.getId() + "',");
			sql.append(" '" + dto.getContent() + "',");
			sql.append(" '" + dto.getIp() + "',");
			sql.append(" NOW(),");
			sql.append(" " + grpno + ",");
			sql.append(" " + indent + ",");
			sql.append(" " + ansnum + ")");
			System.out.println(sql.toString());
			pstmt = con.prepareStatement(sql.toString()); // 쿼리문 변환
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("답변 쓰기 실패 : " + e);
		} finally {
			dbcolse.close(con, pstmt, rs);
		}
		
		return res;
	}

	// 목록
	public ArrayList<BbsDTO> list(String col, String word, int nowPage, int recordPerPage){
	    ArrayList<BbsDTO> list=null;
	    
	    // 페이지당 출력 할 레코드 수 (10개를 기준)
	    // 1 page : WHERE r >= 1 AND r <= 10
	    // 2 page : WHERE r >= 11 AND r <= 20 
	    // 3 page : WHERE r >= 21 AND r <= 30
	    int startRow = ((nowPage - 1) * recordPerPage) + 1 ;
	    int endRow   = nowPage * recordPerPage;
	    
	    try{
		      con=dbopen.getConnection();
		      sql=new StringBuffer();
		      
		      word = word.trim(); //검색어의 좌우 공백 제거
		      
		      if(word.length() == 0) { //검색을 하지 않는 경우
		        sql.append(" SELECT bbsno, subject, id, readcnt, indent, regdt");
		        sql.append(" FROM(SELECT bbsno, subject, id, readcnt, indent, regdt, @ROWNUM :=@ROWNUM +1 AS ROW");
		        sql.append("       FROM(SELECT bbsno, subject, id, readcnt, indent, regdt");
		        sql.append("              FROM bbs ORDER BY grpno DESC, ansnum ASC) A, (SELECT @ROWNUM := 0) B) C");
		        sql.append(" WHERE C.ROW>=" + startRow + " AND C.ROW<=" + endRow) ;
		      } else {
		        //검색을 하는 경우
		        sql.append(" SELECT bbsno, subject, id, readcnt, indent, regdt");
		        sql.append(" FROM(SELECT bbsno, subject, id, readcnt, indent, regdt, @ROWNUM :=@ROWNUM +1 AS ROW");
		        sql.append("       FROM(SELECT bbsno, subject, id, readcnt, indent, regdt");
		        sql.append("              FROM bbs");
		        
		        String search="";
		        if(col.equals("id")) {
		          search += " WHERE id LIKE '%" + word + "%'";
		        } else if(col.equals("subject")) {
		          search += " WHERE subject LIKE '%" + word + "%'";
		        } else if(col.equals("content")) {
		          search += " WHERE content LIKE '%" + word + "%'";
		        }
		        
		        sql.append(search);        
		        
		        sql.append("              order by grpno DESC, ansnum ASC) A, (SELECT @ROWNUM := 0) B) C ");
		        sql.append(" WHERE C.ROW>=" + startRow + " AND C.ROW<=" + endRow) ;
		      }//if end
		      
		      pstmt=con.prepareStatement(sql.toString());
		      
		      rs=pstmt.executeQuery();

		      if(rs.next()){
		        list = new ArrayList<BbsDTO>();
		        do{
		          BbsDTO dto = new BbsDTO();
		          dto.setBbsno(rs.getInt("bbsno"));
		          dto.setSubject(rs.getString("subject"));
		          dto.setId(rs.getString("id"));
		          dto.setReadcnt(rs.getInt("readcnt"));
		          dto.setRegdt(rs.getString("regdt"));
		          dto.setIndent(rs.getInt("indent"));
		          list.add(dto);
		        }while(rs.next());
		      }
		    }catch(Exception e) {
	      System.out.println("목록 페이지 실패 : " + e);
	    }finally{
	      dbcolse.close(con, pstmt, rs);
	    }    
	    return list;
	  }//list() end 
}
