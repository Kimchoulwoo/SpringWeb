package kr.co.radesk.match.team;

public class TMemberDTO {
	private int tmemno;
	private String tcode;
	private String tid;

	private String cname;

	public TMemberDTO() {}

	public int getTmemno() {
		return tmemno;
	}

	public void setTmemno(int tmemno) {
		this.tmemno = tmemno;
	}

	public String getTcode() {
		return tcode;
	}

	public void setTcode(String tcode) {
		this.tcode = tcode;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
}
