package model.msg;

import java.util.Date;

/*mid int primary key,
memid varchar(15),
msg varchar(100),
favcount int default 0,
replycount int default 0,
day date default sysdate*/


public class MessageVO {

	private int mid;
	private String memid;
	private String msg;
	private int favcount;
	private int replycount;
	private Date day;
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getFavcount() {
		return favcount;
	}
	public void setFavcount(int favcount) {
		this.favcount = favcount;
	}
	public int getReplycount() {
		return replycount;
	}
	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	
	@Override
	public String toString() {
		return "MessageVO [mid=" + mid + ", memid=" + memid + ", msg=" + msg + ", favcount=" + favcount
				+ ", replycount=" + replycount + ", day=" + day + "]";
	}
	
	
	
}
