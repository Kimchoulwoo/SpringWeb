package kr.co.radesk.match.result;

public class MatchResultDTO {
	private int mrno;
	private String mcode;
	private String hpoint;
	private String apoint;
	
	public MatchResultDTO() {}

	public int getMrno() {
		return mrno;
	}

	public void setMrno(int mrno) {
		this.mrno = mrno;
	}

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public String getHpoint() {
		return hpoint;
	}

	public void setHpoint(String hpoint) {
		this.hpoint = hpoint;
	}

	public String getApoint() {
		return apoint;
	}

	public void setApoint(String apoint) {
		this.apoint = apoint;
	}

	@Override
	public String toString() {
		return "MatchResultDTO [mrno=" + mrno + ", mcode=" + mcode + ", hpoint=" + hpoint + ", apoint=" + apoint + "]";
	}
	
}
