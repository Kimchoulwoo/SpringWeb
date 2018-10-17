package kr.co.radesk.match.club;

public class CmemberDTO {
	private int cmemno;
	private String ccode;
	private String cid;
	
	public CmemberDTO() {}

	public int getCmemno() {
		return cmemno;
	}

	public void setCmemno(int cmemno) {
		this.cmemno = cmemno;
	}

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

	@Override
	public String toString() {
		return "CmemberDTO [cmemno=" + cmemno + ", ccode=" + ccode + ", cid=" + cid + "]";
	}
	
	

}
