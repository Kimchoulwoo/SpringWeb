package kr.co.radesk.basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.radesk.admin.adground.AdgroundDTO;
import kr.co.radesk.admin.adstadium.AdStadiumDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class BasketDAO {
	@Autowired
	DBOpen dbopen;
	@Autowired
	DBClose dbclose;

	Connection con = null;
	PreparedStatement pstmt = null;
	StringBuffer sql = null;
	ResultSet rs = null;
	ArrayList<BasketDTO> list = null;
	ArrayList<EtcDTO> namelist = null;

	public BasketDAO() {
		System.out.println("AdgroundDAO() 생성");
	}// Adn oticeDao() end

	public int create(BasketDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" insert into basket(stacode,grcode,pdate,ptime,cost,basketdate,pid)");
			sql.append(" VALUES(?,?,?,?,?,now(),? ) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getStacode());
			pstmt.setInt(2, dto.getGrcode());
			pstmt.setString(3, dto.getPdate());
			pstmt.setString(4, dto.getPtime());
			pstmt.setInt(5, dto.getCost());
			pstmt.setString(6, dto.getPid());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(e);
		} finally {
			dbclose.close(con, pstmt);
		}
		return cnt;
	}// create() end
	
	public ArrayList<EtcDTO> basketlist(String pid) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();

/*			sql.append(" select bno,pid,pdate,ptime,cost,basketdate,stacode,grcode ");
			sql.append(" from basket ");
			sql.append(" where pid=? ");
			sql.append(" order by bno DESC ");*/
			
		sql.append("select BB.bno as bno, BB.pid as pid, BB.pdate as pdate, BB.ptime as ptime, BB.cost as cost, BB.basketdate as basketdate, BB.stacode as stacode, BB.grcode as grcode, BB.grname as grname, BB.staname as staname");
			sql.append(" from (select AA.bno, AA.pid, AA.pdate, AA.ptime, AA.cost, AA.basketdate, AA.stacode, AA.grcode, AA.grname, S.staname");
			sql.append(" from (select B.bno, B.pid, B.pdate, B.ptime, B.cost, B.basketdate, G.stacode, G.grcode, G.grname");
			sql.append(" from basket B join ground G");
			sql.append(" on B.grcode = G.grcode) AA join stadium S");
			sql.append(" on AA.stacode = S.stacode) BB");
			sql.append(" where BB.pid=?");
			sql.append(" order by BB.basketdate desc ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				namelist = new ArrayList<EtcDTO>();
				do {
					EtcDTO dto = new EtcDTO();
					dto.setBno(rs.getInt("bno"));
					dto.setPid(rs.getString("pid"));
					dto.setPdate(rs.getString("pdate"));
					dto.setPtime(rs.getString("ptime"));
					dto.setCost(rs.getInt("cost"));
					dto.setBasketdate(rs.getString("basketdate"));
					dto.setStacode(rs.getInt("stacode"));
					dto.setGrcode(rs.getInt("grcode"));

					namelist.add(dto);
				} while (rs.next());
			}
			else {
				namelist=null;
			}
		} catch (Exception e) {
			System.out.println("namelist() 실패!!!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return namelist;
	}// list() end
	
	
	
	//결제전 경기장 상세정보
	public BasketDTO basketread(int bno) {
		BasketDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" select bno,stacode,grcode,pdate,ptime,cost,pid ");
			sql.append(" from basket where bno=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new BasketDTO();
				do {
					dto.setBno(rs.getInt("bno"));
					dto.setStacode(rs.getInt("stacode"));
					dto.setGrcode(rs.getInt("grcode"));
					dto.setPdate(rs.getString("pdate"));
					dto.setPtime(rs.getString("ptime"));
					dto.setCost(rs.getInt("cost"));
					dto.setPid(rs.getString("pid"));
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("basketread() 실패!!!");
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end
	
	public AdgroundDTO callgroundinfo() {
		AdgroundDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" select ground.grcode, ground.grname ");
			sql.append(" from ground ");
			sql.append(" inner join basket on ground.stacode = basket.stacode ");
			
			pstmt = con.prepareStatement(sql.toString());
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

	public AdStadiumDTO callstadiuminfo() {
		AdStadiumDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();

			sql.append(" select stadium.stacode, stadium.staname,stadium.staaddr1,stadium.staaddr2,stadium.staaddr3 ");
			sql.append(" from stadium ");
			sql.append(" inner join basket on stadium.id = basket.pid ");
			
			

			pstmt = con.prepareStatement(sql.toString());
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
			System.out.println("결제2 구장 정보 read 실패!!!");
			System.out.println(e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	}// read() end
	
	

}// class end
