package kr.co.radesk.stadiumrating;

import org.springframework.web.multipart.MultipartFile;

public class StadiumRatingDTO {
	private int avgno;
	private int stacode;
	private int staaccess;
	private int staclean;
	private int stasafety;
	private int stafacility;
	private String stacoment;
	private String pcode;
	private String aid;

	public StadiumRatingDTO() {

		System.out.println("구장 평가 DTO 생성");
	}

	public int getAvgno() {
		return avgno;
	}

	public void setAvgno(int avgno) {
		this.avgno = avgno;
	}

	public int getStacode() {
		return stacode;
	}

	public void setStacode(int stacode) {
		this.stacode = stacode;
	}

	public int getStaaccess() {
		return staaccess;
	}

	public void setStaaccess(int staaccess) {
		this.staaccess = staaccess;
	}

	public int getStaclean() {
		return staclean;
	}

	public void setStaclean(int staclean) {
		this.staclean = staclean;
	}

	public int getStasafety() {
		return stasafety;
	}

	public void setStasafety(int stasafety) {
		this.stasafety = stasafety;
	}

	public int getStafacility() {
		return stafacility;
	}

	public void setStafacility(int stafacility) {
		this.stafacility = stafacility;
	}

	public String getStacoment() {
		return stacoment;
	}

	public void setStacoment(String stacoment) {
		this.stacoment = stacoment;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	@Override
	public String toString() {
		return "StadiumRatingDTO [avgno=" + avgno + ", stacode=" + stacode + ", staaccess=" + staaccess + ", staclean="
				+ staclean + ", stasafety=" + stasafety + ", stafacility=" + stafacility + ", stacoment=" + stacoment
				+ ", pcode=" + pcode + ", aid=" + aid + "]";
	}

}// class end
