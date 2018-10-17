package kr.co.radesk.stadiumrating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.radesk.admin.adground.AdgroundDTO;
import kr.co.radesk.admin.adstadium.AdStadiumDTO;
import kr.co.radesk.basket.BasketDTO;
import kr.co.radesk.member.MemberDTO;
import kr.co.radesk.payment.PaymentDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class StadiumRatingDAO {
	@Autowired
	DBOpen dbopen;
	@Autowired
	DBClose dbclose;

	Connection con = null;
	PreparedStatement pstmt = null;
	StringBuffer sql = null;
	ResultSet rs = null;
	ArrayList<StadiumRatingDTO> ratinglist = null;

	public StadiumRatingDAO() {
		System.out.println("AdnoticeDAO() 생성");
	}// Adn oticeDao() end

	/*
	 * 등록---------------------------------------------------------------------------
	 */

	public int ratingcreate(StadiumRatingDTO dto, String pcode) {
		int res = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			/*
			 * sql.
			 * append(" insert into stadiumrating(stacode, pcode,staaccess,staclean,stasafety,stafacility,stacoment,aid) "
			 * ); sql.append(" values(?,?,?,?,?,?,?,?) ");
			 */

			sql.append(" insert into stadiumrating(stacode,staaccess,staclean,stasafety,stafacility,stacoment,pcode,aid) ");
			sql.append(" select stacode,?,?,?,?,?,pcode,pid ");
			sql.append(" from payment ");
			sql.append(" where pcode=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getStaaccess());
			pstmt.setInt(2,dto.getStaclean());
			pstmt.setInt(3, dto.getStasafety());
			pstmt.setInt(4, dto.getStafacility());
			pstmt.setString(5, dto.getStacoment());
			pstmt.setString(6, dto.getPcode());
			
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("평가 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt);
		}
		return res;
	}

	// 평가하는 페이지
	// DAO-------------------------------------------------------------------------------
	public PaymentDTO ratingcallpaymentinfo(String pcode) {
		PaymentDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT pcode,stacode, grcode, pdate, ptime, cost, pid, costdate   ");
			sql.append(" FROM payment WHERE pcode=?");
			sql.append(" ORDER BY pcode DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new PaymentDTO();
				do {
					dto.setPcode(rs.getString("pcode"));
					dto.setStacode(rs.getInt("stacode"));
					dto.setGrcode(rs.getInt("grcode"));
					dto.setPdate(rs.getString("pdate"));
					dto.setPtime(rs.getString("ptime"));
					dto.setCost(rs.getInt("cost"));
					dto.setPid(rs.getString("pid"));
					dto.setCostdate(rs.getString("costdate"));
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("read() 실패!!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end

	public AdgroundDTO ratingcallgroundinfo(int grcode) {
		AdgroundDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT grcode,grname ");
			sql.append(" FROM ground ");
			sql.append(" where grcode=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, grcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new AdgroundDTO();
				do {
					dto.setGrcode(rs.getInt("grcode"));
					dto.setGrname(rs.getString("grname"));
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("경기장 정보 read() 실패!!!");
			System.out.println(e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end

	public AdStadiumDTO ratingcallstadiuminfo(int stacode) {
		AdStadiumDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();

			sql.append(" SELECT stacode,staname,staaddr1,staaddr2,staaddr3 ");
			sql.append(" FROM stadium ");
			sql.append(" where stacode=? ");

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
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("구장 평가 정보 read 실패!!!");
			System.out.println(e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end
	
	public AdStadiumDTO callstadiuminfo() {
		AdStadiumDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();

			sql.append(" select stadium.stacode, stadium.staname ");
			sql.append(" from stadium ");
			sql.append(" inner join stadiumrating on stadium.id = stadiumrating.aid ");
			
			

			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new AdStadiumDTO();
				do {
					dto.setStacode(rs.getInt("stacode"));
					dto.setStaname(rs.getString("staname"));
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("결제2 구장 정보 read 실패!!!");
			System.out.println(e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end
	
	

	

	
	

}// class end
