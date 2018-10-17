package kr.co.radesk.match.matching;

public class MatchingDTO {
	private int mcode;
	private String home;
	private String away;
	
	public MatchingDTO() {}

	public int getMcode() {
		return mcode;
	}

	public void setMcode(int mcode) {
		this.mcode = mcode;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}

	@Override
	public String toString() {
		return "MatchingDTO [mcode=" + mcode + ", home=" + home + ", away=" + away + "]";
	}
	
}
