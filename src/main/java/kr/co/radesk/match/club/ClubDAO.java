package kr.co.radesk.match.club;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.radesk.admin.adnotice.AdNoticeDTO;
import kr.co.radesk.admin.adstadium.AdStadiumDTO;
import kr.co.radesk.match.team.TeamDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class ClubDAO {
	
	@Autowired
	DBOpen dbopen;
	
	@Autowired
	DBClose dbclose;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	StringBuffer sql = null;
	ResultSet rs = null;
	ArrayList<ClubDTO> club = null;

	public ClubDAO() {
		System.out.println("ClubDAO() 생성!!");
	}//ClubDAO() end 	

	public ArrayList<ClubDTO> list(ClubDTO dto, String col, String word, int nowPage, int recordPerPage){
		String str = "";
		int startRow = ((nowPage - 1) * recordPerPage) + 1;
		int endRow = nowPage * recordPerPage;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();

			if (word.length() == 0) {
				sql.append(" SELECT ccode, cid, cname, carea, ccontent, cposter ");
				sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, ccode, cid, cname, carea, ccontent, cposter ");
				sql.append(" 		FROM( SELECT ccode, cid, cname, carea, ccontent, cposter ");
				sql.append(" 				 FROM club ");
				sql.append(" 				 ORDER BY ccode DESC) A, (SELECT @ROWNUM :=0) ");
				sql.append(" B)C ");
				sql.append(" WHERE C.ROW>=" + startRow + " AND C.ROW<=" + endRow +" AND NOT C.ccode='c00000000000' ");

			} else {
				sql.append(" SELECT ccode, cid, cname, carea, ccontent, cposter ");
				sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, ccode, cid, cname, carea, ccontent, cposter ");
				sql.append(" 		FROM( SELECT ccode, cid, cname, carea, ccontent, cposter ");
				sql.append(" 				 FROM club ");
				if (col.equals("cname")) {
					str += "WHERE cname LIKE '%" + word + "%'";
				} else if (col.equals("cid")) {
					str += "WHERE cid LIKE '%" + word + "%'";
				} else if (col.equals("carea")) {
					str += "WHERE carea LIKE '%" + word + "%'";
				}//if end
				sql.append(str);
				sql.append(" 				ORDER BY ccode DESC) A, (SELECT @ROWNUM :=0) ");
				sql.append(" B)C ");
				sql.append(" WHERE C.ROW>=" + startRow + " AND C.ROW<=" + endRow + " AND NOT C.ccode='c00000000000' ");
			} // if end
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				club = new ArrayList<ClubDTO>();
				do {
					dto = new ClubDTO();
					dto.setCcode(rs.getString("ccode"));
					dto.setCid(rs.getString("cid"));
					dto.setCname(rs.getString("cname"));
					dto.setCarea(rs.getString("carea"));
					dto.setCcontent(rs.getString("ccontent"));
					dto.setCposter(rs.getString("cposter"));
					club.add(dto);
				} while (rs.next());
			} else {
				club = null;
			} // if end
		}catch (Exception e) {
			System.out.println("list() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}
		return club;
	}//list() end
	
	public int count(String col, String word) {
		int res = 0;
		word = word.trim();
		String str = "";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT count(ccode) FROM club ");
			if (word.length() >= 1) {
				if (col.equals("cid")) {
					str += " WHERE cid LIKE '%" + word + "%'";
				} else if (col.equals("carea")) {
					str += " WHERE carea LIKE '%" + word + "%'";
				} else if (col.equals("cname")) {
					str += " WHERE cname LIKE '%" + word + "%'";
				}
				sql.append(str);
			} // if end
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				res = rs.getInt("count(ccode)");
			} // if end

		} catch (Exception e) {
			System.out.println("클럽 count() 실패");
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return res;
	}// count() end
	
	public int create(ClubDTO dto) {
		int res=0;
		String ccode="";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" INSERT INTO club(ccode, cid, cname, carea, ccontent, cposter) ");
			sql.append(" VALUE(( ");
			sql.append(" select concat('c',(cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8))), ");
			sql.append("		(select if( ");
			sql.append("				   ( ");
			sql.append("				    select ifnull(max(ccode) COLLATE utf8_general_ci , (select concat('t',(select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)))) ) ) from club as T ");
			sql.append("				    where cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)) = (select substring(ifnull(max(ccode),1),2,8) from club as T2) COLLATE utf8_general_ci ");
			sql.append("				   ) =(select max(ccode) from club as T3) COLLATE utf8_general_ci, ");
			sql.append("				   (select right(cast(((select right(max(ccode),3) from club as T4)+1)+1000 as char(4)),3)), ");
			sql.append("				   '001' ");
			sql.append("				  ) ");
			sql.append("		)) ");
			sql.append(" ),?,?,?,?,?      ) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getCid());
			pstmt.setString(2, dto.getCname());
			pstmt.setString(3, dto.getCarea());
			pstmt.setString(4, dto.getCcontent());
			pstmt.setString(5, dto.getCposter());
			res=pstmt.executeUpdate();
			if(res==1) {
				res=0;
				sql.delete(0, sql.length());
				sql.append(" select ccode FROM club where cid=? ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, dto.getCid());
				rs=pstmt.executeQuery();
				if(rs.next()) {
					ccode=rs.getString("ccode");
					if(!ccode.equals("")) {
						sql.delete(0, sql.length());
						sql.append(" UPDATE member ");
						sql.append(" SET ccode=?    ");
						sql.append(" WHERE id=? ");
						pstmt=con.prepareStatement(sql.toString());
						pstmt.setString(1, ccode);
						pstmt.setString(2, dto.getCid());						
						res=pstmt.executeUpdate();
						if(res==1) {
							res=0;
							sql.delete(0, sql.length());
							sql.append(" INSERT INTO cmember(ccode, cid) ");
							sql.append(" value(?,?) ");
							pstmt=con.prepareStatement(sql.toString());
							pstmt.setString(1, ccode);
							pstmt.setString(2, dto.getCid());
							res=pstmt.executeUpdate();
						}
					}
				}
			}//if end
		} catch (Exception e) {
			System.out.println("게시글 등록 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt);
		}
		return res;
	}//create() end
	
	public String ccode(String id) {
		String ccode="";
		try {
				con = dbopen.getConnection();
				sql = new StringBuffer();
				sql.append(" SELECT ccode ");
				sql.append(" FROM member ");
				sql.append(" WHERE id=? ");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					ccode=rs.getString("ccode");
				}
				
		} catch (Exception e) {
			System.out.println("ccode() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt);
		}
		
		return ccode;
	}//ccode() end
	
	public String alreadyclub(String id) {
		String ccode="";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT ccode FROM member WHERE id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ccode=rs.getString("ccode");
			}//if end
			
		}catch (Exception e) {
			System.out.println("alreadyclub() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}
		return ccode;
	}//alreadyclub() end
	
	public ClubDTO read(String ccode) {
		ClubDTO dto =null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT ccode, cid, cname, carea, ccontent, cposter "); 
			sql.append(" FROM club "); 
			sql.append(" WHERE ccode=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, ccode);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto = new ClubDTO();
				dto.setCcode(rs.getString("ccode"));
				dto.setCid(rs.getString("cid"));
				dto.setCname(rs.getString("cname"));
				dto.setCarea(rs.getString("carea"));
				dto.setCcontent(rs.getString("ccontent"));
				dto.setCposter(rs.getString("cposter"));
			}//if end
		}catch (Exception e) {
			System.out.println("공지사항 read() 실패!!"+ e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}
		return dto;
	}//read() end
	
	public ArrayList<Map<String,String>> cmread(String ccode) {
		ArrayList<Map<String,String>> cmem = null;
		Map<String, String> map = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT DISTINCT A.cid, ifnull(sum(E.shooting+E.stamina+E.manner+E.pass)/(count(A.cid)*4), 0) as avg ");
			sql.append("   FROM (SELECT DISTINCT TM.cid, TM.ccode ");
			sql.append(" 	  	 FROM cmember TM JOIN club M  ") ;
			sql.append("         on TM.ccode=M.ccode where M.ccode=?) A ");
			sql.append(" LEFT JOIN playeravg E ON A.cid=E.player "); 
			sql.append(" group by A.cid ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, ccode);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cmem = new ArrayList<>();
				do {
					map = new HashMap<>();
					map.put("cid", rs.getString("A.cid"));
					map.put("avg", rs.getString("avg"));	
					cmem.add(map);
				}while(rs.next());
			}
			
		} catch (Exception e) {
			System.out.println("cmread()실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}		
		return cmem;
	}//class end
	
	public int clubBan(String cid) {
		int res=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" DELETE FROM cmember WHERE cid=? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, cid);
			res=pstmt.executeUpdate();		
			if(res==1) {
				res=0;
				sql.delete(0, sql.length());
				sql.append(" UPDATE member ");
				sql.append(" SET ccode='C00000000000' ");
				sql.append(" WHERE id=? ");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, cid);
				res=pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("clubBan()실패!!"+e);
		}finally {
			dbclose.close(con,pstmt);
		}		
		return res;
	}//clubBan() end
	
	public int noclub(String id) {
		int no=0;
		String ccode="";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT ccode FROM member WHERE id=? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ccode=rs.getString("ccode");
				if(ccode.equals("C00000000000")) {
					no=0;
				}else {
					no=1;
				}
			}
		} catch (Exception e) {
			System.out.println("noJoin()실패!!"+e);
		}finally {
			dbclose.close(con,pstmt);
		}		
		return no;
	}//clubJoin() end
	
	public int clubJoin(String id, String ccode) {
		int res=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" INSERT INTO cmember(cid,ccode) ");
			sql.append(" VALUE(?,?) ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, ccode);
			res=pstmt.executeUpdate();
			if(res==1) {
				res=0;
				sql.delete(0, sql.length());
				sql.append(" UPDATE member ");
				sql.append(" SET ccode=? ");
				sql.append(" WHERE id=? ");
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, ccode);
				pstmt.setString(2, id);
				res=pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("clubJoin()실패!!"+e);
		}finally {
			dbclose.close(con,pstmt);
		}		
		return res;
	}//clubJoin() end
	
	

}//class end
