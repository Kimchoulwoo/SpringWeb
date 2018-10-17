package kr.co.radesk.match.result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.radesk.match.matching.MatchingDTO;
import kr.co.radesk.match.team.TMemberDTO;
import kr.co.radesk.match.team.TeamDTO;
import kr.co.radesk.match.result.PlayerAvgDTO;
import kr.co.radesk.member.MemberDTO;
import kr.co.radesk.payment.PaymentDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class ResultDAO {
	
	@Autowired
	DBOpen dbopen;

	@Autowired
	DBClose dbclose;

	public ResultDAO() {
		System.out.println("ResultDAO() 실행");
	}

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;
	
	
	
	public ArrayList<Map<String, String>> matchlist(String id) {
		ArrayList<Map<String, String>> mlist = null;
		Map<String, String> map = null;
		
		try {
			con = dbopen.getConnection(); // DB연결
			sql = new StringBuffer();
			
			sql.append(" SELECT distinct B.tid, B.ttime, B.tname, m.mcode, m.input FROM ( ");
			sql.append("	 SELECT tid, ttime, tname, A.tcode FROM team JOIN ( ");
			sql.append("	 	SELECT tcode FROM tmember WHERE tid=? ");
			sql.append("	 		)A  ON team.tcode=A.tcode ");
			sql.append("	 			)B JOIN matching m on B.tcode=m.home or B.tcode=m.away ");
			
			pstmt = con.prepareStatement(sql.toString()); // SQL문으로 변환
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				mlist = new ArrayList<Map<String, String>>();
				do {
					map = new HashMap<>();
					map.put("tid", rs.getString("B.tid"));
					map.put("ttime", rs.getString("B.ttime"));
					map.put("tname", rs.getString("B.tname"));
					map.put("mcode", rs.getString("m.mcode"));
					map.put("input", rs.getString("m.input"));
					mlist.add(map);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("경기 목록 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return mlist;
	} //나의 매치내역 불러오기
	
	public Map<String, String>teamsRead (String mcode) {
		Map<String, String> map = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT home, away FROM matching ");
			sql.append(" WHERE mcode = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				map = new HashMap<>();
				map.put("home", rs.getString("home"));
				map.put("away", rs.getString("away"));
			} else {
				map = null;
			}
		} catch (Exception e) {
			System.out.println("팀 불러오기 실패" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return map;
	} // teamsread() end
	
	public Map<String, String>pointRead (String mcode) {
		Map<String, String> map = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT hpoint, apoint FROM matchresult ");
			sql.append(" WHERE mcode = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				map = new HashMap<>();
				map.put("hpoint", rs.getString("hpoint"));
				map.put("apoint", rs.getString("apoint"));
			} else {
				map = null;
			}
		} catch (Exception e) {
			System.out.println("점수 불러오기 실패" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return map;
	} // teamsread() end
	
	public TeamDTO teaminfo (String tcode) {
		TeamDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT tcode, tname, tarea, tid, ttime FROM team");
			sql.append(" WHERE tcode = ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new TeamDTO();
				dto.setTcode(rs.getString("tcode"));
				dto.setTname(rs.getString("tname"));
		        dto.setTarea(rs.getString("tarea"));
		        dto.setTid(rs.getString("tid"));
		        dto.setTtime(rs.getString("ttime"));
			} else {
				dto = null;
			}
		} catch (Exception e) {
			System.out.println("팀 정보 불러오기 실패" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	} // teamread() end
	
	public ArrayList<TMemberDTO> tmeminfo (String tcode) {
		ArrayList<TMemberDTO> tmeminfo = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT A.tid FROM tmember A");
			sql.append(" JOIN (SELECT tid FROM team WHERE tcode=?)B ");
			sql.append(" ON NOT A.tid=B.tid WHERE tcode=?"); 
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);
			pstmt.setString(2, tcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tmeminfo = new ArrayList<TMemberDTO>();
				do {
					TMemberDTO dto = new TMemberDTO();

					dto.setTid(rs.getString("tid"));

					tmeminfo.add(dto);
				} while (rs.next());
			} else {
				tmeminfo = null;
			}
		} catch (Exception e) {
			System.out.println("팀 정보 불러오기 실패" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return tmeminfo;
	} // tmeminfo() end
	
	public int insertResult(MatchResultDTO dto) {
		int res = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" INSERT INTO matchresult ");
			sql.append(" 	(mcode, hpoint, apoint)  ");
			sql.append(" VALUES (?, ?, ?) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMcode());
			pstmt.setString(2, dto.getHpoint());
			pstmt.setString(3, dto.getApoint());
			res=pstmt.executeUpdate();

			if (res == 1) {
				res=0;
				sql.delete(0, sql.length());
				sql.append(" UPDATE matching SET input='y' WHERE mcode=? ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, dto.getMcode());
				res=pstmt.executeUpdate();
			} // if end

		} catch (Exception e) {
			System.out.println("결과입력 실패!! : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return res;
	} // creatTeam() end
	
	public int insertAvg(PlayerAvgDTO dto) {
		  int res=0;
	    	
		  try{
	   	      con = dbopen.getConnection();
	   	      sql = new StringBuffer();  
	   	      sql.append(" INSERT INTO playeravg(mcode, shooting, pass, stamina, manner, comment, player, writer)");
	   	      sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
	   	      pstmt=con.prepareStatement(sql.toString());
	   	      pstmt.setInt(1, dto.getMcode()); 
	   	      pstmt.setInt(2, dto.getShooting());
	   	      pstmt.setInt(3, dto.getPass());
	   	      pstmt.setInt(4, dto.getStamina());
	   	      pstmt.setInt(5, dto.getManner());
	   	      pstmt.setString(6, dto.getComment());
	   	      pstmt.setString(7, dto.getPlayer());
	   	      pstmt.setString(8, dto.getWriter());
	   	      res=pstmt.executeUpdate();
	   	      
	    	  }catch(Exception e) {
	    	      System.out.println("선수 평가 실패 : "+e);
	    	    }finally{
	    	      dbclose.close(con, pstmt, rs);
	    	    }    
	    	 return res;
	     }
	
	public int avgcount (int mcode, String player, String id) {
		int cnt=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT count(player) FROM playeravg");
			sql.append(" WHERE mcode=? and writer=? and player=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, mcode);
			pstmt.setString(2, id);
			pstmt.setString(3, player);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt=rs.getInt("count(player)");
			}
		} catch (Exception e) {
			System.out.println("팀 정보 불러오기 실패" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return cnt;
	} // teamread() end
	
	public ArrayList<Map<String, String>> playeravg (String id, int mcode) {
		ArrayList<Map<String, String>> playeravg = null;
		Map<String, String> map = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT writer, shooting, stamina, manner, pass, comment, ");
			sql.append(" ifnull((shooting+stamina+manner+pass)/4, 0) as avg FROM playeravg");
			sql.append(" WHERE player=? and mcode=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setInt(2, mcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				playeravg = new ArrayList<Map<String, String>>();
				do {
					map = new HashMap<>();
					map.put("writer", rs.getString("writer"));
					map.put("shooting", rs.getString("shooting"));
					map.put("stamina", rs.getString("stamina"));
					map.put("manner", rs.getString("manner"));
					map.put("pass", rs.getString("pass"));
					map.put("comment", rs.getString("comment"));
					map.put("avg", rs.getString("avg"));
					playeravg.add(map);
				}while (rs.next());
			} else {
				playeravg = null;
			}
		} catch (Exception e) {
			System.out.println("평가내용 불러오기 실패" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return playeravg;
	}
	
	public ArrayList<Map<String, String>> myavg (String id, int nowPage, int recordPerPage) {
		ArrayList<Map<String, String>> myavg = null;
		Map<String, String> map = null;		
		int startRow = ((nowPage - 1) * recordPerPage) + 1;
		int endRow = nowPage * recordPerPage;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT writer, shooting, stamina, manner, pass, comment, avg FROM(");
			sql.append(" SELECT @ROWNUM :=@ROWNUM+1 AS ROW, writer, shooting, stamina, manner, pass, comment, avg FROM (");
			sql.append(" 	SELECT writer, shooting, stamina, manner, pass, comment, ");
			sql.append(" 	ifnull((shooting+stamina+manner+pass)/4, 0) as avg FROM playeravg");
			sql.append(" 	WHERE player=?");
			sql.append(" ) A, (SELECT @ROWNUM :=0) B) C");
			sql.append(" WHERE C.ROW>=" + startRow + " AND C.ROW<=" + endRow);
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				myavg = new ArrayList<Map<String, String>>();
				do {
					map = new HashMap<>();
					map.put("writer", rs.getString("writer"));
					map.put("shooting", rs.getString("shooting"));
					map.put("stamina", rs.getString("stamina"));
					map.put("manner", rs.getString("manner"));
					map.put("pass", rs.getString("pass"));
					map.put("comment", rs.getString("comment"));
					map.put("avg", rs.getString("avg"));
					myavg.add(map);
				}while (rs.next());
			} else {
				myavg = null;
			}
		} catch (Exception e) {
			System.out.println("평가내용 불러오기 실패" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return myavg;
	}
	
	public double totalavg (String id) {
		double cnt=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" select ifnull(sum(shooting+stamina+manner+pass)/(count(player)*4), 0) as avg");
			sql.append(" from playeravg where player=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
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
	} //totalavg() end
	
	public int count(String id) {
		int res = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT count(pgno) FROM playeravg where player=?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				res = rs.getInt("count(pgno)");
			} // if end

		} catch (Exception e) {
			System.out.println("평가 count() 실패");
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return res;
	}// count() end
	
}
