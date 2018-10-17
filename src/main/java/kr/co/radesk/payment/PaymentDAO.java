package kr.co.radesk.payment;

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
import kr.co.radesk.stadiumrating.StadiumRatingDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class PaymentDAO {
	@Autowired
	DBOpen dbopen;
	@Autowired
	DBClose dbclose;

	Connection con = null;
	PreparedStatement pstmt = null;
	StringBuffer sql = null;
	ResultSet rs = null;
	ArrayList<PaymentDTO> list = null;
	private ArrayList<PaymentDTO> plist;
	private ArrayList<StadiumRatingDTO> rlist;
	ArrayList<PaymentDTO> ptimelist = null;

	public PaymentDAO() {
		System.out.println("AdnoticeDAO() 생성");
	}// Adn oticeDao() end

	/*
	 * 목록---------------------------------------------------------------------------
	 */
	public ArrayList<PaymentDTO> dateRead(int stacode, int grcode, String date) {
		PaymentDTO dto = null;
		ptimelist = new ArrayList<PaymentDTO>();

		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT ptime");
			sql.append(" FROM payment");
			sql.append(" WHERE stacode=? AND grcode=? AND pdate=?");

			pstmt = con.prepareStatement(sql.toString());

			pstmt.setInt(1, stacode);
			pstmt.setInt(2, grcode);
			pstmt.setString(3, date);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					dto = new PaymentDTO();
					dto.setPtime(rs.getString("ptime"));

					ptimelist.add(dto);
				} while (rs.next());
			} else {
				ptimelist = null;
			}
		} catch (Exception e) {
			System.out.println("결제 된 시간 조회 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return ptimelist;
	}
	
	public ArrayList<PaymentDTO> paylist(String id){   
	    try{
	      con=dbopen.getConnection();
	      sql=new StringBuffer();      
	      sql.append(" SELECT pcode,stacode, grcode, pdate, ptime, cost, pid, costdate,avg");
	      sql.append(" FROM payment where id=?");
	      sql.append(" ORDER BY pcode DESC");
	      
	      pstmt=con.prepareStatement(sql.toString());
	      pstmt.setString(1, id);
	      rs=pstmt.executeQuery();
	      if(rs.next()){
	        plist=new ArrayList<PaymentDTO>();
	        do{
	        	PaymentDTO dto=new PaymentDTO();
	          dto.setPcode(rs.getString("pcode"));
	          dto.setStacode(rs.getInt("stacode"));
	          dto.setGrcode(rs.getInt("grcode"));
	          dto.setPdate(rs.getString("pdate"));
	          dto.setPtime(rs.getString("ptime"));
	          dto.setCost(rs.getInt("cost"));
	          dto.setPid(rs.getString("pid"));
	          dto.setCostdate(rs.getString("costdate"));
	          dto.setAvg(rs.getString("avg"));
	          plist.add(dto);
	        }while(rs.next());
	      }
	      
	    }catch(Exception e) {
	      System.out.println("실패: "+e);
	    }finally{
	      dbclose.close(con, pstmt, rs);
	    }    
	    return plist;
	  }//list() end 


	
	
	public int create(BasketDTO dto, int bno, String pid) {
		int res = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" insert into payment(pcode,stacode, grcode, pdate, ptime, cost, pid, costdate) ");
			sql.append(" select (select concat((CAST(DATE_FORMAT(now(),'%Y%m%d%H%i') AS CHAR(12))),(select if(( ");
			sql.append("         select ifnull(max(pcode), (select concat((select CAST(DATE_FORMAT(now(),'%Y%m%d%H%i')  ");
			sql.append("         AS CHAR(12)))) ) ) from payment as P1 where CAST(DATE_FORMAT(now(),'%Y%m%d%H%i')  ");
			sql.append("         AS CHAR(12)) = (select substring(ifnull(max(pcode),1),1,12) from payment as P2 )  ");
			sql.append("         COLLATE utf8_general_ci) COLLATE utf8_general_ci =(select max(pcode) from payment as P3) "); 
			sql.append("         COLLATE utf8_general_ci,(select right(cast(((select right(max(pcode),3) "); 
			sql.append("         from payment as P4)+1)+1000 as char(4)),3)),'001')))), "); 
			sql.append("         stacode,grcode, pdate, ptime, cost, pid, now() from basket  ");
			sql.append(" where pid=? AND bno=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pid);
			pstmt.setInt(2, bno);
			res = pstmt.executeUpdate();

			sql.delete(0, sql.length());
			sql.append("delete from basket where bno=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, bno);
			res = pstmt.executeUpdate();
			


		} catch (Exception e) {
			System.out.println("결제 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt);
		}
		return res;
	}

	//
	public ArrayList<PaymentDTO> paymentlist(String id) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();

			sql.append(" select A.pcode as apcode, A.pid as pid, A.stacode as stacode, A.grcode as grcode, A.pdate as pdate, A.ptime as ptime, A.cost as cost, A.costdate as costdate, S.pcode as spcode");
			sql.append(" from (select *");
			sql.append(" from payment where pid = ?) A left join stadiumrating S");
			sql.append(" on A.pcode = S.pcode");
		    
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				plist = new ArrayList<PaymentDTO>();
				do {
					PaymentDTO dto = new PaymentDTO();
					dto.setPcode(rs.getString("apcode"));
					dto.setPid(rs.getString("pid"));
					dto.setStacode(rs.getInt("stacode"));
					dto.setGrcode(rs.getInt("grcode"));
					dto.setPdate(rs.getString("pdate"));
					dto.setPtime(rs.getString("ptime"));
					dto.setCost(rs.getInt("cost"));					
					dto.setCostdate(rs.getString("costdate"));
					dto.setAvg(rs.getString("spcode"));

					plist.add(dto);
				} while (rs.next());
			} else {
				plist = null;
			}
		} catch (Exception e) {
			System.out.println("결제 목록 가져오기 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}

		return plist;
	}

	public AdgroundDTO callgroundinfo() {
		AdgroundDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" select ground.grcode, ground.grname ");
			sql.append(" from ground ");
			sql.append(" inner join payment on ground.stacode = payment.stacode ");
			
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
			sql.append(" inner join payment on stadium.id = payment.pid ");
			
			

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
