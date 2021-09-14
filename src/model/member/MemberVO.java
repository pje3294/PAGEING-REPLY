package model.member;

import java.util.Date;

/*memid varchar(15) primary key,
	name varchar(15),
	passwd varchar(10),
	day date default sysdate
*/

public class MemberVO {
	private String memid;
	private String name;
	private String passwd;
	private Date day;
	
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	
	@Override
	public String toString() {
		return "MemberVO [memid=" + memid + ", name=" + name + ", passwd=" + passwd + ", day=" + day + "]";
	}
	
	
	
	
}
