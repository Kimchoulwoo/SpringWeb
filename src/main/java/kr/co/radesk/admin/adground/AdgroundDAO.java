package kr.co.radesk.admin.adground;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.radesk.admin.adstadium.AdStadiumDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class AdgroundDAO {
	@Autowired
	DBOpen dbopen;
	@Autowired
	DBClose dbclose;

	Connection con = null;
	PreparedStatement pstmt = null;
	StringBuffer sql = null;
	ResultSet rs = null;
	ArrayList<AdStadiumDTO> list = null;

	public AdgroundDAO() {
		System.out.println("AdgroundDAO() 생성");
	}// Adn oticeDao() end

	// 리스트---------------------------------------------------------
	public ArrayList<AdgroundDTO> list(int stacode) {
		AdgroundDTO dto = new AdgroundDTO();
		ArrayList<AdgroundDTO> list = null;
		StringBuffer sql = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" select stacode,grcode,grname,grday,grtime,grlevel ");
			sql.append(" from ground ");
			sql.append(" where stacode=? ");
			sql.append(" order by grcode desc ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, stacode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<AdgroundDTO>();
				do {
					dto = new AdgroundDTO();
					dto.setStacode(rs.getInt("stacode"));
					dto.setGrcode(rs.getInt("grcode"));
					dto.setGrname(rs.getString("grname"));
					dto.setGrday(rs.getString("grday"));
					dto.setGrtime(rs.getString("grtime"));
					dto.setGrlevel(rs.getString("grlevel"));
					list.add(dto);
				} while (rs.next());
			} else {
				list = null;
			}

		} catch (Exception e) {
			System.out.println("실패: " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return list;
	}// list() end
		// 등록
		// ---------------------------------------------------------------------------

	public int create(AdgroundDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" INSERT INTO ground(stacode,grcode,grday,grname,grtime,grlevel)");
			sql.append(" VALUES(?,(select ifnull(max(grcode),0)+1 from ground as TB),");
			sql.append("             ?,?,?,'Y')");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getStacode());
			pstmt.setString(2, dto.getGrday());
			pstmt.setString(3, dto.getGrname());
			pstmt.setString(4, dto.getGrtime());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			dbclose.close(con, pstmt);
		}
		return cnt;
	}// create() end
	
	public AdStadiumDTO callopenclose(int stacode) {
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
					dto.setPoster2(rs.getString("poster3"));
					dto.setPoster2(rs.getString("poster4"));
					dto.setPoster2(rs.getString("poster5"));
					
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("read() 실패!!!");
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end


	// 수정 삭제 페이지로 이동 하는 dao
	public ArrayList<AdgroundDTO> Updatelist(int stacode) {
		AdgroundDTO dto = new AdgroundDTO();
		ArrayList<AdgroundDTO> list = null;
		StringBuffer sql = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" select stacode,grcode,grname,grday,grtime,grlevel ");
			sql.append(" from ground ");
			sql.append(" where stacode=? ");
			sql.append(" order by grcode desc ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, stacode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<AdgroundDTO>();
				do {
					dto = new AdgroundDTO();
					dto.setStacode(rs.getInt("stacode"));
					dto.setGrcode(rs.getInt("grcode"));
					dto.setGrname(rs.getString("grname"));
					dto.setGrday(rs.getString("grday"));
					dto.setGrtime(rs.getString("grtime"));
					dto.setGrlevel(rs.getString("grlevel"));
					list.add(dto);
				} while (rs.next());
			} else {
				list = null;
			}

		} catch (Exception e) {
			System.out.println("실패: " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return list;
	}// list() end

	// 수정 폼으로 이동
	public AdgroundDTO grread(int grcode) {
		AdgroundDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT stacode,grcode,grname,grday,grtime,grlevel ");
			sql.append(" FROM ground WHERE grcode=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, grcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new AdgroundDTO();
				do {
					dto.setStacode(rs.getInt("stacode"));
					dto.setGrcode(rs.getInt("grcode"));
					dto.setGrname(rs.getString("grname"));
					dto.setGrday(rs.getString("grday"));
					dto.setGrtime(rs.getString("grtime"));
					dto.setGrlevel(rs.getString("grlevel"));
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("read() 실패!!!");
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end

	public AdStadiumDTO stadiuminfo(int stacode) {
		AdStadiumDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT stacode,staopen,staclose ");
			sql.append(" FROM stadium WHERE stacode=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, stacode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new AdStadiumDTO();
				do {
					dto.setStacode(rs.getInt("stacode"));
					dto.setStaopen(rs.getInt("staopen"));
					dto.setStaclose(rs.getInt("staclose"));
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("stadiuminfo 경기장 정보 불러오기 실패!!!"+e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end
	
	
	
	// 수정 시작
	public int grupdate(AdgroundDTO dto) {
		int res = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" update ground ");
			sql.append(" set grname=?,grday=?,grtime=?,grlevel='Y' ");
			sql.append(" where grcode=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getGrname());
			pstmt.setString(2, dto.getGrday());
			pstmt.setString(3, dto.getGrtime());
			;
			// pstmt.setString(8, dto.getPoster());
			pstmt.setInt(4, dto.getGrcode());
			res = pstmt.executeUpdate();
			System.out.println("업데이트에서의 레벨" + dto.getGrlevel());

		} catch (Exception e) {
			System.out.println("update() 실패!!!");
			System.out.println(e);

		} finally {
			dbclose.close(con, pstmt);
		}
		return res;
	}// delete() end

	// 삭제 시작
	public int grdelete(AdgroundDTO dto) {
		int res = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" UPDATE ground SET grlevel='N' ");
			sql.append(" WHERE grcode = ?");
			pstmt = con.prepareStatement(sql.toString());
			// pstmt.setString(8, dto.getPoster());
			pstmt.setInt(1, dto.getGrcode());
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("update() 실패!!!");
			System.out.println(e);

		} finally {
			dbclose.close(con, pstmt);
		}
		return res;
	}
	
	
	// 전체 삭제 폼으로 이동
	public AdgroundDTO grAllDelete(int stacode) {
		AdgroundDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT stacode,grcode,grname,grday,grtime,grlevel ");
			sql.append(" FROM ground WHERE stacode=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, stacode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new AdgroundDTO();
				do {
					dto.setStacode(rs.getInt("stacode"));
					dto.setGrcode(rs.getInt("grcode"));
					dto.setGrname(rs.getString("grname"));
					dto.setGrday(rs.getString("grday"));
					dto.setGrtime(rs.getString("grtime"));
					dto.setGrlevel(rs.getString("grlevel"));
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("read() 실패!!!");
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end



	// 경기장 전체 삭제 시작

}// class end
