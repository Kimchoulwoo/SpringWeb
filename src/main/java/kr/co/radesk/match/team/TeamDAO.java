package kr.co.radesk.match.team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.radesk.match.club.CmemberDTO;
import kr.co.radesk.match.team.TMemberDTO;
import kr.co.radesk.payment.PaymentDTO;
import net.utility.DBClose;
import net.utility.DBOpen;

@Component
public class TeamDAO {

	@Autowired
	DBOpen dbopen;

	@Autowired
	DBClose dbclose;

	public TeamDAO() {
		System.out.println("TeamDAO() 실행");
	}// TeamDAO() end

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;

	ArrayList<TeamDTO> list = null;
	ArrayList<TeammemDTO> tm_list = null;
	ArrayList<TMemberDTO> tmlist = null; // 창재형
	ArrayList<PaymentDTO> plist = null; // 창우형
	ArrayList<CmemberDTO> clist = null; // 창우형

	public String ccode(String id) {
		String ccode = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT ccode");
			sql.append(" FROM member");
			sql.append(" WHERE id = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ccode = (rs.getString("ccode"));
			} else {
				ccode = null;
			}
		} catch (Exception e) {
			System.out.println("ccode 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return ccode;
	} // read() end

	public ArrayList<PaymentDTO> plist(String id) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT pcode, pdate, ptime, stacode  ");
			sql.append(" FROM (SELECT T.pcode as team_pcode, P.pcode, P.pid, P.pdate, P.ptime, P.stacode ");
			sql.append(" 	  FROM team T right JOIN payment P ");
			sql.append("	  on T.pcode=P.pcode) A ");
			sql.append("	            WHERE A.team_pcode is null AND A.pid=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				plist = new ArrayList<PaymentDTO>();
				do {
					PaymentDTO dto = new PaymentDTO();
					dto.setPcode(rs.getString("pcode"));
					dto.setPdate(rs.getString("pdate"));
					dto.setPtime(rs.getString("ptime"));
					dto.setStacode(rs.getInt("stacode"));
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
	} // s_id 의 결제정보 불러오기

	public PayDTO payread(String pcode) {
		PayDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT stadium.staaddr1,stadium.staaddr2, payment.pdate, payment.ptime");
			sql.append(" FROM stadium JOIN payment");
			sql.append(" ON stadium.stacode = payment.stacode");
			sql.append(" WHERE pcode=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new PayDTO();
				dto.setStaaddr1(rs.getString(1));
				dto.setStaaddr2(rs.getString(2));
				dto.setPdate(rs.getString(3));
				dto.setPtime(rs.getString(4));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return dto;
	} // read() end

	public ArrayList<CmemberDTO> findCmember(String id) {
		try {
			con = dbopen.getConnection(); // DB연결
			sql = new StringBuffer();

			sql.append(" SELECT AA.cid FROM (");
			sql.append(" SELECT cmember.cid FROM member JOIN cmember ");
			sql.append(" ON member.ccode = cmember.ccode WHERE member.id = ?");
			sql.append(" )AA WHERE AA.cid <> ?");

			pstmt = con.prepareStatement(sql.toString()); // SQL문으로 변환
			pstmt.setString(1, id);
			pstmt.setString(2, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				clist = new ArrayList<>();
				do {
					CmemberDTO dto = new CmemberDTO();
					dto.setCid(rs.getString("cid"));

					clist.add(dto);
				} while (rs.next());

			}

		} catch (Exception e) {
			System.out.println("실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return clist;
	} // 클럽원 리스트 불러오기

	public String createTeam(TeamDTO dto) {
		int res = 0;
		String tcode = "";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" INSERT INTO team(tcode,tarea,tname,tid,player,pcode,ttime  ) ");
			sql.append(" VALUE((  ");
			sql.append(" 			select concat('t',(cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8))), ");
			sql.append(" 			(select if( ");
			sql.append(" 					   ( ");
			sql.append(" 					    select ifnull(max(tcode) COLLATE utf8_general_ci, (select concat('t',(select cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)))) ) ) from team as T ");
			sql.append(" 					    where cast(DATE_FORMAT(NOW(), '%Y%m%d') as char(8)) = (select substring(ifnull(max(tcode),1),2,8) from team as T2) COLLATE utf8_general_ci ");
			sql.append(" 					   ) =(select max(tcode) from team as T3) COLLATE utf8_general_ci, ");
			sql.append(" 					   (select right(cast(((select right(max(tcode),3) from team as T4)+1)+1000 as char(4)),3)), ");
			sql.append(" 					   '001' ");
			sql.append(" 					  ) ");
			sql.append(" 			)) ");
			sql.append(" ) ");
			sql.append(" ,?,?,?,?,?,?) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTarea());
			pstmt.setString(2, dto.getTname());
			pstmt.setString(3, dto.getTid());
			pstmt.setInt(4, dto.getPlayer());
			pstmt.setString(5, dto.getPcode());
			pstmt.setString(6, dto.getTtime());
			res = pstmt.executeUpdate();

			if (res == 1) {
				sql.delete(0, sql.length());
				sql.append(" SELECT max(tcode) FROM team WHERE tid=? ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, dto.getTid());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					tcode = rs.getString("max(tcode)");
				} // if end

			} // if end

		} catch (Exception e) {
			System.out.println("팀 생성 실패!! : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return tcode;
	} // creatTeam() end

	public int insertMember(TMemberDTO dto) {
		int res = 0;

		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" INSERT INTO tmember(tcode, tid)");
			sql.append(" VALUES(?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTcode());
			pstmt.setString(2, dto.getTid());
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("팀 멤버 추가 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return res;
	}// insertMember() end

	public ArrayList<TeamDTO> list(TeamDTO dto) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT distinct tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter FROM club D JOIN ");
			sql.append(" (SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, ccode ");
			sql.append(" FROM team A JOIN (SELECT ccode, cid FROM cmember) B ");
			sql.append(" ON A.tid=B.cid) C ON C.ccode=D.ccode ");
			sql.append(" WHERE ttime > DATE_FORMAT(now(),'%Y%m%d%H') ");
			sql.append(" ORDER BY tcode DESC ");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<TeamDTO>();
				do {
					dto = new TeamDTO();
					dto.setTcode(rs.getString("tcode"));
					dto.setTarea(rs.getString("tarea"));
					dto.setTname(rs.getString("tname"));
					dto.setTid(rs.getString("tid"));
					dto.setPlayer(rs.getInt("player"));
					dto.setPcode(rs.getString("pcode"));
					dto.setTmatch(rs.getString("tmatch"));
					dto.setTtime(rs.getString("ttime"));
					dto.setCposter(rs.getString("cposter"));
					list.add(dto);
				} while (rs.next());
			} // if end
		} catch (Exception e) {
			System.out.println("팀 list() 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return list;
	}// list() end

	public int count(String col, String word) {
		int res = 0;
		word = word.trim();
		String str = "";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT count(tcode) FROM team WHERE ttime > DATE_FORMAT(now(),'%Y%m%d%H') ");
			if (word.length() >= 1) {
				if (col.equals("tid")) {
					str += " WHERE tid LIKE '%" + word + "%'";
				} else if (col.equals("tarea")) {
					str += " WHERE tarea LIKE '%" + word + "%'";
				} else if (col.equals("ttime")) {
					str += " WHERE ttime LIKE '%" + word + "%'";
				}
				sql.append(str);
			} // if end
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				res = rs.getInt("count(tcode)");
			} // if end

		} catch (Exception e) {
			System.out.println("팀 count() 실패");
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return res;
	}// count() end

	public ArrayList<TeammemDTO> teammemlist(String tcode) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			//sql.append(" SELECT DISTINCT F.id, F.birth, F.cname, avg FROM team H JOIN  ");
			sql.append(" SELECT DISTINCT D.id, D.birth, D.cname, ifnull(sum(E.shooting+E.stamina+E.manner+E.pass)/(count(D.id)*4),0) as avg ");
			sql.append("  FROM (SELECT DISTINCT A.id, A.birth, B.cname ");
			sql.append("		FROM (SELECT DISTINCT M.id, M.birth, M.ccode ");
			sql.append("	  		  FROM tmember TM JOIN member M ");
			sql.append("	          on TM.tid=M.id where tcode =?) A ");
			sql.append("	    JOIN club B ON A.ccode=B.ccode) D ");
			sql.append(" LEFT JOIN playeravg E ON D.id=E.player ");
			sql.append(" group by D.id ");
			//sql.append(" where tcode='t20180315001' AND ttime > DATE_FORMAT(now(),'%Y%m%d%H') ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tm_list = new ArrayList<>();
				do {
					TeammemDTO tmdto = new TeammemDTO();
					// 팀 맴버정보 리스트에 저장
					tmdto.setTm_id(rs.getString("D.id"));
					tmdto.setTm_birth(rs.getString("D.birth"));
					tmdto.setTm_cname(rs.getString("D.cname"));
					tmdto.setTm_playeravg(rs.getInt("avg"));
					tm_list.add(tmdto);
				} while (rs.next());
			} else {
				tm_list = null;
			} // if end

		} catch (Exception e) {
			System.out.println("teammemlist() 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return tm_list;
	}// teammemlist() end

	public Integer teamavg(String tcode) {
		Integer teamavg = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT sum(F.avg)/count(F.id) as teamavg ");
			sql.append(" FROM(SELECT DISTINCT D.id, D.birth, D.cname, sum(E.shooting+E.stamina+E.manner+E.pass)/(count(D.id)*4) as avg ");
			sql.append("	  FROM (SELECT DISTINCT A.id, A.birth, B.cname ");
			sql.append("			FROM (SELECT DISTINCT M.id, M.birth, M.ccode ");
			sql.append("		  		  FROM tmember TM JOIN member M ");
			sql.append("		          on TM.tid=M.id where tcode =?) A ");
			sql.append("		    JOIN club B ON A.ccode=B.ccode) D ");
			sql.append(" JOIN playeravg E ON D.id=E.player ");
			sql.append(" group by D.id) F ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				teamavg = rs.getInt("teamavg");
			}
		} catch (Exception e) {
			System.out.println("teamavg 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return teamavg;

	}// teamavg() end

	public ArrayList<TeamDTO> list(TeamDTO dto, String col, String word, int nowPage, int recordPerPage) {
		String str = "";
		int startRow = ((nowPage - 1) * recordPerPage) + 1;
		int endRow = nowPage * recordPerPage;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();

			if (word.length() == 0) {
				sql.append(" SELECT distinct tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter ");
				sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter ");
				sql.append(" 		  FROM(SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter ");
				sql.append(" 		           FROM club D JOIN (SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, ccode ");
				sql.append(" 										FROM team A JOIN (SELECT ccode, cid FROM cmember) B ");
				sql.append(" 				   ON A.tid=B.cid) C ON C.ccode=D.ccode ");
				sql.append(" 		  WHERE ttime > DATE_FORMAT(now(),'%Y%m%d%H') ");
				sql.append(" 		  ORDER BY tcode DESC ");
				sql.append(" 		  ) E, (SELECT @ROWNUM :=0) F)G ");
				sql.append("  WHERE G.ROW>=" + startRow + " AND G.ROW<="+ endRow);

			} else {
				sql.append(" SELECT distinct tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter ");
				sql.append(" FROM(SELECT @ROWNUM :=@ROWNUM+1 AS ROW, tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter ");
				sql.append(" 		  FROM(SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, cposter ");
				sql.append(" 		           FROM club D JOIN (SELECT tcode, tarea, tname, tid, player, pcode, tmatch, ttime, ccode ");
				sql.append(" 										FROM team A JOIN (SELECT ccode, cid FROM cmember) B ");
				sql.append(" 				   ON A.tid=B.cid) C ON C.ccode=D.ccode ");
				sql.append(" 		  WHERE ttime > DATE_FORMAT(now(),'%Y%m%d%H') ");
				if (col.equals("tid")) {
					str += "WHERE tid LIKE '%" + word + "%'";
				} else if (col.equals("tarea")) {
					str += "WHERE tarea LIKE '%" + word + "%'";
				} else if (col.equals("ttime")) {
					str += "WHERE ttime LIKE '%" + word + "%'";
				}else if (col.equals("tname")) {
					str += "WHERE tname LIKE '%" + word + "%'";
				}else if (col.equals("player")) {
					str += "WHERE player LIKE '%" + word + "%'";
				} // if end
				sql.append(str);
				sql.append(" 		  ORDER BY tcode DESC ");
				sql.append(" 		  ) E, (SELECT @ROWNUM :=0) F)G ");
				sql.append("  WHERE G.ROW>=" + startRow + " AND G.ROW<="+ endRow);
			} // if end

			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<TeamDTO>();
				do {
					dto = new TeamDTO();
					dto.setTcode(rs.getString("tcode"));
					dto.setTarea(rs.getString("tarea"));
					dto.setTname(rs.getString("tname"));
					dto.setTid(rs.getString("tid"));
					dto.setPlayer(rs.getInt("player"));
					dto.setPcode(rs.getString("pcode"));
					dto.setTmatch(rs.getString("tmatch"));
					dto.setTtime(rs.getString("ttime"));
					dto.setCposter(rs.getString("cposter"));
					list.add(dto);
				} while (rs.next());
			} else {
				list = null;
			} // if end
		} catch (Exception e) {
			System.out.println("팀 list() 실패!!" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return list;
	}// list() end

	// 팀장 정보
	public TeamDTO teamLeader(String tcode) {
		TeamDTO dto = new TeamDTO();

		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT A.tcode as tcode, A.tarea as tarea, A.tname as tname, A.tid as tid, A.player as player, A.pcode as pcode, A.tmatch as tmatch, A.ttime as ttime, B.ccode as ccode, B.cname as cname");
			sql.append(" FROM (SELECT tcode, tarea, tname, tid,  player, pcode, tmatch, ttime");
			sql.append(" FROM team");
			sql.append(" WHERE tcode=?) A JOIN");
			sql.append(" (SELECT m.id, c.ccode, c.cname");
			sql.append(" FROM member m JOIN club c ON m.ccode = c.ccode) B");
			sql.append(" ON A.tid = B.id");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setTcode(rs.getString("tcode"));
				dto.setTarea(rs.getString("tarea"));
				dto.setTname(rs.getString("tname"));
				dto.setTid(rs.getString("tid"));
				dto.setPlayer(rs.getInt("player"));
				dto.setPcode(rs.getString("pcode"));
				dto.setTmatch(rs.getString("tmatch"));
				dto.setTtime(rs.getString("ttime"));
				dto.setCcode(rs.getString("ccode"));
				dto.setCname(rs.getString("cname"));
			} else {
				dto = null;
			}
		} catch (Exception e) {
			System.out.println(" teamLeader() 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}

		return dto;
	}// teamLeader() end

	// 팀원 정보
	public ArrayList<TMemberDTO> read(String tid, String tcode) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT B.tmemno AS tmemno, B.tcode AS tcode, B.tid AS tid, C.id as id, C.ccode as ccode, C.cname as cname");
			sql.append(" FROM (SELECT A.tmemno, A.tcode, A.tid FROM (SELECT tm.tmemno, tm.tcode, tm.tid");
			sql.append(" FROM team t JOIN tmember tm");
			sql.append(" ON t.tcode = tm.tcode");
			sql.append(" ORDER BY tm.tmemno DESC) A where A.tid <> ? AND A.tcode = ?) B");
			sql.append(" JOIN (SELECT m.id, c.ccode, c.cname");
			sql.append(" FROM member m JOIN club c ON m.ccode = c.ccode) C");
			sql.append(" ON B.tid = C.id");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tid);
			pstmt.setString(2, tcode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				tmlist = new ArrayList<TMemberDTO>();
				do {
					TMemberDTO dto = new TMemberDTO(); // 한줄 dto객체에 담기
					dto.setTmemno(rs.getInt("tmemno"));
					dto.setTcode(rs.getString("tcode"));
					dto.setTid(rs.getString("tid"));
					dto.setCname(rs.getString("cname"));

					tmlist.add(dto);
				} while (rs.next());
			} else {
				tmlist = null;
			}
		} catch (Exception e) {
			System.out.println("팀원 조회 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}

		return tmlist;
	}// read() end

	// 현재원 카운트
	public int tmCount(String tcode) {
		int tcount = 1;

		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT COUNT(tcode) AS cnt");
			sql.append(" FROM tmember WHERE tcode = ?");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);

			rs = pstmt.executeQuery();

			if (rs.next())
				tcount = rs.getInt("cnt");
		} catch (Exception e) {
			System.out.println("팀원 수 가져오기 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}

		return tcount;
	}// tmCount() end

	// 팀원 등록
	public int tMemInsert(TMemberDTO dto) {
		int res = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("INSERT INTO tmember(tcode, tid)");
			sql.append(" VALUES(?, ?)");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTcode());
			pstmt.setString(2, dto.getTid());

			res = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("팀원 등록 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt);
		}
		return res;
	}// tMemInsert() end

	// 팀원 탈퇴
	public void tMemDelete(int tmemno) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("DELETE FROM tmember");
			sql.append(" WHERE tmemno=?");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, tmemno);

			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("팀원 삭제 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt);
		}
	}// tMemDelete() end

	// 팀 삭제
	public void tDelete(String tcode) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("DELETE FROM team");
			sql.append(" WHERE tcode=?");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);

			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("팀 삭제 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
	}// tDelete() end

	// 팀원 전체 삭제
	public void tMemDelete(String tcode) {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("DELETE FROM tmember");
			sql.append(" WHERE tcode=?");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("팀원 전체 삭제 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt);
		}
	}// tMemDelete() end

	// 클럽 유무 확인
	public String cCodeCheck(String s_id) {
		String ccode = "";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT ccode from member");
			sql.append(" WHERE id = ?");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, s_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ccode = rs.getString("ccode");
			} else {
				ccode = null;
			}
		} catch (Exception e) {
			System.out.println("클럽 코드 가져오기 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return ccode;
	}// cCodeCheck() end

	// 가입 된 ID 확인
	public String joinIdCheck(String tcode, String s_id) {
		String joinid = "";
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append("SELECT tmemno, tcode, tid FROM tmember");
			sql.append(" WHERE tcode=? AND tid=?");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tcode);
			pstmt.setString(2, s_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				joinid = rs.getString("tid");
			} else {
				joinid = null;
			}
		} catch (Exception e) {
			System.out.println("가입 된 ID 가져오기 실패 : " + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return joinid;
	}// joinIdCheck() end
	
	// 매칭 대기 전환 tmatch = 'n' -> 'y'
		public int tmatch(String tcode) {
			int res = 0;

			try {
				con = dbopen.getConnection();
				sql = new StringBuffer();
				sql.append("UPDATE team");
				sql.append(" SET tmatch='y'");
				sql.append(" WHERE tcode=?");
				
				pstmt = con.prepareStatement(sql.toString()); // 쿼리문 변환
				
				pstmt.setString(1, tcode); // 1번째 물음표

				res = pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("tmatch 수정 실패 : " + e);
			} finally {
				dbclose.close(con, pstmt);
			}
			
			return res;
		}//tmatch() end
		
		// mbasket push
		public int mbasket(String tcode) {
			int res = 0;
			try {
				con = dbopen.getConnection();
				sql = new StringBuffer();
				sql.append("INSERT INTO mbasket(home, pcode)");
			    sql.append(" VALUES(?, (select ifnull(pcode, 'away') from team where tcode=?))");

				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, tcode);
				pstmt.setString(2, tcode);
				res = pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("매칭 임시 등록 실패 : " + e);
			} finally {
				dbclose.close(con, pstmt);
			}
			return res;
		}//mbasket() end

}// class end
