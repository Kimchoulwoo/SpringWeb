package kr.co.radesk.match.team;

public class TeamDTO {
	private String tcode;
	private String tarea;
	private String tname;
	private String tid;
	private int player;
	private String pcode;
	private String tmatch;
	private String ttime;
	private String cposter;

	

	private String ccode;
	private String cname;

	// 기본 생성자
	public TeamDTO() {
	}

	// getter setter
	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getTmatch() {
		return tmatch;
	}

	public void setTmatch(String tmatch) {
		this.tmatch = tmatch;
	}

	public String getTtime() {
		return ttime;
	}

	public void setTtime(String ttime) {
		this.ttime = ttime;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCposter() {
		return cposter;
	}

	public void setCposter(String cposter) {
		this.cposter = cposter;
	}

}// class end
