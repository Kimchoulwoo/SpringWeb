package kr.co.radesk.basket;

public class BasketDTO {
	private int bno;
	private int stacode;
	private int grcode;
	private String pdate;
	private String ptime;
	private int cost;
	private String basketdate;
	private String pid;
	
	//기본 생성자
	public BasketDTO() {
	System.out.println("장바구니 DTO 생성");
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getBasketdate() {
		return basketdate;
	}

	public void setBasketdate(String basketdate) {
		this.basketdate = basketdate;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "BasketDTO [bno=" + bno + ", stacode=" + stacode + ", grcode=" + grcode + ", pdate=" + pdate + ", ptime="
				+ ptime + ", cost=" + cost + ", basketdate=" + basketdate + ", pid=" + pid + "]";
	}

	
	
	
}//class end
