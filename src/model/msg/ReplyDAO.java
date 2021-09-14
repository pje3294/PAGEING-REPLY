package model.msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.common.JNDI;

/*rid int primary key,
	mid int,
	memid varchar(15),
	day date default sysdate,
	rmsg varchar(50),
	constraint msgrp foreign key (mid) references message (mid) on delete cascade*/

public class ReplyDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	public boolean insert(ReplyVO vo) {
		conn = JNDI.getConnection();
		//insert into reply values((select nvl(max(rid),0)+1 from reply),1,'timo',sysdate,'´ñ±Û1');
		String sql ="insert into reply values ((select nvl(max(rid),0)+1 from reply), ?,?,sysdate,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMid());
			pstmt.setString(2, vo.getMemid());
			pstmt.setString(3, vo.getRmsg());
			pstmt.executeUpdate();
			
			
			// message DB¿¡ replyCountµµ 1Áõ°¡µÇ¾ßµÊ
			String sql2= "update message set replycount= replycount+1 where mid=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, vo.getMid());
			pstmt.executeUpdate();
			System.out.println("´ñ±Û¼ö + 1¿Ï·á");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;
		
	}
	
	public boolean delete(ReplyVO vo) {
		conn = JNDI.getConnection();
		String sql = "delete from reply where rid=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getRid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return true;
	
	}
	
	
	
	
	
	
}
