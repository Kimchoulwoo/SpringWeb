package kr.co.radesk.match.result;

public class PlayerAvgDTO {
	private int pgno;
	private int mcode;
	private int shooting;
	private int pass;
	private int stamina;
	private int manner;
	private String comment;
	private String player;
	private String writer;
	
	public PlayerAvgDTO() {}

	public int getPgno() {
		return pgno;
	}

	public void setPgno(int pgno) {
		this.pgno = pgno;
	}

	public int getMcode() {
		return mcode;
	}

	public void setMcode(int mcode) {
		this.mcode = mcode;
	}

	public int getShooting() {
		return shooting;
	}

	public void setShooting(int shooting) {
		this.shooting = shooting;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getManner() {
		return manner;
	}

	public void setManner(int manner) {
		this.manner = manner;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "PlayerAvgDTO [pgno=" + pgno + ", mcode=" + mcode + ", shooting=" + shooting + ", pass=" + pass
				+ ", stamina=" + stamina + ", manner=" + manner + ", comment=" + comment + ", player=" + player
				+ ", writer=" + writer + "]";
	}
}
