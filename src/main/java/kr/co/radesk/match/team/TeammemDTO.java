package kr.co.radesk.match.team;

public class TeammemDTO {
	private String tm_id;          //팀원 아이디
	private int tm_playeravg;    //팀원 평균 평점
	private String tm_birth;      //팀원 생년월일
	private String tm_cname;   //팀원 클럽
	
	//기본생성자
	public TeammemDTO() {}

	//getter setter
	public String getTm_id() {
		return tm_id;
	}

	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}

	public int getTm_playeravg() {
		return tm_playeravg;
	}

	public void setTm_playeravg(int tm_playeravg) {
		this.tm_playeravg = tm_playeravg;
	}

	public String getTm_birth() {
		return tm_birth;
	}

	public void setTm_birth(String tm_birth) {
		this.tm_birth = tm_birth;
	}

	public String getTm_cname() {
		return tm_cname;
	}

	public void setTm_cname(String tm_cname) {
		this.tm_cname = tm_cname;
	}
	
	
	
	
}//class end
