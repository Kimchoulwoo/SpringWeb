package kr.co.radesk.match.matching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class MatchingDAO {

	@Autowired
	DBOpen dbopen;

	@Autowired
	DBClose dbclose;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;

	public MatchingDAO() {
		System.out.println("MatchingDAO() 생성");
	}// MatchingDAO() end

	//내가 속한 팀 리스트
	public ArrayList<Map<String, String>> teamList(String id) {
		ArrayList<Map<String, String>> teamlist = null;
		Map<String, String> map = null;

		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT distinct tcode, tname ");
			sql.append(" FROM team D JOIN (SELECT home ");
			sql.append("   				   FROM mbasket A JOIN (SELECT tcode FROM tmember WHERE tid=?) B ");
			sql.append("				   		ON A.home=B.tcode where A.away='yet') C ");
			sql.append(" ON D.tcode=C.home AND D.ttime > DATE_FORMAT(now(),'%Y%m%d%H') ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				teamlist = new ArrayList<Map<String, String>>();

				do {
					map = new HashMap<>();
					map.put("tcode", rs.getString("tcode"));
					map.put("tname", rs.getString("tname"));
					teamlist.add(map);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("teamList() 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return teamlist;
	}// teamList() end

	// home팀 띄우기
	public Map<String, String> homeTeam(String tcode) {
		Map<String, String> map = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();			 
			sql.append(" SELECT tid, tname, cposter, tcode FROM club D JOIN "); 
			sql.append(" 	(SELECT tid, tname, ccode, tcode ");
			sql.append(" 	FROM cmember A JOIN (SELECT tid, tname, tcode  ");
			sql.append("                   FROM team WHERE tcode=?) B ");
			sql.append(" 	ON A.cid=B.tid) C ");
			sql.append(" ON C.ccode=D.ccode ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				map = new HashMap<>();
				map.put("tid", rs.getString("tid"));
				map.put("tname", rs.getString("tname"));
				map.put("cposter", rs.getString("cposter"));
				map.put("tcode", rs.getString("tcode"));
			} // if end

		} catch (Exception e) {
			System.out.println("homeTeam() 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return map;
	}// homeTeam() end

	//상대 찾기
	public ArrayList<Map<String, String>> search(String tcode) {
		ArrayList<Map<String, String>> awaylist = null;
		Map<String, String> map = null;
		String pcode = "";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			// 1)내 결제코드가 있는지 확인
			sql.append(" SELECT pcode FROM mbasket WHERE home=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pcode = rs.getString("pcode");
			} // if end
			// 2)pcode가 있으면
			if (!pcode.equals("null")) {
				sql.delete(0, sql.length());
				sql.append(" SELECT distinct H.cposter, G.tname, G.tcode, G.tid ");
				sql.append(" FROM club H JOIN ");
				sql.append(" (SELECT ccode, tname, tcode, tid ");
				sql.append(" FROM cmember E JOIN (SELECT C.ttime, C.tarea, C.player, C.tcode, C.tname, C.tid ");
				sql.append(" 	 				 FROM (SELECT ttime, tarea, player, tcode, tname, tid ");
				sql.append(" 						   FROM team A JOIN (SELECT home FROM mbasket where pcode='null' AND away='yet') B ");
				sql.append(" 						   ON A.tcode=B.home) C JOIN (SELECT ttime, tarea, player FROM team WHERE tcode=?) D ");
				sql.append("					 ON C.ttime=D.ttime AND C.tarea=D.tarea AND C.player=D.player) F ");
				sql.append(" ON F.tid=E.cid) G ");
				sql.append(" ON G.ccode=H.ccode ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, tcode);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					awaylist = new ArrayList<>();
					do {
						map = new HashMap<>();
						map.put("cposter", rs.getString("H.cposter"));
						map.put("tname", rs.getString("G.tname"));
						map.put("tcode", rs.getString("G.tcode"));
						map.put("tid", rs.getString("G.tid"));
						awaylist.add(map);
					} while (rs.next());
				} // if end

			} else {				
				sql.delete(0, sql.length());
				sql.append(" SELECT distinct H.cposter, G.tname, G.tcode, G.tid ");
				sql.append(" FROM club H JOIN ");
				sql.append(" (SELECT ccode, tname, tcode, tid ");
				sql.append(" FROM cmember E JOIN (SELECT C.ttime, C.tarea, C.player, C.tcode, C.tname, C.tid ");
				sql.append(" 	 				 FROM (SELECT ttime, tarea, player, tcode, tname, tid ");
				sql.append(" 						   FROM team A JOIN (SELECT home FROM mbasket where NOT pcode='null' AND away='yet') B ");
				sql.append(" 						   ON A.tcode=B.home) C JOIN (SELECT ttime, tarea, player FROM team WHERE tcode=?) D ");
				sql.append("					 ON C.ttime=D.ttime AND C.tarea=D.tarea AND C.player=D.player) F ");
				sql.append(" ON F.tid=E.cid) G ");
				sql.append(" ON G.ccode=H.ccode ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, tcode);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					awaylist = new ArrayList<>();
					do {
						map = new HashMap<>();
						map.put("cposter", rs.getString("H.cposter"));
						map.put("tname", rs.getString("G.tname"));
						map.put("tcode", rs.getString("G.tcode"));
						map.put("tid", rs.getString("G.tid"));
						awaylist.add(map);
					} while (rs.next());
				} // if end
			}

		} catch (Exception e) {
			System.out.println("search() 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return awaylist;
	}// search() end

	//매치신청
	public int comeon(String homecode, String awaycode) {
		int res = 0;
		String pcode = "";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT pcode FROM mbasket WHERE home=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, homecode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pcode = rs.getString("pcode");
			} // if end

			sql.delete(0, sql.length());
			if (pcode.equals("null")) {
				sql.append(" SELECT pcode FROM mbasket WHERE home=? ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, awaycode);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					pcode = rs.getString("pcode");
				} // if end

				sql.delete(0, sql.length());
				sql.append(" UPDATE mbasket ");
				sql.append(" SET away=?, pcode=? ");
				sql.append(" where home=? ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, awaycode);
				pstmt.setString(2, pcode);
				pstmt.setString(3, homecode);
				res = pstmt.executeUpdate();
			} else {
				sql.append(" UPDATE mbasket ");
				sql.append(" SET away=? ");
				sql.append(" where home=? ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, awaycode);
				pstmt.setString(2, homecode);
				res = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("comeon() 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return res;
	}// comeon() end

	// 매치신청이 온 팀 리스트 띄우기
	public ArrayList<Map<String, String>> agreeList(String id) {
		ArrayList<Map<String, String>> agreelist = null;
		Map<String, String> map = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT distinct D.tcode, D.tname ");
			sql.append(" FROM team D JOIN (SELECT tcode ");
			sql.append("  				   FROM tmember A JOIN (SELECT away FROM mbasket WHERE NOT pcode='null' AND NOT away='yet')B ");
			sql.append("				   ON A.tcode=B.away AND A.tid=?)C ");
			sql.append(" ON D.tcode=C.tcode ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				agreelist = new ArrayList<Map<String, String>>();

				do {
					map = new HashMap<>();
					map.put("tcode", rs.getString("tcode"));
					map.put("tname", rs.getString("tname"));
					agreelist.add(map);
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("agreelist()실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return agreelist;
	}// agreeList() end

	//내팀 정보
	public Map<String, String> myTeam(String tcode) {
		Map<String, String> map = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			 
			sql.append(" SELECT cposter, tname, tcode, tid FROM club E JOIN ");
			sql.append(" 	(SELECT distinct ccode, tname, tcode, tid ");
			sql.append(" 	FROM cmember D JOIN (SELECT A.tid, A.tname, A.tcode ");
			sql.append(" 			           FROM team A JOIN (SELECT away, pcode  ");
			sql.append("			         				     FROM mbasket ");
			sql.append(" 		    	       				     WHERE NOT pcode='null' AND NOT away='yet' AND away=?) B ");
			sql.append(" 			    	   ON A.tcode=B.away) C ");
			sql.append(" 	ON C.tid=D.cid) F ");
			sql.append(" ON E.ccode=F.ccode ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				map = new HashMap<>();
				map.put("tid", rs.getString("tid"));
				map.put("tname", rs.getString("tname"));
				map.put("cposter", rs.getString("cposter"));
				map.put("tcode", rs.getString("tcode"));
			} // if end

		} catch (Exception e) {
			System.out.println("agreeTeam() 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return map;
	}// homeTeam() end

	//상대 팀 정보
	public Map<String, String> matchTeam(String tcode) {
		Map<String, String> map = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT cposter, tname, tcode, tid FROM club E JOIN ");
			sql.append(" 	(SELECT distinct ccode, tname, tcode, tid ");
			sql.append(" 	FROM cmember D JOIN (SELECT A.tid, A.tname, A.tcode ");
			sql.append(" 		           FROM team A JOIN (SELECT home, pcode  ");
			sql.append("		         				     FROM mbasket ");
			sql.append(" 		           				     WHERE NOT pcode='null' AND NOT away='yet' AND away=?) B ");
			sql.append(" 			       ON A.tcode=B.home) C ");
			sql.append(" 	ON C.tid=D.cid) F ");
			sql.append(" ON E.ccode=F.ccode ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				map = new HashMap<>();
				map.put("tid", rs.getString("tid"));
				map.put("tname", rs.getString("tname"));
				map.put("cposter", rs.getString("cposter"));
				map.put("tcode", rs.getString("tcode"));
			} // if end

		} catch (Exception e) {
			System.out.println("agreeTeam() 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return map;
	}// homeTeam() end
	
	//신청 거절
	public int refuse(String home, String away) {
		int res = 0;
		String pcode = "";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT pcode FROM team WHERE tcode=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, away);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pcode = rs.getString("pcode");
			} // if end

			sql.delete(0, sql.length());
			if (pcode.equals("null")) {
				sql.delete(0, sql.length());
				sql.append(" UPDATE mbasket ");
				sql.append(" SET away='yet' ");
				sql.append(" where home=? AND away=? ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, home);
				pstmt.setString(2, away);
				res = pstmt.executeUpdate();
			} else {
				sql.append(" UPDATE mbasket ");
				sql.append(" SET away='yet', pcode='null' ");
				sql.append(" where home=? AND away=? ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, home);
				pstmt.setString(2, away);
				res = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("comeon() 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return res;
	}// refuse() end
	
	//신청 수락
	public int agree(String home, String away) {
		int res=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" insert into matching(home, away) "); 
			sql.append(" select home, away from mbasket where away=? AND home=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, away);
			pstmt.setString(2, home);
			res=pstmt.executeUpdate();
			if(res==1) {
				res=0;
				sql.delete(0, sql.length());
				sql.append(" DELETE FROM mbasket WHERE home=? ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, home);
				res=pstmt.executeUpdate();
				if(res==1) {
					res=0;
					sql.delete(0, sql.length());
					sql.append(" DELETE FROM mbasket WHERE home=? ");
					pstmt = con.prepareStatement(sql.toString());
					pstmt.setString(1, away);
					res=pstmt.executeUpdate();
				}//if end				
			}//if end
			}catch (Exception e) {
			System.out.println("agree()실패!!"+e);
		}finally {
			dbclose.close(con,pstmt);
		}		
		return res;
	}//agree() end
	

}// class end
