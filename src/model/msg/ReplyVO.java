package model.msg;

import java.util.Date;

/*rid int primary key,
	mid int,
	memid varchar(15),
	day date default sysdate,
	rmsg varchar(50),
	constraint msgrp foreign key (mid) references message (mid) on delete cascade*/

public class ReplyVO {
	private int rid;
	private int mid;
	private String memid;
	private Date day;
	private String rmsg;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
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
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public String getRmsg() {
		return rmsg;
	}
	public void setRmsg(String rmsg) {
		this.rmsg = rmsg;
	}
	@Override
	public String toString() {
		return "ReplyVO [rid=" + rid + ", mid=" + mid + ", memid=" + memid + ", day=" + day + ", rmsg=" + rmsg + "]";
	}
	
	
	
}
