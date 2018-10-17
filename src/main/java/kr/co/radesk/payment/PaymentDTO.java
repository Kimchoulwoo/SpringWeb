package kr.co.radesk.payment;

public class PaymentDTO {
	private String pcode;
	private int stacode;
	private int grcode;
	private String pdate;
	private String ptime;
	private String pid;
	private int cost;
	private String costdate;
	private String avg;

	public PaymentDTO() {
		System.out.println("搬力 包访 CONT 积己");
	}

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

	public int getGrcode() {
		return grcode;
	}

	public void setGrcode(int grcode) {
		this.grcode = grcode;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getCostdate() {
		return costdate;
	}

	public void setCostdate(String costdate) {
		this.costdate = costdate;
	}

	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}

	@Override
	public String toString() {
		return "PaymentDTO [pcode=" + pcode + ", stacode=" + stacode + ", grcode=" + grcode + ", pdate=" + pdate
				+ ", ptime=" + ptime + ", pid=" + pid + ", cost=" + cost + ", costdate=" + costdate + ", avg=" + avg
				+ "]";
	}
	
	
	
	
}