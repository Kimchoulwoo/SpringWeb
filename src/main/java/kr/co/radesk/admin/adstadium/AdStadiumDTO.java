package kr.co.radesk.admin.adstadium;

import org.springframework.web.multipart.MultipartFile;

public class AdStadiumDTO {
	private int stacode;
	private String staname;
	private String stainfo;
	private String stadate;
	private String staaddr1;
	private String staaddr2;
	private String staaddr3;
	private String id;
	private int stapay;
	private int staopen;
	private int staclose;
	private String stalevel;
	private String poster1;
	private String poster2;
	private String poster3;
	private String poster4;
	private String poster5;
	
	//------------------------------------------------------------------------
	//스프링 파일 객체
		private MultipartFile posterMF1;
		private MultipartFile posterMF2;
		private MultipartFile posterMF3;
		private MultipartFile posterMF4;
		private MultipartFile posterMF5;
		
	
	//------------------------------------------------------------------------

		public MultipartFile getPosterMF1() {
			return posterMF1;
		}

		public void setPosterMF1(MultipartFile posterMF1) {
			this.posterMF1 = posterMF1;
		}

		public MultipartFile getPosterMF2() {
			return posterMF2;
		}

		public void setPosterMF2(MultipartFile posterMF2) {
			this.posterMF2 = posterMF2;
		}

		public MultipartFile getPosterMF3() {
			return posterMF3;
		}

		public void setPosterMF3(MultipartFile posterMF3) {
			this.posterMF3 = posterMF3;
		}

		public MultipartFile getPosterMF4() {
			return posterMF4;
		}

		public void setPosterMF4(MultipartFile posterMF4) {
			this.posterMF4 = posterMF4;
		}

		public MultipartFile getPosterMF5() {
			return posterMF5;
		}

		public void setPosterMF5(MultipartFile posterMF5) {
			this.posterMF5 = posterMF5;
		}

		public AdStadiumDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public int getStacode() {
			return stacode;
		}

		public void setStacode(int stacode) {
			this.stacode = stacode;
		}

		public String getStaname() {
			return staname;
		}

		public void setStaname(String staname) {
			this.staname = staname;
		}

		public String getStainfo() {
			return stainfo;
		}

		public void setStainfo(String stainfo) {
			this.stainfo = stainfo;
		}

		public String getStadate() {
			return stadate;
		}

		public void setStadate(String stadate) {
			this.stadate = stadate;
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

		public String getStaaddr3() {
			return staaddr3;
		}

		public void setStaaddr3(String staaddr3) {
			this.staaddr3 = staaddr3;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getStapay() {
			return stapay;
		}

		public void setStapay(int stapay) {
			this.stapay = stapay;
		}

		public int getStaopen() {
			return staopen;
		}

		public void setStaopen(int staopen) {
			this.staopen = staopen;
		}

		public int getStaclose() {
			return staclose;
		}

		public void setStaclose(int staclose) {
			this.staclose = staclose;
		}

		public String getStalevel() {
			return stalevel;
		}

		public void setStalevel(String stalevel) {
			this.stalevel = stalevel;
		}

		public String getPoster1() {
			return poster1;
		}

		public void setPoster1(String poster1) {
			this.poster1 = poster1;
		}

		public String getPoster2() {
			return poster2;
		}

		public void setPoster2(String poster2) {
			this.poster2 = poster2;
		}

		public String getPoster3() {
			return poster3;
		}

		public void setPoster3(String poster3) {
			this.poster3 = poster3;
		}

		public String getPoster4() {
			return poster4;
		}

		public void setPoster4(String poster4) {
			this.poster4 = poster4;
		}

		public String getPoster5() {
			return poster5;
		}

		public void setPoster5(String poster5) {
			this.poster5 = poster5;
		}

		@Override
		public String toString() {
			return "AdStadiumDTO [stacode=" + stacode + ", staname=" + staname + ", stainfo=" + stainfo + ", stadate="
					+ stadate + ", staaddr1=" + staaddr1 + ", staaddr2=" + staaddr2 + ", staaddr3=" + staaddr3 + ", id="
					+ id + ", stapay=" + stapay + ", staopen=" + staopen + ", staclose=" + staclose + ", stalevel="
					+ stalevel + ", poster1=" + poster1 + ", poster2=" + poster2 + ", poster3=" + poster3 + ", poster4="
					+ poster4 + ", poster5=" + poster5 + ", posterMF1=" + posterMF1 + ", posterMF2=" + posterMF2
					+ ", posterMF3=" + posterMF3 + ", posterMF4=" + posterMF4 + ", posterMF5=" + posterMF5 + "]";
		}


	

		
	
	
	
	
}//class end