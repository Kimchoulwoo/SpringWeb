package kr.co.radesk.stadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.radesk.admin.adground.AdgroundDTO;
import kr.co.radesk.admin.adstadium.AdStadiumDTO;
import kr.co.radesk.member.MemberDTO;
import kr.co.radesk.stadiumrating.StadiumRatingDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class StadiumDAO {
	@Autowired
	DBOpen dbopen;
	@Autowired
	DBClose dbclose;

	Connection con = null;
	PreparedStatement pstmt = null;
	StringBuffer sql = null;
	ResultSet rs = null;
	ArrayList<AdStadiumDTO> list = null;

	public StadiumDAO() {
		System.out.println("AdnoticeDAO() 생성");
	}// Adn oticeDao() end

	/*
	 * 목록---------------------------------------------------------------------------
	 */

	public AdgroundDTO grRVread(int grcode) {
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
	
	
	public AdStadiumDTO staRVread(int stacode) {
		AdStadiumDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT stacode,staname, staaddr1,staaddr2,staaddr3,stapay,stadate,staclose,staopen,stainfo,stalevel,poster1,poster2,poster3,poster4,poster5 ");
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
					dto.setStaaddr3(rs.getString("staaddr3"));
					dto.setStapay(rs.getInt("stapay"));
					dto.setStadate(rs.getString("stadate"));
					dto.setStaclose(rs.getInt("staclose"));
					dto.setStaopen(rs.getInt("staopen"));
					dto.setStainfo(rs.getString("stainfo"));
					dto.setStalevel(rs.getString("stalevel"));
					dto.setPoster1(rs.getString("poster1"));
					dto.setPoster2(rs.getString("poster2"));
					dto.setPoster3(rs.getString("poster3"));
					dto.setPoster4(rs.getString("poster4"));
					dto.setPoster5(rs.getString("poster5"));
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("read() 실패!!!");
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end
	
	
	public double totalpoint (int stacode) {
		double cnt=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" select ifnull(sum(staaccess+staclean+stasafety+stafacility)/(count(stacode)*4) , 0) as avg");
			sql.append(" from stadiumrating where stacode=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, stacode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt=rs.getDouble("avg");
			}
		} catch (Exception e) {
			System.out.println("전체평균 불러오기 실패" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return cnt;
	}
	

	



}// class end