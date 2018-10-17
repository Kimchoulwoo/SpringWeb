package kr.co.radesk.match.club;

import org.springframework.web.multipart.MultipartFile;

public class ClubDTO {
	private String ccode;
	private String cid;
	private String cname;
	private String carea;
	private String ccontent;
	private String cposter;
	
	
	
	//스프링 파일 객체
	private MultipartFile posterMF;
	
	public MultipartFile getPosterMF() {
		return posterMF;
	}

	public void setPosterMF(MultipartFile posterMF) {
		this.posterMF = posterMF;
	}

	//------------------------------------------------------------------------
	//기본생성자
	public ClubDTO() {}
	
	//getter setter
	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCarea() {
		return carea;
	}

	public void setCarea(String carea) {
		this.carea = carea;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getCposter() {
		return cposter;
	}

	public void setCposter(String cposter) {
		this.cposter = cposter;
	}
	
	
	
	
}
