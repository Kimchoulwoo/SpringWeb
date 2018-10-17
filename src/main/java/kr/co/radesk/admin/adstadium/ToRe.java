package kr.co.radesk.admin.adstadium;

import org.springframework.web.multipart.MultipartFile;

public class ToRe {
	private int stacode;
	private String id;
	private int cnt;
	
	public ToRe() {}

	public int getStacode() {
		return stacode;
	}

	public void setStacode(int stacode) {
		this.stacode = stacode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}