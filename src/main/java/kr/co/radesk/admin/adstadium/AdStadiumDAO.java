package kr.co.radesk.admin.adstadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.SessionScope;

import kr.co.radesk.admin.adground.AdgroundDTO;
import kr.co.radesk.admin.adstadium.AdStadiumDTO;
import kr.co.radesk.member.MemberDTO;
import kr.co.radesk.stadiumrating.StadiumRatingDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class AdStadiumDAO {
	@Autowired
	DBOpen dbopen;
	@Autowired
	DBClose dbclose;

	Connection con = null;
	PreparedStatement pstmt = null;
	StringBuffer sql = null;
	ResultSet rs = null;
	ArrayList<AdStadiumDTO> list = null;
	ArrayList<ToRe> tr = null;

	public AdStadiumDAO() {
		System.out.println("AdnoticeDAO() 생성");
	}// Adn oticeDao() end

	/*
	 * 목록---------------------------------------------------------------------------
	 */
	public ArrayList<AdStadiumDTO> list(AdStadiumDTO dto, String col, String word, int nowPage, int recordPerPage) {
		int startRow = ((nowPage-1)*recordPerPage)+1;
		int endRow = nowPage *recordPerPage;
		String str="";
		
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			
			if(word.length()==0) {				
				sql.append(" SELECT stacode, staname, staaddr1, staaddr2, staaddr3, stapay, stadate, staclose, staopen,  stalevel, poster1,poster2,poster3,poster4,poster5,id"); 
				sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, stacode, staname, staaddr1, staaddr2, staaddr3, stapay, stadate, staclose, staopen, stalevel, poster1,poster2,poster3,poster4,poster5,id"); 
				sql.append(" 		FROM(SELECT stacode, staname, staaddr1, staaddr2, staaddr3, stapay, stadate, staclose, staopen,  stalevel, poster1,poster2,poster3,poster4,poster5,id");
				sql.append(" 				FROM stadium ");
				sql.append(" 				ORDER BY stacode DESC) A, (SELECT @ROWNUM :=0) ");
				sql.append(" B)C ");
				sql.append(" WHERE C.ROW>="+startRow+" AND C.ROW<="+endRow );
			} else {
				sql.append(" SELECT stacode, staname, staaddr1, staaddr2, staaddr3, stapay, stadate, staclose, staopen,  stalevel, poster1,poster2,poster3,poster4,poster5,id"); 
				sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, stacode, staname, staaddr1, staaddr2, staaddr3, stapay, stadate, staclose, staopen,  stalevel, poster1,poster2,poster3,poster4,poster5,id"); 
				sql.append(" 		FROM(SELECT stacode, staname, staaddr1, staaddr2, staaddr3, stapay, stadate, staclose, staopen, stalevel, poster1,poster2,poster3,poster4,poster5,id");
				sql.append(" 				FROM stadium");
				if(col.equals("staname")) {
					str+=" WHERE stalevel='Y' AND staname LIKE '%"+word+"%' ";
				}else if(col.equals("staaddr1")){
					str+=" WHERE stalevel='Y' AND staaddr1 LIKE '%"+word+"%' ";
				}//if end
				sql.append(str);
				sql.append(" 				ORDER BY stacode DESC) A, (SELECT @ROWNUM :=0) ");
				sql.append(" B)C ");
				sql.append(" WHERE C.ROW>="+startRow+" AND C.ROW<="+endRow );
			}//if end
			
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<AdStadiumDTO>();
				do {
					dto = new AdStadiumDTO();
					dto.setStacode(rs.getInt("stacode"));
					dto.setStaname(rs.getString("staname"));
					dto.setStaaddr1(rs.getString("staaddr1"));
					dto.setStaaddr2(rs.getString("staaddr2"));
					dto.setStaaddr3(rs.getString("staaddr3"));
					dto.setStapay(rs.getInt("stapay"));
					dto.setStadate(rs.getString("stadate"));
					dto.setStaclose(rs.getInt("staclose"));
					dto.setStaopen(rs.getInt("staopen"));
					dto.setStalevel(rs.getString("stalevel"));
					dto.setPoster1(rs.getString("poster1"));
					dto.setPoster2(rs.getString("poster2"));
					dto.setPoster3(rs.getString("poster3"));
					dto.setPoster4(rs.getString("poster4"));
					dto.setPoster5(rs.getString("poster5"));
					dto.setId(rs.getString("id"));
					list.add(dto);
					System.out.println(dto.getPoster1());
				} while (rs.next());
			} else {
				list = null;
			}
		} catch (Exception e) {
			System.out.println("list() 실패!!!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return list;
	}// list() end
	/*
	 * 등록---------------------------------------------------------------------------
	 */

	public int create(AdStadiumDTO dto) {
		int res = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" INSERT INTO stadium(stacode, staname, staaddr1,staaddr2,stainfo,stapay,stadate,staopen,staclose,stalevel,poster1,staaddr3,id,poster2,poster3,poster4,poster5)");
			sql.append(" VALUE((SELECT IFNULL(MAX(stacode),0)+1 FROM stadium AS TB),?,?,?,?,?,now(),?,?,'Y',?,?,?,?,?,?,?)");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getStaname());
			pstmt.setString(2, dto.getStaaddr1());
			pstmt.setString(3, dto.getStaaddr2());
			pstmt.setString(4, dto.getStainfo());
			pstmt.setInt(5, dto.getStapay());
			pstmt.setInt(6, dto.getStaopen());
			pstmt.setInt(7, dto.getStaclose());
			pstmt.setString(8, dto.getPoster1());
			pstmt.setString(9, dto.getStaaddr3());
			pstmt.setString(10, dto.getId());
			pstmt.setString(11, dto.getPoster2());
			pstmt.setString(12, dto.getPoster3());
			pstmt.setString(13, dto.getPoster4());
			pstmt.setString(14, dto.getPoster5());
		

			res = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글 등록 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt);
		}
		return res;
	}

	/*
	 * 상세보기-------------------------------------------------------------------------
	 * --
	 */
	public AdStadiumDTO read(int stacode) {
		AdStadiumDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT stacode,staname, staaddr1,staaddr2,stapay,stadate,staclose,staopen,stainfo,stalevel,poster1,staaddr3,id,poster2,poster3,poster4,poster5 ");
			sql.append(" FROM stadium WHERE stacode=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, stacode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new AdStadiumDTO();
				do {
					dto.setStacode(rs.getInt("stacode"));
					dto.setStaname(rs.getString("staname"));
					dto.setStaaddr1(rs.getString("staaddr1"));
					dto.setStaaddr2(rs.getString("staaddr2"));
					dto.setStapay(rs.getInt("stapay"));
					dto.setStadate(rs.getString("stadate"));
					dto.setStaclose(rs.getInt("staclose"));
					dto.setStaopen(rs.getInt("staopen"));
					dto.setStainfo(rs.getString("stainfo"));
					dto.setStalevel(rs.getString("stalevel"));
					dto.setPoster1(rs.getString("poster1"));
					dto.setStaaddr3(rs.getString("staaddr3"));
					dto.setId(rs.getString("id"));
					dto.setPoster2(rs.getString("poster2"));
					dto.setPoster3(rs.getString("poster3"));
					dto.setPoster4(rs.getString("poster4"));
					dto.setPoster5(rs.getString("poster5"));
					
					
					
					System.out.println(dto.getPoster1());
					System.out.println(dto.getPoster2());
					System.out.println(dto.getPoster3());
					System.out.println(dto.getPoster4());
					System.out.println(dto.getPoster5());
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("read() 실패!!!");
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end

	/*
	 * 비밀번호 확인
	 * ----------------------------------------------------------------------------
	 */
	public AdStadiumDTO select(AdStadiumDTO dto) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT stacode,staname, staaddr1,staaddr2,stapay,stadate,staclose,staopen,stalevel,poster,staaddr3,id,poster2,poster3,poster4,poster5");
			sql.append(" FROM stadium WHERE stacode=?");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getStacode());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setStacode(rs.getInt("stacode"));
				dto.setStaname(rs.getString("staname"));
				dto.setStaaddr1(rs.getString("staaddr1"));
				dto.setStaaddr2(rs.getString("staaddr2"));
				dto.setStapay(rs.getInt("stapay"));
				dto.setStadate(rs.getString("stadate"));
				dto.setStaclose(rs.getInt("staclose"));
				dto.setStaopen(rs.getInt("staopen"));
				dto.setStalevel(rs.getString("stalevel"));
				dto.setPoster1(rs.getString("poster1"));
				dto.setStaaddr3(rs.getString("staaddr3"));
				dto.setId(rs.getString("id"));
				dto.setPoster2(rs.getString("poster2"));
				dto.setPoster3(rs.getString("poster3"));
				dto.setPoster4(rs.getString("poster4"));
				dto.setPoster5(rs.getString("poster5"));
				
			} else {
				dto = null;
			}
		} catch (Exception e) {
			System.out.println("회원정보 불러오기 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		// System.out.println(dto);
		return dto;
	} // select() end

	/*
	 * 수정-----------------------------------------------------------------------------

	 */
	public int staupdate(AdStadiumDTO dto) {
		int res=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" UPDATE stadium SET staname=?,staaddr1=?, staaddr2=?,stainfo=?,stapay=?,staopen=?,staclose=?,staaddr3=?,id=?,stadate=now(),stalevel='Y',poster1=?,poster2=?,poster3=?,poster4=?,poster5=? ");
			sql.append(" WHERE stacode = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getStaname());
			pstmt.setString(2, dto.getStaaddr1());
			pstmt.setString(3, dto.getStaaddr2());
			pstmt.setString(4, dto.getStainfo());
			pstmt.setInt(5, dto.getStapay());
			pstmt.setInt(6, dto.getStaopen());
			pstmt.setInt(7, dto.getStaclose());
			pstmt.setString(8, dto.getStaaddr3());
			pstmt.setString(9, dto.getId());
			pstmt.setString(10, dto.getPoster1());
			pstmt.setString(11, dto.getPoster2());
			pstmt.setString(12, dto.getPoster3());
			pstmt.setString(13, dto.getPoster4());
			pstmt.setString(14, dto.getPoster5());
			pstmt.setInt(15, dto.getStacode());
			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("update() 실패!!!");
			System.out.println(e);
			
		}finally {
			dbclose.close(con,pstmt);
		}
		return res;
	}//delete() end

	/* 삭제 ---------------------------------------------------------------------- */
	public int stadelete(AdStadiumDTO dto) {
		int res=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" UPDATE stadium SET stalevel='N' ");
			sql.append(" WHERE stacode = ?");
			pstmt = con.prepareStatement(sql.toString());
			//pstmt.setString(8, dto.getPoster());
			pstmt.setInt(1, dto.getStacode());
			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("delete() 실패!!!");
			System.out.println(e);
			
		}finally {
			dbclose.close(con,pstmt);
		}
		return res;
	}
	
	public int count(String col, String word) {
		int totalRecord=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT count(stacode) as count FROM stadium ");		
			
			if(word.length()>=1) {
				String search="";
				if(col.equals("staname")) {
					search+=" WHERE staname LIKE '%"+word+"%' ";
				}else if(col.equals("staaddr1")) {
					search+=" WHERE staaddr1 LIKE '%"+word+"%' ";
				}//if end
				sql.append(search);
			}//if end

			pstmt = con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalRecord=rs.getInt("count");
			}//if end
			
		}catch (Exception e) {
			System.out.println("count() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}
		
		return totalRecord;
	}//count() end
	
	
	public int count2(String col, String word, String id) {
		int totalRecord2=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT count(stacode) as count FROM stadium ");		
			sql.append(" WHERE id=? ");	
			
			if(word.length()>=1) {
				String search="";
				if(col.equals("staname")) {
					search+=" AND staname LIKE '%"+word+"%' ";
				}else if(col.equals("staaddr1")) {
					search+=" AND staaddr1 LIKE '%"+word+"%' ";
				}//if end
				sql.append(search);
			}//if end
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalRecord2=rs.getInt("count");
			}//if end
			
		}catch (Exception e) {
			System.out.println("count2() 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}
		
		return totalRecord2;
	}//count() end

	
	
	
	
	
	
	// 구장 이용자 집계
	public ArrayList<ToRe> tore(int stacode) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("select A.stacode as stacode, A.pid as id, count(A.pid) as cnt");
			sql.append(" from (select stacode, pid from payment where stacode=?) A group by A.pid order by cnt desc");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, stacode);

			rs=pstmt.executeQuery();
			if (rs.next()) {
				tr = new ArrayList<ToRe>();
				do {
					ToRe tore = new ToRe();
					tore.setStacode(rs.getInt("stacode"));
					tore.setId(rs.getString("id"));
					tore.setCnt(rs.getInt("cnt"));

					tr.add(tore);
					System.out.println(tore.getId());
				} while (rs.next());
			} else {
				tr = null;
			}
			
		}catch (Exception e) {
			System.out.println("이용자 집계 실패!!"+e);
		}finally {
			dbclose.close(con,pstmt,rs);
		}

		return tr;
	}//count() end
}// class end
