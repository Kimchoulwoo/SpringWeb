package kr.co.radesk.admin.adground;

public class AdgroundDTO {

	private int stacode;
	private int grcode;
	private String grname;
	private String grday;
	private String grtime;
	private String grlevel;
	
	public AdgroundDTO() {}
	
	public int getStacode() {
		return stacode;
	}
	public void setStacode(int stacode) {
		this.stacode = stacode;
	}
	public int getGrcode() {
		return grcode;
	}
	public void setGrcode(int grcode) {
		this.grcode = grcode;
	}
	public String getGrname() {
		return grname;
	}
	public void setGrname(String grname) {
		this.grname = grname;
	}
	public String getGrday() {
		return grday;
	}
	public void setGrday(String grday) {
		this.grday = grday;
	}
	public String getGrtime() {
		return grtime;
	}
	public void setGrtime(String grtime) {
		this.grtime = grtime;
	}
	public String getGrlevel() {
		return grlevel;
	}
	public void setGrlevel(String grlevel) {
		this.grlevel = grlevel;
	}
	@Override
	public String toString() {
		return "AdgroundDTO [stacode=" + stacode + ", grcode=" + grcode + ", grname=" + grname + ", grday=" + grday
				+ ", grtime=" + grtime + ", grlevel=" + grlevel + "]";
	}

	


}// class end
