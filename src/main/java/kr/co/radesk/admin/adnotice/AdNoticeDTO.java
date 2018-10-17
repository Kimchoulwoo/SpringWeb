package kr.co.radesk.admin.adnotice;

public class AdNoticeDTO {
	
	private int noticeno;
	private String subject;
	private String content;
	private int readcnt;
	private String regdt;

	
	//기본생성자
	public AdNoticeDTO() {}

	//getter,setter
	public int getNoticeno() {
		return noticeno;
	}

	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public String getRegdt() {
		return regdt;
	}

	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}

	
	
	
	
}//class end
