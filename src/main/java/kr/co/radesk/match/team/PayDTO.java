package kr.co.radesk.match.team;

public class PayDTO {
	private String pcode;
	private int stacode;
	private String pdate;
	private String ptime;
	private String staaddr1;
	private String staaddr2;
	
	public PayDTO() {}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public int getStacode() {
		return stacode;
	}

	public void setStacode(int stacode) {
		this.stacode = stacode;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public String getStaaddr1() {
		return staaddr1;
	}

	public void setStaaddr1(String staaddr1) {
		this.staaddr1 = staaddr1;
	}

	public String getStaaddr2() {
		return staaddr2;
	}

	public void setStaaddr2(String staaddr2) {
		this.staaddr2 = staaddr2;
	}

}
